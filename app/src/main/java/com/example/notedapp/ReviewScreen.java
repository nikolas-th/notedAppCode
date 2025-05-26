package com.example.notedapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ReviewScreen extends AppCompatActivity {

    private NavigationView navigationView;
    private ImageButton menuButton;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_review_screen);

        RatingBar ratingBar = findViewById(R.id.ratingBarRev);
        TextView ratingText = findViewById(R.id.ratingText);
        EditText userInput = findViewById(R.id.userInputText);
        Button submitBtn = findViewById(R.id.publishBtn);

        //gia to side menu
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuButton = findViewById(R.id.menuButton);

        int releaseId = getIntent().getIntExtra("releaseId", -1);

        //Symplhrwma tou ratingText me vash ta asteria pou eisagei o xrhsths
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating == (int) rating) {//An o xrhsths eisagei "Oloklhra asteria"
                    ratingText.setText("(" + (int) rating + "/5)");
                }else{
                    ratingText.setText("(" + String.format("%.1f", rating) + "/5)");
                }
            }
        });

        // Otan o xrhsths pathsei to bar menu anoigei to side menu
        menuButton.setOnClickListener(v -> {
            drawerLayout.openDrawer(navigationView);
        });

        // Otan o xrhsths epileksei kati apo to side menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers(); // kleisimo tou menu

                int id = item.getItemId();
                if (id == R.id.nav_home) {

                } else if (id == R.id.nav_profil) {
                    // Τha phgainei sto profil
                } else if (id == R.id.nav_lists) {
                    // Τha phgainei stis listes
                    Intent myListsIntent = new Intent(ReviewScreen.this, MyListsScreen.class);
                    myListsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Optional: Clears activity stack
                    startActivity(myListsIntent);
                } else if (id == R.id.nav_logout) {
                    Intent homeIntent = new Intent(ReviewScreen.this, MainActivity.class);
                    homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Optional: Clears activity stack
                    startActivity(homeIntent);
                }
                return true;
            }
        });

        //Otan o xrhsths ksepernaei tous 100 xarakthres ( den plhroi orous xrhshs)
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = userInput.getText().toString().trim();

                if (text.length() > 100) {
                    Toast.makeText(ReviewScreen.this, "Το κείμενό σου ξεπερνά τους 100 χαρακτήρες!", Toast.LENGTH_LONG).show(); //emfanish mhnymatos
                } else {
                    // Συνέχισε με την αποθήκευση ή δημοσίευση
                    float ratingValue = ratingBar.getRating();
                    String formattedRating = (ratingValue % 1 == 0)
                            ? String.format("%.0f/5", ratingValue)
                            : String.format("%.1f/5", ratingValue);

                    String today = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());


                    // Δημιουργία νέου Review και προσθήκη
                    Review newReview = new Review( UserSession.getUserId(), text, formattedRating, today, releaseId);
                    DBmanager.addReview(newReview);
                    Toast.makeText(ReviewScreen.this, "Η κριτική σου αποθηκεύτηκε!", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish(); // Θα ενεργοποιήσει το onActivityResult της ReleaseInfo
                }
            }
        });

    }
}