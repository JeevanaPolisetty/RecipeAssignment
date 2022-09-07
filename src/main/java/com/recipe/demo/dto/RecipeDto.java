package com.recipe.demo.dto;

import com.recipe.demo.recipeTypeEnum.TypeEnum;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Dto class for recipe
 */

@Getter
@Setter
public class RecipeDto {
	
	private Integer recipeNo;
	private String recipeName;
	private TypeEnum recipeType;
	private int noOfServings;
	private String ingredients;
	private String instructions;
	
	public RecipeDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public RecipeDto(Integer recipeNo, String recipeName, TypeEnum recipeType, int noOfServings, String ingredients,
			String instructions) {
		super();
		this.recipeNo = recipeNo;
		this.recipeName = recipeName;
		this.recipeType = recipeType;
		this.noOfServings = noOfServings;
		this.ingredients = ingredients;
		this.instructions = instructions;
	}

	public RecipeDto(String recipeName, TypeEnum recipeType, int noOfServings, String ingredients,
			String instructions) {
		super();
		this.recipeName = recipeName;
		this.recipeType = recipeType;
		this.noOfServings = noOfServings;
		this.ingredients = ingredients;
		this.instructions = instructions;
	}

}
