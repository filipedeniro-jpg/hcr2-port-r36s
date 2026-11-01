package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcsc implements zzcra<zzcsd> {
    private zzdcs zzfnq;
    zzrn zzggo;
    Context zzlk;

    public zzcsc(zzrn zzrnVar, zzdcs zzdcsVar, Context context) {
        this.zzggo = zzrnVar;
        this.zzfnq = zzdcsVar;
        this.zzlk = context;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcsd> zzalr() {
        return this.zzfnq.submit(new Callable(this) { // from class: com.google.android.gms.internal.ads.zzcsf
            private final zzcsc zzggq;

            {
                this.zzggq = this;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                zzcsc zzcscVar = this.zzggq;
                return new zzcsd(zzcscVar.zzggo.zzf(zzcscVar.zzlk));
            }
        });
    }
}
