package com.example.notedapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class HomeScreen extends AppCompatActivity {
    // UI Components
    private RecyclerView releasesRecyclerView;
    private RecyclerView reviewsRecyclerView;
    private RecyclerView newReleasesRecyclerView;
    private ReleaseAdapter releaseAdapter;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageButton menuButton;

    private FloatingActionButton fab_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        //Initialize koumpi menu
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuButton = findViewById(R.id.menuButton);
        fab_button = findViewById(R.id.fab_new_list);


        //Gia for you section:
        // 1. Initialize RecyclerView from layout
        releasesRecyclerView = findViewById(R.id.releases_recyclerview);

        // 2. Configure layout manager for horizontal scrolling
        // Parameters: context, orientation (HORIZONTAL), reverseLayout (false)
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        releasesRecyclerView.setLayoutManager(layoutManager);

        // 4. Create adapter with our data and set it to RecyclerView
        releaseAdapter = new ReleaseAdapter(this, Arrays.asList(DBmanager.releases));
        releasesRecyclerView.setAdapter(releaseAdapter);

        // Optional: Improve performance if all items have same size
        releasesRecyclerView.setHasFixedSize(true);

        //Gia kritikes section:
        reviewsRecyclerView = findViewById(R.id.reviews_recycler);
        //Configure layout manager for horizontal scrolling inside recycler view & set it
        LinearLayoutManager reviewsLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        reviewsRecyclerView.setLayoutManager(reviewsLayoutManager);

        //Create reviews adapter object with required data and set it to the Recycler View
        ReviewAdapter reviewsAdapter = new ReviewAdapter(Arrays.asList(DBmanager.reviews));
        reviewsRecyclerView.setAdapter(reviewsAdapter);

        PagerSnapHelper snapHelper = new PagerSnapHelper(); //For review snapping while scrolling.
        snapHelper.attachToRecyclerView(reviewsRecyclerView);


        //Gia nea releases section:
        newReleasesRecyclerView = findViewById(R.id.new_release_recycler);
        //COnfigure layout manager inside recycler view and set it to Horizontal
        LinearLayoutManager newReleasesRecyclerLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        newReleasesRecyclerView.setLayoutManager(newReleasesRecyclerLayoutManager);

        //Get release list sorted by year using Streams API
        List<Release> sortedByYear = Arrays.stream(DBmanager.releases)
                .sorted((r1, r2) -> Integer.compare(r2.year, r1.year))
                .collect(Collectors.toList());

        //Make adapter object to populate
        ReleaseAdapter newReleasesAdapter = new ReleaseAdapter(this, sortedByYear);
        newReleasesRecyclerView.setAdapter(newReleasesAdapter);

        // Otan o xrhsths pathsei to bar menu anoigei to side menu
        menuButton.setOnClickListener(v -> {
            drawerLayout.openDrawer(navigationView);
        });

        Button viewEventButton = findViewById(R.id.viewEventsButton);
        viewEventButton.setOnClickListener(v -> {
            Intent eventListIntent = new Intent(HomeScreen.this, EventList.class);
            startActivity(eventListIntent);
        });


    // Otan o xrhsths epileksei kati apo to side menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers(); // kleisimo tou menu

                int id = item.getItemId();
                if (id == R.id.nav_home) {

                } else if (id == R.id.nav_profil) {
                    // Tha phgainei sto profil
                    Intent myProfileIntent = new Intent(HomeScreen.this, ProfileScreen.class);
                    myProfileIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Optional: Clears activity stack
                    startActivity(myProfileIntent);

                } else if (id == R.id.nav_lists) {
                    Intent myListsIntent = new Intent(HomeScreen.this, MyListsScreen.class);
                    myListsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Optional: Clears activity stack
                    startActivity(myListsIntent);
                } else if (id == R.id.nav_create_event) {
                    Intent historyIntent = new Intent(HomeScreen.this, CreateEvent.class);
                    historyIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(historyIntent);

                } else if (id == R.id.nav_history) {
                    Intent historyIntent = new Intent(HomeScreen.this, HistoryActivity.class);
                    historyIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(historyIntent);

                } else if (id == R.id.nav_logout) {
                    Intent homeIntent = new Intent(HomeScreen.this, MainActivity.class);
                    homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Optional: Clears activity stack
                    startActivity(homeIntent);
                }

                return true;
            }
        });

        // Otan patithei to koumpi anazhthsh
        fab_button.setOnClickListener(view -> {
            Intent homeIntent = new Intent(HomeScreen.this, SearchScreen.class);
            startActivity(homeIntent);
        });
    }



}