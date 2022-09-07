package com.recipe.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.demo.recipeTypeEnum.TypeEnum;
import com.recipe.demo.dto.RecipeDto;
import com.recipe.demo.dto.SearchDto;
import com.recipe.demo.entity.Recipe;
import com.recipe.demo.exception.RecipeException;
import com.recipe.demo.repository.RecipeRepository;

@Service(value = "RecipeService")
@Transactional
public class RecipeServiceImpl implements RecipeService{

	@Autowired
	RecipeRepository recipeRepository;

	/**
	 * Fetches all recipes
 	 * @return List of recipes
	 * @throws RecipeException
	 */
	@Override
	public List<RecipeDto> getAllRecipes() throws RecipeException {
		Iterable<Recipe> recipeDetails = recipeRepository.findAll();
		List<RecipeDto> recipes = new ArrayList<>();
		recipeDetails.forEach(recipe -> {
			RecipeDto recipe1 = new RecipeDto();
			setFields(recipe, recipe1);
			recipes.add(recipe1);
		});
		if (recipes.isEmpty())
			throw new RecipeException("Service.RECIPES_NOT_FOUND");
		return recipes;
		
	}

	/**
	 * Extracted method to set fields
	 * @param recipe
	 * @param recipe1
	 */
	private void setFields(Recipe recipe, RecipeDto recipe1) {
		recipe1.setRecipeNo(recipe.getRecipeNo());
		recipe1.setRecipeName(recipe.getRecipeName());
		recipe1.setRecipeType(recipe.getRecipeType());
		recipe1.setNoOfServings(recipe.getNoOfServings());
		recipe1.setIngredients(recipe.getIngredients());
		recipe1.setInstructions(recipe.getInstructions());
	}

	/**
	 * Fetches recipe based on recipe id
	 * @param recipeNo
	 * @return recipe details
	 * @throws RecipeException
	 */
	@Override
	public RecipeDto getRecipeById(Integer recipeNo) throws RecipeException {
		Recipe recipe = getRecipe(recipeNo);
		RecipeDto recipe1 = new RecipeDto();
		setFields(recipe, recipe1);
		return recipe1;
	}

	/**
	 * Extracted method to check findById null exception
	 * @param recipeNo
	 * @return
	 * @throws RecipeException
	 */
	private Recipe getRecipe(Integer recipeNo) throws RecipeException {
		Optional<Recipe> optional = recipeRepository.findById(recipeNo);
		Recipe recipe = optional.orElseThrow(() -> new RecipeException("Service.RECIPE_NOT_FOUND"));
		return recipe;
	}

	/**
	 * Creates new recipe
	 * @param recipe
	 * @return recipe details
	 * @throws RecipeException
	 */
	@Override
	public Recipe addNewRecipe(RecipeDto recipe) throws RecipeException {
		Recipe recipe1 = new Recipe();
		recipe1.setRecipeNo(recipe.getRecipeNo());
		recipe1.setRecipeName(recipe.getRecipeName());
		recipe1.setRecipeType(recipe.getRecipeType());
		recipe1.setNoOfServings(recipe.getNoOfServings());
		recipe1.setIngredients(recipe.getIngredients());
		recipe1.setInstructions(recipe.getInstructions());
		Recipe newRecipe = recipeRepository.save(recipe1);
		return newRecipe;
	}

	/**
	 * Updates the recipe type
	 * @param recipeNo
	 * @param recipeType
	 * @throws RecipeException
	 */
	@Override
	public void updateRecipe(Integer recipeNo,TypeEnum recipeType) throws RecipeException {
		Recipe recipe = getRecipe(recipeNo);
		recipe.setRecipeType(recipeType);
	}

	/**
	 * Deletes the recipe based on recipe id
	 * @param recipeNo
	 * @throws RecipeException
	 */
	@Override
	public void deleteRecipe(Integer recipeNo) throws RecipeException {
		Recipe recipe = getRecipe(recipeNo);
		recipeRepository.deleteById(recipeNo);
	}

	/**
	 * Searches the recipes based on given criteria
	 * @param recipe
	 * @return list of recipes that match the criteria
	 * @throws RecipeException
	 */
	@Override
	public List<RecipeDto> getRecipeBySearch(SearchDto recipe) throws RecipeException {
		Iterable<Recipe> recipeDetails = recipeRepository.findAll();
		List<RecipeDto> recipes = new ArrayList<>();
		recipeDetails.forEach(r -> {
			RecipeDto recipe1 = new RecipeDto();
			setFields(r, recipe1);
			recipes.add(recipe1);
		});
		//Converting list to stream
		Stream<RecipeDto> filteredList=recipes.stream();
		
		//To filter based on vegetarian or non vegetarian
		if(recipe.getRecipeType()!=null)
		{
			filteredList=filteredList.filter(x->x.getRecipeType().name().equalsIgnoreCase((recipe.getRecipeType()).name()));
		}
		//To filter based on number of servings
		if(recipe.getNoOfServings()>0)
		{
			filteredList=filteredList.filter(x->x.getNoOfServings()==(recipe.getNoOfServings()));
		}
		//To filter based on specific ingredients included or excluded
		if(recipe.getIngredients()!=null)
		{
			if(recipe.getIngredientInclude()) 
				filteredList=filteredList.filter(x->(x.getIngredients().toLowerCase()).contains(recipe.getIngredients().toLowerCase()));
			else
				filteredList=filteredList.filter(x->!((x.getIngredients().toLowerCase()).contains(recipe.getIngredients().toLowerCase())));	
		}
		//To filter based on instructions included or excluded
		if(recipe.getInstructions()!=null) 
		{
			if(recipe.getInstructionInclude())
				filteredList=filteredList.filter(x->(x.getInstructions().toLowerCase()).contains(recipe.getInstructions().toLowerCase()));
			else
				filteredList=filteredList.filter(x->!((x.getInstructions().toLowerCase()).contains(recipe.getInstructions().toLowerCase())));
		}
		
		//Converting the stream back to list 
		List<RecipeDto> finalList=filteredList.collect(Collectors.toList());
		if(finalList.isEmpty())
			throw new RecipeException("Service.SEARCH_RECIPE_NOT_FOUND");
		return finalList;
	}
	
}
