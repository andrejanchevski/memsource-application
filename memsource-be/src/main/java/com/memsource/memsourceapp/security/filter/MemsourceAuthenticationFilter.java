package com.memsource.memsourceapp.security.filter;

import com.memsource.memsourceapp.domain.response.UserResponse;
import com.memsource.memsourceapp.events.PersistAuthenticationTokenEvent;
import com.memsource.memsourceapp.exceptions.UserNotFoundException;
import com.memsource.memsourceapp.http_client.ProjectsHolderClient;
import com.memsource.memsourceapp.http_client.request.AuthenticateUserRequest;
import com.memsource.memsourceapp.http_client.response.AuthenticatedUserLoginResponse;
import com.memsource.memsourceapp.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.header.HeaderWriterFilter;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class MemsourceAuthenticationFilter extends GenericFilterBean {

    private final ProjectsHolderClient projectsHolderClient;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final UserMapper userMapper;

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if (httpServletRequest.getRequestURI().equals("/api/login")) {
            AuthenticateUserRequest authenticateUserRequest =
                    AuthenticateUserRequest.builder()
                            .userName(servletRequest.getParameter("username"))
                            .password(servletRequest.getParameter("password"))
                            .build();
            ResponseEntity<AuthenticatedUserLoginResponse> responseObject =
                    projectsHolderClient.authenticateUser(authenticateUserRequest);
            if (responseObject.getStatusCode().equals(HttpStatus.OK)) {
                applicationEventPublisher.publishEvent(
                        new PersistAuthenticationTokenEvent(this,
                                Objects.requireNonNull(responseObject.getBody()).getToken()));
                try{
                    UserResponse userResponse = userMapper
                            .findUserByUserName(responseObject.getBody().getUser().getUserName());
                    filterChain.doFilter(servletRequest, servletResponse);
                }catch (UserNotFoundException userNotFoundException){
                    userMapper.saveUser(responseObject.getBody(), servletRequest.getParameter("password"));
                }
            }
        }
    }
}
