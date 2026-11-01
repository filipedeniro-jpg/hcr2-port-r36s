package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbac extends zzati {
    final zzayt zzdxm;
    private final String zzdxy;
    private final String[] zzdxz;
    final zzbah zzecq;

    zzbac(zzayt zzaytVar, zzbah zzbahVar, String str, String[] strArr) {
        this.zzdxm = zzaytVar;
        this.zzecq = zzbahVar;
        this.zzdxy = str;
        this.zzdxz = strArr;
        com.google.android.gms.ads.internal.zzp.zzku().zza(this);
    }

    @Override // com.google.android.gms.internal.ads.zzati
    public final void zzst() {
        try {
            this.zzecq.zze(this.zzdxy, this.zzdxz);
        } finally {
            zzatv.zzdsk.post(new zzbaf(this));
        }
    }
}
