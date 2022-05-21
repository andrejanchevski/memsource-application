package com.memsource.memsourceapp.service.impl;

import com.memsource.memsourceapp.domain.User;
import com.memsource.memsourceapp.domain.dto.UserDTO;
import com.memsource.memsourceapp.exceptions.RoleNotFoundException;
import com.memsource.memsourceapp.exceptions.UserNotFoundException;
import com.memsource.memsourceapp.repository.RoleRepository;
import com.memsource.memsourceapp.repository.UserRepository;
import com.memsource.memsourceapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public User saveUser(UserDTO userDTO) {
        log.info("Saving new user user=[{}]", userDTO.toString());
        return userRepository.save(User.builder()
                .name(userDTO.getName())
                .userName(userDTO.getUserName())
                        .password(passwordEncoder.encode(userDTO.getPassword()))
                        .isActive(userDTO.getIs_active())
                        .isExpired(userDTO.getIsExpired())
                        .isLocked(userDTO.getIsLocked())
                        .activeAuthenticationToken(userDTO.getAuthenticationToken())
                        .roles(
                                List.of(roleRepository.findByName("ADMIN")
                                        .orElseThrow(() -> new RoleNotFoundException("Role ADMIN Does not Exist"))))
                .build());
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User with %s username does not exist", userName)));
    }
}
