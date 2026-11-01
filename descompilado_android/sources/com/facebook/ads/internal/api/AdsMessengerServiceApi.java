package com.facebook.ads.internal.api;

import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Keep;

/* JADX INFO: loaded from: classes.dex */
@Keep
public interface AdsMessengerServiceApi {
    IBinder onBind(Intent intent);

    void onCreate();

    void onDestroy();
}
