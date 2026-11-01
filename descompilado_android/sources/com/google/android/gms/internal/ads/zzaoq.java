package com.google.android.gms.internal.ads;

import com.facebook.internal.ServerProtocol;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaoq {
    private final int errorCode;
    private final String type;
    private String url;
    private final String zzdis;
    private final String zzdle;
    private final boolean zzdlf;
    private final List<String> zzdls;
    private final String zzdlt;
    private final String zzdlu;
    private final boolean zzdlv;
    private final String zzdlw;
    private final boolean zzdlx;
    private final JSONObject zzdly;

    public zzaoq(JSONObject jSONObject) {
        this.url = jSONObject.optString("url");
        this.zzdlt = jSONObject.optString("base_uri");
        this.zzdlu = jSONObject.optString("post_parameters");
        String strOptString = jSONObject.optString("drt_include");
        this.zzdlv = strOptString != null && (strOptString.equals("1") || strOptString.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE));
        this.zzdis = jSONObject.optString("request_id");
        this.type = jSONObject.optString("type");
        String strOptString2 = jSONObject.optString("errors");
        this.zzdls = strOptString2 == null ? null : Arrays.asList(strOptString2.split(","));
        this.errorCode = jSONObject.optInt("valid", 0) == 1 ? -2 : 1;
        this.zzdlw = jSONObject.optString("fetched_ad");
        this.zzdlx = jSONObject.optBoolean("render_test_ad_label");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("preprocessor_flags");
        this.zzdly = jSONObjectOptJSONObject == null ? new JSONObject() : jSONObjectOptJSONObject;
        this.zzdle = jSONObject.optString("analytics_query_ad_event_id");
        this.zzdlf = jSONObject.optBoolean("is_analytics_logging_enabled");
    }

    public final int getErrorCode() {
        return this.errorCode;
    }

    public final List<String> zzsz() {
        return this.zzdls;
    }

    public final String zzta() {
        return this.zzdlt;
    }

    public final String zztb() {
        return this.zzdlu;
    }

    public final String getUrl() {
        return this.url;
    }

    public final boolean zztc() {
        return this.zzdlv;
    }

    public final JSONObject zztd() {
        return this.zzdly;
    }
}
