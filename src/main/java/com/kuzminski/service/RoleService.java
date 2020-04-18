package com.kuzminski.service;

import com.kuzminski.domain.Roles;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
  public Roles getDefaultRole(Long userId) {
    Roles roles = new Roles();
    roles.setRoleName("USER");
    roles.setUserId(userId);
    return roles;
  }
}
