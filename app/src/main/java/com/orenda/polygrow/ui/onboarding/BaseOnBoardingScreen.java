package com.orenda.polygrow.ui.onboarding;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.orenda.polygrow.R;

public abstract class BaseOnBoardingScreen extends Fragment {

    protected View view;

    protected void skipOnBoarding(int routeId) {
        // Save the selected language to shared preferences

        // Navigate to the next fragment
         Navigation.findNavController(view).navigate(routeId);
    }
}
