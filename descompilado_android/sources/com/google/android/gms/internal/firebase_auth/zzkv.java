package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzkv implements Iterator<String> {
    private Iterator<String> zza;
    private final /* synthetic */ zzkt zzb;

    zzkv(zzkt zzktVar) {
        this.zzb = zzktVar;
        this.zza = this.zzb.zza.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ String next() {
        return this.zza.next();
    }
}
