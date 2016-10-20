package com.erikmejia.onamet.model;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.erikmejia.onamet.R;
import com.erikmejia.onamet.util.Utils;

import java.util.List;

/**
 * Created by erik on 9/4/16.
 */

public class BulletinsAdapter extends RecyclerView.Adapter<BulletinsAdapter.ViewHolder>{
    private String TAG = ForecastAdapter.class.getSimpleName();

    private List<Bulletin> bulletins;
    private String[] dataset;
    private String description = "Lorem ipsum dolor sit amet. Ip dolor ipsum lot of trouble " +
            "sitting in the dinning table of Frank Abagnale Jr. The Guy that almost break " +
            "all of it.";
    private Typeface font_thin;
    private Typeface font_reg;
    private Typeface font_bold;

    public BulletinsAdapter(List<Bulletin> receivedData){
        bulletins = receivedData;

        Log.d(TAG, "BulletinsAdapter: " + receivedData.size());
    }

    @Override
    public BulletinsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bulletin_item,
                parent, false);

//        Initiating font objects
        font_thin = Typeface.createFromAsset(parent.getContext().getAssets(),
                "fonts/Brandon_thin.otf");
        font_reg = Typeface.createFromAsset(parent.getContext().getAssets(),
                "fonts/Brandon_reg.otf");
        font_bold = Typeface.createFromAsset(parent.getContext().getAssets(),
                "fonts/Brandon_bld.otf");

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.date.setText(bulletins.get(position).getDate());
        holder.title.setText(bulletins.get(position).getTitle());
        holder.description.setText(bulletins.get(position).getDescription());
        holder.icon.setImageResource(Utils.bulletinIcon(bulletins.get(position).getIconId()));

        holder.title.setTypeface(font_bold);
        holder.description.setTypeface(font_reg);
        holder.date.setTypeface(font_thin);
    }

    @Override
    public int getItemCount() {
        return bulletins.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView date;
        public TextView title;
        public TextView description;
        public ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.bulletins_date_text);
            title = (TextView) itemView.findViewById(R.id.bulletins_title_text);
            description = (TextView) itemView.findViewById(R.id.bulletins_description_text);
            icon = (ImageView) itemView.findViewById(R.id.bulletin_icon);
        }
    }
}