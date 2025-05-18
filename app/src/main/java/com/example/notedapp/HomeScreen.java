package com.example.notedapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;



public class HomeScreen extends AppCompatActivity {
    // UI Components
    private RecyclerView releasesRecyclerView;
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
        fab_button = findViewById(R.id.fab_search);

        // 1. Initialize RecyclerView from layout
        releasesRecyclerView = findViewById(R.id.releases_recyclerview);

        // 2. Configure layout manager for horizontal scrolling
        // Parameters: context, orientation (HORIZONTAL), reverseLayout (false)
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        releasesRecyclerView.setLayoutManager(layoutManager);

        //3. Create some sample Releases
        List<Release> releases = new ArrayList<Release>() {{
            // Using the pattern: title, image, rating
            add(new Release("The Queen Is Dead", R.drawable.smithscover, "5/5", 1986,
                    "The Smiths", "Album", null, "Seminal indie rock album", "Alternative"));

            add(new Release("Paranoid", R.drawable.paranoid, "4.5/5", 1970,
                    "Black Sabbath", "Album", null, "Classic metal album", "Heavy Metal"));

            add(new Release("Meat Is Murder", R.drawable.smithscover2, "4/5", 1985,
                    "The Smiths", "Album", null, "Controversial vegetarian-themed album", "Alternative"));

            add(new Release("Swimming", R.drawable.swimming2, "3.5/5", 2018,
                    "Mac Miller", "Album", null, "Final studio album before passing", "Hip-Hop"));

            add(new Release("Strangeways, Here We Come", R.drawable.smithscover, "4/5", 1987,
                    "The Smiths", "Album", null, "The Smiths' final studio album", "Alternative"));

            add(new Release("Master of Reality", R.drawable.paranoid, "4.5/5", 1971,
                    "Black Sabbath", "Album", null, "Pioneering doom metal sound", "Heavy Metal"));

            add(new Release("Louder Than Bombs", R.drawable.smithscover, "4/5", 1987,
                    "The Smiths", "Compilation", null, "Collection of singles and B-sides", "Alternative"));

            add(new Release("The World Won't Listen", R.drawable.smithscover2, "3.5/5", 1987,
                    "The Smiths", "Compilation", null, "International compilation album", "Alternative"));

            add(new Release("Vol. 4", R.drawable.paranoid, "4/5", 1972,
                    "Black Sabbath", "Album", null, "Experimental metal album", "Heavy Metal"));

            add(new Release("Hatful of Hollow", R.drawable.smithscover2, "5/5", 1984,
                    "The Smiths", "Compilation", null, "Early recordings and BBC sessions", "Alternative"));
        }};

        // 4. Create adapter with our data and set it to RecyclerView
        releaseAdapter = new ReleaseAdapter(releases);
        releasesRecyclerView.setAdapter(releaseAdapter);

        // Optional: Improve performance if all items have same size
        releasesRecyclerView.setHasFixedSize(true);

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