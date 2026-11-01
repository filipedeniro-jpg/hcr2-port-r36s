package com.google.android.gms.internal.drive;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes2.dex */
final class zzmh {
    private static final Class<?> zzuz = zzep();
    private static final zzmx<?, ?> zzva = zzf(false);
    private static final zzmx<?, ?> zzvb = zzf(true);
    private static final zzmx<?, ?> zzvc = new zzmz();

    public static void zzg(Class<?> cls) {
        Class<?> cls2;
        if (!zzkk.class.isAssignableFrom(cls) && (cls2 = zzuz) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zza(int i, List<Double> list, zzns zznsVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznsVar.zzg(i, list, z);
    }

    public static void zzb(int i, List<Float> list, zzns zznsVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznsVar.zzf(i, list, z);
    }

    public static void zzc(int i, List<Long> list, zzns zznsVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznsVar.zzc(i, list, z);
    }

    public static void zzd(int i, List<Long> list, zzns zznsVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznsVar.zzd(i, list, z);
    }

    public static void zze(int i, List<Long> list, zzns zznsVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznsVar.zzn(i, list, z);
    }

    public static void zzf(int i, List<Long> list, zzns zznsVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznsVar.zze(i, list, z);
    }

    public static void zzg(int i, List<Long> list, zzns zznsVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznsVar.zzl(i, list, z);
    }

    public static void zzh(int i, List<Integer> list, zzns zznsVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznsVar.zza(i, list, z);
    }

    public static void zzi(int i, List<Integer> list, zzns zznsVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznsVar.zzj(i, list, z);
    }

    public static void zzj(int i, List<Integer> list, zzns zznsVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznsVar.zzm(i, list, z);
    }

    public static void zzk(int i, List<Integer> list, zzns zznsVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznsVar.zzb(i, list, z);
    }

    public static void zzl(int i, List<Integer> list, zzns zznsVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznsVar.zzk(i, list, z);
    }

    public static void zzm(int i, List<Integer> list, zzns zznsVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznsVar.zzh(i, list, z);
    }

    public static void zzn(int i, List<Boolean> list, zzns zznsVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznsVar.zzi(i, list, z);
    }

    public static void zza(int i, List<String> list, zzns zznsVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznsVar.zza(i, list);
    }

    public static void zzb(int i, List<zzjc> list, zzns zznsVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznsVar.zzb(i, list);
    }

    public static void zza(int i, List<?> list, zzns zznsVar, zzmf zzmfVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznsVar.zza(i, list, zzmfVar);
    }

    public static void zzb(int i, List<?> list, zzns zznsVar, zzmf zzmfVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznsVar.zzb(i, list, zzmfVar);
    }

    static int zza(List<Long> list) {
        int iZzo;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzle) {
            zzle zzleVar = (zzle) list;
            iZzo = 0;
            while (i < size) {
                iZzo += zzjr.zzo(zzleVar.getLong(i));
                i++;
            }
        } else {
            iZzo = 0;
            while (i < size) {
                iZzo += zzjr.zzo(list.get(i).longValue());
                i++;
            }
        }
        return iZzo;
    }

    static int zzo(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zza(list) + (list.size() * zzjr.zzab(i));
    }

    static int zzb(List<Long> list) {
        int iZzp;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzle) {
            zzle zzleVar = (zzle) list;
            iZzp = 0;
            while (i < size) {
                iZzp += zzjr.zzp(zzleVar.getLong(i));
                i++;
            }
        } else {
            iZzp = 0;
            while (i < size) {
                iZzp += zzjr.zzp(list.get(i).longValue());
                i++;
            }
        }
        return iZzp;
    }

    static int zzp(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzb(list) + (size * zzjr.zzab(i));
    }

