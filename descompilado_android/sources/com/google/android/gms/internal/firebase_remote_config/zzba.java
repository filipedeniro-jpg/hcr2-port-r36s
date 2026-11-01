package com.google.android.gms.internal.firebase_remote_config;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import net.hockeyapp.android.Strings;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzba {
    private static WeakHashMap<Class<?>, Field> zzdd = new WeakHashMap<>();
    private static final Lock zzde = new ReentrantLock();

    public abstract void close() throws IOException;

    public abstract int getIntValue() throws IOException;

    public abstract String getText() throws IOException;

    public abstract zzaw zzay();

    public abstract zzbg zzaz() throws IOException;

    public abstract zzbg zzba();

    public abstract String zzbb() throws IOException;

    public abstract zzba zzbc() throws IOException;

    public abstract byte zzbd() throws IOException;

    public abstract short zzbe() throws IOException;

    public abstract float zzbf() throws IOException;

    public abstract long zzbg() throws IOException;

    public abstract double zzbh() throws IOException;

    public abstract BigInteger zzbi() throws IOException;

    public abstract BigDecimal zzbj() throws IOException;

    public final <T> T zza(Class<T> cls, zzau zzauVar) throws IOException {
        try {
            return (T) zza(cls, false, null);
        } finally {
            close();
        }
    }

    public final String zza(Set<String> set) throws IOException {
        zzbg zzbgVarZzbl = zzbl();
        while (zzbgVarZzbl == zzbg.FIELD_NAME) {
            String text = getText();
            zzaz();
            if (set.contains(text)) {
                return text;
            }
            zzbc();
            zzbgVarZzbl = zzaz();
        }
        return null;
    }

    private final zzbg zzbk() throws IOException {
        zzbg zzbgVarZzba = zzba();
        if (zzbgVarZzba == null) {
            zzbgVarZzba = zzaz();
        }
        if (zzbgVarZzba != null) {
            return zzbgVarZzba;
        }
        throw new IllegalArgumentException("no JSON input found");
    }

    private final zzbg zzbl() throws IOException {
        zzbg zzbgVarZzbk = zzbk();
        int i = zzbd.zzdg[zzbgVarZzbk.ordinal()];
        boolean z = true;
        if (i != 1) {
            return i != 2 ? zzbgVarZzbk : zzaz();
        }
        zzbg zzbgVarZzaz = zzaz();
        if (zzbgVarZzaz != zzbg.FIELD_NAME && zzbgVarZzaz != zzbg.END_OBJECT) {
            z = false;
        }
        if (z) {
            return zzbgVarZzaz;
        }
        throw new IllegalArgumentException(String.valueOf(zzbgVarZzaz));
    }

    public final Object zza(Type type, boolean z, zzau zzauVar) throws IOException {
        try {
            if (!Void.class.equals(type)) {
                zzbk();
            }
            return zza(null, type, new ArrayList<>(), null, null, true);
        } finally {
            if (z) {
                close();
            }
        }
    }

    private final void zza(Field field, Map<String, Object> map, Type type, ArrayList<Type> arrayList, zzau zzauVar) throws IOException {
        zzbg zzbgVarZzbl = zzbl();
        while (zzbgVarZzbl == zzbg.FIELD_NAME) {
            String text = getText();
            zzaz();
            if (zzauVar != null) {
                throw new NoSuchMethodError();
            }
            map.put(text, zza(field, type, arrayList, map, zzauVar, true));
            zzbgVarZzbl = zzaz();
        }
    }

    private final Object zza(Field field, Type type, ArrayList<Type> arrayList, Object obj, zzau zzauVar, boolean z) throws IOException {
        Object objZze;
        zzbr zzbrVar;
        Type typeZzbp;
        Type typeZza = zzbt.zza((List<Type>) arrayList, type);
        Type typeZze = null;
        Class<?> clsZza = typeZza instanceof Class ? (Class) typeZza : null;
        if (typeZza instanceof ParameterizedType) {
            clsZza = zzco.zza((ParameterizedType) typeZza);
        }
        if (clsZza == Void.class) {
            zzbc();
            return null;
        }
        zzbg zzbgVarZzba = zzba();
        try {
            boolean z2 = false;
            boolean z3 = true;
            switch (zzbd.zzdg[zzba().ordinal()]) {
                case 1:
                case 4:
                case 5:
                    Object[] objArr = {typeZza};
                    if (!(!zzco.zzc(typeZza))) {
                        throw new IllegalArgumentException(zzdy.zza("expected object or map type but got %s", objArr));
                    }
                    Field fieldZzb = z ? zzb(clsZza) : null;
                    if (clsZza != null && zzauVar != null) {
                        throw new NoSuchMethodError();
                    }
                    boolean z4 = clsZza != null && zzco.zza(clsZza, (Class<?>) Map.class);
                    if (fieldZzb != null) {
                        objZze = new zzax();
                    } else if (z4 || clsZza == null) {
                        objZze = zzbt.zze(clsZza);
                    } else {
                        objZze = zzco.zzf((Class<Object>) clsZza);
                    }
                    Object obj2 = objZze;
                    int size = arrayList.size();
                    if (typeZza != null) {
                        arrayList.add(typeZza);
                    }
                    if (z4 && !zzby.class.isAssignableFrom(clsZza)) {
                        Type typeZzf = Map.class.isAssignableFrom(clsZza) ? zzco.zzf(typeZza) : null;
                        if (typeZzf != null) {
                            zza(field, (Map) obj2, typeZzf, arrayList, zzauVar);
                            return obj2;
                        }
                    }
                    if (obj2 instanceof zzax) {
                        ((zzax) obj2).zza(zzay());
                    }
                    zzbg zzbgVarZzbl = zzbl();
                    Class<?> cls = obj2.getClass();
                    zzbr zzbrVarZzc = zzbr.zzc(cls);
                    boolean zIsAssignableFrom = zzby.class.isAssignableFrom(cls);
                    if (!zIsAssignableFrom && Map.class.isAssignableFrom(cls)) {
                        zza(null, (Map) obj2, zzco.zzf((Type) cls), arrayList, zzauVar);
                    } else {
                        while (zzbgVarZzbl == zzbg.FIELD_NAME) {
                            String text = getText();
                            zzaz();
                            if (zzauVar != null) {
                                throw new NoSuchMethodError();
                            }
                            zzbz zzbzVarZzae = zzbrVarZzc.zzae(text);
                            if (zzbzVarZzae != null) {
                                if (zzbzVarZzae.zzca() && !zzbzVarZzae.isPrimitive()) {
                                    throw new IllegalArgumentException("final array/object fields are not supported");
                                }
                                Field fieldZzbz = zzbzVarZzae.zzbz();
                                int size2 = arrayList.size();
                                arrayList.add(fieldZzbz.getGenericType());
                                zzbrVar = zzbrVarZzc;
                                Object objZza = zza(fieldZzbz, zzbzVarZzae.getGenericType(), arrayList, obj2, zzauVar, true);
                                arrayList.remove(size2);
                                zzbzVarZzae.zzb(obj2, objZza);
                            } else {
                                zzbrVar = zzbrVarZzc;
                                if (zIsAssignableFrom) {
                                    ((zzby) obj2).zzb(text, zza(null, null, arrayList, obj2, zzauVar, true));
                                } else {
                                    if (zzauVar != null) {
                                        throw new NoSuchMethodError();
                                    }
                                    zzbc();
                                }
                            }
                            zzbgVarZzbl = zzaz();
                            zzbrVarZzc = zzbrVar;
                        }
                    }
                    if (typeZza != null) {
                        arrayList.remove(size);
                    }
                    if (fieldZzb == null) {
                        return obj2;
                    }
                    Object obj3 = ((zzax) obj2).get(fieldZzb.getName());
                    if (!(obj3 != null)) {
                        throw new IllegalArgumentException("No value specified for @JsonPolymorphicTypeMap field");
                    }
                    String string = obj3.toString();
                    zzbc.zza[] zzaVarArrZzbn = ((zzbc) fieldZzb.getAnnotation(zzbc.class)).zzbn();
                    int length = zzaVarArrZzbn.length;
                    int i = 0;
                    while (true) {
                        if (i < length) {
                            zzbc.zza zzaVar = zzaVarArrZzbn[i];
                            if (zzaVar.zzbo().equals(string)) {
                                typeZzbp = zzaVar.zzbp();
                            } else {
                                i++;
                            }
                        } else {
                            typeZzbp = null;
                        }
                    }
                    if (typeZzbp == null) {
                        z3 = false;
                    }
                    String strValueOf = String.valueOf(string);
                    String strConcat = strValueOf.length() != 0 ? "No TypeDef annotation found with key: ".concat(strValueOf) : new String("No TypeDef annotation found with key: ");
                    if (!z3) {
                        throw new IllegalArgumentException(String.valueOf(strConcat));
                    }
                    zzaw zzawVarZzay = zzay();
                    zzba zzbaVarZzac = zzawVarZzay.zzac(zzawVarZzay.toString(obj2));
                    zzbaVarZzac.zzbk();
                    return zzbaVarZzac.zza(field, typeZzbp, arrayList, null, null, false);
                case 2:
                case 3:
                    boolean zZzc = zzco.zzc(typeZza);
                    Object[] objArr2 = {typeZza};
                    if (!(typeZza == null || zZzc || (clsZza != null && zzco.zza(clsZza, (Class<?>) Collection.class)))) {
                        throw new IllegalArgumentException(zzdy.zza("expected collection or array type but got %s", objArr2));
                    }
                    if (zzauVar != null && field != null) {
                        throw new NoSuchMethodError();
                    }
                    Collection<Object> collectionZzb = zzbt.zzb(typeZza);
                    if (zZzc) {
                        typeZze = zzco.zzd(typeZza);
                    } else if (clsZza != null && Iterable.class.isAssignableFrom(clsZza)) {
                        typeZze = zzco.zze(typeZza);
                    }
                    Type typeZza2 = zzbt.zza((List<Type>) arrayList, typeZze);
                    zzbg zzbgVarZzbl2 = zzbl();
                    while (zzbgVarZzbl2 != zzbg.END_ARRAY) {
                        collectionZzb.add(zza(field, typeZza2, arrayList, collectionZzb, zzauVar, true));
                        zzbgVarZzbl2 = zzaz();
                    }
                    return zZzc ? zzco.zza((Collection<?>) collectionZzb, zzco.zzb(arrayList, typeZza2)) : collectionZzb;
                case 6:
                case 7:
                    Object[] objArr3 = {typeZza};
                    if (typeZza == null || clsZza == Boolean.TYPE || (clsZza != null && clsZza.isAssignableFrom(Boolean.class))) {
                        return zzbgVarZzba == zzbg.VALUE_TRUE ? Boolean.TRUE : Boolean.FALSE;
                    }
                    throw new IllegalArgumentException(zzdy.zza("expected type Boolean or boolean but got %s", objArr3));
                case 8:
                case 9:
                    if (!(field == null || field.getAnnotation(zzbe.class) == null)) {
                        throw new IllegalArgumentException("number type formatted as a JSON number cannot use @JsonString annotation");
                    }
                    if (clsZza != null && !clsZza.isAssignableFrom(BigDecimal.class)) {
                        if (clsZza == BigInteger.class) {
                            return zzbi();
                        }
                        if (clsZza != Double.class && clsZza != Double.TYPE) {
                            if (clsZza != Long.class && clsZza != Long.TYPE) {
                                if (clsZza != Float.class && clsZza != Float.TYPE) {
                                    if (clsZza != Integer.class && clsZza != Integer.TYPE) {
                                        if (clsZza != Short.class && clsZza != Short.TYPE) {
                                            if (clsZza != Byte.class && clsZza != Byte.TYPE) {
                                                String strValueOf2 = String.valueOf(typeZza);
                                                StringBuilder sb = new StringBuilder(String.valueOf(strValueOf2).length() + 30);
                                                sb.append("expected numeric type but got ");
                                                sb.append(strValueOf2);
                                                throw new IllegalArgumentException(sb.toString());
                                            }
                                            return Byte.valueOf(zzbd());
                                        }
                                        return Short.valueOf(zzbe());
                                    }
                                    return Integer.valueOf(getIntValue());
                                }
                                return Float.valueOf(zzbf());
                            }
                            return Long.valueOf(zzbg());
                        }
                        return Double.valueOf(zzbh());
                    }
                    return zzbj();
                case 10:
                    String lowerCase = getText().trim().toLowerCase(Locale.US);
                    if ((clsZza != Float.TYPE && clsZza != Float.class && clsZza != Double.TYPE && clsZza != Double.class) || (!lowerCase.equals("nan") && !lowerCase.equals("infinity") && !lowerCase.equals("-infinity"))) {
                        if (clsZza == null || !Number.class.isAssignableFrom(clsZza) || (field != null && field.getAnnotation(zzbe.class) != null)) {
                            z2 = true;
                        }
                        if (!z2) {
                            throw new IllegalArgumentException("number field formatted as a JSON string must use the @JsonString annotation");
                        }
                    }
                    return zzbt.zza(typeZza, getText());
                case 11:
                    if (!(clsZza == null || !clsZza.isPrimitive())) {
                        throw new IllegalArgumentException("primitive number field but found a JSON null");
                    }
                    if (clsZza != null && (clsZza.getModifiers() & Strings.PAINT_INDICATOR_TOAST_ID) != 0) {
                        if (zzco.zza(clsZza, (Class<?>) Collection.class)) {
                            return zzbt.zzd(zzbt.zzb(typeZza).getClass());
                        }
                        if (zzco.zza(clsZza, (Class<?>) Map.class)) {
                            return zzbt.zzd(zzbt.zze(clsZza).getClass());
                        }
                    }
                    return zzbt.zzd(zzco.zzb(arrayList, typeZza));
                default:
                    String strValueOf3 = String.valueOf(zzbgVarZzba);
                    StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf3).length() + 27);
                    sb2.append("unexpected JSON node type: ");
                    sb2.append(strValueOf3);
                    throw new IllegalArgumentException(sb2.toString());
            }
        } catch (IllegalArgumentException e) {
            StringBuilder sb3 = new StringBuilder();
            String strZzbb = zzbb();
            if (strZzbb != null) {
                sb3.append("key ");
                sb3.append(strZzbb);
            }
            if (field != null) {
                if (strZzbb != null) {
                    sb3.append(", ");
                }
                sb3.append("field ");
                sb3.append(field);
            }
            throw new IllegalArgumentException(sb3.toString(), e);
        }
    }

    private static Field zzb(Class<?> cls) {
        Field field = null;
        if (cls == null) {
            return null;
        }
        zzde.lock();
        try {
            if (zzdd.containsKey(cls)) {
                Field field2 = zzdd.get(cls);
                zzde.unlock();
                return field2;
            }
            Iterator<zzbz> it = zzbr.zzc(cls).zzbw().iterator();
            while (it.hasNext()) {
                Field fieldZzbz = it.next().zzbz();
                zzbc zzbcVar = (zzbc) fieldZzbz.getAnnotation(zzbc.class);
                if (zzbcVar != null) {
                    Object[] objArr = {cls};
                    if (!(field == null)) {
                        throw new IllegalArgumentException(zzdy.zza("Class contains more than one field with @JsonPolymorphicTypeMap annotation: %s", objArr));
                    }
                    boolean zZza = zzbt.zza(fieldZzbz.getType());
                    Object[] objArr2 = {cls, fieldZzbz.getType()};
                    if (!zZza) {
                        throw new IllegalArgumentException(zzdy.zza("Field which has the @JsonPolymorphicTypeMap, %s, is not a supported type: %s", objArr2));
                    }
                    zzbc.zza[] zzaVarArrZzbn = zzbcVar.zzbn();
                    HashSet hashSet = new HashSet();
                    if (!(zzaVarArrZzbn.length > 0)) {
                        throw new IllegalArgumentException("@JsonPolymorphicTypeMap must have at least one @TypeDef");
                    }
                    for (zzbc.zza zzaVar : zzaVarArrZzbn) {
                        boolean zAdd = hashSet.add(zzaVar.zzbo());
                        Object[] objArr3 = {zzaVar.zzbo()};
                        if (!zAdd) {
                            throw new IllegalArgumentException(zzdy.zza("Class contains two @TypeDef annotations with identical key: %s", objArr3));
                        }
                    }
                    field = fieldZzbz;
                }
            }
            zzdd.put(cls, field);
            zzde.unlock();
            return field;
        } catch (Throwable th) {
            zzde.unlock();
            throw th;
        }
    }
}
