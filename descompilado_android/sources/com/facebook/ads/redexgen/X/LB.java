package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import java.util.Deque;
import java.util.LinkedList;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class LB {
    public final int C;
    public final int E;
    public final Deque<String> D = new LinkedList();
    public final Deque<String> B = new LinkedList();

    @Nullable
    public String F = null;

    public LB(int beforeSize, int afterSize) {
        this.E = beforeSize;
        this.C = afterSize;
    }

    public final void A(String line) {
        this.B.addLast(line);
        if (this.B.size() <= this.C) {
            return;
        }
        if (this.F != null) {
            this.D.addLast(this.F);
        }
        this.F = this.B.removeFirst();
        if (this.D.size() <= this.E) {
            return;
        }
        this.D.removeFirst();
    }

    public final Iterable<String> B() {
        return this.B;
    }

    public final Iterable<String> C() {
        return this.D;
    }

    @Nullable
    public final String D() {
        return this.F;
    }

    public final void E() {
        if (this.F != null) {
            this.D.addLast(this.F);
            if (this.D.size() > this.E) {
                this.D.removeFirst();
            }
        }
        if (this.B.size() > 0) {
            this.F = this.B.removeFirst();
        } else {
            this.F = null;
        }
    }
}
