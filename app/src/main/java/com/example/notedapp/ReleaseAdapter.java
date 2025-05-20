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

public class ReleaseAdapter extends RecyclerView.Adapter<ReleaseAdapter.ReleaseViewHolder> {
    private Release[] releases;
    private Context context;

    public ReleaseAdapter(Context context, Release[] releases) {
        this.context = context;
        this.releases = releases;
    }

    @NonNull
    @Override
    public ReleaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.release_card_item, parent, false);
        return new ReleaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReleaseViewHolder holder, int position) {
        Release release = releases[position];

        holder.title.setText(release.title);
        holder.artist.setText(release.artist);
        holder.artwork.setImageResource(release.imageId);

        // 🔗 Handle click
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ReleaseInfoScreen.class);
            intent.putExtra("title", release.title);
            intent.putExtra("imageId", release.imageId);
            intent.putExtra("artist", release.artist);
            intent.putExtra("year", release.year);
            intent.putExtra("type", release.genre);
            intent.putExtra("rating", release.rating);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return releases.length;
    }

    public static class ReleaseViewHolder extends RecyclerView.ViewHolder {
        ImageView artwork;
        TextView title;
        TextView artist;

        public ReleaseViewHolder(@NonNull View itemView) {
            super(itemView);
            artwork = itemView.findViewById(R.id.release_cover);
            title = itemView.findViewById(R.id.release_title);
            artist = itemView.findViewById(R.id.release_artist);
        }
    }
}
