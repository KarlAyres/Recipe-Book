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
 * data. The tests are run using the JUnit framework.
 * The tests are run using the @DataJpaTest annotation, which allows the tests to run in the context of a JPA database.
 * The @AutoConfigureTestDatabase annotation is used to configure the test database to use the application properties.
 * The @Rollback annotation is used to prevent the tests from rolling back the changes made to the database.
 * The @Autowired annotation is used to inject the RecipeRepository class into the test class.
 * The @Test annotation is used to identify the methods that are run as tests.
 * The Assertions.assertThat() method is used to assert that the test results are as expected.
 * The Assertions.assertThatThrownBy() method is used to assert that the test results are as expected, and that an
 * exception is thrown.
 *
 * @see Recipe
 * @see RecipeRepository
 *
 * @author Karl Ayres
 * @version 1.1 Added documentation
 * @since 14/04/2023
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
     * Method to test the functionality of adding a new recipe. The test data is created using the Recipe class.
     * The test data is saved to the database using the save() method of the RecipeRepository class.
     * The assertThat() method is used to assert that the saved recipe is not null, and that the recipe id is greater
     * than 0.
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
