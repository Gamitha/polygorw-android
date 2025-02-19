package com.orenda.bottom_tab_navigation.ui.onboarding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.orenda.bottom_tab_navigation.R;

public abstract class BaseOnBoardingScreen extends Fragment {

    protected View view;

    protected void skipOnBoarding(int routeId) {
        // Save the selected language to shared preferences

        // Navigate to the next fragment
         Navigation.findNavController(view).navigate(routeId);
    }
}
