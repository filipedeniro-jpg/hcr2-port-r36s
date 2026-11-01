package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdbu extends zzdbs {
    zzdbu(zzdbr zzdbrVar, zzdaf<? extends zzdcp<? extends V>> zzdafVar, boolean z) {
        super(zzdbrVar, zzdafVar, true);
    }

    @Override // com.google.android.gms.internal.ads.zzdbs
    public final /* synthetic */ Object zzj(List list) {
        ArrayList arrayListZzdu = zzdas.zzdu(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            zzczy zzczyVar = (zzczy) it.next();
            arrayListZzdu.add(zzczyVar != null ? zzczyVar.zzaoa() : null);
        }
        return Collections.unmodifiableList(arrayListZzdu);
    }
}
