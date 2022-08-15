package com.recipe.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.recipe.demo.Enum.TypeEnum;
import com.sun.istack.NotNull;


@Entity
@Table(name="recipe")

public class Recipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer recipeNo; 
	@NotNull
	private String recipeName;
	@Column(columnDefinition = "enum('Vegetarian','Non_Vegetarian')")
    @Enumerated(EnumType.STRING)
	private TypeEnum recipeType;
	private int noOfServings;
	private String ingredients;
	private String instructions;
	
	public Recipe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Recipe(Integer recipeNo, String recipeName, TypeEnum recipeType, int noOfServings, String ingredients,
			String instructions) {
		super();
		this.recipeNo = recipeNo;
		this.recipeName = recipeName;
		this.recipeType = recipeType;
		this.noOfServings = noOfServings;
		this.ingredients = ingredients;
		this.instructions = instructions;
	}

	public Recipe(String recipeName, TypeEnum recipeType, int noOfServings, String ingredients, String instructions) {
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
