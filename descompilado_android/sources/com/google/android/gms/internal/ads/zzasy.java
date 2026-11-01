package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.ironsource.sdk.constants.Constants;
import com.my.target.aa;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzasy {
    private String zzdjf;
    private final long zzdpx;
    private final List<String> zzdpy = new ArrayList();
    private final List<String> zzdpz = new ArrayList();
    private final Map<String, zzajc> zzdqa = new HashMap();
    private String zzdqb;
    private JSONObject zzdqc;
    private boolean zzdqd;

    public zzasy(String str, long j) {
        JSONObject jSONObjectOptJSONObject;
        this.zzdqd = false;
        this.zzdjf = str;
        this.zzdpx = j;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            this.zzdqc = new JSONObject(str);
            if (this.zzdqc.optInt("status", -1) != 1) {
                this.zzdqd = false;
                zzatm.zzeu("App settings could not be fetched successfully.");
                return;
            }
            this.zzdqd = true;
            this.zzdqb = this.zzdqc.optString("app_id");
            JSONArray jSONArrayOptJSONArray = this.zzdqc.optJSONArray("ad_unit_id_settings");
            if (jSONArrayOptJSONArray != null) {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArrayOptJSONArray.getJSONObject(i);
                    String strOptString = jSONObject.optString("format");
                    String strOptString2 = jSONObject.optString("ad_unit_id");
                    if (!TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2)) {
                        if (aa.e.bo.equalsIgnoreCase(strOptString)) {
                            this.zzdpz.add(strOptString2);
                        } else if (Constants.CONVERT_REWARDED.equalsIgnoreCase(strOptString) && (jSONObjectOptJSONObject = jSONObject.optJSONObject("mediation_config")) != null) {
                            this.zzdqa.put(strOptString2, new zzajc(jSONObjectOptJSONObject));
                        }
                    }
                }
            }
            JSONArray jSONArrayOptJSONArray2 = this.zzdqc.optJSONArray("persistable_banner_ad_unit_ids");
            if (jSONArrayOptJSONArray2 != null) {
                for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                    this.zzdpy.add(jSONArrayOptJSONArray2.optString(i2));
                }
            }
        } catch (JSONException e) {
            zzatm.zzd("Exception occurred while processing app setting json", e);
            com.google.android.gms.ads.internal.zzp.zzkc().zza(e, "AppSettings.parseAppSettingsJson");
        }
    }

    public final long zztu() {
        return this.zzdpx;
    }

    public final boolean zztv() {
        return this.zzdqd;
    }

    public final String zztw() {
        return this.zzdjf;
    }

    public final String zztx() {
        return this.zzdqb;
    }

    public final Map<String, zzajc> zzty() {
        return this.zzdqa;
    }

    public final JSONObject zztz() {
        return this.zzdqc;
    }
}
