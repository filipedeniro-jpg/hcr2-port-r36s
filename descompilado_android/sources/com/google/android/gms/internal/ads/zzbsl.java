package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbsl implements zzbjv<zzbka> {
    private final zzbui zzfdc;
    private final Map<String, zzcfk<zzbka>> zzfex;
    private final Map<String, zzcfk<zzbtp>> zzfim;
    private final Map<String, zzchn<zzbtp>> zzfin;
    private final zzdvv<zzbjv<zzbhy>> zzfio;

    zzbsl(Map<String, zzcfk<zzbka>> map, Map<String, zzcfk<zzbtp>> map2, Map<String, zzchn<zzbtp>> map3, zzdvv<zzbjv<zzbhy>> zzdvvVar, zzbui zzbuiVar) {
        this.zzfex = map;
        this.zzfim = map2;
        this.zzfin = map3;
        this.zzfio = zzdvvVar;
        this.zzfdc = zzbuiVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbjv
    @Nullable
    public final zzcfk<zzbka> zze(int i, String str) {
        zzcfk<zzbhy> zzcfkVarZze;
        zzcfk<zzbka> zzcfkVar = this.zzfex.get(str);
        if (zzcfkVar != null) {
            return zzcfkVar;
        }
        if (i == 1) {
            if (this.zzfdc.zzaid() == null || (zzcfkVarZze = this.zzfio.get().zze(i, str)) == null) {
                return null;
            }
            return zzbka.zza(zzcfkVarZze);
        }
        if (i != 4) {
            return null;
        }
        zzchn<zzbtp> zzchnVar = this.zzfin.get(str);
        if (zzchnVar != null) {
            return zzbka.zza((zzchn<? extends zzbju>) zzchnVar);
        }
        zzcfk<zzbtp> zzcfkVar2 = this.zzfim.get(str);
        if (zzcfkVar2 != null) {
            return zzbka.zza(zzcfkVar2);
        }
        return null;
    }
}
