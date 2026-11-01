package com.google.android.gms.internal.ads;

import java.util.Set;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcbk implements zzdvi<Set<zzbqc<zzboi>>> {
    public static Set<zzbqc<zzboi>> zzb(zzcbd zzcbdVar, zzcbn zzcbnVar, Executor executor) {
        return (Set) zzdvo.zza(zzcbd.zzi(zzcbnVar, executor), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        throw new NoSuchMethodError();
    }
}
