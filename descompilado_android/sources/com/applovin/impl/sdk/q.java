package com.applovin.impl.sdk;

import java.util.LinkedList;
import java.util.Queue;

/* JADX INFO: loaded from: classes.dex */
class q {
    private int a;
    private final Queue<com.applovin.impl.sdk.ad.j> b;
    private final Object c;

    q(int i) {
        a(i);
        this.b = new LinkedList();
        this.c = new Object();
    }

    int a() {
        int size;
        synchronized (this.c) {
            size = this.b.size();
        }
        return size;
    }

    void a(int i) {
        if (i > 25) {
            i = 25;
        }
        this.a = i;
    }

    void a(com.applovin.impl.sdk.ad.j jVar) {
        synchronized (this.c) {
            if (a() <= 25) {
                this.b.offer(jVar);
            }
        }
    }

    int b() {
        return this.a;
    }

    boolean c() {
        boolean z;
        synchronized (this.c) {
            z = a() >= this.a;
        }
        return z;
    }

    boolean d() {
        boolean z;
        synchronized (this.c) {
            z = a() == 0;
        }
        return z;
    }

    com.applovin.impl.sdk.ad.j e() {
        com.applovin.impl.sdk.ad.j jVarPoll;
        try {
            synchronized (this.c) {
                jVarPoll = !d() ? this.b.poll() : null;
            }
            return jVarPoll;
        } catch (Exception unused) {
            return null;
        }
    }

    com.applovin.impl.sdk.ad.j f() {
        com.applovin.impl.sdk.ad.j jVarPeek;
        synchronized (this.c) {
            jVarPeek = this.b.peek();
        }
        return jVarPeek;
    }
}
