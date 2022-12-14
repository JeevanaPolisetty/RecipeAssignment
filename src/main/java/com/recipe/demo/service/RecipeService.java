package com.recipe.demo.service;

import java.util.List;

import com.recipe.demo.recipeTypeEnum.TypeEnum;
import com.recipe.demo.dto.RecipeDto;
import com.recipe.demo.dto.SearchDto;
import com.recipe.demo.entity.Recipe;
import com.recipe.demo.exception.RecipeException;

/**
 * Interface for service methods
 */
public interface RecipeService {

	List<RecipeDto> getAllRecipes()throws RecipeException;

	RecipeDto getRecipeById(Integer recipeNo)throws RecipeException;

	Recipe addNewRecipe(RecipeDto recipe)throws RecipeException;

	void updateRecipe(Integer recipeNo, TypeEnum recipeType)throws RecipeException;

	void deleteRecipe(Integer recipeNo)throws RecipeException;

	List<RecipeDto> getRecipeBySearch(SearchDto recipe)throws RecipeException;

	

}
