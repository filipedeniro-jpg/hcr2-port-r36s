package com.ironsource.mediationsdk;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class AuctionResponseItem {
    private String mInstanceName;
    private boolean mIsValid;
    private String mServerData;
    private List<String> mWinUrls;

    public AuctionResponseItem(String str) {
        this.mInstanceName = str;
        this.mServerData = "";
        this.mWinUrls = new ArrayList();
        this.mIsValid = true;
    }

    public AuctionResponseItem(JSONObject jSONObject) {
        this.mIsValid = false;
        try {
            this.mInstanceName = jSONObject.getString("instance");
            if (jSONObject.has("adMarkup")) {
                this.mServerData = jSONObject.getString("adMarkup");
            } else {
                this.mServerData = "";
            }
            this.mWinUrls = new ArrayList();
            JSONArray jSONArray = jSONObject.getJSONArray("winURLs");
            for (int i = 0; i < jSONArray.length(); i++) {
                this.mWinUrls.add(jSONArray.getString(i));
            }
            this.mIsValid = true;
        } catch (Exception unused) {
        }
    }

    public String getInstanceName() {
        return this.mInstanceName;
    }

    public String getServerData() {
        return this.mServerData;
    }

    public List<String> getWinUrls() {
        return this.mWinUrls;
    }

    public boolean isValid() {
        return this.mIsValid;
    }
}
