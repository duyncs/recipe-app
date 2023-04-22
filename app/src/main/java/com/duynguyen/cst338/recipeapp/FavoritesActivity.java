package com.duynguyen.cst338.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.duynguyen.cst338.recipeapp.db.AppDataBase;
import com.duynguyen.cst338.recipeapp.db.Favorite;
import com.duynguyen.cst338.recipeapp.db.FavoriteDAO;
import com.duynguyen.cst338.recipeapp.db.Recipe;
import com.duynguyen.cst338.recipeapp.db.RecipeDAO;
import com.duynguyen.cst338.recipeapp.utils.RecipeListAdapter;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView favoritesRecyclerView;
    private RecipeListAdapter adapter;
    private FavoriteDAO favoriteDAO;
    private RecipeDAO recipeDAO;
    private TextView noFavoritesText;
    private TextView logOutLink;
    private int currentUserId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        SharedPreferences sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE);
        currentUserId = sharedPreferences.getInt("userId", -1);

        noFavoritesText = findViewById(R.id.no_favorites_text);
        logOutLink = findViewById(R.id.logOut_link);

        recipeDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME).allowMainThreadQueries().build().RecipeDAO();
        favoriteDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME).allowMainThreadQueries().build().favoriteDAO();

        favoritesRecyclerView = findViewById(R.id.favorites_recycler_view);
        favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecipeListAdapter(new ArrayList<>(), this);
        favoritesRecyclerView.setAdapter(adapter);

        int userId = getIntent().getIntExtra("user_id", -1);
        if (userId != -1) {
            List<Recipe> favoriteRecipes = new ArrayList<>();
            List<Favorite> favorites = favoriteDAO.getAllFavorites(userId);

            for (Favorite favorite : favorites) {
                Recipe recipe = recipeDAO.getRecipeById(favorite.getRecipeId());
                if (recipe != null) {
                    favoriteRecipes.add(recipe);
                }
            }

            if (favoriteRecipes.isEmpty()) {
                noFavoritesText.setVisibility(View.VISIBLE);
            } else {
                noFavoritesText.setVisibility(View.GONE);
            }

            adapter.setRecipes(favoriteRecipes);
        } else {
            Log.e("FavoritesActivity", "Invalid user ID");
        }

        //Log Out
        logOutLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(FavoritesActivity.this, LoginActivity.class);
                Toast.makeText(FavoritesActivity.this, "You has been logout", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });
    }
}