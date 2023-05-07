package com.nighthawk.recipe;

/**
 * RecipeNotFoundException handles invalid user input. If no matching recipe id is found, the exception is thrown. Error
 * message can be altered per instantiation.
 *
 * @author Karl Ayres
 * @version 1.0
 * @since 14/04/2023
 */
public class RecipeNotFoundException extends Throwable {
    public RecipeNotFoundException(String message) {
        super(message);
    }
}
