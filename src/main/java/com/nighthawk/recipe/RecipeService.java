package com.nighthawk.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    @Autowired private RecipeRepository repo;

    public List<Recipe> listAll() {
        return (List<Recipe>) repo.findAll();
    }
}
