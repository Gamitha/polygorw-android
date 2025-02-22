package com.orenda.polygrow.ui.onboarding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.orenda.polygrow.MainActivity;
import com.orenda.polygrow.R;

public class LangSelectFragment extends Fragment {

    private static final int RC_SIGN_IN = 100;
    private FirebaseAuth mAuth;
    private GoogleSignInClient googleSignInClient;

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
            MainActivity app = (MainActivity) getActivity();
            if (app != null) {
                app.applyAndSavedLocale("en");
            }
            // Navigate to the next fragment
            Navigation.findNavController(view).navigate(R.id.action_langSelectFragment_to_onBoardingFragment1);

        });

        Button btnSinhala = view.findViewById(R.id.select_sin);
        btnSinhala.setOnClickListener(v -> {
            // Save the selected language to shared preferences
            MainActivity app = (MainActivity) getActivity();
            if (app != null) {
                app.applyAndSavedLocale("si");
            }
            // Navigate to the next fragment
            Navigation.findNavController(view).navigate(R.id.action_langSelectFragment_to_onBoardingFragment1);
        });
        return view;
    }
}