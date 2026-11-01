package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class CV implements Runnable {
    public final /* synthetic */ CU B;
    public final /* synthetic */ int C;

    public CV(CU this$1, int i) {
        this.B = this$1;
        this.C = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.B.B.j != null && this.B.B.j.getState() == C5K.BUFFERING && this.B.B.j.getCurrentPositionInMillis() == this.C) {
            this.B.B.a.removeCallbacksAndMessages(null);
            this.B.B.f("Buffering indefinitely");
        }
    }
}
