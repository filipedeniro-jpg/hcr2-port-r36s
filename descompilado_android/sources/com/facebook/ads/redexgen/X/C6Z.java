package com.facebook.ads.redexgen.X;

import android.os.Handler;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.6Z, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C6Z implements F0 {
    private boolean J;
    private boolean K;

    @Nullable
    private C0447Co L;
    private boolean M;
    private final GG F = new GG() { // from class: com.facebook.ads.redexgen.X.6g
        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.facebook.ads.redexgen.X.AbstractC01210a
        public final void B(AnonymousClass72 event) {
            this.B.D.removeCallbacksAndMessages(null);
            if (this.B.N(EnumC02595i.REVERSE_ANIMATED)) {
                this.B.M();
                this.B.O(true, false);
            }
            this.B.K = true;
        }
    };
    private final GK E = new GK() { // from class: com.facebook.ads.redexgen.X.6f
        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.facebook.ads.redexgen.X.AbstractC01210a
        public final void B(AnonymousClass76 event) {
            this.B.M();
            this.B.O(false, false);
            this.B.K = true;
        }
    };
    private final GE G = new C02806d(this);
    private final FO H = new FO() { // from class: com.facebook.ads.redexgen.X.6c
        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.facebook.ads.redexgen.X.AbstractC01210a
        public final void B(C03026z event) {
            if (!this.B.J && this.B.N(EnumC02595i.REVERSE_ANIMATED)) {
                this.B.M();
                this.B.O(true, false);
            }
        }
    };
    private final AbstractC02976u I = new C02776a(this);
    private final Handler D = new Handler();
    private final List<InterfaceC02585h> B = new ArrayList();
    private int C = 2000;

    public C6Z(boolean reverseEnabled) {
        this.J = reverseEnabled;
    }

    public final void A(InterfaceC02585h animation) {
        this.B.add(animation);
    }

    public final void B() {
        this.B.clear();
    }

    public final void C() {
        if (this.J) {
            this.D.removeCallbacksAndMessages(null);
            this.J = false;
        }
    }

    public final boolean D() {
        return this.J;
    }

    public final void E(int delayInMillis) {
        this.C = delayInMillis;
    }

    public final void F() {
        this.M = true;
        this.K = true;
        O(false, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M() {
        this.D.removeCallbacksAndMessages(null);
        Iterator<InterfaceC02585h> it = this.B.iterator();
        while (it.hasNext()) {
            it.next().cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean N(EnumC02595i state) {
        Iterator<InterfaceC02585h> it = this.B.iterator();
        while (it.hasNext()) {
            if (it.next().JC() != state) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O(boolean showAnimation, boolean isReverse) {
        Iterator<InterfaceC02585h> it = this.B.iterator();
        while (it.hasNext()) {
            it.next().XB(showAnimation, isReverse);
        }
    }

    @Override // com.facebook.ads.redexgen.X.F0
    public final void cC(C0447Co videoView) {
        this.L = videoView;
        videoView.getEventBus().B(this.F, this.I, this.G, this.H, this.E);
    }

    @Override // com.facebook.ads.redexgen.X.F0
    public final void wF(C0447Co videoView) {
        M();
        videoView.getEventBus().D(this.E, this.I, this.G, this.H, this.F);
        this.L = null;
    }
}
