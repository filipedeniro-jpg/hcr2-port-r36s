package com.google.android.gms.internal.ads;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdsw extends zzdsu<zzdsx, zzdsx> {
    zzdsw() {
    }

    @Override // com.google.android.gms.internal.ads.zzdsu
    final boolean zza(zzdsd zzdsdVar) {
        return false;
    }

    private static void zza(Object obj, zzdsx zzdsxVar) {
        ((zzdqd) obj).zzhkd = zzdsxVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdsu
    final void zzak(Object obj) {
        ((zzdqd) obj).zzhkd.zzaxe();
    }

    @Override // com.google.android.gms.internal.ads.zzdsu
    final /* synthetic */ int zzau(zzdsx zzdsxVar) {
        return zzdsxVar.zzazp();
    }

    @Override // com.google.android.gms.internal.ads.zzdsu
    final /* synthetic */ int zzba(zzdsx zzdsxVar) {
        return zzdsxVar.zzbbu();
    }

    @Override // com.google.android.gms.internal.ads.zzdsu
    final /* synthetic */ zzdsx zzj(zzdsx zzdsxVar, zzdsx zzdsxVar2) {
        zzdsx zzdsxVar3 = zzdsxVar;
        zzdsx zzdsxVar4 = zzdsxVar2;
        return zzdsxVar4.equals(zzdsx.zzbbs()) ? zzdsxVar3 : zzdsx.zza(zzdsxVar3, zzdsxVar4);
    }

    @Override // com.google.android.gms.internal.ads.zzdsu
    final /* synthetic */ void zzc(zzdsx zzdsxVar, zzdtr zzdtrVar) throws IOException {
        zzdsxVar.zza(zzdtrVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdsu
    final /* synthetic */ void zza(zzdsx zzdsxVar, zzdtr zzdtrVar) throws IOException {
        zzdsxVar.zzb(zzdtrVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdsu
    final /* synthetic */ void zzi(Object obj, zzdsx zzdsxVar) {
        zza(obj, zzdsxVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdsu
    final /* synthetic */ zzdsx zzaz(Object obj) {
        zzdsx zzdsxVar = ((zzdqd) obj).zzhkd;
        if (zzdsxVar != zzdsx.zzbbs()) {
            return zzdsxVar;
        }
        zzdsx zzdsxVarZzbbt = zzdsx.zzbbt();
        zza(obj, zzdsxVarZzbbt);
        return zzdsxVarZzbbt;
    }

    @Override // com.google.android.gms.internal.ads.zzdsu
    final /* synthetic */ zzdsx zzay(Object obj) {
        return ((zzdqd) obj).zzhkd;
    }

    @Override // com.google.android.gms.internal.ads.zzdsu
    final /* synthetic */ void zzh(Object obj, zzdsx zzdsxVar) {
        zza(obj, zzdsxVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdsu
    final /* synthetic */ zzdsx zzaq(zzdsx zzdsxVar) {
        zzdsx zzdsxVar2 = zzdsxVar;
        zzdsxVar2.zzaxe();
        return zzdsxVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzdsu
    final /* synthetic */ zzdsx zzbbr() {
        return zzdsx.zzbbt();
    }

    @Override // com.google.android.gms.internal.ads.zzdsu
    final /* synthetic */ void zza(zzdsx zzdsxVar, int i, zzdsx zzdsxVar2) {
        zzdsxVar.zzc((i << 3) | 3, zzdsxVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdsu
    final /* synthetic */ void zza(zzdsx zzdsxVar, int i, zzdot zzdotVar) {
        zzdsxVar.zzc((i << 3) | 2, zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdsu
    final /* synthetic */ void zzb(zzdsx zzdsxVar, int i, long j) {
        zzdsxVar.zzc((i << 3) | 1, Long.valueOf(j));
    }

    @Override // com.google.android.gms.internal.ads.zzdsu
    final /* synthetic */ void zzc(zzdsx zzdsxVar, int i, int i2) {
        zzdsxVar.zzc((i << 3) | 5, Integer.valueOf(i2));
    }

    @Override // com.google.android.gms.internal.ads.zzdsu
    final /* synthetic */ void zza(zzdsx zzdsxVar, int i, long j) {
        zzdsxVar.zzc(i << 3, Long.valueOf(j));
    }
}
