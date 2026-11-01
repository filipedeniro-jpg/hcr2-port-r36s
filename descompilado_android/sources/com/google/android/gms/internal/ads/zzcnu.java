package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcnu implements zzcra<Object> {
    private static final Object lock = new Object();
    private final String zzcdj;
    private final String zzdil;
    private final zzcvk zzffn;
    private final zzbkx zzgdy;
    private final zzcwa zzgdz;

    public zzcnu(String str, String str2, zzbkx zzbkxVar, zzcwa zzcwaVar, zzcvk zzcvkVar) {
        this.zzdil = str;
        this.zzcdj = str2;
        this.zzgdy = zzbkxVar;
        this.zzgdz = zzcwaVar;
        this.zzffn = zzcvkVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<Object> zzalr() {
        final Bundle bundle = new Bundle();
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcrn)).booleanValue()) {
            this.zzgdy.zzf(this.zzffn.zzgjs);
            bundle.putAll(this.zzgdz.zzanc());
        }
        return zzdcf.zzah(new zzcrb(this, bundle) { // from class: com.google.android.gms.internal.ads.zzcnx
            private final Bundle zzdpi;
            private final zzcnu zzgec;

            {
                this.zzgec = this;
                this.zzdpi = bundle;
            }

            @Override // com.google.android.gms.internal.ads.zzcrb
            public final void zzr(Object obj) {
                this.zzgec.zzb(this.zzdpi, (Bundle) obj);
            }
        });
    }

    final /* synthetic */ void zzb(Bundle bundle, Bundle bundle2) {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcrn)).booleanValue()) {
            bundle2.putBundle("quality_signals", bundle);
        } else {
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcrm)).booleanValue()) {
                synchronized (lock) {
                    this.zzgdy.zzf(this.zzffn.zzgjs);
                    bundle2.putBundle("quality_signals", this.zzgdz.zzanc());
                }
            } else {
                this.zzgdy.zzf(this.zzffn.zzgjs);
                bundle2.putBundle("quality_signals", this.zzgdz.zzanc());
            }
        }
        bundle2.putString("seq_num", this.zzdil);
        bundle2.putString("session_id", this.zzcdj);
    }
}
