package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcqe implements zzcra<zzcrb<Bundle>> {
    private final String zzfgg;
    private final Context zzlk;

    zzcqe(Context context, @Nullable String str) {
        this.zzlk = context;
        this.zzfgg = str;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcrb<Bundle>> zzalr() {
        return zzdcf.zzah(this.zzfgg == null ? null : new zzcrb(this) { // from class: com.google.android.gms.internal.ads.zzcqh
            private final zzcqe zzgfd;

            {
                this.zzgfd = this;
            }

            @Override // com.google.android.gms.internal.ads.zzcrb
            public final void zzr(Object obj) {
                this.zzgfd.zzo((Bundle) obj);
            }
        });
    }

    final /* synthetic */ void zzo(Bundle bundle) {
        bundle.putString("rewarded_sku_package", this.zzlk.getPackageName());
    }
}
