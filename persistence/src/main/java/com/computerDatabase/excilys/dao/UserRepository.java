package com.computerDatabase.excilys.dao;

import org.springframework.data.jpa.repository.*;

import java.util.*;

import com.computerDatabase.excilys.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(" select u from User u " + " where u.username = ?1")
    Optional<User> findUserWithName(String username);
}