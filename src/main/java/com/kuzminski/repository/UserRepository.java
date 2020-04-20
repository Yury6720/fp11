package com.kuzminski.repository;


import com.kuzminski.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername (String username);

    User findUserByEmail (String email);

    //User findUserByUsernameOrEmail (String some);

    Optional<User> findByUsernameOrEmail(String usernameOrEmail, String usernameOrEmail1);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Override
    Optional<User> findById(Long aLong);
}
