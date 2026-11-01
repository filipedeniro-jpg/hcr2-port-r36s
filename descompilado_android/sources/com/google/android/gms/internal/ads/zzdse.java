package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdse {
    private static final Class<?> zzhnj = zzbbh();
    private static final zzdsu<?, ?> zzhnk = zzbk(false);
    private static final zzdsu<?, ?> zzhnl = zzbk(true);
    private static final zzdsu<?, ?> zzhnm = new zzdsw();

    public static void zzi(Class<?> cls) {
        Class<?> cls2;
        if (!zzdqd.class.isAssignableFrom(cls) && (cls2 = zzhnj) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zza(int i, List<Double> list, zzdtr zzdtrVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdtrVar.zzg(i, list, z);
    }

    public static void zzb(int i, List<Float> list, zzdtr zzdtrVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdtrVar.zzf(i, list, z);
    }

    public static void zzc(int i, List<Long> list, zzdtr zzdtrVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdtrVar.zzc(i, list, z);
    }

    public static void zzd(int i, List<Long> list, zzdtr zzdtrVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdtrVar.zzd(i, list, z);
    }

    public static void zze(int i, List<Long> list, zzdtr zzdtrVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdtrVar.zzn(i, list, z);
    }

    public static void zzf(int i, List<Long> list, zzdtr zzdtrVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdtrVar.zze(i, list, z);
    }

    public static void zzg(int i, List<Long> list, zzdtr zzdtrVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdtrVar.zzl(i, list, z);
    }

    public static void zzh(int i, List<Integer> list, zzdtr zzdtrVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdtrVar.zza(i, list, z);
    }

    public static void zzi(int i, List<Integer> list, zzdtr zzdtrVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdtrVar.zzj(i, list, z);
    }

    public static void zzj(int i, List<Integer> list, zzdtr zzdtrVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdtrVar.zzm(i, list, z);
    }

    public static void zzk(int i, List<Integer> list, zzdtr zzdtrVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdtrVar.zzb(i, list, z);
    }

    public static void zzl(int i, List<Integer> list, zzdtr zzdtrVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdtrVar.zzk(i, list, z);
    }

    public static void zzm(int i, List<Integer> list, zzdtr zzdtrVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdtrVar.zzh(i, list, z);
    }

    public static void zzn(int i, List<Boolean> list, zzdtr zzdtrVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdtrVar.zzi(i, list, z);
    }

    public static void zza(int i, List<String> list, zzdtr zzdtrVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdtrVar.zza(i, list);
    }

    public static void zzb(int i, List<zzdot> list, zzdtr zzdtrVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdtrVar.zzb(i, list);
    }

    public static void zza(int i, List<?> list, zzdtr zzdtrVar, zzdsc zzdscVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdtrVar.zza(i, list, zzdscVar);
    }

    public static void zzb(int i, List<?> list, zzdtr zzdtrVar, zzdsc zzdscVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdtrVar.zzb(i, list, zzdscVar);
    }

    static int zzaa(List<Long> list) {
        int iZzfd;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdrb) {
            zzdrb zzdrbVar = (zzdrb) list;
            iZzfd = 0;
            while (i < size) {
                iZzfd += zzdpm.zzfd(zzdrbVar.getLong(i));
                i++;
            }
        } else {
            iZzfd = 0;
            while (i < size) {
                iZzfd += zzdpm.zzfd(list.get(i).longValue());
                i++;
            }
        }
        return iZzfd;
    }

    static int zzo(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzaa(list) + (list.size() * zzdpm.zzge(i));
    }

    static int zzab(List<Long> list) {
        int iZzfe;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdrb) {
            zzdrb zzdrbVar = (zzdrb) list;
            iZzfe = 0;
            while (i < size) {
                iZzfe += zzdpm.zzfe(zzdrbVar.getLong(i));
                i++;
            }
        } else {
            iZzfe = 0;
            while (i < size) {
                iZzfe += zzdpm.zzfe(list.get(i).longValue());
                i++;
            }
        }
        return iZzfe;
    }

    static int zzp(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzab(list) + (size * zzdpm.zzge(i));
    }

    static int zzac(List<Long> list) {
        int iZzff;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdrb) {
            zzdrb zzdrbVar = (zzdrb) list;
            iZzff = 0;
            while (i < size) {
                iZzff += zzdpm.zzff(zzdrbVar.getLong(i));
                i++;
            }
        } else {
            iZzff = 0;
            while (i < size) {
                iZzff += zzdpm.zzff(list.get(i).longValue());
                i++;
            }
        }
        return iZzff;
    }

    static int zzq(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzac(list) + (size * zzdpm.zzge(i));
    }

    static int zzad(List<Integer> list) {
        int iZzgk;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdqf) {
            zzdqf zzdqfVar = (zzdqf) list;
            iZzgk = 0;
            while (i < size) {
                iZzgk += zzdpm.zzgk(zzdqfVar.getInt(i));
                i++;
            }
        } else {
            iZzgk = 0;
            while (i < size) {
                iZzgk += zzdpm.zzgk(list.get(i).intValue());
                i++;
            }
        }
        return iZzgk;
    }

    static int zzr(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzad(list) + (size * zzdpm.zzge(i));
    }

    static int zzae(List<Integer> list) {
        int iZzgf;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdqf) {
            zzdqf zzdqfVar = (zzdqf) list;
            iZzgf = 0;
            while (i < size) {
                iZzgf += zzdpm.zzgf(zzdqfVar.getInt(i));
                i++;
            }
        } else {
            iZzgf = 0;
            while (i < size) {
                iZzgf += zzdpm.zzgf(list.get(i).intValue());
                i++;
            }
        }
        return iZzgf;
    }

    static int zzs(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzae(list) + (size * zzdpm.zzge(i));
    }

    static int zzaf(List<Integer> list) {
        int iZzgg;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdqf) {
            zzdqf zzdqfVar = (zzdqf) list;
            iZzgg = 0;
            while (i < size) {
                iZzgg += zzdpm.zzgg(zzdqfVar.getInt(i));
                i++;
            }
        } else {
            iZzgg = 0;
            while (i < size) {
                iZzgg += zzdpm.zzgg(list.get(i).intValue());
                i++;
            }
        }
        return iZzgg;
    }

    static int zzt(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzaf(list) + (size * zzdpm.zzge(i));
    }

    static int zzag(List<Integer> list) {
        int iZzgh;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdqf) {
            zzdqf zzdqfVar = (zzdqf) list;
            iZzgh = 0;
            while (i < size) {
                iZzgh += zzdpm.zzgh(zzdqfVar.getInt(i));
                i++;
            }
        } else {
            iZzgh = 0;
            while (i < size) {
                iZzgh += zzdpm.zzgh(list.get(i).intValue());
                i++;
            }
        }
        return iZzgh;
    }

    static int zzu(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzag(list) + (size * zzdpm.zzge(i));
    }

    static int zzah(List<?> list) {
        return list.size() << 2;
    }

    static int zzv(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzdpm.zzah(i, 0);
    }

    static int zzai(List<?> list) {
        return list.size() << 3;
    }

    static int zzw(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzdpm.zzm(i, 0L);
    }

    static int zzaj(List<?> list) {
        return list.size();
    }

    static int zzx(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzdpm.zzi(i, true);
    }

    static int zzc(int i, List<?> list) {
        int iZzhj;
        int iZzhj2;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int iZzge = zzdpm.zzge(i) * size;
        if (list instanceof zzdqu) {
            zzdqu zzdquVar = (zzdqu) list;
            while (i2 < size) {
                Object objZzgr = zzdquVar.zzgr(i2);
                if (objZzgr instanceof zzdot) {
                    iZzhj2 = zzdpm.zzda((zzdot) objZzgr);
                } else {
                    iZzhj2 = zzdpm.zzhj((String) objZzgr);
                }
                iZzge += iZzhj2;
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof zzdot) {
                    iZzhj = zzdpm.zzda((zzdot) obj);
                } else {
                    iZzhj = zzdpm.zzhj((String) obj);
                }
                iZzge += iZzhj;
                i2++;
            }
        }
        return iZzge;
    }

    static int zzc(int i, Object obj, zzdsc zzdscVar) {
        if (obj instanceof zzdqs) {
            return zzdpm.zza(i, (zzdqs) obj);
        }
        return zzdpm.zzb(i, (zzdrn) obj, zzdscVar);
    }

    static int zzc(int i, List<?> list, zzdsc zzdscVar) {
        int iZzb;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzge = zzdpm.zzge(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof zzdqs) {
                iZzb = zzdpm.zza((zzdqs) obj);
            } else {
                iZzb = zzdpm.zzb((zzdrn) obj, zzdscVar);
            }
            iZzge += iZzb;
        }
        return iZzge;
    }

    static int zzd(int i, List<zzdot> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzge = size * zzdpm.zzge(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            iZzge += zzdpm.zzda(list.get(i2));
        }
        return iZzge;
    }

    static int zzd(int i, List<zzdrn> list, zzdsc zzdscVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzc = 0;
        for (int i2 = 0; i2 < size; i2++) {
            iZzc += zzdpm.zzc(i, list.get(i2), zzdscVar);
        }
        return iZzc;
    }

    public static zzdsu<?, ?> zzbbe() {
        return zzhnk;
    }

    public static zzdsu<?, ?> zzbbf() {
        return zzhnl;
    }

    public static zzdsu<?, ?> zzbbg() {
        return zzhnm;
    }

    private static zzdsu<?, ?> zzbk(boolean z) {
        try {
            Class<?> clsZzbbi = zzbbi();
            if (clsZzbbi == null) {
                return null;
            }
            return (zzdsu) clsZzbbi.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzbbh() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzbbi() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    static boolean zzg(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    static <T> void zza(zzdrg zzdrgVar, T t, T t2, long j) {
        zzdta.zza(t, j, zzdrgVar.zze(zzdta.zzp(t, j), zzdta.zzp(t2, j)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    static <T, FT extends zzdpv<FT>> void zza(zzdps<FT> zzdpsVar, T t, T t2) {
        zzdpt<T> zzdptVarZzai = zzdpsVar.zzai(t2);
        if (zzdptVarZzai.zzhhb.isEmpty()) {
            return;
        }
        zzdpsVar.zzaj(t).zza(zzdptVarZzai);
    }

    static <T, UT, UB> void zza(zzdsu<UT, UB> zzdsuVar, T t, T t2) {
        zzdsuVar.zzh(t, zzdsuVar.zzj(zzdsuVar.zzay(t), zzdsuVar.zzay(t2)));
    }

    static <UT, UB> UB zza(int i, List<Integer> list, zzdqj zzdqjVar, UB ub, zzdsu<UT, UB> zzdsuVar) {
        UB ub2;
        if (zzdqjVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            ub2 = ub;
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int iIntValue = list.get(i3).intValue();
                if (zzdqjVar.zzf(iIntValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(iIntValue));
                    }
                    i2++;
                } else {
                    ub2 = (UB) zza(i, iIntValue, ub2, zzdsuVar);
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
                    if (!zzdqjVar.zzf(iIntValue2)) {
                        ub = (UB) zza(i, iIntValue2, ub2, zzdsuVar);
                        it.remove();
                    }
                }
                break loop1;
            }
        }
        return ub2;
    }

    static <UT, UB> UB zza(int i, int i2, UB ub, zzdsu<UT, UB> zzdsuVar) {
        if (ub == null) {
            ub = zzdsuVar.zzbbr();
        }
        zzdsuVar.zza(ub, i, i2);
        return ub;
    }
}
