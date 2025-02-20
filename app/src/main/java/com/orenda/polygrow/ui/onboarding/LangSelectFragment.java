package com.orenda.polygrow.ui.onboarding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.orenda.polygrow.R;

public class LangSelectFragment extends Fragment {


    public LangSelectFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lang_select, container, false);

        Button btnEnglish = view.findViewById(R.id.select_eng);
        btnEnglish.setOnClickListener(v -> {
            // Save the selected language to shared preferences

            // Navigate to the next fragment
            Navigation.findNavController(view).navigate(R.id.action_langSelectFragment_to_onBoardingFragment1);
        });

        Button btnSinhala = view.findViewById(R.id.select_sin);
        btnSinhala.setOnClickListener(v -> {
            // Save the selected language to shared preferences

            // Navigate to the next fragment
            Navigation.findNavController(view).navigate(R.id.action_langSelectFragment_to_onBoardingFragment1);
        });
        return view;
    }
}