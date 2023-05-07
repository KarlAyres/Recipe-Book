package com.nighthawk.recipe;
import jakarta.persistence.*;

/**
 * The recipe class is implemented to create recipe objects. Each recipe object will have an id, a name, a set of
 * instructions, and a list of ingredients.
 *
 * @author Karl Ayres
 * @version 1.0
 * @since 14/04/2023
 */
@Entity
@Table(name = "recipe")
public class Recipe {

    /**
     * Recipe data fields mapped to their respective columns in the recipebook database
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
    Override toString method to display recipe object fields
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
