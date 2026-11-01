package com.google.android.gms.internal.firebase_remote_config;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes2.dex */
final class zzjb {
    private static final zzjb zzwk = new zzjb();
    private final ConcurrentMap<Class<?>, zzjf<?>> zzwm = new ConcurrentHashMap();
    private final zzji zzwl = new zzid();

    public static zzjb zzii() {
        return zzwk;
    }

    public final <T> zzjf<T> zzk(Class<T> cls) {
        zzhm.zza(cls, "messageType");
        zzjf<T> zzjfVar = (zzjf) this.zzwm.get(cls);
        if (zzjfVar != null) {
            return zzjfVar;
        }
        zzjf<T> zzjfVarZzj = this.zzwl.zzj(cls);
        zzhm.zza(cls, "messageType");
        zzhm.zza(zzjfVarZzj, "schema");
        zzjf<T> zzjfVar2 = (zzjf) this.zzwm.putIfAbsent(cls, zzjfVarZzj);
        return zzjfVar2 != null ? zzjfVar2 : zzjfVarZzj;
    }

    public final <T> zzjf<T> zzz(T t) {
        return zzk(t.getClass());
    }

    private zzjb() {
    }
}
