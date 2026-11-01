package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbiv implements zzdvi<zzcfk<zzbhy>> {
    private final zzdvv<zzcft> zzewn;
    private final zzdvv<zzcgn> zzewo;
    private final zzdvv<zzcvk> zzfds;

    public zzbiv(zzdvv<zzcvk> zzdvvVar, zzdvv<zzcgn> zzdvvVar2, zzdvv<zzcft> zzdvvVar3) {
        this.zzfds = zzdvvVar;
        this.zzewo = zzdvvVar2;
        this.zzewn = zzdvvVar3;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0031  */
    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        zzcvk zzcvkVar = this.zzfds.get();
        zzcgn zzcgnVar = this.zzewo.get();
        zzcft zzcftVar = this.zzewn.get();
        if (zzcvkVar.zzamv() != null) {
            if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzckw)).booleanValue()) {
                zzcgnVar = zzcftVar;
            }
        } else {
            zzcgnVar = zzcftVar;
        }
        return (zzcfk) zzdvo.zza(zzcgnVar, "Cannot return null from a non-@Nullable @Provides method");
    }
}
