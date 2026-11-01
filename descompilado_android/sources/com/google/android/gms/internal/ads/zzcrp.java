package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcrp implements zzcrb<JSONObject> {
    private final AdvertisingIdClient.Info zzggf;
    private final String zzggg;
    private final Context zzlk;

    public zzcrp(AdvertisingIdClient.Info info, Context context, String str) {
        this.zzlk = context;
        this.zzggf = info;
        this.zzggg = str;
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final /* synthetic */ void zzr(JSONObject jSONObject) {
        try {
            JSONObject jSONObjectZzb = zzavm.zzb(jSONObject, "pii");
            String id = null;
            boolean zIsLimitAdTrackingEnabled = false;
            if (this.zzggf != null) {
                id = this.zzggf.getId();
                zIsLimitAdTrackingEnabled = this.zzggf.isLimitAdTrackingEnabled();
            }
            if (!TextUtils.isEmpty(id)) {
                jSONObjectZzb.put("rdid", id);
                jSONObjectZzb.put("is_lat", zIsLimitAdTrackingEnabled);
                jSONObjectZzb.put("idtype", "adid");
            } else {
                jSONObjectZzb.put("pdid", this.zzggg);
                jSONObjectZzb.put("pdidtype", "ssaid");
            }
        } catch (JSONException e) {
            zzatm.zza("Failed putting Ad ID.", e);
        }
    }
}
