package com.recipe.demo.dto;

import com.recipe.demo.recipeTypeEnum.TypeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * Dto class to specify search criteria
 */
@Getter
@Setter
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

}
