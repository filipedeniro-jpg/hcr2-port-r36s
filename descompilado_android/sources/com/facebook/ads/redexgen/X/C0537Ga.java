package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.NativeAdsManager;
import com.facebook.ads.internal.api.NativeAdsManagerApi;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ga, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0537Ga implements NativeAdsManagerApi {
    private static final String L = NativeAdsManager.class.getSimpleName();
    private final Context B;
    private AnonymousClass07 C;
    private String E;
    private NativeAdsManager.Listener G;
    private final List<NativeAd> H;
    private final int I;
    private final String J;
    private int D = -1;
    private boolean F = false;
    private boolean K = false;

    @SuppressLint({"CatchGeneralException"})
    public C0537Ga(Context context, String placementId, int numAdsRequested) {
        this.B = context;
        this.J = placementId;
        this.I = Math.max(numAdsRequested, 0);
        this.H = new ArrayList(numAdsRequested);
        try {
            CookieManager.getInstance();
            if (Build.VERSION.SDK_INT < 21) {
                CookieSyncManager.createInstance(context);
            }
        } catch (Exception e) {
            Log.w(L, "Failed to initialize CookieManager.", e);
        }
    }

    @Override // com.facebook.ads.internal.api.NativeAdsManagerApi
    public final void disableAutoRefresh() {
        this.K = true;
        if (this.C != null) {
            this.C.B();
        }
    }

    @Override // com.facebook.ads.internal.api.NativeAdsManagerApi
    public final int getUniqueNativeAdCount() {
        return this.H.size();
    }

    @Override // com.facebook.ads.internal.api.NativeAdsManagerApi
    public final boolean isLoaded() {
        return this.F;
    }

    @Override // com.facebook.ads.internal.api.NativeAdsManagerApi
    public final void loadAds() {
        loadAds(NativeAdBase.MediaCacheFlag.ALL);
    }

    @Override // com.facebook.ads.internal.api.NativeAdsManagerApi
    public final void loadAds(NativeAdBase.MediaCacheFlag mediaCacheFlag) {
        KW kw = KW.NATIVE_UNKNOWN;
        int i = this.I;
        if (this.C != null) {
            this.C.A();
        }
        this.C = new AnonymousClass07(this.B, this.J, kw, null, i);
        if (this.K) {
            this.C.B();
        }
        this.C.E(this.E);
        this.C.F(new GZ(this, mediaCacheFlag));
        this.C.C();
    }

    @Override // com.facebook.ads.internal.api.NativeAdsManagerApi
    public final NativeAd nextNativeAd() {
        if (this.H.size() == 0) {
            return null;
        }
        int i = this.D;
        this.D = i + 1;
        List<NativeAd> list = this.H;
        int pos = this.H.size();
        NativeAd nativeAd = list.get(i % pos);
        int pos2 = this.H.size();
        if (i < pos2) {
            return nativeAd;
        }
        return new NativeAd(this.B, nativeAd);
    }

    @Override // com.facebook.ads.internal.api.NativeAdsManagerApi
    public final void setExtraHints(String extraHints) {
        this.E = extraHints;
    }

    @Override // com.facebook.ads.internal.api.NativeAdsManagerApi
    public final void setListener(NativeAdsManager.Listener listener) {
        this.G = listener;
    }
}
