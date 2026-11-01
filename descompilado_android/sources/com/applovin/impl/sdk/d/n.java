package com.applovin.impl.sdk.d;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class n extends m {
    private final int a;
    private final AppLovinNativeAdLoadListener c;

    public n(String str, int i, com.applovin.impl.sdk.h hVar, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        super(com.applovin.impl.sdk.ad.d.b(str, hVar), null, "TaskFetchNextNativeAd", hVar);
        this.a = i;
        this.c = appLovinNativeAdLoadListener;
    }

    @Override // com.applovin.impl.sdk.d.m, com.applovin.impl.sdk.d.a
    public com.applovin.impl.sdk.c.i a() {
        return com.applovin.impl.sdk.c.i.o;
    }

    @Override // com.applovin.impl.sdk.d.m
    protected a a(JSONObject jSONObject) {
        return new u(jSONObject, this.b, this.c);
    }

    @Override // com.applovin.impl.sdk.d.m
    protected void a(int i) {
        AppLovinNativeAdLoadListener appLovinNativeAdLoadListener = this.c;
        if (appLovinNativeAdLoadListener != null) {
            appLovinNativeAdLoadListener.onNativeAdsFailedToLoad(i);
        }
    }

    @Override // com.applovin.impl.sdk.d.m
    Map<String, String> b() {
        Map<String, String> mapB = super.b();
        mapB.put("slot_count", Integer.toString(this.a));
        return mapB;
    }

    @Override // com.applovin.impl.sdk.d.m
    protected String h() {
        return ((String) this.b.a(com.applovin.impl.sdk.b.b.aE)) + "4.0/nad";
    }

    @Override // com.applovin.impl.sdk.d.m
    protected String i() {
        return ((String) this.b.a(com.applovin.impl.sdk.b.b.aF)) + "4.0/nad";
    }
}
