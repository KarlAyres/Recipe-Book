package com.nighthawk.recipe;

import org.springframework.data.repository.CrudRepository;

/**
 * The RecipeRepository interface is implemented by the RecipeService class, and performs operations on the database.
 * The RecipeRepository interface extends the CrudRepository interface, which provides CRUD operations.
 *
 * @author Karl Ayres
 * @version 1.0
 * @since 14/04/2023
 */
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
    public Long countByRecipeId(Integer recipeId);
}
