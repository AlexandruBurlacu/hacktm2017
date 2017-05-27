package forsec.paxra.com.myapplication.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Set;

import forsec.paxra.com.myapplication.R;
import forsec.paxra.com.myapplication.activities.CarPermissionActivity;

/**
 * Created by iuriegaitur on 5/27/17.
 */

public class FirebaseServiceReceiver extends FirebaseMessagingService {

    private static final String TAG = "FCM Service";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO: Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
        Map<String, String> data = remoteMessage.getData();

        Set<String> messageType = data.keySet();

        String title = remoteMessage.getNotification().getTitle();
        String message = remoteMessage.getNotification().getBody();


        if (data.get("type") != null && data.get("type").equals("ARTICOL")) {
            sendNotification(data.get("id"), title, message);
        } else {
            sendNotification(title, message);
        }

    }

    private void sendNotification(String title, String message) {
        this.sendNotification(null, title, message);
    }


    private void sendNotification(String id, String title, String message) {
        Intent intent = new Intent(this, CarPermissionActivity.class);
        intent.setData((Uri.parse("custom://"+System.currentTimeMillis())));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,  17/* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_lock)
                .setLargeIcon(((BitmapDrawable) ContextCompat.getDrawable(this, R.mipmap.ic_launcher)).getBitmap())
                .setContentTitle("Agora")
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(17, notificationBuilder.build());
    }

}
