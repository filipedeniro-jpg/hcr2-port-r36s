package com.facebook.ads.redexgen.X;

import com.facebook.ads.internal.protocol.AdErrorType;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class K3 implements InterfaceC01280h {
    public final /* synthetic */ KF B;
    public final /* synthetic */ C01360p C;
    public final /* synthetic */ boolean D;

    public K3(KF this$0, C01360p c01360p, boolean z) {
        this.B = this$0;
        this.C = c01360p;
        this.D = z;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC01280h
    public final void dD() {
        if (this.B.B != null) {
            this.B.B.j();
            this.B.B = null;
        }
        if (this.B.D == null) {
            return;
        }
        this.B.D.yD(KQ.C(AdErrorType.CACHE_FAILURE_ERROR, "Failed to download a media."));
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC01280h
    public final void nD() {
        this.B.B = this.C;
        if (this.B.D == null) {
            return;
        }
        if (this.B.U.equals(K0.ALL) && !this.B.p()) {
            this.B.D.aE();
        }
        if (!this.D) {
            return;
        }
        this.B.D.PD();
    }
}
