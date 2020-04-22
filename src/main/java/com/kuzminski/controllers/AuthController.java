package com.kuzminski.controllers;

import com.kuzminski.configuration.security.JwtTokenProvider;
import com.kuzminski.configuration.security.request.ApiResponse;
import com.kuzminski.configuration.security.request.JwtAuthenticationResponse;
import com.kuzminski.configuration.security.request.LoginRequest;
import com.kuzminski.controllers.requests.UserRequest;
import com.kuzminski.domain.User;
import com.kuzminski.repository.RoleRepository;
import com.kuzminski.repository.UserRepository;
import com.kuzminski.service.RoleService;
import com.kuzminski.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired private AuthenticationManager authenticationManager;
  @Autowired private UserRepository userRepository;
  @Autowired private UserService userService;
  @Autowired private RoleService roleService;
  @Autowired private RoleRepository roleRepository;
  @Autowired private JwtTokenProvider tokenProvider;

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

  @ApiOperation(value = " New User Registration ")
  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @ModelAttribute UserRequest request) {
    if (userRepository.existsByUsername(request.getUsername())) {
      return new ResponseEntity(
          new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
    }

    if (userRepository.existsByEmail(request.getEmail())) {
      return new ResponseEntity(
          new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
    }

    User user = new User();
    userRepository.saveAndFlush(userService.create(user, request));
    roleRepository.saveAndFlush(roleService.getDefaultRole(user.getId()));

    URI location =
        ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/api/users/{username}")
            .buildAndExpand(user.getUsername())
            .toUri();

    return ResponseEntity.created(location)
        .body(new ApiResponse(true, "User registered successfully"));
  }
}
