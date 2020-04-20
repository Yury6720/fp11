package com.kuzminski.service;

import com.kuzminski.configuration.security.JwtTokenProvider;
import com.kuzminski.controllers.requests.OrderAddressUpdateRequest;
import com.kuzminski.domain.Order;
import com.kuzminski.domain.User;
import com.kuzminski.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class OrderValidator {
  @Autowired private OrderRepository orderRepository;
  @Autowired private JwtTokenProvider tokenProvider;

  public boolean orderAccessor(
      HttpServletRequest request, OrderAddressUpdateRequest orderAddressUpdateRequest) {
    Long  orderId = orderAddressUpdateRequest.getOrderId();
    Order order = orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("Order not found with id: " + orderId));
    Long userId = order.getUser().getId();

//    Long userId = orderRepository.findById(orderId).orElseThrow().getUser().getId();
    if (userId == tokenProvider.getActualUserId(request)) {
      return true;
    } else return false;
  }
}
