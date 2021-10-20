package com.recipe.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.recipe.controller.UserAuthenticationController;
import com.recipe.entities.User;
import com.recipe.model.UserAuthenticationRequest;
import com.recipe.model.UserAuthenticationResponse;
import com.recipe.model.UserDetailsDTO;
import com.recipe.util.JwtTokenUtil;
import com.recipe.util.RecordNotFoundException;

/**
 * @author saemmadi
 *
 */
@ExtendWith(SpringExtension.class)

@SpringBootTest
public class UserAuthenticationControllerTest {

	@InjectMocks
	private UserAuthenticationController authenticationController;

	@Mock
	private AuthenticationManager authenticationManager;

	@Mock
	private UserDetailsService userDetailsService;

	@Mock
	private JwtTokenUtil jwtTokenUtil;

	/**
	 * This test case for generate jwt token
	 * @throws Exception BadCredentialsException
	 */
	@Test
	public void testGenerateJwtToken() throws Exception {
		User user = new User();
		user.setId(1l);
		user.setIsActive(true);
		user.setPassword("pass");
		user.setUserName("admin");
		user.setRoles("Admin");
		UserDetailsDTO userDetailsdto = new UserDetailsDTO(user);
		userDetailsdto.getAuthorities();
		userDetailsdto.getPassword();
		userDetailsdto.getUsername();
		userDetailsdto.isAccountNonExpired();
		userDetailsdto.isAccountNonLocked();
		userDetailsdto.isCredentialsNonExpired();
		userDetailsdto.isEnabled();
		new UserAuthenticationResponse("Token").getToken();
		UserAuthenticationRequest authenticationRequest = new UserAuthenticationRequest("user", "pass");
		when(userDetailsService.loadUserByUsername(Mockito.anyString())).thenReturn(userDetailsdto);
		when(jwtTokenUtil.generateToken(Mockito.any())).thenReturn(
				"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYzNDE5MTc2MiwiaWF0IjoxNjM0MTkwNTYyfQ.zule9M-BBWQBs8_6IipHseJttF2hg8N5m1PTh6iE52w");
		ResponseEntity<?> responseEntity = authenticationController.createAuthenticationToken(authenticationRequest);
		assertEquals(200, responseEntity.getStatusCodeValue());
	}

	/**This test case for validate token
	 * @throws Exception BadCredentialsException
	 */
	@Test
	public void invalidTokenTest() throws Exception {
		UserAuthenticationRequest authenticationRequest = new UserAuthenticationRequest("user", "pass");
		when(authenticationManager.authenticate(Mockito.any())).thenThrow(BadCredentialsException.class);
		assertThrows(BadCredentialsException.class,
				() -> authenticationController.createAuthenticationToken(authenticationRequest));
	}
}
