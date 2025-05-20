package com.example.notedapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ReleaseInfoScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_release_info_screen);

        //apothikeysh dedomenwn kai emfanish pou stalthikan apo thn SearchScreen
        // apothikeysh dedomenwn
        String title = getIntent().getStringExtra("title");
        int imageId = getIntent().getIntExtra("imageId", -1);
        String artist = getIntent().getStringExtra("artist");
        int year = getIntent().getIntExtra("year", -1);
        String releaseType = getIntent().getStringExtra("type");
        String rating = getIntent().getStringExtra("rating");
        String description = getIntent().getStringExtra("description");


        TextView titleText = findViewById(R.id.releaseTitle);
        ImageView imageView = findViewById(R.id.itemIcon);
        TextView artistText = findViewById(R.id.releaseArtist);
        TextView yearText = findViewById(R.id.releaseDate);
        TextView releaseTypeText = findViewById(R.id.releaseType);
        TextView ratingText = findViewById(R.id.ratingInd);
        RatingBar ratingBar = findViewById(R.id.ratingBarTotal);
        TextView infoText = findViewById(R.id.releaseInfoText);
        TextView ratingTotalCnt = findViewById(R.id.releaseRevCount);
        LinearLayout reviewsContainer = findViewById(R.id.reviewsContainer);

        String yearString = String.valueOf(year); //metatroph tou year se string

        // emfanish dedomenwn
        titleText.setText(title);
        imageView.setImageResource(imageId);
        artistText.setText(artist);
        yearText.setText(yearString);
        releaseTypeText.setText(releaseType);
        ratingText.setText(rating);
        infoText.setText(description);

        //Symplhrwma twn asteriwn me vash to ratingText
        if (ratingText != null) {
            String raw = ratingText.getText().toString().replace("(", "").replace(")", "").trim(); // Πάρε το string και μετά κάνε replace

            String regex = "^([0-9]+\\.?[0-9]*)\\/([0-9]+)$";

            if (raw.matches(regex)) {
                String[] parts = raw.split("/");

                try {
                    float ratingValue = Float.parseFloat(parts[0]);
                    ratingBar.setRating(ratingValue);
                } catch (NumberFormatException e) {
                    ratingBar.setRating(0);
                }
            } else {
                ratingBar.setRating(0);
            }
        }


        // Eμφανισε τα reviews για καθε κυκλοφορία
        Release currentRelease = null;
        for (Release r : DBmanager.releases) {
            if (r.title.equals(title)) {
                currentRelease = r;
                break;
            }
        }

        if (currentRelease != null) {
            int reviewCount = currentRelease.getReviewCount(); // evresh toy plhthous twn kritikwn
            ratingTotalCnt.setText("(" + reviewCount + ")");
            for (Review review : DBmanager.reviews) {
                if (review.getReleaseId() == currentRelease.id) {
                    TextView reviewView = new TextView(this);
                    reviewView.setText("@" + review.getUsername() + ": " + review.getComment() + " (" + review.getRating() + ")");
                    reviewView.setTextSize(15);
                    reviewView.setPadding(0, 8, 0, 8);
                    reviewsContainer.addView(reviewView);
                }
            }
        }



    }
}