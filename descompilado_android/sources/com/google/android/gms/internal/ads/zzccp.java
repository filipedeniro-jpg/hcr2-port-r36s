package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzccp implements zzdvi<zzdcp<String>> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzdf> zzftv;
    private final zzdvv<zzdcs> zzftw;

    private zzccp(zzdvv<zzdf> zzdvvVar, zzdvv<Context> zzdvvVar2, zzdvv<zzdcs> zzdvvVar3) {
        this.zzftv = zzdvvVar;
        this.zzejn = zzdvvVar2;
        this.zzftw = zzdvvVar3;
    }

    public static zzccp zzn(zzdvv<zzdf> zzdvvVar, zzdvv<Context> zzdvvVar2, zzdvv<zzdcs> zzdvvVar3) {
        return new zzccp(zzdvvVar, zzdvvVar2, zzdvvVar3);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        final zzdf zzdfVar = this.zzftv.get();
        final Context context = this.zzejn.get();
        return (zzdcp) zzdvo.zza(this.zzftw.get().submit(new Callable(zzdfVar, context) { // from class: com.google.android.gms.internal.ads.zzccl
            private final Context zzcev;
            private final zzdf zzfth;

            {
                this.zzfth = zzdfVar;
                this.zzcev = context;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                zzdf zzdfVar2 = this.zzfth;
                return zzdfVar2.zzcc().zza(this.zzcev);
            }
        }), "Cannot return null from a non-@Nullable @Provides method");
    }
}
