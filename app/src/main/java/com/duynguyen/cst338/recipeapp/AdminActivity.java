package com.duynguyen.cst338.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.duynguyen.cst338.recipeapp.db.AppDataBase;
import com.duynguyen.cst338.recipeapp.db.Recipe;
import com.duynguyen.cst338.recipeapp.db.RecipeDAO;
import com.duynguyen.cst338.recipeapp.db.User;
import com.duynguyen.cst338.recipeapp.db.UserDAO;
import com.duynguyen.cst338.recipeapp.utils.RecipeListAdapter;
import com.duynguyen.cst338.recipeapp.utils.UserListAdapter;

import java.util.List;

public class AdminActivity extends AppCompatActivity {
    private UserDAO userDAO;
    private RecipeDAO recipeDAO;
    private AppDataBase appDataBase;
    private RecyclerView userRecyclerView;
    private TextView logOutLink;
    private int currentUserId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        SharedPreferences sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE);
        currentUserId = sharedPreferences.getInt("userId", -1);

        logOutLink = findViewById(R.id.logOut_link);
        userRecyclerView = findViewById(R.id.user_recycler_view);
        appDataBase = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries().build();

        userDAO = appDataBase.UserDAO();

        List<User> users = userDAO.getAllUsers();
        UserListAdapter userListAdapter = new UserListAdapter(users, this);
        userRecyclerView.setAdapter(userListAdapter);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Log Out
        logOutLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(AdminActivity.this, LoginActivity.class);
                Toast.makeText(AdminActivity.this, "You has been logout", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });
    }


}