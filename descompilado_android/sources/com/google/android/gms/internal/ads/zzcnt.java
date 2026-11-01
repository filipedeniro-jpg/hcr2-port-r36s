package com.google.android.gms.internal.ads;

import android.os.Bundle;
import java.util.ArrayList;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzcnt implements zzczs {
    static final zzczs zzdoi = new zzcnt();

    private zzcnt() {
    }

    @Override // com.google.android.gms.internal.ads.zzczs
    public final Object apply(Object obj) {
        final ArrayList arrayList = (ArrayList) obj;
        if (arrayList.isEmpty()) {
            return null;
        }
        return new zzcrb(arrayList) { // from class: com.google.android.gms.internal.ads.zzcns
            private final ArrayList zzgdt;

            {
                this.zzgdt = arrayList;
            }

            @Override // com.google.android.gms.internal.ads.zzcrb
            public final void zzr(Object obj2) {
                ((Bundle) obj2).putStringArrayList("android_permissions", this.zzgdt);
            }
        };
    }
}
