package com.facebook.ads.redexgen.X;

import android.view.View;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Bq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class ViewOnClickListenerC0423Bq implements View.OnClickListener {
    public final /* synthetic */ C0418Bl B;
    public final /* synthetic */ C0430Bx C;

    public ViewOnClickListenerC0423Bq(C0418Bl this$0, C0430Bx c0430Bx) {
        this.B = this$0;
        this.C = c0430Bx;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View v) {
        this.C.A();
        ((AbstractC0432Bz) this.B).B.bE(EnumC01982z.HIDE);
    }
}
