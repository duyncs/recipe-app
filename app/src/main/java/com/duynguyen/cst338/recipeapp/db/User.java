package com.duynguyen.cst338.recipeapp.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = AppDataBase.USER_TABLE)
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "is_suspended")
    private boolean isSuspended;

    @ColumnInfo(name = "is_admin")
    private boolean isAdmin;

    public User(String username, String password, String email, boolean isSuspended, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isSuspended = isSuspended;
        this.isAdmin = isAdmin;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String setEmail(String email) {
        return this.email= email;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        this.isSuspended = suspended;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}