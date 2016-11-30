package com.erikmejia.onamet;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
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
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.erikmejia.onamet.model.CitiesAdapter;
import com.erikmejia.onamet.service.FirebaseBackgroundService;
import com.erikmejia.onamet.ui.BulletinsFragment;
import com.erikmejia.onamet.ui.ForecastFragment;
import com.erikmejia.onamet.ui.SettingsActivity;
import com.erikmejia.onamet.util.PageTransformer;
import com.erikmejia.onamet.util.Utils;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInLeftAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;

import static com.erikmejia.onamet.R.layout.activity_main;


public class MainActivity extends AppCompatActivity{

    private static final String TAG = MainActivity.class.getSimpleName();
    String[] city_list = {
            "Santo Domingo",
            "Santiago",
            "Moca",
            "La Romana",
            "Higuey",
            "Punta Cana",
            "San Pedro de Macorís",
            "Hato Mayor del Rey",
            "El Seibo",
            "Sabana de la Mar",
            "Miches",
            "Monte Plata",
            "Puerto Plata",
            "La Vega",
            "San Francisco",
            "Mao",
            "Jarabacoa",
            "Bonao",
            "Nagua",
            "Samaná",
            "Azua",
            "Baní",
            "Dajabón",
            "Duarte",
            "Hermanas Mirabal",
            "Independencia",
            "Monte Cristi",
            "Pedernales",
            "Sánchez Ramírez",
            "San Cristobal",
            "San José de Ocoa",
            "San Juan"
    };

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private RecyclerView cityList;
    private ViewPagerAdapter viewPagerAdapter;
    FrameLayout rootLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        MobileAds.initialize(getApplicationContext(), "ca-app-pub-6005843157698202~1566560378");

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
                    e.putBoolean("firstStart", false);

                    //  Apply changes
                    e.apply();
                }
            }
        });

        // Start the thread
        t.start();


        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
//        drawerLayout.setAlpha(0.7f);

        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(drawerToggle);
        CitiesAdapter citiesAdapter = new CitiesAdapter(city_list, viewPagerAdapter,
                drawerLayout, this);

        SlideInLeftAnimationAdapter animationAdapter =
                new SlideInLeftAnimationAdapter(citiesAdapter);
        animationAdapter.setInterpolator(new OvershootInterpolator());
        animationAdapter.setDuration(550);
        animationAdapter.setFirstOnly(false);

        cityList.setAdapter(animationAdapter);


        if (getIntent().getBooleanExtra("bulletin", false)) {
            incomingBulletin();
        }

//        startService(new Intent(this, FirebaseBackgroundService.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: initiated");

        Utils.dynamicBackground(this, rootLayout);

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

    private void showSettings() {
//        Start explicit intent
        Intent intent;
        intent = new Intent(this, SettingsActivity.class);
        /*if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(
                    this,
                    rootLayout,
                    rootLayout.getTransitionName()
            )
                    .toBundle();
            startActivity(intent, bundle);
        } else {
            startActivity(intent);
        }*/
        startActivity(intent);
    }

    public void changeCity(int city) {
        Bundle extras = new Bundle();
//        extras.putInt("city", city);
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("city", city);
        editor.apply();
        viewPagerAdapter.getItem(0).onCreate(extras);
        viewPagerAdapter.notifyDataSetChanged();
    }

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


}
