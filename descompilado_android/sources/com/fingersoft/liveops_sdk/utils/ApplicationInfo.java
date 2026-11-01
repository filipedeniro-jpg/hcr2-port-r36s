package com.fingersoft.liveops_sdk.utils;

import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ApplicationInfo {
    private JSONObject mProviderSettings;
    private String mBannerPriorityList = null;
    private String mInterstitialPriorityList = null;
    private String mVideoPriorityList = null;
    private ArrayList<String> mVideoGroupPriorities = new ArrayList<>();

    public int getBannerProviderTimeout(String str) {
        return 60;
    }

    public ApplicationInfo(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("provider_settings");
            parseJson(jSONObject, jSONObject2);
            this.mProviderSettings = jSONObject2;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("", "Failed to parse JSON in applicationInfo");
        }
    }

    private void parseJson(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            this.mBannerPriorityList = jSONObject.getString("providers");
            this.mInterstitialPriorityList = jSONObject.getString("providers_int");
            this.mVideoPriorityList = jSONObject.getString("providers_vid");
            JSONObject jSONObject3 = jSONObject.getJSONObject("providers_vid_groups");
            jSONObject.getLong("srvtime");
            int i = 1;
            while (true) {
                if (!jSONObject3.has("group_" + i)) {
                    return;
                }
                this.mVideoGroupPriorities.add(jSONObject3.getString("group_" + i));
                i++;
            }
        } catch (JSONException unused) {
        }
    }

    public int getProviderShowLimit(String str) {
        JSONObject jSONObject = this.mProviderSettings;
        if (jSONObject == null) {
            return -1;
        }
        try {
            try {
                return jSONObject.getJSONObject(str).getInt("showLimit");
            } catch (JSONException unused) {
                Log.e("", "couldnt find showlimit from JSON");
                return -1;
            }
        } catch (JSONException unused2) {
            Log.e("", "couldnt find provider from JSON");
            return -1;
        }
    }

    public String getBannerPriorityList() {
        return this.mBannerPriorityList;
    }

    public String getVideoPriorityList() {
        return this.mVideoPriorityList;
    }

    public String getInterstitialPriorityList() {
        return this.mInterstitialPriorityList;
    }

    public ArrayList<String> getVideoGroupPriorities() {
        return this.mVideoGroupPriorities;
    }
}
