package com.chartboost.sdk.impl;

import com.appsflyer.share.Constants;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.CBError;
import com.facebook.appevents.UserDataStore;
import com.facebook.share.internal.ShareConstants;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class aj extends ad<JSONObject> {
    public final JSONObject a;
    public final a k;
    public boolean l;
    protected final ap m;
    private final String n;
    private String o;
    private final com.chartboost.sdk.Tracking.a p;

    public interface a {
        void a(aj ajVar, CBError cBError);

        void a(aj ajVar, JSONObject jSONObject);
    }

    public aj(String str, ap apVar, com.chartboost.sdk.Tracking.a aVar, int i, a aVar2) {
        super(HttpRequest.METHOD_POST, a(str), i, null);
        this.l = false;
        this.a = new JSONObject();
        this.n = str;
        this.m = apVar;
        this.p = aVar;
        this.k = aVar2;
    }

    public static String a(String str) {
        Object[] objArr = new Object[3];
        objArr[0] = "https://live.chartboost.com";
        String str2 = Constants.URL_PATH_DELIMITER;
        if (str != null && str.startsWith(Constants.URL_PATH_DELIMITER)) {
            str2 = "";
        }
        objArr[1] = str2;
        if (str == null) {
            str = "";
        }
        objArr[2] = str;
        return String.format("%s%s%s", objArr);
    }

    public void a(String str, Object obj) {
        com.chartboost.sdk.Libraries.e.a(this.a, str, obj);
    }

    protected void c() {
        a("app", this.m.s);
        a("model", this.m.f);
        a("device_type", this.m.t);
        a("actual_device_type", this.m.u);
        a("os", this.m.g);
        a(UserDataStore.COUNTRY, this.m.h);
        a("language", this.m.i);
        a("sdk", this.m.l);
        a("user_agent", com.chartboost.sdk.i.w);
        a("timestamp", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(this.m.e.a())));
        a(SettingsJsonConstants.SESSION_KEY, Integer.valueOf(this.m.d.getInt("cbPrefSessionCount", 0)));
        a("reachability", Integer.valueOf(this.m.b.a()));
        a("scale", this.m.r);
        a("is_portrait", Boolean.valueOf(CBUtility.a(CBUtility.a())));
        a("bundle", this.m.j);
        a(com.my.target.be.a.eX, this.m.k);
        a("carrier", this.m.v);
        a("custom_id", com.chartboost.sdk.i.a);
        a("mediation", com.chartboost.sdk.i.h);
        if (com.chartboost.sdk.i.d != null) {
            a("framework_version", com.chartboost.sdk.i.f);
            a("wrapper_version", com.chartboost.sdk.i.b);
        }
        a("rooted_device", Boolean.valueOf(this.m.w));
        a(com.my.target.i.K, this.m.x);
        a("mobile_network", this.m.y);
        a("dw", this.m.o);
        a("dh", this.m.p);
        a(com.my.target.i.N, this.m.q);
        a(com.my.target.i.WIDTH, this.m.m);
        a(com.my.target.i.HEIGHT, this.m.n);
        a("commit_hash", "ea5c9878e5dca6c95016765177cbd146c39a21f7");
        com.chartboost.sdk.Libraries.d.a aVarA = this.m.a.a();
        a("identity", aVarA.b);
        if (aVarA.a != -1) {
            a("limit_ad_tracking", Boolean.valueOf(aVarA.a == 1));
        }
        a("pidatauseconsent", Integer.valueOf(com.chartboost.sdk.i.x.getValue()));
        String str = this.m.c.get().a;
        if (!s.a().a(str)) {
            a("config_variant", str);
        }
        a("certification_providers", o.e());
    }

    public String d() {
        return e();
    }

    public String e() {
        String str = this.n;
        String str2 = Constants.URL_PATH_DELIMITER;
        if (str == null) {
            return Constants.URL_PATH_DELIMITER;
        }
        StringBuilder sb = new StringBuilder();
        if (this.n.startsWith(Constants.URL_PATH_DELIMITER)) {
            str2 = "";
        }
        sb.append(str2);
        sb.append(this.n);
        return sb.toString();
    }

    private void a(ag agVar, CBError cBError) {
        com.chartboost.sdk.Libraries.e.a[] aVarArr = new com.chartboost.sdk.Libraries.e.a[5];
        aVarArr[0] = com.chartboost.sdk.Libraries.e.a("endpoint", e());
        aVarArr[1] = com.chartboost.sdk.Libraries.e.a("statuscode", agVar == null ? "None" : Integer.valueOf(agVar.a));
        aVarArr[2] = com.chartboost.sdk.Libraries.e.a("error", cBError == null ? "None" : cBError.a().toString());
        aVarArr[3] = com.chartboost.sdk.Libraries.e.a("errorDescription", cBError != null ? cBError.b() : "None");
        aVarArr[4] = com.chartboost.sdk.Libraries.e.a("retryCount", (Object) 0);
        this.p.a("request_manager", ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, cBError == null ? "success" : "failure", (String) null, (String) null, (String) null, com.chartboost.sdk.Libraries.e.a(aVarArr));
    }

    public void b(String str) {
        this.o = str;
    }

    @Override // com.chartboost.sdk.impl.ad
    public ae a() {
        c();
        String string = this.a.toString();
        String str = com.chartboost.sdk.i.k;
        String strB = com.chartboost.sdk.Libraries.c.b(com.chartboost.sdk.Libraries.c.a(String.format(Locale.US, "%s %s\n%s\n%s", this.b, d(), com.chartboost.sdk.i.l, string).getBytes()));
        HashMap map = new HashMap();
        map.put("Accept", "application/json");
        map.put("X-Chartboost-Client", CBUtility.b());
        map.put("X-Chartboost-API", "7.3.1");
        map.put("X-Chartboost-App", str);
        map.put("X-Chartboost-Signature", strB);
        return new ae(map, string.getBytes(), "application/json");
    }

    @Override // com.chartboost.sdk.impl.ad
    public af<JSONObject> a(ag agVar) {
        try {
            if (agVar.b == null) {
                return af.a(new CBError(CBError.a.INVALID_RESPONSE, "Response is not a valid json object"));
            }
            JSONObject jSONObject = new JSONObject(new String(agVar.b));
            CBLogging.c("CBRequest", "Request " + e() + " succeeded. Response code: " + agVar.a + ", body: " + jSONObject.toString(4));
            if (this.l) {
                int iOptInt = jSONObject.optInt("status");
                if (iOptInt == 404) {
                    return af.a(new CBError(CBError.a.HTTP_NOT_FOUND, "404 error from server"));
                }
                if (iOptInt < 200 || iOptInt > 299) {
                    String str = "Request failed due to status code " + iOptInt + " in message";
                    CBLogging.b("CBRequest", str);
                    return af.a(new CBError(CBError.a.UNEXPECTED_RESPONSE, str));
                }
            }
            return af.a(jSONObject);
        } catch (Exception e) {
            com.chartboost.sdk.Tracking.a.a(getClass(), "parseServerResponse", e);
            return af.a(new CBError(CBError.a.MISCELLANEOUS, e.getLocalizedMessage()));
        }
    }

    @Override // com.chartboost.sdk.impl.ad
    public void a(JSONObject jSONObject, ag agVar) {
        a aVar = this.k;
        if (aVar != null && jSONObject != null) {
            aVar.a(this, jSONObject);
        }
        if (this.p != null) {
            a(agVar, (CBError) null);
        }
    }

    @Override // com.chartboost.sdk.impl.ad
    public void a(CBError cBError, ag agVar) {
        if (cBError == null) {
            return;
        }
        a aVar = this.k;
        if (aVar != null) {
            aVar.a(this, cBError);
        }
        if (this.p != null) {
            a(agVar, cBError);
        }
    }
}
