package com.nighthawk.recipe;

import org.springframework.data.repository.CrudRepository;

/**
 * RecipeRepository interface is an extension of the Spring CrudRepository framework. The interface is implemented to
 * handle the CRUD operations.
 *
 * @author Karl Ayres
 * @version 1.0
 * @since 14/04/2023
 */
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
    public Long countByRecipeId(Integer recipeId);
}
