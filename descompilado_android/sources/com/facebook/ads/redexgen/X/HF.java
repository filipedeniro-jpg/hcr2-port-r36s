package com.facebook.ads.redexgen.X;

import android.content.Context;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class HF implements Callable<C0679Lq> {
    public final /* synthetic */ HG B;
    public final /* synthetic */ Context C;

    public HF(HG this$0, Context context) {
        this.B = this$0;
        this.C = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public final C0679Lq call() {
        return new C0679Lq(this.C);
    }
}
