package com.ironsource.sdk.utils;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class IronSourceQaProperties {
    private static IronSourceQaProperties mInstance;
    private static Map<String, String> parameters = new HashMap();

    public static IronSourceQaProperties getInstance() {
        if (mInstance == null) {
            mInstance = new IronSourceQaProperties();
        }
        return mInstance;
    }

    private IronSourceQaProperties() {
    }

    public static boolean isInitialized() {
        return mInstance != null;
    }

    public void setQaParameter(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        parameters.put(str, str2);
    }

    public String getQaParameter(String str) {
        return parameters.containsKey(str) ? parameters.get(str) : "";
    }

    public Map<String, String> getParameters() {
        return parameters;
    }
}
