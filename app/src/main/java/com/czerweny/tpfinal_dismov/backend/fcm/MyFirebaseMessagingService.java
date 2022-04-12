package com.czerweny.tpfinal_dismov.backend.fcm;

import android.os.Looper;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

import com.czerweny.tpfinal_dismov.backend.models.Notification;
import com.czerweny.tpfinal_dismov.backend.repositories.NotificationsRepository;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Date;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO(developer): Handle FCM messages here.
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

            String id = remoteMessage.getMessageId();
            String title = remoteMessage.getNotification().getTitle();
            String dateTime = (String) DateFormat.format("dd/MM/yyyy kk:mm", new Date(remoteMessage.getSentTime()));
            String message = remoteMessage.getNotification().getBody();

            Notification notification = new Notification(id, title, dateTime, message);

            NotificationsRepository.saveNotication(notification);

            Looper.prepare();
            Toast.makeText(getApplicationContext(), "Nueva notificación recibida.", Toast.LENGTH_SHORT).show();
            Looper.loop();
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
}
