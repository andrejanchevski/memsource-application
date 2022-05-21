package com.memsource.memsourceapp.mapper;

import com.memsource.memsourceapp.domain.dto.UserDTO;
import com.memsource.memsourceapp.domain.response.UserResponse;
import com.memsource.memsourceapp.http_client.response.AuthenticatedUserLoginResponse;

public interface UserMapper {
    UserResponse findUserByUserName(String userName);
    UserResponse saveUser(AuthenticatedUserLoginResponse authenticatedUserLoginResponse,
                          String password);
}
