package com.kuzminski.repository;

import com.kuzminski.domain.Order;
import com.kuzminski.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> { //CrudRepository<Order, Long> {
    List<Order> findOrdersByUser (User user);


}
