package com.fingersoft.ads_sdk.advertising.json;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public interface IResultContainer {
    ApplicationInfo getApplicationInfo();

    JSONObject getNewsCollection();

    CrossPromotionCollection getPromotions();

    long getServerTime();

    SettingCollection getSettings();
}
