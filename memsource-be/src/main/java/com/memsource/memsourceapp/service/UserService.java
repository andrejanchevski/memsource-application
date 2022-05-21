package com.memsource.memsourceapp.service;

import com.memsource.memsourceapp.domain.Role;
import com.memsource.memsourceapp.domain.User;
import com.memsource.memsourceapp.domain.dto.UserDTO;

import java.util.List;

public interface UserService {
    User saveUser(UserDTO userDTO);
    User findUserByUserName(String userName);
}
