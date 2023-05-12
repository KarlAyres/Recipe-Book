package com.nighthawk.recipe;
import jakarta.persistence.*;

/**
 * The Recipe class is the model for the recipebook database. The class is implemented by the RecipeService class, and
 * retrieves the information requested by the service class. The RecipeRepository class is implemented by the methods of
 * this class to perform operations on the database.
 * The class is annotated with the @Entity annotation to indicate that it is a JPA entity.
 * The @Table annotation specifies the name of the database table to be used for mapping.
 * The @Id annotation specifies the primary key of the entity.
 * The @GeneratedValue annotation specifies that the primary key is automatically allocated by the database.
 * The @Column annotation specifies the name of the column in the database table to be used for mapping, and specifies
 * the constraints to be applied to the column.
 *
 * @author Karl Ayres
 * @version 1.0
 * @since 14/04/2023
 */
@Entity
@Table(name = "recipe")
public class Recipe {

    /**
     * Recipe class fields
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id", nullable = false, unique = true)
    private Integer recipeId;
    @Column(name = "recipe_name", nullable = false, unique = true, length = 50)
    private String recipeName;
    @Column(name = "instructions", nullable = false, unique = true, length = 1000)
    private String instructions;
    @Column(name = "ingredients_list", nullable = false, unique = true, length = 500)
    private String ingredientsList;

    /**
     * Recipe id getter
     */
    public Integer getRecipeId() {
        return recipeId;
    }
    /**
     * Recipe id setter
     */
    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }
    /**
     * Recipe name getter
     */
    public String getRecipeName() {
        return recipeName;
    }
    /**
     * Recipe name setter
     */
    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }
    /**
     * Recipe instructions getter
     */
    public String getInstructions() {
        return instructions;
    }
    /**
     * Recipe instructions setter
     */
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    /**
     * Recipe ingredients list getter
     */
    public String getIngredientsList() {
        return ingredientsList;
    }
    /**
     * Recipe ingredients list setter
     */
    public void setIngredientsList(String ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    /**
     * Override toString method to display recipe object fields
     */
    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId=" + recipeId +
                ", recipeName='" + recipeName + '\'' +
                ", instructions='" + instructions + '\'' +
                ", ingredientsList='" + ingredientsList + '\'' +
                '}';
    }
}
