package com.google.ads.mediation.nend;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public class NendExtrasBundleBuilder {
    private NendAdapter.InterstitialType interstitialType;
    private String userId;

    public NendExtrasBundleBuilder setUserId(String str) {
        this.userId = str;
        return this;
    }

    public NendExtrasBundleBuilder setInterstitialType(NendAdapter.InterstitialType interstitialType) {
        this.interstitialType = interstitialType;
        return this;
    }

    public Bundle build() {
        Bundle bundle = new Bundle();
        bundle.putString("key_user_id", this.userId);
        bundle.putSerializable("key_interstitial_type", this.interstitialType);
        return bundle;
    }
}
