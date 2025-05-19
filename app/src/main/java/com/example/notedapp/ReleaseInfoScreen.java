package com.example.notedapp;

import android.os.Bundle;
import android.widget.ImageView;
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

        // emfanish dedomenwn
        TextView titleText = findViewById(R.id.releaseTitle);
        ImageView imageView = findViewById(R.id.itemIcon);
        TextView artistText = findViewById(R.id.releaseArtist);

        titleText.setText(title);
        imageView.setImageResource(imageId);
        artistText.setText(artist);
    }
}