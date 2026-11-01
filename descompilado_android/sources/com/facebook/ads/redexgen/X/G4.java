package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.UiThread;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.ExtraHints;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.RewardedAdListener;
import com.facebook.ads.internal.api.InterstitialAdApi;
import java.util.EnumSet;

/* JADX INFO: loaded from: assets/audience_network.dex */
@UiThread
public final class G4 implements InterstitialAdApi {
    private final InterstitialAd B;
    private final C01772e C;
    private final C01782f D;

    public G4(Context context, String placementId, InterstitialAd ad) {
        this.B = ad;
        this.D = new C01782f(context.getApplicationContext(), ad, placementId);
        this.C = new C01772e(this.D);
    }

    private void B(EnumSet<CacheFlag> enumSet, String bidPayload) {
        this.C.K(this.B, enumSet, bidPayload);
    }

    @Override // com.facebook.ads.Ad
    public final void destroy() {
        C0642Kd.B("destroy", "Interstitial ad destroyed");
        this.C.C();
    }

    public final void finalize() {
        this.C.G();
    }

    @Override // com.facebook.ads.Ad
    public final String getPlacementId() {
        return this.D.I;
    }

    @Override // com.facebook.ads.Ad
    public final boolean isAdInvalidated() {
        return this.C.L();
    }

    @Override // com.facebook.ads.internal.api.InterstitialAdApi
    public final boolean isAdLoaded() {
        return this.C.M();
    }

    @Override // com.facebook.ads.Ad
    public final void loadAd() {
        C0642Kd.B("loadAd", "Interstitial ad load requested");
        loadAd(CacheFlag.ALL);
    }

    @Override // com.facebook.ads.internal.api.InterstitialAdApi
    public final void loadAd(EnumSet<CacheFlag> enumSet) {
        C0642Kd.B("loadAd", "Interstitial ad load requested");
        B(enumSet, null);
    }

    @Override // com.facebook.ads.Ad
    public final void loadAdFromBid(String str) {
        C0642Kd.B("loadAdFromBid", "Interstitial ad load requested");
        B(CacheFlag.ALL, str);
    }

    @Override // com.facebook.ads.internal.api.InterstitialAdApi
    public final void loadAdFromBid(EnumSet<CacheFlag> enumSet, String str) {
        C0642Kd.B("loadAdFromBid", "Interstitial ad load requested");
        B(enumSet, str);
    }

    @Override // com.facebook.ads.internal.api.InterstitialAdApi
    public final void setAdListener(InterstitialAdListener adListener) {
        this.D.C = adListener;
    }

    @Override // com.facebook.ads.internal.api.InterstitialAdApi, com.facebook.ads.Ad
    public final void setExtraHints(ExtraHints extraHints) {
        this.D.G = extraHints.getHints();
        this.D.H = extraHints.getMediationData();
    }

    @Override // com.facebook.ads.internal.api.InterstitialAdApi
    public final void setRewardedAdListener(RewardedAdListener adListener) {
        this.D.J = adListener;
    }

    @Override // com.facebook.ads.internal.api.InterstitialAdApi
    public final boolean show() {
        C0642Kd.B("show", "Interstitial ad show called");
        return this.C.N(this.B);
    }
}
