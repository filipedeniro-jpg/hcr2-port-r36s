package com.google.android.gms.internal.ads;

import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzctg implements zzcrb<JSONObject> {
    private final Map<String, Object> zzghf;

    public zzctg(Map<String, Object> map) {
        this.zzghf = map;
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final /* synthetic */ void zzr(JSONObject jSONObject) {
        try {
            jSONObject.put("video_decoders", com.google.android.gms.ads.internal.zzp.zzjy().zzi(this.zzghf));
        } catch (JSONException e) {
            String strValueOf = String.valueOf(e.getMessage());
            zzatm.zzdy(strValueOf.length() != 0 ? "Could not encode video decoder properties: ".concat(strValueOf) : new String("Could not encode video decoder properties: "));
        }
    }
}
