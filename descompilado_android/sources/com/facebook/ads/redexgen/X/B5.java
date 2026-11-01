package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import java.util.HashMap;

/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class B5 extends LinearLayout {
    public static final LinearLayout.LayoutParams F = new LinearLayout.LayoutParams(-2, -2);
    public final RelativeLayout B;
    public final B0 C;
    public final int D;
    public final CN E;

    public B5(Context context, int iconSize, C1Y colors, String clickEventName, JV adEventManager, InterfaceC0525Fo adListener, C5I viewabilityChecker, C0621Ji touchDataRecorder) {
        super(context);
        this.D = iconSize;
        this.E = new CN(context);
        J6.O(this.E, 0);
        J6.U(this.E);
        this.C = new B0(context, true, false, clickEventName, colors, adEventManager, adListener, viewabilityChecker, touchDataRecorder);
        this.B = new RelativeLayout(context);
        this.B.setLayoutParams(F);
        J6.U(this.B);
    }

    public final void A(boolean actionEnabled) {
        this.C.setActionEnabled(actionEnabled);
        if (!TextUtils.isEmpty(this.C.getText())) {
            B(true);
        }
    }

    public final void B(boolean show) {
        this.C.setVisibility(show ? 0 : 8);
    }

    public final void C(C1Z ctaData, String clientToken) {
        this.C.B(ctaData, clientToken, new HashMap());
    }

    public final B0 getCTAButton() {
        return this.C;
    }

    @VisibleForTesting
    public final ImageView getIconView() {
        return this.E;
    }

    public void setInfo(C1V metadata, C1Z ctaData, String clientToken, String imageUrl, @Nullable CP listener) {
        this.C.setCta(ctaData, clientToken, new HashMap(), listener);
        new B7(this.E).D(this.D, this.D).E(imageUrl);
    }
}
