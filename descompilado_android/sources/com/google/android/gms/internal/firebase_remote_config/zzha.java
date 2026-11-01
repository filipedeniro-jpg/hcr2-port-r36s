package com.google.android.gms.internal.firebase_remote_config;

import com.google.android.gms.internal.firebase_remote_config.zzhc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class zzha<FieldDescriptorType extends zzhc<FieldDescriptorType>> {
    private static final zzha zzqi = new zzha(true);
    private boolean zzqg;
    private boolean zzqh = false;
    final zzjk<FieldDescriptorType, Object> zzqf = zzjk.zzbl(16);

    private zzha() {
    }

    private zzha(boolean z) {
        zzes();
    }

    public static <T extends zzhc<T>> zzha<T> zzgi() {
        return zzqi;
    }

    public final void zzes() {
        if (this.zzqg) {
            return;
        }
        this.zzqf.zzes();
        this.zzqg = true;
    }

    public final boolean isImmutable() {
        return this.zzqg;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzha) {
            return this.zzqf.equals(((zzha) obj).zzqf);
        }
        return false;
    }

    public final int hashCode() {
        return this.zzqf.hashCode();
    }

    public final Iterator<Map.Entry<FieldDescriptorType, Object>> iterator() {
        if (this.zzqh) {
            return new zzhw(this.zzqf.entrySet().iterator());
        }
        return this.zzqf.entrySet().iterator();
    }

    final Iterator<Map.Entry<FieldDescriptorType, Object>> descendingIterator() {
        if (this.zzqh) {
            return new zzhw(this.zzqf.zzit().iterator());
        }
        return this.zzqf.zzit().iterator();
    }

    private final Object zza(FieldDescriptorType fielddescriptortype) {
        Object obj = this.zzqf.get(fielddescriptortype);
        return obj instanceof zzhr ? zzhr.zzhn() : obj;
    }

    private final void zza(FieldDescriptorType fielddescriptortype, Object obj) {
        if (fielddescriptortype.zzgq()) {
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
                zza(fielddescriptortype.zzgo(), obj2);
            }
            obj = arrayList;
        } else {
            zza(fielddescriptortype.zzgo(), obj);
        }
        if (obj instanceof zzhr) {
            this.zzqh = true;
        }
        this.zzqf.put(fielddescriptortype, obj);
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0026  */
    private static void zza(zzko zzkoVar, Object obj) {
        boolean z;
        zzhm.checkNotNull(obj);
        boolean z2 = false;
        switch (zzhd.zzql[zzkoVar.zzjj().ordinal()]) {
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
                if ((obj instanceof zzfx) || (obj instanceof byte[])) {
                    z2 = true;
                }
                break;
            case 8:
                if ((obj instanceof Integer) || (obj instanceof zzhl)) {
                    z2 = true;
                }
                break;
            case 9:
                if ((obj instanceof zziq) || (obj instanceof zzhr)) {
                    z2 = true;
                }
                break;
        }
        if (!z2) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    public final boolean isInitialized() {
        for (int i = 0; i < this.zzqf.zzir(); i++) {
            if (!zzb(this.zzqf.zzbm(i))) {
                return false;
            }
        }
        Iterator it = this.zzqf.zzis().iterator();
        while (it.hasNext()) {
            if (!zzb((Map.Entry) it.next())) {
                return false;
            }
        }
        return true;
    }

    private static boolean zzb(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorType key = entry.getKey();
        if (key.zzgp() == zzkr.MESSAGE) {
            if (key.zzgq()) {
                Iterator it = ((List) entry.getValue()).iterator();
                while (it.hasNext()) {
                    if (!((zziq) it.next()).isInitialized()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zziq) {
                    if (!((zziq) value).isInitialized()) {
                        return false;
                    }
                } else {
                    if (value instanceof zzhr) {
                        return true;
                    }
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    public final void zza(zzha<FieldDescriptorType> zzhaVar) {
        for (int i = 0; i < zzhaVar.zzqf.zzir(); i++) {
            zzc(zzhaVar.zzqf.zzbm(i));
        }
        Iterator it = zzhaVar.zzqf.zzis().iterator();
        while (it.hasNext()) {
            zzc((Map.Entry) it.next());
        }
    }

    private static Object zzn(Object obj) {
        if (obj instanceof zziv) {
            return ((zziv) obj).zzie();
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
        zziq zziqVarZzhb;
        FieldDescriptorType key = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzhr) {
            value = zzhr.zzhn();
        }
        if (key.zzgq()) {
            Object objZza = zza(key);
            if (objZza == null) {
                objZza = new ArrayList();
            }
            Iterator it = ((List) value).iterator();
            while (it.hasNext()) {
                ((List) objZza).add(zzn(it.next()));
            }
            this.zzqf.put(key, objZza);
            return;
        }
        if (key.zzgp() == zzkr.MESSAGE) {
            Object objZza2 = zza(key);
            if (objZza2 == null) {
                this.zzqf.put(key, zzn(value));
                return;
            }
            if (objZza2 instanceof zziv) {
                zziqVarZzhb = key.zza((zziv) objZza2, (zziv) value);
            } else {
                zziqVarZzhb = key.zza(((zziq) objZza2).zzgu(), (zziq) value).zzhb();
            }
            this.zzqf.put(key, zziqVarZzhb);
            return;
        }
        this.zzqf.put(key, zzn(value));
    }

    static void zza(zzgs zzgsVar, zzko zzkoVar, int i, Object obj) throws IOException {
        if (zzkoVar == zzko.zzys) {
            zziq zziqVar = (zziq) obj;
            zzhm.zzf(zziqVar);
            zzgsVar.zzc(i, 3);
            zziqVar.zzb(zzgsVar);
            zzgsVar.zzc(i, 4);
        }
        zzgsVar.zzc(i, zzkoVar.zzjk());
        switch (zzhd.zzpr[zzkoVar.ordinal()]) {
            case 1:
                zzgsVar.zzc(((Double) obj).doubleValue());
                break;
            case 2:
                zzgsVar.zzb(((Float) obj).floatValue());
                break;
            case 3:
                zzgsVar.zzf(((Long) obj).longValue());
                break;
            case 4:
                zzgsVar.zzf(((Long) obj).longValue());
                break;
            case 5:
                zzgsVar.zzam(((Integer) obj).intValue());
                break;
            case 6:
                zzgsVar.zzh(((Long) obj).longValue());
                break;
            case 7:
                zzgsVar.zzap(((Integer) obj).intValue());
                break;
            case 8:
                zzgsVar.zzd(((Boolean) obj).booleanValue());
                break;
            case 9:
                ((zziq) obj).zzb(zzgsVar);
                break;
            case 10:
                zzgsVar.zzb((zziq) obj);
                break;
            case 11:
                if (obj instanceof zzfx) {
                    zzgsVar.zzb((zzfx) obj);
                } else {
                    zzgsVar.zzbk((String) obj);
                }
                break;
            case 12:
                if (obj instanceof zzfx) {
                    zzgsVar.zzb((zzfx) obj);
                } else {
                    byte[] bArr = (byte[]) obj;
                    zzgsVar.zzd(bArr, 0, bArr.length);
                }
                break;
            case 13:
                zzgsVar.zzan(((Integer) obj).intValue());
                break;
            case 14:
                zzgsVar.zzap(((Integer) obj).intValue());
                break;
            case 15:
                zzgsVar.zzh(((Long) obj).longValue());
                break;
            case 16:
                zzgsVar.zzao(((Integer) obj).intValue());
                break;
            case 17:
                zzgsVar.zzg(((Long) obj).longValue());
                break;
            case 18:
                if (obj instanceof zzhl) {
                    zzgsVar.zzam(((zzhl) obj).zzgn());
                } else {
                    zzgsVar.zzam(((Integer) obj).intValue());
                }
                break;
        }
    }

    public final int zzgj() {
        int iZzd = 0;
        for (int i = 0; i < this.zzqf.zzir(); i++) {
            iZzd += zzd(this.zzqf.zzbm(i));
        }
        Iterator it = this.zzqf.zzis().iterator();
        while (it.hasNext()) {
            iZzd += zzd((Map.Entry) it.next());
        }
        return iZzd;
    }

    private static int zzd(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorType key = entry.getKey();
        Object value = entry.getValue();
        if (key.zzgp() == zzkr.MESSAGE && !key.zzgq() && !key.zzgr()) {
            if (value instanceof zzhr) {
                return zzgs.zzb(entry.getKey().zzgn(), (zzhr) value);
            }
            return zzgs.zzb(entry.getKey().zzgn(), (zziq) value);
        }
        return zzb((zzhc<?>) key, value);
    }

    static int zza(zzko zzkoVar, int i, Object obj) {
        int iZzaq = zzgs.zzaq(i);
        if (zzkoVar == zzko.zzys) {
            zzhm.zzf((zziq) obj);
            iZzaq <<= 1;
        }
        return iZzaq + zzb(zzkoVar, obj);
    }

    private static int zzb(zzko zzkoVar, Object obj) {
        switch (zzhd.zzpr[zzkoVar.ordinal()]) {
            case 1:
                return zzgs.zzd(((Double) obj).doubleValue());
            case 2:
                return zzgs.zzc(((Float) obj).floatValue());
            case 3:
                return zzgs.zzi(((Long) obj).longValue());
            case 4:
                return zzgs.zzj(((Long) obj).longValue());
            case 5:
                return zzgs.zzar(((Integer) obj).intValue());
            case 6:
                return zzgs.zzl(((Long) obj).longValue());
            case 7:
                return zzgs.zzau(((Integer) obj).intValue());
            case 8:
                return zzgs.zze(((Boolean) obj).booleanValue());
            case 9:
                return zzgs.zzd((zziq) obj);
            case 10:
                if (obj instanceof zzhr) {
                    return zzgs.zza((zzhr) obj);
                }
                return zzgs.zzc((zziq) obj);
            case 11:
                if (obj instanceof zzfx) {
                    return zzgs.zzc((zzfx) obj);
                }
                return zzgs.zzbl((String) obj);
            case 12:
                if (obj instanceof zzfx) {
                    return zzgs.zzc((zzfx) obj);
                }
                return zzgs.zzd((byte[]) obj);
            case 13:
                return zzgs.zzas(((Integer) obj).intValue());
            case 14:
                return zzgs.zzav(((Integer) obj).intValue());
            case 15:
                return zzgs.zzm(((Long) obj).longValue());
            case 16:
                return zzgs.zzat(((Integer) obj).intValue());
            case 17:
                return zzgs.zzk(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzhl) {
                    return zzgs.zzaw(((zzhl) obj).zzgn());
                }
                return zzgs.zzaw(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int zzb(zzhc<?> zzhcVar, Object obj) {
        zzko zzkoVarZzgo = zzhcVar.zzgo();
        int iZzgn = zzhcVar.zzgn();
        if (zzhcVar.zzgq()) {
            int iZza = 0;
            if (zzhcVar.zzgr()) {
                Iterator it = ((List) obj).iterator();
                while (it.hasNext()) {
                    iZza += zzb(zzkoVarZzgo, it.next());
                }
                return zzgs.zzaq(iZzgn) + iZza + zzgs.zzay(iZza);
            }
            Iterator it2 = ((List) obj).iterator();
            while (it2.hasNext()) {
                iZza += zza(zzkoVarZzgo, iZzgn, it2.next());
            }
            return iZza;
        }
        return zza(zzkoVarZzgo, iZzgn, obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzha zzhaVar = new zzha();
        for (int i = 0; i < this.zzqf.zzir(); i++) {
            Map.Entry<K, Object> entryZzbm = this.zzqf.zzbm(i);
            zzhaVar.zza((zzhc) entryZzbm.getKey(), entryZzbm.getValue());
        }
        Iterator it = this.zzqf.zzis().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            zzhaVar.zza((zzhc) entry.getKey(), entry.getValue());
        }
        zzhaVar.zzqh = this.zzqh;
        return zzhaVar;
    }
}
