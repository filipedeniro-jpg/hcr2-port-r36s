package com.facebook.ads.redexgen.X;

import android.view.View;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Fs, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class ViewOnLongClickListenerC0529Fs implements View.OnLongClickListener {
    public final /* synthetic */ C0531Fu B;

    private ViewOnLongClickListenerC0529Fs(C0531Fu c0531Fu) {
        this.B = c0531Fu;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View v) {
        if (this.B.J != null && this.B.P != null) {
            this.B.J.setBounds(0, 0, this.B.P.getWidth(), this.B.P.getHeight());
            this.B.J.C(this.B.J.A() ? false : true);
        }
        return true;
    }
}
