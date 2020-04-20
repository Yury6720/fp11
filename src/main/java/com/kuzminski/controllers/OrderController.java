package com.kuzminski.controllers;

import com.kuzminski.controllers.requests.OrderAddressUpdateRequest;
import com.kuzminski.domain.Order;
import com.kuzminski.repository.OrderRepository;
import com.kuzminski.repository.UserRepository;
import com.kuzminski.service.OrderService;
import com.kuzminski.service.OrderValidator;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
  @Autowired private OrderRepository orderRepository;
  @Autowired private OrderService orderService;
  @Autowired private UserRepository userRepository;
  @Autowired private OrderValidator orderValidator;

  @ApiOperation(value = "Get All Orders")
  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "Auth-Token",
        value = "token",
        required = true,
        dataType = "string",
        paramType = "header")
  })
  @GetMapping("/all")
  private ResponseEntity<List<Order>> getAllOrders() {
    return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);
  }

  @ApiOperation(value = "Get Order by Id")
  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "Auth-Token",
        value = "token",
        required = true,
        dataType = "string",
        paramType = "header")
  })
  @GetMapping(value = "/{id}")
  private ResponseEntity<Order> getOrderById(@PathVariable("id") Long orderId) {
    return new ResponseEntity(orderRepository.findById(orderId), HttpStatus.FOUND);
  }

  @ApiOperation(value = "Get Orders by Username")
  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "Auth-Token",
        value = "token",
        required = true,
        dataType = "string",
        paramType = "header")
  })
  @GetMapping(value = "{/username}")
  private ResponseEntity<List<Order>> getOrdersByUsername(
      @PathVariable("username") String username) {
    List<Order> orders =
        orderRepository.findOrdersByUser(userRepository.findUserByUsername(username));
    return new ResponseEntity(orders, HttpStatus.FOUND);
  }

  @ApiOperation(value = "Create new Order")
  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "Auth-Token",
        value = "token",
        required = true,
        dataType = "string",
        paramType = "header")
  })
  @PostMapping
  private ResponseEntity<Order> createNewOrder(HttpServletRequest request) {
    Order order = new Order();
    orderRepository.saveAndFlush(orderService.createUpdate(order, request));
    return new ResponseEntity<>(order, HttpStatus.CREATED);
  }

  @ApiOperation(value = "Add Address")
  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "Auth-Token",
        value = "token",
        required = true,
        dataType = "string",
        paramType = "header")
  })
  @PutMapping
  private ResponseEntity addAddress(
      @ModelAttribute OrderAddressUpdateRequest orderAddressUpdateRequest,
      HttpServletRequest request) {
    if (orderValidator.orderAccessor(request, orderAddressUpdateRequest)) {
      return new ResponseEntity(
          orderRepository.save(orderService.addAddress(orderAddressUpdateRequest, request)),
          HttpStatus.OK);
    } else return new ResponseEntity(HttpStatus.BAD_REQUEST);
  }

  @ApiOperation(value = "Delete Order")
  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.GONE)
  private void deleteOrder(@PathVariable("id") Long id) {
    orderRepository.deleteById(id);
  }

}
