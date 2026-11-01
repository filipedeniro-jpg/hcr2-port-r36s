package com.applovin.impl.sdk.d;

/* JADX INFO: loaded from: classes.dex */
public class aa extends a {
    private final Runnable a;

    public aa(com.applovin.impl.sdk.h hVar, Runnable runnable) {
        super("TaskRunnable", hVar);
        this.a = runnable;
    }

    @Override // com.applovin.impl.sdk.d.a
    public com.applovin.impl.sdk.c.i a() {
        return com.applovin.impl.sdk.c.i.f;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.run();
    }
}
