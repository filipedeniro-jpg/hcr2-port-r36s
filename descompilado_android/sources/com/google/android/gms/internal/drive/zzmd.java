package com.google.android.gms.internal.drive;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes2.dex */
final class zzmd {
    private static final zzmd zzuw = new zzmd();
    private final ConcurrentMap<Class<?>, zzmf<?>> zzuy = new ConcurrentHashMap();
    private final zzmg zzux = new zzlf();

    public static zzmd zzej() {
        return zzuw;
    }

    public final <T> zzmf<T> zzf(Class<T> cls) {
        zzkm.zza(cls, "messageType");
        zzmf<T> zzmfVar = (zzmf) this.zzuy.get(cls);
        if (zzmfVar != null) {
            return zzmfVar;
        }
        zzmf<T> zzmfVarZze = this.zzux.zze(cls);
        zzkm.zza(cls, "messageType");
        zzkm.zza(zzmfVarZze, "schema");
        zzmf<T> zzmfVar2 = (zzmf) this.zzuy.putIfAbsent(cls, zzmfVarZze);
        return zzmfVar2 != null ? zzmfVar2 : zzmfVarZze;
    }

    public final <T> zzmf<T> zzq(T t) {
        return zzf(t.getClass());
    }

    private zzmd() {
    }
}
