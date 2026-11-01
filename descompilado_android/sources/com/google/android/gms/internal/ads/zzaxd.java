package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzaxd implements zzdcg<Object> {
    private final /* synthetic */ String zzdwe;

    zzaxd(String str) {
        this.zzdwe = str;
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final void onSuccess(@Nullable Object obj) {
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final void zzb(Throwable th) {
        com.google.android.gms.ads.internal.zzp.zzkc().zzb(th, this.zzdwe);
    }
}
