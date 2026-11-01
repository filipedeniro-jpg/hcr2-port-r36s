package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcoq implements zzcra<zzcov> {
    private final zzdcs zzfnq;
    private final Context zzzc;

    zzcoq(Context context, zzdcs zzdcsVar) {
        this.zzzc = context;
        this.zzfnq = zzdcsVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcov> zzalr() {
        return this.zzfnq.submit(new Callable(this) { // from class: com.google.android.gms.internal.ads.zzcot
            private final zzcoq zzgeq;

            {
                this.zzgeq = this;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zzgeq.zzalw();
            }
        });
    }

    final /* synthetic */ zzcov zzalw() throws Exception {
        com.google.android.gms.ads.internal.zzp.zzjy();
        String strZzay = zzatv.zzay(this.zzzc);
        com.google.android.gms.ads.internal.zzp.zzjy();
        return new zzcov(strZzay, zzatv.zzaz(this.zzzc));
    }
}
