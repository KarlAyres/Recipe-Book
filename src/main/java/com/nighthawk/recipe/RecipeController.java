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
 * RecipeService class. When a particular URL is requested, the appropriate method is invoked. The methods return the
 * name of the view to be rendered, along with any model data needed to render the view.
 * The @Controller annotation indicates that the class is a controller. The @Autowired annotation tells Spring to
 * inject an instance of the RecipeService class into the controller.
 * The @GetMapping annotation is used to map HTTP GET requests onto specific handler methods. The @PostMapping
 * annotation is used to map HTTP POST requests onto specific handler methods.
 * The @PathVariable annotation is used to extract a variable from the URL. The @ModelAttribute annotation
 * binds a method parameter or method return value to a named model attribute and then exposes it to a web
 * view.
 * The Model interface defines a holder for model attributes and is primarily designed for adding
 * attributes to the model. The RedirectAttributes interface extends the Model interface and adds a
 * method for setting flash attributes. Flash attributes are saved temporarily before the redirect (typically
 * in the session) and are available only during the request redirected to.
 *
 * @author Karl Ayres
 * @version 1.0
 * @since 14/04/2023
 */
@Controller
public class RecipeController {

    /**
     * Injects the RecipeService class
     * @see RecipeService
     */
    @Autowired private RecipeService service;

    /**
     * Handles the request for reading the list of recipes
     * @param model is a holder for model attributes
     */
    @GetMapping("/recipes")
    public String showRecipeList(Model model) {
        List<Recipe> listRecipes = service.listAll();
        model.addAttribute("listRecipes", listRecipes);
        return "recipes";
    }

    /**
     * Handles the request for "new" interface, displays empty form. Returns user to recipe list.
     */
    @GetMapping("/recipes/new")
    public String showNewForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("pageTitle", "Add New Recipe");
        return "recipe_form";
    }

    /**
     * Handles the request for "save" interface, allows saving of a recipe and updates the database. Displays save
     * confirmation message. Returns user to recipe list.
     */
    @PostMapping("/recipes/save")
    public String saveRecipe(Recipe recipe, RedirectAttributes redirectAttributes) {
        service.save(recipe);
        redirectAttributes.addFlashAttribute("message", "The recipe has been saved successfully!");
        return "redirect:/recipes";
    }

    /**
     * Handles the request for "edit" interface, allows editing of a recipe and updates the database. Displays edit
     * confirmation message. Returns user to recipe list.
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
     * confirmation message. Returns user to recipe list.
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
