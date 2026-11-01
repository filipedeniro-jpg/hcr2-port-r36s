package com.google.android.gms.internal.firebase_remote_config;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes2.dex */
final class zzjh {
    private static final Class<?> zzwo = zzip();
    private static final zzjx<?, ?> zzwp = zzg(false);
    private static final zzjx<?, ?> zzwq = zzg(true);
    private static final zzjx<?, ?> zzwr = new zzjz();

    public static void zzl(Class<?> cls) {
        Class<?> cls2;
        if (!zzhi.class.isAssignableFrom(cls) && (cls2 = zzwo) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zza(int i, List<Double> list, zzku zzkuVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkuVar.zzg(i, list, z);
    }

    public static void zzb(int i, List<Float> list, zzku zzkuVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkuVar.zzf(i, list, z);
    }

    public static void zzc(int i, List<Long> list, zzku zzkuVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkuVar.zzc(i, list, z);
    }

    public static void zzd(int i, List<Long> list, zzku zzkuVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkuVar.zzd(i, list, z);
    }

    public static void zze(int i, List<Long> list, zzku zzkuVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkuVar.zzn(i, list, z);
    }

    public static void zzf(int i, List<Long> list, zzku zzkuVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkuVar.zze(i, list, z);
    }

    public static void zzg(int i, List<Long> list, zzku zzkuVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkuVar.zzl(i, list, z);
    }

    public static void zzh(int i, List<Integer> list, zzku zzkuVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkuVar.zza(i, list, z);
    }

    public static void zzi(int i, List<Integer> list, zzku zzkuVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkuVar.zzj(i, list, z);
    }

    public static void zzj(int i, List<Integer> list, zzku zzkuVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkuVar.zzm(i, list, z);
    }

    public static void zzk(int i, List<Integer> list, zzku zzkuVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkuVar.zzb(i, list, z);
    }

    public static void zzl(int i, List<Integer> list, zzku zzkuVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkuVar.zzk(i, list, z);
    }

    public static void zzm(int i, List<Integer> list, zzku zzkuVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkuVar.zzh(i, list, z);
    }

    public static void zzn(int i, List<Boolean> list, zzku zzkuVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkuVar.zzi(i, list, z);
    }

    public static void zza(int i, List<String> list, zzku zzkuVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkuVar.zza(i, list);
    }

    public static void zzb(int i, List<zzfx> list, zzku zzkuVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkuVar.zzb(i, list);
    }

    public static void zza(int i, List<?> list, zzku zzkuVar, zzjf zzjfVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkuVar.zza(i, list, zzjfVar);
    }

    public static void zzb(int i, List<?> list, zzku zzkuVar, zzjf zzjfVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkuVar.zzb(i, list, zzjfVar);
    }

    static int zzs(List<Long> list) {
        int iZzi;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzie) {
            zzie zzieVar = (zzie) list;
            iZzi = 0;
            while (i < size) {
                iZzi += zzgs.zzi(zzieVar.getLong(i));
                i++;
            }
        } else {
            iZzi = 0;
            while (i < size) {
                iZzi += zzgs.zzi(list.get(i).longValue());
                i++;
            }
        }
        return iZzi;
    }

    static int zzo(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzs(list) + (list.size() * zzgs.zzaq(i));
    }

    static int zzt(List<Long> list) {
        int iZzj;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzie) {
            zzie zzieVar = (zzie) list;
            iZzj = 0;
            while (i < size) {
                iZzj += zzgs.zzj(zzieVar.getLong(i));
                i++;
            }
        } else {
            iZzj = 0;
            while (i < size) {
                iZzj += zzgs.zzj(list.get(i).longValue());
                i++;
            }
        }
        return iZzj;
    }

    static int zzp(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzt(list) + (size * zzgs.zzaq(i));
    }

