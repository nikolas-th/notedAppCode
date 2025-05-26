package com.example.notedapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.Arrays;

public class HistoryActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private RecyclerView historyRecyclerView;
    private ImageButton menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Σύνδεση με τα views
        drawerLayout = findViewById(R.id.history_drawer_layout);
        navigationView = findViewById(R.id.history_navigation_view);
        historyRecyclerView = findViewById(R.id.history_recycler);
        menuButton = findViewById(R.id.menuButton);

        // Άνοιγμα του drawer όταν πατιέται το menu button
        menuButton.setOnClickListener(v -> {
            drawerLayout.openDrawer(navigationView);
        });

        // Ρύθμιση RecyclerView για κάθετη προβολή κριτικών
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        historyRecyclerView.setLayoutManager(layoutManager);

        // Χρήση του υπάρχοντος ReviewAdapter με τις κριτικές
        ReviewAdapter adapter = new ReviewAdapter(Arrays.asList(DBmanager.reviews));
        historyRecyclerView.setAdapter(adapter);

        // Πλοήγηση από το side menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers(); // Κλείσιμο του menu

                int id = item.getItemId();

                if (id == R.id.nav_home) {
                    Intent homeIntent = new Intent(HistoryActivity.this, HomeScreen.class);
                    homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(homeIntent);

                } else if (id == R.id.nav_profil) {
                    //Θα πηγαίνει
                } else if (id == R.id.nav_lists) {
                    Intent myListsIntent = new Intent(HistoryActivity.this, MyListsScreen.class);
                    myListsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(myListsIntent);

                } else if (id == R.id.nav_history) {
                    // Ήδη βρισκόμαστε εδώ — δε χρειάζεται κάτι

                } else if (id == R.id.nav_logout) {
                    Intent logoutIntent = new Intent(HistoryActivity.this, MainActivity.class);
                    logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(logoutIntent);
                }

                return true;
            }
        });
    }
}
