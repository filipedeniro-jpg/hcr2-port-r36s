package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.VideoController;
import java.util.Collections;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbpp implements zzdvi<Set<zzbqc<VideoController.VideoLifecycleCallbacks>>> {
    private final zzbox zzfhi;

    private zzbpp(zzbox zzboxVar) {
        this.zzfhi = zzboxVar;
    }

    public static zzbpp zzx(zzbox zzboxVar) {
        return new zzbpp(zzboxVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (Set) zzdvo.zza(Collections.emptySet(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
