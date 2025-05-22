package com.example.notedapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    private List<Review> reviews; // Array of reviews

    public ReviewAdapter(List<Review> reviews) {
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the review card layout
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_card_item, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = reviews.get(position);

        Release reviewedRelease = DBmanager.getReleaseById(review.getReleaseId());

        holder.coverImage.setImageResource(reviewedRelease.imageId);
        holder.title.setText(reviewedRelease.title);
        holder.artist.setText(reviewedRelease.artist);
        holder.rating.setText(review.getRating());
        holder.author.setText(review.getUsername());
        holder.reviewText.setText(review.getComment());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        ImageView coverImage;
        TextView title, artist, rating, author, reviewText;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            coverImage = itemView.findViewById(R.id.release_cover);
            title = itemView.findViewById(R.id.release_title);
            artist = itemView.findViewById(R.id.release_artist);
            rating = itemView.findViewById(R.id.review_rating);
            author = itemView.findViewById(R.id.review_author);
            reviewText = itemView.findViewById(R.id.review_text);
        }
    }
}
