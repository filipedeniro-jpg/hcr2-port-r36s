package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.VideoController;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbqx implements zzdvi<zzbqr> {
    private final zzdvv<Set<zzbqc<VideoController.VideoLifecycleCallbacks>>> zzfdr;

    private zzbqx(zzdvv<Set<zzbqc<VideoController.VideoLifecycleCallbacks>>> zzdvvVar) {
        this.zzfdr = zzdvvVar;
    }

    public static zzbqx zzt(zzdvv<Set<zzbqc<VideoController.VideoLifecycleCallbacks>>> zzdvvVar) {
        return new zzbqx(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbqr(this.zzfdr.get());
    }
}
