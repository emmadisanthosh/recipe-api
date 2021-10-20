package com.recipe.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.recipe.dao.UserRepository;
import com.recipe.entities.User;
import com.recipe.model.UserDetailsDTO;

/**
 * This class is used for load user details form DB
 * 
 * @author saemmadi
 *
 */
@Service
public class UserAuthenticationService implements UserDetailsService {

	private static final Logger logger = LogManager.getLogger(UserAuthenticationService.class);

	@Autowired
	private UserRepository userRepository;

	/**
	 * This method used for load user by username from database.
	 * 
	 * @param username
	 * @return UserDetails
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Start loadUserByUsername() method");
		Optional<User> user = userRepository.findByUserName(username);
		if (!user.isEmpty()) {
			return user.map(UserDetailsDTO::new).get();
		} else {
			throw new UsernameNotFoundException(username + " Not Found");
		}

	}

}
