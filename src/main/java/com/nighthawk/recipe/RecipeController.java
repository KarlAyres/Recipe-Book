package com.nighthawk.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RecipeController {
    @Autowired private RecipeService service;

    @GetMapping("/recipes")
    public String showRecipeList(Model model) {
        List<Recipe> listRecipes = service.listAll();
        model.addAttribute("listRecipes", listRecipes);
        return "recipes";
    }

    @GetMapping("/recipes/new")
    public String showNewForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "recipe_form";
    }

}
