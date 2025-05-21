package com.example.notedapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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

import com.google.android.material.navigation.NavigationView;

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

        //gia to side menu
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuButton = findViewById(R.id.menuButton);


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
                } else if (id == R.id.nav_logout) {
                    Intent homeIntent = new Intent(ReviewScreen.this, MainActivity.class);
                    homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Optional: Clears activity stack
                    startActivity(homeIntent);
                }
                return true;
            }
        });

    }
}