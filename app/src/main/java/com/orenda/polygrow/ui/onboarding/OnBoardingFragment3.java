package com.orenda.polygrow.ui.onboarding;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseUser;
import com.orenda.polygrow.MainActivity;
import com.orenda.polygrow.R;
import com.orenda.polygrow.auth.AuthenticatedActivity;

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
                activity.authenticateAsync(new AuthenticatedActivity.Callback<FirebaseUser, Exception>() {
                    @Override
                    public void onAuthenticated(FirebaseUser user) {
                        Log.i("OnBoardingFragment3", "Authenticated user: " + user);
                        // show toast
                        Toast.makeText(activity, "Authenticated user: " + user, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Exception error) {

                    }
                });

                BottomNavigationView navView = activity.findViewById(R.id.nav_view);
                navView.setVisibility(View.VISIBLE);
            }else {
                Log.e("LangSelectFragment", "Activity is null");
            }

            Navigation.findNavController(v).setGraph(R.navigation.mobile_navigation);

//            Context context = getContext();
//            GetGoogleIdOption googleIdOption = new GetGoogleIdOption.Builder()
//                    .setFilterByAuthorizedAccounts(true)
//                    .setServerClientId(context.getString(R.string.default_web_client_id))
//                    .build();
//            GetCredentialRequest request = new GetCredentialRequest.Builder()
//                    .addCredentialOption(googleIdOption)
//                    .build();
//            CredentialManager credentialManager = CredentialManager.create(context);
//
//            credentialManager.getCredentialAsync(
//                    context,
//                    request,
//                    new CancellationSignal(),
//                    Executors.newSingleThreadExecutor(),
//                    new CredentialManagerCallback<GetCredentialResponse, GetCredentialException>() {
//                        @Override
//                        public void onResult(GetCredentialResponse result) {
//                            Log.i("Result", result.toString());
//                        }
//
//                        @Override
//                        public void onError(GetCredentialException e) {
//                            Log.e("CredentialManager", "Error: " + e.getMessage());
//                            if ( e.getMessage().contains("ClassNotFoundException") ) {
//                                Log.e("CredentialManager", "Google Play Services missing or outdated. Switching to manual sign-in.");
//                                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                                         .requestIdToken(getString(R.string.default_web_client_id))
//                                        .requestEmail()
//                                        .build();
//
//                                googleSignInClient = GoogleSignIn.getClient(context, gso);
//
//                                Intent signInIntent = googleSignInClient.getSignInIntent();
//                                startActivityForResult(signInIntent, RC_SIGN_IN);
//                            }
//                        }
//                    }
//            );

        });
        return view;
    }
}