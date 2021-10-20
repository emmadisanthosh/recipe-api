package com.recipe.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * This class used for jwt token validation
 * 
 * @author saemmadi
 *
 */
@Component
public class JwtTokenUtil {

	private String key = "HRlELXqpSB";

	/**
	 * This method is use to extractUsername from token.
	 * 
	 * @param token
	 * @return String
	 */
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	/**
	 * This method is use to extractExpiration from token.
	 * 
	 * @param token
	 * @return Date
	 */
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	/**
	 * This method is use to extract claim from token.
	 * 
	 * @param <T>
	 * @param token
	 * @param claimResolver
	 * @return T
	 */
	public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}

	/**
	 * This method is use to extract all claims from token.
	 * 
	 * @param token
	 * @return Claims
	 */
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
	}

	/**
	 * This method is use to check if token expired or not.
	 * 
	 * @param token
	 * @return Boolean
	 */
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	/**
	 * This method is use to generate token by passing UserDetails.
	 * 
	 * @param userDetails
	 * @return String
	 */
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername());
	}

	/**
	 * This method is use to createToken by passing claims and username. Token
	 * Expiration 15 min
	 * 
	 * @param claims
	 * @param username
	 * @return String
	 */

	private String createToken(Map<String, Object> claims, String username) {
		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
				.signWith(SignatureAlgorithm.HS256, key).compact();
	}

	/**
	 * This method is used to check token is valid or invalid.
	 * 
	 * @param token
	 * @param userDetails
	 * @return Boolean
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
