package com.facebook.ads.redexgen.X;

import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Gu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class RunnableC0556Gu implements Runnable {
    public final /* synthetic */ RunnableC0557Gv B;
    public final /* synthetic */ AtomicBoolean C;

    public RunnableC0556Gu(RunnableC0557Gv this$1, AtomicBoolean atomicBoolean) {
        this.B = this$1;
        this.C = atomicBoolean;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.B.D != null) {
            if (this.C.get()) {
                this.B.B.J(EnumC0617Je.CACHE_SUCCESS);
                HI.G(this.B.C, true, this.B.B.F);
                this.B.B.F();
                this.B.D.nD();
                return;
            }
            this.B.B.J(EnumC0617Je.CACHE_FAILURE);
            HI.G(this.B.C, false, this.B.B.F);
            this.B.B.G();
            this.B.D.dD();
        }
    }
}
