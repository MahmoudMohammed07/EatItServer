package com.android.eatitserver.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.android.eatitserver.Model.Request;
import com.android.eatitserver.OrderStatus;
import com.android.eatitserver.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class ListenOrder extends Service implements ChildEventListener {

    FirebaseDatabase database;
    DatabaseReference orders;

    public ListenOrder() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        database = FirebaseDatabase.getInstance();
        orders = database.getReference("Requests");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        orders.addChildEventListener(this);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        Request request = dataSnapshot.getValue(Request.class);
        if (request.getStatus().equals("0")) {
            showNotification(dataSnapshot.getKey(), request);
        }
    }

    private void showNotification(String key, Request request) {
        Intent intent = new Intent(getBaseContext(), OrderStatus.class);
        PendingIntent contentPendingIntent = PendingIntent.getActivity(getBaseContext(), 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());

        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setTicker("Mahmoud")
                .setContentInfo("New Order")
                .setContentText("You have new order #" + key)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(contentPendingIntent);

        NotificationManager manager = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);

        int randomInt = new Random().nextInt(9999 - 1) + 1;
        manager.notify(randomInt, builder.build());
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
}
