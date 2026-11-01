package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbjh implements zzdvi<zzack> {
    private final zzbjf zzfee;

    public zzbjh(zzbjf zzbjfVar) {
        this.zzfee = zzbjfVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzack) zzdvo.zza(this.zzfee.zzafd(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
