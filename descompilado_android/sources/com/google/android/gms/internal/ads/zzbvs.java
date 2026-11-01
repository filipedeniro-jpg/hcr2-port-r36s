package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.VideoController;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbvs implements zzdvi<zzbqc<VideoController.VideoLifecycleCallbacks>> {
    private final zzdvv<zzbyb> zzfca;
    private final zzdvv<Executor> zzfdl;
    private final zzbvm zzfni;

    public zzbvs(zzbvm zzbvmVar, zzdvv<zzbyb> zzdvvVar, zzdvv<Executor> zzdvvVar2) {
        this.zzfni = zzbvmVar;
        this.zzfca = zzdvvVar;
        this.zzfdl = zzdvvVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzbqc) zzdvo.zza(new zzbqc(this.zzfca.get(), this.zzfdl.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
