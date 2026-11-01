package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzblq implements zzdvi<zzczs<zzcvb, zzaun>> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzawv> zzfaa;
    private final zzdvv<zzcvk> zzfds;

    public zzblq(zzdvv<Context> zzdvvVar, zzdvv<zzawv> zzdvvVar2, zzdvv<zzcvk> zzdvvVar3) {
        this.zzejn = zzdvvVar;
        this.zzfaa = zzdvvVar2;
        this.zzfds = zzdvvVar3;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        final Context context = this.zzejn.get();
        final zzawv zzawvVar = this.zzfaa.get();
        final zzcvk zzcvkVar = this.zzfds.get();
        return (zzczs) zzdvo.zza(new zzczs(context, zzawvVar, zzcvkVar) { // from class: com.google.android.gms.internal.ads.zzbln
            private final Context zzdpm;
            private final zzawv zzfdn;
            private final zzcvk zzfgc;

            {
                this.zzdpm = context;
                this.zzfdn = zzawvVar;
                this.zzfgc = zzcvkVar;
            }

            @Override // com.google.android.gms.internal.ads.zzczs
            public final Object apply(Object obj) {
                Context context2 = this.zzdpm;
                zzawv zzawvVar2 = this.zzfdn;
                zzcvk zzcvkVar2 = this.zzfgc;
                zzcvb zzcvbVar = (zzcvb) obj;
                zzaun zzaunVar = new zzaun(context2);
                zzaunVar.zzej(zzcvbVar.zzdkj);
                zzaunVar.zzek(zzcvbVar.zzgjb.toString());
                zzaunVar.zzr(zzawvVar2.zzbnh);
                zzaunVar.setAdUnitId(zzcvkVar2.zzgjt);
                return zzaunVar;
            }
        }, "Cannot return null from a non-@Nullable @Provides method");
    }
}
