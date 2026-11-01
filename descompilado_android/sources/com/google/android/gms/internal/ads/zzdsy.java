package com.google.android.gms.internal.ads;

import java.util.ListIterator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdsy implements ListIterator<String> {
    private final /* synthetic */ int zzgqj;
    private ListIterator<String> zzhod;
    private final /* synthetic */ zzdsz zzhoe;

    zzdsy(zzdsz zzdszVar, int i) {
        this.zzhoe = zzdszVar;
        this.zzgqj = i;
        this.zzhod = this.zzhoe.zzhof.listIterator(this.zzgqj);
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.zzhod.hasNext();
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.zzhod.hasPrevious();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.zzhod.nextIndex();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.zzhod.previousIndex();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ void add(String str) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ void set(String str) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ String previous() {
        return this.zzhod.previous();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final /* synthetic */ Object next() {
        return this.zzhod.next();
    }
}
