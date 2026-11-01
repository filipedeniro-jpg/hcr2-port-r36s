package com.fingersoft.liveops_sdk.utils;

import com.fingersoft.liveops_sdk.crosspromotions.CrossPromotionCollection;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public interface IResultContainer {
    ApplicationInfo getApplicationInfo();

    JSONObject getNewsCollection();

    CrossPromotionCollection getPromotions();

    long getServerTime();

    SettingCollection getSettings();
}
