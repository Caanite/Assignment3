//Programmed by Sam Spark (18040422) and Sarbjot Singh (17190067)
package com.example.myapplicationas3;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DateNotificationBroadcast extends BroadcastReceiver {
    public static String NOTIFICATION_ID = "notification-id";
    public static String NOTIFICATION = "notification";

    public void onReceive(Context context, Intent intent)
    {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = intent.getParcelableExtra(NOTIFICATION);    //NOTIFICATION and NOTIFICATION is sent as extra from
        int id = intent.getIntExtra(NOTIFICATION_ID, 0);            //notification function
        notificationManager.notify(id, notification);                   //Create Notification
    }
}
