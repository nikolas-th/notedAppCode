// com.example.notedapp.UserAdapter
package com.example.notedapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {

    private List<User> originalUsers;
    private List<User> filteredUsers;
    private int currentUserId;


    public UserAdapter(Context context, List<User> users, int currentUserId) {
        super(context, 0, users);
        this.originalUsers = new ArrayList<>(users);
        this.filteredUsers = new ArrayList<>(users);
        this.currentUserId = currentUserId;
    }

    @Override
    public int getCount() {
        return filteredUsers.size();
    }

    @Override
    public User getItem(int position) {
        return filteredUsers.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User user = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_list_item, parent, false);
        }

        TextView usernameView = convertView.findViewById(R.id.usernameTextView);
        TextView rankView = convertView.findViewById(R.id.rankTextView);

        usernameView.setText(user.getUsername());
        rankView.setText(user.getRank());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<User> filteredResults = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    filteredResults.addAll(originalUsers);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (User user : originalUsers) {
                        if (user.getId() != currentUserId &&
                                user.getUsername().toLowerCase().contains(filterPattern)) {
                            filteredResults.add(user);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredResults;
                results.count = filteredResults.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredUsers.clear();
                filteredUsers.addAll((List<User>) results.values);
                notifyDataSetChanged();
            }
        };
    }
}
