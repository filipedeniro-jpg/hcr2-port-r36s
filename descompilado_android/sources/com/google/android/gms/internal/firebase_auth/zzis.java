package com.google.android.gms.internal.firebase_auth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzis extends zziq {
    private static final Class<?> zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzis() {
        super();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zziq
    final <L> List<L> zza(Object obj, long j) {
        return zza(obj, j, 10);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zziq
    final void zzb(Object obj, long j) {
        Object objUnmodifiableList;
        List list = (List) zzky.zzf(obj, j);
        if (list instanceof zzir) {
            objUnmodifiableList = ((zzir) list).zze();
        } else {
            if (zza.isAssignableFrom(list.getClass())) {
                return;
            }
            if ((list instanceof zzjt) && (list instanceof zzih)) {
                zzih zzihVar = (zzih) list;
                if (zzihVar.zza()) {
                    zzihVar.zzb();
                    return;
                }
                return;
            }
            objUnmodifiableList = Collections.unmodifiableList(list);
        }
        zzky.zza(obj, j, objUnmodifiableList);
    }

    private static <L> List<L> zza(Object obj, long j, int i) {
        Object obj2;
        List<L> arrayList;
        List<L> listZzc = zzc(obj, j);
        if (listZzc.isEmpty()) {
            if (listZzc instanceof zzir) {
                arrayList = new zzio(i);
            } else if ((listZzc instanceof zzjt) && (listZzc instanceof zzih)) {
                arrayList = ((zzih) listZzc).zza(i);
            } else {
                arrayList = new ArrayList<>(i);
            }
            zzky.zza(obj, j, arrayList);
            return arrayList;
        }
        if (zza.isAssignableFrom(listZzc.getClass())) {
            ArrayList arrayList2 = new ArrayList(listZzc.size() + i);
            arrayList2.addAll(listZzc);
            zzky.zza(obj, j, arrayList2);
            obj2 = arrayList2;
        } else if (listZzc instanceof zzkt) {
            zzio zzioVar = new zzio(listZzc.size() + i);
            zzioVar.addAll((zzkt) listZzc);
            zzky.zza(obj, j, zzioVar);
            obj2 = zzioVar;
        } else {
            if (!(listZzc instanceof zzjt) || !(listZzc instanceof zzih)) {
                return listZzc;
            }
            zzih zzihVar = (zzih) listZzc;
            if (zzihVar.zza()) {
                return listZzc;
            }
            zzih zzihVarZza = zzihVar.zza(listZzc.size() + i);
            zzky.zza(obj, j, zzihVarZza);
            return zzihVarZza;
        }
        return (List<L>) obj2;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zziq
    final <E> void zza(Object obj, Object obj2, long j) {
        List listZzc = zzc(obj2, j);
        List listZza = zza(obj, j, listZzc.size());
        int size = listZza.size();
        int size2 = listZzc.size();
        if (size > 0 && size2 > 0) {
            listZza.addAll(listZzc);
        }
        if (size > 0) {
            listZzc = listZza;
        }
        zzky.zza(obj, j, listZzc);
    }

    private static <E> List<E> zzc(Object obj, long j) {
        return (List) zzky.zzf(obj, j);
    }
}
