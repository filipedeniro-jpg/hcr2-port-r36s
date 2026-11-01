package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes.dex */
abstract class zzae<T> implements Iterator<T> {
    private int zza = zzag.zzb;

    @NullableDecl
    private T zzb;

    protected zzae() {
    }

    protected abstract T zza();

    @NullableDecl
    protected final T zzb() {
        this.zza = zzag.zzc;
        return null;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (!(this.zza != zzag.zzd)) {
            throw new IllegalStateException();
        }
        int i = zzad.zza[this.zza - 1];
        if (i == 1) {
            return true;
        }
        if (i != 2) {
            this.zza = zzag.zzd;
            this.zzb = zza();
            if (this.zza != zzag.zzc) {
                this.zza = zzag.zza;
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Iterator
    public final T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.zza = zzag.zzb;
        T t = this.zzb;
        this.zzb = null;
        return t;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
