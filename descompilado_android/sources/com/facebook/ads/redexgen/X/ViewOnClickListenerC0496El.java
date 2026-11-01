package com.facebook.ads.redexgen.X;

import android.view.View;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.El, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class ViewOnClickListenerC0496El implements View.OnClickListener {
    public final /* synthetic */ EU B;
    public final /* synthetic */ String C;

    public ViewOnClickListenerC0496El(EU this$0, String str) {
        this.B = this$0;
        this.C = str;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View v) {
        this.B.D.A(EnumC0617Je.AD_REPORT_CLICKED, null);
        this.B.C.YC(this.C, true, null);
    }
}
