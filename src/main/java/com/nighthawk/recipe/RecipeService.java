package com.nighthawk.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The RecipeService class is implemented by the RecipeController class, and retrieves the information requested by the
 * controller class. The RecipeRepository class is implemented by the methods of this class to perform operations on the
 * database.
 *
 * @author Karl Ayres
 * @version 1.0
 * @since 14/04/2023
 */
@Service
public class RecipeService {
    /**
     * Injects the RecipeRepository class
     */
    @Autowired private RecipeRepository repo;

    /**
     * List all recipes using findAll iterable, calls RecipeRepository for database operations
     * @return List of recipe objects
     */
    public List<Recipe> listAll() {
        return (List<Recipe>) repo.findAll();
    }

    /**
     * Save new recipe, calls RecipeRepository for database operations
     * @param recipe is a new recipe object
     */
    public void save(Recipe recipe) {
        repo.save(recipe);
    }

    /**
     * Getter for recipe object, matches a recipe to its id. Throws exception if no matching id is found.
     * @param recipeId unique id assigned to each recipe
     * @return recipe object matched to id
     * @throws RecipeNotFoundException if the id does not match that of an existing recipe
     */
    public Recipe get(Integer recipeId) throws RecipeNotFoundException {
        Optional<Recipe> result = repo.findById(recipeId);
        if (result.isPresent()) {
            return result.get();
        }
        throw new RecipeNotFoundException("Could not find recipes with ID " + recipeId);
    }

    /**
     * Deletes matching recipe object by id. Throws exception if no matching id is found.
     * @param recipeId unique id assigned to each recipe
     * @throws RecipeNotFoundException if the id does not match that of an existing recipe
     */
    public void delete(Integer recipeId) throws RecipeNotFoundException {
        Long count = repo.countByRecipeId(recipeId);
        if (count == null || count == 0) {
            throw new RecipeNotFoundException("Could not find recipes with ID " + recipeId);
        }
        repo.deleteById(recipeId);
    }
}
