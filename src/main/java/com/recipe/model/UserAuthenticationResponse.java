package com.recipe.model;

/**
 * UserAuthenticationResponse class containing authentication response(jwtToken)
 * 
 * @author saemmadi
 *
 */
public class UserAuthenticationResponse {

	private final String token;

	/**
	 * Parameterized constructor
	 * 
	 * @param token
	 */
	public UserAuthenticationResponse(String token) {
		super();
		this.token = token;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	@Override
	public String toString() {
		return "UserAuthenticationResponse [token=" + token + "]";
	}

}
