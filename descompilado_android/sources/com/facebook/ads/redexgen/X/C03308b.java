package com.facebook.ads.redexgen.X;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.8b, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C03308b extends AbstractC0463De {
    private final C5I B;
    private List<C03298a> C;

    @Nullable
    private C8Z F;
    private boolean J;
    private final C0484Dz K;
    private final int L;
    private final E7 N;
    private final Set<Integer> D = new HashSet();
    private boolean I = true;
    private boolean G = true;
    private boolean H = true;
    private int M = -1;
    private float E = 0.0f;
    private final AnonymousClass99 Q = new AnonymousClass99() { // from class: com.facebook.ads.redexgen.X.8e
        @Override // com.facebook.ads.redexgen.X.AnonymousClass99
        public final float getVolume() {
            return this.B.E;
        }

        @Override // com.facebook.ads.redexgen.X.AnonymousClass99
        public final void setVolume(float newLevel) {
            this.B.E = newLevel;
        }
    };
    private final C9B O = new C9B() { // from class: com.facebook.ads.redexgen.X.8d
        @Override // com.facebook.ads.redexgen.X.C9B
        public final void KF(int position) {
            this.B.Y(position, true);
            if (this.B.W()) {
                this.B.S();
            } else {
                this.B.Z(position);
            }
        }
    };
    private final C9A P = new C9A() { // from class: com.facebook.ads.redexgen.X.8c
        @Override // com.facebook.ads.redexgen.X.C9A
        public final void MF(View view) {
            if (this.B.J) {
                this.B.H = false;
            }
        }

        @Override // com.facebook.ads.redexgen.X.C9A
        public final void NF(View view) {
            C9K c9k = (C9K) view;
            c9k.V();
            if (this.B.J) {
                this.B.H = true;
            }
            if (this.B.B.C() && ((Integer) c9k.getTag(-1593835536)).intValue() == 0) {
                this.B.B.F();
            }
        }
    };

    public C03308b(C0562Ha recyclerView, int orientation, List<C03298a> list, C5I adViewabilityChecker, @Nullable Bundle savedInstanceState) {
        this.K = recyclerView.getLayoutManager();
        this.L = orientation;
        this.C = list;
        this.B = adViewabilityChecker;
        this.N = new E8(recyclerView.getContext());
        recyclerView.E(this);
        X(savedInstanceState);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0463De
    public final void A(C0503Es recyclerView, int newState) {
        super.A(recyclerView, newState);
        if (newState == 0) {
            this.J = true;
            K();
        }
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0463De
    public final void B(C0503Es recyclerView, int dx, int dy) {
        super.B(recyclerView, dx, dy);
        this.J = false;
        if (this.G) {
            this.J = true;
            K();
            this.G = false;
        }
        int iFB = this.K.FB();
        int iGB = this.K.GB();
        N(iFB, iGB);
        P(iFB, iGB);
        c(iFB, iGB, dx);
    }

    public final C9B C() {
        return this.O;
    }

    public final C9A D() {
        return this.P;
    }

    public final AnonymousClass99 E() {
        return this.Q;
    }

    public final void F() {
        this.M = -1;
        int iGB = this.K.GB();
        for (int lastPos = this.K.FB(); lastPos <= iGB && lastPos >= 0; lastPos++) {
            C9K c9k = (C9K) this.K.V(lastPos);
            if (c9k != null && c9k.R()) {
                this.M = lastPos;
                c9k.T();
                return;
            }
        }
    }

    public final void G() {
        C9K c9k = (C9K) this.K.V(this.M);
        if (this.M >= 0) {
            c9k.U();
        }
    }

    public final void H(Bundle savedInstanceState) {
        savedInstanceState.putFloat("VOLUME_LEVEL_PARAM", this.E);
        savedInstanceState.putBoolean("AUTO_PLAY_ENABLED_PARAM", this.H);
        savedInstanceState.putBoolean("IS_FIRST_VIDEO_PARAM", this.I);
    }

    public final void I(C8Z dotsLayoutListener) {
        this.F = dotsLayoutListener;
    }

    private void K() {
        if (!this.H) {
            return;
        }
        int iFB = this.K.FB();
        int firstVisibleItem = this.K.GB();
        C9K c9kQ = Q(iFB, firstVisibleItem);
        if (c9kQ == null) {
            return;
        }
        c9kQ.U();
    }

    private static void L(View view, boolean shouldBeVisible) {
        view.setAlpha(shouldBeVisible ? 1.0f : 0.5f);
    }

    private void M(int cardPosition) {
        C9K c9k = (C9K) this.K.V(cardPosition);
        if (!T(c9k)) {
            b(c9k, false);
        }
    }

    private void N(int firstVisibleItem, int lastVisibleItem) {
        M(firstVisibleItem);
        M(lastVisibleItem);
    }

    private void O(int cardPosition) {
        C9K c9k = (C9K) this.K.V(cardPosition);
        if (T(c9k)) {
            b(c9k, true);
        }
        if (U(c9k)) {
            this.Q.setVolume(this.C.get(((Integer) c9k.getTag(-1593835536)).intValue()).A().C().I() ? 0.0f : 1.0f);
        }
    }

    private void P(int firstVisibleItem, int lastVisibleItem) {
        while (firstVisibleItem <= lastVisibleItem) {
            O(firstVisibleItem);
            firstVisibleItem++;
        }
    }

    @Nullable
    private C9K Q(int firstVisibleItem, int lastVisibleItem) {
        return R(firstVisibleItem, lastVisibleItem, true);
    }

    @Nullable
    private C9K R(int firstVisibleItem, int lastVisibleItem, boolean checkPlayableArea) {
        C9K c9k = null;
        while (firstVisibleItem <= lastVisibleItem) {
            C9K c9k2 = (C9K) this.K.V(firstVisibleItem);
            if (c9k2.R()) {
                return null;
            }
            boolean zT = T(c9k2);
            if (c9k == null && c9k2.S() && zT && !this.D.contains(Integer.valueOf(firstVisibleItem)) && (!checkPlayableArea || V(c9k2))) {
                c9k = c9k2;
            }
            if (c9k2.S() && !zT) {
                Y(firstVisibleItem, false);
            }
            firstVisibleItem++;
        }
        return c9k;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void S() {
        int iEB = this.K.EB();
        if (iEB != -1 && iEB < this.C.size() - 1) {
            a(iEB + 1);
        }
    }

    private static boolean T(View view) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        return ((float) rect.width()) / ((float) view.getWidth()) >= 0.15f;
    }

    private boolean U(C9K cardLayout) {
        if (!this.I || !cardLayout.S()) {
            return false;
        }
        this.I = false;
        return true;
    }

    private static boolean V(C9K cardLayout) {
        int width = (int) (cardLayout.getWidth() * 1.3f);
        int furthestX = (int) (cardLayout.getX() + cardLayout.getWidth());
        return furthestX <= width;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean W() {
        return this.L == 1;
    }

    private void X(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return;
        }
        this.E = savedInstanceState.getFloat("VOLUME_LEVEL_PARAM", 0.0f);
        this.H = savedInstanceState.getBoolean("AUTO_PLAY_ENABLED_PARAM", true);
        this.I = savedInstanceState.getBoolean("IS_FIRST_VIDEO_PARAM", true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Y(int pos, boolean isCompleted) {
        if (isCompleted) {
            this.D.add(Integer.valueOf(pos));
        } else {
            this.D.remove(Integer.valueOf(pos));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Z(int curPos) {
        C9K c9kR = R(curPos + 1, this.K.GB(), false);
        if (c9kR != null) {
            c9kR.U();
            a(((Integer) c9kR.getTag(-1593835536)).intValue());
        }
    }

    private void a(int targetPosition) {
        this.N.L(targetPosition);
        this.K.CA(this.N);
    }

    private void b(C9K cardLayout, boolean enable) {
        if (W()) {
            L(cardLayout, enable);
        }
        if (!enable && cardLayout.R()) {
            cardLayout.T();
        }
    }

    private void c(int firstVisible, int lastVisible, int dx) {
        if (!W() || this.F == null) {
            return;
        }
        int iEB = this.K.EB();
        if (iEB == -1) {
            iEB = dx < 0 ? firstVisible : lastVisible;
        }
        this.F.iF(iEB);
    }
}
