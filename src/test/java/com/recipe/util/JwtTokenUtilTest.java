package com.recipe.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.recipe.entities.User;
import com.recipe.model.UserDetailsDTO;

/**
 * JwtTokenUtilTest for test generateToken test case
 * @author saemmadi
 *
 */
@ExtendWith(SpringExtension.class)

@SpringBootTest
public class JwtTokenUtilTest {

	@InjectMocks
	private JwtTokenUtil jwtTokenUtil;

	@Test
	public void generateToken() {
		User user = new User(1l, "pass", "Admin", true, "admin");
		String token = jwtTokenUtil.generateToken(new UserDetailsDTO(user));
		assertNotNull(token);
		assertNotNull(jwtTokenUtil.extractUsername(token));
		assertTrue(jwtTokenUtil.validateToken(token, new UserDetailsDTO(user)));
	}
}
