package com.google.android.gms.internal.drive;

/* JADX INFO: loaded from: classes2.dex */
final class zzld extends zzla {
    private zzld() {
        super();
    }

    @Override // com.google.android.gms.internal.drive.zzla
    final void zza(Object obj, long j) {
        zzc(obj, j).zzbp();
    }

    @Override // com.google.android.gms.internal.drive.zzla
    final <E> void zza(Object obj, Object obj2, long j) {
        zzkp zzkpVarZzc = zzc(obj, j);
        zzkp zzkpVarZzc2 = zzc(obj2, j);
        int size = zzkpVarZzc.size();
        int size2 = zzkpVarZzc2.size();
        if (size > 0 && size2 > 0) {
            if (!zzkpVarZzc.zzbo()) {
                zzkpVarZzc = zzkpVarZzc.zzr(size2 + size);
            }
            zzkpVarZzc.addAll(zzkpVarZzc2);
        }
        if (size > 0) {
            zzkpVarZzc2 = zzkpVarZzc;
        }
        zznd.zza(obj, j, zzkpVarZzc2);
    }

    private static <E> zzkp<E> zzc(Object obj, long j) {
        return (zzkp) zznd.zzo(obj, j);
    }
}
