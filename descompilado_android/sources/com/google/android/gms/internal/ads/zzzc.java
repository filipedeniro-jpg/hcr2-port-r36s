package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzzc {
    public static boolean zza(@Nullable zzzh zzzhVar, @Nullable zzzf zzzfVar, String... strArr) {
        if (zzzhVar == null || zzzfVar == null || !zzzhVar.zzcuc || zzzfVar == null) {
            return false;
        }
        return zzzhVar.zza(zzzfVar, com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime(), strArr);
    }

    @Nullable
    public static zzzf zzb(@Nullable zzzh zzzhVar) {
        if (zzzhVar == null) {
            return null;
        }
        return zzzhVar.zzer(com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime());
    }
}
