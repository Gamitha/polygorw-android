package com.orenda.bottom_tab_navigation.ui.onboarding;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.orenda.bottom_tab_navigation.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OnBoardingFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OnBoardingFragment1 extends BaseOnBoardingScreen {

    public OnBoardingFragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         this.view = inflater.inflate(R.layout.fragment_on_boarding1, container, false);

        Button nextButton = view.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> {
            // Navigate to the next fragment
            Navigation.findNavController(v).navigate(R.id.action_onBoardingFragment1_to_onBoardingFragment2);
        });

        Button skipButton = view.findViewById(R.id.skipButton);
        skipButton.setOnClickListener(v -> {
            this.skipOnBoarding(R.id.action_onBoardingFragment1_to_onBoardingFragment3);
        });
        return view;
    }
}