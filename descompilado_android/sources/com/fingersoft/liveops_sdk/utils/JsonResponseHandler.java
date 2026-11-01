package com.fingersoft.liveops_sdk.utils;

import com.fingersoft.liveops_sdk.crosspromotions.CrossPromotionCollection;
import com.ironsource.sdk.precache.DownloadManager;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class JsonResponseHandler {
    private JsonResultContainer resultContainer = new JsonResultContainer();

    public JsonResponseHandler(String str) {
        parseJson(str);
    }

    private void parseJson(String str) {
        CommonUtils.log("result is: " + str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            int iIntValue = 1;
            long jLongValue = 0;
            try {
                iIntValue = ((Integer) jSONObject.get("jsonver")).intValue();
            } catch (Exception unused) {
            }
            try {
                jLongValue = ((Long) jSONObject.get("srvtime")).longValue();
            } catch (Exception unused2) {
            }
            CommonUtils.log("Parsing jsonver " + iIntValue);
            CommonUtils.log("Parsing srvtime " + jLongValue);
            this.resultContainer.setApplicationInfo(new ApplicationInfo(new JSONObject(str).getJSONObject("application")));
            this.resultContainer.setSettingCollection(new SettingCollection(new JSONObject(str).getJSONArray(DownloadManager.SETTINGS), iIntValue));
            this.resultContainer.setPromotionCollection(new CrossPromotionCollection(new JSONObject(str).getJSONArray("promotions"), iIntValue));
            this.resultContainer.setServerTime(jLongValue);
            this.resultContainer.setNewsCollection(new JSONObject(str).getJSONObject("news"));
        } catch (Exception e) {
            CommonUtils.log("Error parsing JSON " + e.getMessage());
        }
    }

    public class JsonResultContainer implements IResultContainer {
        private JSONObject mNewsCollection;
        private SettingCollection settingCollection = null;
        private CrossPromotionCollection crossPromotionCollection = null;
        private ApplicationInfo applicationInfo = null;
        private long serverTime = 0;

        public JsonResultContainer() {
        }

        protected void setApplicationInfo(ApplicationInfo applicationInfo) {
            this.applicationInfo = applicationInfo;
        }

        protected void setSettingCollection(SettingCollection settingCollection) {
            this.settingCollection = settingCollection;
        }

        protected void setPromotionCollection(CrossPromotionCollection crossPromotionCollection) {
            CommonUtils.log("promotion collection saved " + crossPromotionCollection.toString());
            this.crossPromotionCollection = crossPromotionCollection;
        }

        protected void setNewsCollection(JSONObject jSONObject) {
            this.mNewsCollection = jSONObject;
        }

        protected void setServerTime(long j) {
            this.serverTime = j;
        }

        @Override // com.fingersoft.liveops_sdk.utils.IResultContainer
        public ApplicationInfo getApplicationInfo() {
            return this.applicationInfo;
        }

        @Override // com.fingersoft.liveops_sdk.utils.IResultContainer
        public SettingCollection getSettings() {
            return this.settingCollection;
        }

        @Override // com.fingersoft.liveops_sdk.utils.IResultContainer
        public CrossPromotionCollection getPromotions() {
            CommonUtils.log("promotion collection get " + this.crossPromotionCollection.toString());
            return this.crossPromotionCollection;
        }

        @Override // com.fingersoft.liveops_sdk.utils.IResultContainer
        public JSONObject getNewsCollection() {
            return this.mNewsCollection;
        }

        @Override // com.fingersoft.liveops_sdk.utils.IResultContainer
        public long getServerTime() {
            return this.serverTime;
        }
    }

    public IResultContainer getResultContainer() {
        return this.resultContainer;
    }
}
