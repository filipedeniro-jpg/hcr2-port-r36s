package com.facebook.ads.redexgen.X;

import android.view.View;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Bi, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class ViewOnClickListenerC0415Bi implements View.OnClickListener {
    public final /* synthetic */ C0414Bh B;
    public final /* synthetic */ C0430Bx C;
    public final /* synthetic */ AnonymousClass31 D;

    public ViewOnClickListenerC0415Bi(C0414Bh this$0, C0430Bx c0430Bx, AnonymousClass31 anonymousClass31) {
        this.B = this$0;
        this.C = c0430Bx;
        this.D = anonymousClass31;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View v) {
        this.C.A();
        this.B.B.jE(this.D);
    }
}
