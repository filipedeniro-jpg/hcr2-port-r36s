package com.facebook.ads.redexgen.X;

import android.content.Context;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class H3 implements Callable<H4> {
    public final /* synthetic */ H5 B;
    public final /* synthetic */ Context C;

    public H3(H5 this$0, Context context) {
        this.B = this$0;
        this.C = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public final H4 call() {
        return new H4(this.C);
    }
}
