package com.computerDatabase.excilys.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.computerDatabase.excilys.dao.UserDAO;
import com.computerDatabase.excilys.model.User;

@Service
public class UserService implements UserDetailsService {

	private final UserDAO userDAO;

	@Autowired
	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userDAO.getUserByName(username);
		UserBuilder userBuilder = null;
		if (!user.isPresent())
			throw new UsernameNotFoundException(username);
		
		userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
		userBuilder.password(user.get().getPassword());
		userBuilder.roles(user.get().getName().toUpperCase());

		return userBuilder.build();
	}
}
