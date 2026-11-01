package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbpd implements zzdvi<Set<zzbqc<zzbml>>> {
    private final zzbox zzfhi;

    private zzbpd(zzbox zzboxVar) {
        this.zzfhi = zzboxVar;
    }

    public static zzbpd zzl(zzbox zzboxVar) {
        return new zzbpd(zzboxVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (Set) zzdvo.zza(this.zzfhi.zzagb(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
