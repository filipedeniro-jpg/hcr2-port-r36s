package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzatd extends zzati {
    private final /* synthetic */ zzatb zzdqs;

    zzatd(zzatb zzatbVar) {
        this.zzdqs = zzatbVar;
    }

    @Override // com.google.android.gms.internal.ads.zzati
    public final void zzst() {
        zzyy zzyyVar = new zzyy(this.zzdqs.zzlk, this.zzdqs.zzblh.zzbnh);
        synchronized (this.zzdqs.lock) {
            try {
                com.google.android.gms.ads.internal.zzp.zzkh();
                zzyz.zza(this.zzdqs.zzdqk, zzyyVar);
            } catch (IllegalArgumentException e) {
                zzatm.zzd("Cannot config CSI reporter.", e);
            }
        }
    }
}
