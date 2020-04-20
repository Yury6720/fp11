package com.kuzminski.service;

import com.kuzminski.domain.Roles;
import com.kuzminski.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
  @Autowired
  private RoleRepository roleRepository;
  public Roles getDefaultRole(Long userId) {
    Roles roles = new Roles();
    roles.setRoleName("USER");
    roles.setUserId(userId);
    roleRepository.saveAndFlush(roles);
    return roles;
  }
}
