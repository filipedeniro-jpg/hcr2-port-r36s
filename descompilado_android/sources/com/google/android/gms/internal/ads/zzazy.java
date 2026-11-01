package com.google.android.gms.internal.ads;

import android.os.Build;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzazy implements zzbap {
    @Override // com.google.android.gms.internal.ads.zzbap
    public final zzbah zza(zzayt zzaytVar, int i, String str, zzayq zzayqVar) {
        if (Build.VERSION.SDK_INT < 16 || i <= 0 || !Arrays.asList(zzayqVar.zzdza.split(",")).contains("3")) {
            return new zzbao(zzaytVar);
        }
        int iZzym = zzazm.zzym();
        if (iZzym < zzayqVar.zzdzd) {
            return new zzbas(zzaytVar, zzayqVar);
        }
        return iZzym < zzayqVar.zzdyx ? new zzbat(zzaytVar, zzayqVar) : new zzbar(zzaytVar);
    }
}
