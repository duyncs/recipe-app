package com.duynguyen.cst338.recipeapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RecipeDAO {
    @Insert
    void insert(Recipe recipe);

    @Query("SELECT * FROM recipes")
    List<Recipe> getAllRecipes();

    @Query("SELECT * FROM recipes WHERE id = :id")
    Recipe getRecipeById(int id);
    @Update
    void updateReceipt(Recipe recipe);
    @Delete
    void delete(Recipe recipe);
}