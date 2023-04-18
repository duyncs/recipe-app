package com.duynguyen.cst338.recipeapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.duynguyen.cst338.recipeapp.db.User;

public class LandingPage extends AppCompatActivity {
    private int currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        if (getIntent().getExtras() != null) {
            currentUserId = getIntent().getIntExtra("currentUserId", -1);
        }
        SharedPreferences sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        boolean isAdmin = sharedPreferences.getBoolean("isAdmin", false);

        TextView welcomeText = findViewById(R.id.welcome_text);
        Button adminButton = findViewById(R.id.manage_users_button);
        Button logoutButton = findViewById(R.id.logout_button);
        Button addRecipeButton = findViewById(R.id.add_recipe_button);
        Button viewRecipesButton = findViewById(R.id.view_recipes_button);
        welcomeText.setText("Welcome, " + username);

        if (isAdmin) {
            adminButton.setVisibility(View.VISIBLE);
        }

        // Handle the Add Recipe button
        addRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingPage.this, AddRecipeActivity.class);
                intent.putExtra("currentUserId", currentUserId);
                startActivity(intent);
            }
        });

        // Handle the View Recipes button
        viewRecipesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingPage.this, RecipeListActivity.class);
                startActivity(intent);
            }
        });

        // Admin area button
        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingPage.this, AdminActivity.class);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(LandingPage.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}