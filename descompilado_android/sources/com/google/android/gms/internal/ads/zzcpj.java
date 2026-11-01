package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcpj implements zzcrb<Bundle> {
    private final zzcuz zzfao;

    public zzcpj(zzcuz zzcuzVar) {
        this.zzfao = zzcuzVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final /* synthetic */ void zzr(Bundle bundle) {
        Bundle bundle2 = bundle;
        zzcuz zzcuzVar = this.zzfao;
        if (zzcuzVar != null) {
            bundle2.putBoolean("render_in_browser", zzcuzVar.zzamt());
            bundle2.putBoolean("disable_ml", this.zzfao.zzamu());
        }
    }
}
