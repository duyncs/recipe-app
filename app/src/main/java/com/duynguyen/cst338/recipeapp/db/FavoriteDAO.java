package com.duynguyen.cst338.recipeapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FavoriteDAO {
    @Insert
    void insert(Favorite favorite);

    @Update
    void updateFavorite(Favorite favorite);

    @Delete
    void delete(Favorite favorite);

    @Query("SELECT * FROM favorites WHERE user_id = :userId AND recipe_id = :recipeId")
    Favorite getFavorite(int userId, int recipeId);

    @Query("SELECT * FROM favorites WHERE user_id = :userId")
    List<Favorite> getAllFavorites(int userId);
}