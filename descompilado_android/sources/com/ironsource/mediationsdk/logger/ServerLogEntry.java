package com.ironsource.mediationsdk.logger;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class ServerLogEntry {
    private int mLogLevel;
    private String mMessage;
    private IronSourceLogger.IronSourceTag mTag;
    private String mTimetamp;

    public ServerLogEntry(IronSourceLogger.IronSourceTag ironSourceTag, String str, String str2, int i) {
        this.mTag = ironSourceTag;
        this.mTimetamp = str;
        this.mMessage = str2;
        this.mLogLevel = i;
    }

    public JSONObject toJSON() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("timestamp", this.mTimetamp);
            jSONObject.put("tag", this.mTag);
            jSONObject.put("level", this.mLogLevel);
            jSONObject.put("message", this.mMessage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public int getLogLevel() {
        return this.mLogLevel;
    }
}
