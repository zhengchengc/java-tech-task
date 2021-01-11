package com.rezdy.lunch.controller;

import com.rezdy.lunch.service.LunchService;
import com.rezdy.lunch.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.tags.EditorAwareTag;

import java.time.LocalDate;
import java.util.List;

@RestController
public class LunchController {

    private LunchService lunchService;

    @Autowired
    public LunchController(LunchService lunchService) {
        this.lunchService = lunchService;
    }


    @GetMapping("/lunch")
    public List<Recipe> getRecipes(@RequestParam(value = "date") String date, @RequestParam(value = "exclude", required = false, defaultValue = "") String excludeIngredient) {
        if (!excludeIngredient.isEmpty()) {
            return lunchService.getNonExpiredRecipesOnDate(LocalDate.parse(date), excludeIngredient);
        } else {
            return lunchService.getNonExpiredRecipesOnDate(LocalDate.parse(date), null);
        }
    }

    @GetMapping("/recipe")
    public Recipe getRecipesByTitle(@RequestParam(value = "title") String title) {
        Recipe recipe = lunchService.getRecipesByTitle(title);
        if (recipe != null) {
            return recipe;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no recipe has the provided title, please try other titles");
        }
    }
}
