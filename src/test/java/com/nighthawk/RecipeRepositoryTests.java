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

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RecipeRepositoryTests {
    @Autowired private RecipeRepository repo;

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

    @Test
    public void testListAll() {
        Iterable<Recipe> recipes = repo.findAll();
        Assertions.assertThat(recipes).hasSizeGreaterThan(0);

        for (Recipe recipe: recipes) {
            System.out.println(recipe);
        }
    }

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

    @Test
    public void testGet() {
         Integer recipeId = 32;
         Optional<Recipe> optionalRecipe = repo.findById(recipeId);
         Assertions.assertThat(optionalRecipe).isPresent();
         System.out.println(optionalRecipe.get());
    }

    @Test
    public void testDelete() {
        Integer recipeId = 32;
        repo.deleteById(recipeId);
        Optional<Recipe> optionalRecipe = repo.findById(recipeId);
        Assertions.assertThat(optionalRecipe).isNotPresent();
    }
}
