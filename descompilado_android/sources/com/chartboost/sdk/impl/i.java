package com.chartboost.sdk.impl;

/* JADX INFO: loaded from: classes.dex */
public class i implements Runnable {
    public final boolean a;
    private final h b;
    private final int c;
    private final int d;

    i(h hVar, boolean z, int i, int i2) {
        this.b = hVar;
        this.a = z;
        this.c = i;
        this.d = i2;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.a(this.a, this.c, this.d);
    }
}
