package com.facebook.ads.redexgen.X;

import android.R;
import android.content.Context;
import android.widget.Button;
import androidx.core.view.ViewCompat;
import java.util.Locale;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class B1 extends Button {
    private int B;
    private int C;
    private final int D;
    private int E;
    public static final int G = (int) (16.0f * J6.B);
    private static final int F = (int) (4.0f * J6.B);

    public B1(Context context, boolean roundCornersEnabled, boolean ctaOverMedia, C1Y colors) {
        super(context);
        this.E = 0;
        this.B = 0;
        this.C = 0;
        J6.Q(this, false, 16);
        setGravity(17);
        setPadding(G, G, G, G);
        if (colors != null) {
            this.C = colors.E(ctaOverMedia);
            this.B = C5W.C(this.C, ViewCompat.MEASURED_STATE_MASK, 0.1f);
            this.E = colors.F(ctaOverMedia);
        }
        this.D = roundCornersEnabled ? F : 0;
        J();
    }

    private void J() {
        J6.R(this, new int[][]{new int[]{R.attr.state_pressed}, new int[0]}, new int[]{this.B, this.C}, this.D);
        setTextColor(this.E);
    }

    public void setText(String text) {
        super.setText((CharSequence) text.toUpperCase(Locale.US));
    }
}
