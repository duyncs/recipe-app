package com.duynguyen.cst338.recipeapp.utils;

import com.duynguyen.cst338.recipeapp.R;
import com.duynguyen.cst338.recipeapp.db.AppDataBase;
import com.duynguyen.cst338.recipeapp.db.User;
import com.duynguyen.cst338.recipeapp.db.UserDAO;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    private List<User> users;
    private Context context;

    AppDataBase userDatabase;
    UserDAO userDao;

    public UserListAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
        userDao = userDatabase.getInstance(context).UserDAO();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get(position);
        holder.username.setText(user.getUsername());
        holder.email.setText(user.getEmail());

        if (user.isSuspended()) {
            holder.status_Textview.setText("inactive");
            holder.status_Button.setText("activate");
        } else {
            holder.status_Textview.setText("active");
            holder.status_Button.setText("suspend");
        }

        // Activate or Suspend a user account
        holder.status_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want to " + holder.status_Button.getText() + " " + user.getUsername() + "?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                user.setSuspended(true);
                                notifyDataSetChanged();
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
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView username;
        private TextView email;
        private TextView status_Textview;
        private Button status_Button;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            email = itemView.findViewById(R.id.email);
            status_Textview = itemView.findViewById(R.id.status_Textview);
            status_Button = itemView.findViewById(R.id.status_button);
        }
    }
}