package com.erikmejia.onamet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.erikmejia.onamet.ui.BulletinsFragment;
import com.erikmejia.onamet.ui.ForecastFragment;
import com.erikmejia.onamet.ui.SettingsActivity;
import com.erikmejia.onamet.util.PageTransformer;

import java.util.ArrayList;
import java.util.List;

import com.erikmejia.onamet.util.PageTransformer.*;
import com.google.android.gms.ads.MobileAds;

import static com.erikmejia.onamet.R.layout.activity_main;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    // Firebase Database Object
    /* Firebase References to objects of the Realtime database. */
//    private DatabaseReference azuaReference;

//    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-6005843157698202~1566560378");



        /*
        * Initializing the OnBoarding experience UI
        * */

        //  Declare a new thread to do a preference check
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Initialize SharedPreferences
                SharedPreferences getPrefs = PreferenceManager
                        .getDefaultSharedPreferences(getBaseContext());

                //  Create a new boolean and preference and set it to true
                boolean isFirstStart = getPrefs.getBoolean("firstStart", true);

                //  If the activity has never started before...
                if (isFirstStart) {

                    //  Launch app intro
                    Intent i = new Intent(MainActivity.this, IntroActivity.class);
                    startActivity(i);

                    //  Make a new preferences editor
                    SharedPreferences.Editor e = getPrefs.edit();

                    //  Edit preference to make it false because we don't want this to run again
//                    e.putBoolean("firstStart", false);

                    //  Apply changes
                    e.apply();
                }
            }
        });

        // Start the thread
        t.start();


        setContentView(activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        Cache data to local disk ( OFFLINE SUPPORT ).
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

//        azuaReference= FirebaseDatabase.getInstance().getReference("/demo");

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setPageTransformer(true, new PageTransformer(PageTransformer.TransformType.DEPTH));
        setViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: initiated");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        Check which selection is being made.
        switch (item.getItemId()) {
//            Open settings ui panel.
            case R.id.settings:
                showSettings();
                return true;
            case R.id.map:
                Toast.makeText(this, "Abriendo mapa", Toast.LENGTH_SHORT).show();
                showMap();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showSettings() {
//        Start explicit intent
        Intent intent;
        intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);

    }

    private void showMap(){
        Intent intent;
        intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    private void setViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ForecastFragment(), "Pronósticos");
        adapter.addFragment(new BulletinsFragment(), "Boletines");
//        adapter.addFragment(new NewsFragment(), "Noticias");
        viewPager.setAdapter(adapter);
    }

    /* onItemSelected
    * onNothingSelected
    * methods used by the spinner on the toolbar.
    * */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedProvince = parent.getItemAtPosition(position).toString();
        Log.d(TAG, "onItemSelected: " + selectedProvince);
        Toast.makeText(this, selectedProvince, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
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

}
