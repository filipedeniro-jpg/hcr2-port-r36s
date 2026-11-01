package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbjc implements zzdvi<Boolean> {
    private final zzdvv<zzcvk> zzfds;

    public zzbjc(zzdvv<zzcvk> zzdvvVar) {
        this.zzfds = zzdvvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        boolean zBooleanValue;
        if (this.zzfds.get().zzamv() != null) {
            zBooleanValue = ((Boolean) zzuo.zzoj().zzd(zzyt.zzckw)).booleanValue();
        } else {
            zBooleanValue = ((Boolean) zzuo.zzoj().zzd(zzyt.zzcrl)).booleanValue();
        }
        return Boolean.valueOf(zBooleanValue);
    }
}
