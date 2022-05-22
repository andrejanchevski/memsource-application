package com.memsource.memsourceapp.security.filter;

import com.memsource.memsourceapp.exceptions.PersistingTokenToFileException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        }
        catch (FeignException.FeignClientException feignClientException){
            log.error("Memsource ApiClient Communication Error");
            log.error(feignClientException.getMessage());
            response.setStatus(feignClientException.status());
            response.getWriter().write(feignClientException.getMessage());
        } catch (RuntimeException runtimeException){
            log.error("User Authentication Error");
            log.error(runtimeException.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(runtimeException.getMessage());
        }
    }
}
