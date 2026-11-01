package com.google.firebase.auth.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzr extends com.google.firebase.auth.zzz {
    private final zzn zza;

    public zzr(zzn zznVar) {
        Preconditions.checkNotNull(zznVar);
        this.zza = zznVar;
    }

    @Override // com.google.firebase.auth.zzz
    public final List<com.google.firebase.auth.zzy> zza() {
        return this.zza.zzl();
    }
}
