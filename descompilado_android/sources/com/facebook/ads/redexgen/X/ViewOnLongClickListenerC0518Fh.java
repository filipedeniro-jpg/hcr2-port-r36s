package com.facebook.ads.redexgen.X;

import android.view.View;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Fh, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class ViewOnLongClickListenerC0518Fh implements View.OnLongClickListener {
    public final /* synthetic */ C0519Fi B;

    public ViewOnLongClickListenerC0518Fh(C0519Fi this$1) {
        this.B = this$1;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View v) {
        if (this.B.B.L != null) {
            this.B.B.F.setBounds(0, 0, this.B.B.L.getWidth(), this.B.B.L.getHeight());
            this.B.B.F.C(this.B.B.F.A() ? false : true);
        }
        return true;
    }
}
