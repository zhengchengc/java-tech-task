package com.rezdy.lunch.service;

import com.rezdy.lunch.model.Ingredient;
import com.rezdy.lunch.model.Recipe;
import com.rezdy.lunch.repository.RecipeRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class LunchService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private RecipeRepository recipeRepository;

    /**
     * This method returns recipes exclude the list of ingredients
     * Ingredients needs to be submitted seperated by ","
     * @param date
     * @param excludeIngredient
     * @return list of recipes
     */
    public List<Recipe> getNonExpiredRecipesOnDate(LocalDate date, String excludeIngredient) {
        List<String> exclude = new ArrayList<>();

        if (excludeIngredient != null) {
            exclude = Arrays.asList(excludeIngredient.split(","));
        }

        List<Recipe> recipes = loadRecipes(date, exclude);

        return recipes;
    }

    /**
     * This method checks the recipe has expired ingredient or the ingredient is on excluded list
     * @param recipe
     * @param localDate
     * @param excludeIngredient
     * @return boolean value
     */
    public boolean hasExpiredIngredient(Recipe recipe, LocalDate localDate, List<String> excludeIngredient) {
        if (recipe.getIngredients() != null) {
            for (Ingredient ingredient : recipe.getIngredients()) {
                if (ingredient.getUseBy().isBefore(localDate)) {
                    return true;
                }
                if (excludeIngredient != null && excludeIngredient.contains(ingredient.getTitle())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    /**
     * This method returns recipes meet the requirements
     * @param localDate
     * @param excludeIngredient
     * @return
     */
    public List<Recipe> loadRecipes(LocalDate localDate, List<String> excludeIngredient) {
        List<Recipe> recipes = new ArrayList<>();
        List<Recipe> allRecipes = recipeRepository.findAll();

        if (allRecipes != null) {
            for (Recipe recipe : allRecipes) {
                if (!hasExpiredIngredient(recipe, localDate, excludeIngredient)) {
                    recipes.add(recipe);
                }
            }
        }
        return recipes;
    }

    /**
     * This method returns recipe with provide title
     * @param title
     * @return recipe
     */
    public Recipe getRecipesByTitle(String title) {
        return recipeRepository.findByTitle(title);
    }

}
