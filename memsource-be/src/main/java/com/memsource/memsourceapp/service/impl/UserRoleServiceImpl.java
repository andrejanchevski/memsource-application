package com.memsource.memsourceapp.service.impl;

import com.memsource.memsourceapp.domain.Role;
import com.memsource.memsourceapp.domain.User;
import com.memsource.memsourceapp.exceptions.RoleNotFoundException;
import com.memsource.memsourceapp.exceptions.UserNotFoundException;
import com.memsource.memsourceapp.repository.RoleRepository;
import com.memsource.memsourceapp.repository.UserRepository;
import com.memsource.memsourceapp.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Override
    public void addRoleToUser(String userName, String roleName) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User with %s username does not exist", userName)));
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RoleNotFoundException(
                        String.format("User with %s username does not exist", userName)));
        log.info("Adding role {} to user {} with id {}", roleName, userName, user.getId());
        user.getRoles().add(role);

    }
}
