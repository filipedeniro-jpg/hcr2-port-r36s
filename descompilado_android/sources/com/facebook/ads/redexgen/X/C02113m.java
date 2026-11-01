package com.facebook.ads.redexgen.X;

import java.util.EnumSet;
import java.util.List;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.3m, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C02113m {

    @InterfaceC01641r
    private final List<C02093k> B;
    private final C4S C;
    private final EnumSet<EnumC02053g> D;
    private final int E;

    public C02113m(int signalId, EnumSet<EnumC02053g> enumSet, C4S signalExecutor, @InterfaceC01641r List<C02093k> list) {
        this.E = signalId;
        this.D = enumSet;
        this.C = signalExecutor;
        this.B = list;
    }

    @InterfaceC01641r
    public final C4S A(EnumC02083j reputationTier) {
        if (B(reputationTier)) {
            return this.C;
        }
        return null;
    }

    public final EnumSet<EnumC02053g> B() {
        return this.D;
    }

    private boolean B(EnumC02083j reputationTier) {
        return C3O.E(reputationTier) && (this.D.contains(EnumC02053g.SUSPICIOUS_TIER) || this.D.contains(EnumC02053g.PARANOID_TIER) || this.D.contains(EnumC02053g.BENIGN_COLLECT_TIER));
    }

    public final int C() {
        return this.E;
    }
}
