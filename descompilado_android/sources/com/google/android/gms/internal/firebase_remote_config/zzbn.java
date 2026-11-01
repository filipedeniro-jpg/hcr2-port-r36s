package com.google.android.gms.internal.firebase_remote_config;

import java.lang.reflect.Field;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class zzbn {
    private final Map<String, zzbm> zzei = new zzbl();
    private final Map<Field, zzbm> zzej = new zzbl();
    private final Object zzek;

    public zzbn(Object obj) {
        this.zzek = obj;
    }

    public final void zzbu() {
        for (Map.Entry<String, zzbm> entry : this.zzei.entrySet()) {
            ((Map) this.zzek).put(entry.getKey(), entry.getValue().zzbt());
        }
        for (Map.Entry<Field, zzbm> entry2 : this.zzej.entrySet()) {
            zzbz.zza(entry2.getKey(), this.zzek, entry2.getValue().zzbt());
        }
    }

    public final void zza(Field field, Class<?> cls, Object obj) {
        zzbm zzbmVar = this.zzej.get(field);
        if (zzbmVar == null) {
            zzbmVar = new zzbm(cls);
            this.zzej.put(field, zzbmVar);
        }
        if (!(cls == zzbmVar.zzeg)) {
            throw new IllegalArgumentException();
        }
        zzbmVar.zzeh.add(obj);
    }
}
