package com.fingersoft.hcr2.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import androidx.core.app.NotificationCompat;
import com.fingersoft.hcr2.AppActivity;
import com.fingersoft.hcr2.Log;
import com.fingersoft.hcr2.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/* JADX INFO: loaded from: classes.dex */
public class FirebaseMessageReceiver extends FirebaseMessagingService {
    public static final String FIREBASE_NOTIFICATION_PAYLOAD_KEY = "fb_payload";
    private static final String TAG = "hcr2 - FirebaseMsg";

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage) {
        try {
            if (remoteMessage.getData().size() <= 0 || !remoteMessage.getData().containsKey(FIREBASE_NOTIFICATION_PAYLOAD_KEY)) {
                return;
            }
            sendNotification(remoteMessage.getNotification().getBody(), remoteMessage.getData().get(FIREBASE_NOTIFICATION_PAYLOAD_KEY));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendNotification(String str, String str2) {
        Log.d(TAG, "Creating notification with message: " + str + " and payload: " + str2);
        Intent intent = new Intent(this, (Class<?>) AppActivity.class);
        intent.putExtra(FIREBASE_NOTIFICATION_PAYLOAD_KEY, str2);
        intent.addFlags(67108864);
        ((NotificationManager) getSystemService("notification")).notify(0, new NotificationCompat.Builder(this).setSmallIcon(R.mipmap.ic_launcher).setContentTitle("Hill Climb Racing 2").setContentText(str).setAutoCancel(true).setSound(RingtoneManager.getDefaultUri(2)).setContentIntent(PendingIntent.getActivity(this, 0, intent, 1073741824)).build());
    }
}
