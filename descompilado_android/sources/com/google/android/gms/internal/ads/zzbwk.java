package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzbwk implements zzczs {
    static final zzczs zzdoi = new zzbwk();

    private zzbwk() {
    }

    @Override // com.google.android.gms.internal.ads.zzczs
    public final Object apply(Object obj) {
        ArrayList arrayList = new ArrayList();
        for (zzbwm zzbwmVar : (List) obj) {
            if (zzbwmVar != null) {
                arrayList.add(zzbwmVar);
            }
        }
        return arrayList;
    }
}
