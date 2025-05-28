package com.example.notedapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameInput, emailInput, passwordInput, confirmPasswordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Σύνδεση με τα πεδία του XML
        usernameInput = findViewById(R.id.editUsername);
        emailInput = findViewById(R.id.editEmail);
        passwordInput = findViewById(R.id.editPassword);
        confirmPasswordInput = findViewById(R.id.editConfirmPassword);

        findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        findViewById(R.id.loginLink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void registerUser() {
        String username = usernameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        String confirmPassword = confirmPasswordInput.getText().toString().trim();

        // Έλεγχος για κενά πεδία
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Συμπλήρωσε όλα τα πεδία.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Έλεγχος ταύτισης κωδικών
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Οι κωδικοί δεν ταιριάζουν.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Έλεγχος ύπαρξης χρήστη
        if (DBmanager.usernameExists(username)) {
            Toast.makeText(this, "Το όνομα χρήστη υπάρχει ήδη.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Δημιουργία χρήστη
        User newUser = new User(DBmanager.users.length + 1, "User", "ok", username, password, 0);
        DBmanager.addUser(newUser);
        Toast.makeText(this, "Επιτυχής δημιουργία χρήστη!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
