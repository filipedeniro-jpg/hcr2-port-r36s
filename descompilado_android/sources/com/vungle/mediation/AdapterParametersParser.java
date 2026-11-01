package com.vungle.mediation;

import android.os.Bundle;
import android.util.Log;
import com.appsflyer.AppsFlyerProperties;

/* JADX INFO: loaded from: classes2.dex */
class AdapterParametersParser {
    private static final String TAG = VungleManager.class.getSimpleName();

    AdapterParametersParser() {
    }

    static class Config {
        private String[] allPlacements;
        private String appId;

        Config() {
        }

        String getAppId() {
            return this.appId;
        }

        String[] getAllPlacements() {
            return this.allPlacements;
        }
    }

    public static Config parse(Bundle bundle, Bundle bundle2) throws IllegalArgumentException {
        String[] stringArray = bundle != null ? bundle.getStringArray("allPlacements") : null;
        String string = bundle2.getString(AppsFlyerProperties.APP_ID);
        if (string == null || string.isEmpty()) {
            Log.e(TAG, "Vungle app ID should be specified!");
            throw new IllegalArgumentException();
        }
        Config config = new Config();
        config.appId = string;
        config.allPlacements = stringArray;
        return config;
    }
}
