package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcnp implements zzcra<zzcnm> {
    private final zzcvk zzffn;
    private final zzdcs zzfnq;

    public zzcnp(zzdcs zzdcsVar, zzcvk zzcvkVar) {
        this.zzfnq = zzdcsVar;
        this.zzffn = zzcvkVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcnm> zzalr() {
        return this.zzfnq.submit(new Callable(this) { // from class: com.google.android.gms.internal.ads.zzcno
            private final zzcnp zzgdx;

            {
                this.zzgdx = this;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zzgdx.zzalt();
            }
        });
    }

    final /* synthetic */ zzcnm zzalt() throws Exception {
        return new zzcnm(this.zzffn.zzgjw);
    }
}
