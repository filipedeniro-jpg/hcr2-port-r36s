package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.VisibleForTesting;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcfj<DelegateT, AdapterT> implements zzcfk<AdapterT> {

    @VisibleForTesting
    private final zzcfk<DelegateT> zzfwr;
    private final zzczs<DelegateT, AdapterT> zzfws;

    public zzcfj(zzcfk<DelegateT> zzcfkVar, zzczs<DelegateT, AdapterT> zzczsVar) {
        this.zzfwr = zzcfkVar;
        this.zzfws = zzczsVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcfk
    public final boolean zza(zzcvj zzcvjVar, zzcvb zzcvbVar) {
        return this.zzfwr.zza(zzcvjVar, zzcvbVar);
    }

    @Override // com.google.android.gms.internal.ads.zzcfk
    public final zzdcp<AdapterT> zzb(zzcvj zzcvjVar, zzcvb zzcvbVar) {
        return zzdcf.zzb(this.zzfwr.zzb(zzcvjVar, zzcvbVar), this.zzfws, zzawx.zzdvx);
    }
}
