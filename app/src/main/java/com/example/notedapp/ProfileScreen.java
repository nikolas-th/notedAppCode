package com.example.notedapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class ProfileScreen extends AppCompatActivity {

    private NavigationView navigationView;
    private ImageButton menuButton;
    private DrawerLayout drawerLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_screen);

        //gia to side menu
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuButton = findViewById(R.id.menuButton);

        TextView userNameText = findViewById(R.id.usernameText);
        TextView rankText = findViewById(R.id.rankText);
        Button historyBtn = findViewById(R.id.historyBtn);

        userNameText.setText(DBmanager.getUserById(UserSession.getUserId()).getUsername()); // provolh username tou xrhsth
        rankText.setText(DBmanager.getUserById(UserSession.getUserId()).getRank()); //Provolh rank tou xrhsth

        historyBtn.setOnClickListener(v -> {

            Intent historyIntent = new Intent(ProfileScreen.this, HistoryActivity.class);
            historyIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(historyIntent);

        });

        //Side menu
        navigationView.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawers();
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                Intent homeIntent = new Intent(ProfileScreen.this, HomeScreen.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            } else if (id == R.id.nav_lists) {
                Intent myListsIntent = new Intent(ProfileScreen.this, MyListsScreen.class);
                myListsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myListsIntent);
            } else if (id == R.id.nav_logout) {
                finish();
            } else if (id == R.id.nav_history) {
                Intent historyIntent = new Intent(ProfileScreen.this, HistoryActivity.class);
                historyIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(historyIntent);
            }
            return true;
        });

    }


}