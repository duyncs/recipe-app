package com.duynguyen.cst338.recipeapp.db;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void insert(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    User getUser(String username, String password);

    @Query("SELECT * FROM users WHERE username = :username")
    User getUserByUsername(String username);

    @Query("SELECT * FROM users WHERE email = :email")
    User getUserByEmail(String email);

    @Query("SELECT * FROM users WHERE is_admin = 0")
    List<User> getAllUsers();

    @Query("UPDATE users SET suspended = :suspended WHERE id = :userId")
    void updateSuspended(int userId, boolean suspended);

    @Query("SELECT COUNT(*) from users")
    int countUsers();
}