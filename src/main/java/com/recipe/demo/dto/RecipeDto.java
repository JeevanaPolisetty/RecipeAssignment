package com.recipe.demo.dto;

import com.recipe.demo.Enum.TypeEnum;
import com.sun.istack.NotNull;


public class RecipeDto {
	
	private Integer recipeNo; 
	@NotNull
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
	public Integer getRecipeNo() {
		return recipeNo;
	}
	public void setRecipeNo(Integer recipeNo) {
		this.recipeNo = recipeNo;
	}
	public String getRecipeName() {
		return recipeName;
	}
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}
	public TypeEnum getRecipeType() {
		return recipeType;
	}
	public void setRecipeType(TypeEnum recipeType) {
		this.recipeType = recipeType;
	}
	public int getNoOfServings() {
		return noOfServings;
	}
	public void setNoOfServings(int noOfServings) {
		this.noOfServings = noOfServings;
	}
	public String getIngredients() {
		return ingredients;
	}
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	
}
