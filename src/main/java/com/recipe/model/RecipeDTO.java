package com.recipe.model;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * RecipeDTO class for recipe data members
 * 
 * @author saemmadi
 *
 */
public class RecipeDTO {

	private Long recipeId;
	private String creationDateTime;
	private Boolean isVegetarian;

	@NotNull(message = "Recipe Name Should Not Be Empty")
	@NotBlank
	@Size(min = 5, max = 30, message = "Recipe name should not be less than 5 and more than 30 character.")
	private String name;

	@Min(value = 1, message = "Number of people should be minimum 1 person")
	private Integer numberOfPeople;

	private List<IngredientsDTO> ingredientList;

	@NotNull
	@NotBlank(message = "cookingInstructions Should Not Be Empty")
	private String cookingInstructions;

	/**
	 * Default Constructor
	 */
	public RecipeDTO() {
	}

	/**
	 * @param recipeId
	 * @param creationDateTime
	 * @param isVegetarian
	 * @param name
	 * @param numberOfPeople
	 * @param ingredientList
	 * @param cookingInstructions
	 */
	public RecipeDTO(Long recipeId, String creationDateTime, Boolean isVegetarian, String name, Integer numberOfPeople,
			List<IngredientsDTO> ingredientList, String cookingInstructions) {
		super();
		this.recipeId = recipeId;
		this.creationDateTime = creationDateTime;
		this.isVegetarian = isVegetarian;
		this.name = name;
		this.numberOfPeople = numberOfPeople;
		this.ingredientList = ingredientList;
		this.cookingInstructions = cookingInstructions;
	}

	public Long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Long recipeId) {
		this.recipeId = recipeId;
	}

	public String getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(String creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public Boolean getIsVegetarian() {
		return isVegetarian;
	}

	public void setIsVegetarian(Boolean isVegetarian) {
		this.isVegetarian = isVegetarian;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(Integer numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	public List<IngredientsDTO> getIngredientList() {
		return ingredientList;
	}

	public void setIngredientList(List<IngredientsDTO> ingredientList) {
		this.ingredientList = ingredientList;
	}

	public String getCookingInstructions() {
		return cookingInstructions;
	}

	public void setCookingInstructions(String cookingInstructions) {
		this.cookingInstructions = cookingInstructions;
	}

	@Override
	public String toString() {
		return "RecipeDTO [recipeId=" + recipeId + ", creationDateTime=" + creationDateTime + ", isVegetarian="
				+ isVegetarian + ", name=" + name + ", numberOfPeople=" + numberOfPeople + ", ingredientList="
				+ ingredientList + ", cookingInstructions=" + cookingInstructions + "]";
	}

}
