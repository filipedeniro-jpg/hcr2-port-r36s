package com.ironsource.sdk;

import android.app.Activity;
import android.content.Context;
import com.ironsource.sdk.ISNAdView.ISNAdView;
import com.ironsource.sdk.agent.IronSourceAdsPublisherAgent;
import com.ironsource.sdk.listeners.OnBannerListener;
import com.ironsource.sdk.listeners.OnOfferWallListener;
import com.ironsource.sdk.utils.SDKUtils;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class IronSourceNetwork {
    private static IronSourceNetworkAPI ironSourceNetwork;
    private static JSONObject mConsentParams;

    public static synchronized void initSDK(final Activity activity, final String str, final String str2, final Map<String, String> map) {
        activity.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.IronSourceNetwork.1
            @Override // java.lang.Runnable
            public void run() {
                if (IronSourceNetwork.ironSourceNetwork == null) {
                    SDKUtils.setInitSDKParams(map);
                    IronSourceNetworkAPI unused = IronSourceNetwork.ironSourceNetwork = IronSourceAdsPublisherAgent.createInstance(activity, str, str2);
                    IronSourceNetwork.applyConsentInfo(IronSourceNetwork.mConsentParams);
                }
            }
        });
    }

    private static synchronized void validateInitSDK() throws Exception {
        if (ironSourceNetwork == null) {
            throw new NullPointerException("Call initSDK first");
        }
    }

    public static synchronized void loadAd(IronSourceAdInstance ironSourceAdInstance, Map<String, String> map) throws Exception {
        validateInitSDK();
        ironSourceNetwork.loadAd(ironSourceAdInstance, map);
    }

    public static String getVersion() {
        return SDKUtils.getSDKVersion();
    }

    public static synchronized void loadAd(IronSourceAdInstance ironSourceAdInstance) throws Exception {
        loadAd(ironSourceAdInstance, null);
    }

    public static synchronized void showAd(IronSourceAdInstance ironSourceAdInstance, Map<String, String> map) throws Exception {
        validateInitSDK();
        ironSourceNetwork.showAd(ironSourceAdInstance, map);
    }

    public static synchronized void showAd(IronSourceAdInstance ironSourceAdInstance) throws Exception {
        showAd(ironSourceAdInstance, null);
    }

    public static synchronized boolean isAdAvailableForInstance(IronSourceAdInstance ironSourceAdInstance) {
        if (ironSourceNetwork == null) {
            return false;
        }
        return ironSourceNetwork.isAdAvailable(ironSourceAdInstance);
    }

    public static synchronized void onPause(Activity activity) {
        if (ironSourceNetwork == null) {
            return;
        }
        ironSourceNetwork.onPause(activity);
    }

    public static synchronized void onResume(Activity activity) {
        if (ironSourceNetwork == null) {
            return;
        }
        ironSourceNetwork.onResume(activity);
    }

    public static synchronized void updateConsentInfo(JSONObject jSONObject) {
        mConsentParams = jSONObject;
        applyConsentInfo(jSONObject);
    }

    public static synchronized void applyConsentInfo(JSONObject jSONObject) {
        if (ironSourceNetwork == null) {
            return;
        }
        if (jSONObject == null) {
            return;
        }
        ironSourceNetwork.updateConsentInfo(jSONObject);
    }

    public static synchronized void release(Activity activity) {
        if (ironSourceNetwork == null) {
            return;
        }
        ironSourceNetwork.release(activity);
    }

    public static synchronized String getToken(Context context) {
        if (ironSourceNetwork == null) {
            return null;
        }
        return ironSourceNetwork.getToken(context);
    }

    public static synchronized void initOfferWall(Map<String, String> map, OnOfferWallListener onOfferWallListener) throws Exception {
        validateInitSDK();
        ironSourceNetwork.initOfferWall(map, onOfferWallListener);
    }

    public static synchronized void showOfferWall(Map<String, String> map) throws Exception {
        validateInitSDK();
        ironSourceNetwork.showOfferWall(map);
    }

    public static synchronized void getOfferWallCredits(OnOfferWallListener onOfferWallListener) throws Exception {
        validateInitSDK();
        ironSourceNetwork.getOfferWallCredits(onOfferWallListener);
    }

    public static synchronized void initBanner(String str, Map<String, String> map, OnBannerListener onBannerListener) throws Exception {
        validateInitSDK();
        ironSourceNetwork.initBanner(str, map, onBannerListener);
    }

    public static synchronized void loadBanner(JSONObject jSONObject) throws Exception {
        validateInitSDK();
        ironSourceNetwork.loadBanner(jSONObject);
    }

    public static synchronized ISNAdView createBanner(Activity activity, ISAdSize iSAdSize) throws Exception {
        validateInitSDK();
        return ironSourceNetwork.createBanner(activity, iSAdSize);
    }
}
