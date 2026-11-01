package com.google.android.gms.internal.ads;

import com.facebook.internal.NativeProtocol;
import com.ironsource.mediationsdk.utils.ServerResponseWrapper;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzcdx implements zzcwu {
    static final zzcwu zzfti = new zzcdx();

    private zzcdx() {
    }

    @Override // com.google.android.gms.internal.ads.zzcwu
    public final Object apply(Object obj) throws zzcdu {
        JSONObject jSONObject = (JSONObject) obj;
        if (jSONObject.optInt(NativeProtocol.BRIDGE_ARG_ERROR_CODE) == 6) {
            return zzaic.zzdao.zzd(jSONObject.getJSONObject(ServerResponseWrapper.RESPONSE_FIELD));
        }
        throw new zzcdu(jSONObject.getString("error_reason"), jSONObject.optInt(NativeProtocol.BRIDGE_ARG_ERROR_CODE, 0));
    }
}
