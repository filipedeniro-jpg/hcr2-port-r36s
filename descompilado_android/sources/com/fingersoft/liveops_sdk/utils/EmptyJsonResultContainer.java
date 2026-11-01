package com.fingersoft.liveops_sdk.utils;

import com.fingersoft.liveops_sdk.crosspromotions.CrossPromotionCollection;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class EmptyJsonResultContainer implements IResultContainer {
    @Override // com.fingersoft.liveops_sdk.utils.IResultContainer
    public long getServerTime() {
        return -1L;
    }

    @Override // com.fingersoft.liveops_sdk.utils.IResultContainer
    public ApplicationInfo getApplicationInfo() {
        return new ApplicationInfo(new JSONObject());
    }

    @Override // com.fingersoft.liveops_sdk.utils.IResultContainer
    public SettingCollection getSettings() {
        return new SettingCollection(new JSONArray());
    }

    @Override // com.fingersoft.liveops_sdk.utils.IResultContainer
    public CrossPromotionCollection getPromotions() {
        return new CrossPromotionCollection(new JSONArray(), 0);
    }

    @Override // com.fingersoft.liveops_sdk.utils.IResultContainer
    public JSONObject getNewsCollection() {
        return new JSONObject();
    }
}
