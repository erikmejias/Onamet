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

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
    private String TAG = ForecastAdapter.class.getSimpleName();

    private String[] dataset;
    private String description = "Lorem ipsum dolor sit amet. Ip dolor ipsum lot of trouble " +
            "sitting in the dinning table of Frank Abagnale Jr. The Guy that almost break " +
            "all of it.";

    public NewsAdapter(String[] receivedData){
        dataset = receivedData;
        Log.d(TAG, "NewsAdapter: " + dataset.length);
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,
                parent, false);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.date.setText("Sep 2");
        holder.title.setText("Onamet firma acuerdo con Erik");
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
            date = (TextView) itemView.findViewById(R.id.news_date_text);
            title = (TextView) itemView.findViewById(R.id.news_title_text);
            description = (TextView) itemView.findViewById(R.id.news_description_text);
        }
    }
}