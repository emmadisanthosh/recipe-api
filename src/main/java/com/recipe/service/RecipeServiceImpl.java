package com.recipe.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.recipe.util.Constants.SAVE;
import com.recipe.dao.RecipeRepository;
import com.recipe.entities.IngredientEntity;
import com.recipe.entities.RecipeEntity;
import com.recipe.util.RecordNotFoundException;
import com.recipe.model.IngredientsDTO;
import com.recipe.model.RecipeDTO;

/**
 * Recipe service implementation for recipe operations.
 * 
 * @author saemmadi
 *
 */
@Service
public class RecipeServiceImpl implements RecipeService {

	private static final Logger logger = LogManager.getLogger(RecipeServiceImpl.class);

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Get List of all Recipes.
	 *
	 */
	@Override
	public List<RecipeDTO> listAllRecipeDetails() {
		logger.info("Start listAllRecipeDetails() method.");
		var allRecipeList = recipeRepository.findAll();
		var recipeDetailsList = Optional.of(allRecipeList).filter(records -> !records.isEmpty())
				.orElseThrow(RecordNotFoundException::new).stream()
				.map(recipe -> modelMapper.map(recipe, RecipeDTO.class)).collect(Collectors.toList());
		logger.info("End listAllRecipeDetails() method.");
		return recipeDetailsList;
	}

	/**
	 * Get Recipe by passing specific recipe id
	 * 
	 * @param id
	 * @return RecipeDTO
	 */
	@Override
	public RecipeDTO findByRecipeId(Long id) {
		logger.info("Start findByRecipeId() method");
		RecipeEntity recipe = recipeRepository.findById(id).orElseThrow(RecordNotFoundException::new);
		logger.info("End findByRecipeId() method");
		return modelMapper.map(recipe, RecipeDTO.class);
	}

	/**
	 * Save new Recipe details
	 * 
	 * @param recipeDto recipe details
	 */
	@Override
	public String createRecipe(RecipeDTO recipeDto) {
		logger.info("Start createRecipe() method");
		RecipeEntity recipe = modelMapper.map(recipeDto, RecipeEntity.class);
		recipe.setCreationDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
		recipeRepository.save(recipe);
		logger.info("End createRecipe() method");
		return SAVE;

	}

	/**
	 * Update existing Recipe details.
	 * 
	 * @param recipeDto recipe details
	 * @param id        recipeId
	 * @return - RecipeDTO Object updated.
	 */
	@Override
	public RecipeDTO updateRecipe(RecipeDTO recipeDto, Long id) {

		logger.info("Start updateRecipe() method");
		List<IngredientsDTO> updatedIngredientList = recipeDto.getIngredientList();
		List<IngredientEntity> ingredientList = updatedIngredientList.stream()
				.map(ingredientDto -> modelMapper.map(ingredientDto, IngredientEntity.class))
				.collect(Collectors.toList());

		return recipeRepository.findById(id).map(recipe -> {
			recipe.setCookingInstructions(recipeDto.getCookingInstructions());
			recipe.setCreationDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
			recipe.setIngredientList(ingredientList);
			recipe.setIsVegetarian(recipeDto.getIsVegetarian());
			recipe.setNumberOfPeople(recipeDto.getNumberOfPeople());
			recipe.setName(recipeDto.getName());
			RecipeEntity recipeObject = recipeRepository.save(recipe);
			return modelMapper.map(recipeObject, RecipeDTO.class);
		}).orElseThrow(RecordNotFoundException::new);

	}

	/**
	 * Delete Recipe details by passing recipe id.
	 * 
	 * @param id
	 */
	@Override
	public String deleteRecipe(Long id) {
		logger.info("Start deleteRecipe() method");
		RecipeEntity recipe = recipeRepository.findById(id).orElseThrow(RecordNotFoundException::new);
		recipeRepository.delete(recipe);
		logger.info("End deleteRecipe() method.");
		return "Deleted Successfully";
	}

}
