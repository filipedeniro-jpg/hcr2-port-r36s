package com.facebook.ads;

import androidx.annotation.Keep;

/* JADX INFO: loaded from: classes.dex */
@Keep
public interface InterstitialAdListener extends AdListener {
    void onInterstitialDismissed(Ad ad);

    void onInterstitialDisplayed(Ad ad);
}
