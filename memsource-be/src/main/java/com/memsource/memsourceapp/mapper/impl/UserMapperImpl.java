package com.memsource.memsourceapp.mapper.impl;

import com.memsource.memsourceapp.domain.Role;
import com.memsource.memsourceapp.domain.User;
import com.memsource.memsourceapp.domain.dto.UserDTO;
import com.memsource.memsourceapp.domain.response.UserResponse;
import com.memsource.memsourceapp.http_client.response.AuthenticatedUserLoginResponse;
import com.memsource.memsourceapp.mapper.UserMapper;
import com.memsource.memsourceapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    private final UserService userService;

    @Override
    public UserResponse findUserByUserName(String userName) {
        User user = userService.findUserByUserName(userName);
        return UserResponse.builder()
                .userId(user.getId())
                .userFirstName(user.getName())
                .userName(user.getUserName())
                .authenticationToken(user.getActiveAuthenticationToken())
                .roles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .build();
    }

    @Override
    public UserResponse saveUser(AuthenticatedUserLoginResponse authenticatedUserLoginResponse,
                                 String password) {
        User user = userService.saveUser(
                this.mapApiClientAuthenticatedUserLoginResponseToUserDTO(authenticatedUserLoginResponse, password));
        return UserResponse.builder()
                .userId(user.getId())
                .userFirstName(user.getName())
                .userName(user.getUserName())
                .authenticationToken(user.getActiveAuthenticationToken())
                .roles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .build();
    }

    private UserDTO mapApiClientAuthenticatedUserLoginResponseToUserDTO(
            AuthenticatedUserLoginResponse authenticatedUserLoginResponse, String password) {
        return UserDTO.builder()
                .name(authenticatedUserLoginResponse.getUser().getFirstName())
                .userName(authenticatedUserLoginResponse.getUser().getUserName())
                .password(password)
                .is_active(true)
                .isExpired(false)
                .isLocked(false)
                .authenticationToken(authenticatedUserLoginResponse.getToken())
                .build();
    }
}
