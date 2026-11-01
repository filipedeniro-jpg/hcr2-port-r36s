package com.google.android.gms.internal.firebase_auth;

import java.util.NoSuchElementException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzgp extends zzgr {
    private int zza = 0;
    private final int zzb;
    private final /* synthetic */ zzgm zzc;

    zzgp(zzgm zzgmVar) {
        this.zzc = zzgmVar;
        this.zzb = this.zzc.zza();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza < this.zzb;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgv
    public final byte zza() {
        int i = this.zza;
        if (i >= this.zzb) {
            throw new NoSuchElementException();
        }
        this.zza = i + 1;
        return this.zzc.zzb(i);
    }
}
