package com.google.android.gms.internal.firebase_remote_config;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
final class zzjz extends zzjx<zzka, zzka> {
    zzjz() {
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjx
    final boolean zza(zzjg zzjgVar) {
        return false;
    }

    private static void zza(Object obj, zzka zzkaVar) {
        ((zzhi) obj).zztc = zzkaVar;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjx
    final void zzm(Object obj) {
        ((zzhi) obj).zztc.zzes();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjx
    final /* synthetic */ int zzw(zzka zzkaVar) {
        return zzkaVar.zzgs();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjx
    final /* synthetic */ int zzac(zzka zzkaVar) {
        return zzkaVar.zzjc();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjx
    final /* synthetic */ zzka zzi(zzka zzkaVar, zzka zzkaVar2) {
        zzka zzkaVar3 = zzkaVar;
        zzka zzkaVar4 = zzkaVar2;
        return zzkaVar4.equals(zzka.zzja()) ? zzkaVar3 : zzka.zza(zzkaVar3, zzkaVar4);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjx
    final /* synthetic */ void zzc(zzka zzkaVar, zzku zzkuVar) throws IOException {
        zzkaVar.zza(zzkuVar);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjx
    final /* synthetic */ void zza(zzka zzkaVar, zzku zzkuVar) throws IOException {
        zzkaVar.zzb(zzkuVar);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjx
    final /* synthetic */ void zzh(Object obj, zzka zzkaVar) {
        zza(obj, zzkaVar);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjx
    final /* synthetic */ zzka zzab(Object obj) {
        zzka zzkaVar = ((zzhi) obj).zztc;
        if (zzkaVar != zzka.zzja()) {
            return zzkaVar;
        }
        zzka zzkaVarZzjb = zzka.zzjb();
        zza(obj, zzkaVarZzjb);
        return zzkaVarZzjb;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjx
    final /* synthetic */ zzka zzaa(Object obj) {
        return ((zzhi) obj).zztc;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjx
    final /* synthetic */ void zzg(Object obj, zzka zzkaVar) {
        zza(obj, zzkaVar);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjx
    final /* synthetic */ zzka zzs(zzka zzkaVar) {
        zzka zzkaVar2 = zzkaVar;
        zzkaVar2.zzes();
        return zzkaVar2;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjx
    final /* synthetic */ zzka zziz() {
        return zzka.zzjb();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjx
    final /* synthetic */ void zza(zzka zzkaVar, int i, zzka zzkaVar2) {
        zzkaVar.zzb((i << 3) | 3, zzkaVar2);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjx
    final /* synthetic */ void zza(zzka zzkaVar, int i, zzfx zzfxVar) {
        zzkaVar.zzb((i << 3) | 2, zzfxVar);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjx
    final /* synthetic */ void zzb(zzka zzkaVar, int i, long j) {
        zzkaVar.zzb((i << 3) | 1, Long.valueOf(j));
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjx
    final /* synthetic */ void zzc(zzka zzkaVar, int i, int i2) {
        zzkaVar.zzb((i << 3) | 5, Integer.valueOf(i2));
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjx
    final /* synthetic */ void zza(zzka zzkaVar, int i, long j) {
        zzkaVar.zzb(i << 3, Long.valueOf(j));
    }
}
