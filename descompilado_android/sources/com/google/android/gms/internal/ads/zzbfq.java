package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbfq implements zzdvi<zzave> {
    private final zzdvv<Context> zzejn;

    public zzbfq(zzdvv<Context> zzdvvVar) {
        this.zzejn = zzdvvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzave) zzdvo.zza(new zzave(this.zzejn.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
