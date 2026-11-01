package com.facebook.ads.redexgen.X;

import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.Map;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class B2 implements View.OnClickListener {
    public boolean B = true;
    public final WeakReference<B0> C;
    public final String D;
    public final Map<String, String> E;
    public final C0618Jf F;
    public boolean G;

    @Nullable
    public CP H;
    public final String I;

    public B2(String url, String clientToken, Map<String, String> map, B0 ctaButton, C0618Jf funnelLoggingHandler) {
        this.I = url;
        this.D = clientToken;
        this.E = map;
        this.C = new WeakReference<>(ctaButton);
        this.F = funnelLoggingHandler;
    }

    public final boolean A() {
        return this.B;
    }

    public final void B(boolean actionEnabled) {
        this.B = actionEnabled;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C(B0 ctaButton) {
        if (JA.GB(ctaButton.getContext()) && !ctaButton.F.D()) {
            return;
        }
        try {
            Uri uri = Uri.parse(this.I);
            ctaButton.G.A(this.E);
            this.E.put("touch", MJ.C(ctaButton.F.m258B()));
            AnonymousClass09 adAction = ctaButton.I(uri, this.D, this.E, this.B, this.G);
            if (adAction != null) {
                adAction.A();
            }
            if (this.H != null) {
                this.H.pD();
            }
            if (ctaButton.C != null) {
                ctaButton.C.aB(ctaButton.E);
            }
        } catch (ActivityNotFoundException e) {
            Log.e(String.valueOf(B0.class), "Error while opening " + this.I, e);
        } catch (Exception e2) {
            Log.e(String.valueOf(B0.class), "Error executing action", e2);
        }
    }

    public final void C(boolean isInAppBrowser) {
        this.G = isInAppBrowser;
    }

    public final void D(@Nullable CP listener) {
        this.H = listener;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View v) {
        this.F.A(EnumC0617Je.CTA_CLICKED, null);
        B0 b0 = this.C.get();
        if (b0 != null) {
            if (b0.F.C(b0.getContext())) {
                if (b0.B != null) {
                    b0.B.hC(this.D, this.E);
                }
            } else {
                if (JA.BB(b0.getContext())) {
                    if (b0.B != null) {
                        b0.B.BD(this.D, this.E);
                    }
                    MX.B(new B4(this, b0), new B3(this, b0), MI.B());
                    return;
                }
                C(b0);
            }
        }
    }
}
