package com.duynguyen.cst338.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        if (getIntent().getExtras() != null) {
            currentUserId = getIntent().getIntExtra("currentUserId", -1);
        }

        recipeRecyclerView = findViewById(R.id.recipe_list_recyclerview);

        recipeDao = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries().build().RecipeDAO();

        List<Recipe> recipes = recipeDao.getAllRecipes();
        RecipeListAdapter recipeListAdapter = new RecipeListAdapter(recipes, this);
        recipeRecyclerView.setAdapter(recipeListAdapter);
        recipeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Recipe> recipes = recipeDao.getAllRecipes();
        RecipeListAdapter recipeListAdapter = new RecipeListAdapter(recipes, this);
        recipeRecyclerView.setAdapter(recipeListAdapter);
    }
}