package com.google.android.gms.internal.firebase_auth;

import java.io.IOException;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzho extends zzhm<Object> {
    zzho() {
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhm
    final boolean zza(zzjg zzjgVar) {
        return zzjgVar instanceof zzhx.zzd;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhm
    final zzhq<Object> zza(Object obj) {
        return ((zzhx.zzd) obj).zzc;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhm
    final zzhq<Object> zzb(Object obj) {
        zzhx.zzd zzdVar = (zzhx.zzd) obj;
        if (zzdVar.zzc.zzc()) {
            zzdVar.zzc = (zzhq) zzdVar.zzc.clone();
        }
        return zzdVar.zzc;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhm
    final void zzc(Object obj) {
        zza(obj).zzb();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhm
    final <UT, UB> UB zza(zzjx zzjxVar, Object obj, zzhk zzhkVar, zzhq<Object> zzhqVar, UB ub, zzks<UT, UB> zzksVar) throws IOException {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhm
    final int zza(Map.Entry<?, ?> entry) {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhm
    final void zza(zzll zzllVar, Map.Entry<?, ?> entry) throws IOException {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhm
    final Object zza(zzhk zzhkVar, zzjg zzjgVar, int i) {
        return zzhkVar.zza(zzjgVar, i);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhm
    final void zza(zzjx zzjxVar, Object obj, zzhk zzhkVar, zzhq<Object> zzhqVar) throws IOException {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhm
    final void zza(zzgm zzgmVar, Object obj, zzhk zzhkVar, zzhq<Object> zzhqVar) throws IOException {
        throw new NoSuchMethodError();
    }
}
