package com.erikmejia.onamet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.erikmejia.onamet.model.CitiesAdapter;
import com.erikmejia.onamet.model.CitiesHolder;
import com.erikmejia.onamet.model.ForecastLite;
import com.erikmejia.onamet.service.FirebaseBackgroundService;
import com.erikmejia.onamet.ui.BulletinsFragment;
import com.erikmejia.onamet.ui.ForecastFragment;
import com.erikmejia.onamet.ui.SettingsActivity;
import com.erikmejia.onamet.util.PageTransformer;
import com.erikmejia.onamet.util.Utils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    FrameLayout rootLayout;
    private static boolean calledAlready = false;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private RecyclerView cityList;
    private ViewPagerAdapter viewPagerAdapter;
    private DatabaseReference cities_reference;
    private CitiesAdapter citiesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        MobileAds.initialize(getApplicationContext(), "ca-app-pub-6005843157698202~1566560378");

//        SharedPreferences sharedPreferences = PreferenceManager
//                .getDefaultSharedPreferences(this);

//        calledAlready = sharedPreferences.getBoolean("calledAlready", false);

//        Check that this method is called only once so it doesn't crash the app
        if (!calledAlready) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putBoolean("calledAlready", false);

//            editor.apply();
        }


        setContentView(R.layout.activity_main);
//        getWindow().setBackgroundDrawable(null);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Utils.applyFontForToolbarTitle(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        rootLayout = (FrameLayout) findViewById(R.id.main_content_frame);


        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setPageTransformer(true, new PageTransformer(PageTransformer.TransformType.DEPTH));
        setViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        cityList = (RecyclerView) findViewById(R.id.left_drawer);
        cityList.setHasFixedSize(true);
        cityList.setClickable(true);
        cityList.setLayoutManager(manager);

        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.drawer_open,
                R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };

        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerLayout.setScrimColor(getResources().getColor(R.color.black_alpha_drawer));

//        Getting the database reference of cities list
        cities_reference = FirebaseDatabase.getInstance().getReference("forecasts_list");

        citiesAdapter = new CitiesAdapter(
                ForecastLite.class,
                R.layout.city_list_item,
                CitiesHolder.class,
                cities_reference,
                this,
                viewPagerAdapter,
                drawerLayout,
                rootLayout
        );

        cityList.setAdapter(citiesAdapter);


        if (getIntent().getBooleanExtra("bulletin", false)) {
            incomingBulletin();
        }

//        startService(new Intent(this, FirebaseBackgroundService.class));

    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: initiated");
        SharedPreferences getPrefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        int PROVINCE_ID = getPrefs.getInt("city", 0);

        Utils.dynamicBackground(this, rootLayout, PROVINCE_ID);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Start the background services that handles notifications
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
//        Check which selection is being made.
        switch (item.getItemId()) {
//            Open settings ui panel.
            case R.id.settings:
                showSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*
    * Custom method to show settings UI
    * */
    private void showSettings() {
//        Start explicit intent
        Intent intent;
        intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    /*
    * Custom method that initialize the custom viewpager with the fragments.
    * */
    private void setViewPager(ViewPager viewPager) {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new ForecastFragment(), "Pronosticos");
        viewPagerAdapter.addFragment(new BulletinsFragment(), "Boletines");
        viewPager.setAdapter(viewPagerAdapter);
    }

    private void incomingBulletin() {
        viewPagerAdapter.getItem(0);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    /*
    * ViewPagerAdapter inner class that handles fragments objects that gets displayed in
    * the main content view as Forecasts & Bulletins list
    * */
    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Release all listeners.
        citiesAdapter.cleanup();
    }
}
