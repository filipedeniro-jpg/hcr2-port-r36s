package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzbzb implements zzbqs {
    private final zzbbc zzehf;

    private zzbzb(zzbbc zzbbcVar) {
        this.zzehf = zzbbcVar;
    }

    static zzbqs zzn(zzbbc zzbbcVar) {
        return new zzbzb(zzbbcVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbqs
    public final void zzagn() {
        this.zzehf.destroy();
    }
}
