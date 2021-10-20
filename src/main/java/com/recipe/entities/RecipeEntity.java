package com.recipe.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * This class is used to create Recipe Entity
 * 
 * @author saemmadi
 *
 */
@Entity
@Table(name = "recipe")
public class RecipeEntity implements Serializable {

	private static final long serialVersionUID = -4873805520945117457L;

	@Id
	@Column(name = "recipe_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "recipe_name")
	private String name;

	@Column(name = "number_of_people")
	private Integer numberOfPeople;

	@Column(name = "creation_date_time")
	private String creationDateTime;

	@Column(name = "is_vegetarian")
	private Boolean isVegetarian;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "recipe_id", nullable = false)
	private List<IngredientEntity> ingredientList;

	@NotNull
	@NotBlank
	@Column(name = "cooking_instructions")
	private String cookingInstructions;

	/**
	 * Default constructor
	 */
	public RecipeEntity() {
	}

	/**
	 * Parameterized constructor
	 * 
	 * @param id
	 * @param name
	 * @param numberOfPeople
	 * @param creationDateTime
	 * @param isVegetarian
	 * @param ingredientList
	 * @param cookingInstructions
	 */
	public RecipeEntity(Long id, String name, Integer numberOfPeople, String creationDateTime, Boolean isVegetarian,
			List<IngredientEntity> ingredientList, String cookingInstructions) {
		super();
		this.id = id;
		this.name = name;
		this.numberOfPeople = numberOfPeople;
		this.creationDateTime = creationDateTime;
		this.isVegetarian = isVegetarian;
		this.ingredientList = ingredientList;
		this.cookingInstructions = cookingInstructions;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the numberOfPeople
	 */
	public Integer getNumberOfPeople() {
		return numberOfPeople;
	}

	/**
	 * @param numberOfPeople the numberOfPeople to set
	 */
	public void setNumberOfPeople(Integer numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	/**
	 * @return the creationDateTime
	 */
	public String getCreationDateTime() {
		return creationDateTime;
	}

	/**
	 * @param creationDateTime the creationDateTime to set
	 */
	public void setCreationDateTime(String creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	/**
	 * @return the isVegetarian
	 */
	public Boolean getIsVegetarian() {
		return isVegetarian;
	}

	/**
	 * @param isVegetarian the isVegetarian to set
	 */
	public void setIsVegetarian(Boolean isVegetarian) {
		this.isVegetarian = isVegetarian;
	}

	/**
	 * @return the ingredientList
	 */
	public List<IngredientEntity> getIngredientList() {
		return ingredientList;
	}

	/**
	 * @param ingredientList the ingredientList to set
	 */
	public void setIngredientList(List<IngredientEntity> ingredientList) {
		this.ingredientList = ingredientList;
	}

	/**
	 * @return the cookingInstructions
	 */
	public String getCookingInstructions() {
		return cookingInstructions;
	}

	/**
	 * @param cookingInstructions the cookingInstructions to set
	 */
	public void setCookingInstructions(String cookingInstructions) {
		this.cookingInstructions = cookingInstructions;
	}

	@Override
	public String toString() {
		return "RecipeEntity [id=" + id + ", name=" + name + ", numberOfPeople=" + numberOfPeople
				+ ", creationDateTime=" + creationDateTime + ", isVegetarian=" + isVegetarian + ", ingredientList="
				+ ingredientList + ", cookingInstructions=" + cookingInstructions + "]";
	}

}
