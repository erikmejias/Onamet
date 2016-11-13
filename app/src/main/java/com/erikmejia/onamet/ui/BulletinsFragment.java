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
import com.erikmejia.onamet.model.BulletinHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    private DatabaseReference bulletinsReference;
    private FirebaseRecyclerAdapter firebaseAdapter;

    public BulletinsFragment() {
//        required empty constructor.
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bulletinsReference = FirebaseDatabase.getInstance().getReference("bulletins");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.bulletins_layout, container, false);

        bulletinsList = (RecyclerView)
                rootView.findViewById(R.id.bulletins_recyler_list);

        bulletinsList.setHasFixedSize(true);

        firebaseAdapter = new FirebaseRecyclerAdapter<Bulletin, BulletinHolder>(
                Bulletin.class,
                R.layout.bulletin_item,
                BulletinHolder.class,
                bulletinsReference
        ) {
            @Override
            protected void populateViewHolder(BulletinHolder viewHolder, Bulletin model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setContent(model.getDescription());
                viewHolder.setDate(model.getDate());
            }
        };

        if (container != null) {
            bulletinsList.setLayoutManager(new LinearLayoutManager(container.getContext()));
        }

        bulletinsList.setAdapter(firebaseAdapter);

        return rootView;
    }

}
