package com.recipe.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recipe.entities.User;

/**
 * UserRepository to access H2 database User table.
 * 
 * @author saemmadi
 *
 */
public interface UserRepository extends JpaRepository<User, Integer> {
	/**
	 * @param userName
	 * @return User
	 */
	Optional<User> findByUserName(String userName);

}
