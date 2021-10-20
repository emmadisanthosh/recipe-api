package com.recipe.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.recipe.entities.User;

/**
 * This class containing UserDetails data members.
 * 
 * @author saemmadi
 *
 */
public class UserDetailsDTO implements UserDetails {

	private static final long serialVersionUID = 3834911598666083369L;
	
	private String userName;
	private String password;
	private Boolean active;
	private List<GrantedAuthority> authorities;

	/**
	 * @param user UserObject
	 */
	public UserDetailsDTO(User user) {
		super();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.active = user.getIsActive();
		this.authorities = Arrays.stream(user.getRoles().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	/**
	 * Default Constructor
	 */
	public UserDetailsDTO() {
	}

	/**
	 * @return the authorities
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	/**
	 * @return the password
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * @return the userName
	 */
	@Override
	public String getUsername() {
		return userName;
	}

	/**
	 * @return the active
	 */
	@Override
	public boolean isAccountNonExpired() {
		return active;
	}

	/**
	 * @return the active
	 */
	@Override
	public boolean isAccountNonLocked() {
		return active;
	}

	/**
	 * @return the active
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return active;
	}

	/**
	 * @return the active
	 */
	@Override
	public boolean isEnabled() {
		return active;
	}
}
