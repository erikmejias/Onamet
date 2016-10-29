package com.erikmejia.onamet.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.erikmejia.onamet.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.BuildConfig;
import com.firebase.ui.auth.ui.email.SignInActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.ExecutionException;

import static android.app.Activity.RESULT_OK;
import static com.firebase.ui.auth.ui.AcquireEmailHelper.RC_SIGN_IN;

/**
 * Created by erik on 9/1/16.
 */

public class SettingsFragment extends PreferenceFragment {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    SimpleTarget target;
    EditTextPreference smsPreference;
    Preference signOutPreference;
    Preference signInPreference;

    private static final String TAG = SettingsFragment.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        target = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                // do something with the bitmap
                // for demonstration purposes, let's just set it to an ImageView
//                imageView1.setImageBitmap( bitmap );
                Drawable d = new BitmapDrawable(getResources(), bitmap);
                signInPreference.setIcon(d);
            }
        };

        super.onCreate(savedInstanceState);

//        Load the preference from an XML resource.
        addPreferencesFromResource(R.xml.preferences);

        smsPreference = (EditTextPreference) findPreference(getString(R.string.pref_account_sms_key));
//        smsPreference.setEnabled(true);

        signInPreference = findPreference(getString(R.string.pref_account_key));
        signInPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
//                Toast.makeText(getActivity(), "Signing in", Toast.LENGTH_SHORT).show();
                signIn();
                return false;
            }
        });

        signOutPreference = findPreference(getString(R.string.pref_account_signout_key));
        signOutPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                signOut();
                return false;
            }
        });

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null){
            smsPreference.setEnabled(true);
            signOutPreference.setEnabled(true);
        }
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
                user = FirebaseAuth.getInstance().getCurrentUser();
                smsPreference.setEnabled(true);
                signOutPreference.setEnabled(true);
                signInPreference.setTitle(user.getEmail());
                signInPreference.setSummary("Has iniciado sesión como " + user.getDisplayName());

                Glide.with(getActivity())
                        .load(user.getPhotoUrl())
                        .asBitmap()
                        .centerCrop()
                        .into(new SimpleTarget<Bitmap>(150, 150) {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                Drawable profilePicture = new BitmapDrawable(getResources(), resource);

                                signInPreference.setIcon(profilePicture);
                            }
                        });

                Log.d(TAG, "onActivityResult: welcome " + user.getDisplayName());

                Toast.makeText(getActivity(), "Bienvenido "
                        + mAuth.getCurrentUser().getDisplayName(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onActivityResult: profile url " + user.getPhotoUrl());
//                getActivity().finish();
            } else {
                // user is not signed in. Maybe just wait for the user to press
                // "sign in" again, or show a message
            }
        }
    }

    public void signIn() {
        if (user != null) {
            // Already signed in
            Log.d(TAG, "signIn: already signed in" + user.getDisplayName());
            Toast.makeText(getActivity(), "Ya estabas logueado como "
                    + user.getDisplayName(), Toast.LENGTH_SHORT).show();
        } else {
            // Not signed in... yet :)

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setIsSmartLockEnabled(!BuildConfig.DEBUG) // TODO - Change later to true
                            .setTheme(R.style.SignInTheme)
                            .setProviders(
                                    AuthUI.GOOGLE_PROVIDER,
                                    AuthUI.FACEBOOK_PROVIDER)
                            .setLogo(R.drawable.rain_snow) // TODO - Put real big logo
                            .build(),
                    RC_SIGN_IN);
        }
    }

    public void signOut() {

        if (user != null) {

            AuthUI.getInstance()
                    .signOut(getActivity())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(@NonNull Task<Void> task) {
                            // user is now signed out
//                        startActivity(new Intent(getActivity(), SignInActivity.class));
//                        getActivity().finish();
                            Toast.makeText(getActivity(), "Has cerrado sesión", Toast.LENGTH_SHORT).show();
                        }
                    });
            smsPreference.setEnabled(false);
            signOutPreference.setEnabled(false);
            signInPreference.setTitle(getString(R.string.acc_prefs_sync));
            signInPreference.setSummary(getString(R.string.pref_account_summary));
            signInPreference.setIcon(getResources().getDrawable(R.drawable.unknown)); // TODO - replace this call
            user = null;

        } else {

            Toast.makeText(getActivity(), "No estabas logeado", Toast.LENGTH_SHORT).show();

        }
    }
}
