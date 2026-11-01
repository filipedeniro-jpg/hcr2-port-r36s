package com.google.android.gms.internal.firebase_remote_config;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
final class zzh implements zzah {
    private final /* synthetic */ zzah zzab;
    private final /* synthetic */ zzab zzac;
    private final /* synthetic */ zzf zzad;

    zzh(zzf zzfVar, zzah zzahVar, zzab zzabVar) {
        this.zzad = zzfVar;
        this.zzab = zzahVar;
        this.zzac = zzabVar;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzah
    public final void zzb(zzac zzacVar) throws IOException {
        zzah zzahVar = this.zzab;
        if (zzahVar != null) {
            zzahVar.zzb(zzacVar);
        }
        if (!zzacVar.zzad() && this.zzac.zzab()) {
            throw this.zzad.zza(zzacVar);
        }
    }
}
