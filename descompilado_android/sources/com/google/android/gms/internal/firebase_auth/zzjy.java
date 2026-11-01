package com.google.android.gms.internal.firebase_auth;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjy {
    private static final Class<?> zza = zzd();
    private static final zzks<?, ?> zzb = zza(false);
    private static final zzks<?, ?> zzc = zza(true);
    private static final zzks<?, ?> zzd = new zzku();

    public static void zza(Class<?> cls) {
        Class<?> cls2;
        if (!zzhx.class.isAssignableFrom(cls) && (cls2 = zza) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zza(int i, List<Double> list, zzll zzllVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzllVar.zzg(i, list, z);
    }

    public static void zzb(int i, List<Float> list, zzll zzllVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzllVar.zzf(i, list, z);
    }

    public static void zzc(int i, List<Long> list, zzll zzllVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzllVar.zzc(i, list, z);
    }

    public static void zzd(int i, List<Long> list, zzll zzllVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzllVar.zzd(i, list, z);
    }

    public static void zze(int i, List<Long> list, zzll zzllVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzllVar.zzn(i, list, z);
    }

    public static void zzf(int i, List<Long> list, zzll zzllVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzllVar.zze(i, list, z);
    }

    public static void zzg(int i, List<Long> list, zzll zzllVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzllVar.zzl(i, list, z);
    }

    public static void zzh(int i, List<Integer> list, zzll zzllVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzllVar.zza(i, list, z);
    }

    public static void zzi(int i, List<Integer> list, zzll zzllVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzllVar.zzj(i, list, z);
    }

    public static void zzj(int i, List<Integer> list, zzll zzllVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzllVar.zzm(i, list, z);
    }

    public static void zzk(int i, List<Integer> list, zzll zzllVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzllVar.zzb(i, list, z);
    }

    public static void zzl(int i, List<Integer> list, zzll zzllVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzllVar.zzk(i, list, z);
    }

    public static void zzm(int i, List<Integer> list, zzll zzllVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzllVar.zzh(i, list, z);
    }

    public static void zzn(int i, List<Boolean> list, zzll zzllVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzllVar.zzi(i, list, z);
    }

    public static void zza(int i, List<String> list, zzll zzllVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzllVar.zza(i, list);
    }

    public static void zzb(int i, List<zzgm> list, zzll zzllVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzllVar.zzb(i, list);
    }

    public static void zza(int i, List<?> list, zzll zzllVar, zzjw zzjwVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzllVar.zza(i, list, zzjwVar);
    }

    public static void zzb(int i, List<?> list, zzll zzllVar, zzjw zzjwVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzllVar.zzb(i, list, zzjwVar);
    }

    static int zza(List<Long> list) {
        int iZzd;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zziu) {
            zziu zziuVar = (zziu) list;
            iZzd = 0;
            while (i < size) {
                iZzd += zzhh.zzd(zziuVar.zzb(i));
                i++;
            }
        } else {
            iZzd = 0;
            while (i < size) {
                iZzd += zzhh.zzd(list.get(i).longValue());
                i++;
            }
        }
        return iZzd;
    }

    static int zza(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zza(list) + (list.size() * zzhh.zze(i));
    }

    static int zzb(List<Long> list) {
        int iZze;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zziu) {
            zziu zziuVar = (zziu) list;
            iZze = 0;
            while (i < size) {
                iZze += zzhh.zze(zziuVar.zzb(i));
                i++;
            }
        } else {
            iZze = 0;
            while (i < size) {
                iZze += zzhh.zze(list.get(i).longValue());
                i++;
            }
        }
        return iZze;
    }

    static int zzb(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzb(list) + (size * zzhh.zze(i));
    }

    static int zzc(List<Long> list) {
        int iZzf;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zziu) {
            zziu zziuVar = (zziu) list;
            iZzf = 0;
            while (i < size) {
                iZzf += zzhh.zzf(zziuVar.zzb(i));
                i++;
            }
        } else {
            iZzf = 0;
            while (i < size) {
                iZzf += zzhh.zzf(list.get(i).longValue());
                i++;
            }
        }
        return iZzf;
    }

    static int zzc(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzc(list) + (size * zzhh.zze(i));
    }

    static int zzd(List<Integer> list) {
        int iZzk;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhz) {
            zzhz zzhzVar = (zzhz) list;
            iZzk = 0;
            while (i < size) {
                iZzk += zzhh.zzk(zzhzVar.zzc(i));
                i++;
            }
        } else {
            iZzk = 0;
            while (i < size) {
                iZzk += zzhh.zzk(list.get(i).intValue());
                i++;
            }
        }
        return iZzk;
    }

    static int zzd(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzd(list) + (size * zzhh.zze(i));
    }

    static int zze(List<Integer> list) {
        int iZzf;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhz) {
            zzhz zzhzVar = (zzhz) list;
            iZzf = 0;
            while (i < size) {
                iZzf += zzhh.zzf(zzhzVar.zzc(i));
                i++;
            }
        } else {
            iZzf = 0;
            while (i < size) {
                iZzf += zzhh.zzf(list.get(i).intValue());
                i++;
            }
        }
        return iZzf;
    }

    static int zze(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzhh.zze(i));
    }

    static int zzf(List<Integer> list) {
        int iZzg;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhz) {
            zzhz zzhzVar = (zzhz) list;
            iZzg = 0;
            while (i < size) {
                iZzg += zzhh.zzg(zzhzVar.zzc(i));
                i++;
            }
        } else {
            iZzg = 0;
            while (i < size) {
                iZzg += zzhh.zzg(list.get(i).intValue());
                i++;
            }
        }
        return iZzg;
    }

    static int zzf(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzf(list) + (size * zzhh.zze(i));
    }

    static int zzg(List<Integer> list) {
        int iZzh;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhz) {
            zzhz zzhzVar = (zzhz) list;
            iZzh = 0;
            while (i < size) {
                iZzh += zzhh.zzh(zzhzVar.zzc(i));
                i++;
            }
        } else {
            iZzh = 0;
            while (i < size) {
                iZzh += zzhh.zzh(list.get(i).intValue());
                i++;
            }
        }
        return iZzh;
    }

    static int zzg(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzg(list) + (size * zzhh.zze(i));
    }

    static int zzh(List<?> list) {
        return list.size() << 2;
    }

    static int zzh(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzhh.zzi(i, 0);
    }

    static int zzi(List<?> list) {
        return list.size() << 3;
    }

    static int zzi(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzhh.zzg(i, 0L);
    }

    static int zzj(List<?> list) {
        return list.size();
    }

    static int zzj(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzhh.zzb(i, true);
    }

    static int zza(int i, List<?> list) {
        int iZzb;
        int iZzb2;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int iZze = zzhh.zze(i) * size;
        if (list instanceof zzir) {
            zzir zzirVar = (zzir) list;
            while (i2 < size) {
                Object objZzb = zzirVar.zzb(i2);
                if (objZzb instanceof zzgm) {
                    iZzb2 = zzhh.zzb((zzgm) objZzb);
                } else {
                    iZzb2 = zzhh.zzb((String) objZzb);
                }
                iZze += iZzb2;
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof zzgm) {
                    iZzb = zzhh.zzb((zzgm) obj);
                } else {
                    iZzb = zzhh.zzb((String) obj);
                }
                iZze += iZzb;
                i2++;
            }
        }
        return iZze;
    }

    static int zza(int i, Object obj, zzjw zzjwVar) {
        if (obj instanceof zzip) {
            return zzhh.zza(i, (zzip) obj);
        }
        return zzhh.zzb(i, (zzjg) obj, zzjwVar);
    }

    static int zza(int i, List<?> list, zzjw zzjwVar) {
        int iZza;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZze = zzhh.zze(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof zzip) {
                iZza = zzhh.zza((zzip) obj);
            } else {
                iZza = zzhh.zza((zzjg) obj, zzjwVar);
            }
            iZze += iZza;
        }
        return iZze;
    }

    static int zzb(int i, List<zzgm> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZze = size * zzhh.zze(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            iZze += zzhh.zzb(list.get(i2));
        }
        return iZze;
    }

    static int zzb(int i, List<zzjg> list, zzjw zzjwVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzc = 0;
        for (int i2 = 0; i2 < size; i2++) {
            iZzc += zzhh.zzc(i, list.get(i2), zzjwVar);
        }
        return iZzc;
    }

    public static zzks<?, ?> zza() {
        return zzb;
    }

    public static zzks<?, ?> zzb() {
        return zzc;
    }

    public static zzks<?, ?> zzc() {
        return zzd;
    }

    private static zzks<?, ?> zza(boolean z) {
        try {
            Class<?> clsZze = zze();
            if (clsZze == null) {
                return null;
            }
            return (zzks) clsZze.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzd() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zze() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    static boolean zza(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    static <T> void zza(zzjd zzjdVar, T t, T t2, long j) {
        zzky.zza(t, j, zzjdVar.zza(zzky.zzf(t, j), zzky.zzf(t2, j)));
    }

    static <T, FT extends zzhs<FT>> void zza(zzhm<FT> zzhmVar, T t, T t2) {
        zzhq<T> zzhqVarZza = zzhmVar.zza(t2);
        if (zzhqVarZza.zza.isEmpty()) {
            return;
        }
        zzhmVar.zzb(t).zza((zzhq) zzhqVarZza);
    }

    static <T, UT, UB> void zza(zzks<UT, UB> zzksVar, T t, T t2) {
        zzksVar.zza(t, zzksVar.zzc(zzksVar.zzb(t), zzksVar.zzb(t2)));
    }

    static <UT, UB> UB zza(int i, List<Integer> list, zzic zzicVar, UB ub, zzks<UT, UB> zzksVar) {
        UB ub2;
        if (zzicVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            ub2 = ub;
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int iIntValue = list.get(i3).intValue();
                if (zzicVar.zza(iIntValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(iIntValue));
                    }
                    i2++;
                } else {
                    ub2 = (UB) zza(i, iIntValue, ub2, zzksVar);
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
                    if (!zzicVar.zza(iIntValue2)) {
                        ub = (UB) zza(i, iIntValue2, ub2, zzksVar);
                        it.remove();
                    }
                }
                break loop1;
            }
        }
        return ub2;
    }

    static <UT, UB> UB zza(int i, int i2, UB ub, zzks<UT, UB> zzksVar) {
        if (ub == null) {
            ub = zzksVar.zza();
        }
        zzksVar.zza(ub, i, i2);
        return ub;
    }
}
