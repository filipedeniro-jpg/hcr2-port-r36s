package com.google.android.gms.internal.ads;

import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdqy extends zzdqx {
    private zzdqy() {
        super();
    }

    @Override // com.google.android.gms.internal.ads.zzdqx
    final <L> List<L> zza(Object obj, long j) {
        zzdqk zzdqkVarZzc = zzc(obj, j);
        if (zzdqkVarZzc.zzaxd()) {
            return zzdqkVarZzc;
        }
        int size = zzdqkVarZzc.size();
        zzdqk zzdqkVarZzfm = zzdqkVarZzc.zzfm(size == 0 ? 10 : size << 1);
        zzdta.zza(obj, j, zzdqkVarZzfm);
        return zzdqkVarZzfm;
    }

    @Override // com.google.android.gms.internal.ads.zzdqx
    final void zzb(Object obj, long j) {
        zzc(obj, j).zzaxe();
    }

    @Override // com.google.android.gms.internal.ads.zzdqx
    final <E> void zza(Object obj, Object obj2, long j) {
        zzdqk zzdqkVarZzc = zzc(obj, j);
        zzdqk zzdqkVarZzc2 = zzc(obj2, j);
        int size = zzdqkVarZzc.size();
        int size2 = zzdqkVarZzc2.size();
        if (size > 0 && size2 > 0) {
            if (!zzdqkVarZzc.zzaxd()) {
                zzdqkVarZzc = zzdqkVarZzc.zzfm(size2 + size);
            }
            zzdqkVarZzc.addAll(zzdqkVarZzc2);
        }
        if (size > 0) {
            zzdqkVarZzc2 = zzdqkVarZzc;
        }
        zzdta.zza(obj, j, zzdqkVarZzc2);
    }

    private static <E> zzdqk<E> zzc(Object obj, long j) {
        return (zzdqk) zzdta.zzp(obj, j);
    }
}
