package com.facebook.ads.redexgen.X;

import android.widget.LinearLayout;
import java.lang.ref.WeakReference;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Gj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0546Gj implements MR {
    private final C1W B;
    private final WeakReference<FV> C;
    private int D;
    private final WeakReference<C0397Aq> E;

    public C0546Gj(FV chooseYourOwnAdFullscreenView, C1W adDataBundle, int maxTimerTimeInSec) {
        this.C = new WeakReference<>(chooseYourOwnAdFullscreenView);
        this.E = new WeakReference<>(chooseYourOwnAdFullscreenView.P);
        this.B = adDataBundle;
        this.D = maxTimerTimeInSec;
    }

    @Override // com.facebook.ads.redexgen.X.MR
    public final void mD() {
        if (this.C.get() != null) {
            LinearLayout linearLayout = this.C.get().I;
            int iB = this.B.J().B();
            if (((C03217s) linearLayout.getChildAt(iB)).C()) {
                for (int i = 0; i < i; i++) {
                    if (!((C03217s) linearLayout.getChildAt(i)).C()) {
                        iB = i;
                        break;
                    }
                }
            }
            C0618Jf c0618Jf = this.C.get().L;
            if (c0618Jf != null) {
                c0618Jf.A(EnumC0617Je.AD_SELECTION_TIMEOUT, null);
            }
            this.C.get().L(this.B.K().get(iB));
        }
    }

    @Override // com.facebook.ads.redexgen.X.MR
    public final void uE(int curSec) {
        C0397Aq c0397Aq = this.E.get();
        if (c0397Aq != null) {
            c0397Aq.setProgress(((this.D - curSec) * 100) / this.D);
            c0397Aq.setText(this.B.O().A(String.valueOf(curSec)));
        }
    }
}
