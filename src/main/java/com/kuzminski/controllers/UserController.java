package com.kuzminski.controllers;

import com.kuzminski.controllers.requests.PhoneRequest;
import com.kuzminski.controllers.requests.UserRequest;
import com.kuzminski.domain.Roles;
import com.kuzminski.domain.User;
import com.kuzminski.repository.RoleRepository;
import com.kuzminski.repository.UserRepository;
import com.kuzminski.service.RoleService;
import com.kuzminski.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserService userService;
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private RoleService roleService;

  @GetMapping("/all")
  private ResponseEntity<List<User>> getAllUsers() {
    return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
  }

  @GetMapping(value = "/{id}")
  private ResponseEntity<User> getUserById(@PathVariable("id") Long userId) {
    return new ResponseEntity(userRepository.findById(userId), HttpStatus.FOUND);
  }

  @GetMapping(value = "{/username}")
  private ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
    return new ResponseEntity(userRepository.findUserByUsername(username), HttpStatus.FOUND);
  }

  @PostMapping
  private ResponseEntity<User> createNewUser(@RequestBody UserRequest request) {
    User user = new User();
            userRepository.saveAndFlush(userService.create(user, request));
    Roles roles = roleRepository.saveAndFlush(roleService.getDefaultRole(user.getId()));
    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }
  @ApiOperation(value = "Update your Email or Password")
  @PutMapping(value = "/updateByUsername")
  private ResponseEntity<User> updateEmailOrPasswordByUsername(@RequestBody UserRequest request) {
    User user = userRepository.findUserByUsername(request.getUsername());
    userRepository.saveAndFlush(userService.create(user, request));
    return new ResponseEntity<>(user, HttpStatus.OK);
  }
  @ApiOperation(value = "Update your Username by Emale")
  @PutMapping(value = "/updateByEmail")
  private ResponseEntity<User> updateUsernameByEmail(@RequestBody UserRequest request) {
    User user = userRepository.findUserByEmail(request.getEmail());
    userRepository.saveAndFlush(userService.create(user, request));
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @ApiOperation(value = "Add phone number ")
  @PutMapping (value = "/phone" )
  private ResponseEntity<User> setPhone(@RequestBody PhoneRequest phoneRequest) {
    User user = userRepository.findUserByUsername(phoneRequest.getUsername());
    user.setPhone(phoneRequest.getPhone());
    userRepository.saveAndFlush(user);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @ApiOperation(value = "Fake Delete")
  @DeleteMapping(value = "/{username}")
  private ResponseEntity<User> deleteUser (@PathVariable ("username") String username){
    User user = userRepository.findUserByUsername(username);
    user.setActive(false);
    userRepository.saveAndFlush(user);
    return new ResponseEntity<User>(user, HttpStatus.GONE);
  }

}
