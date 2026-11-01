package com.chartboost.sdk.impl;

import android.os.Handler;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class ah {
    private final Executor a;
    private final Executor b;
    private final ao c;
    private final ai d;
    private final com.chartboost.sdk.Libraries.i e;
    private final Handler f;

    public ah(Executor executor, ao aoVar, ai aiVar, com.chartboost.sdk.Libraries.i iVar, Handler handler, Executor executor2) {
        this.a = executor2;
        this.b = executor;
        this.c = aoVar;
        this.d = aiVar;
        this.e = iVar;
        this.f = handler;
    }

    public <T> void a(ad<T> adVar) {
        this.a.execute(new an(this.b, this.c, this.d, this.e, this.f, adVar));
    }
}
