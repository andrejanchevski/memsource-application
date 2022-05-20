package com.memsource.memsourceapp.service;

import com.memsource.memsourceapp.domain.Role;
import com.memsource.memsourceapp.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User findUserByUserName(String userName);
}
