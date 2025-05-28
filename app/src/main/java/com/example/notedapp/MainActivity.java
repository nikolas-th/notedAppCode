package com.example.notedapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText editUsername, editPassword;
    CheckBox checkboxRemember;
    Button btnLogin;
    TextView registerLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        checkboxRemember = findViewById(R.id.checkboxRemember);
        btnLogin = findViewById(R.id.btnLogin);
        registerLink = findViewById(R.id.registerLink);

        btnLogin.setOnClickListener(v -> {
            String username = editUsername.getText().toString().trim();
            String password = editPassword.getText().toString().trim();

            if (!username.isEmpty() && !password.isEmpty()) {

                User foundUser = null;
                for (User user : DBmanager.users) {
                    if (user.getUsername().trim().equalsIgnoreCase(username.trim())) {
                        foundUser = user;
                        break;
                    }
                }

                if (foundUser == null) {
                    Toast.makeText(MainActivity.this, "Δεν βρέθηκε χρήστης με αυτό το username", Toast.LENGTH_SHORT).show();
                } else {
                    if (foundUser.getPassword().equals(password)) {
                        UserSession.setUserId(foundUser.getId());
                        Intent intent = new Intent(MainActivity.this, HomeScreen.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "Λάθος κωδικός πρόσβασης", Toast.LENGTH_SHORT).show();
                    }
                }

            } else {
                Toast.makeText(MainActivity.this, "Συμπλήρωσε όλα τα πεδία!", Toast.LENGTH_SHORT).show();
            }
        });


        registerLink.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
        });
    }

    private User getUserByUsernameAndPassword(String username, String password) {
        for (User user : DBmanager.users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
