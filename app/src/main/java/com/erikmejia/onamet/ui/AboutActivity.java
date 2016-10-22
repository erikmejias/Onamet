package com.erikmejia.onamet.ui;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.erikmejia.onamet.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar = (Toolbar) findViewById(R.id.about_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView content = (TextView) findViewById(R.id.pref_about_text);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Brandon_reg.otf");

        content.setTypeface(typeface);
    }
}
