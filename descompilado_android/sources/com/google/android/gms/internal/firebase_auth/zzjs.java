package com.google.android.gms.internal.firebase_auth;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjs {
    private static final zzjs zza = new zzjs();
    private final ConcurrentMap<Class<?>, zzjw<?>> zzc = new ConcurrentHashMap();
    private final zzjz zzb = new zzix();

    public static zzjs zza() {
        return zza;
    }

    public final <T> zzjw<T> zza(Class<T> cls) {
        zzib.zza(cls, "messageType");
        zzjw<T> zzjwVar = (zzjw) this.zzc.get(cls);
        if (zzjwVar != null) {
            return zzjwVar;
        }
        zzjw<T> zzjwVarZza = this.zzb.zza(cls);
        zzib.zza(cls, "messageType");
        zzib.zza(zzjwVarZza, "schema");
        zzjw<T> zzjwVar2 = (zzjw) this.zzc.putIfAbsent(cls, zzjwVarZza);
        return zzjwVar2 != null ? zzjwVar2 : zzjwVarZza;
    }

    public final <T> zzjw<T> zza(T t) {
        return zza((Class) t.getClass());
    }

    private zzjs() {
    }
}
