package com.erikmejia.onamet.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.erikmejia.onamet.R;
import com.erikmejia.onamet.util.Utils;

/**
 * Created by erik on 12/2/16.
 */

public class CitiesHolder extends RecyclerView.ViewHolder {

    private TextView city_name;
    public ImageView city_status;
    private View rootView;

    public CitiesHolder(View itemView) {
        super(itemView);
        rootView = itemView;

        city_name = (TextView) itemView.findViewById(R.id.city_name_text);
        city_status = (ImageView) itemView.findViewById(R.id.city_item_icon);
    }

    public ImageView getCity_status() {
        return city_status;
    }

    public void setCity_status(int status) {
        city_status.setImageResource(Utils.setIcon(status));
    }

    public TextView getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name.setText(city_name);
    }

    public View getRootView() {
        return  this.rootView;
    }
}
