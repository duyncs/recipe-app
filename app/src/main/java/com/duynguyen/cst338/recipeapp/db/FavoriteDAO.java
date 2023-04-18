package com.duynguyen.cst338.recipeapp.db;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteDAO {
    @Insert
    void insertFavorite(Favorite favorite);

    @Delete
    void deleteFavorite(Favorite favorite);

    @Query("SELECT * FROM favorites WHERE user_id = :userId")
    LiveData<List<Favorite>> getFavoritesByUserId(int userId);
}