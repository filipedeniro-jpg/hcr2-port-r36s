package com.google.android.gms.internal.firebase_remote_config;

import com.facebook.appevents.AppEventsConstants;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public final class zzbt {
    private static final Boolean zzes = new Boolean(true);
    private static final String zzet = new String();
    private static final Character zzeu = new Character(0);
    private static final Byte zzev = new Byte((byte) 0);
    private static final Short zzew = new Short((short) 0);
    private static final Integer zzex = new Integer(0);
    private static final Float zzey = new Float(0.0f);
    private static final Long zzez = new Long(0);
    private static final Double zzfa = new Double(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    private static final BigInteger zzfb = new BigInteger(AppEventsConstants.EVENT_PARAM_VALUE_NO);
    private static final BigDecimal zzfc = new BigDecimal(AppEventsConstants.EVENT_PARAM_VALUE_NO);
    private static final zzbw zzfd = new zzbw(0);
    private static final ConcurrentHashMap<Class<?>, Object> zzfe;

    public static <T> T zzd(Class<?> cls) {
        Object objZzf = (T) zzfe.get(cls);
        if (objZzf == null) {
            synchronized (zzfe) {
                objZzf = zzfe.get(cls);
                if (objZzf == null) {
                    int i = 0;
                    if (cls.isArray()) {
                        Class<?> componentType = cls;
                        do {
                            componentType = componentType.getComponentType();
                            i++;
                        } while (componentType.isArray());
                        objZzf = (T) Array.newInstance(componentType, new int[i]);
                    } else if (cls.isEnum()) {
                        zzbz zzbzVarZzae = zzbr.zzc(cls).zzae(null);
                        Object[] objArr = {cls};
                        if (zzbzVarZzae == null) {
                            throw new NullPointerException(zzdy.zza("enum missing constant with @NullValue annotation: %s", objArr));
                        }
                        objZzf = zzbzVarZzae.zzcb();
                    } else {
                        objZzf = zzco.zzf((Class<Object>) cls);
                    }
                    zzfe.put(cls, objZzf);
                }
            }
        }
        return (T) objZzf;
    }

    public static boolean isNull(Object obj) {
        return obj != null && obj == zzfe.get(obj.getClass());
    }

    public static Map<String, Object> zzf(Object obj) {
        if (obj == null || isNull(obj)) {
            return Collections.emptyMap();
        }
        if (obj instanceof Map) {
            return (Map) obj;
        }
        return new zzbs(obj, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T clone(T t) {
        T t2;
        if (t == 0 || zza(t.getClass())) {
            return t;
        }
        if (t instanceof zzby) {
            return (T) ((zzby) ((zzby) t).clone());
        }
        Class<?> cls = t.getClass();
        if (cls.isArray()) {
            t2 = (T) Array.newInstance(cls.getComponentType(), Array.getLength(t));
        } else if (t instanceof zzbl) {
            t2 = (T) ((zzbl) ((zzbl) t).clone());
        } else {
            if ("java.util.Arrays$ArrayList".equals(cls.getName())) {
                Object[] array = ((List) t).toArray();
                zza(array, array);
                return (T) Arrays.asList(array);
            }
            t2 = (T) zzco.zzf((Class) cls);
        }
        zza(t, t2);
        return t2;
    }

    public static void zza(Object obj, Object obj2) {
        Class<?> cls = obj.getClass();
        int i = 0;
        if (!(cls == obj2.getClass())) {
            throw new IllegalArgumentException();
        }
        if (cls.isArray()) {
            if (!(Array.getLength(obj) == Array.getLength(obj2))) {
                throw new IllegalArgumentException();
            }
            Iterator it = zzco.zzi(obj).iterator();
            while (it.hasNext()) {
                Array.set(obj2, i, clone(it.next()));
                i++;
            }
            return;
        }
        if (Collection.class.isAssignableFrom(cls)) {
            Collection collection = (Collection) obj;
            if (ArrayList.class.isAssignableFrom(cls)) {
                ((ArrayList) obj2).ensureCapacity(collection.size());
            }
            Collection collection2 = (Collection) obj2;
            Iterator it2 = collection.iterator();
            while (it2.hasNext()) {
                collection2.add(clone(it2.next()));
            }
            return;
        }
        boolean zIsAssignableFrom = zzby.class.isAssignableFrom(cls);
        if (zIsAssignableFrom || !Map.class.isAssignableFrom(cls)) {
            zzbr zzbrVarZzc = zIsAssignableFrom ? ((zzby) obj).zzay : zzbr.zzc(cls);
            Iterator<String> it3 = zzbrVarZzc.zzer.iterator();
            while (it3.hasNext()) {
                zzbz zzbzVarZzae = zzbrVarZzc.zzae(it3.next());
                if (!zzbzVarZzae.zzca() && (!zIsAssignableFrom || !zzbzVarZzae.isPrimitive())) {
                    Object objZzh = zzbzVarZzae.zzh(obj);
                    if (objZzh != null) {
                        zzbzVarZzae.zzb(obj2, clone(objZzh));
                    }
                }
            }
            return;
        }
        if (zzbl.class.isAssignableFrom(cls)) {
            zzbl zzblVar = (zzbl) obj2;
            zzbl zzblVar2 = (zzbl) obj;
            int size = zzblVar2.size();
            while (i < size) {
                zzblVar.set(i, clone(zzblVar2.zzg(i)));
                i++;
            }
            return;
        }
        Map map = (Map) obj2;
        for (Map.Entry entry : ((Map) obj).entrySet()) {
            map.put((String) entry.getKey(), clone(entry.getValue()));
        }
    }

    public static boolean zza(Type type) {
        if (type instanceof WildcardType) {
            type = zzco.zza((WildcardType) type);
        }
        if (!(type instanceof Class)) {
            return false;
        }
        Class cls = (Class) type;
        return cls.isPrimitive() || cls == Character.class || cls == String.class || cls == Integer.class || cls == Long.class || cls == Short.class || cls == Byte.class || cls == Float.class || cls == Double.class || cls == BigInteger.class || cls == BigDecimal.class || cls == zzbw.class || cls == Boolean.class;
    }

    public static boolean zzg(Object obj) {
        return obj == null || zza(obj.getClass());
    }

    public static Object zza(Type type, String str) {
        Class cls = type instanceof Class ? (Class) type : null;
        if (type == null || cls != null) {
            if (cls == Void.class) {
                return null;
            }
            if (str == null || cls == null || cls.isAssignableFrom(String.class)) {
                return str;
            }
            if (cls == Character.class || cls == Character.TYPE) {
                if (str.length() != 1) {
                    String strValueOf = String.valueOf(cls);
                    StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 37);
                    sb.append("expected type Character/char but got ");
                    sb.append(strValueOf);
                    throw new IllegalArgumentException(sb.toString());
                }
                return Character.valueOf(str.charAt(0));
            }
            if (cls == Boolean.class || cls == Boolean.TYPE) {
                return Boolean.valueOf(str);
            }
            if (cls == Byte.class || cls == Byte.TYPE) {
                return Byte.valueOf(str);
            }
            if (cls == Short.class || cls == Short.TYPE) {
                return Short.valueOf(str);
            }
            if (cls == Integer.class || cls == Integer.TYPE) {
                return Integer.valueOf(str);
            }
            if (cls == Long.class || cls == Long.TYPE) {
                return Long.valueOf(str);
            }
            if (cls == Float.class || cls == Float.TYPE) {
                return Float.valueOf(str);
            }
            if (cls == Double.class || cls == Double.TYPE) {
                return Double.valueOf(str);
            }
            if (cls == zzbw.class) {
                return zzbw.zzaf(str);
            }
            if (cls == BigInteger.class) {
                return new BigInteger(str);
            }
            if (cls == BigDecimal.class) {
                return new BigDecimal(str);
            }
            if (cls.isEnum()) {
                if (!zzbr.zzc(cls).zzer.contains(str)) {
                    throw new IllegalArgumentException(String.format("given enum name %s not part of enumeration", str));
                }
                return zzbr.zzc(cls).zzae(str).zzcb();
            }
        }
        String strValueOf2 = String.valueOf(type);
        StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf2).length() + 35);
        sb2.append("expected primitive class, but got: ");
        sb2.append(strValueOf2);
        throw new IllegalArgumentException(sb2.toString());
    }

    public static Collection<Object> zzb(Type type) {
        if (type instanceof WildcardType) {
            type = zzco.zza((WildcardType) type);
        }
        if (type instanceof ParameterizedType) {
            type = ((ParameterizedType) type).getRawType();
        }
        Class cls = type instanceof Class ? (Class) type : null;
        if (type == null || (type instanceof GenericArrayType) || (cls != null && (cls.isArray() || cls.isAssignableFrom(ArrayList.class)))) {
            return new ArrayList();
        }
        if (cls == null) {
            String strValueOf = String.valueOf(type);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 39);
            sb.append("unable to create new instance of type: ");
            sb.append(strValueOf);
            throw new IllegalArgumentException(sb.toString());
        }
        if (cls.isAssignableFrom(HashSet.class)) {
            return new HashSet();
        }
        if (cls.isAssignableFrom(TreeSet.class)) {
            return new TreeSet();
        }
        return (Collection) zzco.zzf(cls);
    }

    public static Map<String, Object> zze(Class<?> cls) {
        if (cls == null || cls.isAssignableFrom(zzbl.class)) {
            return new zzbl();
        }
        if (cls.isAssignableFrom(TreeMap.class)) {
            return new TreeMap();
        }
        return (Map) zzco.zzf((Class) cls);
    }

    public static Type zza(List<Type> list, Type type) {
        if (type instanceof WildcardType) {
            type = zzco.zza((WildcardType) type);
        }
        while (type instanceof TypeVariable) {
            Type typeZza = zzco.zza(list, (TypeVariable<?>) type);
            if (typeZza != null) {
                type = typeZza;
            }
            if (type instanceof TypeVariable) {
                type = ((TypeVariable) type).getBounds()[0];
            }
        }
        return type;
    }

    static {
        ConcurrentHashMap<Class<?>, Object> concurrentHashMap = new ConcurrentHashMap<>();
        zzfe = concurrentHashMap;
        concurrentHashMap.put(Boolean.class, zzes);
        zzfe.put(String.class, zzet);
        zzfe.put(Character.class, zzeu);
        zzfe.put(Byte.class, zzev);
        zzfe.put(Short.class, zzew);
        zzfe.put(Integer.class, zzex);
        zzfe.put(Float.class, zzey);
        zzfe.put(Long.class, zzez);
        zzfe.put(Double.class, zzfa);
        zzfe.put(BigInteger.class, zzfb);
        zzfe.put(BigDecimal.class, zzfc);
        zzfe.put(zzbw.class, zzfd);
    }
}
