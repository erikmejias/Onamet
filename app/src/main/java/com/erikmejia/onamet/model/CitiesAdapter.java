package com.erikmejia.onamet.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.erikmejia.onamet.MainActivity;
import com.erikmejia.onamet.R;
import com.erikmejia.onamet.ui.ForecastDetails;
import com.erikmejia.onamet.util.Utils;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

import java.util.List;

/**
 * Created by erik on 11/22/16.
 */

public class CitiesAdapter extends FirebaseRecyclerAdapter<ForecastLite, CitiesHolder> {
    private String TAG = CitiesAdapter.class.getSimpleName();


    private Typeface font_bold;
    private Context context;
    private MainActivity.ViewPagerAdapter content_adapter;
    private DrawerLayout nav_drawer;
    private FrameLayout root;


    public CitiesAdapter(Class<ForecastLite> modelClass, int layout,
                         Class<CitiesHolder> viewHolderClass, Query ref,
                         Context context, MainActivity.ViewPagerAdapter adapter,
                         DrawerLayout drawer_layout, FrameLayout root) {

        super(modelClass, layout, viewHolderClass, ref);
        this.content_adapter = adapter;
        this.nav_drawer = drawer_layout;
        this.context = context;
        this.root = root;
        font_bold = Typeface.createFromAsset(this.context.getAssets(),
                "fonts/Brandon_bld.otf");

        Log.d(TAG, "CitiesAdapter: constructor called");
    }

    @Override
    public void populateViewHolder(final CitiesHolder viewHolder, final ForecastLite model, final int position) {

        viewHolder.setCity_name(model.getName());
        viewHolder.setCity_status(model.getIconId());

        viewHolder.getCity_name().setTypeface(font_bold);
        Log.d(TAG, "populateViewHolder: " + model.getIconId());
        viewHolder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nav_drawer.closeDrawer(GravityCompat.START);
                SharedPreferences sharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("city", position);
                editor.apply();
                content_adapter.getItem(0).onCreate(new Bundle());
                content_adapter.notifyDataSetChanged();
                Utils.dynamicBackground(context, root, position);
            }
        });

    }

    @Override
    public CitiesHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_list_item,
                parent, false);

        return new CitiesHolder(root);
        }

}

