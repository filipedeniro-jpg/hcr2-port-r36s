package com.google.android.gms.internal.ads;

import com.facebook.share.internal.ShareConstants;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.ironsource.mediationsdk.utils.ServerResponseWrapper;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcef implements zzaia<zzceg> {
    zzcef() {
    }

    @Override // com.google.android.gms.internal.ads.zzaia
    public final /* synthetic */ JSONObject zzj(zzceg zzcegVar) throws JSONException {
        zzceg zzcegVar2 = zzcegVar;
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        jSONObject2.put("base_url", zzcegVar2.zzfva.zzta());
        jSONObject2.put("signals", zzcegVar2.zzfuz);
        jSONObject3.put(TtmlNode.TAG_BODY, zzcegVar2.zzfuy.zzdkc);
        jSONObject3.put("headers", com.google.android.gms.ads.internal.zzp.zzjy().zzi(zzcegVar2.zzfuy.zzab));
        jSONObject3.put("response_code", zzcegVar2.zzfuy.zzfvg);
        jSONObject3.put("latency", zzcegVar2.zzfuy.zzfvh);
        jSONObject.put(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, jSONObject2);
        jSONObject.put(ServerResponseWrapper.RESPONSE_FIELD, jSONObject3);
        jSONObject.put("flags", zzcegVar2.zzfva.zztd());
        return jSONObject;
    }
}
