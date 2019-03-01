package com.computerDatabase.excilys.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.computerDatabase.excilys.dao.UserRepository;
import com.computerDatabase.excilys.model.User;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Objects.requireNonNull(username);
        User user = userRepository.findUserWithName(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        return user;
    }
}