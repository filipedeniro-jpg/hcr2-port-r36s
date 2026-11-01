package com.applovin.impl.sdk.ad;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;

/* JADX INFO: loaded from: classes.dex */
public final class i implements AppLovinAd {
    public String a() {
        return "<html><head></head><body></body></html>";
    }

    @Override // com.applovin.sdk.AppLovinAd
    public long getAdIdNumber() {
        return 0L;
    }

    @Override // com.applovin.sdk.AppLovinAd
    public String getAdValue(String str) {
        return null;
    }

    @Override // com.applovin.sdk.AppLovinAd
    public AppLovinAdSize getSize() {
        return AppLovinAdSize.BANNER;
    }

    @Override // com.applovin.sdk.AppLovinAd
    public AppLovinAdType getType() {
        return AppLovinAdType.REGULAR;
    }

    @Override // com.applovin.sdk.AppLovinAd
    public String getZoneId() {
        return null;
    }

    @Override // com.applovin.sdk.AppLovinAd
    public boolean isVideoAd() {
        return false;
    }
}
