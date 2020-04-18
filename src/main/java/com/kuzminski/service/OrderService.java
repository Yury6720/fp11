package com.kuzminski.service;

import com.kuzminski.controllers.requests.OrderRequest;
import com.kuzminski.domain.Order;
import com.kuzminski.domain.User;
import com.kuzminski.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class OrderService {
    @Autowired
    UserRepository userRepository;

    public Order createUpdate (Order order, OrderRequest request){
        order.setUser(userRepository.findUserByUsername(request.getUsername()));
        order.setDate(new Timestamp(new Date().getTime()));
        order.setActive(true);
        return order;
    }
}
