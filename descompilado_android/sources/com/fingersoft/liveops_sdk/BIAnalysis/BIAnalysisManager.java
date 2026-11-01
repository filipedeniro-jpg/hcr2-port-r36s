package com.fingersoft.liveops_sdk.BIAnalysis;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.appsflyer.AFInAppEventParameterName;
import com.appsflyer.AFInAppEventType;
import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerInAppPurchaseValidatorListener;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.AppsFlyerProperties;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class BIAnalysisManager {
    private static final String CUSTOM_EVENT_STRING_BANNER_SHOWN = "EVENT_NAME_BANNER_SHOWN";
    private static final String CUSTOM_EVENT_STRING_COINS_USED = "EVENT_NAME_COINS_USED";
    private static final String CUSTOM_EVENT_STRING_GEMS_TO_COINS = "EVENT_NAME_GEMS_TO_COINS";
    private static final String CUSTOM_EVENT_STRING_GEMS_USED = "EVENT_NAME_GEMS_USED";
    private static final String CUSTOM_EVENT_STRING_INTERSTITIAL_SHOWN = "EVENT_NAME_INTERSTITIAL_SHOWN";
    private static final String CUSTOM_EVENT_STRING_LEVEL_UNLOCKED = "EVENT_NAME_LEVEL_UNLOCKED";
    private static final String CUSTOM_EVENT_STRING_NUMBER_OF_FRIENDS_CHANGED = "EVENT_NAME_NUM_OF_FRIENDS_CHANGED";
    private static final String CUSTOM_EVENT_STRING_PROGRESS_CHANGED = "EVENT_NAME_PROGRESS_CHANGED";
    private static final String CUSTOM_EVENT_STRING_RANK_CHANGED = "EVENT_NAME_RANK_CHANGED";
    private static final String CUSTOM_EVENT_STRING_SOCIAL_SHARE = "EVENT_NAME_SOCIAL_SHARE";
    private static final String CUSTOM_EVENT_STRING_SOCIAL_SHARE_RECEIVED = "EVENT_NAME_SOCIAL_SHARE_RECEIVED";
    private static final String CUSTOM_EVENT_STRING_SOCIAL_SHARE_RECEIVED_AT_FIRST_START = "EVENT_NAME_SOCIAL_SHARE_AT_FIRST_START";
    private static final String CUSTOM_EVENT_STRING_VEHICLE_UNLOCKED = "EVENT_NAME_VEHICLE_UNLOCKED";
    private static final String CUSTOM_EVENT_STRING_VEHICLE_UPGRADE = "EVENT_NAME_VEHICLE_UPGRADE";
    private static final String CUSTOM_EVENT_STRING_VIDEO_SHOWN = "EVENT_NAME_VIDEO_SHOWN";
    private static final int MSG_TYPE_APPSFLYER_IAP_EVENT = 3;
    private static final int MSG_TYPE_BANNER_SHOWN_EVENT = 6;
    private static final int MSG_TYPE_COINS_USED_EVENT = 11;
    private static final int MSG_TYPE_CUSTOM_EVENT = 23;
    private static final int MSG_TYPE_FACEBOOK_IAP_EVENT = 4;
    private static final int MSG_TYPE_GEMS_TO_COINS_EVENT = 9;
    private static final int MSG_TYPE_GEMS_USED_EVENT = 10;
    private static final int MSG_TYPE_IAP_EVENT = 2;
    private static final int MSG_TYPE_INITIALIZE_SDK = 1;
    private static final int MSG_TYPE_INTERSTITIAL_SHOWN_EVENT = 7;
    private static final int MSG_TYPE_LEVEL_UNLOCKED_EVENT = 15;
    private static final int MSG_TYPE_NUMBER_OF_FRIENDS_CHANGED_EVENT = 20;
    private static final int MSG_TYPE_PROGRESS_CHANGED_EVENT = 12;
    private static final int MSG_TYPE_RANK_CHANGED_EVENT = 16;
    private static final int MSG_TYPE_SET_PLAYER_ID = 22;
    private static final int MSG_TYPE_SOCIAL_SHARE_EVENT = 17;
    private static final int MSG_TYPE_SOCIAL_SHARE_RECEIVED_AT_FIRST_START_EVENT = 19;
    private static final int MSG_TYPE_SOCIAL_SHARE_RECEIVED_EVENT = 18;
    private static final int MSG_TYPE_TRACK_AND_VALIDATE_IAP_PURCHASE = 21;
    private static final int MSG_TYPE_TUTORIAL_COMPLETED = 5;
    private static final int MSG_TYPE_VEHICLE_UNLOCKED_EVENT = 13;
    private static final int MSG_TYPE_VEHICLE_UPGRADED_EVENT = 14;
    private static final int MSG_TYPE_VIDEO_SHOWN_EVENT = 8;
    private Activity mActivity;
    private AppsFlyerLib mAppsFlyer;
    private BIAnalysisManagerState mBiAnalysisManagerState = BIAnalysisManagerState.NOT_INITIALIZED;
    private EventHandlerThread mEventHandlerThread = new EventHandlerThread();
    private AppEventsLogger mFacebookLogger;
    private EventHandler mHandler;

    public enum BIAnalysisManagerState {
        NOT_INITIALIZED,
        INITIALIZED
    }

    public BIAnalysisManager(Activity activity) {
        this.mActivity = activity;
        this.mEventHandlerThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.fingersoft.liveops_sdk.BIAnalysis.BIAnalysisManager.1
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, Throwable th) {
            }
        });
        this.mEventHandlerThread.start();
    }

    public BIAnalysisManager withAppsflyer(String str, Application application, String str2) {
        AppsFlyerConversionListener appsFlyerConversionListener = new AppsFlyerConversionListener() { // from class: com.fingersoft.liveops_sdk.BIAnalysis.BIAnalysisManager.2
            @Override // com.appsflyer.AppsFlyerConversionListener
            public void onAppOpenAttribution(Map<String, String> map) {
            }

            @Override // com.appsflyer.AppsFlyerConversionListener
            public void onAttributionFailure(String str3) {
            }

            @Override // com.appsflyer.AppsFlyerConversionListener
            public void onInstallConversionDataLoaded(Map<String, String> map) {
            }

            @Override // com.appsflyer.AppsFlyerConversionListener
            public void onInstallConversionFailure(String str3) {
            }
        };
        this.mAppsFlyer = AppsFlyerLib.getInstance();
        AppsFlyerLib appsFlyerLib = this.mAppsFlyer;
        if (appsFlyerLib != null) {
            appsFlyerLib.init(str, appsFlyerConversionListener, this.mActivity);
            if (AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.APP_USER_ID) == null) {
                this.mAppsFlyer.setCustomerUserId(str2);
            }
            this.mAppsFlyer.startTracking(application);
        }
        return this;
    }

    public BIAnalysisManager withFacebook() {
        try {
            AppEventsLogger.activateApp(this.mActivity);
            this.mFacebookLogger = AppEventsLogger.newLogger(this.mActivity);
        } catch (Exception unused) {
            this.mFacebookLogger = null;
        }
        return this;
    }

    public void sendAppsFlyerIAPEvent(float f, String str, String str2) {
        if (this.mHandler != null) {
            Message message = new Message();
            message.what = 3;
            message.obj = new IAPEvent(f, str, str2);
            this.mHandler.sendMessage(message);
        }
    }

    public void sendFacebookIAPEvent(float f, String str, String str2) {
        if (this.mHandler != null) {
            Message message = new Message();
            message.what = 4;
            message.obj = new IAPEvent(f, str, str2);
            this.mHandler.sendMessage(message);
        }
    }

    public void sendIAPEvent(float f, String str) {
        if (this.mHandler != null) {
            Message message = new Message();
            message.what = 2;
            message.obj = new IAPEvent(f, str, "IAP_ID1");
            this.mHandler.sendMessage(message);
        }
    }

    public void sendBannerShownEvent(String str) {
        if (this.mHandler != null) {
            Message message = new Message();
            message.what = 6;
            message.obj = str;
            this.mHandler.sendMessage(message);
        }
    }

    public void sendInterstitialShownEvent(String str) {
        if (this.mHandler != null) {
            Message message = new Message();
            message.what = 7;
            message.obj = str;
            this.mHandler.sendMessage(message);
        }
    }

    public void sendVideoShownEvent(String str) {
        if (this.mHandler != null) {
            Message message = new Message();
            message.what = 8;
            message.obj = str;
            this.mHandler.sendMessage(message);
        }
    }

    public void sendGemsToCoinsEvent(int i) {
        if (this.mHandler != null) {
            Message message = new Message();
            message.what = 9;
            message.obj = new Integer(i);
            this.mHandler.sendMessage(message);
        }
    }

    public void sendGemsUsedEvent(int i) {
        if (this.mHandler != null) {
            Message message = new Message();
            message.what = 10;
            message.obj = new Integer(i);
            this.mHandler.sendMessage(message);
        }
    }

    public void sendCoinsUsedEvent(int i) {
        if (this.mHandler != null) {
            Message message = new Message();
            message.what = 11;
            message.obj = new Integer(i);
            this.mHandler.sendMessage(message);
        }
    }

    public void sendProgressChangedEvent(int i) {
        if (this.mHandler != null) {
            Message message = new Message();
            message.what = 12;
            message.obj = new Integer(i);
            this.mHandler.sendMessage(message);
        }
    }

    public void sendVehicleUnlockedEvent(String str) {
        if (this.mHandler != null) {
            Message message = new Message();
            message.what = 13;
            message.obj = str;
            this.mHandler.sendMessage(message);
        }
    }

    public void sendVehicleUpgradedEvent(String str, int i) {
        if (this.mHandler != null) {
            VehicleUpgrade vehicleUpgrade = new VehicleUpgrade();
            vehicleUpgrade.mVehicle = str;
            vehicleUpgrade.mUpgrade = i;
            Message message = new Message();
            message.what = 14;
            message.obj = vehicleUpgrade;
            this.mHandler.sendMessage(message);
        }
    }

    public void sendLevelUnlockedEvent(int i) {
        if (this.mHandler != null) {
            Message message = new Message();
            message.what = 15;
            message.obj = new Integer(i);
            this.mHandler.sendMessage(message);
        }
    }

    public void sendTutorialCompletedEvent() {
        EventHandler eventHandler = this.mHandler;
        if (eventHandler != null) {
            eventHandler.sendEmptyMessage(5);
        }
    }

    public void sendRankChangedEvent(int i) {
        if (this.mHandler != null) {
            Message message = new Message();
            message.what = 16;
            message.obj = new Integer(i);
            this.mHandler.sendMessage(message);
        }
    }

    public void sendSocialShareEvent() {
        EventHandler eventHandler = this.mHandler;
        if (eventHandler != null) {
            eventHandler.sendEmptyMessage(17);
        }
    }

    public void sendSocialShareReceivedEvent() {
        EventHandler eventHandler = this.mHandler;
        if (eventHandler != null) {
            eventHandler.sendEmptyMessage(18);
        }
    }

    public void sendSocialShareReceivedAtFirstStartEvent() {
        EventHandler eventHandler = this.mHandler;
        if (eventHandler != null) {
            eventHandler.sendEmptyMessage(19);
        }
    }

    public void sendNumberOfFriendsChangedEvent(int i) {
        if (this.mHandler != null) {
            Message message = new Message();
            message.what = 20;
            message.obj = new Integer(i);
            this.mHandler.sendMessage(message);
        }
    }

    public void sendTrackAndValidateIapPurchase(String str, String str2, String str3, String str4, String str5, HashMap<String, String> map, IAPValidationListener iAPValidationListener) {
        if (this.mHandler != null) {
            ValidateIapPurchase validateIapPurchase = new ValidateIapPurchase();
            validateIapPurchase.mPublicKey = str;
            validateIapPurchase.mSignature = str2;
            validateIapPurchase.mPurchaseData = str3;
            validateIapPurchase.mPrice = str4;
            validateIapPurchase.mCurrency = str5;
            validateIapPurchase.mAdditionalParameters = map;
            validateIapPurchase.mListener = iAPValidationListener;
            Message message = new Message();
            message.what = 21;
            message.obj = validateIapPurchase;
            this.mHandler.sendMessage(message);
        }
    }

    public void setPlayerID(String str) {
        if (this.mHandler != null) {
            Message message = new Message();
            message.what = 22;
            message.obj = str;
            this.mHandler.sendMessage(message);
        }
    }

    public void sendCustomEvent(String str) {
        if (this.mHandler != null) {
            Message message = new Message();
            message.what = 23;
            message.obj = str;
            this.mHandler.sendMessage(message);
        }
    }

    public class EventHandlerThread extends Thread {
        public EventHandlerThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Looper.prepare();
            BIAnalysisManager bIAnalysisManager = BIAnalysisManager.this;
            bIAnalysisManager.mHandler = bIAnalysisManager.new EventHandler();
            BIAnalysisManager.this.mHandler.sendEmptyMessage(1);
            Looper.loop();
        }
    }

    public class EventHandler extends Handler {
        public EventHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = AnonymousClass3.$SwitchMap$com$fingersoft$liveops_sdk$BIAnalysis$BIAnalysisManager$BIAnalysisManagerState[BIAnalysisManager.this.mBiAnalysisManagerState.ordinal()];
            if (i == 1) {
                if (message.what != 1) {
                    return;
                }
                BIAnalysisManager.this.mBiAnalysisManagerState = BIAnalysisManagerState.INITIALIZED;
                return;
            }
            if (i != 2) {
                return;
            }
            try {
                switch (message.what) {
                    case 2:
                        IAPEvent iAPEvent = (IAPEvent) message.obj;
                        if (BIAnalysisManager.this.mFacebookLogger != null) {
                            try {
                                BIAnalysisManager.this.mFacebookLogger.logPurchase(iAPEvent.getAmount(), iAPEvent.getCurrency());
                                break;
                            } catch (Exception unused) {
                            }
                        }
                        if (BIAnalysisManager.this.mAppsFlyer != null) {
                            HashMap map = new HashMap();
                            map.put(AFInAppEventParameterName.REVENUE, Float.valueOf(iAPEvent.getFloatAmount()));
                            map.put(AFInAppEventParameterName.CONTENT_TYPE, "category_a");
                            map.put(AFInAppEventParameterName.CONTENT_ID, "IAP_ID1");
                            map.put(AFInAppEventParameterName.CURRENCY, iAPEvent.getCurrencyCode());
                            AppsFlyerLib unused2 = BIAnalysisManager.this.mAppsFlyer;
                            AppsFlyerLib.getInstance().trackEvent(BIAnalysisManager.this.mActivity, AFInAppEventType.PURCHASE, map);
                            return;
                        }
                        return;
                    case 3:
                        IAPEvent iAPEvent2 = (IAPEvent) message.obj;
                        if (BIAnalysisManager.this.mAppsFlyer == null) {
                            return;
                        }
                        HashMap map2 = new HashMap();
                        map2.put(AFInAppEventParameterName.REVENUE, Float.valueOf(iAPEvent2.getFloatAmount()));
                        map2.put(AFInAppEventParameterName.CONTENT_TYPE, "category_a");
                        map2.put(AFInAppEventParameterName.CONTENT_ID, iAPEvent2.getIAPCode());
                        map2.put(AFInAppEventParameterName.CURRENCY, iAPEvent2.getCurrencyCode());
                        AppsFlyerLib unused3 = BIAnalysisManager.this.mAppsFlyer;
                        AppsFlyerLib.getInstance().trackEvent(BIAnalysisManager.this.mActivity, AFInAppEventType.PURCHASE, map2);
                        break;
                    case 4:
                        IAPEvent iAPEvent3 = (IAPEvent) message.obj;
                        Bundle bundle = new Bundle();
                        bundle.putString("iap_id", iAPEvent3.getIAPCode());
                        if (BIAnalysisManager.this.mFacebookLogger == null) {
                            return;
                        } else {
                            BIAnalysisManager.this.mFacebookLogger.logPurchase(iAPEvent3.getAmount(), iAPEvent3.getCurrency(), bundle);
                        }
                        break;
                    case 5:
                        if (BIAnalysisManager.this.mFacebookLogger != null) {
                            BIAnalysisManager.this.mFacebookLogger.logEvent(AppEventsConstants.EVENT_NAME_COMPLETED_TUTORIAL);
                        }
                        if (BIAnalysisManager.this.mAppsFlyer != null) {
                            BIAnalysisManager.this.mAppsFlyer.trackEvent(BIAnalysisManager.this.mActivity, AFInAppEventType.TUTORIAL_COMPLETION, null);
                            return;
                        }
                        return;
                    case 6:
                        String str = (String) message.obj;
                        if (BIAnalysisManager.this.mFacebookLogger != null) {
                            Bundle bundle2 = new Bundle();
                            bundle2.putString("provider", str);
                            BIAnalysisManager.this.mFacebookLogger.logEvent(BIAnalysisManager.CUSTOM_EVENT_STRING_BANNER_SHOWN, bundle2);
                        }
                        if (BIAnalysisManager.this.mAppsFlyer != null) {
                            HashMap map3 = new HashMap();
                            map3.put(AFInAppEventParameterName.PARAM_1, str);
                            BIAnalysisManager.this.mAppsFlyer.trackEvent(BIAnalysisManager.this.mActivity, BIAnalysisManager.CUSTOM_EVENT_STRING_BANNER_SHOWN, map3);
                            return;
                        }
                        return;
                    case 7:
                        String str2 = (String) message.obj;
                        if (BIAnalysisManager.this.mFacebookLogger != null) {
                            Bundle bundle3 = new Bundle();
                            bundle3.putString("provider", str2);
                            BIAnalysisManager.this.mFacebookLogger.logEvent(BIAnalysisManager.CUSTOM_EVENT_STRING_INTERSTITIAL_SHOWN, bundle3);
                        }
                        if (BIAnalysisManager.this.mAppsFlyer != null) {
                            HashMap map4 = new HashMap();
                            map4.put(AFInAppEventParameterName.PARAM_1, str2);
                            BIAnalysisManager.this.mAppsFlyer.trackEvent(BIAnalysisManager.this.mActivity, BIAnalysisManager.CUSTOM_EVENT_STRING_INTERSTITIAL_SHOWN, map4);
                            return;
                        }
                        return;
                    case 8:
                        String str3 = (String) message.obj;
                        if (BIAnalysisManager.this.mFacebookLogger != null) {
                            Bundle bundle4 = new Bundle();
                            bundle4.putString("provider", str3);
                            BIAnalysisManager.this.mFacebookLogger.logEvent(BIAnalysisManager.CUSTOM_EVENT_STRING_VIDEO_SHOWN, bundle4);
                        }
                        if (BIAnalysisManager.this.mAppsFlyer != null) {
                            HashMap map5 = new HashMap();
                            map5.put(AFInAppEventParameterName.PARAM_1, str3);
                            BIAnalysisManager.this.mAppsFlyer.trackEvent(BIAnalysisManager.this.mActivity, BIAnalysisManager.CUSTOM_EVENT_STRING_VIDEO_SHOWN, map5);
                            return;
                        }
                        return;
                    case 9:
                        double dDoubleValue = ((Integer) message.obj).doubleValue();
                        if (BIAnalysisManager.this.mFacebookLogger != null) {
                            BIAnalysisManager.this.mFacebookLogger.logEvent(BIAnalysisManager.CUSTOM_EVENT_STRING_GEMS_TO_COINS, dDoubleValue);
                        }
                        if (BIAnalysisManager.this.mAppsFlyer != null) {
                            HashMap map6 = new HashMap();
                            map6.put(AFInAppEventParameterName.PARAM_1, Integer.valueOf((int) dDoubleValue));
                            BIAnalysisManager.this.mAppsFlyer.trackEvent(BIAnalysisManager.this.mActivity, BIAnalysisManager.CUSTOM_EVENT_STRING_GEMS_TO_COINS, map6);
                            return;
                        }
                        return;
                    case 10:
                        double dDoubleValue2 = ((Integer) message.obj).doubleValue();
                        if (BIAnalysisManager.this.mFacebookLogger != null) {
                            BIAnalysisManager.this.mFacebookLogger.logEvent(BIAnalysisManager.CUSTOM_EVENT_STRING_GEMS_USED, dDoubleValue2);
                        }
                        if (BIAnalysisManager.this.mAppsFlyer != null) {
                            HashMap map7 = new HashMap();
                            map7.put(AFInAppEventParameterName.PARAM_1, Integer.valueOf((int) dDoubleValue2));
                            BIAnalysisManager.this.mAppsFlyer.trackEvent(BIAnalysisManager.this.mActivity, BIAnalysisManager.CUSTOM_EVENT_STRING_GEMS_USED, map7);
                            return;
                        }
                        return;
                    case 11:
                        double dDoubleValue3 = ((Integer) message.obj).doubleValue();
                        if (BIAnalysisManager.this.mFacebookLogger != null) {
                            BIAnalysisManager.this.mFacebookLogger.logEvent(BIAnalysisManager.CUSTOM_EVENT_STRING_COINS_USED, dDoubleValue3);
                        }
                        if (BIAnalysisManager.this.mAppsFlyer != null) {
                            HashMap map8 = new HashMap();
                            map8.put(AFInAppEventParameterName.PARAM_1, Integer.valueOf((int) dDoubleValue3));
                            BIAnalysisManager.this.mAppsFlyer.trackEvent(BIAnalysisManager.this.mActivity, BIAnalysisManager.CUSTOM_EVENT_STRING_COINS_USED, map8);
                            return;
                        }
                        return;
                    case 12:
                        int iIntValue = ((Integer) message.obj).intValue();
                        if (BIAnalysisManager.this.mFacebookLogger != null) {
                            Bundle bundle5 = new Bundle();
                            bundle5.putInt("progress", iIntValue);
                            BIAnalysisManager.this.mFacebookLogger.logEvent(BIAnalysisManager.CUSTOM_EVENT_STRING_PROGRESS_CHANGED, bundle5);
                        }
                        if (BIAnalysisManager.this.mAppsFlyer != null) {
                            HashMap map9 = new HashMap();
                            map9.put(AFInAppEventParameterName.PARAM_1, Integer.valueOf(iIntValue));
                            BIAnalysisManager.this.mAppsFlyer.trackEvent(BIAnalysisManager.this.mActivity, BIAnalysisManager.CUSTOM_EVENT_STRING_PROGRESS_CHANGED, map9);
                            return;
                        }
                        return;
                    case 13:
                        String str4 = (String) message.obj;
                        if (BIAnalysisManager.this.mFacebookLogger != null) {
                            Bundle bundle6 = new Bundle();
                            bundle6.putString("vehicle", str4);
                            BIAnalysisManager.this.mFacebookLogger.logEvent(BIAnalysisManager.CUSTOM_EVENT_STRING_VEHICLE_UNLOCKED, bundle6);
                        }
                        if (BIAnalysisManager.this.mAppsFlyer != null) {
                            HashMap map10 = new HashMap();
                            map10.put(AFInAppEventParameterName.PARAM_1, str4);
                            BIAnalysisManager.this.mAppsFlyer.trackEvent(BIAnalysisManager.this.mActivity, BIAnalysisManager.CUSTOM_EVENT_STRING_VEHICLE_UNLOCKED, map10);
                            return;
                        }
                        return;
                    case 14:
                        VehicleUpgrade vehicleUpgrade = (VehicleUpgrade) message.obj;
                        String str5 = vehicleUpgrade.mVehicle;
                        int i2 = vehicleUpgrade.mUpgrade;
                        if (BIAnalysisManager.this.mFacebookLogger != null) {
                            Bundle bundle7 = new Bundle();
                            bundle7.putString("vehicle", str5);
                            bundle7.putInt("upgrade", i2);
                            BIAnalysisManager.this.mFacebookLogger.logEvent(BIAnalysisManager.CUSTOM_EVENT_STRING_VEHICLE_UPGRADE, bundle7);
                        }
                        if (BIAnalysisManager.this.mAppsFlyer != null) {
                            HashMap map11 = new HashMap();
                            map11.put(AFInAppEventParameterName.PARAM_1, str5);
                            map11.put(AFInAppEventParameterName.PARAM_2, vehicleUpgrade);
                            BIAnalysisManager.this.mAppsFlyer.trackEvent(BIAnalysisManager.this.mActivity, BIAnalysisManager.CUSTOM_EVENT_STRING_VEHICLE_UPGRADE, map11);
                            return;
                        }
                        return;
                    case 15:
                        int iIntValue2 = ((Integer) message.obj).intValue();
                        if (BIAnalysisManager.this.mFacebookLogger != null) {
                            Bundle bundle8 = new Bundle();
                            bundle8.putInt("level", iIntValue2);
                            BIAnalysisManager.this.mFacebookLogger.logEvent(BIAnalysisManager.CUSTOM_EVENT_STRING_LEVEL_UNLOCKED, bundle8);
                        }
                        if (BIAnalysisManager.this.mAppsFlyer != null) {
                            HashMap map12 = new HashMap();
                            map12.put(AFInAppEventParameterName.PARAM_1, Integer.valueOf(iIntValue2));
                            BIAnalysisManager.this.mAppsFlyer.trackEvent(BIAnalysisManager.this.mActivity, BIAnalysisManager.CUSTOM_EVENT_STRING_LEVEL_UNLOCKED, map12);
                            return;
                        }
                        return;
                    case 16:
                        int iIntValue3 = ((Integer) message.obj).intValue();
                        if (BIAnalysisManager.this.mFacebookLogger != null) {
                            Bundle bundle9 = new Bundle();
                            bundle9.putInt("rank", iIntValue3);
                            BIAnalysisManager.this.mFacebookLogger.logEvent(BIAnalysisManager.CUSTOM_EVENT_STRING_RANK_CHANGED, bundle9);
                        }
                        if (BIAnalysisManager.this.mAppsFlyer != null) {
                            HashMap map13 = new HashMap();
                            map13.put(AFInAppEventParameterName.PARAM_1, Integer.valueOf(iIntValue3));
                            BIAnalysisManager.this.mAppsFlyer.trackEvent(BIAnalysisManager.this.mActivity, BIAnalysisManager.CUSTOM_EVENT_STRING_RANK_CHANGED, map13);
                            return;
                        }
                        return;
                    case 17:
                        if (BIAnalysisManager.this.mFacebookLogger != null) {
                            BIAnalysisManager.this.mFacebookLogger.logEvent(BIAnalysisManager.CUSTOM_EVENT_STRING_SOCIAL_SHARE);
                        }
                        if (BIAnalysisManager.this.mAppsFlyer != null) {
                            BIAnalysisManager.this.mAppsFlyer.trackEvent(BIAnalysisManager.this.mActivity, AFInAppEventType.SHARE, null);
                            return;
                        }
                        return;
                    case 18:
                        if (BIAnalysisManager.this.mFacebookLogger != null) {
                            BIAnalysisManager.this.mFacebookLogger.logEvent(BIAnalysisManager.CUSTOM_EVENT_STRING_SOCIAL_SHARE_RECEIVED);
                        }
                        if (BIAnalysisManager.this.mAppsFlyer != null) {
                            BIAnalysisManager.this.mAppsFlyer.trackEvent(BIAnalysisManager.this.mActivity, BIAnalysisManager.CUSTOM_EVENT_STRING_SOCIAL_SHARE_RECEIVED, null);
                            return;
                        }
                        return;
                    case 19:
                        if (BIAnalysisManager.this.mFacebookLogger != null) {
                            BIAnalysisManager.this.mFacebookLogger.logEvent(BIAnalysisManager.CUSTOM_EVENT_STRING_SOCIAL_SHARE_RECEIVED_AT_FIRST_START);
                        }
                        if (BIAnalysisManager.this.mAppsFlyer != null) {
                            BIAnalysisManager.this.mAppsFlyer.trackEvent(BIAnalysisManager.this.mActivity, BIAnalysisManager.CUSTOM_EVENT_STRING_SOCIAL_SHARE_RECEIVED_AT_FIRST_START, null);
                            return;
                        }
                        return;
                    case 20:
                        int iIntValue4 = ((Integer) message.obj).intValue();
                        if (BIAnalysisManager.this.mFacebookLogger != null) {
                            Bundle bundle10 = new Bundle();
                            bundle10.putInt("numberOfFriends", iIntValue4);
                            BIAnalysisManager.this.mFacebookLogger.logEvent(BIAnalysisManager.CUSTOM_EVENT_STRING_NUMBER_OF_FRIENDS_CHANGED, bundle10);
                        }
                        if (BIAnalysisManager.this.mAppsFlyer != null) {
                            HashMap map14 = new HashMap();
                            map14.put(AFInAppEventParameterName.PARAM_1, Integer.valueOf(iIntValue4));
                            BIAnalysisManager.this.mAppsFlyer.trackEvent(BIAnalysisManager.this.mActivity, BIAnalysisManager.CUSTOM_EVENT_STRING_NUMBER_OF_FRIENDS_CHANGED, map14);
                            return;
                        }
                        return;
                    case 21:
                        final ValidateIapPurchase validateIapPurchase = (ValidateIapPurchase) message.obj;
                        if (BIAnalysisManager.this.mAppsFlyer != null) {
                            AppsFlyerLib unused4 = BIAnalysisManager.this.mAppsFlyer;
                            AppsFlyerLib.getInstance().registerValidatorListener(BIAnalysisManager.this.mActivity, new AppsFlyerInAppPurchaseValidatorListener() { // from class: com.fingersoft.liveops_sdk.BIAnalysis.BIAnalysisManager.EventHandler.1
                                @Override // com.appsflyer.AppsFlyerInAppPurchaseValidatorListener
                                public void onValidateInApp() {
                                    if (BIAnalysisManager.this.mFacebookLogger != null) {
                                        try {
                                            IAPEvent iAPEvent4 = BIAnalysisManager.this.new IAPEvent(Float.parseFloat(validateIapPurchase.mPrice), validateIapPurchase.mCurrency, "IAP_ID1");
                                            BIAnalysisManager.this.mFacebookLogger.logPurchase(iAPEvent4.getAmount(), iAPEvent4.getCurrency());
                                        } catch (Exception unused5) {
                                        }
                                    }
                                    validateIapPurchase.mListener.onIapSuccess();
                                }

                                @Override // com.appsflyer.AppsFlyerInAppPurchaseValidatorListener
                                public void onValidateInAppFailure(String str6) {
                                    validateIapPurchase.mListener.onIapFailed(str6);
                                }
                            });
                            AppsFlyerLib unused5 = BIAnalysisManager.this.mAppsFlyer;
                            AppsFlyerLib.getInstance().validateAndTrackInAppPurchase(BIAnalysisManager.this.mActivity, validateIapPurchase.mPublicKey, validateIapPurchase.mSignature, validateIapPurchase.mPurchaseData, validateIapPurchase.mPrice, validateIapPurchase.mCurrency, validateIapPurchase.mAdditionalParameters);
                            return;
                        }
                        return;
                    case 22:
                        String str6 = (String) message.obj;
                        if (BIAnalysisManager.this.mAppsFlyer != null) {
                            AppsFlyerLib unused6 = BIAnalysisManager.this.mAppsFlyer;
                            AppsFlyerLib.getInstance().setCustomerUserId(str6);
                            return;
                        }
                        return;
                    case 23:
                        String str7 = (String) message.obj;
                        if (BIAnalysisManager.this.mAppsFlyer != null) {
                            BIAnalysisManager.this.mAppsFlyer.trackEvent(BIAnalysisManager.this.mActivity, str7, null);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            } catch (Exception unused7) {
            }
        }
    }

    /* JADX INFO: renamed from: com.fingersoft.liveops_sdk.BIAnalysis.BIAnalysisManager$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$fingersoft$liveops_sdk$BIAnalysis$BIAnalysisManager$BIAnalysisManagerState = new int[BIAnalysisManagerState.values().length];

        static {
            try {
                $SwitchMap$com$fingersoft$liveops_sdk$BIAnalysis$BIAnalysisManager$BIAnalysisManagerState[BIAnalysisManagerState.NOT_INITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$fingersoft$liveops_sdk$BIAnalysis$BIAnalysisManager$BIAnalysisManagerState[BIAnalysisManagerState.INITIALIZED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    class IAPEvent {
        private BigDecimal mAmount;
        private Currency mCurrency;
        private String mIAPCode;

        public IAPEvent(float f, String str, String str2) {
            try {
                this.mAmount = BigDecimal.valueOf(f);
                this.mCurrency = Currency.getInstance(str);
                this.mIAPCode = str2;
            } catch (Exception unused) {
                if (this.mCurrency == null) {
                    this.mCurrency = Currency.getInstance("EUR");
                }
                if (this.mAmount == null) {
                    this.mAmount = BigDecimal.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                }
                if (this.mIAPCode == null) {
                    this.mIAPCode = "IAP_ID1";
                }
            }
        }

        public BigDecimal getAmount() {
            return this.mAmount;
        }

        public Currency getCurrency() {
            return this.mCurrency;
        }

        public float getFloatAmount() {
            return this.mAmount.floatValue();
        }

        public String getCurrencyCode() {
            return this.mCurrency.getCurrencyCode();
        }

        public String getIAPCode() {
            return this.mIAPCode;
        }
    }

    class VehicleUpgrade {
        public int mUpgrade;
        public String mVehicle;

        VehicleUpgrade() {
        }
    }

    class ValidateIapPurchase {
        HashMap<String, String> mAdditionalParameters;
        String mCurrency;
        IAPValidationListener mListener;
        String mPrice;
        String mPublicKey;
        String mPurchaseData;
        String mSignature;

        ValidateIapPurchase() {
        }
    }
}
