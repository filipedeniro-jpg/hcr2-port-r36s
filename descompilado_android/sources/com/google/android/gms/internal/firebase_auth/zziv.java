package com.google.android.gms.internal.firebase_auth;

import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zziv extends zziq {
    private zziv() {
        super();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zziq
    final <L> List<L> zza(Object obj, long j) {
        zzih zzihVarZzc = zzc(obj, j);
        if (zzihVarZzc.zza()) {
            return zzihVarZzc;
        }
        int size = zzihVarZzc.size();
        zzih zzihVarZza = zzihVarZzc.zza(size == 0 ? 10 : size << 1);
        zzky.zza(obj, j, zzihVarZza);
        return zzihVarZza;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zziq
    final void zzb(Object obj, long j) {
        zzc(obj, j).zzb();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zziq
    final <E> void zza(Object obj, Object obj2, long j) {
        zzih zzihVarZzc = zzc(obj, j);
        zzih zzihVarZzc2 = zzc(obj2, j);
        int size = zzihVarZzc.size();
        int size2 = zzihVarZzc2.size();
        if (size > 0 && size2 > 0) {
            if (!zzihVarZzc.zza()) {
                zzihVarZzc = zzihVarZzc.zza(size2 + size);
            }
            zzihVarZzc.addAll(zzihVarZzc2);
        }
        if (size > 0) {
            zzihVarZzc2 = zzihVarZzc;
        }
        zzky.zza(obj, j, zzihVarZzc2);
    }

    private static <E> zzih<E> zzc(Object obj, long j) {
        return (zzih) zzky.zzf(obj, j);
    }
}
