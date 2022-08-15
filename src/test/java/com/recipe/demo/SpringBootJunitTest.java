package com.recipe.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.recipe.demo.Enum.TypeEnum;
import com.recipe.demo.dto.RecipeDto;
import com.recipe.demo.entity.Recipe;
import com.recipe.demo.exception.RecipeException;
import com.recipe.demo.repository.RecipeRepository;
import com.recipe.demo.service.RecipeServiceImpl;


@SpringBootTest
public class SpringBootJunitTest {

	@Mock
	RecipeRepository recipeRepository;

	@InjectMocks
	RecipeServiceImpl recipeService;


	//Test case for Read all
	@Test
	public void getAllRecipesTest() throws RecipeException{
		List<Recipe> myRecipes=new ArrayList<Recipe>();
		myRecipes.add(new Recipe(1,"Biryani",TypeEnum.NON_VEG,4,"Rice, Masala","Make Biryani"));
		myRecipes.add(new Recipe(2,"Pulao",TypeEnum.VEG,9,"Rice, Masala","Make Pulao"));
		when(recipeRepository.findAll()).thenReturn(myRecipes); //mocking
		assertEquals(2,recipeService.getAllRecipes().size());

	}

	//Test case for Read specific recipe
	@Test
	public void getRecipeByIdTest() throws RecipeException{
		Optional<Recipe> myRecipe=Optional.of(new Recipe(1,"Biryani",TypeEnum.NON_VEG,4,"Rice, Masala","Make Biryani"));
		when(recipeRepository.findById(1)).thenReturn(myRecipe); //mocking
		assertEquals(1,recipeService.getRecipeById(1).getRecipeNo());

	}

	//Test case for Create new recipe
	@Test
	public void addNewRecipeTest() throws RecipeException{
		Recipe recipe=new Recipe(1,"Biryani",TypeEnum.VEG,4,"Rice, Masala","Make Biryani");
		RecipeDto rdto=new RecipeDto(1,"Biryani",TypeEnum.VEG,4,"Rice, Masala","Make Biryani");
		when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe); //mocking
		assertEquals(recipe,recipeService.addNewRecipe(rdto));
	}	

	//Test case for Updating a recipe
	@Test
	public void updateRecipeTest() throws RecipeException{
		Optional<Recipe> myRecipe=Optional.of(new Recipe(1,"Biryani",TypeEnum.NON_VEG,4,"Rice, Masala","Make Biryani"));
		when(recipeRepository.findById(1)).thenReturn(myRecipe); //mocking
		assertEquals(TypeEnum.NON_VEG,recipeService.getRecipeById(1).getRecipeType());
		verify(recipeRepository,times(1)).findById(1);
	}

	//Test case for Deleting a recipe
	@Test
	public void deleteRecipeTest() throws RecipeException{
		Optional<Recipe> myRecipe=Optional.of(new Recipe(1,"Biryani",TypeEnum.NON_VEG,4,"Rice, Masala","Make Biryani"));
		when(recipeRepository.findById(1)).thenReturn(myRecipe); //mocking
		recipeService.deleteRecipe(1);
		verify(recipeRepository,times(1)).deleteById(1);
	}

}
