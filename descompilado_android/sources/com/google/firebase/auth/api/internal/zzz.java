package com.google.firebase.auth.api.internal;

import androidx.annotation.Nullable;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzz implements zzfe<com.google.android.gms.internal.firebase_auth.zzeq> {
    private final /* synthetic */ zzfe zza;
    private final /* synthetic */ com.google.android.gms.internal.firebase_auth.zzey zzb;
    private final /* synthetic */ zzaa zzc;

    zzz(zzaa zzaaVar, zzfe zzfeVar, com.google.android.gms.internal.firebase_auth.zzey zzeyVar) {
        this.zzc = zzaaVar;
        this.zza = zzfeVar;
        this.zzb = zzeyVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfb
    public final void zza(@Nullable String str) {
        this.zza.zza(str);
    }

    @Override // com.google.firebase.auth.api.internal.zzfe
    public final /* synthetic */ void zza(com.google.android.gms.internal.firebase_auth.zzeq zzeqVar) {
        List<com.google.android.gms.internal.firebase_auth.zzes> listZzb = zzeqVar.zzb();
        if (listZzb == null || listZzb.isEmpty()) {
            this.zza.zza("No users");
        } else {
            this.zzc.zza.zza(this.zzb, listZzb.get(0));
        }
    }
}
