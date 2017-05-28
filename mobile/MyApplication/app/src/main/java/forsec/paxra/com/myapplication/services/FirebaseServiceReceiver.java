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

        Map<String, String> data = remoteMessage.getData();

        String title = data.get("title");
        String message = data.get("body");
        String image = data.get("image");


        sendNotification(title, message, image);


    }


    private void sendNotification( String title, String message, String image) {
        Intent intent = new Intent(this, CarPermissionActivity.class);
        intent.setData((Uri.parse("custom://"+System.currentTimeMillis())));
        intent.putExtra("Image", image);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,  17/* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_lock)
                .setLargeIcon(((BitmapDrawable) ContextCompat.getDrawable(this, R.mipmap.ic_launcher)).getBitmap())
                .setContentTitle("4SEC")
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(17, notificationBuilder.build());
    }

}
