package com.google.android.gms.internal.firebase_remote_config;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: classes2.dex */
final class zzcq<T> implements Iterator<T> {
    private int index = 0;
    private final int length;
    private final /* synthetic */ zzcn zzgi;

    zzcq(zzcn zzcnVar) {
        this.zzgi = zzcnVar;
        this.length = Array.getLength(this.zzgi.zzgh);
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.index < this.length;
    }

    @Override // java.util.Iterator
    public final T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Object obj = this.zzgi.zzgh;
        int i = this.index;
        this.index = i + 1;
        return (T) Array.get(obj, i);
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
