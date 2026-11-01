package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbji implements zzdvi<Runnable> {
    private final zzbjf zzfee;

    public zzbji(zzbjf zzbjfVar) {
        this.zzfee = zzbjfVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (Runnable) zzdvo.zza(this.zzfee.zzafe(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
