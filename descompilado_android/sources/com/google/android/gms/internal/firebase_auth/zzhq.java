package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzhs;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzhq<T extends zzhs<T>> {
    private static final zzhq zzd = new zzhq(true);
    final zzkb<T, Object> zza;
    private boolean zzb;
    private boolean zzc;

    private zzhq() {
        this.zza = zzkb.zza(16);
    }

    private zzhq(boolean z) {
        this(zzkb.zza(0));
        zzb();
    }

    private zzhq(zzkb<T, Object> zzkbVar) {
        this.zza = zzkbVar;
        zzb();
    }

    public static <T extends zzhs<T>> zzhq<T> zza() {
        return zzd;
    }

    public final void zzb() {
        if (this.zzb) {
            return;
        }
        this.zza.zza();
        this.zzb = true;
    }

    public final boolean zzc() {
        return this.zzb;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzhq) {
            return this.zza.equals(((zzhq) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final Iterator<Map.Entry<T, Object>> zzd() {
        if (this.zzc) {
            return new zzim(this.zza.entrySet().iterator());
        }
        return this.zza.entrySet().iterator();
    }

    final Iterator<Map.Entry<T, Object>> zze() {
        if (this.zzc) {
            return new zzim(this.zza.zze().iterator());
        }
        return this.zza.zze().iterator();
    }

    private final Object zza(T t) {
        Object obj = this.zza.get(t);
        return obj instanceof zzil ? zzil.zza() : obj;
    }

    private final void zzb(T t, Object obj) {
        if (t.zzd()) {
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
                zza(t.zzb(), obj2);
            }
            obj = arrayList;
        } else {
            zza(t.zzb(), obj);
        }
        if (obj instanceof zzil) {
            this.zzc = true;
        }
        this.zza.put(t, obj);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:4:0x0014  */
    private static void zza(zzlf zzlfVar, Object obj) {
        zzib.zza(obj);
        boolean z = true;
        switch (zzhp.zza[zzlfVar.zza().ordinal()]) {
            case 1:
                z = obj instanceof Integer;
                break;
            case 2:
                z = obj instanceof Long;
                break;
            case 3:
                z = obj instanceof Float;
                break;
            case 4:
                z = obj instanceof Double;
                break;
            case 5:
                z = obj instanceof Boolean;
                break;
            case 6:
                z = obj instanceof String;
                break;
            case 7:
                if (!(obj instanceof zzgm) && !(obj instanceof byte[])) {
                    z = false;
                }
                break;
            case 8:
                if (!(obj instanceof Integer) && !(obj instanceof zzia)) {
                    z = false;
                }
                break;
            case 9:
                if (!(obj instanceof zzjg) && !(obj instanceof zzil)) {
                    z = false;
                }
                break;
            default:
                z = false;
                break;
        }
        if (!z) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    public final boolean zzf() {
        for (int i = 0; i < this.zza.zzc(); i++) {
            if (!zza((Map.Entry) this.zza.zzb(i))) {
                return false;
            }
        }
        Iterator it = this.zza.zzd().iterator();
        while (it.hasNext()) {
            if (!zza((Map.Entry) it.next())) {
                return false;
            }
        }
        return true;
    }

    private static <T extends zzhs<T>> boolean zza(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        if (key.zzc() == zzlm.MESSAGE) {
            if (key.zzd()) {
                Iterator it = ((List) entry.getValue()).iterator();
                while (it.hasNext()) {
                    if (!((zzjg) it.next()).zzaa()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zzjg) {
                    if (!((zzjg) value).zzaa()) {
                        return false;
                    }
                } else {
                    if (value instanceof zzil) {
                        return true;
                    }
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    public final void zza(zzhq<T> zzhqVar) {
        for (int i = 0; i < zzhqVar.zza.zzc(); i++) {
            zzb(zzhqVar.zza.zzb(i));
        }
        Iterator it = zzhqVar.zza.zzd().iterator();
        while (it.hasNext()) {
            zzb((Map.Entry) it.next());
        }
    }

    private static Object zza(Object obj) {
        if (obj instanceof zzjm) {
            return ((zzjm) obj).clone();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    private final void zzb(Map.Entry<T, Object> entry) {
        zzjg zzjgVarZzf;
        T key = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzil) {
            value = zzil.zza();
        }
        if (key.zzd()) {
            Object objZza = zza((zzhs) key);
            if (objZza == null) {
                objZza = new ArrayList();
            }
            Iterator it = ((List) value).iterator();
            while (it.hasNext()) {
                ((List) objZza).add(zza(it.next()));
            }
            this.zza.put(key, objZza);
            return;
        }
        if (key.zzc() == zzlm.MESSAGE) {
            Object objZza2 = zza((zzhs) key);
            if (objZza2 == null) {
                this.zza.put(key, zza(value));
                return;
            }
            if (objZza2 instanceof zzjm) {
                zzjgVarZzf = key.zza((zzjm) objZza2, (zzjm) value);
            } else {
                zzjgVarZzf = key.zza(((zzjg) objZza2).zzae(), (zzjg) value).zzf();
            }
            this.zza.put(key, zzjgVarZzf);
            return;
        }
        this.zza.put(key, zza(value));
    }

    static void zza(zzhh zzhhVar, zzlf zzlfVar, int i, Object obj) throws IOException {
        if (zzlfVar == zzlf.zzj) {
            zzjg zzjgVar = (zzjg) obj;
            zzib.zza(zzjgVar);
            zzhhVar.zza(i, 3);
            zzjgVar.zza(zzhhVar);
            zzhhVar.zza(i, 4);
        }
        zzhhVar.zza(i, zzlfVar.zzb());
        switch (zzhp.zzb[zzlfVar.ordinal()]) {
            case 1:
                zzhhVar.zza(((Double) obj).doubleValue());
                break;
            case 2:
                zzhhVar.zza(((Float) obj).floatValue());
                break;
            case 3:
                zzhhVar.zza(((Long) obj).longValue());
                break;
            case 4:
                zzhhVar.zza(((Long) obj).longValue());
                break;
            case 5:
                zzhhVar.zza(((Integer) obj).intValue());
                break;
            case 6:
                zzhhVar.zzc(((Long) obj).longValue());
                break;
            case 7:
                zzhhVar.zzd(((Integer) obj).intValue());
                break;
            case 8:
                zzhhVar.zza(((Boolean) obj).booleanValue());
                break;
            case 9:
                ((zzjg) obj).zza(zzhhVar);
                break;
            case 10:
                zzhhVar.zza((zzjg) obj);
                break;
            case 11:
                if (obj instanceof zzgm) {
                    zzhhVar.zza((zzgm) obj);
                } else {
                    zzhhVar.zza((String) obj);
                }
                break;
            case 12:
                if (obj instanceof zzgm) {
                    zzhhVar.zza((zzgm) obj);
                } else {
                    byte[] bArr = (byte[]) obj;
                    zzhhVar.zzb(bArr, 0, bArr.length);
                }
                break;
            case 13:
                zzhhVar.zzb(((Integer) obj).intValue());
                break;
            case 14:
                zzhhVar.zzd(((Integer) obj).intValue());
                break;
            case 15:
                zzhhVar.zzc(((Long) obj).longValue());
                break;
            case 16:
                zzhhVar.zzc(((Integer) obj).intValue());
                break;
            case 17:
                zzhhVar.zzb(((Long) obj).longValue());
                break;
            case 18:
                if (obj instanceof zzia) {
                    zzhhVar.zza(((zzia) obj).zza());
                } else {
                    zzhhVar.zza(((Integer) obj).intValue());
                }
                break;
        }
    }

    public final int zzg() {
        int iZzc = 0;
        for (int i = 0; i < this.zza.zzc(); i++) {
            iZzc += zzc(this.zza.zzb(i));
        }
        Iterator it = this.zza.zzd().iterator();
        while (it.hasNext()) {
            iZzc += zzc((Map.Entry) it.next());
        }
        return iZzc;
    }

    private static int zzc(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        Object value = entry.getValue();
        if (key.zzc() == zzlm.MESSAGE && !key.zzd() && !key.zze()) {
            if (value instanceof zzil) {
                return zzhh.zzb(entry.getKey().zza(), (zzil) value);
            }
            return zzhh.zzb(entry.getKey().zza(), (zzjg) value);
        }
        return zza((zzhs<?>) key, value);
    }

    static int zza(zzlf zzlfVar, int i, Object obj) {
        int iZze = zzhh.zze(i);
        if (zzlfVar == zzlf.zzj) {
            zzib.zza((zzjg) obj);
            iZze <<= 1;
        }
        return iZze + zzb(zzlfVar, obj);
    }

    private static int zzb(zzlf zzlfVar, Object obj) {
        switch (zzhp.zzb[zzlfVar.ordinal()]) {
            case 1:
                return zzhh.zzb(((Double) obj).doubleValue());
            case 2:
                return zzhh.zzb(((Float) obj).floatValue());
            case 3:
                return zzhh.zzd(((Long) obj).longValue());
            case 4:
                return zzhh.zze(((Long) obj).longValue());
            case 5:
                return zzhh.zzf(((Integer) obj).intValue());
            case 6:
                return zzhh.zzg(((Long) obj).longValue());
            case 7:
                return zzhh.zzi(((Integer) obj).intValue());
            case 8:
                return zzhh.zzb(((Boolean) obj).booleanValue());
            case 9:
                return zzhh.zzc((zzjg) obj);
            case 10:
                if (obj instanceof zzil) {
                    return zzhh.zza((zzil) obj);
                }
                return zzhh.zzb((zzjg) obj);
            case 11:
                if (obj instanceof zzgm) {
                    return zzhh.zzb((zzgm) obj);
                }
                return zzhh.zzb((String) obj);
            case 12:
                if (obj instanceof zzgm) {
                    return zzhh.zzb((zzgm) obj);
                }
                return zzhh.zzb((byte[]) obj);
            case 13:
                return zzhh.zzg(((Integer) obj).intValue());
            case 14:
                return zzhh.zzj(((Integer) obj).intValue());
            case 15:
                return zzhh.zzh(((Long) obj).longValue());
            case 16:
                return zzhh.zzh(((Integer) obj).intValue());
            case 17:
                return zzhh.zzf(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzia) {
                    return zzhh.zzk(((zzia) obj).zza());
                }
                return zzhh.zzk(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int zza(zzhs<?> zzhsVar, Object obj) {
        zzlf zzlfVarZzb = zzhsVar.zzb();
        int iZza = zzhsVar.zza();
        if (zzhsVar.zzd()) {
            int iZza2 = 0;
            if (zzhsVar.zze()) {
                Iterator it = ((List) obj).iterator();
                while (it.hasNext()) {
                    iZza2 += zzb(zzlfVarZzb, it.next());
                }
                return zzhh.zze(iZza) + iZza2 + zzhh.zzl(iZza2);
            }
            Iterator it2 = ((List) obj).iterator();
            while (it2.hasNext()) {
                iZza2 += zza(zzlfVarZzb, iZza, it2.next());
            }
            return iZza2;
        }
        return zza(zzlfVarZzb, iZza, obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzhq zzhqVar = new zzhq();
        for (int i = 0; i < this.zza.zzc(); i++) {
            Map.Entry<K, Object> entryZzb = this.zza.zzb(i);
            zzhqVar.zzb((zzhs) entryZzb.getKey(), entryZzb.getValue());
        }
        Iterator it = this.zza.zzd().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            zzhqVar.zzb((zzhs) entry.getKey(), entry.getValue());
        }
        zzhqVar.zzc = this.zzc;
        return zzhqVar;
    }
}
