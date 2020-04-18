package com.kuzminski.repository;


import com.kuzminski.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    User findUserByUsername (String username);

    User findUserByEmail (String email);

}
