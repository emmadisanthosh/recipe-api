package com.recipe.service;

import java.util.List;

import com.recipe.model.RecipeDTO;

/**
 * RecipeService for recipe CRUD Operations
 * 
 * @author saemmadi
 *
 */
public interface RecipeService {

	/**
	 * This method is used for get list of recipe details
	 * 
	 */
	List<RecipeDTO> listAllRecipeDetails();

	/**
	 * Find Recipe details by passing specific recipe ID
	 * 
	 * @param id
	 * @return RecipeDTO recipeDTO details
	 */
	RecipeDTO findByRecipeId(Long id);

	/**
	 * Save new Recipe details
	 * 
	 * @param recipeDto recipeDTO details
	 */
	String createRecipe(RecipeDTO recipeDto);

	/**
	 * Update existing Recipe details.
	 * 
	 * @param recipeDto recipeDTO details
	 * @param id        - Recipe ID
	 * @return - RecipeDTO Object updated.
	 */
	RecipeDTO updateRecipe(RecipeDTO recipeDto, Long id);

	/**
	 * Delete existing Recipe details by passing recipe id.
	 * 
	 * @param id - Recipe ID
	 */
	String deleteRecipe(Long id);
}
