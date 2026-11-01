package com.facebook.ads.redexgen.X;

import android.os.Handler;
import android.support.annotation.VisibleForTesting;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class MS {
    private boolean B;
    private int C;
    private final Handler D;
    private boolean E;
    private final MR F;

    public MS(int startingTimeInSecs, MR listener) {
        this(startingTimeInSecs, listener, new Handler());
    }

    @VisibleForTesting
    private MS(int startingTimeInSecs, MR listener, Handler handler) {
        this.E = false;
        this.C = startingTimeInSecs;
        this.F = listener;
        this.D = handler;
    }

    public final int A() {
        return this.C;
    }

    public final boolean B() {
        return this.C <= 0;
    }

    public final boolean C() {
        return this.E;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D() {
        this.C--;
        this.F.uE(this.C);
        if (this.C == 0 && !this.B) {
            this.B = true;
            this.F.mD();
            this.E = false;
        }
    }

    /* JADX INFO: renamed from: D, reason: collision with other method in class */
    public final boolean m263D() {
        if (!C()) {
            return false;
        }
        this.E = false;
        return true;
    }

    public final boolean E() {
        if (B() && !this.B) {
            this.F.mD();
        }
        if (B() || C()) {
            return false;
        }
        this.E = true;
        this.F.uE(this.C);
        this.D.postDelayed(new MQ(this), 1000L);
        return true;
    }
}
