package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdqz extends zzdqx {
    private static final Class<?> zzhlz = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzdqz() {
        super();
    }

    @Override // com.google.android.gms.internal.ads.zzdqx
    final <L> List<L> zza(Object obj, long j) {
        return zza(obj, j, 10);
    }

    @Override // com.google.android.gms.internal.ads.zzdqx
    final void zzb(Object obj, long j) {
        Object objUnmodifiableList;
        List list = (List) zzdta.zzp(obj, j);
        if (list instanceof zzdqu) {
            objUnmodifiableList = ((zzdqu) list).zzbaj();
        } else {
            if (zzhlz.isAssignableFrom(list.getClass())) {
                return;
            }
            if ((list instanceof zzdrz) && (list instanceof zzdqk)) {
                zzdqk zzdqkVar = (zzdqk) list;
                if (zzdqkVar.zzaxd()) {
                    zzdqkVar.zzaxe();
                    return;
                }
                return;
            }
            objUnmodifiableList = Collections.unmodifiableList(list);
        }
        zzdta.zza(obj, j, objUnmodifiableList);
    }

    private static <L> List<L> zza(Object obj, long j, int i) {
        Object obj2;
        List<L> arrayList;
        List<L> listZzd = zzd(obj, j);
        if (listZzd.isEmpty()) {
            if (listZzd instanceof zzdqu) {
                arrayList = new zzdqv(i);
            } else if ((listZzd instanceof zzdrz) && (listZzd instanceof zzdqk)) {
                arrayList = ((zzdqk) listZzd).zzfm(i);
            } else {
                arrayList = new ArrayList<>(i);
            }
            zzdta.zza(obj, j, arrayList);
            return arrayList;
        }
        if (zzhlz.isAssignableFrom(listZzd.getClass())) {
            ArrayList arrayList2 = new ArrayList(listZzd.size() + i);
            arrayList2.addAll(listZzd);
            zzdta.zza(obj, j, arrayList2);
            obj2 = arrayList2;
        } else if (listZzd instanceof zzdsz) {
            zzdqv zzdqvVar = new zzdqv(listZzd.size() + i);
            zzdqvVar.addAll((zzdsz) listZzd);
            zzdta.zza(obj, j, zzdqvVar);
            obj2 = zzdqvVar;
        } else {
            if (!(listZzd instanceof zzdrz) || !(listZzd instanceof zzdqk)) {
                return listZzd;
            }
            zzdqk zzdqkVar = (zzdqk) listZzd;
            if (zzdqkVar.zzaxd()) {
                return listZzd;
            }
            zzdqk zzdqkVarZzfm = zzdqkVar.zzfm(listZzd.size() + i);
            zzdta.zza(obj, j, zzdqkVarZzfm);
            return zzdqkVarZzfm;
        }
        return (List<L>) obj2;
    }

    @Override // com.google.android.gms.internal.ads.zzdqx
    final <E> void zza(Object obj, Object obj2, long j) {
        List listZzd = zzd(obj2, j);
        List listZza = zza(obj, j, listZzd.size());
        int size = listZza.size();
        int size2 = listZzd.size();
        if (size > 0 && size2 > 0) {
            listZza.addAll(listZzd);
        }
        if (size > 0) {
            listZzd = listZza;
        }
        zzdta.zza(obj, j, listZzd);
    }

    private static <E> List<E> zzd(Object obj, long j) {
        return (List) zzdta.zzp(obj, j);
    }
}
