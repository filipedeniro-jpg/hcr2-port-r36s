package com.google.android.gms.internal.firebase_remote_config;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes2.dex */
public final class zzbz {
    private static final Map<Field, zzbz> zzem = new WeakHashMap();
    private final String name;
    private final boolean zzfs;
    private final Field zzft;

    public static zzbz zza(Enum<?> r5) {
        try {
            zzbz zzbzVarZza = zza(r5.getClass().getField(r5.name()));
            Object[] objArr = {r5};
            if (zzbzVarZza != null) {
                return zzbzVarZza;
            }
            throw new IllegalArgumentException(zzdy.zza("enum constant missing @Value or @NullValue annotation: %s", objArr));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static zzbz zza(Field field) {
        String strValue = null;
        if (field == null) {
            return null;
        }
        synchronized (zzem) {
            zzbz zzbzVar = zzem.get(field);
            boolean zIsEnumConstant = field.isEnumConstant();
            if (zzbzVar == null && (zIsEnumConstant || !Modifier.isStatic(field.getModifiers()))) {
                if (zIsEnumConstant) {
                    zzcp zzcpVar = (zzcp) field.getAnnotation(zzcp.class);
                    if (zzcpVar != null) {
                        strValue = zzcpVar.value();
                    } else if (((zzcf) field.getAnnotation(zzcf.class)) == null) {
                        return null;
                    }
                } else {
                    zzcc zzccVar = (zzcc) field.getAnnotation(zzcc.class);
                    if (zzccVar == null) {
                        return null;
                    }
                    strValue = zzccVar.value();
                    field.setAccessible(true);
                }
                if ("##default".equals(strValue)) {
                    strValue = field.getName();
                }
                zzbzVar = new zzbz(field, strValue);
                zzem.put(field, zzbzVar);
            }
            return zzbzVar;
        }
    }

    private zzbz(Field field, String str) {
        this.zzft = field;
        this.name = str == null ? null : str.intern();
        this.zzfs = zzbt.zza(this.zzft.getType());
    }

    public final Field zzbz() {
        return this.zzft;
    }

    public final String getName() {
        return this.name;
    }

    public final Type getGenericType() {
        return this.zzft.getGenericType();
    }

    public final boolean zzca() {
        return Modifier.isFinal(this.zzft.getModifiers());
    }

    public final boolean isPrimitive() {
        return this.zzfs;
    }

    public final Object zzh(Object obj) {
        return zza(this.zzft, obj);
    }

    public final void zzb(Object obj, Object obj2) {
        zza(this.zzft, obj, obj2);
    }

    public final <T extends Enum<T>> T zzcb() {
        return (T) Enum.valueOf(this.zzft.getDeclaringClass(), this.zzft.getName());
    }

    private static Object zza(Field field, Object obj) {
        try {
            return field.get(obj);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void zza(Field field, Object obj, Object obj2) {
        if (Modifier.isFinal(field.getModifiers())) {
            Object objZza = zza(field, obj);
            if (obj2 == null) {
                if (objZza == null) {
                    return;
                }
            } else if (obj2.equals(objZza)) {
                return;
            }
            String strValueOf = String.valueOf(objZza);
            String strValueOf2 = String.valueOf(obj2);
            String name = field.getName();
            String name2 = obj.getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 48 + String.valueOf(strValueOf2).length() + String.valueOf(name).length() + String.valueOf(name2).length());
            sb.append("expected final value <");
            sb.append(strValueOf);
            sb.append("> but was <");
            sb.append(strValueOf2);
            sb.append("> on ");
            sb.append(name);
            sb.append(" field in ");
            sb.append(name2);
            throw new IllegalArgumentException(sb.toString());
        }
        try {
            field.set(obj, obj2);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        } catch (SecurityException e2) {
            throw new IllegalArgumentException(e2);
        }
    }
}
