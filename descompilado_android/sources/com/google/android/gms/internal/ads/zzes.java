package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzes implements Callable {
    private final zzbo.zza.zzb zzaaa;
    private final zzdx zzvo;

    public zzes(zzdx zzdxVar, zzbo.zza.zzb zzbVar) {
        this.zzvo = zzdxVar;
        this.zzaaa = zzbVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: zzcv, reason: merged with bridge method [inline-methods] */
    public final Void call() throws Exception {
        if (this.zzvo.zzcm() != null) {
            this.zzvo.zzcm().get();
        }
        zzbo.zza zzaVarZzcl = this.zzvo.zzcl();
        if (zzaVarZzcl == null) {
            return null;
        }
        try {
            synchronized (this.zzaaa) {
                zzbo.zza.zzb zzbVar = this.zzaaa;
                byte[] byteArray = zzaVarZzcl.toByteArray();
                zzbVar.zza(byteArray, 0, byteArray.length, zzdpq.zzayw());
            }
            return null;
        } catch (zzdqn unused) {
            return null;
        }
    }
}
