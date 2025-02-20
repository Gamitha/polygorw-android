package com.orenda.polygrow.ui.onboarding;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.orenda.polygrow.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OnBoardingFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OnBoardingFragment2 extends BaseOnBoardingScreen {

    public OnBoardingFragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_on_boarding2, container, false);

        Button backBtn = view.findViewById(R.id.backButton);
        backBtn.setOnClickListener(v -> {
            // Navigate to the next fragment
            Navigation.findNavController(v).popBackStack();
        });

        Button nextButton = view.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> {
            // Navigate to the next fragment
            Navigation.findNavController(v).navigate(R.id.action_onBoardingFragment2_to_onBoardingFragment3);
        });

        Button skipButton = view.findViewById(R.id.skipButton);
        skipButton.setOnClickListener(v -> {
            this.skipOnBoarding(R.id.action_onBoardingFragment2_to_onBoardingFragment3);
        });
        return view;
    }
}