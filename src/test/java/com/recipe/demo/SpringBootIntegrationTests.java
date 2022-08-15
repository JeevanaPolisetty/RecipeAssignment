package com.recipe.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipe.demo.Enum.TypeEnum;
import com.recipe.demo.dto.RecipeDto;
import com.recipe.demo.entity.Recipe;
import com.recipe.demo.service.RecipeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class SpringBootIntegrationTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    RecipeServiceImpl recipeService;
    @Autowired
    ObjectMapper mapper;
    @Test
    public void getAllRecipesIntegrationTest() throws Exception {
        List<RecipeDto> myRecipes=new ArrayList<RecipeDto>();
        myRecipes.add(new RecipeDto(1,"Biryani", TypeEnum.NON_VEG,4,"Rice, Masala","Make Biryani"));
        myRecipes.add(new RecipeDto(2,"Pulao",TypeEnum.VEG,9,"Rice, Masala","Make Pulao"));
        when(recipeService.getAllRecipes()).thenReturn(myRecipes);
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(mapper.writeValueAsString(myRecipes))));
    }

    @Test
    public void getRecipeByIdIntegrationTest() throws Exception {
        RecipeDto recipeDto=new RecipeDto(1,"Biryani", TypeEnum.NON_VEG,4,"Rice, Masala","Make Biryani");
        when(recipeService.getRecipeById(1)).thenReturn(recipeDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(mapper.writeValueAsString(recipeDto))));
    }

    @Test
    public void addNewRecipeIntegrationTest() throws Exception {
        RecipeDto recipeDto=new RecipeDto("Biryani", TypeEnum.NON_VEG,4,"Rice, Masala","Make Biryani");
        Recipe recipe=new Recipe(1,"Biryani",TypeEnum.NON_VEG,4,"Rice, Masala","Make Biryani");
        when(recipeService.addNewRecipe(recipeDto)).thenReturn(recipe);
        mockMvc.perform(MockMvcRequestBuilders.post("/recipe").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(recipeDto)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void updateRecipeIntegrationTest() throws Exception {
        RecipeDto recipeDto=new RecipeDto(1,"Biryani", TypeEnum.NON_VEG,4,"Rice, Masala","Make Biryani");
        mockMvc.perform(MockMvcRequestBuilders.put("/recipe/1").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(recipeDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Recipe successfully updated.")));

    }

    @Test
    public void deleteRecipeIntegrationTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/recipe/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Recipe deleted successfully.")));
    }

    }
