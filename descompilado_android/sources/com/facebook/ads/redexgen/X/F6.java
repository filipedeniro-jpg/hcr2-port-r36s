package com.facebook.ads.redexgen.X;

import android.net.Uri;
import android.text.TextUtils;
import android.view.View;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class F6 implements View.OnClickListener {
    public final /* synthetic */ C0510Ez B;
    public final /* synthetic */ String C;
    public final /* synthetic */ C01551i D;

    public F6(C0510Ez this$0, C01551i c01551i, String str) {
        this.B = this$0;
        this.D = c01551i;
        this.C = str;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View v) {
        String strA;
        this.B.G.A(EnumC0617Je.AD_CHOICES_CLICKED, null);
        if (!TextUtils.isEmpty(C01972y.B(this.B.getContext()))) {
            strA = C01972y.B(this.B.getContext());
        } else {
            strA = this.D.A();
        }
        if (!TextUtils.isEmpty(strA)) {
            I7.B(new I7(), this.B.getContext(), Uri.parse(strA), this.C);
        }
    }
}
