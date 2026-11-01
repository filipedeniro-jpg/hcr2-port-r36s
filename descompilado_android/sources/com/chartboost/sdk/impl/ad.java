package com.chartboost.sdk.impl;

import com.chartboost.sdk.Model.CBError;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class ad<T> {
    public final String b;
    public final String c;
    public final int d;
    public final File f;
    public final AtomicInteger e = new AtomicInteger();
    public long g = 0;
    public long h = 0;
    public long i = 0;
    public int j = 0;

    public void a(CBError cBError, ag agVar) {
    }

    public void a(T t, ag agVar) {
    }

    public ad(String str, String str2, int i, File file) {
        this.b = str;
        this.c = str2;
        this.d = i;
        this.f = file;
    }

    public ae a() {
        return new ae(null, null, null);
    }

    public af<T> a(ag agVar) {
        return af.a((Object) null);
    }

    public boolean b() {
        return this.e.compareAndSet(0, -1);
    }
}
