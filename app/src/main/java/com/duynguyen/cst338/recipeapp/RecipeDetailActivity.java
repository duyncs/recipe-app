package com.duynguyen.cst338.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.room.Room;

import com.duynguyen.cst338.recipeapp.db.AppDataBase;
import com.duynguyen.cst338.recipeapp.db.Recipe;
import com.duynguyen.cst338.recipeapp.db.RecipeDAO;

public class RecipeDetailActivity extends AppCompatActivity {
    private TextView recipeTitle;
    private TextView recipeIngredients;
    private TextView recipeDirections;
    private Button deleteButton;
    private RecipeDAO recipeDao;

    private AppDataBase appDatabase;
    private Recipe recipe;
    private int currentUserId;
    private int recipeId;
    private TextView logOutLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        SharedPreferences sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE);
        boolean isAdmin = sharedPreferences.getBoolean("isAdmin", false);
        currentUserId = sharedPreferences.getInt("userId", -1);

        logOutLink = findViewById(R.id.logOut_link);
        recipeTitle = findViewById(R.id.recipe_title);
        recipeIngredients = findViewById(R.id.recipe_ingredients);
        recipeDirections = findViewById(R.id.recipe_directions);
        deleteButton = findViewById(R.id.delete_button);

        recipeDao = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries().build().RecipeDAO();

        Intent intent = getIntent();
        int recipeId = intent.getIntExtra("recipe_id", -1);

        if (recipeId != -1) {
            recipe = recipeDao.getRecipeById(recipeId);
            recipeTitle.setText(recipe.getTitle());
            recipeIngredients.setText(recipe.getIngredients());
            recipeDirections.setText(recipe.getDirections());
        }

        if (isAdmin || recipe.getUserId() == currentUserId) {
            deleteButton.setVisibility(View.VISIBLE);
        }

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RecipeDetailActivity.this);
                builder.setMessage("Are you sure you want to delete this recipe?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                recipeDao.delete(recipe);
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                builder.create().show();
            }
        });

        //Log Out
        logOutLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(RecipeDetailActivity.this, LoginActivity.class);
                Toast.makeText(RecipeDetailActivity.this, "You has been logout", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });
    }
}