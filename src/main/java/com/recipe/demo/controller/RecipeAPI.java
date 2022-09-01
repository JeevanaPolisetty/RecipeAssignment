package com.recipe.demo.controller;

import java.util.List;

import com.recipe.demo.service.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipe.demo.dto.RecipeDto;
import com.recipe.demo.dto.SearchDto;
import com.recipe.demo.entity.Recipe;
import com.recipe.demo.exception.RecipeException;
import com.recipe.demo.service.RecipeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.slf4j.LoggerFactory;
import org.apache.catalina.mapper.Mapper;
import org.slf4j.Logger;

@CrossOrigin
@RestController
public class RecipeAPI {

	@Autowired
	RecipeService recipeService;
	
	Logger logger=LoggerFactory.getLogger(RecipeAPI.class);

	//API to READ a recipe
	@Operation(summary = "Get recipes",description= "Get a list of recipes",tags="Get")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Found the recipes", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = RecipeDto.class)) }),
	  @ApiResponse(responseCode = "404", description = "Recipes not found", 
	    content = @Content) })
	@GetMapping(value = "/recipe",produces = "application/json")
	public ResponseEntity<List<RecipeDto>> getAllRecipes() throws RecipeException {
		logger.info("Entered get all method");
		List<RecipeDto> recipeList = recipeService.getAllRecipes();
		logger.info("Exited get all method");
		return new ResponseEntity<>(recipeList, HttpStatus.OK);
	}

	//API to READ a recipe by given id
	@Operation(summary = "Get specific recipe",description= "Get a recipe by id",tags="Get")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Found the recipe", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = RecipeDto.class)) }),
	  @ApiResponse(responseCode = "404", description = "Recipe not found", 
	    content = @Content) })
	@GetMapping(value = "/recipe/{recipeNo}",produces = "application/json")
	public ResponseEntity<RecipeDto> getRecipeById(@PathVariable Integer recipeNo) throws RecipeException {
		logger.info("Called get method with id - "+ recipeNo);
		RecipeDto recipe = recipeService.getRecipeById(recipeNo);
		return new ResponseEntity<>(recipe, HttpStatus.OK);
	}
	
	//API to CREATE a recipe
	@Operation(summary = "Add recipe",description= "Add a new recipe",tags="Post")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "201", description = "Added the recipe",
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = RecipeDto.class)) }),
	  @ApiResponse(responseCode = "404", description = "Recipe not added", 
	    content = @Content) })
	@PostMapping(value = "/recipe",consumes="application/json")
	public ResponseEntity<Recipe> addNewRecipe(@RequestBody RecipeDto recipe) throws RecipeException, JsonProcessingException {
		Recipe r = recipeService.addNewRecipe(recipe);
		logger.info("Added new recipe");
		return new ResponseEntity<>(r, HttpStatus.CREATED);
	}

	//API to UPDATE a recipe
	@Operation(summary = "Update recipe",description= "Update a recipe",tags="Put")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Updated the recipe", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = RecipeDto.class)) }),
	  @ApiResponse(responseCode = "404", description = "Recipe not updated", 
	    content = @Content) })
	@PutMapping(value = "/recipe/{recipeNo}",consumes="application/json")
	public ResponseEntity<String> updateRecipe(@PathVariable Integer recipeNo, @RequestBody RecipeDto recipe)
			throws RecipeException {
		recipeService.updateRecipe(recipeNo,recipe.getRecipeType());
		String successMessage = "Recipe successfully updated.";
		logger.info("Updated the recipe with id - "+recipeNo);
		return new ResponseEntity<>(successMessage,HttpStatus.OK);
	}

	//API to DELETE a recipe
	@DeleteMapping(value = "/recipe/{recipeNo}")
	@Operation(summary = "Delete recipe",description= "Delete a recipe",tags="Delete")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Deleted the recipe", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = RecipeDto.class)) }),
	  @ApiResponse(responseCode = "404", description = "Recipe not deleted", 
	    content = @Content) })
	public ResponseEntity<String> deleteRecipe(@PathVariable Integer recipeNo) throws RecipeException {
		recipeService.deleteRecipe(recipeNo);
		String successMessage ="Recipe deleted successfully.";
		logger.info("Deleted the recipe with id - "+recipeNo);
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}

	//API to SEARCH a recipe based on given criteria
	@Operation(summary = "Search recipe",description= "Search a recipe based on given criteria",tags="Post")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Found the recipe", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = RecipeDto.class)) }),
	  @ApiResponse(responseCode = "404", description = "Recipe not found", 
	    content = @Content) })
	@PostMapping(value = "/recipe/search",consumes="application/json",produces = "application/json")
	public ResponseEntity<List<RecipeDto>> getRecipeBySearch(@RequestBody SearchDto recipe) throws RecipeException {
		logger.info("Started filtering");
		List<RecipeDto> recipeList = recipeService.getRecipeBySearch(recipe);
		logger.info("Filtering Done");
		return new ResponseEntity<>(recipeList, HttpStatus.OK);
	}
}
