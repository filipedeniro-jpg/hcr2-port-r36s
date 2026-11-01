package com.facebook.ads.internal.api;

import android.content.res.Configuration;
import androidx.annotation.Keep;
import androidx.annotation.UiThread;
import com.facebook.ads.Ad;
import com.facebook.ads.AdListener;
import com.facebook.ads.ExtraHints;

/* JADX INFO: loaded from: classes.dex */
@Keep
@UiThread
public interface AdViewApi extends AdViewParentApi, Ad {
    @Override // com.facebook.ads.internal.api.AdViewParentApi
    void onConfigurationChanged(Configuration configuration);

    void setAdListener(AdListener adListener);

    @Override // com.facebook.ads.Ad
    void setExtraHints(ExtraHints extraHints);
}
