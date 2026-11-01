package com.google.android.gms.internal.ads;

import java.util.concurrent.ThreadFactory;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcwv implements zzdvi<ThreadFactory> {
    private static final zzcwv zzgkw = new zzcwv();

    public static zzcwv zzank() {
        return zzgkw;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (ThreadFactory) zzdvo.zza(new zzcwm(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
