package com.erikmejia.onamet.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.erikmejia.onamet.R;
import com.erikmejia.onamet.util.Utils;

/**
 * Created by erik on 9/1/16.
 */

public class SettingsActivity extends AppCompatActivity {

    LinearLayout frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pref_with_actionbar);

        frame = (LinearLayout) findViewById(R.id.settings_frame);

        Toolbar toolbar = (Toolbar) findViewById(R.id.settings_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getFragmentManager().beginTransaction()
                .replace(R.id.content_settings_frame, new SettingsFragment())
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences getPrefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        String defaultBackg = "https://firebasestorage.googleapis.com/v0/b/project-7000350159161293832.appspot.com/o/default.jpg?alt=media&token=cfbcaa6d-033a-4e6f-b04b-2d6644ac0630";
        String PROVINCE_ID = getPrefs.getString("background", defaultBackg); // TODO - Must pass a default value to ensure works on startup

        Utils.dynamicBackground(this, frame, PROVINCE_ID);
    }
}
