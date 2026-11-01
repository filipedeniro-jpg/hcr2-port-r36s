package com.ironsource.adapters.supersonicads;

import android.text.TextUtils;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.model.ProviderSettingsHolder;
import com.ironsource.sdk.constants.Constants;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class SupersonicConfig {
    private static SupersonicConfig mInstance;
    private Map<String, String> mOfferwallCustomParams;
    private Map<String, String> mRewardedVideoCustomParams;
    private final String CUSTOM_PARAM_PREFIX = "custom_";
    private final String CLIENT_SIDE_CALLBACKS = Constants.ParametersKeys.USE_CLIENT_SIDE_CALLBACKS;
    private final String MAX_VIDEO_LENGTH = "maxVideoLength";
    private final String DYNAMIC_CONTROLLER_URL = "controllerUrl";
    private final String DYNAMIC_CONTROLLER_DEBUG_MODE = "debugMode";
    private final String CAMPAIGN_ID = Constants.RequestParameters.CAMPAIGN_ID;
    private final String LANGUAGE = "language";
    private final String APPLICATION_PRIVATE_KEY = "privateKey";
    private final String ITEM_NAME = "itemName";
    private final String ITEM_COUNT = "itemCount";
    ProviderSettings mProviderSettings = new ProviderSettings(ProviderSettingsHolder.getProviderSettingsHolder().getProviderSettings("Mediation"));

    public static SupersonicConfig getConfigObj() {
        if (mInstance == null) {
            mInstance = new SupersonicConfig();
        }
        return mInstance;
    }

    private SupersonicConfig() {
    }

    public void setClientSideCallbacks(boolean z) {
        this.mProviderSettings.setRewardedVideoSettings(Constants.ParametersKeys.USE_CLIENT_SIDE_CALLBACKS, String.valueOf(z));
    }

    public void setCustomControllerUrl(String str) {
        this.mProviderSettings.setRewardedVideoSettings("controllerUrl", str);
        this.mProviderSettings.setInterstitialSettings("controllerUrl", str);
        this.mProviderSettings.setBannerSettings("controllerUrl", str);
    }

    public void setDebugMode(int i) {
        this.mProviderSettings.setRewardedVideoSettings("debugMode", Integer.valueOf(i));
        this.mProviderSettings.setInterstitialSettings("debugMode", Integer.valueOf(i));
        this.mProviderSettings.setBannerSettings("debugMode", Integer.valueOf(i));
    }

    public void setCampaignId(String str) {
        this.mProviderSettings.setRewardedVideoSettings(Constants.RequestParameters.CAMPAIGN_ID, str);
    }

    public void setLanguage(String str) {
        this.mProviderSettings.setRewardedVideoSettings("language", str);
        this.mProviderSettings.setInterstitialSettings("language", str);
    }

    public void setRewardedVideoCustomParams(Map<String, String> map) {
        this.mRewardedVideoCustomParams = convertCustomParams(map);
    }

    public void setOfferwallCustomParams(Map<String, String> map) {
        this.mOfferwallCustomParams = convertCustomParams(map);
    }

    private Map<String, String> convertCustomParams(Map<String, String> map) {
        HashMap map2 = new HashMap();
        if (map != null) {
            try {
                Set<String> setKeySet = map.keySet();
                if (setKeySet != null) {
                    for (String str : setKeySet) {
                        if (!TextUtils.isEmpty(str)) {
                            String str2 = map.get(str);
                            if (TextUtils.isEmpty(str2)) {
                                continue;
                            } else {
                                map2.put("custom_" + str, str2);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, ":convertCustomParams()", e);
            }
        }
        return map2;
    }

    public boolean getClientSideCallbacks() {
        ProviderSettings providerSettings = this.mProviderSettings;
        if (providerSettings == null || providerSettings.getRewardedVideoSettings() == null || !this.mProviderSettings.getRewardedVideoSettings().has(Constants.ParametersKeys.USE_CLIENT_SIDE_CALLBACKS)) {
            return false;
        }
        return this.mProviderSettings.getRewardedVideoSettings().optBoolean(Constants.ParametersKeys.USE_CLIENT_SIDE_CALLBACKS, false);
    }

    Map<String, String> getOfferwallCustomParams() {
        return this.mOfferwallCustomParams;
    }

    Map<String, String> getRewardedVideoCustomParams() {
        return this.mRewardedVideoCustomParams;
    }
}
