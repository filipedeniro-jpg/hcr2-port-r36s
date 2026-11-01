package com.facebook.ads.redexgen.X;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.ViewTreeObserver;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Cw, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class ViewTreeObserverOnGlobalLayoutListenerC0455Cw implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ AbstractC0466Dh B;
    public final /* synthetic */ ViewTreeObserver C;

    public ViewTreeObserverOnGlobalLayoutListenerC0455Cw(AbstractC0466Dh this$0, ViewTreeObserver viewTreeObserver) {
        this.B = this$0;
        this.C = viewTreeObserver;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    @RequiresApi(api = 16)
    public final void onGlobalLayout() {
        this.B.E.L();
        if (Build.VERSION.SDK_INT < 16) {
            this.C.removeGlobalOnLayoutListener(this);
        } else {
            this.C.removeOnGlobalLayoutListener(this);
        }
    }
}
