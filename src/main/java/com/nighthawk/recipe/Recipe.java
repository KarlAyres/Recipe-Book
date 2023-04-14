package com.nighthawk.recipe;

import jakarta.persistence.*;

@Entity
@Table(name = "recipe")
public class Recipe {
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

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(String ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

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
