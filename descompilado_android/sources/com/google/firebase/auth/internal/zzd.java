package com.google.firebase.auth.internal;

import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzd extends com.google.firebase.auth.zzc {
    private final com.google.firebase.auth.zzy zzb;

    public zzd(String str, com.google.firebase.auth.zzy zzyVar) {
        this.zza = Preconditions.checkNotEmpty(str);
        this.zzb = (com.google.firebase.auth.zzy) Preconditions.checkNotNull(zzyVar);
    }
}
