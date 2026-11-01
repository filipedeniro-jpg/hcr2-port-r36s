package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzdpv;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
abstract class zzdps<T extends zzdpv<T>> {
    zzdps() {
    }

    abstract int zza(Map.Entry<?, ?> entry);

    abstract Object zza(zzdpq zzdpqVar, zzdrn zzdrnVar, int i);

    abstract <UT, UB> UB zza(zzdsd zzdsdVar, Object obj, zzdpq zzdpqVar, zzdpt<T> zzdptVar, UB ub, zzdsu<UT, UB> zzdsuVar) throws IOException;

    abstract void zza(zzdot zzdotVar, Object obj, zzdpq zzdpqVar, zzdpt<T> zzdptVar) throws IOException;

    abstract void zza(zzdsd zzdsdVar, Object obj, zzdpq zzdpqVar, zzdpt<T> zzdptVar) throws IOException;

    abstract void zza(zzdtr zzdtrVar, Map.Entry<?, ?> entry) throws IOException;

    abstract zzdpt<T> zzai(Object obj);

    abstract zzdpt<T> zzaj(Object obj);

    abstract void zzak(Object obj);

    abstract boolean zzm(zzdrn zzdrnVar);
}
