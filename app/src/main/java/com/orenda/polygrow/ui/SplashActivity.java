package com.orenda.polygrow.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.orenda.polygrow.MainActivity;
import com.orenda.polygrow.R;

import java.util.Optional;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        Optional.ofNullable(getSupportActionBar()).ifPresent(ActionBar::hide);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        new Handler().postDelayed(this::checkOnboardingAndNavigate, 2000);
    }

    private void checkOnboardingAndNavigate() {
        SharedPreferences sharedPrefs = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        boolean isOnboarded = sharedPrefs.getBoolean("isOnboarded", false);

        Intent intent;
        if (!isOnboarded) {
            intent = new Intent(this, MainActivity.class); // Or your onboarding activity if separate.
            intent.putExtra("showOnboarding", true); // Signal to MainActivity to show onboarding
        } else {
            intent = new Intent(this, MainActivity.class);
        }
        startActivity(intent);
        finish(); // Prevent going back to the splash screen
    }
}