    static int zzu(List<Long> list) {
        int iZzk;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzie) {
            zzie zzieVar = (zzie) list;
            iZzk = 0;
            while (i < size) {
                iZzk += zzgs.zzk(zzieVar.getLong(i));
                i++;
            }
        } else {
            iZzk = 0;
            while (i < size) {
                iZzk += zzgs.zzk(list.get(i).longValue());
                i++;
            }
        }
        return iZzk;
    }

    static int zzq(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzu(list) + (size * zzgs.zzaq(i));
    }

    static int zzv(List<Integer> list) {
        int iZzaw;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhk) {
            zzhk zzhkVar = (zzhk) list;
            iZzaw = 0;
            while (i < size) {
                iZzaw += zzgs.zzaw(zzhkVar.getInt(i));
                i++;
            }
        } else {
            iZzaw = 0;
            while (i < size) {
                iZzaw += zzgs.zzaw(list.get(i).intValue());
                i++;
            }
        }
        return iZzaw;
    }

    static int zzr(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzv(list) + (size * zzgs.zzaq(i));
    }

    static int zzw(List<Integer> list) {
        int iZzar;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhk) {
            zzhk zzhkVar = (zzhk) list;
            iZzar = 0;
            while (i < size) {
                iZzar += zzgs.zzar(zzhkVar.getInt(i));
                i++;
            }
        } else {
            iZzar = 0;
            while (i < size) {
                iZzar += zzgs.zzar(list.get(i).intValue());
                i++;
            }
        }
        return iZzar;
    }

    static int zzs(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzw(list) + (size * zzgs.zzaq(i));
    }

    static int zzx(List<Integer> list) {
        int iZzas;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhk) {
            zzhk zzhkVar = (zzhk) list;
            iZzas = 0;
            while (i < size) {
                iZzas += zzgs.zzas(zzhkVar.getInt(i));
                i++;
            }
        } else {
            iZzas = 0;
            while (i < size) {
                iZzas += zzgs.zzas(list.get(i).intValue());
                i++;
            }
        }
        return iZzas;
    }

    static int zzt(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzx(list) + (size * zzgs.zzaq(i));
    }

    static int zzy(List<Integer> list) {
        int iZzat;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhk) {
            zzhk zzhkVar = (zzhk) list;
            iZzat = 0;
            while (i < size) {
                iZzat += zzgs.zzat(zzhkVar.getInt(i));
                i++;
            }
        } else {
            iZzat = 0;
            while (i < size) {
                iZzat += zzgs.zzat(list.get(i).intValue());
                i++;
            }
        }
        return iZzat;
    }

    static int zzu(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzy(list) + (size * zzgs.zzaq(i));
    }

    static int zzz(List<?> list) {
        return list.size() << 2;
    }

    static int zzv(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzgs.zzk(i, 0);
    }

    static int zzaa(List<?> list) {
        return list.size() << 3;
    }

    static int zzw(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzgs.zzg(i, 0L);
    }

    static int zzab(List<?> list) {
        return list.size();
    }

    static int zzx(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzgs.zzd(i, true);
    }

    static int zzc(int i, List<?> list) {
        int iZzbl;
        int iZzbl2;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int iZzaq = zzgs.zzaq(i) * size;
        if (list instanceof zzhx) {
            zzhx zzhxVar = (zzhx) list;
            while (i2 < size) {
                Object objZzbd = zzhxVar.zzbd(i2);
                if (objZzbd instanceof zzfx) {
                    iZzbl2 = zzgs.zzc((zzfx) objZzbd);
                } else {
                    iZzbl2 = zzgs.zzbl((String) objZzbd);
                }
                iZzaq += iZzbl2;
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof zzfx) {
                    iZzbl = zzgs.zzc((zzfx) obj);
                } else {
                    iZzbl = zzgs.zzbl((String) obj);
                }
                iZzaq += iZzbl;
                i2++;
            }
        }
        return iZzaq;
    }

    static int zzc(int i, Object obj, zzjf zzjfVar) {
        if (obj instanceof zzhv) {
            return zzgs.zza(i, (zzhv) obj);
        }
        return zzgs.zzb(i, (zziq) obj, zzjfVar);
    }

    static int zzc(int i, List<?> list, zzjf zzjfVar) {
        int iZza;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzaq = zzgs.zzaq(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof zzhv) {
                iZza = zzgs.zza((zzhv) obj);
            } else {
                iZza = zzgs.zza((zziq) obj, zzjfVar);
            }
            iZzaq += iZza;
        }
        return iZzaq;
    }

    static int zzd(int i, List<zzfx> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzaq = size * zzgs.zzaq(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            iZzaq += zzgs.zzc(list.get(i2));
        }
        return iZzaq;
    }

    static int zzd(int i, List<zziq> list, zzjf zzjfVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzc = 0;
        for (int i2 = 0; i2 < size; i2++) {
            iZzc += zzgs.zzc(i, list.get(i2), zzjfVar);
        }
        return iZzc;
    }

    public static zzjx<?, ?> zzim() {
        return zzwp;
    }

    public static zzjx<?, ?> zzin() {
        return zzwq;
    }

    public static zzjx<?, ?> zzio() {
        return zzwr;
    }

    private static zzjx<?, ?> zzg(boolean z) {
        try {
            Class<?> clsZziq = zziq();
            if (clsZziq == null) {
                return null;
            }
            return (zzjx) clsZziq.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzip() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zziq() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    static boolean zzf(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    static <T> void zza(zzij zzijVar, T t, T t2, long j) {
        zzkd.zza(t, j, zzijVar.zzd(zzkd.zzp(t, j), zzkd.zzp(t2, j)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    static <T, FT extends zzhc<FT>> void zza(zzgz<FT> zzgzVar, T t, T t2) {
        zzha<T> zzhaVarZzk = zzgzVar.zzk(t2);
        if (zzhaVarZzk.zzqf.isEmpty()) {
            return;
        }
        zzgzVar.zzl(t).zza(zzhaVarZzk);
    }

    static <T, UT, UB> void zza(zzjx<UT, UB> zzjxVar, T t, T t2) {
        zzjxVar.zzg(t, zzjxVar.zzi(zzjxVar.zzaa(t), zzjxVar.zzaa(t2)));
    }

    static <UT, UB> UB zza(int i, List<Integer> list, zzho zzhoVar, UB ub, zzjx<UT, UB> zzjxVar) {
        UB ub2;
        if (zzhoVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            ub2 = ub;
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int iIntValue = list.get(i3).intValue();
                if (zzhoVar.zzbc(iIntValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(iIntValue));
                    }
                    i2++;
                } else {
                    ub2 = (UB) zza(i, iIntValue, ub2, zzjxVar);
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
                    if (!zzhoVar.zzbc(iIntValue2)) {
                        ub = (UB) zza(i, iIntValue2, ub2, zzjxVar);
                        it.remove();
                    }
                }
                break loop1;
            }
        }
        return ub2;
    }

    static <UT, UB> UB zza(int i, int i2, UB ub, zzjx<UT, UB> zzjxVar) {
        if (ub == null) {
            ub = zzjxVar.zziz();
        }
        zzjxVar.zza(ub, i, i2);
        return ub;
    }
}
