package com.google.android.gms.internal.firebase_remote_config;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes2.dex */
public final class zzbr {
    private static final Map<Class<?>, zzbr> zzem = new WeakHashMap();
    private static final Map<Class<?>, zzbr> zzen = new WeakHashMap();
    private final Class<?> zzeo;
    private final boolean zzep;
    private final IdentityHashMap<String, zzbz> zzeq = new IdentityHashMap<>();
    final List<String> zzer;

    public static zzbr zzc(Class<?> cls) {
        return zza(cls, false);
    }

    public static zzbr zza(Class<?> cls, boolean z) {
        zzbr zzbrVar;
        if (cls == null) {
            return null;
        }
        Map<Class<?>, zzbr> map = z ? zzen : zzem;
        synchronized (map) {
            zzbrVar = map.get(cls);
            if (zzbrVar == null) {
                zzbrVar = new zzbr(cls, z);
                map.put(cls, zzbrVar);
            }
        }
        return zzbrVar;
    }

    public final boolean zzbv() {
        return this.zzep;
    }

    public final zzbz zzae(String str) {
        if (str != null) {
            if (this.zzep) {
                str = str.toLowerCase(Locale.US);
            }
            str = str.intern();
        }
        return this.zzeq.get(str);
    }

    public final boolean isEnum() {
        return this.zzeo.isEnum();
    }

    private zzbr(Class<?> cls, boolean z) {
        this.zzeo = cls;
        this.zzep = z;
        boolean z2 = (z && cls.isEnum()) ? false : true;
        String strValueOf = String.valueOf(cls);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 31);
        sb.append("cannot ignore case on an enum: ");
        sb.append(strValueOf);
        String string = sb.toString();
        if (!z2) {
            throw new IllegalArgumentException(String.valueOf(string));
        }
        TreeSet treeSet = new TreeSet(new zzbq(this));
        for (Field field : cls.getDeclaredFields()) {
            zzbz zzbzVarZza = zzbz.zza(field);
            if (zzbzVarZza != null) {
                String name = zzbzVarZza.getName();
                name = z ? name.toLowerCase(Locale.US).intern() : name;
                zzbz zzbzVar = this.zzeq.get(name);
                boolean z3 = zzbzVar == null;
                Object[] objArr = new Object[4];
                objArr[0] = z ? "case-insensitive " : "";
                objArr[1] = name;
                objArr[2] = field;
                objArr[3] = zzbzVar == null ? null : zzbzVar.zzbz();
                if (!z3) {
                    throw new IllegalArgumentException(zzdy.zza("two fields have the same %sname <%s>: %s and %s", objArr));
                }
                this.zzeq.put(name, zzbzVarZza);
                treeSet.add(name);
            }
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass != null) {
            zzbr zzbrVarZza = zza(superclass, z);
            treeSet.addAll(zzbrVarZza.zzer);
            for (Map.Entry<String, zzbz> entry : zzbrVarZza.zzeq.entrySet()) {
                String key = entry.getKey();
                if (!this.zzeq.containsKey(key)) {
                    this.zzeq.put(key, entry.getValue());
                }
            }
        }
        this.zzer = treeSet.isEmpty() ? Collections.emptyList() : Collections.unmodifiableList(new ArrayList(treeSet));
    }

    public final Collection<zzbz> zzbw() {
        return Collections.unmodifiableCollection(this.zzeq.values());
    }
}
