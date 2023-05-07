package com.nighthawk.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * The RecipeController class and its methods process server requests by handling the user input and implementing the
 * RecipeService class. When a particular URL is requested, the appropriate method is invoked.
 *
 * @author Karl Ayres
 * @version 1.0
 * @since 14/04/2023
 */
@Controller
public class RecipeController {

    /**
     * Injects the RecipeService class
     */
    @Autowired private RecipeService service;

    /**
     * Handles the request for reading the list of recipes
     */
    @GetMapping("/recipes")
    public String showRecipeList(Model model) {
        List<Recipe> listRecipes = service.listAll();
        model.addAttribute("listRecipes", listRecipes);
        return "recipes";
    }

    /**
     * Handles the request for "new" interface, displays new message.
     */
    @GetMapping("/recipes/new")
    public String showNewForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("pageTitle", "Add New Recipe");
        return "recipe_form";
    }

    /**
     * Handles the request for "save" interface, displays save message. Returns user to recipe list.
     */
    @PostMapping("/recipes/save")
    public String saveRecipe(Recipe recipe, RedirectAttributes redirectAttributes) {
        service.save(recipe);
        redirectAttributes.addFlashAttribute("message", "The recipe has been saved successfully!");
        return "redirect:/recipes";
    }

    /**
     * Handles the request for "edit" interface, allows editing of a recipe and saves to the database. Returns user to
     * recipe list.
     */
    @GetMapping("/recipes/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer recipeId, Model model, RedirectAttributes redirectAttributes) {
        try {
            Recipe recipe = service.get(recipeId);
            model.addAttribute("recipe", recipe);
            model.addAttribute("pageTitle", "Edit Recipe (ID: " + recipeId + ")");
            return "recipe_form";
        } catch (RecipeNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/recipes";
        }
    }

    /**
     * Handles the request for "delete" interface, allows deletion of a recipe and updates the database. Displays delete
     * message. Returns user to recipe list.
     */
    @GetMapping ("/recipes/delete/{id}")
    public String deleteRecipe(@PathVariable("id") Integer recipeId, RedirectAttributes redirectAttributes) {
        try {
            service.delete(recipeId);
            redirectAttributes.addFlashAttribute("message", "The recipe ID " + recipeId +
                    " has been deleted.");
        } catch (RecipeNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/recipes";
    }
}
