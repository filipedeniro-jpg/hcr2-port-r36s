package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcpi implements zzcra<zzcpj> {
    private final zzcuz zzfao;
    private final zzdcs zzfnq;

    public zzcpi(zzdcs zzdcsVar, zzcuz zzcuzVar) {
        this.zzfnq = zzdcsVar;
        this.zzfao = zzcuzVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcpj> zzalr() {
        return this.zzfnq.submit(new Callable(this) { // from class: com.google.android.gms.internal.ads.zzcpl
            private final zzcpi zzgev;

            {
                this.zzgev = this;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zzgev.zzalx();
            }
        });
    }

    final /* synthetic */ zzcpj zzalx() throws Exception {
        return new zzcpj(this.zzfao);
    }
}
