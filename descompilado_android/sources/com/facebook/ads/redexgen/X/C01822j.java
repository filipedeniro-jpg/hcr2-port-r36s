package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.ads.RewardData;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
import java.lang.ref.WeakReference;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.2j, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C01822j {

    @Nullable
    public RewardedVideoAdListener C;
    public final Context D;

    @Nullable
    public String E;

    @Nullable
    public String F;
    public boolean G;

    @Nullable
    public String H;
    public final String I;

    @Nullable
    public RewardData J;

    @Nullable
    private RewardedVideoAd L;
    private WeakReference<RewardedVideoAd> M;
    public int K = -1;
    public long B = -1;

    public C01822j(Context appContext, String placementId, @Nullable RewardedVideoAd rewardedVideoAd) {
        this.D = appContext;
        this.I = placementId;
        this.L = rewardedVideoAd;
        this.M = new WeakReference<>(rewardedVideoAd);
    }

    @Nullable
    public final RewardedVideoAd A() {
        return this.L != null ? this.L : this.M.get();
    }

    public final void B(@Nullable RewardedVideoAd ad) {
        if (ad == null && !JA.I(this.D)) {
            return;
        }
        this.L = ad;
    }
}
