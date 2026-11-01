package com.facebook.ads.internal.api;

import android.content.res.Configuration;
import androidx.annotation.Keep;
import androidx.annotation.UiThread;

/* JADX INFO: loaded from: classes.dex */
@Keep
@UiThread
public interface AdViewParentApi {
    void onConfigurationChanged(Configuration configuration);
}
