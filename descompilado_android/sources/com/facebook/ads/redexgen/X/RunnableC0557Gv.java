package com.facebook.ads.redexgen.X;

import java.util.ArrayList;
import java.util.concurrent.Callable;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Gv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class RunnableC0557Gv implements Runnable {
    public final /* synthetic */ H2 B;
    public final /* synthetic */ C0558Gw C;
    public final /* synthetic */ InterfaceC01280h D;
    public final /* synthetic */ ArrayList E;
    public final /* synthetic */ ArrayList F;

    public RunnableC0557Gv(H2 this$0, ArrayList arrayList, InterfaceC01280h interfaceC01280h, C0558Gw c0558Gw, ArrayList arrayList2) {
        this.B = this$0;
        this.E = arrayList;
        this.D = interfaceC01280h;
        this.C = c0558Gw;
        this.F = arrayList2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.B.D.post(new RunnableC0556Gu(this, H2.I((ArrayList<Callable<Boolean>>) this.E)));
        H2.I((ArrayList<Callable<Boolean>>) this.F);
    }
}
