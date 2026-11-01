package com.facebook.ads.redexgen.X;

import com.facebook.ads.internal.protocol.AdPlacementType;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.0T, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0T {
    private static C0O B;

    public final C0O A(AdPlacementType placementType) {
        if (B != null) {
            return B;
        }
        switch (C0S.B[placementType.ordinal()]) {
            case 1:
                return new C0Z();
            case 2:
                return new C01340n();
            case 3:
                return new C01360p();
            case 4:
                return new C01360p() { // from class: com.facebook.ads.redexgen.X.0q
                    @Override // com.facebook.ads.redexgen.X.C01360p, com.facebook.ads.redexgen.X.C0O
                    public final AdPlacementType HC() {
                        return AdPlacementType.NATIVE_BANNER;
                    }
                };
            case 5:
                return new C01320l();
            case 6:
                return new C01440x();
            default:
                return null;
        }
    }
}
