package com.rezdy.lunch;

import com.rezdy.lunch.model.Ingredient;
import com.rezdy.lunch.model.Recipe;
import com.rezdy.lunch.repository.IngredientRepository;
import com.rezdy.lunch.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class EntityTest {

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @Test
    public void ingredEntityTest() {
        Optional<Ingredient> ing = ingredientRepository.findById("Butter");
        Ingredient ingredient = ing.get();
        assertNotNull(ingredient);
    }

    @Test
    public void recipeEntityTest() {
        Optional<Recipe> recipes = recipeRepository.findById("Hotdog");
        assertNotNull(recipes.get());
    }
}
