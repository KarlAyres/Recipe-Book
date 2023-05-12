package com.nighthawk.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The RecipeService class is implemented by the RecipeController class, and retrieves the information requested by the
 * controller class. The RecipeRepository class is implemented by the methods of this class to perform operations on the
 * database. The class is annotated with the @Service annotation to indicate that it is a service class. The @Autowired
 * annotation injects the RecipeRepository class's dependencies into the service class.
 * @author Karl Ayres
 * @version 1.0
 * @since 14/04/2023
 */
@Service
public class RecipeService {
    /**
     * Injects the RecipeRepository class's dependencies into the service class
     */
    @Autowired private RecipeRepository repo;

    /**
     * Lists all recipes, calls RecipeRepository for database operations.
     * @return list of all recipes
     */
    public List<Recipe> listAll() {
        return (List<Recipe>) repo.findAll();
    }

    /**
     * Saves recipe object, calls RecipeRepository for database operations.
     * @param recipe recipe object to be saved
     */
    public void save(Recipe recipe) {
        repo.save(recipe);
    }

    /**
     * Gets matching recipe object by id. Throws exception if no matching id is found. Calls RecipeRepository for database
     * operations.
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
     * Deletes matching recipe object by id. Throws exception if no matching id is found. Calls RecipeRepository for database
     * operations.
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
