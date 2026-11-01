package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class BD implements Runnable {
    public final /* synthetic */ BC B;

    public BD(BC this$0) {
        this.B = this$0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (!this.B.F) {
            this.B.E(-1, null);
        }
    }
}
