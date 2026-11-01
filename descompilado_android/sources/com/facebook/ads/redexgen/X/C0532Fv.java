package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.internal.api.AudienceNetworkAdsApi;
import com.facebook.ads.internal.settings.MultithreadedBundleWrapper;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Fv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0532Fv implements AudienceNetworkAdsApi {
    @Override // com.facebook.ads.internal.api.AudienceNetworkAdsApi
    public final int getAdFormatForPlacement(String placementId) {
        return C0588Ia.E(placementId).intValue();
    }

    @Override // com.facebook.ads.internal.api.AudienceNetworkAdsApi
    public final String getAdsProcessName() {
        I6.B = true;
        return ":adnw";
    }

    @Override // com.facebook.ads.internal.api.AudienceNetworkAdsApi
    public final void initialize(Context context, @Nullable MultithreadedBundleWrapper initSettings, @Nullable AudienceNetworkAds.InitListener initListener) {
        C0588Ia.F(context, initSettings, initListener);
    }

    @Override // com.facebook.ads.internal.api.AudienceNetworkAdsApi
    public final boolean isInAdsProcess(Context context) {
        I6.B = true;
        String strB = I6.B(context);
        return !TextUtils.isEmpty(strB) && strB.endsWith(":adnw");
    }

    @Override // com.facebook.ads.internal.api.AudienceNetworkAdsApi
    public final boolean isInitialized() {
        return C0588Ia.H();
    }
}
