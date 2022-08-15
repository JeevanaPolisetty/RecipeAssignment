package com.recipe.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.recipe.demo.entity.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Integer>{

	Optional<Recipe> findByRecipeName(String recipeName);

	void deleteByRecipeName(String recipeName);
	

}
