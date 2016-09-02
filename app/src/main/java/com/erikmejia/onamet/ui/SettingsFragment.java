package com.erikmejia.onamet.ui;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.erikmejia.onamet.R;

/**
 * Created by erik on 9/1/16.
 */

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Load the preference from an XML resource.
        addPreferencesFromResource(R.xml.preferences);
    }
}