    static int zzc(List<Long> list) {
        int iZzq;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzle) {
            zzle zzleVar = (zzle) list;
            iZzq = 0;
            while (i < size) {
                iZzq += zzjr.zzq(zzleVar.getLong(i));
                i++;
            }
        } else {
            iZzq = 0;
            while (i < size) {
                iZzq += zzjr.zzq(list.get(i).longValue());
                i++;
            }
        }
        return iZzq;
    }

    static int zzq(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzc(list) + (size * zzjr.zzab(i));
    }

    static int zzd(List<Integer> list) {
        int iZzah;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkl) {
            zzkl zzklVar = (zzkl) list;
            iZzah = 0;
            while (i < size) {
                iZzah += zzjr.zzah(zzklVar.getInt(i));
                i++;
            }
        } else {
            iZzah = 0;
            while (i < size) {
                iZzah += zzjr.zzah(list.get(i).intValue());
                i++;
            }
        }
        return iZzah;
    }

    static int zzr(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzd(list) + (size * zzjr.zzab(i));
    }

    static int zze(List<Integer> list) {
        int iZzac;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkl) {
            zzkl zzklVar = (zzkl) list;
            iZzac = 0;
            while (i < size) {
                iZzac += zzjr.zzac(zzklVar.getInt(i));
                i++;
            }
        } else {
            iZzac = 0;
            while (i < size) {
                iZzac += zzjr.zzac(list.get(i).intValue());
                i++;
            }
        }
        return iZzac;
    }

    static int zzs(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzjr.zzab(i));
    }

    static int zzf(List<Integer> list) {
        int iZzad;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkl) {
            zzkl zzklVar = (zzkl) list;
            iZzad = 0;
            while (i < size) {
                iZzad += zzjr.zzad(zzklVar.getInt(i));
                i++;
            }
        } else {
            iZzad = 0;
            while (i < size) {
                iZzad += zzjr.zzad(list.get(i).intValue());
                i++;
            }
        }
        return iZzad;
    }

    static int zzt(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzf(list) + (size * zzjr.zzab(i));
    }

    static int zzg(List<Integer> list) {
        int iZzae;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkl) {
            zzkl zzklVar = (zzkl) list;
            iZzae = 0;
            while (i < size) {
                iZzae += zzjr.zzae(zzklVar.getInt(i));
                i++;
            }
        } else {
            iZzae = 0;
            while (i < size) {
                iZzae += zzjr.zzae(list.get(i).intValue());
                i++;
            }
        }
        return iZzae;
    }

    static int zzu(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzg(list) + (size * zzjr.zzab(i));
    }

    static int zzh(List<?> list) {
        return list.size() << 2;
    }

    static int zzv(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzjr.zzj(i, 0);
    }

    static int zzi(List<?> list) {
        return list.size() << 3;
    }

    static int zzw(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzjr.zzg(i, 0L);
    }

    static int zzj(List<?> list) {
        return list.size();
    }

    static int zzx(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzjr.zzc(i, true);
    }

    static int zzc(int i, List<?> list) {
        int iZzm;
        int iZzm2;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int iZzab = zzjr.zzab(i) * size;
        if (list instanceof zzkz) {
            zzkz zzkzVar = (zzkz) list;
            while (i2 < size) {
                Object objZzao = zzkzVar.zzao(i2);
                if (objZzao instanceof zzjc) {
                    iZzm2 = zzjr.zzb((zzjc) objZzao);
                } else {
                    iZzm2 = zzjr.zzm((String) objZzao);
                }
                iZzab += iZzm2;
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof zzjc) {
                    iZzm = zzjr.zzb((zzjc) obj);
                } else {
                    iZzm = zzjr.zzm((String) obj);
                }
                iZzab += iZzm;
                i2++;
            }
        }
        return iZzab;
    }

    static int zzc(int i, Object obj, zzmf zzmfVar) {
        if (obj instanceof zzkx) {
            return zzjr.zza(i, (zzkx) obj);
        }
        return zzjr.zzb(i, (zzlq) obj, zzmfVar);
    }

    static int zzc(int i, List<?> list, zzmf zzmfVar) {
        int iZza;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzab = zzjr.zzab(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof zzkx) {
                iZza = zzjr.zza((zzkx) obj);
            } else {
                iZza = zzjr.zza((zzlq) obj, zzmfVar);
            }
            iZzab += iZza;
        }
        return iZzab;
    }

    static int zzd(int i, List<zzjc> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzab = size * zzjr.zzab(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            iZzab += zzjr.zzb(list.get(i2));
        }
        return iZzab;
    }

    static int zzd(int i, List<zzlq> list, zzmf zzmfVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzc = 0;
        for (int i2 = 0; i2 < size; i2++) {
            iZzc += zzjr.zzc(i, list.get(i2), zzmfVar);
        }
        return iZzc;
    }

    public static zzmx<?, ?> zzem() {
        return zzva;
    }

    public static zzmx<?, ?> zzen() {
        return zzvb;
    }

    public static zzmx<?, ?> zzeo() {
        return zzvc;
    }

    private static zzmx<?, ?> zzf(boolean z) {
        try {
            Class<?> clsZzeq = zzeq();
            if (clsZzeq == null) {
                return null;
            }
            return (zzmx) clsZzeq.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzep() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzeq() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    static boolean zzd(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    static <T> void zza(zzll zzllVar, T t, T t2, long j) {
        zznd.zza(t, j, zzllVar.zzb(zznd.zzo(t, j), zznd.zzo(t2, j)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    static <T, FT extends zzkd<FT>> void zza(zzjy<FT> zzjyVar, T t, T t2) {
        zzkb<T> zzkbVarZzb = zzjyVar.zzb(t2);
        if (zzkbVarZzb.zzos.isEmpty()) {
            return;
        }
        zzjyVar.zzc(t).zza(zzkbVarZzb);
    }

    static <T, UT, UB> void zza(zzmx<UT, UB> zzmxVar, T t, T t2) {
        zzmxVar.zze(t, zzmxVar.zzg(zzmxVar.zzr(t), zzmxVar.zzr(t2)));
    }

    static <UT, UB> UB zza(int i, List<Integer> list, zzko zzkoVar, UB ub, zzmx<UT, UB> zzmxVar) {
        UB ub2;
        if (zzkoVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            ub2 = ub;
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int iIntValue = list.get(i3).intValue();
                if (zzkoVar.zzan(iIntValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(iIntValue));
                    }
                    i2++;
                } else {
                    ub2 = (UB) zza(i, iIntValue, ub2, zzmxVar);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            loop1: while (true) {
                ub2 = ub;
                while (it.hasNext()) {
                    int iIntValue2 = it.next().intValue();
                    if (!zzkoVar.zzan(iIntValue2)) {
                        ub = (UB) zza(i, iIntValue2, ub2, zzmxVar);
                        it.remove();
                    }
                }
                break loop1;
            }
        }
        return ub2;
    }

    private static <UT, UB> UB zza(int i, int i2, UB ub, zzmx<UT, UB> zzmxVar) {
        if (ub == null) {
            ub = zzmxVar.zzez();
        }
        zzmxVar.zza(ub, i, i2);
        return ub;
    }
}
