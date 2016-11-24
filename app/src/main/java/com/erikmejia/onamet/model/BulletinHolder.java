package com.erikmejia.onamet.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.erikmejia.onamet.R;

/**
 * Created by erik on 11/13/16.
 */

public class BulletinHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private TextView content;
    private TextView date;

    public BulletinHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.bulletins_title_text);
        content = (TextView) itemView.findViewById(R.id.bulletins_description_text);
        date = (TextView) itemView.findViewById(R.id.bulletins_date_text);

    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public TextView getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content.setText(content);
    }

    public TextView getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date.setText(date);
    }
}
