package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzrf implements zzpy {
    private final /* synthetic */ zzrd zzbqu;

    zzrf(zzrd zzrdVar) {
        this.zzbqu = zzrdVar;
    }

    @Override // com.google.android.gms.internal.ads.zzpy
    public final void zzo(boolean z) {
        if (!z) {
            this.zzbqu.disconnect();
        } else {
            this.zzbqu.connect();
        }
    }
}
