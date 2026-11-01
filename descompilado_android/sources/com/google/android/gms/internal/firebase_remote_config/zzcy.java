package com.google.android.gms.internal.firebase_remote_config;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzcy extends zzl {
    zzcy(zzcx zzcxVar) {
        super(zzcxVar);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzd
    protected final void zza(zzf<?> zzfVar) throws IOException {
        super.zza(zzfVar);
    }

    static {
        boolean z = zzb.zzb.intValue() == 1 && zzb.zzc.intValue() >= 15;
        Object[] objArr = {zzb.VERSION};
        if (!z) {
            throw new IllegalStateException(zzdy.zza("You are currently running with version %s of google-api-client. You need at least version 1.15 of google-api-client to run version 1.25.0-SNAPSHOT of the Firebase Remote Config API library.", objArr));
        }
    }
}
