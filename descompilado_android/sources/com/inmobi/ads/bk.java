package com.inmobi.ads;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: PollingVisibilityTracker.java */
/* JADX INFO: loaded from: classes2.dex */
class bk extends ce {
    private static final String d = "bk";

    @Nullable
    private c.k e;

    bk(@NonNull ce.a aVar, @Nullable c.k kVar) {
        super(aVar);
        this.e = kVar;
    }

    @Override // com.inmobi.ads.ce
    protected final int a() {
        c.k kVar = this.e;
        if (kVar == null) {
            return 100;
        }
        return kVar.c;
    }

    @Override // com.inmobi.ads.ce
    protected final void b() {
        g();
    }
}
