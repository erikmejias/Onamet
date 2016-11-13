package com.erikmejia.onamet.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.erikmejia.onamet.R;
import com.erikmejia.onamet.model.Bulletin;
import com.erikmejia.onamet.model.BulletinsAdapter;
import com.erikmejia.onamet.model.OnBulletinItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erik on 9/4/16.
 *
 * Fragment responsible of displaying bulletins user interface.
 */

public class BulletinsFragment extends Fragment {

    private static final String TAG = BulletinsFragment.class.getSimpleName();

    private List<Bulletin> bulletins;
    RecyclerView bulletinsList;

    public BulletinsFragment() {
//        required empty constructor.
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        loadDemoData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.bulletins_layout, container, false);

        bulletinsList = (RecyclerView)
                rootView.findViewById(R.id.bulletins_recyler_list);

        bulletinsList.setHasFixedSize(true);

        if (container != null) {
            bulletinsList.setLayoutManager(new LinearLayoutManager(container.getContext()));
        }

        BulletinsAdapter bulletinsAdapter = new BulletinsAdapter(bulletins, new OnBulletinItemClickListener() {
            @Override
            public void onItemClicked(Bulletin bulletinItem) {
                Intent intent;
                intent = new Intent(getContext(), BulletinsDetails.class);
                startActivity(intent);
            }
        });
        bulletinsList.setAdapter(bulletinsAdapter);

        return rootView;
    }

    public void loadDemoData() {
//        Initialize a forecast objects holder.
        bulletins = new ArrayList<>();

        Bulletin bulletin = new Bulletin("Peligro de extensiones de agua",
                "Lorem ipsum dolor sit amet. Ip dolor ipsum lot of trouble sitting in the dinning table of Frank Abagnale Jr. " +
                        "The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it.",
                "Oct 18 - 08:15 AM");
        Bulletin bulletin1 = new Bulletin("Vaguada afectará el país",
                "Lorem ipsum dolor sit amet. Ip dolor ipsum lot of trouble sitting in the dinning table of Frank Abagnale Jr. " +
                        "The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        "Lorem ipsum dolor sit amet. Ip dolor ipsum lot of trouble sitting in the dinning table of Frank Abagnale Jr. " +
                "The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it.",
                "Oct 17 - 08:15 AM");
        Bulletin bulletin2 = new Bulletin("Deslizamientos provincia Espaillat",
                "Lorem ipsum dolor sit amet. Ip dolor ipsum lot of trouble sitting in the dinning table of Frank Abagnale Jr. " +
                        "The Guy that almost breakall of it.\n\n" +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it.\n" +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it.",
                "Oct 16 - 01:13 PM");
        Bulletin bulletin3 = new Bulletin("Prevén inicio de temporada ciclonica",
                "Lorem ipsum dolor sit amet. Ip dolor ipsum lot of trouble sitting in the dinning table of Frank Abagnale Jr. " +
                        "The Guy that almost breakall of it.",
                "Oct 14 - 09:12 AM");
        Bulletin bulletin4 = new Bulletin("Tormenta Allison se desvía",
                "Lorem ipsum dolor sit amet. Ip dolor ipsum lot of trouble sitting in the dinning table of Frank Abagnale Jr. " +
                        "The Guy that almost breakall of it.",
                "Oct 11 - 07:10 AM");
        Bulletin bulletin5 = new Bulletin("Búsqueda de sistemas de navegación",
                "Lorem ipsum dolor sit amet. Ip dolor ipsum lot of trouble sitting in the dinning table of Frank Abagnale Jr. " +
                        "The Guy that almost breakall of it.",
                "Oct 08 - 08:15 AM");
        Bulletin bulletin6 = new Bulletin("Fallece exdirector ONAMET",
                "Lorem ipsum dolor sit amet. Ip dolor ipsum lot of trouble sitting in the dinning table of Frank Abagnale Jr." +
                        " The Guy that almost breakall of it.",
                "Oct 08 - 11:12 AM");
        Bulletin bulletin7 = new Bulletin("Onamet y COE firman acuerdo para seguir promoviendo el desarrollo de nuevas tecnologias",
                "Lorem ipsum dolor sit amet. Ip dolor ipsum lot of trouble sitting in the dinning table of Frank Abagnale Jr. " +
                        "The Guy that almost breakall of it.",
                "Oct 08 - 03:15 PM");
        Bulletin bulletin8 = new Bulletin("Tipos de boletines en caso de desastre",
                "Lorem ipsum dolor sit amet. Ip dolor ipsum lot of trouble sitting in the dinning table of Frank Abagnale Jr. " +
                        "The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it." +
                        " The Guy that almost breakall of it.",
                "Oct 05 - 08:15 AM");
        Bulletin bulletin9 = new Bulletin("Temporada ciclonica 2016",
                "Lorem ipsum dolor sit amet. Ip dolor ipsum lot of trouble sitting in the dinning table of Frank Abagnale Jr. " +
                        "The Guy that almost breakall of it.Ip dolor ipsum lot of trouble sitting in the dinning table of Frank Abagnale Jr." +
                        "The Guy that almost breakall of it.",
                "Oct 04 - 08:15 AM");
        Bulletin bulletin10 = new Bulletin("Onamet asiste simulacro en China",
                "Lorem ipsum dolor sit amet. Ip dolor ipsum lot of trouble sitting in the dinning table of Frank Abagnale Jr. " +
                        "The Guy that almost breakall of it.",
                "Oct 03 - 08:15 AM");

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
