package com.google.android.gms.internal.drive;

import java.util.ListIterator;

/* JADX INFO: loaded from: classes2.dex */
final class zznb implements ListIterator<String> {
    private ListIterator<String> zzvu;
    private final /* synthetic */ int zzvv;
    private final /* synthetic */ zzna zzvw;

    zznb(zzna zznaVar, int i) {
        this.zzvw = zznaVar;
        this.zzvv = i;
        this.zzvu = this.zzvw.zzvt.listIterator(this.zzvv);
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.zzvu.hasNext();
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.zzvu.hasPrevious();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.zzvu.nextIndex();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.zzvu.previousIndex();
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
        return this.zzvu.previous();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final /* synthetic */ Object next() {
        return this.zzvu.next();
    }
}
