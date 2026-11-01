package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.Set;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcpv implements zzcra<zzcps> {
    private final zzdcs zzfnq;
    private final Set<String> zzgdu;
    private final Context zzlk;

    public zzcpv(zzdcs zzdcsVar, Context context, Set<String> set) {
        this.zzfnq = zzdcsVar;
        this.zzlk = context;
        this.zzgdu = set;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcps> zzalr() {
        return this.zzfnq.submit(new Callable(this) { // from class: com.google.android.gms.internal.ads.zzcpu
            private final zzcpv zzgez;

            {
                this.zzgez = this;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zzgez.zzalz();
            }
        });
    }

    final /* synthetic */ zzcps zzalz() throws Exception {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcqm)).booleanValue() && zzcps.zzd(this.zzgdu)) {
            return new zzcps(com.google.android.gms.ads.internal.zzp.zzkn().getVersion(this.zzlk));
        }
        return new zzcps(null);
    }
}
