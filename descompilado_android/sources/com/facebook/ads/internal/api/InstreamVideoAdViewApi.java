package com.facebook.ads.internal.api;

import android.os.Bundle;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.facebook.ads.Ad;
import com.facebook.ads.ExtraHints;
import com.facebook.ads.InstreamVideoAdListener;

/* JADX INFO: loaded from: classes.dex */
@Keep
public interface InstreamVideoAdViewApi extends Ad {
    @Override // com.facebook.ads.Ad
    void destroy();

    @Override // com.facebook.ads.Ad
    String getPlacementId();

    @Nullable
    Bundle getSaveInstanceState();

    @Override // com.facebook.ads.Ad
    boolean isAdInvalidated();

    boolean isAdLoaded();

    @Override // com.facebook.ads.Ad
    void loadAd();

    @Override // com.facebook.ads.Ad
    void loadAdFromBid(String str);

    void setAdListener(@Nullable InstreamVideoAdListener instreamVideoAdListener);

    @Override // com.facebook.ads.Ad
    void setExtraHints(ExtraHints extraHints);

    boolean show();
}
