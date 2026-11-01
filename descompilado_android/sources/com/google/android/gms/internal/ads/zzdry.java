package com.google.android.gms.internal.ads;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdry {
    private static final zzdry zzhnf = new zzdry();
    private final ConcurrentMap<Class<?>, zzdsc<?>> zzhnh = new ConcurrentHashMap();
    private final zzdsf zzhng = new zzdra();

    public static zzdry zzbba() {
        return zzhnf;
    }

    public final <T> zzdsc<T> zzh(Class<T> cls) {
        zzdqe.zza(cls, "messageType");
        zzdsc<T> zzdscVar = (zzdsc) this.zzhnh.get(cls);
        if (zzdscVar != null) {
            return zzdscVar;
        }
        zzdsc<T> zzdscVarZzg = this.zzhng.zzg(cls);
        zzdqe.zza(cls, "messageType");
        zzdqe.zza(zzdscVarZzg, "schema");
        zzdsc<T> zzdscVar2 = (zzdsc) this.zzhnh.putIfAbsent(cls, zzdscVarZzg);
        return zzdscVar2 != null ? zzdscVar2 : zzdscVarZzg;
    }

    public final <T> zzdsc<T> zzax(T t) {
        return zzh(t.getClass());
    }

    private zzdry() {
    }
}
