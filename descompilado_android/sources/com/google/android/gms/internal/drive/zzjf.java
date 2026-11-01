package com.google.android.gms.internal.drive;

/* JADX INFO: loaded from: classes2.dex */
abstract class zzjf implements zzjj {
    zzjf() {
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Iterator
    public /* synthetic */ Byte next() {
        return Byte.valueOf(nextByte());
    }
}
