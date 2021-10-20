package com.recipe.model;

/**
 * IngredientsDTO class for ingredient data members.
 * 
 * @author saemmadi
 *
 */
public class IngredientsDTO {

	private Long id;
	private String name;

	/**
	 * Default constructor
	 */
	public IngredientsDTO() {
	}

	/**
	 * Parameterized constructor
	 * 
	 * @param id
	 * @param name
	 */
	public IngredientsDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "IngredientsDTO [id=" + id + ", name=" + name + "]";
	}

}
