package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbhi implements zzdvi<JSONObject> {
    private final zzdvv<zzcvb> zzfcf;

    private zzbhi(zzdvv<zzcvb> zzdvvVar) {
        this.zzfcf = zzdvvVar;
    }

    public static zzbhi zzc(zzdvv<zzcvb> zzdvvVar) {
        return new zzbhi(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    @Nullable
    public final /* synthetic */ Object get() {
        return zzbhf.zza(this.zzfcf.get());
    }
}
