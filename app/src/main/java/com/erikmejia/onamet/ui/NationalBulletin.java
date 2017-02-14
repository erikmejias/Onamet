package com.erikmejia.onamet.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.erikmejia.onamet.R;
import com.erikmejia.onamet.util.Utils;

public class NationalBulletin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_national_bulletin);

        Toolbar toolbar = (Toolbar) findViewById(R.id.national_bulletins_toolbar);
        Utils.applyFontForToolbarTitle(this, R.id.national_bulletins_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
