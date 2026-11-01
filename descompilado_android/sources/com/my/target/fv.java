package com.my.target;

import androidx.annotation.NonNull;

/* JADX INFO: compiled from: ResearchTimerStat.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fv extends aq {
    private int i;
    private int j;
    private int k;

    public static fv a(@NonNull String str) {
        return new fv(str);
    }

    public final int g() {
        return this.i;
    }

    public final void d(int i) {
        this.i = i;
    }

    public final int h() {
        return this.j;
    }

    public final void e(int i) {
        this.j = i;
    }

    public final int i() {
        return this.k;
    }

    public final void f(int i) {
        this.k = i;
    }

    private fv(@NonNull String str) {
        super(aq.a.dJ, str);
        this.k = 1;
    }
}
