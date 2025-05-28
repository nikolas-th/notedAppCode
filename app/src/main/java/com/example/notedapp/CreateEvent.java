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

        descriptionEditText = findViewById(R.id.descriptionEditText);



        Button saveEventButton = findViewById(R.id.saveEventButton);



        saveEventButton.setOnClickListener(v -> {
            String eventTitle = titleEditText.getText().toString();
            String eventArtist = artistEditText.getText().toString();
            String eventGenre = genreEditText.getText().toString();
            String eventLocation = locationEditText.getText().toString();
            String eventArea = areaEditText.getText().toString();
            String eventDate = dateEditText.getText().toString();
            String eventTime = timeEditText.getText().toString();
            Event newEvent = new Event(eventTitle, eventArtist, eventGenre, eventArea, eventLocation, eventDate, eventTime);


            if (eventTitle.isEmpty() || eventArtist.isEmpty()) {
                Toast.makeText(this, "Συμπληρώστε τουλάχιστον Τίτλο και Καλλιτέχνη", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Η εκδήλωση αποθηκεύτηκε επιτυχώς!", Toast.LENGTH_SHORT).show();
                // Εδώ μπορείς να αποθηκεύσεις τα δεδομένα
                DBmanager.addEvent(newEvent);


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

}
