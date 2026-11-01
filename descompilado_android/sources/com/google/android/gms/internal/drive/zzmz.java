package com.google.android.gms.internal.drive;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
final class zzmz extends zzmx<zzmy, zzmy> {
    zzmz() {
    }

    private static void zza(Object obj, zzmy zzmyVar) {
        ((zzkk) obj).zzrq = zzmyVar;
    }

    @Override // com.google.android.gms.internal.drive.zzmx
    final void zzd(Object obj) {
        ((zzkk) obj).zzrq.zzbp();
    }

    @Override // com.google.android.gms.internal.drive.zzmx
    final /* synthetic */ int zzn(zzmy zzmyVar) {
        return zzmyVar.zzcx();
    }

    @Override // com.google.android.gms.internal.drive.zzmx
    final /* synthetic */ int zzs(zzmy zzmyVar) {
        return zzmyVar.zzfc();
    }

    @Override // com.google.android.gms.internal.drive.zzmx
    final /* synthetic */ zzmy zzg(zzmy zzmyVar, zzmy zzmyVar2) {
        zzmy zzmyVar3 = zzmyVar;
        zzmy zzmyVar4 = zzmyVar2;
        return zzmyVar4.equals(zzmy.zzfa()) ? zzmyVar3 : zzmy.zza(zzmyVar3, zzmyVar4);
    }

    @Override // com.google.android.gms.internal.drive.zzmx
    final /* synthetic */ void zzc(zzmy zzmyVar, zzns zznsVar) throws IOException {
        zzmyVar.zza(zznsVar);
    }

    @Override // com.google.android.gms.internal.drive.zzmx
    final /* synthetic */ void zza(zzmy zzmyVar, zzns zznsVar) throws IOException {
        zzmyVar.zzb(zznsVar);
    }

    @Override // com.google.android.gms.internal.drive.zzmx
    final /* synthetic */ void zzf(Object obj, zzmy zzmyVar) {
        zza(obj, zzmyVar);
    }

    @Override // com.google.android.gms.internal.drive.zzmx
    final /* synthetic */ zzmy zzr(Object obj) {
        return ((zzkk) obj).zzrq;
    }

    @Override // com.google.android.gms.internal.drive.zzmx
    final /* synthetic */ void zze(Object obj, zzmy zzmyVar) {
        zza(obj, zzmyVar);
    }

    @Override // com.google.android.gms.internal.drive.zzmx
    final /* synthetic */ zzmy zzez() {
        return zzmy.zzfb();
    }

    @Override // com.google.android.gms.internal.drive.zzmx
    final /* synthetic */ void zza(zzmy zzmyVar, int i, zzjc zzjcVar) {
        zzmyVar.zzb((i << 3) | 2, zzjcVar);
    }

    @Override // com.google.android.gms.internal.drive.zzmx
    final /* synthetic */ void zza(zzmy zzmyVar, int i, long j) {
        zzmyVar.zzb(i << 3, Long.valueOf(j));
    }
}
