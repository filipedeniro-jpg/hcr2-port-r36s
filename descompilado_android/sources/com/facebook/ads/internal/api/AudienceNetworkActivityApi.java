package com.facebook.ads.internal.api;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MotionEvent;
import androidx.annotation.Keep;
import androidx.annotation.UiThread;

/* JADX INFO: loaded from: classes.dex */
@Keep
@UiThread
public interface AudienceNetworkActivityApi {
    void finish();

    void onBackPressed();

    void onConfigurationChanged(Configuration configuration);

    void onCreate(Bundle bundle);

    void onDestroy();

    void onPause();

    void onResume();

    void onSaveInstanceState(Bundle bundle);

    void onStart();

    boolean onTouchEvent(MotionEvent motionEvent);
}
