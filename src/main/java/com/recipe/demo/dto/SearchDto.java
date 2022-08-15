package com.recipe.demo.dto;

import com.recipe.demo.Enum.TypeEnum;

public class SearchDto {
	
	private TypeEnum recipeType;
	private int noOfServings;
	private String ingredients;
	private Boolean ingredientInclude;
	private String instructions;
	private Boolean instructionInclude;
	
	public SearchDto(TypeEnum recipeType, int noOfServings, String ingredients, Boolean ingredientInclude,
			String instructions, Boolean instructionInclude) {
		super();
		this.recipeType = recipeType;
		this.noOfServings = noOfServings;
		this.ingredients = ingredients;
		this.ingredientInclude = ingredientInclude;
		this.instructions = instructions;
		this.instructionInclude = instructionInclude;
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
	public Boolean getIngredientInclude() {
		return ingredientInclude;
	}
	public void setIngredientInclude(Boolean ingredientInclude) {
		this.ingredientInclude = ingredientInclude;
	}
	public Boolean getInstructionInclude() {
		return instructionInclude;
	}
	public void setInstructionInclude(Boolean instructionInclude) {
		this.instructionInclude = instructionInclude;
	}


}
