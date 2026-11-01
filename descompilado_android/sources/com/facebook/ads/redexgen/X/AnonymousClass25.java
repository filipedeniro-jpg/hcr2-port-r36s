package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.facebook.ads.AdSettings;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.internal.protocol.AdPlacementType;
import java.util.EnumSet;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.25, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class AnonymousClass25 {
    public final KV B;
    public final KW C;
    public int D;
    public final EnumSet<CacheFlag> E;

    @Nullable
    public String F;
    public boolean G;

    @Nullable
    public String H;
    public final String I;

    @Nullable
    public K0 J;
    private final AdPlacementType K;
    private final int L;

    public AnonymousClass25(String mPlacementId, KW mAdTemplate, AdPlacementType mAdPlacementType, KV mAdSize, int mNumAdRequested) {
        this(mPlacementId, mAdTemplate, mAdPlacementType, mAdSize, mNumAdRequested, EnumSet.of(CacheFlag.NONE));
    }

    public AnonymousClass25(String placementId, KW adTemplate, AdPlacementType adPlacementType, KV adSize, int numAdRequested, EnumSet<CacheFlag> enumSet) {
        this.I = placementId;
        this.K = adPlacementType;
        this.B = adSize;
        this.L = numAdRequested;
        this.E = enumSet;
        this.C = adTemplate;
        this.D = -1;
    }

    @WorkerThread
    public final C0647Ki A(Context context, C0640Kb bidPayload) {
        M9 m9;
        InterfaceC0596Ii interfaceC0596IiB = C0597Ij.B().B(context, false);
        String str = this.I;
        if (this.B != null) {
            m9 = new M9(this.B.getHeight(), this.B.getWidth());
        } else {
            m9 = null;
        }
        return new C0647Ki(context, interfaceC0596IiB, str, m9, this.C, AdSettings.getTestAdType() != AdSettings.TestAdType.DEFAULT ? AdSettings.getTestAdType().getAdTypeString() : null, this.L, AdSettings.isTestMode(context), AdSettings.isChildDirected(), bidPayload, L0.E(JA.f(context)), this.F);
    }

    public final AdPlacementType B() {
        if (this.K != null) {
            return this.K;
        }
        if (this.B == null) {
            return AdPlacementType.NATIVE;
        }
        if (this.B == KV.INTERSTITIAL) {
            return AdPlacementType.INTERSTITIAL;
        }
        return AdPlacementType.BANNER;
    }

    public final void C(int appOrientation) {
        this.D = appOrientation;
    }

    public final void D(@Nullable String extraHints) {
        this.F = extraHints;
    }

    public final void E(boolean failOnCacheFailure) {
        this.G = failOnCacheFailure;
    }

    public final void F(@Nullable K0 mediaCacheFlag) {
        this.J = mediaCacheFlag;
    }

    public final void G(@Nullable String mediationData) {
        this.H = mediationData;
    }
}
