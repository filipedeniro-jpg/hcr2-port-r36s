package com.google.android.gms.internal.ads;

import java.util.NoSuchElementException;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdap<T> extends zzdbb<T> {
    private boolean zzgov;
    private final /* synthetic */ Object zzgow;

    zzdap(Object obj) {
        this.zzgow = obj;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return !this.zzgov;
    }

    @Override // java.util.Iterator
    public final T next() {
        if (this.zzgov) {
            throw new NoSuchElementException();
        }
        this.zzgov = true;
        return (T) this.zzgow;
    }
}
