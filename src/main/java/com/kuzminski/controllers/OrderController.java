package com.kuzminski.controllers;

import com.kuzminski.controllers.requests.OrderRequest;
import com.kuzminski.controllers.requests.UserRequest;
import com.kuzminski.domain.Order;
import com.kuzminski.domain.Roles;
import com.kuzminski.domain.User;
import com.kuzminski.repository.OrderRepository;
import com.kuzminski.repository.RoleRepository;
import com.kuzminski.repository.UserRepository;
import com.kuzminski.service.OrderService;
import com.kuzminski.service.RoleService;
import com.kuzminski.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/all")
    private ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity<Order> getOrderById (@PathVariable("id") Long orderId) {
        return new ResponseEntity(orderRepository.findById(orderId), HttpStatus.FOUND);
    }

//    @GetMapping(value = "{/username}")
//    private ResponseEntity<List<User>> getUserByUsername(@PathVariable("username") String username) {
//        List<Order> orders = orderRepository.findOrdersByUser(userRepository.findUserByUsername(username));
//        return new ResponseEntity(orders, HttpStatus.FOUND);
//    }

    @PostMapping
    private ResponseEntity<Order> createNewOrder(@RequestBody OrderRequest request) {
        Order order = new Order();
        orderRepository.saveAndFlush(orderService.createUpdate(order, request));
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
//    @ApiOperation(value = "Update your Email or Password")
//    @PutMapping(value = "/updateByUsername")
//    private ResponseEntity<User> updateEmailOrPasswordByUsername(@RequestBody UserRequest request) {
//        User user = userRepository.findUserByUsername(request.getUsername());
//        userRepository.saveAndFlush(userService.create(user, request));
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
//    @ApiOperation(value = "Update your Username by Emale")
//    @PutMapping(value = "/updateByEmail")
//    private ResponseEntity<User> updateUsernameByEmail(@RequestBody UserRequest request) {
//        User user = orderRepository.findUserByEmail(request.getEmail());
//        orderRepository.saveAndFlush(userService.create(user, request));
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
//
//    @ApiOperation(value = "Add phone number ")
//    @RequestMapping(value = "/{username}")
//    @PutMapping (value = "/{phone}")
//    private ResponseEntity<User> setPhone(@PathVariable ("username") String username, @PathVariable ("phone") String phone) {
//        User user = userRepository.findUserByUsername(username);
//        user.setPhone(phone);
//        userRepository.saveAndFlush(user);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
//
//    @ApiOperation(value = "Fake Delete")
//    @DeleteMapping(value = "/{username}")
//    private ResponseEntity<User> deleteUser (@PathVariable ("username") String username){
//        User user = userRepository.findUserByUsername(username);
//        user.setActive(false);
//        userRepository.saveAndFlush(user);
//        return new ResponseEntity<User>(user, HttpStatus.GONE);
//    }

}
