package com.ironsource.eventsmodule;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class EventData {
    private JSONObject mAdditionalData;
    private int mEventId;
    private long mTimeStamp;

    public EventData(int i, long j, JSONObject jSONObject) {
        this.mEventId = -1;
        this.mTimeStamp = -1L;
        this.mEventId = i;
        this.mTimeStamp = j;
        if (jSONObject == null) {
            this.mAdditionalData = new JSONObject();
        } else {
            this.mAdditionalData = jSONObject;
        }
    }

    public EventData(int i, JSONObject jSONObject) {
        this.mEventId = -1;
        this.mTimeStamp = -1L;
        this.mEventId = i;
        this.mTimeStamp = System.currentTimeMillis();
        if (jSONObject == null) {
            this.mAdditionalData = new JSONObject();
        } else {
            this.mAdditionalData = jSONObject;
        }
    }

    public int getEventId() {
        return this.mEventId;
    }

    public long getTimeStamp() {
        return this.mTimeStamp;
    }

    public String getAdditionalData() {
        return this.mAdditionalData.toString();
    }

    public JSONObject getAdditionalDataJSON() {
        return this.mAdditionalData;
    }

    public void setEventId(int i) {
        this.mEventId = i;
    }

    public void addToAdditionalData(String str, Object obj) {
        try {
            this.mAdditionalData.put(str, obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
