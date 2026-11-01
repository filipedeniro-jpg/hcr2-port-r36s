package com.google.android.gms.internal.firebase_auth;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzku extends zzks<zzkr, zzkr> {
    zzku() {
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzks
    final boolean zza(zzjx zzjxVar) {
        return false;
    }

    /* JADX INFO: renamed from: zza, reason: avoid collision after fix types in other method */
    private static void zza2(Object obj, zzkr zzkrVar) {
        ((zzhx) obj).zzb = zzkrVar;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzks
    final void zzd(Object obj) {
        ((zzhx) obj).zzb.zzc();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzks
    final /* synthetic */ int zzf(zzkr zzkrVar) {
        return zzkrVar.zze();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzks
    final /* synthetic */ int zze(zzkr zzkrVar) {
        return zzkrVar.zzd();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzks
    final /* synthetic */ zzkr zzc(zzkr zzkrVar, zzkr zzkrVar2) {
        zzkr zzkrVar3 = zzkrVar;
        zzkr zzkrVar4 = zzkrVar2;
        return zzkrVar4.equals(zzkr.zza()) ? zzkrVar3 : zzkr.zza(zzkrVar3, zzkrVar4);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzks
    final /* synthetic */ void zzb(zzkr zzkrVar, zzll zzllVar) throws IOException {
        zzkrVar.zza(zzllVar);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzks
    final /* synthetic */ void zza(zzkr zzkrVar, zzll zzllVar) throws IOException {
        zzkrVar.zzb(zzllVar);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzks
    final /* synthetic */ void zzb(Object obj, zzkr zzkrVar) {
        zza2(obj, zzkrVar);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzks
    final /* synthetic */ zzkr zzc(Object obj) {
        zzkr zzkrVar = ((zzhx) obj).zzb;
        if (zzkrVar != zzkr.zza()) {
            return zzkrVar;
        }
        zzkr zzkrVarZzb = zzkr.zzb();
        zza2(obj, zzkrVarZzb);
        return zzkrVarZzb;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzks
    final /* synthetic */ zzkr zzb(Object obj) {
        return ((zzhx) obj).zzb;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzks
    final /* bridge */ /* synthetic */ void zza(Object obj, zzkr zzkrVar) {
        zza2(obj, zzkrVar);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzks
    final /* synthetic */ zzkr zza(zzkr zzkrVar) {
        zzkr zzkrVar2 = zzkrVar;
        zzkrVar2.zzc();
        return zzkrVar2;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzks
    final /* synthetic */ zzkr zza() {
        return zzkr.zzb();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzks
    final /* synthetic */ void zza(zzkr zzkrVar, int i, zzkr zzkrVar2) {
        zzkrVar.zza((i << 3) | 3, zzkrVar2);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzks
    final /* synthetic */ void zza(zzkr zzkrVar, int i, zzgm zzgmVar) {
        zzkrVar.zza((i << 3) | 2, zzgmVar);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzks
    final /* synthetic */ void zzb(zzkr zzkrVar, int i, long j) {
        zzkrVar.zza((i << 3) | 1, Long.valueOf(j));
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzks
    final /* synthetic */ void zza(zzkr zzkrVar, int i, int i2) {
        zzkrVar.zza((i << 3) | 5, Integer.valueOf(i2));
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzks
    final /* synthetic */ void zza(zzkr zzkrVar, int i, long j) {
        zzkrVar.zza(i << 3, Long.valueOf(j));
    }
}
