package com.my.target;

import androidx.annotation.NonNull;

/* JADX INFO: compiled from: ResearchViewabilityStat.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fw extends an {
    private int i;
    private int j;

    public static fw b(@NonNull String str) {
        return new fw(str);
    }

    private fw(@NonNull String str) {
        super(aq.a.dI, str);
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
}
