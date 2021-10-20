package com.recipe.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

/**
 * AuthRequestFilter class used for filter incoming requests
 * 
 * @author saemmadi
 *
 */

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	private static final Logger log = LogManager.getLogger(JwtRequestFilter.class);

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	/**
	 * This method used to filter upcoming requests and check passed token is valid
	 * or not.
	 */
	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("Start doFilterInternal() method.");
		try {
			final String authorizationHeader = request.getHeader("Authorization");
			String username = null;
			String jwtToken = null;
			if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
				jwtToken = authorizationHeader.substring(7);
				username = jwtTokenUtil.extractUsername(jwtToken);
			}
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
				boolean isValid=jwtTokenUtil.validateToken(jwtToken, userDetails);
				if (isValid) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
		} catch (ExpiredJwtException e) {
			log.error("Access Denied {}", e.getMessage());
		} catch (Exception e) {
			log.error("Signature Not Matched  {}", e.getMessage());
		}
		filterChain.doFilter(request, response);
		log.info("Exit from doFilterInternal() method.");
	}

}
