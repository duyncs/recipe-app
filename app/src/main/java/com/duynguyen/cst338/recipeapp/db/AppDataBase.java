package com.duynguyen.cst338.recipeapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Recipe.class, Favorite.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public static final String DATABASE_NAME = "Recipe.db";
    public static final String USER_TABLE = "users";
    public static final String RECIPE_TABLE = "recipes";
    public static final String USER_FAVORITES_TABLE = "favorites";
    private static volatile AppDataBase instance;
    private static final Object LOCK = new Object();

    public abstract UserDAO UserDAO();
    public abstract RecipeDAO RecipeDAO();
    public abstract FavoriteDAO favoriteDAO();

    public static AppDataBase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }
}