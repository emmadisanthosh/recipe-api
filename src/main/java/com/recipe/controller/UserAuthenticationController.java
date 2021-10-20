package com.recipe.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.recipe.model.UserAuthenticationRequest;
import com.recipe.model.UserAuthenticationResponse;
import com.recipe.util.JwtTokenUtil;

/**
 * This class used for generate jwt token.
 * 
 * @author saemmadi
 *
 */
@RestController
public class UserAuthenticationController {

	private static final Logger logger = LogManager.getLogger(UserAuthenticationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	/**
	 * This method used for authenticate the user and generate JWT Token.
	 * 
	 * @param authenticationRequest request details
	 * @return ResponseEntity
	 * @throws Exception BadCredentialsException
	 */
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserAuthenticationRequest authenticationRequest) {
		logger.info("Start createAuthenticationToken() method ");
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
				authenticationRequest.getPassword()));
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		logger.info("End createAuthenticationToken() method.");
		return ResponseEntity.ok(new UserAuthenticationResponse(jwt));
	}
}
