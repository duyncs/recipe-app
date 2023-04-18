package com.duynguyen.cst338.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.duynguyen.cst338.recipeapp.db.AppDataBase;
import com.duynguyen.cst338.recipeapp.db.User;
import com.duynguyen.cst338.recipeapp.db.UserDAO;

public class LoginActivity extends AppCompatActivity {
    AppDataBase userDatabase;
    UserDAO userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userDao = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries().build().UserDAO();

        if (userDao.countUsers() == 0) {
            userDao.insert(new User("testuser", "testuser", "testuser1@test.com", false,false));
            userDao.insert(new User("admin", "admin", "admin@test.com",false,true));
        }
        Button loginButton = findViewById(R.id.login_button);
        final EditText usernameInput = findViewById(R.id.username_EditText);
        final EditText passwordInput = findViewById(R.id.password_EditText);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                User user = userDao.getUser(username, password);

                if (user != null) {
                    SharedPreferences sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isLoggedIn", true);
                    editor.putString("username", user.getUsername());
                    editor.putBoolean("isAdmin", user.isAdmin());
                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this, LandingPage.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}