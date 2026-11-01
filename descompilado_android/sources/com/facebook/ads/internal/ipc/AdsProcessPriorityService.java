package com.facebook.ads.internal.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Messenger;
import androidx.annotation.Keep;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class AdsProcessPriorityService extends Service {
    private Messenger mMessenger;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mMessenger = new Messenger(new Handler());
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mMessenger.getBinder();
    }
}
