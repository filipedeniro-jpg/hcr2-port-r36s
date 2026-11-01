package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import java.lang.ref.WeakReference;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Fp, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0526Fp implements InterfaceC0525Fo {
    public final WeakReference<C0531Fu> B;

    private C0526Fp(C0531Fu activityImpl) {
        this.B = new WeakReference<>(activityImpl);
    }

    private void B(C0531Fu audienceNetworkActivityApi) {
        C8N c8n = audienceNetworkActivityApi.O;
        if (c8n == null) {
            return;
        }
        audienceNetworkActivityApi.P.bringChildToFront(c8n);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0525Fo
    public final void VB(View v, int index, RelativeLayout.LayoutParams params) {
        C0531Fu c0531Fu = this.B.get();
        if (c0531Fu == null) {
            return;
        }
        c0531Fu.P.addView(v, index, params);
        B(c0531Fu);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0525Fo
    public final void WB(View v, RelativeLayout.LayoutParams params) {
        C0531Fu c0531Fu = this.B.get();
        if (c0531Fu == null) {
            return;
        }
        c0531Fu.P.addView(v, params);
        B(c0531Fu);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0525Fo
    public final void YC(String clientToken, boolean setAdReportingFlowCompleted, @Nullable H6 listener) {
        if (this.B.get() == null) {
            return;
        }
        this.B.get().P(clientToken, setAdReportingFlowCompleted, listener);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0525Fo
    public void aB(String event) {
        if (this.B.get() == null) {
            return;
        }
        this.B.get().S(event);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0525Fo
    public void bB(String event, It eventObj) {
        if (this.B.get() == null) {
            return;
        }
        this.B.get().T(event, eventObj);
    }
}
