package com.facebook.ads;

import androidx.annotation.Keep;

/* JADX INFO: loaded from: classes.dex */
@Keep
public interface AdListener {
    void onAdClicked(Ad ad);

    void onAdLoaded(Ad ad);

    void onError(Ad ad, AdError adError);

    void onLoggingImpression(Ad ad);
}
