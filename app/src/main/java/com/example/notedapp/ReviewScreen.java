package com.example.notedapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.airbnb.lottie.LottieAnimationView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ReviewScreen extends AppCompatActivity {

    private NavigationView navigationView;
    private ImageButton menuButton;
    private DrawerLayout drawerLayout;

    private LottieAnimationView confettiAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_review_screen);

        RatingBar ratingBar = findViewById(R.id.ratingBarRev);
        TextView ratingText = findViewById(R.id.ratingText);
        EditText userInput = findViewById(R.id.userInputText);
        Button submitBtn = findViewById(R.id.publishBtn);

        confettiAnimation = findViewById(R.id.confettiAnimation); // ✅ Σύνδεση με το layout


        //gia to side menu
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuButton = findViewById(R.id.menuButton);

        int releaseId = getIntent().getIntExtra("releaseId", -1);

        //Symplhrwma tou ratingText me vash ta asteria pou eisagei o xrhsths
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating == (int) rating) {//An o xrhsths eisagei "Oloklhra asteria"
                    ratingText.setText("(" + (int) rating + "/5)");
                }else{
                    ratingText.setText("(" + String.format("%.1f", rating) + "/5)");
                }
            }
        });

        // Otan o xrhsths pathsei to bar menu anoigei to side menu
        menuButton.setOnClickListener(v -> {
            drawerLayout.openDrawer(navigationView);
        });

        // Otan o xrhsths epileksei kati apo to side menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers(); // kleisimo tou menu

                int id = item.getItemId();
                if (id == R.id.nav_home) {

                } else if (id == R.id.nav_profil) {
                    // Τha phgainei sto profil
                } else if (id == R.id.nav_lists) {
                    // Τha phgainei stis listes
                    Intent myListsIntent = new Intent(ReviewScreen.this, MyListsScreen.class);
                    myListsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Optional: Clears activity stack
                    startActivity(myListsIntent);
                } else if (id == R.id.nav_logout) {
                    Intent homeIntent = new Intent(ReviewScreen.this, MainActivity.class);
                    homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Optional: Clears activity stack
                    startActivity(homeIntent);
                }
                else if (id == R.id.nav_history) {
                    Intent historyIntent = new Intent(ReviewScreen.this, HistoryActivity.class);
                    historyIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(historyIntent);}

                return true;
            }
        });

        //Otan o xrhsths ksepernaei tous 100 xarakthres ( den plhroi orous xrhshs)
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = userInput.getText().toString().trim();
                int revCounter =  DBmanager.getUserById(UserSession.getUserId()).getReviewCounter() ;


                if (text.length() > 100) { // periorismos stis 100 lekseis
                    Toast.makeText(ReviewScreen.this, "Το κείμενό σου ξεπερνά τους 100 χαρακτήρες!", Toast.LENGTH_LONG).show(); //emfanish mhnymatos
                } else {
                    // Συνέχισε με την αποθήκευση ή δημοσίευση
                    float ratingValue = ratingBar.getRating();
                    String formattedRating = (ratingValue % 1 == 0)
                            ? String.format("%.0f/5", ratingValue)
                            : String.format("%.1f/5", ratingValue);

                    String today = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

                    // Δημιουργία νέου Review και προσθήκη
                    Review newReview = new Review( UserSession.getUserId(), text, formattedRating, today, releaseId);
                    DBmanager.addReview(newReview);  // prosthkh ston pinaka me ta reviews
                    DBmanager.getUserById(UserSession.getUserId()).setReviewCounter(revCounter) ; //prosthkh ananeomenou review counter ston xrhsth
                    Toast.makeText(ReviewScreen.this, "Η κριτική σου αποθηκεύτηκε!", Toast.LENGTH_SHORT).show();
                    revCounter ++;
                    if(revCounter >= 5){ // an o xrhsths exei perissoteres apo 5 kritikes
                        //kalese edw thn synarthsh
                        playConfettiThenShowDialog();
                    }
                    else{
                        setResult(RESULT_OK);
                        finish();
                    }

                }
            }
        });

    }
    private void playConfettiThenShowDialog() {
        confettiAnimation.setVisibility(View.VISIBLE);
        confettiAnimation.playAnimation();

        new Handler().postDelayed(() -> {
            confettiAnimation.cancelAnimation();
            confettiAnimation.setVisibility(View.GONE);
            showRankEarnedDialog(ReviewScreen.this, "Gold");
        }, 1000);
    }

    private void showRankEarnedDialog(Context context, final String rankName) {
        new AlertDialog.Builder(context)
                .setTitle("Συγχαρητήρια!")
                .setMessage("Υποβάλατε 100 κριτικές και κερδίσατε τον τίτλο " + rankName + "!")
                .setPositiveButton("OK", (dialog, which) -> {
                    dialog.dismiss();
                    showRankVisibilityDialog(context);

                })
                .show();
    }

    private void showRankVisibilityDialog(Context context) {
        new AlertDialog.Builder(context)
                .setTitle("Επιλογή")
                .setMessage("Θέλετε να καταστήσετε το rank σας εμφανές στο προφίλ σας;")
                .setPositiveButton("Ναι", (dialog, which) -> {
                    dialog.dismiss();
                    // TODO: Κάνε κάτι με την επιλογή "Ναι"
                    endReviewActivity(context);
                })
                .setNegativeButton("Όχι", (dialog, which) -> {
                    dialog.dismiss();
                    // TODO: Κάνε κάτι με την επιλογή "Όχι"
                    endReviewActivity(context);
                })
                .show();
    }

    private void endReviewActivity(Context context) { //Synarthsh gia na termathsh to activity
        if (context instanceof Activity) {
            ((Activity) context).setResult(RESULT_OK);
            ((Activity) context).finish();
        }
    }

}