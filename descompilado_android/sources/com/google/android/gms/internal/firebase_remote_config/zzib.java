package com.google.android.gms.internal.firebase_remote_config;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
final class zzib extends zzia {
    private zzib() {
        super();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzia
    final <L> List<L> zza(Object obj, long j) {
        zzhn zzhnVarZzc = zzc(obj, j);
        if (zzhnVarZzc.zzer()) {
            return zzhnVarZzc;
        }
        int size = zzhnVarZzc.size();
        zzhn zzhnVarZzu = zzhnVarZzc.zzu(size == 0 ? 10 : size << 1);
        zzkd.zza(obj, j, zzhnVarZzu);
        return zzhnVarZzu;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzia
    final void zzb(Object obj, long j) {
        zzc(obj, j).zzes();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzia
    final <E> void zza(Object obj, Object obj2, long j) {
        zzhn zzhnVarZzc = zzc(obj, j);
        zzhn zzhnVarZzc2 = zzc(obj2, j);
        int size = zzhnVarZzc.size();
        int size2 = zzhnVarZzc2.size();
        if (size > 0 && size2 > 0) {
            if (!zzhnVarZzc.zzer()) {
                zzhnVarZzc = zzhnVarZzc.zzu(size2 + size);
            }
            zzhnVarZzc.addAll(zzhnVarZzc2);
        }
        if (size > 0) {
            zzhnVarZzc2 = zzhnVarZzc;
        }
        zzkd.zza(obj, j, zzhnVarZzc2);
    }

    private static <E> zzhn<E> zzc(Object obj, long j) {
        return (zzhn) zzkd.zzp(obj, j);
    }
}
