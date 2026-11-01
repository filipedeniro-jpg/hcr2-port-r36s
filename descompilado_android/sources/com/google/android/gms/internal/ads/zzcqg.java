package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcqg implements zzdvi<zzcqe> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<String> zzeob;

    private zzcqg(zzdvv<Context> zzdvvVar, zzdvv<String> zzdvvVar2) {
        this.zzejn = zzdvvVar;
        this.zzeob = zzdvvVar2;
    }

    public static zzcqg zzas(zzdvv<Context> zzdvvVar, zzdvv<String> zzdvvVar2) {
        return new zzcqg(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcqe(this.zzejn.get(), this.zzeob.get());
    }
}
