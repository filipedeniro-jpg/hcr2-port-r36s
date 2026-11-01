package com.google.android.gms.internal.ads;

import android.util.JsonReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcmq implements zzdbq<zzaok, zzcmv> {
    private Executor executor;
    private zzcdo zzgdc;

    public zzcmq(Executor executor, zzcdo zzcdoVar) {
        this.executor = executor;
        this.zzgdc = zzcdoVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdbq
    public final /* synthetic */ zzdcp<zzcmv> zzf(zzaok zzaokVar) throws Exception {
        final zzaok zzaokVar2 = zzaokVar;
        return zzdcf.zzb(this.zzgdc.zzg(zzaokVar2), new zzdbq(zzaokVar2) { // from class: com.google.android.gms.internal.ads.zzcmt
            private final zzaok zzfgt;

            {
                this.zzfgt = zzaokVar2;
            }

            @Override // com.google.android.gms.internal.ads.zzdbq
            public final zzdcp zzf(Object obj) {
                return zzdcf.zzah(new zzcmv(new JsonReader(new InputStreamReader((InputStream) obj))).zzn(this.zzfgt.zzdlo));
            }
        }, this.executor);
    }
}
