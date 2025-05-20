package com.example.notedapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;


public class SearchScreen extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageButton menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);

        ListView releaseList = findViewById(R.id.releaseList); //H lista me tis prosfates anazhthseis
        SearchView searchView = findViewById(R.id.SearchBar); //h bara anazhtishs
        TextView resSearch = findViewById(R.id.resSearch); //label "Πρόσφατες αναζητήσεις"


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuButton = findViewById(R.id.menuButton);


       //pername ta dedomena apo ton pinaka releases ths bashs DBmanager
        Release[] items = DBmanager.releases;


        // Initialize the 6 separate arrays
        String[] titles = new String[items.length];
        int[] imageIds = new int[items.length];
        String[] ratings = new String[items.length];
        String[] artists = new String[items.length];
        int[] year = new int[items.length];
        String[] releaseType = new String[items.length];
        String[] description = new String[items.length];

        // Populate them by looping through the `items` array
        for (int i = 0; i < items.length; i++) {
            titles[i] = items[i].title;
            imageIds[i] = items[i].imageId;
            ratings[i] = items[i].rating;
            artists[i] = items[i].artist;
            year[i] = items[i].year;
            releaseType[i] = items[i].releaseType;
            description[i] = items[i].description;

        }

        CustomListAdapter adapter = new CustomListAdapter(this, titles, imageIds, ratings, artists, year, releaseType, resSearch);
        releaseList.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                //return true;
                return false;
            }
        });

        //Metafora dedomenwn sthn othonh Release otan o xrhsths epilegei mia kykloforia
        releaseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Πάρε τα δεδομένα που θες
                String selectedTitle = titles[position];
                int selectedImageId = imageIds[position];
                String selectedArtist = artists[position];
                int selectedYear = year[position];
                String selectedReleaseType = releaseType[position];
                String selectedRatings = ratings[position];
                String selectedDescription = description[position];


                // Φτιάξε το Intent
                Intent intent = new Intent(SearchScreen.this, ReleaseInfoScreen.class);
                intent.putExtra("title", selectedTitle);
                intent.putExtra("imageId", selectedImageId);
                intent.putExtra("artist", selectedArtist);
                intent.putExtra("year", selectedYear);
                intent.putExtra("type", selectedReleaseType);
                intent.putExtra("rating", selectedRatings);
                intent.putExtra("description", selectedDescription);

                // Ξεκίνα το άλλο activity
                startActivity(intent);
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
                    Intent homeIntent = new Intent(SearchScreen.this, HomeScreen.class);
                    homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Optional: Clears activity stack
                    startActivity(homeIntent);
                } else if (id == R.id.nav_profil) {
                    // Τha phgainei sto profil
                } else if (id == R.id.nav_lists) {
                        // Τha phgainei stis listes
                } else if (id == R.id.nav_logout) {
                    finish(); // eksodos
                }
                return true;
            }
        });
    }

}


