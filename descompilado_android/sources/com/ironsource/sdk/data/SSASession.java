package com.ironsource.sdk.data;

import android.content.Context;
import com.ironsource.environment.ConnectivityService;
import com.ironsource.sdk.utils.SDKUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SSASession {
    private String connectivity;
    private long sessionEndTime;
    private long sessionStartTime;
    private SessionType sessionType;
    public final String SESSION_START_TIME = "sessionStartTime";
    public final String SESSION_END_TIME = "sessionEndTime";
    public final String SESSION_TYPE = "sessionType";
    public final String CONNECTIVITY = "connectivity";

    public enum SessionType {
        launched,
        backFromBG
    }

    public SSASession(Context context, SessionType sessionType) {
        setSessionStartTime(SDKUtils.getCurrentTimeMillis().longValue());
        setSessionType(sessionType);
        setConnectivity(ConnectivityService.getConnectionType(context));
    }

    public SSASession(JSONObject jSONObject) {
        try {
            jSONObject.get("sessionStartTime");
            jSONObject.get("sessionEndTime");
            jSONObject.get("sessionType");
            jSONObject.get("connectivity");
        } catch (JSONException unused) {
        }
    }

    public void endSession() {
        setSessionEndTime(SDKUtils.getCurrentTimeMillis().longValue());
    }

    public long getSessionStartTime() {
        return this.sessionStartTime;
    }

    public void setSessionStartTime(long j) {
        this.sessionStartTime = j;
    }

    public long getSessionEndTime() {
        return this.sessionEndTime;
    }

    public void setSessionEndTime(long j) {
        this.sessionEndTime = j;
    }

    public SessionType getSessionType() {
        return this.sessionType;
    }

    public void setSessionType(SessionType sessionType) {
        this.sessionType = sessionType;
    }

    public String getConnectivity() {
        return this.connectivity;
    }

    public void setConnectivity(String str) {
        this.connectivity = str;
    }
}
