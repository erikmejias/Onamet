package com.erikmejia.onamet.model;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erikmejia.onamet.R;

/**
 * Created by erik on 9/4/16.
 */

public class BulletinsAdapter extends RecyclerView.Adapter<BulletinsAdapter.ViewHolder>{
    private String TAG = ForecastAdapter.class.getSimpleName();

    private String[] dataset;
    private String description = "Lorem ipsum dolor sit amet. Ip dolor ipsum lot of trouble " +
            "sitting in the dinning table of Frank Abagnale Jr. The Guy that almost break " +
            "all of it.";

    public BulletinsAdapter(String[] receivedData){
        dataset = receivedData;
        Log.d(TAG, "NewsAdapter: " + dataset.length);
    }

    @Override
    public BulletinsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bulletin_item,
                parent, false);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.date.setText("Sep 2");
        holder.title.setText("Peligro de extensiones de agua");
        holder.description.setText(description);
        Log.d(TAG, "onBindViewHolder: " + dataset[position]);
    }

    @Override
    public int getItemCount() {
        return dataset.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView date;
        public TextView title;
        public TextView description;

        public ViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.bulletins_date_text);
            title = (TextView) itemView.findViewById(R.id.bulletins_title_text);
            description = (TextView) itemView.findViewById(R.id.bulletins_description_text);
        }
    }
}