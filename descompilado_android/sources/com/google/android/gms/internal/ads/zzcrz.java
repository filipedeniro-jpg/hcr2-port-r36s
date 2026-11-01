package com.google.android.gms.internal.ads;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcrz implements zzcrb<JSONObject> {
    private final JSONObject zzggm;

    public zzcrz(JSONObject jSONObject) {
        this.zzggm = jSONObject;
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final /* synthetic */ void zzr(JSONObject jSONObject) {
        try {
            JSONObject jSONObjectZzb = zzavm.zzb(jSONObject, "content_info");
            JSONObject jSONObject2 = this.zzggm;
            Iterator<String> itKeys = jSONObject2.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                jSONObjectZzb.put(next, jSONObject2.get(next));
            }
        } catch (JSONException unused) {
            zzatm.zzdy("Failed putting app indexing json.");
        }
    }
}
