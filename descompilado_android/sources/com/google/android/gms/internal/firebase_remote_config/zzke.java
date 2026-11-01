package com.google.android.gms.internal.firebase_remote_config;

import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
final class zzke implements Iterator<String> {
    private final /* synthetic */ zzkc zzxk;
    private Iterator<String> zzyg;

    zzke(zzkc zzkcVar) {
        this.zzxk = zzkcVar;
        this.zzyg = this.zzxk.zzxl.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzyg.hasNext();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ String next() {
        return this.zzyg.next();
    }
}
