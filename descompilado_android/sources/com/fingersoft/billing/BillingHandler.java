package com.fingersoft.billing;

import android.content.Context;
import android.content.IntentFilter;
import com.fingersoft.billing.util.IabBroadcastReceiver;
import com.fingersoft.billing.util.IabHelper;
import com.fingersoft.billing.util.IabResult;
import com.fingersoft.billing.util.Inventory;
import com.fingersoft.billing.util.Purchase;
import com.fingersoft.billing.util.SkuDetails;
import com.fingersoft.hcr2.AppActivity;
import com.fingersoft.hcr2.BusinessIntelligence;
import com.fingersoft.hcr2.Config;
import com.fingersoft.hcr2.Log;
import com.fingersoft.liveops_sdk.settings.ApplicationSettings;
import com.tonyodev.fetch.FetchConst;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class BillingHandler {
    private static final int PURCHASE_REQUEST = 20020;
    private static final String TAG = "hcr2 - GoogleBilling";
    private static String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoz9i8hCjH8lO0HMGUthHiegEafTd8Zj2ueGYIlwzRYrym2j48q4zbkSPW0fYacEwI7RhTmDrONrH1Kv4aPS/7N3cK8U76fN6QrFT6rXoLwmAN0es8iZqB7c2r1qIOPWJDIKpv7kQ6HUZINLPcvAy/dFK8btFJOaMU6GRBS4g0eL2rH7voq44saWKOJ8QCd2MDSz/CzdNwpmkyA4VcBZY0YoueQbhYj9QSj474U1Mm1vMMUgtaj44TdIZUxd8pDIQUuA+0hM8wJSXoZZ4Iqaj7qRkgUle2olWTqDfWD6HE1XzlpDnUPuRl95vIqXd/hcIEMpko6vjvJ3lE3SnYj9LOwIDAQAB";
    private static IabHelper mHelper;
    private static Map<String, Integer> subscriptionPeriods;
    private static Map<String, IapDef> mIaps = Collections.synchronizedMap(new HashMap());
    private static Context mContext = null;
    private static int mAfMode = 0;
    private static int mFsMode = 0;
    private static int subscriptionGracePeriod = 604800;
    private static boolean mAsyncQueryInProgress = false;
    private static boolean mSetupCompleted = false;
    private static String mPendingIapId = "";
    static IabBroadcastReceiver mBroadcastReceiver = null;
    static IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() { // from class: com.fingersoft.billing.BillingHandler.4
        @Override // com.fingersoft.billing.util.IabHelper.QueryInventoryFinishedListener
        public void onQueryInventoryFinished(IabResult iabResult, Inventory inventory) {
            try {
                Log.d(BillingHandler.TAG, "onQueryInventoryFinished");
                boolean unused = BillingHandler.mAsyncQueryInProgress = false;
                if (BillingHandler.mHelper == null) {
                    return;
                }
                if (!iabResult.isFailure()) {
                    synchronized (BillingHandler.mIaps) {
                        for (String str : BillingHandler.mIaps.keySet()) {
                            if (inventory.hasDetails(str)) {
                                SkuDetails skuDetails = inventory.getSkuDetails(str);
                                String strReplaceAll = skuDetails.getPrice().replaceAll("[^0-9.,]", "");
                                AppActivity.onIapPriceUpdate(str, skuDetails.getPriceAmountMicros(), strReplaceAll + " " + skuDetails.getPriceCurrencyCode(), skuDetails.getPriceCurrencyCode());
                                ((IapDef) BillingHandler.mIaps.get(str)).priceMicros = skuDetails.getPriceAmountMicros();
                                ((IapDef) BillingHandler.mIaps.get(str)).currencyCode = skuDetails.getPriceCurrencyCode();
                                ((IapDef) BillingHandler.mIaps.get(str)).type = skuDetails.getType();
                                ((IapDef) BillingHandler.mIaps.get(str)).priceString = strReplaceAll.replaceAll(",", ".");
                                ((IapDef) BillingHandler.mIaps.get(str)).subscriptionPeriod = skuDetails.getSubscriptionPeriod();
                                Log.d(BillingHandler.TAG, "IapDef: " + str + ", " + skuDetails.getPriceAmountMicros() + ", " + skuDetails.getPriceCurrencyCode() + ", " + ((IapDef) BillingHandler.mIaps.get(str)).priceString + ", " + ((IapDef) BillingHandler.mIaps.get(str)).type);
                            }
                            Purchase purchase = inventory.getPurchase(str);
                            if (purchase == null) {
                                if (((IapDef) BillingHandler.mIaps.get(str)).type.equals(IabHelper.ITEM_TYPE_SUBS)) {
                                    AppActivity.onSubscriptionInactive(str);
                                }
                            } else {
                                Log.d(BillingHandler.TAG, "Purchase: " + purchase);
                                if (((IapDef) BillingHandler.mIaps.get(str)).type.equals(IabHelper.ITEM_TYPE_SUBS)) {
                                    if (!AppActivity.isSubscriptionExpired()) {
                                        if (purchase.isAutoRenewing() != AppActivity.isSubscriptionAutoRenew()) {
                                            Log.d(BillingHandler.TAG, "Subscription auto-renew state was changed. Re-calculate subscription expiration timestamp");
                                        } else {
                                            Log.d(BillingHandler.TAG, "Subscription iap in inventory, but subscription hasn't expired yet. No need to verify this purchase now");
                                        }
                                    } else {
                                        Log.d(BillingHandler.TAG, "Subscription iap in inventory and subscription has expired. Verify subscription purchase to renew subscription on client");
                                    }
                                }
                                BillingHandler.startVerifyDeveloperPayload(purchase);
                            }
                        }
                        AppActivity.onAllIapPricesUpdated();
                    }
                    return;
                }
                Log.d(BillingHandler.TAG, "Failed to query IAP inventory: " + iabResult);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    static IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() { // from class: com.fingersoft.billing.BillingHandler.9
        @Override // com.fingersoft.billing.util.IabHelper.OnIabPurchaseFinishedListener
        public void onIabPurchaseFinished(IabResult iabResult, Purchase purchase) {
            String sku;
            try {
                Log.d(BillingHandler.TAG, "Purchase finished: " + iabResult + ", purchase: " + purchase + ", response: " + iabResult.getResponse());
                if (purchase == null) {
                    sku = BillingHandler.mPendingIapId;
                } else {
                    sku = purchase.getSku();
                }
                if (iabResult.getResponse() < 0) {
                    sku = "";
                }
                if (BillingHandler.mHelper == null) {
                    AppActivity.onIapError(sku, "purchase_finished_iabhelper_null");
                    String unused = BillingHandler.mPendingIapId = "";
                    return;
                }
                if (iabResult.isCancel()) {
                    Log.i(BillingHandler.TAG, "User cancelled purchase: " + iabResult);
                    AppActivity.onIapCancelled(sku);
                    String unused2 = BillingHandler.mPendingIapId = "";
                    return;
                }
                if (iabResult.getResponse() == 7) {
                    Log.d(BillingHandler.TAG, "Item already owned, querying inventory to consume");
                    BillingHandler.startInventoryQuery();
                    String unused3 = BillingHandler.mPendingIapId = "";
                } else {
                    if (!iabResult.isFailure()) {
                        synchronized (BillingHandler.mIaps) {
                            BillingHandler.startVerifyDeveloperPayload(purchase);
                            String unused4 = BillingHandler.mPendingIapId = "";
                        }
                        return;
                    }
                    Log.e(BillingHandler.TAG, "Error purchasing: " + iabResult);
                    AppActivity.onIapError(sku, iabResult.getMessage());
                    String unused5 = BillingHandler.mPendingIapId = "";
                }
            } catch (Exception e) {
                e.printStackTrace();
                AppActivity.onIapError("", e.getMessage());
                String unused6 = BillingHandler.mPendingIapId = "";
            }
        }
    };
    static IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() { // from class: com.fingersoft.billing.BillingHandler.10
        @Override // com.fingersoft.billing.util.IabHelper.OnConsumeFinishedListener
        public void onConsumeFinished(Purchase purchase, IabResult iabResult) {
            try {
                Log.d(BillingHandler.TAG, "Consumption finished. Purchase: " + purchase + ", result: " + iabResult);
                if (BillingHandler.mHelper == null) {
                    return;
                }
                String sku = purchase.getSku();
                if (iabResult.isSuccess()) {
                    synchronized (BillingHandler.mIaps) {
                        if (BillingHandler.mIaps.containsKey(sku)) {
                            if (AppActivity.getAdSettingInt("late_af_check", 1) == 1 && !purchase.isVerified() && BillingHandler.mAfMode == 3) {
                                HashMap map = new HashMap();
                                map.put("id", purchase.getSku());
                                AppActivity.trackEvent("iap_late_validation_failed", map);
                                Log.d(BillingHandler.TAG, "IAP late validation failed");
                                return;
                            }
                            Log.d(BillingHandler.TAG, "Consume success: " + sku);
                            AppActivity.onIapValidationSuccess(purchase.getSku(), purchase.getToken(), purchase.isVerified());
                        } else {
                            Log.d(BillingHandler.TAG, "Internal error: SKU not found");
                        }
                    }
                } else {
                    Log.d(BillingHandler.TAG, "Failed to consume: " + sku);
                    Log.e(BillingHandler.TAG, iabResult.getMessage());
                }
                Log.d(BillingHandler.TAG, "End consumption flow");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    static IabHelper.OnConsumeFinishedListener mConsumeFinishedListenerWithAfTracking = new IabHelper.OnConsumeFinishedListener() { // from class: com.fingersoft.billing.BillingHandler.11
        @Override // com.fingersoft.billing.util.IabHelper.OnConsumeFinishedListener
        public void onConsumeFinished(Purchase purchase, IabResult iabResult) {
            try {
                Log.d(BillingHandler.TAG, "Consumption finished. Purchase: " + purchase + ", result: " + iabResult);
                if (BillingHandler.mHelper == null) {
                    return;
                }
                String sku = purchase.getSku();
                if (iabResult.isSuccess()) {
                    synchronized (BillingHandler.mIaps) {
                        if (BillingHandler.mIaps.containsKey(sku)) {
                            if (AppActivity.getAdSettingInt("late_af_check", 1) == 1 && !purchase.isVerified() && BillingHandler.mAfMode == 3) {
                                HashMap map = new HashMap();
                                map.put("id", purchase.getSku());
                                AppActivity.trackEvent("iap_late_validation_failed", map);
                                Log.d(BillingHandler.TAG, "IAP late validation failed");
                                return;
                            }
                            Log.d(BillingHandler.TAG, "Consume success: " + sku);
                            AppActivity.onIapValidationSuccess(purchase.getSku(), purchase.getToken(), purchase.isVerified());
                            if (purchase.isVerified() && BillingHandler.mIaps.containsKey(sku)) {
                                IapDef iapDef = (IapDef) BillingHandler.mIaps.get(sku);
                                HashMap map2 = new HashMap();
                                map2.put("id", purchase.getSku());
                                AppActivity.trackEvent("iap_appsflyer_event", map2);
                                double d = iapDef.priceMicros;
                                Double.isNaN(d);
                                BusinessIntelligence.sendAppsFlyerIAPEvent((float) (d / 1000000.0d), iapDef.currencyCode, sku);
                            }
                        } else {
                            Log.d(BillingHandler.TAG, "Internal error: SKU not found");
                        }
                    }
                } else {
                    Log.d(BillingHandler.TAG, "Failed to consume: " + sku);
                    Log.e(BillingHandler.TAG, iabResult.getMessage());
                }
                Log.d(BillingHandler.TAG, "End consumption flow");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public void onResume() {
    }

    public IabHelper getHelper() {
        return mHelper;
    }

    public BillingHandler(Context context) {
        mContext = context;
        mHelper = new IabHelper(context, base64EncodedPublicKey);
        mHelper.enableDebugLogging(false);
        subscriptionPeriods = new HashMap();
        AppActivity.getInstance();
        if (AppActivity.getAdServerAddress().contains(Config.AD_SERVER_ADDRESS_STAGING)) {
            Log.d(TAG, "Use test subscription periods");
            subscriptionPeriods.put("P1W", 300);
            subscriptionPeriods.put("P1M", 300);
            subscriptionPeriods.put("P3M", 600);
            subscriptionPeriods.put("P6M", Integer.valueOf(FetchConst.STATUS_QUEUED));
            subscriptionGracePeriod = 300;
        } else {
            Log.d(TAG, "Use actual subscription periods");
            subscriptionPeriods.put("P1W", 604800);
            subscriptionPeriods.put("P1M", 2678400);
            subscriptionPeriods.put("P3M", 8035200);
            subscriptionPeriods.put("P6M", 16070400);
        }
        if (!mSetupCompleted || mIaps.size() <= 0) {
            return;
        }
        setupIaps(null);
    }

    public void destroy() {
        Context context;
        IabBroadcastReceiver iabBroadcastReceiver = mBroadcastReceiver;
        if (iabBroadcastReceiver != null && (context = mContext) != null) {
            context.unregisterReceiver(iabBroadcastReceiver);
        }
        IabHelper iabHelper = mHelper;
        if (iabHelper != null) {
            iabHelper.dispose();
            mHelper = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void addIaps(String[] strArr) {
        synchronized (mIaps) {
            for (int i = 0; i < strArr.length; i++) {
                if (!mIaps.containsKey(strArr[i])) {
                    Log.d(TAG, "Adding iap: " + strArr[i]);
                    IapDef iapDef = new IapDef();
                    iapDef.sku = strArr[i];
                    mIaps.put(strArr[i], iapDef);
                }
            }
        }
    }

    public static void startInventoryQuery() {
        try {
            if (mSetupCompleted) {
                synchronized (mIaps) {
                    try {
                        mAsyncQueryInProgress = true;
                        Log.d(TAG, "Starting async query");
                        ArrayList arrayList = new ArrayList();
                        arrayList.addAll(mIaps.keySet());
                        mHelper.queryInventoryAsync(true, arrayList, mGotInventoryListener);
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        } catch (IllegalStateException e) {
            AppActivity.onIapError("inventory_query", e.getMessage());
        } catch (Exception e2) {
            e2.printStackTrace();
            AppActivity.onIapError("inventory_query", e2.getMessage());
        }
    }

    public static void querySpecialOffers(final String[] strArr) {
        try {
            new Thread() { // from class: com.fingersoft.billing.BillingHandler.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    BillingHandler.addIaps(strArr);
                    int i = 1000;
                    boolean z = false;
                    while (!z) {
                        try {
                            if (!BillingHandler.mSetupCompleted || BillingHandler.mAsyncQueryInProgress) {
                                Log.d(BillingHandler.TAG, "Async operation already in progress, sleeping...");
                                Thread.sleep(i);
                                i += 2000;
                                if (i > 20000) {
                                    return;
                                }
                            } else {
                                Log.d(BillingHandler.TAG, "Setup complete and no async query running");
                                boolean unused = BillingHandler.mAsyncQueryInProgress = true;
                                AppActivity.getInstance().getHandler().post(new Runnable() { // from class: com.fingersoft.billing.BillingHandler.1.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        BillingHandler.startInventoryQuery();
                                    }
                                });
                                z = true;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setupIaps(String[] strArr) {
        try {
            if (mSetupCompleted) {
                AppActivity.getInstance().getHandler().post(new Runnable() { // from class: com.fingersoft.billing.BillingHandler.2
                    @Override // java.lang.Runnable
                    public void run() {
                        BillingHandler.startInventoryQuery();
                    }
                });
                return;
            }
            if (strArr != null) {
                addIaps(strArr);
            }
            Log.d(TAG, "Starting IAP setup");
            mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() { // from class: com.fingersoft.billing.BillingHandler.3
                @Override // com.fingersoft.billing.util.IabHelper.OnIabSetupFinishedListener
                public void onIabSetupFinished(IabResult iabResult) {
                    try {
                        if (iabResult.isSuccess()) {
                            if (BillingHandler.mHelper == null) {
                                return;
                            }
                            BillingHandler.mBroadcastReceiver = new IabBroadcastReceiver(new IabBroadcastReceiver.IabBroadcastListener() { // from class: com.fingersoft.billing.BillingHandler.3.1
                                @Override // com.fingersoft.billing.util.IabBroadcastReceiver.IabBroadcastListener
                                public void receivedBroadcast() {
                                    try {
                                        Log.d(BillingHandler.TAG, "Broadcast received: Inventory items have changed. Querying inventory.");
                                        BillingHandler.mHelper.queryInventoryAsync(BillingHandler.mGotInventoryListener);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            IntentFilter intentFilter = new IntentFilter(IabBroadcastReceiver.ACTION);
                            if (BillingHandler.mContext != null) {
                                BillingHandler.mContext.registerReceiver(BillingHandler.mBroadcastReceiver, intentFilter);
                            }
                            Log.d(BillingHandler.TAG, "Setup successful. Querying inventory.");
                            boolean unused = BillingHandler.mSetupCompleted = true;
                            BillingHandler.startInventoryQuery();
                            return;
                        }
                        Log.e(BillingHandler.TAG, "Problem setting up in-app billing: " + iabResult);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void purchase(AppActivity appActivity, String str) {
        Log.d(TAG, "Launching purchase flow for: " + str);
        mPendingIapId = str;
        String strGeneratePayload = FsValidation.generatePayload(appActivity.getAnalyticsId());
        if (mIaps.containsKey(str) && mIaps.get(str).type.equals(IabHelper.ITEM_TYPE_SUBS)) {
            mHelper.launchSubscriptionPurchaseFlow(appActivity, str, PURCHASE_REQUEST, mPurchaseFinishedListener, strGeneratePayload);
        } else {
            mHelper.launchPurchaseFlow(appActivity, str, PURCHASE_REQUEST, mPurchaseFinishedListener, strGeneratePayload);
        }
    }

    static void startAppsFlyerValidation(Purchase purchase) {
        IapDef iapDef = mIaps.get(purchase.getSku());
        BusinessIntelligence.verifyPurchase(base64EncodedPublicKey, purchase, iapDef.priceString, iapDef.currencyCode, new ValidationCallback() { // from class: com.fingersoft.billing.BillingHandler.5
            @Override // com.fingersoft.billing.ValidationCallback
            public void onValidateSuccess(Purchase purchase2) {
                Log.d(BillingHandler.TAG, "AF validated ok");
                purchase2.setVerified(true);
                if (purchase2.getItemType().equals(IabHelper.ITEM_TYPE_SUBS)) {
                    BillingHandler.onSubscriptionVerifySuccess(purchase2);
                } else {
                    BillingHandler.onIapVerifySuccess(purchase2, false);
                }
            }

            @Override // com.fingersoft.billing.ValidationCallback
            public void onValidateFailed(Purchase purchase2, String str) {
                if (BillingHandler.mAfMode == 3) {
                    if (BillingHandler.mFsMode == 2 || BillingHandler.mFsMode == 3) {
                        Log.d(BillingHandler.TAG, "AF validate failed! Trying FS validate");
                        BillingHandler.startFsValidation(purchase2);
                        return;
                    }
                    Log.d(BillingHandler.TAG, "AF validate failed!");
                    HashMap map = new HashMap();
                    map.put("id", purchase2.getSku());
                    map.put("error", str);
                    AppActivity.trackEvent("iap_validation_failed", map);
                    AppActivity.onIapValidationFailed(purchase2.getSku(), purchase2.getToken(), str);
                    return;
                }
                Log.d(BillingHandler.TAG, "AF validate failed, giving reward anyway");
                if (purchase2.getItemType().equals(IabHelper.ITEM_TYPE_SUBS)) {
                    BillingHandler.onSubscriptionVerifySuccess(purchase2);
                } else {
                    BillingHandler.onIapVerifySuccess(purchase2, false);
                }
            }

            @Override // com.fingersoft.billing.ValidationCallback
            public void onValidateError(Purchase purchase2, String str) {
                if (BillingHandler.mAfMode == 3) {
                    if (BillingHandler.mFsMode == 2 || BillingHandler.mFsMode == 3) {
                        Log.d(BillingHandler.TAG, "AF validate error! Trying FS validate");
                        BillingHandler.startFsValidation(purchase2);
                        return;
                    }
                    Log.d(BillingHandler.TAG, "AF validate error!");
                    AppActivity.onIapError(purchase2.getSku(), str);
                    HashMap map = new HashMap();
                    map.put("id", purchase2.getSku());
                    map.put("error", str);
                    AppActivity.trackEvent("af_validation_error", map);
                    return;
                }
                Log.d(BillingHandler.TAG, "AF validate error, giving reward anyway");
                if (purchase2.getItemType().equals(IabHelper.ITEM_TYPE_SUBS)) {
                    BillingHandler.onSubscriptionVerifySuccess(purchase2);
                } else {
                    BillingHandler.onIapVerifySuccess(purchase2, false);
                }
            }
        });
    }

    static void startFsValidation(Purchase purchase) {
        FsValidation.validate(purchase, new ValidationCallback() { // from class: com.fingersoft.billing.BillingHandler.6
            @Override // com.fingersoft.billing.ValidationCallback
            public void onValidateSuccess(Purchase purchase2) {
                Log.d(BillingHandler.TAG, "FS validated ok");
                purchase2.setVerified(true);
                if (purchase2.getItemType().equals(IabHelper.ITEM_TYPE_SUBS)) {
                    BillingHandler.onSubscriptionVerifySuccess(purchase2);
                } else {
                    BillingHandler.onIapVerifySuccess(purchase2, true);
                }
            }

            @Override // com.fingersoft.billing.ValidationCallback
            public void onValidateFailed(Purchase purchase2, String str) {
                if (BillingHandler.mFsMode == 3) {
                    Log.d(BillingHandler.TAG, "FS validate failed!");
                    AppActivity.onIapValidationFailed(purchase2.getSku(), purchase2.getToken(), str);
                    BillingHandler.mHelper.consumeAsync(purchase2, (IabHelper.OnConsumeFinishedListener) null);
                    HashMap map = new HashMap();
                    map.put("id", purchase2.getSku());
                    map.put("error", str);
                    AppActivity.trackEvent("iap_validation_failed", map);
                    return;
                }
                Log.d(BillingHandler.TAG, "FS validate failed, giving reward anyway");
                if (purchase2.getItemType().equals(IabHelper.ITEM_TYPE_SUBS)) {
                    BillingHandler.onSubscriptionVerifySuccess(purchase2);
                } else {
                    BillingHandler.onIapVerifySuccess(purchase2, false);
                }
            }

            @Override // com.fingersoft.billing.ValidationCallback
            public void onValidateError(Purchase purchase2, String str) {
                if (BillingHandler.mFsMode == 3) {
                    Log.d(BillingHandler.TAG, "FS validate error!");
                    AppActivity.onIapError(purchase2.getSku(), str);
                    HashMap map = new HashMap();
                    map.put("id", purchase2.getSku());
                    map.put("error", str);
                    AppActivity.trackEvent("fs_validation_error", map);
                    return;
                }
                Log.d(BillingHandler.TAG, "FS validate failed, giving reward anyway");
                if (purchase2.getItemType().equals(IabHelper.ITEM_TYPE_SUBS)) {
                    BillingHandler.onSubscriptionVerifySuccess(purchase2);
                } else {
                    BillingHandler.onIapVerifySuccess(purchase2, false);
                }
            }
        });
    }

    static void startVerifyDeveloperPayload(Purchase purchase) {
        try {
            Log.d(TAG, "JSON: " + purchase.getOriginalJson());
            if (!purchase.getItemType().equals(IabHelper.ITEM_TYPE_SUBS)) {
                AppActivity.onIapPurchased(purchase.getSku(), purchase.getToken());
            }
            mAfMode = ApplicationSettings.getValueInt(mContext, "af_mode", 0);
            mFsMode = ApplicationSettings.getValueInt(mContext, "fs_mode", 3);
            Log.d(TAG, "AF mode: " + mAfMode);
            Log.d(TAG, "FS mode: " + mFsMode);
            if (mAfMode != 2 && mAfMode != 3) {
                if (mFsMode != 2 && mFsMode != 3) {
                    Log.d(TAG, "Validation disabled, giving reward");
                    if (purchase.getItemType().equals(IabHelper.ITEM_TYPE_SUBS)) {
                        onSubscriptionVerifySuccess(purchase);
                        return;
                    } else {
                        onIapVerifySuccess(purchase, false);
                        return;
                    }
                }
                Log.d(TAG, "Starting FS validation");
                startFsValidation(purchase);
                return;
            }
            Log.d(TAG, "Starting AF validation");
            startAppsFlyerValidation(purchase);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                Log.d(TAG, "Validation exception, giving reward");
                if (purchase.getItemType().equals(IabHelper.ITEM_TYPE_SUBS)) {
                    onSubscriptionVerifySuccess(purchase);
                } else {
                    onIapVerifySuccess(purchase, false);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                AppActivity.onIapError(purchase.getSku(), e2.getMessage());
            }
        }
    }

    public static void onIapVerifySuccess(final Purchase purchase, final boolean z) {
        Log.d(TAG, "Purchase verified: " + purchase.getSku());
        Log.d(TAG, "Purchase token: " + purchase.getToken());
        AppActivity.getInstance().getHandler().post(new Runnable() { // from class: com.fingersoft.billing.BillingHandler.7
            @Override // java.lang.Runnable
            public void run() {
                try {
                    synchronized (BillingHandler.mIaps) {
                        try {
                            Log.d(BillingHandler.TAG, "Finding sku: " + purchase.getSku());
                            for (String str : BillingHandler.mIaps.keySet()) {
                                if (purchase.getSku().equals(str)) {
                                    try {
                                        Log.d(BillingHandler.TAG, "Consuming: " + str);
                                        if (z) {
                                            BillingHandler.mHelper.consumeAsync(purchase, BillingHandler.mConsumeFinishedListenerWithAfTracking);
                                        } else {
                                            BillingHandler.mHelper.consumeAsync(purchase, BillingHandler.mConsumeFinishedListener);
                                        }
                                        return;
                                    } catch (IllegalStateException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    public static void onSubscriptionVerifySuccess(final Purchase purchase) {
        Log.d(TAG, "Subscription purchase verified: " + purchase.getSku());
        Log.d(TAG, "Subscription purchase token: " + purchase.getToken());
        AppActivity.getInstance().getHandler().post(new Runnable() { // from class: com.fingersoft.billing.BillingHandler.8
            @Override // java.lang.Runnable
            public void run() {
                try {
                    synchronized (BillingHandler.mIaps) {
                        Log.d(BillingHandler.TAG, "Finding sku: " + purchase.getSku());
                        for (String str : BillingHandler.mIaps.keySet()) {
                            if (purchase.getSku().equals(str)) {
                                long purchaseTime = (purchase.getPurchaseTime() / 1000) + ((long) ((Integer) BillingHandler.subscriptionPeriods.get(((IapDef) BillingHandler.mIaps.get(str)).subscriptionPeriod)).intValue());
                                int timeStampNow = AppActivity.getTimeStampNow();
                                while (purchaseTime < timeStampNow) {
                                    purchaseTime += (long) ((Integer) BillingHandler.subscriptionPeriods.get(((IapDef) BillingHandler.mIaps.get(str)).subscriptionPeriod)).intValue();
                                }
                                if (purchase.isAutoRenewing()) {
                                    Log.d(BillingHandler.TAG, "Add " + BillingHandler.subscriptionGracePeriod + " seconds grace period to subscription");
                                    purchaseTime += (long) BillingHandler.subscriptionGracePeriod;
                                }
                                long j = purchaseTime;
                                Log.d(BillingHandler.TAG, "Subscription expiration timestamp: " + j + " Timestamp now: " + timeStampNow);
                                AppActivity.onSubscriptionValidated(str, purchase.getToken(), j, purchase.isAutoRenewing(), purchase.isVerified());
                                return;
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void onSubscriptionVerifyFailed(Purchase purchase) {
        Log.d(TAG, "Failed to verify subscription purchase: " + purchase.getSku());
        AppActivity.onIapError(purchase.getSku(), "subscription_verify_failed");
    }
}
