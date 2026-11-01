package com.google.android.gms.internal.firebase_remote_config;

import java.util.AbstractMap;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
final class zzbs extends AbstractMap<String, Object> {
    final Object object;
    final zzbr zzay;

    zzbs(Object obj, boolean z) {
        this.object = obj;
        this.zzay = zzbr.zza(obj.getClass(), z);
        if (!(!this.zzay.isEnum())) {
            throw new IllegalArgumentException();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        zzbz zzbzVarZzae;
        if ((obj instanceof String) && (zzbzVarZzae = this.zzay.zzae((String) obj)) != null) {
            return zzbzVarZzae.zzh(this.object);
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final /* synthetic */ Set entrySet() {
        return new zzbx(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final /* synthetic */ Object put(Object obj, Object obj2) {
        String str = (String) obj;
        zzbz zzbzVarZzae = this.zzay.zzae(str);
        String strValueOf = String.valueOf(str);
        String strConcat = strValueOf.length() != 0 ? "no field of key ".concat(strValueOf) : new String("no field of key ");
        if (zzbzVarZzae == null) {
            throw new NullPointerException(String.valueOf(strConcat));
        }
        Object objZzh = zzbzVarZzae.zzh(this.object);
        zzbzVarZzae.zzb(this.object, zzdt.checkNotNull(obj2));
        return objZzh;
    }
}
