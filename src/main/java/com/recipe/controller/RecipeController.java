package com.recipe.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.recipe.model.RecipeDTO;
import com.recipe.service.RecipeService;

/**
 * Recipe Controller to perform CRUD operations for Recipe
 * 
 * @author saemmadi
 */

@RestController
public class RecipeController {

	private static final Logger logger = LogManager.getLogger(RecipeController.class);

	@Autowired
	RecipeService recipeService;

	/**
	 * Returns List of all the recipes
	 * 
	 * @return - List of Recipes
	 */
	@GetMapping("/recipes")
	public List<RecipeDTO> listAllRecipeDetails() {
		logger.info("Start listAllRecipeDetails() method");
		return recipeService.listAllRecipeDetails();
	}

	/**
	 * Returns specific Recipe details by passing recipeId
	 * 
	 * @param id - recipe id
	 * @return - specific record of the recipe
	 */
	@GetMapping("recipe/{id}")
	public RecipeDTO findByRecipeId(@PathVariable Long id) {
		logger.info("Start findByRecipeId() method");
		return recipeService.findByRecipeId(id);
	}

	/**
	 * Create new Recipe details along with Ingredients.
	 * 
	 * @param recipeDto RecipeDTO
	 * @return String status
	 */
	@PostMapping("/recipe")
	public String createRecipe(@Valid @RequestBody RecipeDTO recipeDto) {
		logger.info("Start saveRecipe() method");
		return recipeService.createRecipe(recipeDto);
	}

	/**
	 * Update Recipe details details by passing recipe id.
	 * 
	 * @param recipeDto RecipeDTO
	 * @param id        recipeId
	 * @return recipeDto Object
	 */
	@PutMapping("/recipe/{id}")
	public RecipeDTO updateRecipe(@Valid @RequestBody RecipeDTO recipeDto, @PathVariable Long id) {
		logger.info("Start updateRecipe() method");
		return recipeService.updateRecipe(recipeDto, id);
	}

	/**
	 * Delete Recipe details by passing recipe id from database
	 * 
	 * @param id recipeId
	 * @return status
	 */
	@DeleteMapping("/recipe/{id}")
	public ResponseEntity<String> deleteRecipe(@PathVariable Long id) {
		logger.info("Start deleteRecipe() method");
		return ResponseEntity.ok().body(recipeService.deleteRecipe(id));
	}
}
