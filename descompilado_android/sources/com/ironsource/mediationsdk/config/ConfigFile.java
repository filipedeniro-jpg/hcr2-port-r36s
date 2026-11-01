package com.ironsource.mediationsdk.config;

import com.adcolony.sdk.AdColonyAppOptions;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class ConfigFile {
    private static ConfigFile mInstance;
    private String mPluginFrameworkVersion;
    private String mPluginType;
    private String mPluginVersion;
    private String[] mSupportedPlugins = {AdColonyAppOptions.UNITY, "AdobeAir", "Xamarin", AdColonyAppOptions.CORONA, AdColonyAppOptions.ADMOB, AdColonyAppOptions.MOPUB};

    public static synchronized ConfigFile getConfigFile() {
        if (mInstance == null) {
            mInstance = new ConfigFile();
        }
        return mInstance;
    }

    public void setPluginData(String str, String str2, String str3) {
        if (str != null) {
            if (Arrays.asList(this.mSupportedPlugins).contains(str)) {
                this.mPluginType = str;
            } else {
                this.mPluginType = null;
            }
        }
        if (str2 != null) {
            this.mPluginVersion = str2;
        }
        if (str3 != null) {
            this.mPluginFrameworkVersion = str3;
        }
    }

    public String getPluginType() {
        return this.mPluginType;
    }

    public String getPluginVersion() {
        return this.mPluginVersion;
    }

    public String getPluginFrameworkVersion() {
        return this.mPluginFrameworkVersion;
    }
}
