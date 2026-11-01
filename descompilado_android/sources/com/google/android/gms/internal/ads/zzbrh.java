package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbrh implements zzdvi<zzbqc<zzbnm>> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzawv> zzfaa;
    private final zzdvv<zzcvb> zzfcf;
    private final zzdvv<zzcvk> zzfds;
    private final zzbrd zzfia;

    private zzbrh(zzbrd zzbrdVar, zzdvv<Context> zzdvvVar, zzdvv<zzawv> zzdvvVar2, zzdvv<zzcvb> zzdvvVar3, zzdvv<zzcvk> zzdvvVar4) {
        this.zzfia = zzbrdVar;
        this.zzejn = zzdvvVar;
        this.zzfaa = zzdvvVar2;
        this.zzfcf = zzdvvVar3;
        this.zzfds = zzdvvVar4;
    }

    public static zzbrh zza(zzbrd zzbrdVar, zzdvv<Context> zzdvvVar, zzdvv<zzawv> zzdvvVar2, zzdvv<zzcvb> zzdvvVar3, zzdvv<zzcvk> zzdvvVar4) {
        return new zzbrh(zzbrdVar, zzdvvVar, zzdvvVar2, zzdvvVar3, zzdvvVar4);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        final Context context = this.zzejn.get();
        final zzawv zzawvVar = this.zzfaa.get();
        final zzcvb zzcvbVar = this.zzfcf.get();
        final zzcvk zzcvkVar = this.zzfds.get();
        return (zzbqc) zzdvo.zza(new zzbqc(new zzbnm(context, zzawvVar, zzcvbVar, zzcvkVar) { // from class: com.google.android.gms.internal.ads.zzbrg
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
}
