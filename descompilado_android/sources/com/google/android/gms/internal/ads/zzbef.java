package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbef implements zzdvi<zzaoy> {
    private final zzdvv<Context> zzejn;

    public zzbef(zzdvv<Context> zzdvvVar) {
        this.zzejn = zzdvvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        Context context = this.zzejn.get();
        return (zzaoy) zzdvo.zza(new zzaow(context, new zzapd(context).zzsy()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
