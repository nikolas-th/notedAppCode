package com.example.notedapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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





public class SearchScreen extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageButton menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);

        ListView dummyList = findViewById(R.id.dummyList); //H lista me tis prosfates anazhthseis
        SearchView searchView = findViewById(R.id.SearchBar); //h bara anazhtishs
        TextView resSearch = findViewById(R.id.resSearch); //label "Πρόσφατες αναζητήσεις"


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuButton = findViewById(R.id.menuButton);

        

        Release[] items = new Release[] {
            new Release("Τίτλος 1", R.drawable.smithscover, "(2.5/5)"),
            new Release("Τίτλος 2", R.drawable.paranoid, "(2/5)"),
            new Release("Τίτλος 3", R.drawable.smithscover2, "(3/5)"),
            new Release("Τίτλος 4", R.drawable.swimming2, "(4.5/5)"),
            new Release("Τίτλος 5", R.drawable.smithscover, "(5/5)"),
            new Release("Τίτλος 6", R.drawable.paranoid, "(1.4/5)"),
            new Release("Τίτλος 7", R.drawable.smithscover, "(2.8/5)"),
            new Release("Τίτλος 8", R.drawable.smithscover2, "(3/7)"),
            new Release("Τίτλος 9", R.drawable.paranoid, "(3.9/5)"),
            new Release("Τίτλος 10", R.drawable.smithscover2, "(4/5)")
        };
        

        // Initialize the 3 separate arrays
        String[] titles = new String[items.length];
        int[] imageIds = new int[items.length];
        String[] ratings = new String[items.length];

        // Populate them by looping through the `items` array
        for (int i = 0; i < items.length; i++) {
            titles[i] = items[i].title;
            imageIds[i] = items[i].imageId;
            ratings[i] = items[i].rating;
        }

        CustomListAdapter adapter = new CustomListAdapter(this, titles, imageIds, ratings, resSearch);
        dummyList.setAdapter(adapter);

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
                    //Tha phgainei sthn arxikh
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


