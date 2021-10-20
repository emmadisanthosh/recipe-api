package com.recipe.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recipe.entities.RecipeEntity;


/**
 * RecipeRepository to access H2 database Recipe table.
 * @author saemmadi
 *
 */
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long>{

}
