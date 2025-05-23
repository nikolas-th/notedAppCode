package com.example.notedapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class CreateListItemAdapter extends RecyclerView.Adapter<CreateListItemAdapter.ReleaseViewHolder> {

    private final List<Release> releaseList;
    private final Context context;
    private final OnRemoveClickListener removeClickListener;

    public interface OnRemoveClickListener {
        void onRemoveClick(int position);
    }

    public CreateListItemAdapter(Context context, List<Release> releaseList, OnRemoveClickListener listener) {
        this.context = context;
        this.releaseList = releaseList;
        this.removeClickListener = listener;
    }

    @NonNull
    @Override
    public ReleaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.release_list_create_list_item, parent, false);
        return new ReleaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReleaseViewHolder holder, int position) {
        Release release = releaseList.get(position);

        holder.releaseName.setText(release.title);
        holder.artistName.setText(release.artist);
        holder.coverImage.setImageResource(release.imageId);

        holder.removeButton.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION) {
                removeClickListener.onRemoveClick(adapterPosition);
            }
        });

    }

    @Override
    public int getItemCount() {
        return releaseList.size();
    }

    public void swapItems(int fromPosition, int toPosition) {
        Collections.swap(releaseList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    public static class ReleaseViewHolder extends RecyclerView.ViewHolder {
        ImageView coverImage;
        TextView releaseName, artistName;
        ImageButton removeButton;

        public ReleaseViewHolder(@NonNull View itemView) {
            super(itemView);
            coverImage = itemView.findViewById(R.id.release_cover);
            releaseName = itemView.findViewById(R.id.release_name);
            artistName = itemView.findViewById(R.id.release_artist_name);
            removeButton = itemView.findViewById(R.id.remove_button);
        }
    }
}
