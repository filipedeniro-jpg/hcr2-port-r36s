package com.applovin.impl.sdk.d;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements Runnable {
    private final String a;
    protected final com.applovin.impl.sdk.h b;
    private final com.applovin.impl.sdk.n c;
    private final Context d;
    private final boolean e;

    public a(String str, com.applovin.impl.sdk.h hVar) {
        this(str, hVar, false);
    }

    public a(String str, com.applovin.impl.sdk.h hVar, boolean z) {
        this.a = str;
        this.b = hVar;
        this.c = hVar.w();
        this.d = hVar.z();
        this.e = z;
    }

    public abstract com.applovin.impl.sdk.c.i a();

    protected void a(String str) {
        this.c.a(this.a, str);
    }

    protected void a(String str, Throwable th) {
        this.c.b(this.a, str, th);
    }

    protected void b(String str) {
        this.c.b(this.a, str);
    }

    protected com.applovin.impl.sdk.h c() {
        return this.b;
    }

    protected void c(String str) {
        this.c.c(this.a, str);
    }

    public String d() {
        return this.a;
    }

    protected void d(String str) {
        this.c.d(this.a, str);
    }

    protected Context e() {
        return this.d;
    }

    public boolean f() {
        return this.e;
    }
}
