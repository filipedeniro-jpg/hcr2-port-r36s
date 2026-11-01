package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbij implements zzdvi<zzbqc<zzbnm>> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzawv> zzfaa;
    private final zzdvv<zzcvb> zzfcf;
    private final zzbib zzfdq;
    private final zzdvv<zzcvk> zzfds;

    public zzbij(zzbib zzbibVar, zzdvv<Context> zzdvvVar, zzdvv<zzawv> zzdvvVar2, zzdvv<zzcvb> zzdvvVar3, zzdvv<zzcvk> zzdvvVar4) {
        this.zzfdq = zzbibVar;
        this.zzejn = zzdvvVar;
        this.zzfaa = zzdvvVar2;
        this.zzfcf = zzdvvVar3;
        this.zzfds = zzdvvVar4;
    }

    public static zzbqc<zzbnm> zza(zzbib zzbibVar, final Context context, final zzawv zzawvVar, final zzcvb zzcvbVar, final zzcvk zzcvkVar) {
        return (zzbqc) zzdvo.zza(new zzbqc(new zzbnm(context, zzawvVar, zzcvbVar, zzcvkVar) { // from class: com.google.android.gms.internal.ads.zzbie
            private final Context zzdpm;
            private final zzawv zzfdn;
            private final zzcvb zzfdo;
            private final zzcvk zzfdp;

            {
                this.zzdpm = context;
                this.zzfdn = zzawvVar;
                this.zzfdo = zzcvbVar;
                this.zzfdp = zzcvkVar;
            }

            @Override // com.google.android.gms.internal.ads.zzbnm
            public final void onAdLoaded() {
                com.google.android.gms.ads.internal.zzp.zzki().zzb(this.zzdpm, this.zzfdn.zzbnh, this.zzfdo.zzgjb.toString(), this.zzfdp.zzgjt);
            }
        }, zzawx.zzdwb), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zza(this.zzfdq, this.zzejn.get(), this.zzfaa.get(), this.zzfcf.get(), this.zzfds.get());
    }
}
