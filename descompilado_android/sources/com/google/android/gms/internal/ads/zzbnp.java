package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.ads.reward.AdMetadataListener;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbnp extends zzbos<AdMetadataListener> implements zzadg {
    private Bundle zzfgq;

    public zzbnp(Set<zzbqc<AdMetadataListener>> set) {
        super(set);
        this.zzfgq = new Bundle();
    }

    @Override // com.google.android.gms.internal.ads.zzadg
    public final synchronized void zza(String str, Bundle bundle) {
        this.zzfgq.putAll(bundle);
        zza(zzbno.zzfgm);
    }

    public final synchronized Bundle getAdMetadata() {
        return new Bundle(this.zzfgq);
    }
}
