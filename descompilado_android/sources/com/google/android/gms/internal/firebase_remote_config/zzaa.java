package com.google.android.gms.internal.firebase_remote_config;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzaa {
    private final zzad zzba;
    private final zzag zzx;

    zzaa(zzag zzagVar, zzad zzadVar) {
        this.zzx = zzagVar;
        this.zzba = zzadVar;
    }

    public final zzab zza(String str, zzt zztVar, zzs zzsVar) throws IOException {
        zzab zzabVar = new zzab(this.zzx, null);
        zzad zzadVar = this.zzba;
        if (zzadVar != null) {
            zzadVar.zza(zzabVar);
        }
        zzabVar.zzw(str);
        zzabVar.zza(zztVar);
        if (zzsVar != null) {
            zzabVar.zza(zzsVar);
        }
        return zzabVar;
    }
}
