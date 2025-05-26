package com.example.notedapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.navigation.NavigationView;

public class OtherUsersActivity extends AppCompatActivity {
    private Button followbutton;
    private ImageView profilepicture;
    private TextView username;
    private NavigationView navigationView;
    private ImageButton menuButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        profilepicture = findViewById(R.id.imageView4);
        username = findViewById(R.id.editTextText);
        //navigationView = findViewById(R.id.fab_search);
        menuButton = findViewById(R.id.menuButton);

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_other_users);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}