package com.fingersoft.liveops_sdk.BIAnalysis;

import android.app.Activity;
import com.appsflyer.AFInAppEventParameterName;
import com.appsflyer.AFInAppEventType;
import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerInAppPurchaseValidatorListener;
import com.appsflyer.AppsFlyerLib;
import com.fingersoft.liveops_sdk.settings.ApplicationSettings;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class AppsFlyer {
    static boolean mDisabled = false;
    private Activity mActivity;

    public AppsFlyer(Activity activity, String str, AppsFlyerConversionListener appsFlyerConversionListener) {
        AppsFlyerLib.getInstance().setDebugLog(false);
        if (ApplicationSettings.getValueInt(activity, "appsflyer_enabled", 1) == 0) {
            mDisabled = true;
            return;
        }
        this.mActivity = activity;
        if (mDisabled) {
            return;
        }
        AppsFlyerLib.getInstance().registerConversionListener(activity, appsFlyerConversionListener);
        AppsFlyerLib.getInstance().startTracking(activity.getApplication(), str);
    }

    public static void setDisabled() {
        mDisabled = true;
    }

    public static void setDebugLog(boolean z) {
        AppsFlyerLib.getInstance().setDebugLog(z);
    }

    public void setPlayerID(String str) {
        AppsFlyerLib.getInstance().setCustomerUserId(str);
    }

    public void validateAndTrackIapPurchase(final String str, final String str2, final String str3, final String str4, final String str5, final HashMap<String, String> map, final IAPValidationListener iAPValidationListener) {
        AppsFlyerLib.getInstance().registerValidatorListener(this.mActivity, new AppsFlyerInAppPurchaseValidatorListener() { // from class: com.fingersoft.liveops_sdk.BIAnalysis.AppsFlyer.1
            @Override // com.appsflyer.AppsFlyerInAppPurchaseValidatorListener
            public void onValidateInApp() {
                IAPValidationListener iAPValidationListener2 = iAPValidationListener;
                if (iAPValidationListener2 != null) {
                    iAPValidationListener2.onIapSuccess();
                }
            }

            @Override // com.appsflyer.AppsFlyerInAppPurchaseValidatorListener
            public void onValidateInAppFailure(String str6) {
                IAPValidationListener iAPValidationListener2 = iAPValidationListener;
                if (iAPValidationListener2 != null) {
                    iAPValidationListener2.onIapFailed(str6);
                }
            }
        });
        new Thread(new Runnable() { // from class: com.fingersoft.liveops_sdk.BIAnalysis.AppsFlyer.2
            @Override // java.lang.Runnable
            public void run() {
                AppsFlyerLib.getInstance().validateAndTrackInAppPurchase(AppsFlyer.this.mActivity, str, str2, str3, str4, str5, map);
            }
        }).start();
    }

    public void trackEvent(String str, Map map) {
        if (mDisabled) {
            return;
        }
        AppsFlyerLib.getInstance().trackEvent(this.mActivity, str, map);
    }

    public void trackRevenue(String str, float f, String str2) {
        if (mDisabled) {
            return;
        }
        HashMap map = new HashMap();
        map.put(AFInAppEventParameterName.REVENUE, Float.valueOf(f));
        map.put(AFInAppEventParameterName.CONTENT_TYPE, "category_a");
        map.put(AFInAppEventParameterName.CONTENT_ID, str2);
        map.put(AFInAppEventParameterName.CURRENCY, str);
        AppsFlyerLib.getInstance().trackEvent(this.mActivity, AFInAppEventType.PURCHASE, map);
    }
}
