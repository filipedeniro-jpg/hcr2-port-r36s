package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbnb extends zzbos<zzbmg> {
    public zzbnb(Set<zzbqc<zzbmg>> set) {
        super(set);
    }

    public final void onAdClosed() {
        zza(zzbna.zzfgm);
    }

    public final void onAdLeftApplication() {
        zza(zzbnd.zzfgm);
    }

    public final void onAdOpened() {
        zza(zzbnc.zzfgm);
    }

    public final void onRewardedVideoStarted() {
        zza(zzbnf.zzfgm);
    }

    public final void zzb(final zzape zzapeVar, final String str, final String str2) {
        zza(new zzbou(zzapeVar, str, str2) { // from class: com.google.android.gms.internal.ads.zzbne
            private final String zzcyl;
            private final String zzdbj;
            private final zzape zzfgp;

            {
                this.zzfgp = zzapeVar;
                this.zzcyl = str;
                this.zzdbj = str2;
            }

            @Override // com.google.android.gms.internal.ads.zzbou
            public final void zzp(Object obj) {
                ((zzbmg) obj).zzb(this.zzfgp, this.zzcyl, this.zzdbj);
            }
        });
    }

    public final void onRewardedVideoCompleted() {
        zza(zzbnh.zzfgm);
    }
}
