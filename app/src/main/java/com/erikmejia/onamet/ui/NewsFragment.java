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
import com.erikmejia.onamet.model.NewsAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;

/**
 * Created by erik on 9/4/16.
 *
 * Fragment responsible of displaying news information data.
 */

public class NewsFragment extends Fragment {

    private String[] demoData;

    public NewsFragment() {
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

        View rootView = inflater.inflate(R.layout.news_layout, container, false);

        RecyclerView bulletinsList = (RecyclerView)
                rootView.findViewById(R.id.news_recyler_list);
        bulletinsList.setHasFixedSize(true);
        bulletinsList.setLayoutManager(new LinearLayoutManager(container.getContext()));

        NewsAdapter newsAdapter = new NewsAdapter(demoData);
        bulletinsList.setAdapter(newsAdapter);

        NativeExpressAdView adView = (NativeExpressAdView) rootView.findViewById(R.id.newsAd);

        AdRequest request = new AdRequest.Builder()
                .addTestDevice("E0451870C934704914ACFF7D2E7F7F7F")
                .build();
        adView.loadAd(request); // Load ad into the view

        return rootView;
    }
}
