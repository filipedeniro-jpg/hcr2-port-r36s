package com.appsflyer;

import androidx.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ServerConfigHandler {
    @Nullable
    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    static JSONObject m95(String str) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str);
            try {
                if (jSONObject.optBoolean("monitor", false)) {
                    s.m187().m192();
                } else {
                    s.m187().m195();
                    s.m187().m197();
                }
            } catch (JSONException unused) {
                s.m187().m195();
                s.m187().m197();
            } catch (Throwable th) {
                th = th;
                AFLogger.afErrorLog(th.getMessage(), th);
                s.m187().m195();
                s.m187().m197();
            }
        } catch (JSONException unused2) {
            jSONObject = null;
        } catch (Throwable th2) {
            th = th2;
            jSONObject = null;
        }
        return jSONObject;
    }

    public static String getUrl(String str) {
        return String.format(str, AppsFlyerLib.getInstance().getHostPrefix(), AppsFlyerLib.getInstance().getHostName());
    }
}
