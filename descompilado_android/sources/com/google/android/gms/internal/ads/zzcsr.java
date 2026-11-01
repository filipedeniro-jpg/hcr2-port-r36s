package com.google.android.gms.internal.ads;

import android.location.Location;
import com.ironsource.sdk.constants.LocationConst;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcsr implements zzcrb<JSONObject> {
    private final Location zzng;

    public zzcsr(Location location) {
        this.zzng = location;
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final /* synthetic */ void zzr(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject;
        try {
            if (this.zzng != null) {
                JSONObject jSONObject3 = new JSONObject();
                Float fValueOf = Float.valueOf(this.zzng.getAccuracy() * 1000.0f);
                Long lValueOf = Long.valueOf(this.zzng.getTime() * 1000);
                Long lValueOf2 = Long.valueOf((long) (this.zzng.getLatitude() * 1.0E7d));
                Long lValueOf3 = Long.valueOf((long) (this.zzng.getLongitude() * 1.0E7d));
                jSONObject3.put("radius", fValueOf);
                jSONObject3.put("lat", lValueOf2);
                jSONObject3.put("long", lValueOf3);
                jSONObject3.put(LocationConst.TIME, lValueOf);
                jSONObject2.put("uule", jSONObject3);
            }
        } catch (JSONException e) {
            zzatm.zza("Failed adding location to the request JSON.", e);
        }
    }
}
