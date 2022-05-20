package com.memsource.memsourceapp.service.impl;

import com.memsource.memsourceapp.domain.Role;
import com.memsource.memsourceapp.repository.RoleRepository;
import com.memsource.memsourceapp.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role role=[{}]", role.toString());
        return roleRepository.save(role);
    }
}
