package com.facebook.ads.redexgen.X;

import android.graphics.Color;
import android.text.TextUtils;
import androidx.core.view.ViewCompat;
import java.io.Serializable;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.1Y, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C1Y implements Serializable {
    public static final int I = Color.parseColor("#90949c");
    public static final int J = Color.parseColor("#4b4f56");
    public static final int K = Color.parseColor("#f6f7f9");
    public static final int L = Color.parseColor("#ff4080ff");
    public static final int M = Color.parseColor("#23272F");
    public static final int N = Color.parseColor("#ff4080ff");
    private static final long serialVersionUID = 8946536326456653736L;
    private int B = I;
    private int C = J;
    private int D = ViewCompat.MEASURED_STATE_MASK;
    private int E = K;
    private int F = L;
    private int G = -1;
    private int H = ViewCompat.MEASURED_STATE_MASK;

    public final int A(boolean viewOverMedia) {
        if (viewOverMedia) {
            return -1;
        }
        return this.B;
    }

    public final int B(boolean viewOverMedia) {
        if (viewOverMedia) {
            return -1;
        }
        return this.C;
    }

    public static C1Y B(JSONObject dataObject) {
        C1Y c1y = new C1Y();
        if (dataObject != null) {
            String backgroundColor = dataObject.optString("accent_color");
            String ctaColor = dataObject.optString("body_color");
            String pageNameTextColor = dataObject.optString("subtitle_color");
            String strOptString = dataObject.optString("bg_color");
            String strOptString2 = dataObject.optString("cta_color");
            String strOptString3 = dataObject.optString("cta_text_color");
            String strOptString4 = dataObject.optString("title_color");
            if (!TextUtils.isEmpty(backgroundColor)) {
                c1y.B = Color.parseColor(backgroundColor);
            }
            if (!TextUtils.isEmpty(ctaColor)) {
                c1y.C = Color.parseColor(ctaColor);
            }
            if (!TextUtils.isEmpty(pageNameTextColor)) {
                c1y.D = Color.parseColor(pageNameTextColor);
            }
            if (!TextUtils.isEmpty(strOptString)) {
                c1y.E = Color.parseColor(strOptString);
            }
            if (!TextUtils.isEmpty(strOptString2)) {
                c1y.F = Color.parseColor(strOptString2);
            }
            if (!TextUtils.isEmpty(strOptString3)) {
                c1y.G = Color.parseColor(strOptString3);
            }
            if (!TextUtils.isEmpty(strOptString4)) {
                c1y.H = Color.parseColor(strOptString4);
            }
        }
        return c1y;
    }

    public final int C(boolean viewOverMedia) {
        if (viewOverMedia) {
            return -1;
        }
        return this.D;
    }

    public final int D(boolean fullscreenMedia) {
        return fullscreenMedia ? M : this.E;
    }

    public final int E(boolean viewOverMedia) {
        if (viewOverMedia) {
            return -1;
        }
        return this.F;
    }

    public final int F(boolean viewOverMedia) {
        return viewOverMedia ? N : this.G;
    }

    public final int G(boolean viewOverMedia) {
        if (viewOverMedia) {
            return -1;
        }
        return this.H;
    }

    public final void H(int mCtaColor) {
        this.F = mCtaColor;
    }
}
