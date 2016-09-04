package com.erikmejia.onamet.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erikmejia.onamet.R;
import com.erikmejia.onamet.model.BulletinsAdapter;
import com.erikmejia.onamet.model.NewsAdapter;

/**
 * Created by erik on 9/4/16.
 *
 * Fragment responsible of displaying bulletins user interface.
 */

public class BulletinsFragment extends Fragment {

    private String[] demoData;

    public BulletinsFragment() {
//        required empty constructor.
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //        DEMO DATA for future forecasts
        demoData = new String[]{"today", "tomorrow", "Marcell", "Cindy",
                "MacBook Pro", "Alvin", "Eduardo", "Brayan", "Jorge", "Joel",
                "Jeissy", "David", "Daniel", "Fausto"};
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.bulletins_layout, container, false);

        RecyclerView bulletinsList = (RecyclerView)
                rootView.findViewById(R.id.bulletins_recyler_list);
        bulletinsList.setHasFixedSize(true);
        bulletinsList.setLayoutManager(new LinearLayoutManager(container.getContext()));

        BulletinsAdapter forecastsAdapter = new BulletinsAdapter(demoData);
        bulletinsList.setAdapter(forecastsAdapter);

        return rootView;
    }
}
