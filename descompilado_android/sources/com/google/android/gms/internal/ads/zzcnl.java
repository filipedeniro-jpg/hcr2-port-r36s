package com.google.android.gms.internal.ads;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcnl implements zzcra<zzcrb<Bundle>> {
    private final Set<String> zzgdu;

    zzcnl(Set<String> set) {
        this.zzgdu = set;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcrb<Bundle>> zzalr() {
        final ArrayList arrayList = new ArrayList();
        Iterator<String> it = this.zzgdu.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return zzdcf.zzah(new zzcrb(arrayList) { // from class: com.google.android.gms.internal.ads.zzcnk
            private final ArrayList zzgdt;

            {
                this.zzgdt = arrayList;
            }

            @Override // com.google.android.gms.internal.ads.zzcrb
            public final void zzr(Object obj) {
                ((Bundle) obj).putStringArrayList("ad_types", this.zzgdt);
            }
        });
    }
}
