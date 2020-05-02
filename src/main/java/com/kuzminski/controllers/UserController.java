package com.kuzminski.controllers;

import com.kuzminski.controllers.requests.GenericIdRequest;
import com.kuzminski.controllers.requests.GenericNameRequest;
import com.kuzminski.controllers.requests.PhoneRequest;
import com.kuzminski.controllers.requests.UserRequest;
import com.kuzminski.domain.User;
import com.kuzminski.repository.RoleRepository;
import com.kuzminski.repository.UserRepository;
import com.kuzminski.service.RoleService;
import com.kuzminski.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

  @Autowired private UserRepository userRepository;
  @Autowired private UserService userService;
  @Autowired private RoleRepository roleRepository;
  @Autowired private RoleService roleService;

  @ApiOperation(value = "Get All Userst")
  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "Auth-Token",
        value = "token",
        required = true,
        dataType = "string",
        paramType = "header")
  })
  @GetMapping("/all")
  private ResponseEntity<List<User>> getAllUsers() {
    return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
  }

  @ApiOperation(value = "Get User by Id ")
  @ApiImplicitParams({
          @ApiImplicitParam(
                  name = "Auth-Token",
                  value = "token",
                  required = true,
                  dataType = "string",
                  paramType = "header")
  })
  @GetMapping(value = "/getById")
  private ResponseEntity<User> getUserById(@ModelAttribute GenericIdRequest genericIdRequest) {
    return new ResponseEntity(userRepository.findById(genericIdRequest.getId()), HttpStatus.FOUND);
  }

  @ApiOperation(value = "Get User by Username ")
  @ApiImplicitParams({
          @ApiImplicitParam(
                  name = "Auth-Token",
                  value = "token",
                  required = true,
                  dataType = "string",
                  paramType = "header")
  })
  @GetMapping(value = "getByName")
  private ResponseEntity<User> getUserByUsername(
          @Valid @ModelAttribute GenericNameRequest genericNameRequest) {
    return new ResponseEntity(userRepository.findUserByUsername(genericNameRequest.getName()), HttpStatus.FOUND);
  }

  //  @PostMapping
  //  private ResponseEntity<User> createNewUser(@Valid @ModelAttribute UserRequest request) {
  //    User user = new User();
  //    userRepository.saveAndFlush(userService.create(user, request));
  //    Roles roles = roleRepository.saveAndFlush(roleService.getDefaultRole(user.getId()));
  //    return new ResponseEntity<>(user, HttpStatus.CREATED);
  //  }
  @ApiImplicitParams({
          @ApiImplicitParam(
                  name = "Auth-Token",
                  value = "token",
                  required = true,
                  dataType = "string",
                  paramType = "header")
  })
  @ApiOperation(value = "Update your Email or Password by Username")
  @PutMapping(value = "/updateByUsername")
  private ResponseEntity<User> updateEmailOrPasswordByUsername(
      @Valid @ModelAttribute UserRequest request) {
    User user = userRepository.findUserByUsername(request.getUsername());
    userRepository.saveAndFlush(userService.create(user, request));
    return new ResponseEntity<>(user, HttpStatus.OK);
  }


  @ApiImplicitParams({
          @ApiImplicitParam(
                  name = "Auth-Token",
                  value = "token",
                  required = true,
                  dataType = "string",
                  paramType = "header")
  })
  @ApiOperation(value = "Update your Username by Emale")
  @PutMapping(value = "/updateByEmail")
  private ResponseEntity<User> updateUsernameByEmail(@Valid @ModelAttribute UserRequest request) {
    User user = userRepository.findUserByEmail(request.getEmail());
    userRepository.saveAndFlush(userService.create(user, request));
    return new ResponseEntity<>(user, HttpStatus.OK);
  }
  @ApiImplicitParams({
          @ApiImplicitParam(
                  name = "Auth-Token",
                  value = "token",
                  required = true,
                  dataType = "string",
                  paramType = "header")
  })
  @ApiOperation(value = "Add phone number ")
  @PutMapping(value = "/phone")
  private ResponseEntity<User> setPhone(@Valid @ModelAttribute PhoneRequest phoneRequest) {
    User user = userRepository.findUserByUsername(phoneRequest.getUsername());
    user.setPhone(phoneRequest.getPhone());
    userRepository.saveAndFlush(user);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "Auth-Token",
        value = "token",
        required = true,
        dataType = "string",
        paramType = "header")
  })
  @ApiOperation(value = "Fake Delete")
  @DeleteMapping
  private ResponseEntity<User> deleteUser(@ModelAttribute GenericNameRequest genericNameRequest) {
    User user = userRepository.findUserByUsername(genericNameRequest.getName());
        user.setActive(false);
        userRepository.saveAndFlush(user);
    return new ResponseEntity<User>(user, HttpStatus.GONE);
  }
}
