package com.erikmejia.onamet.ui;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erikmejia.onamet.MainActivity;
import com.erikmejia.onamet.R;
import com.erikmejia.onamet.model.Bulletin;
import com.erikmejia.onamet.model.BulletinHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
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

    RecyclerView bulletinsList;
    private DatabaseReference bulletinsReference;
    private FirebaseRecyclerAdapter firebaseAdapter;
    private SharedPreferences getPrefs;

    public BulletinsFragment() {
//        required empty constructor.
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPrefs = PreferenceManager
                .getDefaultSharedPreferences(getActivity());

        bulletinsReference = FirebaseDatabase.getInstance().getReference("bulletins");
        bulletinsReference.addChildEventListener(childEventListener);
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

    /*
    * Fire a notification with the title of the new Bulletin
    * */
    public void notifyNewBulletin(String title){

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getActivity())
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("Nuevo boletín informativo")
                        .setContentText(title);

        Intent resultIntent = new Intent(getActivity(), MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getActivity());

        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager manager = (NotificationManager)
                getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(0, mBuilder.build());

    }

    ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            boolean isFirstTime = getPrefs.getBoolean("isFirstTimeBulletin", true);

            if (isFirstTime) {
//                Do not notify, its the first time.
                SharedPreferences.Editor e = getPrefs.edit();
                e.putBoolean("isFirstTimeBulletin", false);
                e.apply();
            } else {
//                notifyNewBulletin( dataSnapshot.getValue(Bulletin.class).getTitle() );
            }
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

}
