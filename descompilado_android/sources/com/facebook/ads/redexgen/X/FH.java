package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class FH implements Runnable {
    public final /* synthetic */ FP B;
    public final /* synthetic */ FF C;
    public final /* synthetic */ int D;

    public FH(FP this$0, FF ff, int i) {
        this.B = this$0;
        this.C = ff;
        this.D = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.B.P != null && this.B.P.isAttachedToWindow() && !this.C.F && this.C.K.H() != -1) {
            DY itemAnimator = this.B.P.getItemAnimator();
            if ((itemAnimator == null || !itemAnimator.R(null)) && !this.B.H()) {
                this.B.D.A(this.C.K, this.D);
            } else {
                this.B.P.post(this);
            }
        }
    }
}
