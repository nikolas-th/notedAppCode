package com.example.notedapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class SearchScreen extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageButton menuButton;
    Switch changetype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);

        ListView releaseList = findViewById(R.id.releaseList);
        SearchView searchView = findViewById(R.id.SearchBar);
        TextView resSearch = findViewById(R.id.resSearch);
        boolean isSelectionMode = getIntent().getBooleanExtra("selectionMode", false);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuButton = findViewById(R.id.menuButton);
        changetype = findViewById(R.id.switch4);

        // Αν ο χρήστης ήρθε από την άλλη οθόνη, κράτα τη θέση του switch
        boolean switchState = getIntent().getBooleanExtra("switchState", false);
        changetype.setChecked(switchState);

        // Εναλλαγή οθόνης όταν αλλάζει η θέση του switch
        changetype.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Intent intent = new Intent(SearchScreen.this, ProfileSearchScreen.class);
                intent.putExtra("switchState", true);
                startActivity(intent);
                finish();
            }
        });

        // Φόρτωση των releases και του adapter (όπως πριν)
        Release[] items = DBmanager.releases;
        String[] titles = new String[items.length];
        int[] imageIds = new int[items.length];
        String[] ratings = new String[items.length];
        String[] artists = new String[items.length];
        int[] year = new int[items.length];
        String[] releaseType = new String[items.length];
        String[] description = new String[items.length];
        int[] releaseIds = new int[items.length];

        for (int i = 0; i < items.length; i++) {
            titles[i] = items[i].title;
            imageIds[i] = items[i].imageId;
            ratings[i] = items[i].rating;
            artists[i] = items[i].artist;
            year[i] = items[i].year;
            releaseType[i] = items[i].releaseType;
            description[i] = items[i].description;
            releaseIds[i] = items[i].id;
        }

        CustomListAdapter adapter = new CustomListAdapter(this, titles, imageIds, ratings, artists, year, releaseType, releaseIds, resSearch);
        releaseList.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                int currentUserId = UserSession.getUserId();
                for (User u : DBmanager.users) {
                    if (u.getUsername().toLowerCase().contains(query.trim().toLowerCase()) && u.getId() != currentUserId) {
                        Intent intent = new Intent(SearchScreen.this, OtherUsersActivity.class);
                        intent.putExtra("username", u.getUsername());
                        startActivity(intent);
                        return true;
                    }
                }
                adapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        releaseList.setOnItemClickListener((parent, view, position, id) -> {
            if (isSelectionMode) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("selectedReleaseId", adapter.getReleaseId(position));
                setResult(RESULT_OK, resultIntent);
                finish();
            } else {
                Release selectedRelease = DBmanager.getReleaseById(adapter.getReleaseId(position));
                Intent intent = new Intent(SearchScreen.this, ReleaseInfoScreen.class);
                intent.putExtra("title", selectedRelease.title);
                intent.putExtra("imageId", selectedRelease.imageId);
                intent.putExtra("artist", selectedRelease.artist);
                intent.putExtra("year", selectedRelease.year);
                intent.putExtra("type", selectedRelease.releaseType);
                intent.putExtra("rating", selectedRelease.rating);
                intent.putExtra("description", selectedRelease.description);
                startActivity(intent);
            }
        });

        menuButton.setOnClickListener(v -> drawerLayout.openDrawer(navigationView));

        navigationView.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawers();
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                Intent homeIntent = new Intent(SearchScreen.this, HomeScreen.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            } else if (id == R.id.nav_lists) {
                Intent myListsIntent = new Intent(SearchScreen.this, MyListsScreen.class);
                myListsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myListsIntent);
            } else if (id == R.id.nav_logout) {
                finish();
            } else if (id == R.id.nav_history) {
                Intent historyIntent = new Intent(SearchScreen.this, HistoryActivity.class);
                historyIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(historyIntent);
            }
            return true;
        });
    }
}
