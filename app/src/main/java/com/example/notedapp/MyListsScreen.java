package com.example.notedapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MyListsScreen extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageButton menuButton;
    private RecyclerView listRecyclerView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lists_screen);
        //Initialize koumpi menu
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuButton = findViewById(R.id.menuButton);

        //Get user's lists.
        //TODO: get user's name from UserSession class.
        List<ReleaseList> usersLists = DBmanager.getListsByUser("johndoe");

        //If user has lists, hide message to add more and display them.
        if (!usersLists.isEmpty()){
            findViewById(R.id.no_lists_message).setVisibility(View.INVISIBLE);

            //Display lists in RecyclerView
            listRecyclerView = findViewById(R.id.release_list_recycler_view);
            //Set up LinearLayoutManager for Recycler
            LinearLayoutManager listRecyclerLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            listRecyclerView.setLayoutManager(listRecyclerLayoutManager);

            //Set up ReleaseListAdapter
            ReleaseListAdapter releaseListAdapter = new ReleaseListAdapter(this, usersLists);
            listRecyclerView.setAdapter(releaseListAdapter);
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
                    Intent homeIntent = new Intent(MyListsScreen.this, HomeScreen.class);
                    homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Optional: Clears activity stack
                    startActivity(homeIntent);
                } else if (id == R.id.nav_profil) {
                    // Τha phgainei sto profil
                } else if (id == R.id.nav_lists) {
                    //tipota
                } else if (id == R.id.nav_logout) {
                    Intent logoutIntent = new Intent(MyListsScreen.this, MainActivity.class);
                    logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Optional: Clears activity stack
                    startActivity(logoutIntent);
                }
                return true;
            }
        });

    }
}