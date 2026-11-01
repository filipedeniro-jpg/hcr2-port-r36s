package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbnj;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcka<AdT, AdapterT, ListenerT extends zzbnj> implements zzdvi<zzcjw<AdT, AdapterT, ListenerT>> {
    private final zzdvv<zzcfo<AdapterT, ListenerT>> zzfab;
    private final zzdvv<zzdcs> zzfdl;
    private final zzdvv<zzcxn> zzfdw;
    private final zzdvv<zzcfn<AdT, AdapterT, ListenerT>> zzfzy;

    private zzcka(zzdvv<zzcxn> zzdvvVar, zzdvv<zzdcs> zzdvvVar2, zzdvv<zzcfo<AdapterT, ListenerT>> zzdvvVar3, zzdvv<zzcfn<AdT, AdapterT, ListenerT>> zzdvvVar4) {
        this.zzfdw = zzdvvVar;
        this.zzfdl = zzdvvVar2;
        this.zzfab = zzdvvVar3;
        this.zzfzy = zzdvvVar4;
    }

    public static <AdT, AdapterT, ListenerT extends zzbnj> zzcka<AdT, AdapterT, ListenerT> zzd(zzdvv<zzcxn> zzdvvVar, zzdvv<zzdcs> zzdvvVar2, zzdvv<zzcfo<AdapterT, ListenerT>> zzdvvVar3, zzdvv<zzcfn<AdT, AdapterT, ListenerT>> zzdvvVar4) {
        return new zzcka<>(zzdvvVar, zzdvvVar2, zzdvvVar3, zzdvvVar4);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcjw(this.zzfdw.get(), this.zzfdl.get(), this.zzfab.get(), this.zzfzy.get());
    }
}
