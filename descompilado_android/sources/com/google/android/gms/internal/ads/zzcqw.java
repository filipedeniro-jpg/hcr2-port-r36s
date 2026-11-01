package com.google.android.gms.internal.ads;

import android.os.Bundle;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcqw implements zzcra<zzcrb<Bundle>> {
    private final boolean zzgfs;

    zzcqw(@Nullable zzctp zzctpVar) {
        if (zzctpVar != null) {
            this.zzgfs = zzctpVar.zzgfs;
        } else {
            this.zzgfs = false;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcrb<Bundle>> zzalr() {
        return zzdcf.zzah(this.zzgfs ? zzcqz.zzgft : null);
    }
}
