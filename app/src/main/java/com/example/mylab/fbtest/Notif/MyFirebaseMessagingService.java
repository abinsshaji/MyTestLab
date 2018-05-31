package com.example.mylab.fbtest.Notif;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.mylab.fbtest.R;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Abins Shaji on 17/01/18.
 */

public class MyFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    public static final String TAG = "message";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.e(TAG, "from " + remoteMessage.getFrom());
        if (remoteMessage.getData().size() > 0)
        {
            Log.e(TAG, "msg " + remoteMessage.getData());
            try {
                JSONObject jsonObject=new JSONObject(remoteMessage.getData().toString());
                buildNotifiction(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

        }
    }

    public void buildNotifiction(JSONObject jsonObject) throws JSONException {

        int mNotificatio_id = 101;


        JSONObject payload=jsonObject.getJSONObject("data");
        Log.e(TAG, "payload: "+payload.toString() );

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        inboxStyle.addLine("fqwdsadfafsdgafsdfasdfahsfdhasf");


        NotificationCompat.BigPictureStyle  bigPictureStyle=new NotificationCompat.BigPictureStyle()
                .bigPicture(getBitmapFromURL(payload.getString("image")));


        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(),"test")
                .setContentTitle(payload.getString("title"))
                .setStyle(bigPictureStyle)
                .setSmallIcon(R.mipmap.ic_launcher);


        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

// mNotificationId is a unique integer your app uses to identify the
// notification. For example, to cancel the notification, you can pass its ID
// number to NotificationManager.cancel().
        mNotificationManager.notify(mNotificatio_id, notification.build());

    }

    public Bitmap getBitmapFromURL(String strURL) {
        try {
            URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }
    }
}

