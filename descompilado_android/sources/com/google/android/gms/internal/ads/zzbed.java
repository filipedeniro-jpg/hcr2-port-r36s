package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbed implements zzdvi<zzawv> {
    private final zzbdr zzeji;

    public zzbed(zzbdr zzbdrVar) {
        this.zzeji = zzbdrVar;
    }

    public static zzawv zzb(zzbdr zzbdrVar) {
        return (zzawv) zzdvo.zza(zzbdrVar.zzaaz(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zzb(this.zzeji);
    }
}
