package com.fingersoft.hcr2;

import android.app.Activity;
import com.fingersoft.billing.ValidationCallback;
import com.fingersoft.billing.util.Purchase;
import com.fingersoft.liveops_sdk.BIAnalysis.BIAnalysisManager;
import com.fingersoft.liveops_sdk.BIAnalysis.IAPValidationListener;

/* JADX INFO: loaded from: classes.dex */
public class BusinessIntelligence {
    private static final String TAG = "hcr2 - BI";
    private static BIAnalysisManager bi;
    private static Activity mActivity;

    public BusinessIntelligence(Activity activity) {
        mActivity = activity;
        bi = new BIAnalysisManager(activity);
    }

    public void InitializeAppsflyer(String str) {
        BIAnalysisManager bIAnalysisManager = bi;
        if (bIAnalysisManager != null) {
            bIAnalysisManager.withAppsflyer(PlatformConfig.APPSFLYER_DEV_KEY, mActivity.getApplication(), str);
        }
    }

    public static void verifyPurchase(String str, final Purchase purchase, String str2, String str3, final ValidationCallback validationCallback) {
        try {
            Log.d(TAG, "Verifying purchase: " + purchase.getSku());
            bi.sendTrackAndValidateIapPurchase(str, purchase.getSignature(), purchase.getOriginalJson(), str2, str3, null, new IAPValidationListener() { // from class: com.fingersoft.hcr2.BusinessIntelligence.1
                @Override // com.fingersoft.liveops_sdk.BIAnalysis.IAPValidationListener
                public void onIapSuccess() {
                    Log.d(BusinessIntelligence.TAG, "onIapSuccess");
                    validationCallback.onValidateSuccess(purchase);
                }

                @Override // com.fingersoft.liveops_sdk.BIAnalysis.IAPValidationListener
                public void onIapFailed(String str4) {
                    Log.d(BusinessIntelligence.TAG, "onIapFailed: " + str4);
                    validationCallback.onValidateFailed(purchase, str4);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setPlayerId(String str) {
        try {
            if (bi != null) {
                bi.setPlayerID(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onVideoShown(String str) {
        try {
            Log.d(TAG, "Appsflyer " + str);
            bi.sendVideoShownEvent(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onTutorialCompleted() {
        try {
            bi.sendTutorialCompletedEvent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onSocialShareReceived() {
        try {
            bi.sendSocialShareReceivedEvent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onSocialShareReceivedAtFirstStart() {
        try {
            bi.sendSocialShareReceivedAtFirstStartEvent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onSocialShare() {
        try {
            bi.sendSocialShareEvent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onRankChanged(int i) {
        try {
            bi.sendRankChangedEvent(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onNumberOfFriendsChanged(int i) {
        try {
            bi.sendNumberOfFriendsChangedEvent(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onLevelUnlocked(int i) {
        try {
            bi.sendLevelUnlockedEvent(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onBannerShown(String str) {
        try {
            bi.sendBannerShownEvent(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onInterstitialShown(String str) {
        try {
            bi.sendInterstitialShownEvent(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onIap(float f, String str) {
        try {
            bi.sendIAPEvent(f, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendAppsFlyerIAPEvent(float f, String str, String str2) {
        try {
            Log.d(TAG, "sendAppsFlyerIAPEvent: " + str2);
            bi.sendAppsFlyerIAPEvent(f, str, str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendFacebookIAPEvent(float f, String str, String str2) {
        try {
            Log.d(TAG, "sendFacebookIAPEvent: " + str2);
            bi.sendFacebookIAPEvent(f, str, str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onGemsToCoins(int i) {
        try {
            bi.sendGemsToCoinsEvent(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onGemsUsed(int i) {
        try {
            bi.sendGemsUsedEvent(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onCoinsUsed(int i) {
        try {
            bi.sendCoinsUsedEvent(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onVehicleUpgraded(String str, int i) {
        try {
            bi.sendVehicleUpgradedEvent(str, i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onVehicleUnlocked(String str) {
        try {
            bi.sendVehicleUnlockedEvent(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onCustomEvent(String str) {
        try {
            Log.d(TAG, "Custom AF event: " + str);
            bi.sendCustomEvent(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
