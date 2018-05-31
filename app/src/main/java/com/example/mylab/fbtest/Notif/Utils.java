package com.example.mylab.fbtest.Notif;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Abins Shaji on 17/01/18.
 */

public class Utils {

    @TargetApi(Build.VERSION_CODES.O)
    public static String createNotifChannel(Context context )
    {
        NotificationManager notificationManager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        String channel_id="01";
        NotificationChannel channel = new NotificationChannel(channel_id, "my_channel",
                NotificationManager.IMPORTANCE_HIGH);
        channel.setDescription("my notication");
        channel.enableLights(true);
        notificationManager.createNotificationChannel(channel);
        return channel_id;


    }


}
