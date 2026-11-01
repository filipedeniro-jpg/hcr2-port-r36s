package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzctd implements zzdvi<String> {
    private final zzcsz zzghc;

    public zzctd(zzcsz zzcszVar) {
        this.zzghc = zzcszVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (String) zzdvo.zza(this.zzghc.zzamn(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
