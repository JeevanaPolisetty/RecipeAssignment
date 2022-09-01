package com.recipe.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.recipe.demo.controller.RecipeAPI;
import com.recipe.demo.dto.SearchDto;
import com.recipe.demo.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.recipe.demo.recipeTypeEnum.TypeEnum;
import com.recipe.demo.dto.RecipeDto;
import com.recipe.demo.entity.Recipe;
import com.recipe.demo.exception.RecipeException;
import com.recipe.demo.repository.RecipeRepository;
import com.recipe.demo.service.RecipeServiceImpl;


@SpringBootTest
public class SpringBootJunitTest {

	@Mock
	RecipeService recipeService;
	@InjectMocks
	RecipeAPI recipeAPI;

	@Mock
	RecipeRepository recipeRepository;

	@InjectMocks
	RecipeServiceImpl recipeServiceImpl;

	//Junit Test cases for Controller
	//Test case for Read all
	@Test
	public void getAllRecipesTest() throws RecipeException{
		List<RecipeDto> myRecipes=new ArrayList<RecipeDto>();
		myRecipes.add(new RecipeDto(1,"Biryani",TypeEnum.NON_VEG,4,"Rice, Masala","Make Biryani"));
		myRecipes.add(new RecipeDto(2,"Pulao",TypeEnum.VEG,9,"Rice, Masala","Make Pulao"));
		when(recipeService.getAllRecipes()).thenReturn(myRecipes); //mocking
		assertEquals(2,recipeAPI.getAllRecipes().getBody().size());
	}

	//Test case for Read specific recipe
	@Test
	public void getRecipeByIdTest() throws RecipeException{
		RecipeDto myRecipe=new RecipeDto(1,"Biryani",TypeEnum.NON_VEG,4,"Rice, Masala","Make Biryani");
		when(recipeService.getRecipeById(1)).thenReturn(myRecipe); //mocking
		assertEquals(1,recipeAPI.getRecipeById(1).getBody().getRecipeNo());
	}

	//Test case for Create new recipe
	@Test
	public void addNewRecipeTest() throws RecipeException, JsonProcessingException {
		Recipe recipe=new Recipe(1,"Biryani",TypeEnum.VEG,4,"Rice, Masala","Make Biryani");
		RecipeDto rdto=new RecipeDto(1,"Biryani",TypeEnum.VEG,4,"Rice, Masala","Make Biryani");
		when(recipeService.addNewRecipe(any(RecipeDto.class))).thenReturn(recipe); //mocking
		assertEquals(recipe,recipeAPI.addNewRecipe(rdto).getBody());
	}

	//Test case for Updating a recipe
	@Test
	public void updateRecipeTest() throws RecipeException{
		RecipeDto rdto=new RecipeDto(1,"Biryani",TypeEnum.VEG,4,"Rice, Masala","Make Biryani");
		doNothing().when(recipeService).updateRecipe(any(Integer.class),any(TypeEnum.class)); //mocking
		assertEquals(recipeAPI.updateRecipe(1,rdto).getBody(),"Recipe successfully updated.");
	}

	//Test case for Deleting a recipe
	@Test
	public void deleteRecipeTest() throws RecipeException{
		doNothing().when(recipeService).deleteRecipe(any(Integer.class)); //mocking
		assertEquals(recipeAPI.deleteRecipe(1).getBody(),"Recipe deleted successfully.");
	}

	//Test case for Search
	@Test
	public void getRecipeBySearchTest() throws RecipeException{
		SearchDto s=new SearchDto(TypeEnum.VEG,2,"pasta",false,"bowl",true);
		List<RecipeDto> myRecipes=new ArrayList<RecipeDto>();
		myRecipes.add(new RecipeDto(1,"Biryani",TypeEnum.NON_VEG,4,"Rice, Masala","Make Biryani"));
		myRecipes.add(new RecipeDto(2,"Pulao",TypeEnum.VEG,9,"Rice, Masala","Make Pulao"));
		when(recipeService.getRecipeBySearch(any(SearchDto.class))).thenReturn(myRecipes); //mocking
		assertEquals(2,recipeAPI.getRecipeBySearch(s).getBody().size());
	}

	/*****************************************************************************/
	//Junit test Cases for Service class
	//Test case for Read all
	@Test
	public void getAllRecipesServiceTest() throws RecipeException{
		List<Recipe> myRecipes=new ArrayList<Recipe>();
		myRecipes.add(new Recipe(1,"Biryani",TypeEnum.NON_VEG,4,"Rice, Masala","Make Biryani"));
		myRecipes.add(new Recipe(2,"Pulao",TypeEnum.VEG,9,"Rice, Masala","Make Pulao"));
		when(recipeRepository.findAll()).thenReturn(myRecipes); //mocking
		assertEquals(2,recipeServiceImpl.getAllRecipes().size());
	}

	//Test case for Read specific recipe
	@Test
	public void getRecipeByIdServiceTest() throws RecipeException{
		Optional<Recipe> myRecipe=Optional.of(new Recipe(1,"Biryani",TypeEnum.NON_VEG,4,"Rice, Masala","Make Biryani"));
		when(recipeRepository.findById(1)).thenReturn(myRecipe); //mocking
		assertEquals(1,recipeServiceImpl.getRecipeById(1).getRecipeNo());
	}

