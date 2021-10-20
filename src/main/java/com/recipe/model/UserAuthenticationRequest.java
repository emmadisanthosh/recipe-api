package com.recipe.model;

/**
 * UserAuthenticationRequest class containing authentication request data
 * members
 * 
 * @author saemmadi
 *
 */
public class UserAuthenticationRequest {

	private String username;
	private String password;

	/**
	 * @param username
	 * @param password
	 */
	public UserAuthenticationRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * 
	 */
	public UserAuthenticationRequest() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserAuthenticationRequest [username=" + username + ", password=" + password + "]";
	}

}
