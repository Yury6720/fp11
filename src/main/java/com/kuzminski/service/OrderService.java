package com.kuzminski.service;

import com.kuzminski.configuration.security.JwtTokenProvider;
import com.kuzminski.controllers.requests.OrderAddressUpdateRequest;
import com.kuzminski.domain.Address;
import com.kuzminski.domain.Order;
import com.kuzminski.repository.AddressRepository;
import com.kuzminski.repository.OrderRepository;
import com.kuzminski.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

@Service
public class OrderService {
  @Autowired private UserRepository userRepository;
  @Autowired private AddressRepository addressRepository;
  @Autowired private OrderRepository orderRepository;
  @Autowired private JwtTokenProvider tokenProvider;

  public Order createUpdate(Order order, HttpServletRequest request) {
    Long userId = tokenProvider.getActualUserId(request);
    order.setUser(userRepository.findById(userId).orElseThrow(
            () -> new UsernameNotFoundException("User not found with id : " + userId)
    ));
    order.setDate(new Timestamp(new Date().getTime()));
    order.setActive(true);
    return order;
  }

  public Order addAddress (OrderAddressUpdateRequest orderAddressUpdateRequest, HttpServletRequest request){
    Long orderId = orderAddressUpdateRequest.getOrderId();
    Order order = orderRepository.findById(orderId).orElseThrow(
            () -> new RuntimeException("Order not found with id : " + orderId));
    Long addressId = orderAddressUpdateRequest.getAddressId();
    Address address = addressRepository.findById(addressId).orElseThrow(
            () -> new RuntimeException(("Address not found with id: " + addressId)));
    order.setAddress(address);
    return order;
  }

}
