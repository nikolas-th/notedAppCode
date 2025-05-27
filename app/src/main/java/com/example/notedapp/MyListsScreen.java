package com.example.notedapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MyListsScreen extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageButton menuButton;
    private RecyclerView listRecyclerView;
    private List<ReleaseList> usersLists;
    private ReleaseListAdapter releaseListAdapter;
    private ActivityResultLauncher<Intent> editListLauncher;
    private FloatingActionButton newListButton;
    private ActivityResultLauncher<Intent> newListlauncher;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lists_screen);
        //Initialize koumpi menu
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuButton = findViewById(R.id.menuButton);

        //Get user's lists.
        usersLists = DBmanager.getListsByUser(DBmanager.getUserById(UserSession.getUserId()).getUsername());

        // Initialize the launcher
        editListLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        // Refresh your data and update the adapter
                        // Simple toast with default duration (SHORT)
                        Toast.makeText(this, "Η λίστα αποθηκεύτηκε!", Toast.LENGTH_SHORT).show();
                        usersLists = DBmanager.getListsByUser(DBmanager.getUserById(UserSession.getUserId()).getUsername());
                        releaseListAdapter.notifyDataSetChanged();
                    }
                });

        // Initialize the new list launcher
        newListlauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        // Refresh your data and update the adapter
                        // Simple toast with default duration (SHORT)
                        Toast.makeText(this, "Η λίστα δημιουργήθηκε!", Toast.LENGTH_SHORT).show();
                        usersLists = DBmanager.getListsByUser(DBmanager.getUserById(UserSession.getUserId()).getUsername());
                        releaseListAdapter.updateData(usersLists);
                        releaseListAdapter.notifyDataSetChanged();
                    }
                });



        //If user has lists, hide message to add more and display them.
        if (!usersLists.isEmpty()){
            findViewById(R.id.no_lists_message).setVisibility(View.INVISIBLE);

            //Display lists in RecyclerView
            listRecyclerView = findViewById(R.id.release_list_recycler_view);
            //Set up LinearLayoutManager for Recycler
            LinearLayoutManager listRecyclerLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            listRecyclerView.setLayoutManager(listRecyclerLayoutManager);

            //Set up ReleaseListAdapter
            releaseListAdapter = new ReleaseListAdapter(this, usersLists, editListLauncher);
            listRecyclerView.setAdapter(releaseListAdapter);
        }

        //On click behavior for the new list button.
        newListButton = findViewById(R.id.fab_new_list);
        newListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Redirect to new list activity.
                Intent myListsIntent = new Intent(MyListsScreen.this, CreateListScreen.class);
                newListlauncher.launch(myListsIntent);
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
                    Intent homeIntent = new Intent(MyListsScreen.this, HomeScreen.class);
                    homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Optional: Clears activity stack
                    startActivity(homeIntent);
                } else if (id == R.id.nav_profil) {
                    // Τha phgainei sto profil
                } else if (id == R.id.nav_lists) {
                    //tipota
                }else if (id == R.id.nav_history) {
                    Intent historyIntent = new Intent(MyListsScreen.this, HistoryActivity.class);
                    historyIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(historyIntent);
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