package com.nighthawk.recipe;

/**
 * The RecipeNotFoundException class is a custom exception class that is thrown when a recipe is not found in the
 * database.
 * The exception is thrown by the RecipeService class,and is handled by the RecipeController class.
 * The exception is thrown when the user attempts to perform CRUD operations on a recipe that does not exist.
 * @author Karl Ayres
 * @version 1.0
 * @since 14/04/2023
 */
public class RecipeNotFoundException extends Throwable {
    public RecipeNotFoundException(String message) {
        super(message);
    }
}
