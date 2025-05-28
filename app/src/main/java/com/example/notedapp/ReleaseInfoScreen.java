package com.example.notedapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReleaseInfoScreen extends AppCompatActivity {

    private RecyclerView reviewsRecyclerView;
    private NavigationView navigationView;
    private ImageButton menuButton;
    private DrawerLayout drawerLayout;
    private ActivityResultLauncher<Intent> reviewActivityLauncher;


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
        Button reviewBtn = findViewById(R.id.ReviewButton);

        //gia to side menu
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuButton = findViewById(R.id.menuButton);


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

        reviewActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        // Refresh the reviews
                        refreshReviews();
                    }
                }
        );



        if (currentRelease != null) {
            // Δημιουργία λίστας για φιλτραρισμένες κριτικές
            List<Review> tempList = new ArrayList<>();
            for (Review review : DBmanager.reviews) {
                if (review.getReleaseId() == currentRelease.id) {
                    tempList.add(review);
                }
            }

            // Εύρεση πλήθους και μετατροπή σε πίνακα
            int reviewCount = tempList.size();
            Review[] filteredReviews = tempList.toArray(new Review[reviewCount]);

            // Εμφάνιση πλήθους κριτικών
            ratingTotalCnt.setText("(" + reviewCount + ")");

            // Ρύθμιση RecyclerView
            reviewsRecyclerView = findViewById(R.id.reviews_recycler);
            LinearLayoutManager reviewsLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            reviewsRecyclerView.setLayoutManager(reviewsLayoutManager);

            // Πέρασε τον πίνακα filteredReviews στον adapter
            ReviewAdapter reviewsAdapter = new ReviewAdapter(Arrays.asList(filteredReviews));
            reviewsRecyclerView.setAdapter(reviewsAdapter);

            PagerSnapHelper snapHelper = new PagerSnapHelper();
            snapHelper.attachToRecyclerView(reviewsRecyclerView);

            //Otan o xrhsths pathsei to koympi gia review
            Release finalCurrentRelease = currentRelease;
            reviewBtn.setOnClickListener(v -> {
                Intent reviewScreenIntent = new Intent(ReleaseInfoScreen.this, ReviewScreen.class);
                reviewScreenIntent.putExtra("releaseId", finalCurrentRelease.id);
                reviewActivityLauncher.launch(reviewScreenIntent);
            });

        }



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
                    Intent homeIntent = new Intent(ReleaseInfoScreen.this, HomeScreen.class);
                    homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(homeIntent);
                } else if (id == R.id.nav_profil) {
                    // Τha phgainei sto profil
                } else if (id == R.id.nav_lists) {
                    // Τha phgainei stis listes
                    Intent myListsIntent = new Intent(ReleaseInfoScreen.this, MyListsScreen.class);
                    myListsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Optional: Clears activity stack
                    startActivity(myListsIntent);
                } else if (id == R.id.nav_logout) {
                    Intent homeIntent = new Intent(ReleaseInfoScreen.this, MainActivity.class);
                    homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Optional: Clears activity stack
                    startActivity(homeIntent);
                }
                //phgainei istoriko
                else if (id == R.id.nav_history) {
                    Intent historyIntent = new Intent(ReleaseInfoScreen.this, HistoryActivity.class);
                    historyIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(historyIntent);}

                return true;
            }
        });

    }

    private void refreshReviews() {
        TextView ratingTotalCnt = findViewById(R.id.releaseRevCount);

        Release currentRelease = null;
        String title = getIntent().getStringExtra("title");

        for (Release r : DBmanager.releases) {
            if (r.title.equals(title)) {
                currentRelease = r;
                break;
            }
        }

        if (currentRelease != null) {
            List<Review> tempList = new ArrayList<>();
            for (Review review : DBmanager.reviews) {
                if (review.getReleaseId() == currentRelease.id) {
                    tempList.add(review);
                }
            }

            int reviewCount = tempList.size();
            Review[] filteredReviews = tempList.toArray(new Review[reviewCount]);

            ratingTotalCnt.setText("(" + reviewCount + ")");

            ReviewAdapter reviewsAdapter = new ReviewAdapter(Arrays.asList(filteredReviews));
            reviewsRecyclerView.setAdapter(reviewsAdapter);
        }
    }


}