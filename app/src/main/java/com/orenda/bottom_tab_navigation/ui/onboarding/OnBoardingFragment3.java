package com.orenda.bottom_tab_navigation.ui.onboarding;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.orenda.bottom_tab_navigation.MainActivity;
import com.orenda.bottom_tab_navigation.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OnBoardingFragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OnBoardingFragment3 extends BaseOnBoardingScreen {


    public OnBoardingFragment3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_on_boarding3, container, false);

        Button backBtn = view.findViewById(R.id.backButton);
        backBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack();
        });

        Button finishBtn = view.findViewById(R.id.nextButton);
        finishBtn.setOnClickListener(v -> {
            // Complete onboarding
            MainActivity activity = (MainActivity) getActivity();
            if (activity != null) {
                activity.completeOnboarding();
            }
        });
        return view;
    }
}