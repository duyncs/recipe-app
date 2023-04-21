package com.duynguyen.cst338.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.duynguyen.cst338.recipeapp.db.AppDataBase;
import com.duynguyen.cst338.recipeapp.db.Recipe;
import com.duynguyen.cst338.recipeapp.db.RecipeDAO;
import com.duynguyen.cst338.recipeapp.utils.RecipeListAdapter;

import java.util.List;

public class RecipeListActivity extends AppCompatActivity {
    private RecyclerView recipeRecyclerView;
    private RecipeDAO recipeDao;
    private int currentUserId;

    private TextView logOutLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        SharedPreferences sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE);
        currentUserId = sharedPreferences.getInt("userId", -1);

        logOutLink = findViewById(R.id.logOut_link);
        recipeRecyclerView = findViewById(R.id.recipe_list_recyclerview);

        recipeDao = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries().build().RecipeDAO();

        List<Recipe> recipes = recipeDao.getAllRecipes();
        RecipeListAdapter recipeListAdapter = new RecipeListAdapter(recipes, this);
        recipeRecyclerView.setAdapter(recipeListAdapter);
        recipeRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Log Out
        logOutLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(RecipeListActivity.this, LoginActivity.class);
                Toast.makeText(RecipeListActivity.this, "You has been logout", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Recipe> recipes = recipeDao.getAllRecipes();
        RecipeListAdapter recipeListAdapter = new RecipeListAdapter(recipes, this);
        recipeRecyclerView.setAdapter(recipeListAdapter);
    }
}