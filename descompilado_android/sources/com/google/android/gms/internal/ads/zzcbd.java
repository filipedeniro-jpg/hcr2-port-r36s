package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.AppEventListener;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcbd {
    public static Set<zzbqc<zzbms>> zza(zzcbn zzcbnVar, Executor executor) {
        return zzc(zzcbnVar, executor);
    }

    public static Set<zzbqc<AppEventListener>> zzb(zzcbn zzcbnVar, Executor executor) {
        return zzc(zzcbnVar, executor);
    }

    public static Set<zzbqc<zzbnm>> zzc(zzcbn zzcbnVar, Executor executor) {
        return zzc(zzcbnVar, executor);
    }

    public static Set<zzbqc<zzbml>> zzd(zzcbn zzcbnVar, Executor executor) {
        return zzc(zzcbnVar, executor);
    }

    public static Set<zzbqc<zzbmg>> zze(zzcbn zzcbnVar, Executor executor) {
        return zzc(zzcbnVar, executor);
    }

    public static Set<zzbqc<zzbmt>> zzf(zzcbn zzcbnVar, Executor executor) {
        return zzc(zzcbnVar, executor);
    }

    public static Set<zzbqc<zzth>> zzg(zzcbn zzcbnVar, Executor executor) {
        return zzc(zzcbnVar, executor);
    }

    public static Set<zzbqc<zzcxt>> zzh(zzcbn zzcbnVar, Executor executor) {
        return zzc(zzcbnVar, executor);
    }

    public static Set<zzbqc<zzboi>> zzi(zzcbn zzcbnVar, Executor executor) {
        return zzc(zzcbnVar, executor);
    }

    private static <T> Set<zzbqc<T>> zzc(T t, Executor executor) {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzclp)).booleanValue()) {
            return Collections.singleton(new zzbqc(t, executor));
        }
        return Collections.emptySet();
    }
}
