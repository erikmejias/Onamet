package com.erikmejia.onamet.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.erikmejia.onamet.MainActivity;
import com.erikmejia.onamet.R;
import com.erikmejia.onamet.model.Bulletin;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by erik on 11/17/16.
 */

public class FirebaseBackgroundService extends Service {

    private SharedPreferences getPrefs;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        getPrefs = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("bulletins");

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                boolean isFirstTime = getPrefs.getBoolean("isFirstTimeBulletin", true);

                if (isFirstTime) {
//                Do not notify, its the first time.
                    SharedPreferences.Editor e = getPrefs.edit();
                    e.putBoolean("isFirstTimeBulletin", false);
                    e.apply();
                } else {
                    notifyNewBulletin( dataSnapshot.getValue(Bulletin.class).getTitle() );
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
        });
    }

    /*
    * Fire a notification with the title of the new Bulletin
    * */
    public void notifyNewBulletin(String title){
        Context context = getApplicationContext();

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("Nuevo boletín informativo")
                        .setContentText(title);

        Intent resultIntent = new Intent(context, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);

        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager manager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(0, mBuilder.build());

    }
}
