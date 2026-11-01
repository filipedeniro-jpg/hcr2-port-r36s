package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.AppEventListener;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbor implements zzdvi<zzbop> {
    private final zzdvv<Set<zzbqc<AppEventListener>>> zzfdr;

    private zzbor(zzdvv<Set<zzbqc<AppEventListener>>> zzdvvVar) {
        this.zzfdr = zzdvvVar;
    }

    public static zzbor zzp(zzdvv<Set<zzbqc<AppEventListener>>> zzdvvVar) {
        return new zzbor(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbop(this.zzfdr.get());
    }
}
