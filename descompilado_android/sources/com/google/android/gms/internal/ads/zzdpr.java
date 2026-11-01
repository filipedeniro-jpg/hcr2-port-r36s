package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdpr extends zzdps<Object> {
    zzdpr() {
    }

    @Override // com.google.android.gms.internal.ads.zzdps
    final boolean zzm(zzdrn zzdrnVar) {
        return zzdrnVar instanceof zzdqd.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzdps
    final zzdpt<Object> zzai(Object obj) {
        return ((zzdqd.zzb) obj).zzhkg;
    }

    @Override // com.google.android.gms.internal.ads.zzdps
    final zzdpt<Object> zzaj(Object obj) {
        return ((zzdqd.zzb) obj).zzazu();
    }

    @Override // com.google.android.gms.internal.ads.zzdps
    final void zzak(Object obj) {
        zzai(obj).zzaxe();
    }

    @Override // com.google.android.gms.internal.ads.zzdps
    final <UT, UB> UB zza(zzdsd zzdsdVar, Object obj, zzdpq zzdpqVar, zzdpt<Object> zzdptVar, UB ub, zzdsu<UT, UB> zzdsuVar) throws IOException {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.ads.zzdps
    final int zza(Map.Entry<?, ?> entry) {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.ads.zzdps
    final void zza(zzdtr zzdtrVar, Map.Entry<?, ?> entry) throws IOException {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.ads.zzdps
    final Object zza(zzdpq zzdpqVar, zzdrn zzdrnVar, int i) {
        return zzdpqVar.zza(zzdrnVar, i);
    }

    @Override // com.google.android.gms.internal.ads.zzdps
    final void zza(zzdsd zzdsdVar, Object obj, zzdpq zzdpqVar, zzdpt<Object> zzdptVar) throws IOException {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.ads.zzdps
    final void zza(zzdot zzdotVar, Object obj, zzdpq zzdpqVar, zzdpt<Object> zzdptVar) throws IOException {
        throw new NoSuchMethodError();
    }
}
