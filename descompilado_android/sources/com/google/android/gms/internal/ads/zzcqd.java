package com.google.android.gms.internal.ads;

import android.os.Bundle;
import androidx.annotation.Nullable;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcqd implements zzcra<zzcqa> {
    private final Bundle zzfgf;
    private final zzdcs zzfnq;

    public zzcqd(zzdcs zzdcsVar, @Nullable Bundle bundle) {
        this.zzfnq = zzdcsVar;
        this.zzfgf = bundle;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcqa> zzalr() {
        return this.zzfnq.submit(new Callable(this) { // from class: com.google.android.gms.internal.ads.zzcqc
            private final zzcqd zzgfb;

            {
                this.zzgfb = this;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zzgfb.zzama();
            }
        });
    }

    final /* synthetic */ zzcqa zzama() throws Exception {
        return new zzcqa(this.zzfgf);
    }
}
