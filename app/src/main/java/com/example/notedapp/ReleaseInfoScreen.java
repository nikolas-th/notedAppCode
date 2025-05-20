package com.example.notedapp;

import android.os.Bundle;
import android.widget.ImageView;
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

        // emfanish dedomenwn
        TextView titleText = findViewById(R.id.releaseTitle);
        ImageView imageView = findViewById(R.id.itemIcon);
        TextView artistText = findViewById(R.id.releaseArtist);
        TextView yearText = findViewById(R.id.releaseDate);
        TextView releaseTypeText = findViewById(R.id.releaseType);
        TextView ratingText = findViewById(R.id.ratingInd);
        RatingBar ratingBar = findViewById(R.id.ratingBarTotal);

        String yearString = String.valueOf(year); //metatroph tou year se string

        titleText.setText(title);
        imageView.setImageResource(imageId);
        artistText.setText(artist);
        yearText.setText(yearString);
        releaseTypeText.setText(releaseType);
        ratingText.setText(rating);

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


    }
}