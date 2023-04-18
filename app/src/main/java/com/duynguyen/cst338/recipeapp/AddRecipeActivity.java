package com.duynguyen.cst338.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.duynguyen.cst338.recipeapp.db.AppDataBase;
import com.duynguyen.cst338.recipeapp.db.Recipe;
import com.duynguyen.cst338.recipeapp.db.RecipeDAO;
import com.duynguyen.cst338.recipeapp.db.UserDAO;

public class AddRecipeActivity extends AppCompatActivity {
    private EditText titleEditText;
    private EditText ingredientsEditText;
    private EditText directionsEditText;
    private Button submitButton;

    private RecipeDAO recipeDAO;
    private int currentUserId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        if (getIntent().getExtras() != null) {
            currentUserId = getIntent().getIntExtra("currentUserId", -1);
        }

        titleEditText = findViewById(R.id.title_edit_text);
        ingredientsEditText = findViewById(R.id.ingredients_edit_text);
        directionsEditText = findViewById(R.id.directions_edit_text);
        submitButton = findViewById(R.id.submit_button);

        recipeDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries().build().RecipeDAO();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String ingredients = ingredientsEditText.getText().toString();
                String directions = directionsEditText.getText().toString();

                if (title.isEmpty() || ingredients.isEmpty() || directions.isEmpty()) {
                    Toast.makeText(AddRecipeActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    Recipe newRecipe = new Recipe(currentUserId, title, ingredients, directions);
                    recipeDAO.insert(newRecipe);
                    Toast.makeText(AddRecipeActivity.this, "Recipe added successfully", Toast.LENGTH_SHORT).show();
                    finish(); // Close the activity and return to the previous one
                }
            }
        });
    }
}