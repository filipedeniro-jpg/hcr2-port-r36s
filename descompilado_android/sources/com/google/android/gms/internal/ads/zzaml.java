package com.google.android.gms.internal.ads;

import com.amazon.device.ads.AdWebViewClient;
import com.my.target.ab;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaml {
    private final boolean zzdfv;
    private final boolean zzdfw;
    private final boolean zzdfx;
    private final boolean zzdfy;
    private final boolean zzdfz;

    private zzaml(zzamn zzamnVar) {
        this.zzdfv = zzamnVar.zzdfv;
        this.zzdfw = zzamnVar.zzdfw;
        this.zzdfx = zzamnVar.zzdfx;
        this.zzdfy = zzamnVar.zzdfy;
        this.zzdfz = zzamnVar.zzdfz;
    }

    public final JSONObject toJson() {
        try {
            return new JSONObject().put(AdWebViewClient.SMS, this.zzdfv).put(AdWebViewClient.TELEPHONE, this.zzdfw).put("calendar", this.zzdfx).put(ab.bF, this.zzdfy).put("inlineVideo", this.zzdfz);
        } catch (JSONException e) {
            zzatm.zzc("Error occured while obtaining the MRAID capabilities.", e);
            return null;
        }
    }
}
