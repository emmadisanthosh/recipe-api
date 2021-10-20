
package com.recipe.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.recipe.dao.RecipeRepository;
import com.recipe.entities.IngredientEntity;
import com.recipe.entities.RecipeEntity;
import com.recipe.model.IngredientsDTO;
import com.recipe.model.RecipeDTO;
import com.recipe.util.RecordNotFoundException;

/**
 * This class is used for RecipeService Test
 * @author saemmadi
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RecipeServiceTest {

	@InjectMocks
	private RecipeServiceImpl recipeServiceObj;

	@Mock
	private RecipeRepository recipeRepositoryObj;

	@Mock
	private ModelMapper modelMapperObj;

	/**
	 * Test case to check listAllRecipeDetails
	 */
	@Test
	@DisplayName("Test case for listAllRecipeDetails")
	public void testListAllRecipeDetails() {
		when(recipeRepositoryObj.findAll()).thenReturn(Arrays.asList(entityData()));
		when(modelMapperObj.map(Mockito.any(), Mockito.eq(RecipeDTO.class))).thenReturn(dtoData());
		List<RecipeDTO> expected = recipeServiceObj.listAllRecipeDetails();
		assertThat(expected).hasSize(1);
		assertEquals(1, expected.get(0).getRecipeId());
	}

	/**
	 * Test case to check findByRecipeId condition
	 */
	@Test
	@DisplayName("Test case for findByRecipeId")
	public void testFindByRecipeId() {
		when(recipeRepositoryObj.findById(1l)).thenReturn(Optional.of(entityData()));
		when(modelMapperObj.map(Mockito.any(), Mockito.eq(RecipeDTO.class))).thenReturn(dtoData());
		RecipeDTO expected = recipeServiceObj.findByRecipeId(1l);
		assertThat(expected.getRecipeId()).isGreaterThan(0);
		assertThat(expected.getName()).isEqualTo("Egg Rice");
	}

	/**
	 * Test case to check else condition in findByRecipeId
	 */
	@Test
	@DisplayName("Test case for findByRecipe Exception")
	public void testFindByRecipeIdException() {
		when(recipeRepositoryObj.findById(2l))
				.thenThrow(new RecordNotFoundException("No Record Found in Database for RecipeId"));
		Throwable exception = assertThrows(RecordNotFoundException.class,
				() -> recipeServiceObj.findByRecipeId(2l));
		assertEquals("No Record Found in Database for RecipeId", exception.getMessage());
	}
	
	/**
	 * Test case to check updateRecipe
	 */
	@Test
	@DisplayName("Test case for updateRecipe")
	public void testUpdateRecipe() {
		IngredientEntity ingredientOne = new IngredientEntity(1l, "Dal");
		given(recipeRepositoryObj.findById(1l)).willReturn(Optional.of(entityData()));
		when(modelMapperObj.map(Mockito.any(), Mockito.eq(IngredientEntity.class))).thenReturn(ingredientOne);
		when(recipeRepositoryObj.save(Mockito.any())).thenReturn(entityData());
		when(modelMapperObj.map(Mockito.any(), Mockito.eq(RecipeDTO.class))).thenReturn(dtoData());
		RecipeDTO recipeDTO = recipeServiceObj.updateRecipe(dtoData(), 1l);
		assertNotNull(recipeDTO);
		assertThat(recipeDTO.getRecipeId()).isGreaterThan(0);
	}

	/**
	 * Test case to check updateRecipe exception
	 */
	@Test
	@DisplayName("Test case for updateRecipe Exception")
	public void testUpdateRecipeException() {
		when(recipeRepositoryObj.findById(2l))
				.thenThrow(new RecordNotFoundException("No Records Found in Database for RecipeId"));
		Throwable exception = assertThrows(RecordNotFoundException.class,
				() -> recipeServiceObj.updateRecipe(dtoData(), 2l));
		assertEquals("No Records Found in Database for RecipeId", exception.getMessage());

	}

	/**
	 * Test case to check createRecipe
	 */

	@Test
	@DisplayName("Test case for createRecipe")
	public void testCreateRecipe() {
		RecipeEntity entity = entityData();
		when(modelMapperObj.map(Mockito.any(), Mockito.eq(RecipeEntity.class))).thenReturn(entity);
		when(recipeRepositoryObj.save(any(RecipeEntity.class))).thenReturn(entity);
		String message = recipeServiceObj.createRecipe(dtoData());
		assertNotNull(message);
		assertEquals("Recipe Saved Successfully", message);
	}

	/**
	 * Test case to check deleteRecipe
	 */
	@Test
	@DisplayName("Test case for deleteRecipe")
	public void testDeleteRecipe() {
		when(recipeRepositoryObj.findById(Mockito.anyLong())).thenReturn(Optional.of(entityData()));
		String msg = recipeServiceObj.deleteRecipe(1l);
		assertEquals("Deleted Successfully", msg);
	}

	/**
	 * Test case to check deleteRecipe exception
	 */
	@Test
	@DisplayName("Test case for deleteRecipe Exception")
	public void testDeleteRecipeException() {
		when(recipeRepositoryObj.findById(4l))
				.thenThrow(new RecordNotFoundException("No Records Found in Database for RecipeId"));
		Throwable exception = assertThrows(RecordNotFoundException.class,
				() -> recipeServiceObj.deleteRecipe(4l));
		assertEquals("No Records Found in Database for RecipeId", exception.getMessage());
	}


	/**
	 * mock data for recipe entity
	 */
	private static RecipeEntity entityData() {
		RecipeEntity recipeObj = new RecipeEntity();
		List<IngredientEntity> ingredientList = new ArrayList<>();
		IngredientEntity ingredientOne = new IngredientEntity(1l, "Egg");
		IngredientEntity ingredientTwo = new IngredientEntity(2l, "Rice");
		ingredientList.add(ingredientOne);
		ingredientList.add(ingredientTwo);
		recipeObj.setId(1l);
		recipeObj.setCookingInstructions(
				"Take boiled rice,put oil in a large pan,onion,ginger and chiles,eggs,salt,mix with rice with medium heat about 10 minutes");
		recipeObj.setCreationDateTime("13-10-2021 14:30");
		recipeObj.setIngredientList(ingredientList);
		recipeObj.setIsVegetarian(true);
		recipeObj.setName("Egg Rice");
		recipeObj.setNumberOfPeople(12);
		return recipeObj;
	}

	/**
	 * mock data for recipe dto
	 */
	private static RecipeDTO dtoData() {
		RecipeDTO recipeObj = new RecipeDTO();
		List<IngredientsDTO> ingredientList = new ArrayList<>();
		IngredientsDTO ingredientOne = new IngredientsDTO(1l, "Egg");
		IngredientsDTO ingredientTwo = new IngredientsDTO(2l, "Rice");
		ingredientList.add(ingredientOne);
		ingredientList.add(ingredientTwo);
		recipeObj.setRecipeId(1l);
		recipeObj.setCookingInstructions(
				"Take boiled rice,put oil in a large pan,onion,ginger and chiles,salt,mix with rice with medium heat about 10 minutes");
		recipeObj.setCreationDateTime("13-10-2021 14:30");
		recipeObj.setIngredientList(ingredientList);
		recipeObj.setIsVegetarian(true);
		recipeObj.setName("Egg Rice");
		recipeObj.setNumberOfPeople(12);
		return recipeObj;
	}
}
