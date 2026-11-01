package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.UiThread;
import com.facebook.ads.ExtraHints;
import com.facebook.ads.RewardData;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
import com.facebook.ads.internal.api.RewardedVideoAdApi;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Gi, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
@UiThread
public final class C0545Gi implements RewardedVideoAdApi {
    private final RewardedVideoAd B;
    private final C01812i C;
    private final C01822j D;

    public C0545Gi(Context context, String placementID, RewardedVideoAd ad) {
        this.B = ad;
        this.D = new C01822j(context.getApplicationContext(), placementID, this.B);
        this.C = new C01812i(this.D);
    }

    private void B(String bidPayload, boolean failOnCacheFailure) {
        this.C.K(this.B, bidPayload, failOnCacheFailure);
    }

    @Override // com.facebook.ads.internal.api.RewardedVideoAdApi, com.facebook.ads.Ad
    public final void destroy() {
        C0642Kd.B("destroy", "Rewarded video ad destroyed");
        this.C.C();
    }

    public final void finalize() {
        this.C.G();
    }

    @Override // com.facebook.ads.internal.api.RewardedVideoAdApi, com.facebook.ads.Ad
    public final String getPlacementId() {
        return this.D.I;
    }

    @Override // com.facebook.ads.internal.api.RewardedVideoAdApi
    public final int getVideoDuration() {
        return this.D.K;
    }

    @Override // com.facebook.ads.internal.api.RewardedVideoAdApi, com.facebook.ads.Ad
    public final boolean isAdInvalidated() {
        return this.C.L();
    }

    @Override // com.facebook.ads.internal.api.RewardedVideoAdApi
    public final boolean isAdLoaded() {
        return this.C.M();
    }

    @Override // com.facebook.ads.internal.api.RewardedVideoAdApi, com.facebook.ads.Ad
    public final void loadAd() {
        C0642Kd.B("loadAd", "Rewarded video ad load requested");
        B(null, true);
    }

    @Override // com.facebook.ads.internal.api.RewardedVideoAdApi
    public final void loadAd(boolean z) {
        C0642Kd.B("loadAd", "Rewarded video ad load requested");
        B(null, z);
    }

    @Override // com.facebook.ads.internal.api.RewardedVideoAdApi, com.facebook.ads.Ad
    public final void loadAdFromBid(String str) {
        C0642Kd.B("loadAdFromBid", "Rewarded video ad load requested");
        B(str, true);
    }

    @Override // com.facebook.ads.internal.api.RewardedVideoAdApi
    public final void loadAdFromBid(String str, boolean z) {
        C0642Kd.B("loadAdFromBid", "Rewarded video ad load requested");
        B(str, z);
    }

    @Override // com.facebook.ads.internal.api.RewardedVideoAdApi
    public final void setAdListener(RewardedVideoAdListener adListener) {
        this.D.C = adListener;
    }

    @Override // com.facebook.ads.internal.api.RewardedVideoAdApi, com.facebook.ads.Ad
    public final void setExtraHints(ExtraHints extraHints) {
        this.D.F = extraHints.getHints();
        this.D.H = extraHints.getMediationData();
    }

    @Override // com.facebook.ads.internal.api.RewardedVideoAdApi
    public final void setRewardData(RewardData rewardData) {
        this.C.N(rewardData);
    }

    @Override // com.facebook.ads.internal.api.RewardedVideoAdApi
    public final boolean show() {
        C0642Kd.B("show", "Rewarded video ad show called");
        return show(-1);
    }

    @Override // com.facebook.ads.internal.api.RewardedVideoAdApi
    public final boolean show(int i) {
        C0642Kd.B("show", "Rewarded video ad show called");
        return this.C.O(this.B, i);
    }
}
