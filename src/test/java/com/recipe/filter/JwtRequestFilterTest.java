package com.recipe.filter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.recipe.entities.User;
import com.recipe.model.UserDetailsDTO;
import com.recipe.util.JwtRequestFilter;
import com.recipe.util.JwtTokenUtil;

/**
 * @author saemmadi
 *
 */
@ExtendWith(SpringExtension.class)

@SpringBootTest
public class JwtRequestFilterTest {

	@InjectMocks
	private JwtRequestFilter jwtRequestFilter;

	@Mock
	private UserDetailsService userDetailsService;

	@Mock
	private JwtTokenUtil jwtTokenUtil;

	/**
	 * This test case for authentication filter chain
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void doFilterInternalTest() throws IOException, ServletException {
		User user = new User();
		user.setId(1l);
		user.setIsActive(true);
		user.setPassword("pass");
		user.setRoles("Admin");
		user.setUserName("admin");
		UserDetails UserDetails = new UserDetailsDTO(user);
		MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
		MockHttpServletResponse httpServletResponse = new MockHttpServletResponse();
		MockFilterChain mockfilterChain = new MockFilterChain();
		httpServletRequest.addHeader("Authorization",
				"Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYzNDUzMTMzNiwiaWF0IjoxNjM0NTMwMTM2fQ.yETK3WXWFuQvS8rgPFU6rvMRlVqQ4F2DWTNblv69d0o");
		when(jwtTokenUtil.extractUsername(Mockito.anyString())).thenReturn("user");
		when(userDetailsService.loadUserByUsername(Mockito.anyString())).thenReturn(UserDetails);
		when(jwtTokenUtil.validateToken(Mockito.anyString(), Mockito.any())).thenReturn(true);
		jwtRequestFilter.doFilterInternal(httpServletRequest, httpServletResponse, mockfilterChain);
	}
	@Test
	public void InvalidTokenTest() throws IOException, ServletException {
		User user = new User();
		user.setId(1l);
		user.setIsActive(true);
		user.setPassword("pass");
		user.setRoles("Admin");
		user.setUserName("admin1");
		UserDetails UserDetails = new UserDetailsDTO(user);
		when(jwtTokenUtil.extractUsername(Mockito.anyString())).thenReturn("user");
		when(userDetailsService.loadUserByUsername(Mockito.anyString())).thenReturn(UserDetails);
		when(jwtTokenUtil.validateToken(Mockito.anyString(), Mockito.any())).thenReturn(false);
		boolean isValid=jwtTokenUtil.validateToken(Mockito.anyString(),Mockito.any());
		assertTrue(!isValid);
	}
}
