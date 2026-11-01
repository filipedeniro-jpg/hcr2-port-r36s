package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbvl implements zzdvi<zzbuf> {
    private final zzdvv<zzbvk> zzeup;
    private final zzbvm zzfni;

    public zzbvl(zzbvm zzbvmVar, zzdvv<zzbvk> zzdvvVar) {
        this.zzfni = zzbvmVar;
        this.zzeup = zzdvvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzbuf) zzdvo.zza(this.zzeup.get(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
