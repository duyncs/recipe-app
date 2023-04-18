package com.duynguyen.cst338.recipeapp.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;


@Entity(tableName = AppDataBase.USER_FAVORITES_TABLE, primaryKeys = {"user_id", "recipe_id"})
public class Favorite {
    @ColumnInfo(name = "user_id")
    private int userId;

    @ColumnInfo(name = "recipe_id")
    private int recipeId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
}