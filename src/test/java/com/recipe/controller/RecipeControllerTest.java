package com.recipe.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.recipe.dao.RecipeRepository;
import com.recipe.model.IngredientsDTO;
import com.recipe.model.RecipeDTO;
import com.recipe.service.RecipeService;

/**
 * This class used for RecipeController Test
 * @author saemmadi
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RecipeControllerTest {

	@InjectMocks
	private RecipeController recipeController;

	@Mock
	private RecipeService recipeService;

	@Mock
	private RecipeRepository recipeRepositoryObj;

	@Mock
	private ModelMapper modelMapperObj;

	/**
	 * This test case for listAllRecipeDetails
	 */
	@Test
	public void testListAllRecipeDetails() {
		when(recipeService.listAllRecipeDetails()).thenReturn(Arrays.asList(dtoData()));
		List<RecipeDTO> recipeList = recipeController.listAllRecipeDetails();
		assertThat(recipeList).hasSize(1);
		assertNotNull(recipeList);
	}

	/**
	 * This test case for findByRecipeId 
	 */
	@Test
	public void testFindByRecipeId() {
		when(recipeService.findByRecipeId(Mockito.anyLong())).thenReturn(dtoData());
		RecipeDTO recipeDTOActual = recipeController.findByRecipeId(1l);
		assertNotNull(recipeDTOActual);
		assertThat(recipeDTOActual.getRecipeId()).isGreaterThan(0);
	}

	/**
	 * This test case for create Recipe
	 */
	@Test
	public void testCreateRecipe() {
		when(recipeService.createRecipe(Mockito.any())).thenReturn("Recipe Saved Successfully");
		String message = recipeController.createRecipe(dtoData());
		assertEquals("Recipe Saved Successfully", message);
	}
	
	/**
	 * This test case for update recipe
	 */
	@Test
	public void testUpdateRecipe() {
		when(recipeService.updateRecipe(Mockito.any(), Mockito.anyLong())).thenReturn(dtoData());
		RecipeDTO updatedRecipe = recipeController.updateRecipe(dtoData(), 1l);
		assertEquals(updatedRecipe.getName(), dtoData().getName());
	}

	/**
	 * This test case for delete recipe
	 */
	@Test
	public void testDeleteRecipe() {
		ResponseEntity<String> response = recipeController.deleteRecipe(1l);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	/**
	 * This method for dto mock data
	 * @return RecipeDTO
	 */
	private static RecipeDTO dtoData() {
		RecipeDTO recipeObj = new RecipeDTO();
		List<IngredientsDTO> ingredientList = new ArrayList<>();
		IngredientsDTO ingredientOne = new IngredientsDTO(1l, "Dal");
		IngredientsDTO ingredientTwo = new IngredientsDTO(2l, "Rice");
		ingredientList.add(ingredientOne);
		ingredientList.add(ingredientTwo);
		recipeObj.setRecipeId(2l);
		recipeObj.setCookingInstructions(
				"Take boiled rice,put oil in a large pan,onion,ginger and chiles,salt,mix with rice with medium heat about 10 minutes");
		recipeObj.setCreationDateTime("14-10-2021 11:30");
		recipeObj.setIngredientList(ingredientList);
		recipeObj.setIsVegetarian(true);
		recipeObj.setName("Dal Rice");
		recipeObj.setNumberOfPeople(12);
		return recipeObj;
	}

}