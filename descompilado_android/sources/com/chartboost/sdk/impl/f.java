package com.chartboost.sdk.impl;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes.dex */
public class f implements Comparable<f> {
    final int a;
    final String b;
    int c;
    boolean f = false;
    com.chartboost.sdk.Model.a d = null;
    Integer e = null;
    int g = 3;
    Long h = null;
    Long i = null;
    Long j = null;
    Integer k = null;
    Integer l = null;
    Integer m = null;
    Integer n = null;
    Integer o = null;
    Integer p = null;
    Integer q = null;
    Integer r = null;

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(f fVar) {
        return this.a - fVar.a;
    }

    f(int i, String str, int i2) {
        this.a = i;
        this.b = str;
        this.c = i2;
    }
}
