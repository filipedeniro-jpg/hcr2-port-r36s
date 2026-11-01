package com.google.android.gms.internal.firebase_remote_config;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: loaded from: classes.dex */
abstract class zzdi<T> implements Iterator<T> {
    private int zzgw = zzdk.zzgz;

    @NullableDecl
    private T zzgx;

    protected zzdi() {
    }

    protected abstract T zzcg();

    @NullableDecl
    protected final T zzch() {
        this.zzgw = zzdk.zzha;
        return null;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        zzdt.checkState(this.zzgw != zzdk.zzhb);
        int i = zzdh.zzgv[this.zzgw - 1];
        if (i == 1) {
            return true;
        }
        if (i != 2) {
            this.zzgw = zzdk.zzhb;
            this.zzgx = zzcg();
            if (this.zzgw != zzdk.zzha) {
                this.zzgw = zzdk.zzgy;
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
        this.zzgw = zzdk.zzgz;
        T t = this.zzgx;
        this.zzgx = null;
        return t;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
