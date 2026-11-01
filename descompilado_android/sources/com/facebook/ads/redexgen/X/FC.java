package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class FC {

    @VisibleForTesting
    public final C03137k<AbstractC0502Er, FB> B = new C03137k<>();

    @VisibleForTesting
    public final C03227t<AbstractC0502Er> C = new C03227t<>();

    public final void A(AbstractC0502Er holder, EX info) {
        FB fbC = this.B.get(holder);
        if (fbC == null) {
            fbC = FB.C();
            this.B.put(holder, fbC);
        }
        fbC.B |= 2;
        fbC.D = info;
    }

    private EX B(AbstractC0502Er vh, int flag) {
        FB fbG;
        EX ex = null;
        int iB = this.B.B(vh);
        if (iB >= 0 && (fbG = this.B.G(iB)) != null && (fbG.B & flag) != 0) {
            fbG.B &= flag ^ (-1);
            if (flag == 4) {
                ex = fbG.D;
            } else if (flag == 8) {
                ex = fbG.C;
            } else {
                throw new IllegalArgumentException("Must provide flag PRE or POST");
            }
            if ((fbG.B & 12) == 0) {
                this.B.E(iB);
                FB.D(fbG);
            }
        }
        return ex;
    }

    public final void B(AbstractC0502Er holder) {
        FB fbC = this.B.get(holder);
        if (fbC == null) {
            fbC = FB.C();
            this.B.put(holder, fbC);
        }
        fbC.B |= 1;
    }

    public final void C(long key, AbstractC0502Er holder) {
        this.C.C(key, holder);
    }

    public final void D(AbstractC0502Er holder, EX info) {
        FB fbC = this.B.get(holder);
        if (fbC == null) {
            fbC = FB.C();
            this.B.put(holder, fbC);
        }
        fbC.C = info;
        fbC.B |= 8;
    }

    public final void E(AbstractC0502Er holder, EX info) {
        FB fbC = this.B.get(holder);
        if (fbC == null) {
            fbC = FB.C();
            this.B.put(holder, fbC);
        }
        fbC.D = info;
        fbC.B |= 4;
    }

    public final void F() {
        this.B.clear();
        this.C.A();
    }

    public final AbstractC0502Er G(long key) {
        return this.C.B(key);
    }

    public final boolean H(AbstractC0502Er holder) {
        FB fb = this.B.get(holder);
        return (fb == null || (fb.B & 1) == 0) ? false : true;
    }

    public final boolean I(AbstractC0502Er viewHolder) {
        FB fb = this.B.get(viewHolder);
        return (fb == null || (fb.B & 4) == 0) ? false : true;
    }

    public final void J() {
        FB.B();
    }

    public final void K(AbstractC0502Er viewHolder) {
        O(viewHolder);
    }

    @Nullable
    public final EX L(AbstractC0502Er vh) {
        return B(vh, 8);
    }

    @Nullable
    public final EX M(AbstractC0502Er vh) {
        return B(vh, 4);
    }

    public final void N(EN callback) {
        for (int size = this.B.size() - 1; size >= 0; size--) {
            AbstractC0502Er abstractC0502Er = (AbstractC0502Er) this.B.D(size);
            FB fbE = this.B.E(size);
            if ((fbE.B & 3) == 3) {
                callback.zF(abstractC0502Er);
            } else if ((fbE.B & 1) != 0) {
                if (fbE.D == null) {
                    callback.zF(abstractC0502Er);
                } else {
                    callback.dF(abstractC0502Er, fbE.D, fbE.C);
                }
            } else if ((fbE.B & 14) == 14) {
                callback.bF(abstractC0502Er, fbE.D, fbE.C);
            } else if ((fbE.B & 12) == 12) {
                callback.fF(abstractC0502Er, fbE.D, fbE.C);
            } else if ((fbE.B & 4) != 0) {
                callback.dF(abstractC0502Er, fbE.D, null);
            } else if ((fbE.B & 8) != 0) {
                callback.bF(abstractC0502Er, fbE.D, fbE.C);
            } else if ((fbE.B & 2) != 0) {
            }
            FB.D(fbE);
        }
    }

    public final void O(AbstractC0502Er holder) {
        FB fb = this.B.get(holder);
        if (fb == null) {
            return;
        }
        fb.B &= -2;
    }

    public final void P(AbstractC0502Er holder) {
        for (int iM238E = this.C.m238E() - 1; iM238E >= 0; iM238E--) {
            if (holder == this.C.F(iM238E)) {
                this.C.D(iM238E);
                break;
            }
        }
        FB fbRemove = this.B.remove(holder);
        if (fbRemove != null) {
            FB.D(fbRemove);
        }
    }
}
