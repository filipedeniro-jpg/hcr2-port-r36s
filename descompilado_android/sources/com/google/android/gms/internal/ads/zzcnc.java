package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzcnc implements zzdbq {
    static final zzdbq zzbks = new zzcnc();

    private zzcnc() {
    }

    @Override // com.google.android.gms.internal.ads.zzdbq
    public final zzdcp zzf(Object obj) {
        final String str = (String) obj;
        return zzdcf.zzah(new zzcrb(str) { // from class: com.google.android.gms.internal.ads.zzcnf
            private final String zzcys;

            {
                this.zzcys = str;
            }

            @Override // com.google.android.gms.internal.ads.zzcrb
            public final void zzr(Object obj2) {
                ((Bundle) obj2).putString("ms", this.zzcys);
            }
        });
    }
}
