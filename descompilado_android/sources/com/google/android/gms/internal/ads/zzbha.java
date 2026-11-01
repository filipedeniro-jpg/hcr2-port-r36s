package com.google.android.gms.internal.ads;

import com.facebook.internal.AnalyticsEvents;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbha implements zzdvi<zzpf> {
    private final zzdvv<JSONObject> zzeln;
    private final zzdvv<zzcvb> zzfcf;
    private final zzdvv<zzawv> zzfcg;
    private final zzdvv<String> zzfch;

    private zzbha(zzdvv<zzcvb> zzdvvVar, zzdvv<zzawv> zzdvvVar2, zzdvv<JSONObject> zzdvvVar3, zzdvv<String> zzdvvVar4) {
        this.zzfcf = zzdvvVar;
        this.zzfcg = zzdvvVar2;
        this.zzeln = zzdvvVar3;
        this.zzfch = zzdvvVar4;
    }

    public static zzbha zza(zzdvv<zzcvb> zzdvvVar, zzdvv<zzawv> zzdvvVar2, zzdvv<JSONObject> zzdvvVar3, zzdvv<String> zzdvvVar4) {
        return new zzbha(zzdvvVar, zzdvvVar2, zzdvvVar3, zzdvvVar4);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        this.zzfcf.get();
        zzawv zzawvVar = this.zzfcg.get();
        JSONObject jSONObject = this.zzeln.get();
        String str = this.zzfch.get();
        boolean zEquals = AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE.equals(str);
        com.google.android.gms.ads.internal.zzp.zzjy();
        return (zzpf) zzdvo.zza(new zzpf(zzatv.zzvi(), zzawvVar, str, jSONObject, false, zEquals), "Cannot return null from a non-@Nullable @Provides method");
    }
}
