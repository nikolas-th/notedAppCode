package com.example.notedapp;

import static com.example.notedapp.R.id.button;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Σύνδεση UI στοιχείων με τον κώδικα
        Button myButton = findViewById(R.id.button);
        TextView myTextView = findViewById(R.id.loginLabel);
        EditText usernameField = findViewById(R.id.usernameField);
        EditText passwordField = findViewById(R.id.passwordField);

        // Όταν πατιέται το κουμπί
        myButton.setOnClickListener(v -> {
            String username = usernameField.getText().toString().trim(); //trim gia na mh pernei san xarakthra to keno " "
            String password = passwordField.getText().toString().trim();

            if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password)) {
                usernameField.setError("Συμπλήρωσε το username σας");
                passwordField.setError("Συμπληρώστε τον κωδικό σας");

            } else if (TextUtils.isEmpty(username)) {
                usernameField.setError("Συμπληρώστε το username σας");

            } else if (TextUtils.isEmpty(password)) {
                passwordField.setError("Συμπληρώστε τον κωδικό σας");

            } else {
                // Προαιρετικά: κάνε login, ή άλλη ενέργεια
                Toast.makeText(MainActivity.this, "Login επιτυχές!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, HomeScreen.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }
}