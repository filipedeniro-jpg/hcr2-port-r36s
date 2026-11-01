package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import com.facebook.internal.ServerProtocol;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzaiz {
    private final String zzdbk;
    private final String zzdbl;
    public final List<String> zzdbm;
    private final String zzdbn;
    private final String zzdbo;
    private final List<String> zzdbp;
    private final List<String> zzdbq;
    private final List<String> zzdbr;
    private final List<String> zzdbs;
    private final List<String> zzdbt;
    public final String zzdbu;
    private final List<String> zzdbv;
    private final List<String> zzdbw;
    private final List<String> zzdbx;
    private final String zzdby;
    private final String zzdbz;

    @Nullable
    private final String zzdca;

    @Nullable
    private final String zzdcb;
    private final String zzdcc;

    @Nullable
    private final List<String> zzdcd;
    private final String zzdce;

    @Nullable
    public final String zzdcf;
    private final long zzdcg;

    public zzaiz(JSONObject jSONObject) throws JSONException {
        List<String> listZza;
        this.zzdbl = jSONObject.optString("id");
        JSONArray jSONArray = jSONObject.getJSONArray("adapters");
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        this.zzdbm = Collections.unmodifiableList(arrayList);
        this.zzdbn = jSONObject.optString("allocation_id", null);
        com.google.android.gms.ads.internal.zzp.zzkq();
        this.zzdbp = zzajb.zza(jSONObject, "clickurl");
        com.google.android.gms.ads.internal.zzp.zzkq();
        this.zzdbq = zzajb.zza(jSONObject, "imp_urls");
        com.google.android.gms.ads.internal.zzp.zzkq();
        this.zzdbr = zzajb.zza(jSONObject, "downloaded_imp_urls");
        com.google.android.gms.ads.internal.zzp.zzkq();
        this.zzdbt = zzajb.zza(jSONObject, "fill_urls");
        com.google.android.gms.ads.internal.zzp.zzkq();
        this.zzdbv = zzajb.zza(jSONObject, "video_start_urls");
        com.google.android.gms.ads.internal.zzp.zzkq();
        this.zzdbx = zzajb.zza(jSONObject, "video_complete_urls");
        com.google.android.gms.ads.internal.zzp.zzkq();
        this.zzdbw = zzajb.zza(jSONObject, "video_reward_urls");
        this.zzdby = jSONObject.optString("transaction_id");
        this.zzdbz = jSONObject.optString("valid_from_timestamp");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("ad");
        if (jSONObjectOptJSONObject != null) {
            com.google.android.gms.ads.internal.zzp.zzkq();
            listZza = zzajb.zza(jSONObjectOptJSONObject, "manual_impression_urls");
        } else {
            listZza = null;
        }
        this.zzdbs = listZza;
        this.zzdbk = jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.toString() : null;
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("data");
        this.zzdbu = jSONObjectOptJSONObject2 != null ? jSONObjectOptJSONObject2.toString() : null;
        this.zzdbo = jSONObjectOptJSONObject2 != null ? jSONObjectOptJSONObject2.optString("class_name") : null;
        this.zzdca = jSONObject.optString("html_template", null);
        this.zzdcb = jSONObject.optString("ad_base_url", null);
        JSONObject jSONObjectOptJSONObject3 = jSONObject.optJSONObject("assets");
        this.zzdcc = jSONObjectOptJSONObject3 != null ? jSONObjectOptJSONObject3.toString() : null;
        com.google.android.gms.ads.internal.zzp.zzkq();
        this.zzdcd = zzajb.zza(jSONObject, "template_ids");
        JSONObject jSONObjectOptJSONObject4 = jSONObject.optJSONObject("ad_loader_options");
        this.zzdce = jSONObjectOptJSONObject4 != null ? jSONObjectOptJSONObject4.toString() : null;
        this.zzdcf = jSONObject.optString(ServerProtocol.DIALOG_PARAM_RESPONSE_TYPE, null);
        this.zzdcg = jSONObject.optLong("ad_network_timeout_millis", -1L);
    }
}
