package com.erikmejia.onamet.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

/**
 * Created by erik on 9/1/16.
 */

public class SettingsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}
