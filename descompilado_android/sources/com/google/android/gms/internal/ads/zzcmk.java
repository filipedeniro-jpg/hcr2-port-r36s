package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcmk implements zzdcg<zzcmv> {
    private final /* synthetic */ zzasn zzgcz;

    zzcmk(zzcmh zzcmhVar, zzasn zzasnVar) {
        this.zzgcz = zzasnVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final void zzb(Throwable th) {
        try {
            this.zzgcz.onError("Internal error.");
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final /* synthetic */ void onSuccess(@Nullable zzcmv zzcmvVar) {
        zzcmv zzcmvVar2 = zzcmvVar;
        try {
            this.zzgcz.zzm(zzcmvVar2.zzgdg, zzcmvVar2.zzgdh);
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }
}
