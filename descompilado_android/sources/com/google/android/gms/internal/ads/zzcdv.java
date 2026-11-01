package com.google.android.gms.internal.ads;

import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzcdv implements zzcwu {
    static final zzcwu zzfti = new zzcdv();

    private zzcdv() {
    }

    @Override // com.google.android.gms.internal.ads.zzcwu
    public final Object apply(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        zzatm.zzdy("Ad request signals:");
        zzatm.zzdy(jSONObject.toString(2));
        return jSONObject;
    }
}
