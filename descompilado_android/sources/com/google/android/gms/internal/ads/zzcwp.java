package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcwp implements zzdvi<Executor> {
    private static final zzcwp zzgkr = new zzcwp();

    public static zzcwp zzanf() {
        return zzgkr;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (Executor) zzdvo.zza(zzawx.zzdwa, "Cannot return null from a non-@Nullable @Provides method");
    }
}
