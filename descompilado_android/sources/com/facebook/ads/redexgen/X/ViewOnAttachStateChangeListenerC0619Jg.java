package com.facebook.ads.redexgen.X;

import android.view.View;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Jg, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class ViewOnAttachStateChangeListenerC0619Jg implements View.OnAttachStateChangeListener {
    public final /* synthetic */ EnumC0617Je B;
    public final /* synthetic */ C0618Jf C;

    public ViewOnAttachStateChangeListenerC0619Jg(C0618Jf c0618Jf, EnumC0617Je enumC0617Je) {
        this.C = c0618Jf;
        this.B = enumC0617Je;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View v) {
        this.C.A(this.B, null);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View v) {
    }
}
