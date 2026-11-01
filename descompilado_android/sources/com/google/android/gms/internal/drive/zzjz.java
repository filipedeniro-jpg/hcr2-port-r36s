package com.google.android.gms.internal.drive;

import java.io.IOException;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class zzjz extends zzjy<Object> {
    zzjz() {
    }

    @Override // com.google.android.gms.internal.drive.zzjy
    final boolean zze(zzlq zzlqVar) {
        return zzlqVar instanceof zzkk.zzc;
    }

    @Override // com.google.android.gms.internal.drive.zzjy
    final zzkb<Object> zzb(Object obj) {
        return ((zzkk.zzc) obj).zzrw;
    }

    @Override // com.google.android.gms.internal.drive.zzjy
    final zzkb<Object> zzc(Object obj) {
        return ((zzkk.zzc) obj).zzdg();
    }

    @Override // com.google.android.gms.internal.drive.zzjy
    final void zzd(Object obj) {
        zzb(obj).zzbp();
    }

    @Override // com.google.android.gms.internal.drive.zzjy
    final int zza(Map.Entry<?, ?> entry) {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.drive.zzjy
    final void zza(zzns zznsVar, Map.Entry<?, ?> entry) throws IOException {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.drive.zzjy
    final Object zza(zzjx zzjxVar, zzlq zzlqVar, int i) {
        return zzjxVar.zza(zzlqVar, i);
    }
}
