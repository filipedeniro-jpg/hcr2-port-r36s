package com.chartboost.sdk.impl;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
class j implements Comparable<j> {
    final int a;
    final String b;
    final String c;
    final String d;
    final AtomicInteger e;
    final AtomicInteger f;
    private final com.chartboost.sdk.Libraries.i g;
    private final AtomicReference<h> h;
    private final long i;

    j(com.chartboost.sdk.Libraries.i iVar, int i, String str, String str2, String str3, AtomicInteger atomicInteger, AtomicReference<h> atomicReference, long j, AtomicInteger atomicInteger2) {
        this.g = iVar;
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = atomicInteger;
        this.h = atomicReference;
        this.i = j;
        this.f = atomicInteger2;
        atomicInteger.incrementAndGet();
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(j jVar) {
        return this.a - jVar.a;
    }

    void a(Executor executor, boolean z) {
        h andSet;
        if ((this.e.decrementAndGet() == 0 || !z) && (andSet = this.h.getAndSet(null)) != null) {
            executor.execute(new i(andSet, z, (int) TimeUnit.NANOSECONDS.toMillis(this.g.b() - this.i), this.f.get()));
        }
    }
}
