package com.facebook.ads.redexgen.X;

import android.view.View;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Bm, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class ViewOnClickListenerC0419Bm implements View.OnClickListener {
    public final /* synthetic */ C0418Bl B;
    public final /* synthetic */ AnonymousClass31 C;
    public final /* synthetic */ C0430Bx D;

    public ViewOnClickListenerC0419Bm(C0418Bl this$0, C0430Bx c0430Bx, AnonymousClass31 anonymousClass31) {
        this.B = this$0;
        this.D = c0430Bx;
        this.C = anonymousClass31;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View v) {
        this.D.A();
        ((AbstractC0432Bz) this.B).B.jE(this.C);
    }
}
