package com.facebook.ads;

import androidx.annotation.Keep;

/* JADX INFO: loaded from: classes.dex */
@Keep
public interface Ad {
    void destroy();

    String getPlacementId();

    boolean isAdInvalidated();

    void loadAd();

    void loadAdFromBid(String str);

    void setExtraHints(ExtraHints extraHints);
}
