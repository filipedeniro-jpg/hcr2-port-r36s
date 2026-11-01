package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.HashSet;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbtj implements zzdvi<zzbqb> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzcvb> zzfem;

    public zzbtj(zzdvv<Context> zzdvvVar, zzdvv<zzcvb> zzdvvVar2) {
        this.zzejn = zzdvvVar;
        this.zzfem = zzdvvVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzbqb) zzdvo.zza(new zzbqb(this.zzejn.get(), new HashSet(), this.zzfem.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
