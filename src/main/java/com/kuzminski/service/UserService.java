package com.kuzminski.service;

import com.kuzminski.controllers.requests.UserRequest;
import com.kuzminski.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User create (User user, UserRequest request){
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setActive(true);
        user.setCreationDate(new Timestamp(new Date().getTime()));
        return user;
    }
}
