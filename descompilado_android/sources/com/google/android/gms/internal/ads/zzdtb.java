package com.google.android.gms.internal.ads;

import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdtb implements Iterator<String> {
    private final /* synthetic */ zzdsz zzhoe;
    private Iterator<String> zzhpa;

    zzdtb(zzdsz zzdszVar) {
        this.zzhoe = zzdszVar;
        this.zzhpa = this.zzhoe.zzhof.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzhpa.hasNext();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ String next() {
        return this.zzhpa.next();
    }
}
