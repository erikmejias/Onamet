package com.erikmejia.onamet.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.GravityCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.erikmejia.onamet.R;
import com.erikmejia.onamet.model.User;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.BuildConfig;
import com.firebase.ui.auth.ui.ResultCodes;
import com.firebase.ui.auth.ui.email.SignInActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.firebase.ui.auth.ui.AcquireEmailHelper.RC_SIGN_IN;

/**
 * Created by erik on 9/1/16.
 */

public class SettingsFragment extends PreferenceFragment {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    SimpleTarget target;
    Preference smsPreference;
    Preference signOutPreference;
    Preference signInPreference;
    private String number;

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

        smsPreference = findPreference(getString(R.string.pref_account_sms_key));
        smsPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                final DialogPlus dialogPlus =
                        DialogPlus.newDialog(getActivity())
                        .setContentHolder(new ViewHolder(R.layout.activity_smsvalidation))
                        .setGravity(Gravity.BOTTOM)
                        .setCancelable(true)
                        .setExpanded(true, 900)
                        .create();
                dialogPlus.show();

                TextView title = (TextView) getActivity().findViewById(R.id.sms_verification_title);
                Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),
                        "fonts/Brandon_light.otf");
                title.setTypeface(typeface);

                final EditText editText = (EditText) getActivity().findViewById(R.id.sms_phone_number);

                if (number == null) {
                    editText.requestFocus();
                } else {
                    editText.setText(number);
                    title.setText(R.string.is_this_your_number);
                }

                Button save = (Button) getActivity().findViewById(R.id.submit_button);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogPlus.dismiss();

                        InputMethodManager imm = (InputMethodManager)getActivity()
                                .getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                        Toast.makeText(getActivity(), editText.getText() + " guardado", Toast.LENGTH_SHORT).show();
                        smsPreference.setSummary("En emergencias recibirás los boletines" +
                                " al " + editText.getText() + " por si no tienes Internet");
                        addToFirebase(editText.getText().toString());
                    }
                });

                return false;
            }
        });

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

        checkIfSignedIn();
        isVerified();
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
                if (user.getEmail() != null) {
                    signInPreference.setTitle(user.getEmail());
                    signInPreference.setSummary("Has iniciado sesión como " + user.getDisplayName());
                } else {
                    signInPreference.setTitle(user.getDisplayName());
                    signInPreference.setSummary("Has iniciado sesión correctamente");
                }
                isVerified();

                Glide.with(getActivity())
                        .load(user.getPhotoUrl())
                        .asBitmap()
                        .centerCrop()
                        .into(new SimpleTarget<Bitmap>(150, 150) {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                RoundedBitmapDrawable circularIcon =
                                        RoundedBitmapDrawableFactory.create(getResources(), resource);
                                circularIcon.setCircular(true);

                                signInPreference.setIcon(circularIcon);
                            }
                        });

                Toast.makeText(getActivity(), "Bienvenido "
                        + mAuth.getCurrentUser().getDisplayName(), Toast.LENGTH_SHORT).show();
//                getActivity().finish();
            }
            if (resultCode == RESULT_CANCELED){
                // user is not signed in. Maybe just wait for the user to press
                // "sign in" again, or show a message
                Toast.makeText(getActivity(), "Has cancelado el proceso", Toast.LENGTH_SHORT).show();
            }
            if(resultCode == ResultCodes.RESULT_NO_NETWORK) {
                Toast.makeText(getActivity(), "Intenta de nuevo cuando tengas Internet", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void signIn() {
        if (user != null) {
            // Already signed in
            Toast.makeText(getActivity(), "Has iniciado sesión como "
                    + user.getDisplayName(), Toast.LENGTH_SHORT).show();
        } else {
            // Not signed in... yet :)

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setIsSmartLockEnabled(true)
                            .setTheme(R.style.SignInTheme)
                            .setProviders(Arrays.asList(
                                    new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build(),
                                    new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()
                            ))
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
            smsPreference.setSummary(R.string.pref_sms_summary);
            signOutPreference.setEnabled(false);
            signInPreference.setTitle(getString(R.string.acc_prefs_sync));
            signInPreference.setSummary(getString(R.string.pref_account_summary));
            user = null;

        } else {

            Toast.makeText(getActivity(), "No estabas logeado", Toast.LENGTH_SHORT).show();

        }
    }

    public void checkIfSignedIn() {

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
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
                            RoundedBitmapDrawable circularIcon =
                                    RoundedBitmapDrawableFactory.create(getResources(), resource);
                            circularIcon.setCircular(true);

                            signInPreference.setIcon(circularIcon);
                        }
                    });
        }
        else {

            smsPreference.setEnabled(false);
            signOutPreference.setEnabled(false);

        }
    }

    public void addToFirebase(String phone_number) {
        number = phone_number;
        boolean state = false;
        if (!phone_number.isEmpty()) {
            state = true;
        }

        User user = new User(
                this.user.getUid(),
                this.user.getDisplayName(),
                state,
                phone_number
        );
        Log.d(TAG, "addToFirebase: user " + user.toString());

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("users").child(user.getId()).setValue(user);
    }

    public boolean isVerified() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        if (this.user != null) {
            if (reference.child(this.user.getUid()) != null){
                reference.child(this.user.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                        if (user != null) {
                            if (user.isVerified()) {
                                smsPreference.setSummary("En emergencias recibirás los boletines " +
                                        "al " + user.getPhone_number() + " por si no tienes Internet");
                                number = user.getPhone_number();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }
        return true;
    }
}
