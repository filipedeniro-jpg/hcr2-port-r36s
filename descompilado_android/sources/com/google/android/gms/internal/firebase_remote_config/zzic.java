package com.google.android.gms.internal.firebase_remote_config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
final class zzic extends zzia {
    private static final Class<?> zzvc = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzic() {
        super();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzia
    final <L> List<L> zza(Object obj, long j) {
        return zza(obj, j, 10);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzia
    final void zzb(Object obj, long j) {
        Object objUnmodifiableList;
        List list = (List) zzkd.zzp(obj, j);
        if (list instanceof zzhx) {
            objUnmodifiableList = ((zzhx) list).zzhr();
        } else {
            if (zzvc.isAssignableFrom(list.getClass())) {
                return;
            }
            if ((list instanceof zzjc) && (list instanceof zzhn)) {
                zzhn zzhnVar = (zzhn) list;
                if (zzhnVar.zzer()) {
                    zzhnVar.zzes();
                    return;
                }
                return;
            }
            objUnmodifiableList = Collections.unmodifiableList(list);
        }
        zzkd.zza(obj, j, objUnmodifiableList);
    }

    private static <L> List<L> zza(Object obj, long j, int i) {
        Object obj2;
        List<L> arrayList;
        List<L> listZzd = zzd(obj, j);
        if (listZzd.isEmpty()) {
            if (listZzd instanceof zzhx) {
                arrayList = new zzhy(i);
            } else if ((listZzd instanceof zzjc) && (listZzd instanceof zzhn)) {
                arrayList = ((zzhn) listZzd).zzu(i);
            } else {
                arrayList = new ArrayList<>(i);
            }
            zzkd.zza(obj, j, arrayList);
            return arrayList;
        }
        if (zzvc.isAssignableFrom(listZzd.getClass())) {
            ArrayList arrayList2 = new ArrayList(listZzd.size() + i);
            arrayList2.addAll(listZzd);
            zzkd.zza(obj, j, arrayList2);
            obj2 = arrayList2;
        } else if (listZzd instanceof zzkc) {
            zzhy zzhyVar = new zzhy(listZzd.size() + i);
            zzhyVar.addAll((zzkc) listZzd);
            zzkd.zza(obj, j, zzhyVar);
            obj2 = zzhyVar;
        } else {
            if (!(listZzd instanceof zzjc) || !(listZzd instanceof zzhn)) {
                return listZzd;
            }
            zzhn zzhnVar = (zzhn) listZzd;
            if (zzhnVar.zzer()) {
                return listZzd;
            }
            zzhn zzhnVarZzu = zzhnVar.zzu(listZzd.size() + i);
            zzkd.zza(obj, j, zzhnVarZzu);
            return zzhnVarZzu;
        }
        return (List<L>) obj2;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzia
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
        zzkd.zza(obj, j, listZzd);
    }

    private static <E> List<E> zzd(Object obj, long j) {
        return (List) zzkd.zzp(obj, j);
    }
}
