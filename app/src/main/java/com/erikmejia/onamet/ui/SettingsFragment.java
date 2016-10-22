package com.erikmejia.onamet.ui;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.erikmejia.onamet.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.BuildConfig;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.app.Activity.RESULT_OK;
import static com.firebase.ui.auth.ui.AcquireEmailHelper.RC_SIGN_IN;

/**
 * Created by erik on 9/1/16.
 */

public class SettingsFragment extends PreferenceFragment {

    private FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener authStateListener;

    private static final String TAG = SettingsFragment.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

//        Load the preference from an XML resource.
        addPreferencesFromResource(R.xml.preferences);

        Preference signInPreference = findPreference(getString(R.string.pref_account_key));
        signInPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
//                Toast.makeText(getActivity(), "Signing in", Toast.LENGTH_SHORT).show();
                signIn();
                return false;
            }
        });

        authStateListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
//                            Already logged in
                    Log.d(TAG, "onAuthStateChanged: logged as" + user.getDisplayName());
                }
                else {
//                            User is signed out
                }
            }
        };

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
//        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
//        mAuth.removeAuthStateListener(authStateListener);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                // user is signed in!
//                startActivity(new Intent(this, WelcomeBackActivity.class));
                getPreferenceManager().findPreference(getString(R.string.pref_account_signout_key)).setEnabled(true);
                Log.d(TAG, "onActivityResult: welcome " + FirebaseAuth.getInstance().getCurrentUser().getDisplayName());

                Toast.makeText(getActivity(), "Welcome", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            } else {
                // user is not signed in. Maybe just wait for the user to press
                // "sign in" again, or show a message
                Log.d(TAG, "onActivityResult: welcome " + FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
            }
        }
    }

    public void signIn() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            // Already signed in
            Log.d(TAG, "signIn: already signed in" + auth.getCurrentUser().getDisplayName());
        } else {
            // Not signed in... yet :)

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setIsSmartLockEnabled(false) // Change later to true
                            .setTheme(R.style.SignInTheme)
                            .setProviders(
                                    AuthUI.GOOGLE_PROVIDER,
                                    AuthUI.FACEBOOK_PROVIDER)
                            .build(),
                    RC_SIGN_IN);
        }
    }
}
