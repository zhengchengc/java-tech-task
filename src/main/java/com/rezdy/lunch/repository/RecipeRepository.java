package com.rezdy.lunch.repository;

import com.rezdy.lunch.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, String> {

    Recipe findByTitle(String title);
}
