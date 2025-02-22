package com.orenda.polygrow.auth;

import android.os.CancellationSignal;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.credentials.CredentialManager;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.GetCredentialRequest;
import androidx.credentials.GetCredentialResponse;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.exceptions.NoCredentialException;

import com.google.android.libraries.identity.googleid.GetGoogleIdOption;
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.orenda.polygrow.R;

import java.util.Optional;
import java.util.concurrent.Executors;


public class AuthenticatedActivity extends AppCompatActivity {

    protected CredentialManager credentialManager;
    protected static final int RC_SIGN_IN = 1001;
    protected FirebaseAuth firebaseAuth;

    public interface Callback<T, E> {
        void onAuthenticated(T user);
        void onError(E error);
    }

    @Override
    protected void onStart() {
        super.onStart();
        credentialManager = CredentialManager.create(this);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    public void authenticateAsync(Callback<FirebaseUser, Exception> callback) {
        // Get the Google ID token credential for the returning user
        GetGoogleIdOption googleIdOption = new GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(true)
                .setServerClientId(this.getString(R.string.default_web_client_id))
                .build();
        GetCredentialRequest request = new GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build();

        credentialManager.getCredentialAsync(
                this,
                request,
                new CancellationSignal(),
                Executors.newSingleThreadExecutor(),
                new CredentialManagerCallback<GetCredentialResponse, GetCredentialException>() {
                    @Override
                    public void onResult(GetCredentialResponse result) {
                        Log.i("CredentialManager", "Credential retrieved successfully for returning user: $result");
                        handleSignInSuccess((GoogleIdTokenCredential) result.getCredential(), callback);
                    }

                    @Override
                    public void onError(GetCredentialException e) {
                        handleCredentialManagerError(e, callback);
                    }
                }
        );
    }

    private void handleCredentialManagerError(GetCredentialException e, Callback<FirebaseUser, Exception> callback) {
        if (e instanceof NoCredentialException) {
            Log.i("CredentialManager", "No credential found for returning user");
            handleNewUser(callback);
        } else {
            Log.e("CredentialManager", "Returning User Error: " + e.getMessage());
            Optional.ofNullable(callback).ifPresent(cb -> cb.onError(e));
        }
    }

    private void handleNewUser(Callback<FirebaseUser, Exception> callback) {
        GetGoogleIdOption googleIdOption = new GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(this.getString(R.string.default_web_client_id))
                .build();

        GetCredentialRequest request = new GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build();

        credentialManager.getCredentialAsync(
                this,
                request,
                new CancellationSignal(),
                Executors.newSingleThreadExecutor(),
                new CredentialManagerCallback<GetCredentialResponse, GetCredentialException>() {
                    @Override
                    public void onResult(GetCredentialResponse result) {
                        Log.i("CredentialManager", "Credential retrieved successfully for new user: $result");
                        handleSignInSuccess((GoogleIdTokenCredential) result.getCredential(), callback);
                    }

                    @Override
                    public void onError(GetCredentialException e) {
                        Log.e("CredentialManager", "New User Error: " + e.getMessage());
                        Optional.ofNullable(callback).ifPresent(cb -> cb.onError(e));
                    }
                }
        );
    }

    private void handleSignInSuccess(GoogleIdTokenCredential credential, Callback<FirebaseUser, Exception> callback) {
        String idToken = credential.getIdToken();
        if (idToken != null) {
            // Use the ID token to authenticate the user
            AuthCredential authCredential = GoogleAuthProvider.getCredential(idToken, null);
            // Authenticate with Firebase
            firebaseAuth.signInWithCredential(authCredential)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            Log.i("FirebaseAuth", "Sign in with credential successful");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Optional.ofNullable(callback).ifPresent(cb -> cb.onAuthenticated(user));
                        } else {
                            Log.e("FirebaseAuth", "Sign in with credential failed", task.getException());
                            Optional.ofNullable(callback).ifPresent(cb -> cb.onError(task.getException()));
                        }
                    });
        }else {
            Log.e("GoogleIdTokenCredential", "ID token is null");
        }
    }

    protected void signOut() {
        firebaseAuth.signOut();
    }

    public FirebaseUser getAuthenticatedUser(Callback<FirebaseUser, Exception> callback) {
        // get the currently authenticated user from Firebase if it null then show the login screen.
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user == null) {
            Log.e("AuthenticatedActivity", "User is not authenticated");
            // show the login screen
            this.authenticateAsync(callback);
            return null;
        }
        Optional.ofNullable(callback).ifPresent(cb -> cb.onAuthenticated(user));
        return user;
    }

}
