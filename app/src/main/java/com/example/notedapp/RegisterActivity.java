package com.example.notedapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usernameInput = findViewById(R.id.editUsername);
                String username = usernameInput.getText().toString();

                EditText passwordInput = findViewById(R.id.editPassword);
                String password = usernameInput.getText().toString();

                User newUser = new User(DBmanager.users.length + 1, "User", "ok", username, password, 0);
                DBmanager.addUser(newUser);
                Toast.makeText(RegisterActivity.this, "Επιτυχής δημιουργία χρήστη!",Toast.LENGTH_SHORT).show();
                finish();

            }
        });

        findViewById(R.id.loginLink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Επιστροφή στο login
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
