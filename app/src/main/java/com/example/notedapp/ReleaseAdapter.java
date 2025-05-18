package com.example.notedapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


// ReleaseAdapter.java
public class ReleaseAdapter extends RecyclerView.Adapter<ReleaseAdapter.ReleaseViewHolder> {
    private List<Release> releases; // List to hold all release data

    // Constructor - takes the list of releases to display
    public ReleaseAdapter(List<Release> releases) {
        this.releases = releases;
    }

    // Called when RecyclerView needs a new ViewHolder
    @NonNull
    @Override
    public ReleaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.release_card_item, parent, false);
        return new ReleaseViewHolder(view);
    }

    // Binds data to the views for a specific position
    @Override
    public void onBindViewHolder(@NonNull ReleaseViewHolder holder, int position) {
        // Get the release at current position
        Release release = releases.get(position);

        // Set the text and image for this release item
        holder.title.setText(release.title);
        holder.artist.setText(release.artist);
        holder.artwork.setImageResource(release.imageId); // Set image from drawable
    }

    // Returns total number of items in the list
    @Override
    public int getItemCount() {
        return releases.size();
    }

    // ViewHolder class that holds references to all views in an item
    public static class ReleaseViewHolder extends RecyclerView.ViewHolder {
        ImageView artwork; // Album artwork image
        TextView title;    // Release title text
        TextView artist;   // Artist name text

        public ReleaseViewHolder(@NonNull View itemView) {
            super(itemView);
            // Find all views in the layout
            artwork = itemView.findViewById(R.id.release_cover);
            title = itemView.findViewById(R.id.release_title);
            artist = itemView.findViewById(R.id.release_artist);
        }
    }
}