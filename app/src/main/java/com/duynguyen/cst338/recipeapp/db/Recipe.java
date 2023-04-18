package com.duynguyen.cst338.recipeapp.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = AppDataBase.RECIPE_TABLE)
public class Recipe {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "user_id")
    private int userId;
    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "ingredients")
    private String ingredients;

    @ColumnInfo(name = "directions")
    private String directions;

    public Recipe(int userId, String title, String ingredients, String directions) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.ingredients = ingredients;
        this.directions = directions;
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
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

}