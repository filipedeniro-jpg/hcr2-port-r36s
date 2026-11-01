package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzbvz implements zzczs {
    static final zzczs zzdoi = new zzbvz();

    private zzbvz() {
    }

    @Override // com.google.android.gms.internal.ads.zzczs
    public final Object apply(Object obj) {
        ArrayList arrayList = new ArrayList();
        for (zzaae zzaaeVar : (List) obj) {
            if (zzaaeVar != null) {
                arrayList.add(zzaaeVar);
            }
        }
        return arrayList;
    }
}