	//Test case for Create new recipe
	@Test
	public void addNewRecipeServiceTest() throws RecipeException{
		Recipe recipe=new Recipe(1,"Biryani",TypeEnum.VEG,4,"Rice, Masala","Make Biryani");
		RecipeDto rdto=new RecipeDto(1,"Biryani",TypeEnum.VEG,4,"Rice, Masala","Make Biryani");
		when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe); //mocking
		assertEquals(recipe,recipeServiceImpl.addNewRecipe(rdto));
	}

	//Test case for Updating a recipe
	@Test
	public void updateRecipeServiceTest() throws RecipeException{
		Optional<Recipe> myRecipe=Optional.of(new Recipe(1,"Biryani",TypeEnum.VEG,4,"Rice, Masala","Make Biryani"));
		when(recipeRepository.findById(1)).thenReturn(myRecipe); //mocking
		recipeServiceImpl.updateRecipe(1,TypeEnum.NON_VEG);
		assertEquals(TypeEnum.NON_VEG,myRecipe.get().getRecipeType());
		verify(recipeRepository,times(1)).findById(1);
	}

	//Test case for Deleting a recipe
	@Test
	public void deleteRecipeServiceTest() throws RecipeException{
		Optional<Recipe> myRecipe=Optional.of(new Recipe(1,"Biryani",TypeEnum.NON_VEG,4,"Rice, Masala","Make Biryani"));
		when(recipeRepository.findById(1)).thenReturn(myRecipe); //mocking
		recipeServiceImpl.deleteRecipe(1);
		verify(recipeRepository,times(1)).deleteById(1);
	}

	//Test case for search
	@Test
	public void getRecipeBySearchServiceTest() throws RecipeException{
		List<Recipe> myRecipes=new ArrayList<Recipe>();
		myRecipes.add(new Recipe(1,"Biryani",TypeEnum.NON_VEG,4,"Rice, Masala","Make Biryani"));
		myRecipes.add(new Recipe(2,"Pulao",TypeEnum.VEG,2,"Rice, Masala","Make Pulao"));
		SearchDto s1=new SearchDto(TypeEnum.VEG,2,"pasta",false,"make",true);
		SearchDto s2=new SearchDto(TypeEnum.VEG,2,"rice",true,"bowl",false);
		when(recipeRepository.findAll()).thenReturn(myRecipes); //mocking
		assertEquals(1,recipeServiceImpl.getRecipeBySearch(s1).size());
		assertEquals(1,recipeServiceImpl.getRecipeBySearch(s2).size());
	}

	/***************************************************************************************/
	//Test cases for invalid scenarios
	@Test
	public void getAllRecipesInvalidTest() throws RecipeException{
		List<Recipe> myRecipes=new ArrayList<Recipe>();
		when(recipeRepository.findAll()).thenReturn(myRecipes); //mocking
		try
		{
			recipeServiceImpl.getAllRecipes();
		}
		catch(RecipeException re)
		{
			String message = "Service.RECIPES_NOT_FOUND";
			assertEquals(message, re.getMessage());
		}
	}
	@Test
	public void getRecipeByIdInvalidTest() throws RecipeException{
		Optional<Recipe> myRecipe=Optional.of(new Recipe());
		when(recipeRepository.findById(1)).thenReturn(myRecipe); //mocking
		try
		{
			recipeServiceImpl.getRecipeById(1);
		}
		catch(RecipeException re)
		{
			String message = "Service.RECIPE_NOT_FOUND";
			assertEquals(message, re.getMessage());
		}
	}
	@Test
	public void updateRecipeInvalidTest() throws RecipeException{
		Optional<Recipe> myRecipe=Optional.of(new Recipe());
		when(recipeRepository.findById(1)).thenReturn(myRecipe); //mocking
		try
		{
			recipeServiceImpl.updateRecipe(1,TypeEnum.NON_VEG);
		}
		catch(RecipeException re)
		{
			String message = "Service.RECIPE_NOT_FOUND";
			assertEquals(message, re.getMessage());
		}
	}
	@Test
	public void deleteRecipeInvalidTest() throws RecipeException{
		Optional<Recipe> myRecipe=Optional.of(new Recipe());
		when(recipeRepository.findById(1)).thenReturn(myRecipe); //mocking
		try
		{
			recipeServiceImpl.deleteRecipe(1);
		}
		catch(RecipeException re)
		{
			String message = "Service.RECIPE_NOT_FOUND";
			assertEquals(message, re.getMessage());
		}
	}
	@Test
	public void getRecipeBySearchInvalidTest() throws RecipeException{
		List<Recipe> myRecipes=new ArrayList<Recipe>();
		myRecipes.add(new Recipe(1,"Biryani",TypeEnum.NON_VEG,4,"Rice, Masala","Make Biryani"));
		myRecipes.add(new Recipe(2,"Pulao",TypeEnum.VEG,2,"Rice, Masala","Make Pulao"));
		SearchDto s=new SearchDto(TypeEnum.NON_VEG,2,"pasta",false,"make",true);
		when(recipeRepository.findAll()).thenReturn(myRecipes); //mocking
		try
		{
			recipeServiceImpl.getRecipeBySearch(s);
		}
		catch(RecipeException re)
		{
			String message = "Service.SEARCH_RECIPE_NOT_FOUND";
			assertEquals(message, re.getMessage());
		}
	}
}
