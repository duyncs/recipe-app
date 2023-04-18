package com.duynguyen.cst338.recipeapp.utils;
import com.duynguyen.cst338.recipeapp.R;
import com.duynguyen.cst338.recipeapp.db.AppDataBase;
import com.duynguyen.cst338.recipeapp.db.User;
import com.duynguyen.cst338.recipeapp.db.UserDAO;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
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


        holder.suspendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDao.updateSuspended(user.getId(), !user.isSuspended());
                user.setSuspended(!user.isSuspended());
                notifyDataSetChanged();
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
        private TextView status;
        private Button suspendButton;
//        private Button resetPasswordButton;
//        private Button editDetailsButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            email = itemView.findViewById(R.id.email);
            //status = itemView.findViewById(R.id.status);
            suspendButton = itemView.findViewById(R.id.suspend_button);

        }
    }
}