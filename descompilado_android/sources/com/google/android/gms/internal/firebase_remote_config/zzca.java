package com.google.android.gms.internal.firebase_remote_config;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzca {
    public static long zzb(zzcm zzcmVar) throws IOException {
        zzbp zzbpVar = new zzbp();
        try {
            zzcmVar.writeTo(zzbpVar);
            return zzbpVar.zzel;
        } finally {
            zzbpVar.close();
        }
    }
}
