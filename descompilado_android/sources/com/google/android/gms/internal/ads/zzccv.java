package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzccv implements zzdvi<String> {
    private final zzdvv<Context> zzejn;

    private zzccv(zzdvv<Context> zzdvvVar) {
        this.zzejn = zzdvvVar;
    }

    public static zzccv zzab(zzdvv<Context> zzdvvVar) {
        return new zzccv(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (String) zzdvo.zza(this.zzejn.get().getPackageName(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
