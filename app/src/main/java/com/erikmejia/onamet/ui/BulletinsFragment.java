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
import com.erikmejia.onamet.model.Bulletin;
import com.erikmejia.onamet.model.BulletinsAdapter;
import com.erikmejia.onamet.model.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erik on 9/4/16.
 *
 * Fragment responsible of displaying bulletins user interface.
 */

public class BulletinsFragment extends Fragment {

    private String[] demoData;
    private List<Bulletin> bulletins;

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

        loadDemoData();
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

        BulletinsAdapter bulletinsAdapter = new BulletinsAdapter(bulletins);
        bulletinsList.setAdapter(bulletinsAdapter);

        return rootView;
    }

    public void loadDemoData() {
//        Initialize a forecast objects holder.
        bulletins = new ArrayList<Bulletin>();

        Bulletin bulletin = new Bulletin("Peligro de extensiones de agua",
                "Lorem ipsum dolor sit amet. Ip dolor ipsum lot of trouble sitting in the dinning table of Frank Abagnale Jr. " +
                        "The Guy that almost breakall of it.",
                "Oct 18", 0);
        Bulletin bulletin1 = new Bulletin("Vaguada afectará el país",
                "Lorem ipsum dolor sit amet. Ip dolor ipsum lot of trouble sitting in the dinning table of Frank Abagnale Jr. " +
                        "The Guy that almost breakall of it.",
                "Oct 17", 1);
        Bulletin bulletin2 = new Bulletin("Deslizamientos provincia Espaillat",
                "Lorem ipsum dolor sit amet. Ip dolor ipsum lot of trouble sitting in the dinning table of Frank Abagnale Jr. " +
                        "The Guy that almost breakall of it.",
                "Oct 16", 2);
        Bulletin bulletin3 = new Bulletin("Prevén inicio de temporada ciclonica",
                "Lorem ipsum dolor sit amet. Ip dolor ipsum lot of trouble sitting in the dinning table of Frank Abagnale Jr. " +
                        "The Guy that almost breakall of it.",
                "Oct 14", 3);
        Bulletin bulletin4 = new Bulletin("Tormenta Allison se desvía",
                "Lorem ipsum dolor sit amet. Ip dolor ipsum lot of trouble sitting in the dinning table of Frank Abagnale Jr. " +
                        "The Guy that almost breakall of it.",
                "Oct 11", 4);
        Bulletin bulletin5 = new Bulletin("Búsqueda de sistemas de navegación",
                "Lorem ipsum dolor sit amet. Ip dolor ipsum lot of trouble sitting in the dinning table of Frank Abagnale Jr. " +
                        "The Guy that almost breakall of it.",
                "Oct 08", 1);
        Bulletin bulletin6 = new Bulletin("Fallece exdirector ONAMET",
                "Lorem ipsum dolor sit amet. Ip dolor ipsum lot of trouble sitting in the dinning table of Frank Abagnale Jr. " +
                        "The Guy that almost breakall of it.",
                "Oct 08", 3);
        Bulletin bulletin7 = new Bulletin("Onamet y COE firman acuerdo",
                "Lorem ipsum dolor sit amet. Ip dolor ipsum lot of trouble sitting in the dinning table of Frank Abagnale Jr. " +
                        "The Guy that almost breakall of it.",
                "Oct 08", 2);
        Bulletin bulletin8 = new Bulletin("Tipos de boletines en caso de desastre",
                "Lorem ipsum dolor sit amet. Ip dolor ipsum lot of trouble sitting in the dinning table of Frank Abagnale Jr. " +
                        "The Guy that almost breakall of it.",
                "Oct 05", 0);
        Bulletin bulletin9 = new Bulletin("Temporada ciclonica 2016",
                "Lorem ipsum dolor sit amet. Ip dolor ipsum lot of trouble sitting in the dinning table of Frank Abagnale Jr. " +
                        "The Guy that almost breakall of it.Ip dolor ipsum lot of trouble sitting in the dinning table of Frank Abagnale Jr." +
                        "The Guy that almost breakall of it.",
                "Oct 04", 4);
        Bulletin bulletin10 = new Bulletin("Onamet asiste simulacro en China",
                "Lorem ipsum dolor sit amet. Ip dolor ipsum lot of trouble sitting in the dinning table of Frank Abagnale Jr. " +
                        "The Guy that almost breakall of it.",
                "Oct 03", 1);

        bulletins.add(bulletin);
        bulletins.add(bulletin1);
        bulletins.add(bulletin2);
        bulletins.add(bulletin3);
        bulletins.add(bulletin4);
        bulletins.add(bulletin5);
        bulletins.add(bulletin6);
        bulletins.add(bulletin7);
        bulletins.add(bulletin8);
        bulletins.add(bulletin9);
        bulletins.add(bulletin10);

    }

}
