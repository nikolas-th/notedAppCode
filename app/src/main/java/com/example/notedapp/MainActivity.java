package com.example.notedapp;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
            String username = editUsername.getText().toString();
            String password = editPassword.getText().toString();
            boolean remember = checkboxRemember.isChecked();

            if (!username.isEmpty() && !password.isEmpty()) {
                // Αντί για Toast, ανοίγουμε το MainActivity
                //Check an einai swsta
                //login
                UserSession.setUserId(DBmanager.getUserIdByUsername(username));
                Intent intent = new Intent(MainActivity.this, HomeScreen.class);
                startActivity(intent);
                finish(); // Προαιρετικό: κλείνει το LoginActivity ώστε να μην πάει πίσω με το back button
            } else {
                Toast.makeText(this, "Συμπλήρωσε όλα τα πεδία!", Toast.LENGTH_SHORT).show();
            }
        });


        TextView registerLink = findViewById(R.id.registerLink);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

    }
}