package com.duynguyen.cst338.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import androidx.room.Room;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.duynguyen.cst338.recipeapp.db.AppDataBase;
import com.duynguyen.cst338.recipeapp.db.User;
import com.duynguyen.cst338.recipeapp.db.UserDAO;

public class RegisterActivity extends AppCompatActivity {
    AppDataBase userDatabase;
    UserDAO userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button registerButton = findViewById(R.id.register_button);
        final EditText usernameInput = findViewById(R.id.username_input);
        final EditText emailInput = findViewById(R.id.email_input);
        final EditText passwordInput = findViewById(R.id.password_input);

        userDao = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries().build().UserDAO();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();
                String email = emailInput.getText().toString().trim();

                if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }
                User user = new User(username, password, email, false,false);
                user.setEmail(email);

                if (userDao.getUserByUsername(username) != null) {
                    Toast.makeText(RegisterActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (userDao.getUserByEmail(email) != null) {
                    Toast.makeText(RegisterActivity.this, "Email already exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                userDao.insert(user);
                Toast.makeText(RegisterActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}