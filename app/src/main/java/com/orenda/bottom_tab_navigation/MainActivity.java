package com.orenda.bottom_tab_navigation;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.orenda.bottom_tab_navigation.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }else {
            navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        }
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        SharedPreferences sharedPrefs = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        boolean isOnboarded = sharedPrefs.getBoolean("isOnboarded", false);

        if (!isOnboarded) {
            // Hide BottomNavigationView during onboarding
            navView.setVisibility(View.GONE);
            Objects.requireNonNull(getSupportActionBar()).hide(); // Hide the ActionBar
            navController.setGraph(R.navigation.onboarding_navigation); // Set the onboarding graph
        } else {
            navController.setGraph(R.navigation.mobile_navigation); // Set the main graph
        }
    }

    public void completeOnboarding() {
        SharedPreferences sharedPrefs = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean("isOnboarded", true);
        editor.apply();

        // Show BottomNavigationView after onboarding
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setVisibility(View.VISIBLE);

        navController.setGraph(R.navigation.mobile_navigation); // Switch to the main graph
    }

}