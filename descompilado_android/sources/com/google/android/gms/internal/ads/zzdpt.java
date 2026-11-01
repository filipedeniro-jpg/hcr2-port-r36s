package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzdpv;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdpt<FieldDescriptorType extends zzdpv<FieldDescriptorType>> {
    private static final zzdpt zzhhe = new zzdpt(true);
    private boolean zzhhc;
    private boolean zzhhd = false;
    final zzdsh<FieldDescriptorType, Object> zzhhb = zzdsh.zzgz(16);

    private zzdpt() {
    }

    private zzdpt(boolean z) {
        zzaxe();
    }

    public static <T extends zzdpv<T>> zzdpt<T> zzayx() {
        return zzhhe;
    }

    public final void zzaxe() {
        if (this.zzhhc) {
            return;
        }
        this.zzhhb.zzaxe();
        this.zzhhc = true;
    }

    public final boolean isImmutable() {
        return this.zzhhc;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzdpt) {
            return this.zzhhb.equals(((zzdpt) obj).zzhhb);
        }
        return false;
    }

    public final int hashCode() {
        return this.zzhhb.hashCode();
    }

    public final Iterator<Map.Entry<FieldDescriptorType, Object>> iterator() {
        if (this.zzhhd) {
            return new zzdqt(this.zzhhb.entrySet().iterator());
        }
        return this.zzhhb.entrySet().iterator();
    }

    final Iterator<Map.Entry<FieldDescriptorType, Object>> descendingIterator() {
        if (this.zzhhd) {
            return new zzdqt(this.zzhhb.zzbbl().iterator());
        }
        return this.zzhhb.zzbbl().iterator();
    }

    private final Object zza(FieldDescriptorType fielddescriptortype) {
        Object obj = this.zzhhb.get(fielddescriptortype);
        return obj instanceof zzdqo ? zzdqo.zzbaf() : obj;
    }

    private final void zza(FieldDescriptorType fielddescriptortype, Object obj) {
        if (fielddescriptortype.zzaze()) {
            if (!(obj instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList2.get(i);
                i++;
                zza(fielddescriptortype.zzazc(), obj2);
            }
            obj = arrayList;
        } else {
            zza(fielddescriptortype.zzazc(), obj);
        }
        if (obj instanceof zzdqo) {
            this.zzhhd = true;
        }
        this.zzhhb.put(fielddescriptortype, obj);
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0026  */
    private static void zza(zzdtl zzdtlVar, Object obj) {
        boolean z;
        zzdqe.checkNotNull(obj);
        boolean z2 = false;
        switch (zzdpw.zzhhh[zzdtlVar.zzbcb().ordinal()]) {
            case 1:
                z = obj instanceof Integer;
                z2 = z;
                break;
            case 2:
                z = obj instanceof Long;
                z2 = z;
                break;
            case 3:
                z = obj instanceof Float;
                z2 = z;
                break;
            case 4:
                z = obj instanceof Double;
                z2 = z;
                break;
            case 5:
                z = obj instanceof Boolean;
                z2 = z;
                break;
            case 6:
                z = obj instanceof String;
                z2 = z;
                break;
            case 7:
                if ((obj instanceof zzdot) || (obj instanceof byte[])) {
                    z2 = true;
                }
                break;
            case 8:
                if ((obj instanceof Integer) || (obj instanceof zzdqh)) {
                    z2 = true;
                }
                break;
            case 9:
                if ((obj instanceof zzdrn) || (obj instanceof zzdqo)) {
                    z2 = true;
                }
                break;
        }
        if (!z2) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    public final boolean isInitialized() {
        for (int i = 0; i < this.zzhhb.zzbbj(); i++) {
            if (!zzb(this.zzhhb.zzha(i))) {
                return false;
            }
        }
        Iterator it = this.zzhhb.zzbbk().iterator();
        while (it.hasNext()) {
            if (!zzb((Map.Entry) it.next())) {
                return false;
            }
        }
        return true;
    }

    private static boolean zzb(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorType key = entry.getKey();
        if (key.zzazd() == zzdto.MESSAGE) {
            if (key.zzaze()) {
                Iterator it = ((List) entry.getValue()).iterator();
                while (it.hasNext()) {
                    if (!((zzdrn) it.next()).isInitialized()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zzdrn) {
                    if (!((zzdrn) value).isInitialized()) {
                        return false;
                    }
                } else {
                    if (value instanceof zzdqo) {
                        return true;
                    }
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    public final void zza(zzdpt<FieldDescriptorType> zzdptVar) {
        for (int i = 0; i < zzdptVar.zzhhb.zzbbj(); i++) {
            zzc(zzdptVar.zzhhb.zzha(i));
        }
        Iterator it = zzdptVar.zzhhb.zzbbk().iterator();
        while (it.hasNext()) {
            zzc((Map.Entry) it.next());
        }
    }

    private static Object zzal(Object obj) {
        if (obj instanceof zzdrs) {
            return ((zzdrs) obj).zzbaw();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    private final void zzc(Map.Entry<FieldDescriptorType, Object> entry) {
        zzdrn zzdrnVarZzazm;
        FieldDescriptorType key = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzdqo) {
            value = zzdqo.zzbaf();
        }
        if (key.zzaze()) {
            Object objZza = zza(key);
            if (objZza == null) {
                objZza = new ArrayList();
            }
            Iterator it = ((List) value).iterator();
            while (it.hasNext()) {
                ((List) objZza).add(zzal(it.next()));
            }
            this.zzhhb.put(key, objZza);
            return;
        }
        if (key.zzazd() == zzdto.MESSAGE) {
            Object objZza2 = zza(key);
            if (objZza2 == null) {
                this.zzhhb.put(key, zzal(value));
                return;
            }
            if (objZza2 instanceof zzdrs) {
                zzdrnVarZzazm = key.zza((zzdrs) objZza2, (zzdrs) value);
            } else {
                zzdrnVarZzazm = key.zza(((zzdrn) objZza2).zzazs(), (zzdrn) value).zzazm();
            }
            this.zzhhb.put(key, zzdrnVarZzazm);
            return;
        }
        this.zzhhb.put(key, zzal(value));
    }

    static void zza(zzdpm zzdpmVar, zzdtl zzdtlVar, int i, Object obj) throws IOException {
        if (zzdtlVar == zzdtl.zzhpm) {
            zzdrn zzdrnVar = (zzdrn) obj;
            zzdqe.zzn(zzdrnVar);
            zzdpmVar.zzz(i, 3);
            zzdrnVar.zzb(zzdpmVar);
            zzdpmVar.zzz(i, 4);
        }
        zzdpmVar.zzz(i, zzdtlVar.zzbcc());
        switch (zzdpw.zzhgf[zzdtlVar.ordinal()]) {
            case 1:
                zzdpmVar.zzb(((Double) obj).doubleValue());
                break;
            case 2:
                zzdpmVar.zzf(((Float) obj).floatValue());
                break;
            case 3:
                zzdpmVar.zzfa(((Long) obj).longValue());
                break;
            case 4:
                zzdpmVar.zzfa(((Long) obj).longValue());
                break;
            case 5:
                zzdpmVar.zzga(((Integer) obj).intValue());
                break;
            case 6:
                zzdpmVar.zzfc(((Long) obj).longValue());
                break;
            case 7:
                zzdpmVar.zzgd(((Integer) obj).intValue());
                break;
            case 8:
                zzdpmVar.zzbh(((Boolean) obj).booleanValue());
                break;
            case 9:
                ((zzdrn) obj).zzb(zzdpmVar);
                break;
            case 10:
                zzdpmVar.zzj((zzdrn) obj);
                break;
            case 11:
                if (obj instanceof zzdot) {
                    zzdpmVar.zzcz((zzdot) obj);
                } else {
                    zzdpmVar.zzhi((String) obj);
                }
                break;
            case 12:
                if (obj instanceof zzdot) {
                    zzdpmVar.zzcz((zzdot) obj);
                } else {
                    byte[] bArr = (byte[]) obj;
                    zzdpmVar.zzk(bArr, 0, bArr.length);
                }
                break;
            case 13:
                zzdpmVar.zzgb(((Integer) obj).intValue());
                break;
            case 14:
                zzdpmVar.zzgd(((Integer) obj).intValue());
                break;
            case 15:
                zzdpmVar.zzfc(((Long) obj).longValue());
                break;
            case 16:
                zzdpmVar.zzgc(((Integer) obj).intValue());
                break;
            case 17:
                zzdpmVar.zzfb(((Long) obj).longValue());
                break;
            case 18:
                if (obj instanceof zzdqh) {
                    zzdpmVar.zzga(((zzdqh) obj).zzab());
                } else {
                    zzdpmVar.zzga(((Integer) obj).intValue());
                }
                break;
        }
    }

    public final int zzayy() {
        int iZzd = 0;
        for (int i = 0; i < this.zzhhb.zzbbj(); i++) {
            iZzd += zzd(this.zzhhb.zzha(i));
        }
        Iterator it = this.zzhhb.zzbbk().iterator();
        while (it.hasNext()) {
            iZzd += zzd((Map.Entry) it.next());
        }
        return iZzd;
    }

    private static int zzd(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorType key = entry.getKey();
        Object value = entry.getValue();
        if (key.zzazd() == zzdto.MESSAGE && !key.zzaze() && !key.zzazf()) {
            if (value instanceof zzdqo) {
                return zzdpm.zzb(entry.getKey().zzab(), (zzdqo) value);
            }
            return zzdpm.zzd(entry.getKey().zzab(), (zzdrn) value);
        }
        return zzb((zzdpv<?>) key, value);
    }

    static int zza(zzdtl zzdtlVar, int i, Object obj) {
        int iZzge = zzdpm.zzge(i);
        if (zzdtlVar == zzdtl.zzhpm) {
            zzdqe.zzn((zzdrn) obj);
            iZzge <<= 1;
        }
        return iZzge + zzb(zzdtlVar, obj);
    }

    private static int zzb(zzdtl zzdtlVar, Object obj) {
        switch (zzdpw.zzhgf[zzdtlVar.ordinal()]) {
            case 1:
                return zzdpm.zzc(((Double) obj).doubleValue());
            case 2:
                return zzdpm.zzg(((Float) obj).floatValue());
            case 3:
                return zzdpm.zzfd(((Long) obj).longValue());
            case 4:
                return zzdpm.zzfe(((Long) obj).longValue());
            case 5:
                return zzdpm.zzgf(((Integer) obj).intValue());
            case 6:
                return zzdpm.zzfg(((Long) obj).longValue());
            case 7:
                return zzdpm.zzgi(((Integer) obj).intValue());
            case 8:
                return zzdpm.zzbi(((Boolean) obj).booleanValue());
            case 9:
                return zzdpm.zzl((zzdrn) obj);
            case 10:
                if (obj instanceof zzdqo) {
                    return zzdpm.zza((zzdqo) obj);
                }
                return zzdpm.zzk((zzdrn) obj);
            case 11:
                if (obj instanceof zzdot) {
                    return zzdpm.zzda((zzdot) obj);
                }
                return zzdpm.zzhj((String) obj);
            case 12:
                if (obj instanceof zzdot) {
                    return zzdpm.zzda((zzdot) obj);
                }
                return zzdpm.zzab((byte[]) obj);
            case 13:
                return zzdpm.zzgg(((Integer) obj).intValue());
            case 14:
                return zzdpm.zzgj(((Integer) obj).intValue());
            case 15:
                return zzdpm.zzfh(((Long) obj).longValue());
            case 16:
                return zzdpm.zzgh(((Integer) obj).intValue());
            case 17:
                return zzdpm.zzff(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzdqh) {
                    return zzdpm.zzgk(((zzdqh) obj).zzab());
                }
                return zzdpm.zzgk(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int zzb(zzdpv<?> zzdpvVar, Object obj) {
        zzdtl zzdtlVarZzazc = zzdpvVar.zzazc();
        int iZzab = zzdpvVar.zzab();
        if (zzdpvVar.zzaze()) {
            int iZza = 0;
            if (zzdpvVar.zzazf()) {
                Iterator it = ((List) obj).iterator();
                while (it.hasNext()) {
                    iZza += zzb(zzdtlVarZzazc, it.next());
                }
                return zzdpm.zzge(iZzab) + iZza + zzdpm.zzgm(iZza);
            }
            Iterator it2 = ((List) obj).iterator();
            while (it2.hasNext()) {
                iZza += zza(zzdtlVarZzazc, iZzab, it2.next());
            }
            return iZza;
        }
        return zza(zzdtlVarZzazc, iZzab, obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzdpt zzdptVar = new zzdpt();
        for (int i = 0; i < this.zzhhb.zzbbj(); i++) {
            Map.Entry<K, Object> entryZzha = this.zzhhb.zzha(i);
            zzdptVar.zza((zzdpv) entryZzha.getKey(), entryZzha.getValue());
        }
        Iterator it = this.zzhhb.zzbbk().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            zzdptVar.zza((zzdpv) entry.getKey(), entry.getValue());
        }
        zzdptVar.zzhhd = this.zzhhd;
        return zzdptVar;
    }
}
