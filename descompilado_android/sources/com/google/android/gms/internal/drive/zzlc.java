package com.google.android.gms.internal.drive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
final class zzlc extends zzla {
    private static final Class<?> zzto = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzlc() {
        super();
    }

    @Override // com.google.android.gms.internal.drive.zzla
    final void zza(Object obj, long j) {
        Object objUnmodifiableList;
        List list = (List) zznd.zzo(obj, j);
        if (list instanceof zzkz) {
            objUnmodifiableList = ((zzkz) list).zzds();
        } else {
            if (zzto.isAssignableFrom(list.getClass())) {
                return;
            }
            if ((list instanceof zzmc) && (list instanceof zzkp)) {
                zzkp zzkpVar = (zzkp) list;
                if (zzkpVar.zzbo()) {
                    zzkpVar.zzbp();
                    return;
                }
                return;
            }
            objUnmodifiableList = Collections.unmodifiableList(list);
        }
        zznd.zza(obj, j, objUnmodifiableList);
    }

    @Override // com.google.android.gms.internal.drive.zzla
    final <E> void zza(Object obj, Object obj2, long j) {
        List list;
        List list2;
        List arrayList;
        List listZzb = zzb(obj2, j);
        int size = listZzb.size();
        List listZzb2 = zzb(obj, j);
        if (listZzb2.isEmpty()) {
            if (listZzb2 instanceof zzkz) {
                arrayList = new zzky(size);
            } else if ((listZzb2 instanceof zzmc) && (listZzb2 instanceof zzkp)) {
                arrayList = ((zzkp) listZzb2).zzr(size);
            } else {
                arrayList = new ArrayList(size);
            }
            zznd.zza(obj, j, arrayList);
            list = arrayList;
        } else {
            if (zzto.isAssignableFrom(listZzb2.getClass())) {
                ArrayList arrayList2 = new ArrayList(listZzb2.size() + size);
                arrayList2.addAll(listZzb2);
                zznd.zza(obj, j, arrayList2);
                list2 = arrayList2;
            } else if (listZzb2 instanceof zzna) {
                zzky zzkyVar = new zzky(listZzb2.size() + size);
                zzkyVar.addAll((zzna) listZzb2);
                zznd.zza(obj, j, zzkyVar);
                list2 = zzkyVar;
            } else if ((listZzb2 instanceof zzmc) && (listZzb2 instanceof zzkp)) {
                zzkp zzkpVar = (zzkp) listZzb2;
                if (!zzkpVar.zzbo()) {
                    list = listZzb2;
                    list = listZzb2;
                    list = listZzb2;
                    zzkp<E> zzkpVarZzr = zzkpVar.zzr(listZzb2.size() + size);
                    zznd.zza(obj, j, zzkpVarZzr);
                    list = zzkpVarZzr;
                }
            }
            list = list2;
        }
        list = listZzb2;
        list = listZzb2;
        list = listZzb2;
        list = listZzb2;
        list = listZzb2;
        list = listZzb2;
        int size2 = list.size();
        int size3 = listZzb.size();
        if (size2 > 0 && size3 > 0) {
            list.addAll(listZzb);
        }
        if (size2 > 0) {
            listZzb = list;
        }
        zznd.zza(obj, j, listZzb);
    }

    private static <E> List<E> zzb(Object obj, long j) {
        return (List) zznd.zzo(obj, j);
    }
}
