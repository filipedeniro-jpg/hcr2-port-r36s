package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcsp implements zzcra<zzcsm> {
    List<String> zzdit;
    private zzdcs zzfnq;
    zzyf zzggx;

    public zzcsp(zzyf zzyfVar, zzdcs zzdcsVar, List<String> list) {
        this.zzggx = zzyfVar;
        this.zzfnq = zzdcsVar;
        this.zzdit = list;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcsm> zzalr() {
        return this.zzfnq.submit(new Callable(this) { // from class: com.google.android.gms.internal.ads.zzcso
            private final zzcsp zzggw;

            {
                this.zzggw = this;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                zzcsp zzcspVar = this.zzggw;
                return new zzcsm(zzcspVar.zzggx.zzd(zzcspVar.zzdit));
            }
        });
    }
}
