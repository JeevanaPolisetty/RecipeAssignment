package com.recipe.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.recipe.demo.recipeTypeEnum.TypeEnum;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class for recipe
 */
@Entity
@Table(name="recipe")
@Getter
@Setter
public class Recipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer recipeNo;
	private String recipeName;
	@Column(columnDefinition = "enum('Vegetarian','Non_Vegetarian')")
    @Enumerated(EnumType.STRING)
	private TypeEnum recipeType;
	private int noOfServings;
	private String ingredients;
	private String instructions;
	
	public Recipe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Recipe(Integer recipeNo, String recipeName, TypeEnum recipeType, int noOfServings, String ingredients,
			String instructions) {
		super();
		this.recipeNo = recipeNo;
		this.recipeName = recipeName;
		this.recipeType = recipeType;
		this.noOfServings = noOfServings;
		this.ingredients = ingredients;
		this.instructions = instructions;
	}

	public Recipe(String recipeName, TypeEnum recipeType, int noOfServings, String ingredients, String instructions) {
		super();
		this.recipeName = recipeName;
		this.recipeType = recipeType;
		this.noOfServings = noOfServings;
		this.ingredients = ingredients;
		this.instructions = instructions;
	}


}
