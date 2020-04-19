package com.kuzminski.controllers;

import com.kuzminski.configuration.security.*;
import com.kuzminski.configuration.security.secureResponseRequest.ApiResponse;
import com.kuzminski.configuration.security.secureResponseRequest.JwtAuthenticationResponse;
import com.kuzminski.configuration.security.secureResponseRequest.LoginRequest;
import com.kuzminski.configuration.security.secureResponseRequest.SignUpRequest;
import com.kuzminski.domain.User;
import com.kuzminski.repository.RoleRepository;
import com.kuzminski.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired AuthenticationManager authenticationManager;

  @Autowired UserRepository userRepository;

  @Autowired RoleRepository roleRepository;

  @Autowired PasswordEncoder passwordEncoder;

  @Autowired
  JwtTokenProvider tokenProvider;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @ModelAttribute LoginRequest loginRequest) {

    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = tokenProvider.generateToken(authentication);
    return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @ModelAttribute SignUpRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return new ResponseEntity(
          new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return new ResponseEntity(
          new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
    }


    User user = new User();
    user.setUsername(signUpRequest.getUsername());
    user.setEmail(signUpRequest.getEmail());
    user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
    user.setActive(true);
    user.setCreationDate(new Timestamp(new Date().getTime()));

//    Roles userRole =
//        roleRepository
//            .findByRoleName("USER")
//            .orElseThrow(() -> new ApiException("User Role not set."));
//
//    user.setRoles(Collections.singleton(userRole));
//
    User result = userRepository.save(user);

    URI location =
        ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/api/users/{username}")
            .buildAndExpand(result.getUsername())
            .toUri();

    return ResponseEntity.created(location)
        .body(new ApiResponse(true, "User registered successfully"));
  }
}
