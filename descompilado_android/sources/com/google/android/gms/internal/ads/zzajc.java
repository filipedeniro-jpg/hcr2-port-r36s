package com.google.android.gms.internal.ads;

import com.ironsource.sdk.precache.DownloadManager;
import com.my.target.ai;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzajc {
    private final long zzdcg;
    public final List<zzaiz> zzdch;
    private final List<String> zzdci;
    private final List<String> zzdcj;
    private final List<String> zzdck;
    private final List<String> zzdcl;
    private final List<String> zzdcm;
    private final boolean zzdcn;
    private final String zzdco;
    private final long zzdcp;
    private final String zzdcq;
    private final int zzdcr;
    private final int zzdcs;
    private final long zzdct;
    private final boolean zzdcu;
    private final boolean zzdcv;
    private final boolean zzdcw;
    private final boolean zzdcx;
    private int zzdcy;
    private int zzdcz;
    private boolean zzdda;

    public zzajc(JSONObject jSONObject) throws JSONException {
        if (zzatm.isLoggable(2)) {
            String strValueOf = String.valueOf(jSONObject.toString(2));
            zzatm.zzdy(strValueOf.length() != 0 ? "Mediation Response JSON: ".concat(strValueOf) : new String("Mediation Response JSON: "));
        }
        JSONArray jSONArray = jSONObject.getJSONArray("ad_networks");
        ArrayList arrayList = new ArrayList(jSONArray.length());
        int i = -1;
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            try {
                zzaiz zzaizVar = new zzaiz(jSONArray.getJSONObject(i2));
                boolean z = true;
                if (ai.a.cW.equalsIgnoreCase(zzaizVar.zzdcf)) {
                    this.zzdda = true;
                }
                arrayList.add(zzaizVar);
                if (i < 0) {
                    Iterator<String> it = zzaizVar.zzdbm.iterator();
                    do {
                        if (!it.hasNext()) {
                            z = false;
                            break;
                        }
                    } while (!it.next().equals("com.google.ads.mediation.admob.AdMobAdapter"));
                    if (z) {
                        i = i2;
                    }
                }
            } catch (JSONException unused) {
            }
        }
        this.zzdcy = i;
        this.zzdcz = jSONArray.length();
        this.zzdch = Collections.unmodifiableList(arrayList);
        this.zzdco = jSONObject.optString("qdata");
        this.zzdcs = jSONObject.optInt("fs_model_type", -1);
        this.zzdct = jSONObject.optLong("timeout_ms", -1L);
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(DownloadManager.SETTINGS);
        if (jSONObjectOptJSONObject == null) {
            this.zzdcg = -1L;
            this.zzdci = null;
            this.zzdcj = null;
            this.zzdck = null;
            this.zzdcl = null;
            this.zzdcm = null;
            this.zzdcp = -1L;
            this.zzdcq = null;
            this.zzdcr = 0;
            this.zzdcu = false;
            this.zzdcn = false;
            this.zzdcv = false;
            this.zzdcw = false;
            this.zzdcx = false;
            return;
        }
        this.zzdcg = jSONObjectOptJSONObject.optLong("ad_network_timeout_millis", -1L);
        com.google.android.gms.ads.internal.zzp.zzkq();
        this.zzdci = zzajb.zza(jSONObjectOptJSONObject, "click_urls");
        com.google.android.gms.ads.internal.zzp.zzkq();
        this.zzdcj = zzajb.zza(jSONObjectOptJSONObject, "imp_urls");
        com.google.android.gms.ads.internal.zzp.zzkq();
        this.zzdck = zzajb.zza(jSONObjectOptJSONObject, "downloaded_imp_urls");
        com.google.android.gms.ads.internal.zzp.zzkq();
        this.zzdcl = zzajb.zza(jSONObjectOptJSONObject, "nofill_urls");
        com.google.android.gms.ads.internal.zzp.zzkq();
        this.zzdcm = zzajb.zza(jSONObjectOptJSONObject, "remote_ping_urls");
        this.zzdcn = jSONObjectOptJSONObject.optBoolean("render_in_browser", false);
        long jOptLong = jSONObjectOptJSONObject.optLong("refresh", -1L);
        this.zzdcp = jOptLong > 0 ? 1000 * jOptLong : -1L;
        zzaqd zzaqdVarZza = zzaqd.zza(jSONObjectOptJSONObject.optJSONArray("rewards"));
        if (zzaqdVarZza == null) {
            this.zzdcq = null;
            this.zzdcr = 0;
        } else {
            this.zzdcq = zzaqdVarZza.type;
            this.zzdcr = zzaqdVarZza.zzdng;
        }
        this.zzdcu = jSONObjectOptJSONObject.optBoolean("use_displayed_impression", false);
        this.zzdcv = jSONObjectOptJSONObject.optBoolean("allow_pub_rendered_attribution", false);
        this.zzdcw = jSONObjectOptJSONObject.optBoolean("allow_pub_owned_ad_view", false);
        this.zzdcx = jSONObjectOptJSONObject.optBoolean("allow_custom_click_gesture", false);
    }
}
