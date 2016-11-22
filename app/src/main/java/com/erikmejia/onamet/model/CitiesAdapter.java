package com.erikmejia.onamet.model;

import android.content.Context;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.erikmejia.onamet.MainActivity;
import com.erikmejia.onamet.R;
import com.erikmejia.onamet.util.Utils;

import java.util.List;

/**
 * Created by erik on 11/22/16.
 */

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ViewHolder>{
    private String TAG = CitiesAdapter.class.getSimpleName();

    private String[] dataset;
    private Typeface font_typeface;
    private OnForecastItemClickListener listener;
    private Context receivedContext;
    private MainActivity.ViewPagerAdapter viewpagerAdapter;
    private DrawerLayout drawerLayout;


    public CitiesAdapter(String[] receivedData, MainActivity.ViewPagerAdapter viewpager,
                         DrawerLayout drawerLayout, Context context){
        this.dataset = receivedData;
        this.receivedContext = context;
        this.viewpagerAdapter = viewpager;
        this.drawerLayout = drawerLayout;

        font_typeface = Typeface.createFromAsset(receivedContext.getAssets(),
                "fonts/Brandon_reg.otf");
    }

    @Override
    public CitiesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ViewHolder holder;

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_list_item,
                parent, false);
        holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.city_name.setText(dataset[position]);
        holder.icon.setImageResource(Utils.bulletinIcon(position));

        holder.city_name.setTypeface(font_typeface);

        holder.bind(dataset[position], position);

    }

    @Override
    public int getItemCount() {
        return dataset.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView city_name;
        public ImageView icon;

        ViewHolder(View itemView) {
            super(itemView);
            city_name = (TextView) itemView.findViewById(R.id.city_name_text);
            icon = (ImageView) itemView.findViewById(R.id.city_item_icon);

            itemView.setClickable(true);
        }

        void bind(final String cityItem, final int position) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: " + cityItem + " position: " + position);
                    Bundle extras = new Bundle();
                    SharedPreferences sharedPreferences = PreferenceManager
                            .getDefaultSharedPreferences(receivedContext);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("city", position);
                    editor.apply();
                    viewpagerAdapter.getItem(0).onCreate(extras);
                    viewpagerAdapter.notifyDataSetChanged();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
            });
        }
    }
}