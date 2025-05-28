package com.example.notedapp;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class EventList extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private RecyclerView eventRecyclerView;
    private EventAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        drawerLayout = findViewById(R.id.drawer_layout_event_list);
        eventRecyclerView = findViewById(R.id.eventRecyclerView);
        ImageButton menuButton = findViewById(R.id.menuButton);

        menuButton.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            Toast.makeText(this, "Επιλέχθηκε: " + item.getTitle(), Toast.LENGTH_SHORT).show();
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        eventRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Εικονικά δεδομένα (mock)
        List<Event> events = new ArrayList<>();
        events.add(new Event("Jazz Night", "Μαρία Παπαδοπούλου", "Jazz", "Αθήνα", "Πλ. Συντάγματος", "2025-06-15", "21:00"));
        events.add(new Event("Rock Fest", "The Rockers", "Rock", "Θεσσαλονίκη", "Πλ. Αριστοτέλους", "2025-07-01", "20:00"));

        eventAdapter = new EventAdapter(events);
        eventRecyclerView.setAdapter(eventAdapter);
    }
}

