package com.ironsource.mediationsdk;

import com.applovin.sdk.AppLovinMediationProvider;
import com.integralads.avid.library.adcolony.BuildConfig;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import com.my.target.aa;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public class AdaptersCompatibilityHandler {
    private static final AdaptersCompatibilityHandler instance = new AdaptersCompatibilityHandler();
    private ConcurrentHashMap<String, String> mAdapterNameToMinIsVersion = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, String> mAdapterNameToMinRvVersion;

    public static synchronized AdaptersCompatibilityHandler getInstance() {
        return instance;
    }

    private AdaptersCompatibilityHandler() {
        this.mAdapterNameToMinIsVersion.put(BuildConfig.SDK_NAME, "4.1.6");
        this.mAdapterNameToMinIsVersion.put("vungle", "4.1.5");
        this.mAdapterNameToMinIsVersion.put("applovin", "4.3.3");
        this.mAdapterNameToMinIsVersion.put("admob", "4.3.2");
        this.mAdapterNameToMinRvVersion = new ConcurrentHashMap<>();
        this.mAdapterNameToMinRvVersion.put(BuildConfig.SDK_NAME, "4.1.6");
        this.mAdapterNameToMinRvVersion.put("admob", "4.3.2");
        this.mAdapterNameToMinRvVersion.put("applovin", "4.3.3");
        this.mAdapterNameToMinRvVersion.put("chartboost", "4.1.9");
        this.mAdapterNameToMinRvVersion.put(AppLovinMediationProvider.FYBER, "4.1.0");
        this.mAdapterNameToMinRvVersion.put("hyprmx", "4.1.2");
        this.mAdapterNameToMinRvVersion.put(com.integralads.avid.library.inmobi.BuildConfig.SDK_NAME, "4.3.1");
        this.mAdapterNameToMinRvVersion.put("maio", "4.1.3");
        this.mAdapterNameToMinRvVersion.put("mediabrix", "4.1.1");
        this.mAdapterNameToMinRvVersion.put("mopub", "3.2.0");
        this.mAdapterNameToMinRvVersion.put("tapjoy", "4.0.0");
        this.mAdapterNameToMinRvVersion.put("unityads", "4.1.4");
        this.mAdapterNameToMinRvVersion.put("vungle", "4.1.5");
    }

    public synchronized boolean isAdapterVersionRVCompatible(AbstractAdapter abstractAdapter) {
        return isAdapterVersionCompatible(abstractAdapter, this.mAdapterNameToMinRvVersion, "rewarded video");
    }

    public synchronized boolean isAdapterVersionISCompatible(AbstractAdapter abstractAdapter) {
        return isAdapterVersionCompatible(abstractAdapter, this.mAdapterNameToMinIsVersion, aa.e.bo);
    }

    private synchronized boolean isAdapterVersionCompatible(AbstractAdapter abstractAdapter, Map<String, String> map, String str) {
        if (abstractAdapter == null) {
            return false;
        }
        String lowerCase = abstractAdapter.getProviderName().toLowerCase();
        if (!map.containsKey(lowerCase)) {
            return true;
        }
        String str2 = map.get(lowerCase);
        String version = abstractAdapter.getVersion();
        boolean zIsVersionGreaterOrEqual = isVersionGreaterOrEqual(str2, version);
        if (!zIsVersionGreaterOrEqual) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.API, abstractAdapter.getProviderName() + " adapter " + version + " is incompatible with SDK version " + IronSourceUtils.getSDKVersion() + " for " + str + " ad unit, please update your adapter to the latest version", 3);
        }
        return zIsVersionGreaterOrEqual;
    }

    public synchronized boolean isBannerAdapterCompatible(AbstractAdapter abstractAdapter) {
        if (abstractAdapter == null) {
            return false;
        }
        String version = abstractAdapter.getVersion();
        boolean zIsVersionGreaterOrEqual = isVersionGreaterOrEqual("4.3.0", version);
        if (!zIsVersionGreaterOrEqual) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.API, abstractAdapter.getProviderName() + " adapter " + version + " is incompatible with SDK version " + IronSourceUtils.getSDKVersion() + ", please update your adapter to the latest version", 3);
        }
        return zIsVersionGreaterOrEqual;
    }

    private boolean isVersionGreaterOrEqual(String str, String str2) {
        if (str.equalsIgnoreCase(str2)) {
            return true;
        }
        String[] strArrSplit = str.split("\\.");
        String[] strArrSplit2 = str2.split("\\.");
        for (int i = 0; i < 3; i++) {
            int i2 = Integer.parseInt(strArrSplit[i]);
            int i3 = Integer.parseInt(strArrSplit2[i]);
            if (i3 < i2) {
                return false;
            }
            if (i3 > i2) {
                return true;
            }
        }
        return true;
    }
}
