package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.AppEventListener;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbpm implements zzdvi<Set<zzbqc<AppEventListener>>> {
    private final zzbox zzfhi;

    private zzbpm(zzbox zzboxVar) {
        this.zzfhi = zzboxVar;
    }

    public static zzbpm zzu(zzbox zzboxVar) {
        return new zzbpm(zzboxVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (Set) zzdvo.zza(this.zzfhi.zzage(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
