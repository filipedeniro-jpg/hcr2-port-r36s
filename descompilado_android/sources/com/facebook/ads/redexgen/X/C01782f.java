package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.RewardedAdListener;
import java.lang.ref.WeakReference;
import java.util.EnumSet;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.2f, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C01782f {
    public long B = -1;

    @Nullable
    public InterstitialAdListener C;
    public final Context D;
    public String E;
    public EnumSet<CacheFlag> F;

    @Nullable
    public String G;

    @Nullable
    public String H;
    public final String I;

    @Nullable
    public RewardedAdListener J;

    @Nullable
    private InterstitialAd K;
    private WeakReference<InterstitialAd> L;

    public C01782f(Context appContext, @Nullable InterstitialAd interstitialAd, String placementId) {
        this.D = appContext;
        this.I = placementId;
        this.K = interstitialAd;
        this.L = new WeakReference<>(interstitialAd);
    }

    @Nullable
    public final InterstitialAd A() {
        return this.K != null ? this.K : this.L.get();
    }

    public final void B(@Nullable InterstitialAd ad) {
        if (ad == null && !JA.I(this.D)) {
            return;
        }
        this.K = ad;
    }
}
