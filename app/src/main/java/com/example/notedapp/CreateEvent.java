package com.example.notedapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class CreateEvent extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText titleEditText, artistEditText, genreEditText, locationEditText,
            areaEditText, dateEditText, timeEditText, priceEditText, participantsEditText, descriptionEditText;
    private ImageView posterImageView;
    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        // Σύνδεση views
        titleEditText = findViewById(R.id.titleEditText);
        artistEditText = findViewById(R.id.artistEditText);
        genreEditText = findViewById(R.id.genreEditText);
        locationEditText = findViewById(R.id.locationEditText);
        areaEditText = findViewById(R.id.areaEditText);
        dateEditText = findViewById(R.id.dateEditText);
        timeEditText = findViewById(R.id.timeEditText);
        priceEditText = findViewById(R.id.priceEditText);
        participantsEditText = findViewById(R.id.participantsEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        posterImageView = findViewById(R.id.posterImageView);

        Button selectPosterButton = findViewById(R.id.selectPosterButton);
        Button saveEventButton = findViewById(R.id.saveEventButton);

        selectPosterButton.setOnClickListener(v -> openImageChooser());

        saveEventButton.setOnClickListener(v -> {
            String title = titleEditText.getText().toString().trim();
            String artist = artistEditText.getText().toString().trim();

            if (title.isEmpty() || artist.isEmpty()) {
                Toast.makeText(this, "Συμπληρώστε τουλάχιστον Τίτλο και Καλλιτέχνη", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Η εκδήλωση αποθηκεύτηκε επιτυχώς!", Toast.LENGTH_SHORT).show();
                // Εδώ μπορείς να αποθηκεύσεις τα δεδομένα
            }
        });

        ImageButton menuButton = findViewById(R.id.menuButton);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        menuButton.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            Toast.makeText(this, "Επιλέχθηκε: " + item.getTitle(), Toast.LENGTH_SHORT).show();
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    private void openImageChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Επιλέξτε Εικόνα"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            posterImageView.setImageURI(selectedImageUri);
        }
    }
}
