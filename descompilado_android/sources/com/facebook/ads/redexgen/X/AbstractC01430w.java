package com.facebook.ads.redexgen.X;

import com.facebook.ads.RewardData;
import com.facebook.ads.internal.protocol.AdPlacementType;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.0w, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class AbstractC01430w implements C0O {
    public int B;
    public RewardData C;

    public abstract int A();

    public abstract boolean B();

    public final void C(int appOrientation) {
        this.B = appOrientation;
    }

    public final void D(RewardData rewardData) {
        this.C = rewardData;
    }

    @Override // com.facebook.ads.redexgen.X.C0O
    public final AdPlacementType HC() {
        return AdPlacementType.REWARDED_VIDEO;
    }
}
