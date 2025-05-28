package com.example.notedapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ProfileSearchScreen extends AppCompatActivity {

    private SearchView searchBar;
    private Switch changetype;
    private ListView releaseList;
    private UserAdapter adapter;
    private int currentUserId;
    private List<User> allUsers;
    private ImageButton menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);

        searchBar = findViewById(R.id.SearchBar);
        releaseList = findViewById(R.id.releaseList);
        menuButton = findViewById(R.id.menuButton);
        changetype = findViewById(R.id.switch4);
        currentUserId = UserSession.getUserId();

        // Αν ήρθε με intent από την άλλη οθόνη, ρύθμισε το switch
        boolean switchState = getIntent().getBooleanExtra("switchState", true);
        changetype.setChecked(switchState);

        // Όταν κάνεις uncheck να επιστρέφει στην άλλη οθόνη
        changetype.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!isChecked) {
                Intent intent = new Intent(ProfileSearchScreen.this, SearchScreen.class);
                intent.putExtra("switchState", false);
                startActivity(intent);
                finish();
            }
        });

        allUsers = DBmanager.getAllUsers();
        adapter = new UserAdapter(this, allUsers, currentUserId);
        releaseList.setAdapter(adapter);

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        releaseList.setOnItemClickListener((parent, view, position, id) -> {
            User clickedUser = adapter.getItem(position);
            if (clickedUser.getId() != currentUserId) {
                Intent intent = new Intent(ProfileSearchScreen.this, OtherUsersActivity.class);
                intent.putExtra("username", clickedUser.getUsername());
                startActivity(intent);
            }
        });

        menuButton.setOnClickListener(v -> {
            // Προαιρετικά: Drawer Layout open
        });
    }
}
