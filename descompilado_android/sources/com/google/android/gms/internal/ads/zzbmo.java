package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbmo extends zzbos<zzbmt> {
    private boolean zzfgn;

    public zzbmo(Set<zzbqc<zzbmt>> set) {
        super(set);
        this.zzfgn = false;
    }

    public final synchronized void onAdImpression() {
        if (!this.zzfgn) {
            zza(zzbmr.zzfgm);
            this.zzfgn = true;
        }
    }
}
