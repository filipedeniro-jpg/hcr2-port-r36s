package com.google.android.gms.internal.drive;

import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
final class zznc implements Iterator<String> {
    private final /* synthetic */ zzna zzvw;
    private Iterator<String> zzvx;

    zznc(zzna zznaVar) {
        this.zzvw = zznaVar;
        this.zzvx = this.zzvw.zzvt.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzvx.hasNext();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ String next() {
        return this.zzvx.next();
    }
}
