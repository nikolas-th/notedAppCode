package com.example.notedapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReleaseListAdapter extends RecyclerView.Adapter<ReleaseListAdapter.ReleaseListViewHolder> {

    private final Context context;
    private final List<ReleaseList> releaseLists;

    public ReleaseListAdapter(Context context, List<ReleaseList> releaseLists) {
        this.context = context;
        this.releaseLists = releaseLists;
    }

    @NonNull
    @Override
    public ReleaseListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_of_release_lists_item, parent, false);
        return new ReleaseListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReleaseListViewHolder holder, int position) {
        ReleaseList list = releaseLists.get(position);
        holder.titleTextView.setText(list.getTitle());
        holder.authorTextView.setText(list.getAuthor());
        holder.dateTextView.setText(list.getDate());

        // Click Behavior for each item.
        holder.itemView.setOnClickListener(v -> {
            Intent myListsIntent = new Intent(context, CreateListScreen.class);
            myListsIntent.putExtra("listIndex", position);
            context.startActivity(myListsIntent);
        });
    }

    @Override
    public int getItemCount() {
        return releaseLists.size();
    }

    public static class ReleaseListViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView authorTextView;
        TextView dateTextView;
        ImageView iconImageView;

        public ReleaseListViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.list_title);
            authorTextView = itemView.findViewById(R.id.list_author);
            dateTextView = itemView.findViewById(R.id.list_date);
            iconImageView = itemView.findViewById(R.id.imageView2);
        }
    }
}
