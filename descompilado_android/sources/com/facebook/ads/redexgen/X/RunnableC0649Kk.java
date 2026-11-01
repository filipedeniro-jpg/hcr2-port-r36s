package com.facebook.ads.redexgen.X;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Kk, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class RunnableC0649Kk implements Runnable {
    public final /* synthetic */ C0656Ks B;
    public final /* synthetic */ long C;
    public final /* synthetic */ String D;

    public RunnableC0649Kk(C0656Ks this$0, String str, long j) {
        this.B = this$0;
        this.D = str;
        this.C = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.B.S(this.D, this.C);
    }
}
