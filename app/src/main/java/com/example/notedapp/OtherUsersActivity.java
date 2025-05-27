package com.example.notedapp;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class OtherUsersActivity extends AppCompatActivity {
    private MaterialButton follow;
    private ImageView profilepicture;
    private TextView username;
    private ImageButton menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // ΠΡΕΠΕΙ να είναι πρώτο
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_other_users); // ΠΡΕΠΕΙ να είναι πριν από τα findViewById

        // Αρχικοποίηση views ΜΕΤΑ το setContentView
        follow = findViewById(R.id.followbutton);
        profilepicture = findViewById(R.id.imageView4);
        username = findViewById(R.id.editTextText);
        menuButton = findViewById(R.id.menuButton);

        // Εφαρμογή padding στα system bars (π.χ. status bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Πάρε το username που στάλθηκε από την αναζήτηση
        String user = getIntent().getStringExtra("username");
        if (user != null) {
            username.setText(user); // Εμφάνισέ το στο TextView
        } else {
            username.setText("Άγνωστος χρήστης");
        }

        // === FOLLOW LOGIC ===
        final boolean[] isFollowing = {false};

        follow.setOnClickListener(v -> {
            if (isFollowing[0]) {
                follow.setText("Ακολουθήστε");
                follow.setBackgroundTintList(ColorStateList.valueOf(
                        ContextCompat.getColor(this, com.google.android.material.R.color.design_default_color_primary)
                ));
                isFollowing[0] = false;
            } else {
                follow.setText("Ακολουθείτε!");
                //follow.setBackgroundTintList(ColorStateList.valueOf(
                  //      ContextCompat.getColor(this, R.color.teal_700) // Ή οποιοδήποτε χρώμα σου αρέσει
                //)
                //);
                isFollowing[0] = true;
            }
        });
    }
}
