package com.facebook.ads.redexgen.X;

import android.widget.PopupMenu;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class FD implements PopupMenu.OnDismissListener {
    public final /* synthetic */ C0510Ez B;

    public FD(C0510Ez this$0) {
        this.B = this$0;
    }

    @Override // android.widget.PopupMenu.OnDismissListener
    public final void onDismiss(PopupMenu menu) {
        this.B.I = false;
    }
}
