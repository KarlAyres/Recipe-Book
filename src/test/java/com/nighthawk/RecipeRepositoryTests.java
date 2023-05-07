package com.nighthawk;


import com.nighthawk.recipe.Recipe;
import com.nighthawk.recipe.RecipeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

/**
 * The RecipeRepositoryTests class is a series of unit tests. Each of the database functions is tested with simple test
 * data.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RecipeRepositoryTests {
    /**
     * Injects the RecipeRepository class
     */
    @Autowired private RecipeRepository repo;

    /**
     * Method to test the functionality of creating a new recipe
     */
    @Test
    public void testAddNew() {
        Recipe recipe = new Recipe();
        recipe.setRecipeName("Boiled egg");
        recipe.setInstructions("Boil the egg");
        recipe.setIngredientsList("Egg, salt, water");

        Recipe savedRecipe = repo.save(recipe);

        Assertions.assertThat(savedRecipe).isNotNull();
        Assertions.assertThat(savedRecipe.getRecipeId()).isGreaterThan(0);
    }

    /**
     * Method to test the functionality of listing all the recipes
     */
    @Test
    public void testListAll() {
        Iterable<Recipe> recipes = repo.findAll();
        Assertions.assertThat(recipes).hasSizeGreaterThan(0);

        for (Recipe recipe: recipes) {
            System.out.println(recipe);
        }
    }

    /**
     * Method to test the functionality of updating a recipe
     */
    @Test
    public void testUpdate() {
        Integer recipeId = 32;
        Optional<Recipe> optionalRecipe = repo.findById(recipeId);
        Recipe recipe = optionalRecipe.get();
        recipe.setInstructions("Eat the egg raw");
        repo.save(recipe);

        Recipe updatedRecipe = repo.findById(recipeId).get();
        Assertions.assertThat(updatedRecipe.getInstructions()).isEqualTo("Eat the egg raw");
    }

    /**
     * Method to test the functionality of reading a recipe by matching its id
     */
    @Test
    public void testGet() {
         Integer recipeId = 32;
         Optional<Recipe> optionalRecipe = repo.findById(recipeId);
         Assertions.assertThat(optionalRecipe).isPresent();
         System.out.println(optionalRecipe.get());
    }
    /**
     * Method to test the functionality of deleting a recipe by matching its id
     */
    @Test
    public void testDelete() {
        Integer recipeId = 32;
        repo.deleteById(recipeId);
        Optional<Recipe> optionalRecipe = repo.findById(recipeId);
        Assertions.assertThat(optionalRecipe).isNotPresent();
    }
}
