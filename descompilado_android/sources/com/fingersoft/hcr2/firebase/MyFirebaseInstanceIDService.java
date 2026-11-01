package com.fingersoft.hcr2.firebase;

import com.fingersoft.hcr2.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;

/* JADX INFO: loaded from: classes.dex */
public class MyFirebaseInstanceIDService extends FirebaseMessagingService {
    private static final String TAG = "hcr2 - FirebaseId";
    private static String token;

    public void onNewToken() {
        String token2 = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + token2);
        token = token2;
    }

    public static String getToken() {
        return token;
    }
}
