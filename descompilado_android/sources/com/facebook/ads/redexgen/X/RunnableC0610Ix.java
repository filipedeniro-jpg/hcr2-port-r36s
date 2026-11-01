package com.facebook.ads.redexgen.X;

import com.facebook.share.internal.MessengerShareContentUtility;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ix, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class RunnableC0610Ix implements Runnable {
    public final /* synthetic */ C0611Iy B;
    public final /* synthetic */ AnonymousClass55 C;
    public final /* synthetic */ String D;

    public RunnableC0610Ix(C0611Iy this$0, AnonymousClass55 anonymousClass55, String str) {
        this.B = this$0;
        this.C = anonymousClass55;
        this.D = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.C.A(this.D);
        } catch (Throwable th) {
            C0585Hx.E(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, C0584Hw.BB, new C0583Hv(th));
        }
    }
}
