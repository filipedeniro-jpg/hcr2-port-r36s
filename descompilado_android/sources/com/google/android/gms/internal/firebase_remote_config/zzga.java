package com.google.android.gms.internal.firebase_remote_config;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes2.dex */
final class zzga extends zzgc {
    private final int limit;
    private int position = 0;
    private final /* synthetic */ zzfx zzoy;

    zzga(zzfx zzfxVar) {
        this.zzoy = zzfxVar;
        this.limit = this.zzoy.size();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.position < this.limit;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzgg
    public final byte nextByte() {
        int i = this.position;
        if (i >= this.limit) {
            throw new NoSuchElementException();
        }
        this.position = i + 1;
        return this.zzoy.zzw(i);
    }
}
