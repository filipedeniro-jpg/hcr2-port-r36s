package com.facebook.ads.internal.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.facebook.ads.internal.api.AdsMessengerServiceApi;
import com.facebook.ads.internal.dynamicloading.DynamicLoaderFactory;

/* JADX INFO: loaded from: classes.dex */
@Keep
@UiThread
public class AdsMessengerService extends Service {
    private AdsMessengerServiceApi mAdsMessengerServiceApi;

    @Override // android.app.Service
    public void onCreate() {
        this.mAdsMessengerServiceApi = DynamicLoaderFactory.makeLoader(this).createAdsMessengerServiceApi(this);
        this.mAdsMessengerServiceApi.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.mAdsMessengerServiceApi.onDestroy();
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return this.mAdsMessengerServiceApi.onBind(intent);
    }
}
