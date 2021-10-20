package com.recipe.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This class used to create Ingredient Entity
 * 
 * @author saemmadi
 *
 */
@Entity
@Table(name = "ingredient")
public class IngredientEntity implements Serializable {

	private static final long serialVersionUID = -896573513866831508L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull
	@NotBlank
	@Size(min = 3, message = "Ingredient should be minimum 3 characters")
	@Column(name = "name")
	private String name;

	/**
	 * Default constructor
	 */
	public IngredientEntity() {
	}

	/**
	 * Parameterized constructor
	 * 
	 * @param id
	 * @param name
	 */
	public IngredientEntity(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	@Override
	public String toString() {
		return "IngredientEntity [id=" + id + ", name=" + name + "]";
	}

}
