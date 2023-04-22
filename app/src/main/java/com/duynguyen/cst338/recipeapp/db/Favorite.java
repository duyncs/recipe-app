package com.duynguyen.cst338.recipeapp.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = AppDataBase.USER_FAVORITES_TABLE,
        foreignKeys = {
                @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Recipe.class, parentColumns = "id", childColumns = "recipe_id", onDelete = ForeignKey.CASCADE)
        },
        indices = {@Index("user_id"), @Index("recipe_id")})
public class Favorite {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "user_id")
    private int userId;

    @ColumnInfo(name = "recipe_id")
    private int recipeId;

    public Favorite(int userId, int recipeId) {
        this.userId = userId;
        this.recipeId = recipeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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