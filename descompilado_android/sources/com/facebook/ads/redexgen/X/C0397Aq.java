package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Aq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0397Aq extends LinearLayout {
    private final C0406Az B;
    private final TextView C;
    private static final int D = (int) (16.0f * J6.B);
    private static final int F = (int) (14.0f * J6.B);
    private static final int E = C5W.B(-1, 77);

    public C0397Aq(Context context) {
        super(context);
        setOrientation(0);
        setGravity(16);
        this.B = new C0406Az(context);
        this.B.setPadding(D, D, D, D);
        this.B.setProgress(0.0f);
        B(E, -1);
        this.C = new TextView(context);
        A(false, -1, F);
        addView(this.B);
        addView(this.C);
    }

    public final void A(boolean bold, int color, int fontSizeSP) {
        J6.Q(this.C, bold, fontSizeSP);
        this.C.setTextColor(color);
    }

    private final void B(int backgroundColor, int foregroundColor) {
        this.B.A(backgroundColor, foregroundColor);
    }

    public void setProgress(int progress) {
        this.B.setProgressWithAnimation(progress);
    }

    public void setText(String text) {
        this.C.setText(text);
    }
}
