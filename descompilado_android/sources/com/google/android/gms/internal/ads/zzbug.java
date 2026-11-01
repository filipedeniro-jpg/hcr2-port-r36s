package com.google.android.gms.internal.ads;

import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbug implements zzdvi<zzbue> {
    private final zzdvv<JSONObject> zzelk;
    private final zzdvv<zzcvb> zzfcf;

    public zzbug(zzdvv<zzcvb> zzdvvVar, zzdvv<JSONObject> zzdvvVar2) {
        this.zzfcf = zzdvvVar;
        this.zzelk = zzdvvVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbue(this.zzfcf.get(), this.zzelk.get());
    }
}
