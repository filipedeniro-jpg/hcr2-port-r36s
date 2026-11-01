package com.fingersoft.hcr2;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.Signature;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.MimeTypeMap;
import android.widget.RelativeLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;
import com.amazon.device.ads.AdWebViewClient;
import com.amazon.device.ads.WebRequest;
import com.crashlytics.android.Crashlytics;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.model.AppInviteContent;
import com.facebook.share.widget.AppInviteDialog;
import com.fingersoft.account_sdk.Friend;
import com.fingersoft.ads_sdk.advertising.AdManager;
import com.fingersoft.ads_sdk.advertising.IFSAdSdkListener;
import com.fingersoft.billing.BillingHandler;
import com.fingersoft.hcr2.firebase.Firebase;
import com.fingersoft.hcr2.firebase.FirebaseAdListener;
import com.fingersoft.hcr2.firebase.FirebaseListener;
import com.fingersoft.hcr2.firebase.FirebaseMessageReceiver;
import com.fingersoft.liveops_sdk.LiveopsManager;
import com.fingersoft.liveops_sdk.crosspromotions.CrossPromotionManager;
import com.fingersoft.liveops_sdk.eula.EULAListener;
import com.fingersoft.liveops_sdk.news.GameNewsListener;
import com.fingersoft.liveops_sdk.settings.ApplicationSettings;
import com.fingersoft.liveops_sdk.settings.SettingsListener;
import com.fingersoft.liveops_sdk.utils.ISdkInitListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.scottyab.rootbeer.RootBeer;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.settings.AppSettingsData;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.Tracking;
import org.cocos2dx.lib.Cocos2dxActivity;
import org.cocos2dx.lib.Cocos2dxGLSurfaceView;
import org.cocos2dx.lib.Cocos2dxHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AppActivity extends Cocos2dxActivity implements ISdkInitListener, IFSAdSdkListener, EULAListener, FirebaseAdListener {
    public static final String TAG = "hcr2";
    private static AppActivity mActivityInstance;
    private Cocos2dxGLSurfaceView glSurfaceView;
    private Uri pendingAppLink;
    private static Handler mHandler = new Handler();
    private static BillingHandler mBillingHandler = null;
    private static BusinessIntelligence mBI = null;
    private static GameServices mGameServices = null;
    private static Firebase mFirebase = null;
    private static AdManager mAdManager = null;
    private static LiveopsManager mLiveopsManager = null;
    private static boolean mFirebaseRemoteConfigPending = false;
    private static boolean mNewsDataPending = false;
    private static int mNewsDataNewItems = 0;
    private static String mNewsDataPopupId = "";
    private static String mAnalyticsUid = null;
    private static float mKeyboardHeightRatio = 0.0f;
    private static boolean mGDPRStatus = false;
    private boolean mFinishedLaunching = false;
    private boolean mFinishedAssetLoader = false;
    private boolean mNewAdSettingsPending = false;
    private boolean mIapInventoryQueryPending = false;
    private boolean mIsNetwrokConnected = true;
    private BroadcastReceiver ConnectivityReceiver = new BroadcastReceiver() { // from class: com.fingersoft.hcr2.AppActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean connectivityStatus = AppActivity.getConnectivityStatus(AppActivity.mActivityInstance.getApplicationContext());
            Log.d("hcr2", "Network status changed to " + connectivityStatus + " was " + AppActivity.this.mIsNetwrokConnected);
            AppActivity.onNetworkConnectivityChanged(connectivityStatus);
            if (connectivityStatus && !AppActivity.mFirebase.getAdsInstance().hasVideoCampaigns() && AppActivity.this.hasFinishedLaunching() && connectivityStatus != AppActivity.this.mIsNetwrokConnected) {
                Log.d("hcr2", "Load ads after network offline");
                AppActivity.mFirebase.getAdsInstance().loadVideoAd();
            }
            AppActivity.this.mIsNetwrokConnected = connectivityStatus;
        }
    };

    private void checkForUpdates() {
    }

    public static String getGcmToken() {
        return "";
    }

    public static String getMarketVariation() {
        return "google";
    }

    public static String getPlayerName() {
        return "";
    }

    public static native int getTimeStampNow();

    public static native int getVideoGroupCount();

    public static native void handleAppLink(String str, String str2);

    public static void hideAds() {
    }

    public static native boolean isMyGamezBuild();

    public static native boolean isSubscriptionAutoRenew();

    public static native boolean isSubscriptionExpired();

    public static native void notifyInterstitialDismissed();

    public static native void notifyInterstitialFailed();

    public static native void notifyInterstitialInteract();

    public static native void notifyInterstitialLoaded();

    public static native void notifyInterstitialShow();

    public static native void notifyVideoAdCanceled();

    public static native void notifyVideoAdCompleted();

    public static native void notifyVideoAdFailed();

    public static native void notifyVideoAdStarted(String str);

    public static native void onAdSettingsReceived();

    public static native void onAllIapPricesUpdated();

    public static native void onConfigurationCompleted();

    public static native void onDataRead(boolean z, int i, int i2, String str, String str2, int i3);

    public static native void onDataWrite(boolean z);

    public static native void onFirebaseRemoteConfigReceived();

    public static native void onFriendDataRead(int i, Friend[] friendArr);

    public static native void onGameServicesConnected();

    public static native void onGameServicesFailed();

    public static native void onGameServicesManualDisconnect();

    public static native void onIMEActive(float f);

    public static native void onIapCancelled(String str);

    public static native void onIapError(String str, String str2);

    public static native void onIapPriceUpdate(String str, long j, String str2, String str3);

    public static native void onIapPurchased(String str, String str2);

    public static native void onIapValidationFailed(String str, String str2, String str3);

    public static native void onIapValidationSuccess(String str, String str2, boolean z);

    public static native void onJsonReceived(String str, String str2);

    public static native void onLoginCancel();

    public static native void onLoginError();

    public static native void onLoginSuccess(int i);

    public static native void onLogout();

    public static native void onNetworkConnectivityChanged(boolean z);

    public static native void onNewsDataAvailable(String str, int i);

    public static native void onRetryConnectionClicked();

    public static native void onSubscriptionExpired();

    public static native void onSubscriptionInactive(String str);

    public static native void onSubscriptionValidated(String str, String str2, long j, boolean z, boolean z2);

    public static void trackRevenue(double d, String str, HashMap<String, String> map) {
    }

    private void unregisterManagers() {
    }

    @Override // com.fingersoft.hcr2.firebase.FirebaseAdListener
    public void onVideoAdAvailable() {
    }

    public static AppActivity getInstance() {
        return mActivityInstance;
    }

    public Handler getHandler() {
        return mHandler;
    }

    public static boolean isFirebaseEnabled() {
        return getAdSettingInt("firebase_enabled", 1) == 1;
    }

    @Override // org.cocos2dx.lib.Cocos2dxActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        Log.d("hcr2", "onCreate");
        super.onCreate(bundle);
        checkForUpdates();
        mActivityInstance = this;
        RelativeLayout relativeLayout = new RelativeLayout(this);
        mLiveopsManager = new LiveopsManager(this, relativeLayout, this, null);
        mFirebase = new Firebase(this);
        final View decorView = mActivityInstance.getWindow().getDecorView();
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.fingersoft.hcr2.AppActivity.2
            private int lastVisibleDecorViewHeight;
            private final Rect windowVisibleDisplayFrame = new Rect();

            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                decorView.getWindowVisibleDisplayFrame(this.windowVisibleDisplayFrame);
                int iHeight = this.windowVisibleDisplayFrame.height();
                int i = this.lastVisibleDecorViewHeight;
                if (i != 0) {
                    if (i > iHeight + IronSourceConstants.REWARDED_VIDEO_DAILY_CAPPED) {
                        float unused = AppActivity.mKeyboardHeightRatio = (decorView.getHeight() - this.windowVisibleDisplayFrame.bottom) / decorView.getHeight();
                        AppActivity.onIMEActive(AppActivity.mKeyboardHeightRatio);
                    } else if (decorView.getHeight() - this.windowVisibleDisplayFrame.bottom == 0) {
                        float unused2 = AppActivity.mKeyboardHeightRatio = 0.0f;
                        AppActivity.onIMEActive(0.0f);
                    }
                }
                this.lastVisibleDecorViewHeight = iHeight;
            }
        });
        mLiveopsManager.withLogging(Boolean.valueOf(Config.ENABLE_ANDROID_LOG)).withBaseServerAddress(Config.AD_SERVER_ADDRESS_PRODUCTION).withGameNewsListener(new GameNewsListener() { // from class: com.fingersoft.hcr2.AppActivity.4
            @Override // com.fingersoft.liveops_sdk.news.GameNewsListener
            public void onNewsDataAvailable(JSONObject jSONObject) {
                try {
                    if (jSONObject.has("popup")) {
                        try {
                            JSONArray jSONArray = jSONObject.getJSONArray("popup");
                            if (jSONArray.length() > 0) {
                                String unused = AppActivity.mNewsDataPopupId = (String) jSONArray.get(0);
                            }
                        } catch (JSONException unused2) {
                        }
                    }
                    if (jSONObject.has(AppSettingsData.STATUS_NEW)) {
                        try {
                            int unused3 = AppActivity.mNewsDataNewItems = jSONObject.getInt(AppSettingsData.STATUS_NEW);
                        } catch (JSONException unused4) {
                        }
                    }
                    if (AppActivity.this.mFinishedAssetLoader) {
                        AppActivity.onNewsDataAvailable(AppActivity.mNewsDataPopupId, AppActivity.mNewsDataNewItems);
                    } else {
                        boolean unused5 = AppActivity.mNewsDataPending = true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).withSettingsListener(new SettingsListener() { // from class: com.fingersoft.hcr2.AppActivity.3
            @Override // com.fingersoft.liveops_sdk.settings.SettingsListener
            public void onServerTimeChanged(long j) {
            }

            @Override // com.fingersoft.liveops_sdk.settings.SettingsListener
            public void onSettingsRefreshed() {
                try {
                    if (!AppActivity.this.mFinishedAssetLoader) {
                        AppActivity.this.mNewAdSettingsPending = true;
                    } else {
                        AppActivity.onAdSettingsReceived();
                    }
                    if (AppActivity.isFirebaseEnabled() && AppActivity.mFirebase.getAnalyticsInstance() == null && AppActivity.mAnalyticsUid != null) {
                        AppActivity.startAnalytics(AppActivity.mAnalyticsUid);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            PlatformConfig.setupAds(mFirebase, this, getDeviceScreenWidth());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mLiveopsManager.manage();
        mGameServices = new GameServices(this);
        this.mFrameLayout.addView(relativeLayout);
        mBI = new BusinessIntelligence(this);
        mBillingHandler = new BillingHandler(this);
        checkForDeepLink(getIntent());
        if (this.pendingAppLink == null) {
            Log.d("hcr2", "No deeplink in intent. Skip deferred...");
        }
        try {
            Fabric.with(new Fabric.Builder(this).kits(new Crashlytics()).build());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        doFirstVersionStartupTasks();
    }

    @Override // org.cocos2dx.lib.Cocos2dxActivity
    public Cocos2dxGLSurfaceView onCreateView() {
        this.glSurfaceView = new Cocos2dxGLSurfaceView(this);
        this.glSurfaceView.setEGLConfigChooser(5, 6, 5, 0, 16, 8);
        if (Build.VERSION.SDK_INT >= 19) {
            this.glSurfaceView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.fingersoft.hcr2.AppActivity.5
                @Override // android.view.View.OnSystemUiVisibilityChangeListener
                public void onSystemUiVisibilityChange(int i) {
                    if ((i & 4) == 0) {
                        AppActivity.this.glSurfaceView.setSystemUiVisibility(5894);
                    }
                }
            });
            this.glSurfaceView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.fingersoft.hcr2.AppActivity.6
                @Override // android.view.View.OnFocusChangeListener
                public void onFocusChange(View view, boolean z) {
                    if (z) {
                        view.setSystemUiVisibility(5894);
                    }
                }
            });
            hideSystemUI();
        }
        return this.glSurfaceView;
    }

    public void checkInstallReferrerDeepLink() {
        try {
            File file = new File(getContext().getFilesDir().getAbsolutePath() + "/install_receiver");
            if (file.exists()) {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                getIntent().putExtra("referrer", bufferedReader.readLine());
                bufferedReader.close();
                fileReader.close();
                file.delete();
                checkForDeepLink(getIntent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean getConnectivityStatus(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
            return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getAdServerAddress() {
        try {
            return mLiveopsManager != null ? mLiveopsManager.getBaseAddress() : "-";
        } catch (Exception unused) {
            return "-";
        }
    }

    public static String getAccountServerAddress() {
        try {
            return FingerAccount.getAccountManager() != null ? FingerAccount.getServerAddress() : "-";
        } catch (Exception unused) {
            return "-";
        }
    }

    public static void showAds() {
        try {
            Log.d("hcr2", "showAds");
            mLiveopsManager.onCrossPromotionEvent(CrossPromotionManager.CrossPromotionEvent.EVENT_CUSTOM_EVENT_1);
        } catch (Exception unused) {
        }
    }

    public static void showEula() {
        try {
            Log.d("hcr2", "showEULA");
            getInstance();
            if (mLiveopsManager != null) {
                getInstance();
                mLiveopsManager.showEulaWindow(getInstance());
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.fingersoft.liveops_sdk.eula.EULAListener
    public void onEULAAccepted() {
        Log.d("hcr2", "onEULAAccepted");
    }

    @Override // com.fingersoft.liveops_sdk.eula.EULAListener
    public void onEULACanceled() {
        Log.d("hcr2", "onEULACanceled");
        finish();
    }

    public static long getNewsLastShownTimestamp() {
        return mLiveopsManager.getNewsShownLast();
    }

    public static void onNewsRead() {
        Log.d("hcr2", "onNewsRead");
        mLiveopsManager.onNewsRead();
    }

    public static void onNewsPopupShown() {
        Log.d("hcr2", "onNewsPopupShown");
        mLiveopsManager.onNewsPopupShown();
    }

    public static void openStoreRating() {
        try {
            Log.d("hcr2", "openStoreRating");
            Context context = getContext();
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + context.getPackageName()));
            intent.addFlags(1208483840);
            try {
                getInstance().startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                getInstance().startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onFinishedAssetLoader() {
        try {
            boolean z = false;
            if (getInstance().mNewAdSettingsPending) {
                try {
                    onAdSettingsReceived();
                    getInstance().mNewAdSettingsPending = false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (mFirebaseRemoteConfigPending) {
                try {
                    onFirebaseRemoteConfigReceived();
                    mFirebaseRemoteConfigPending = false;
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (mNewsDataPending) {
                onNewsDataAvailable(mNewsDataPopupId, mNewsDataNewItems);
                mNewsDataPending = false;
            }
            ConnectivityManager connectivityManager = (ConnectivityManager) getAppContext().getSystemService("connectivity");
            NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                z = true;
            }
            onNetworkConnectivityChanged(z);
            if (!getInstance().mFinishedAssetLoader && mFirebase.getAdsInstance() != null) {
                Log.d("hcr2", "Startup finished gdpr consent value setting " + Boolean.toString(mGDPRStatus));
                mFirebase.getAdsInstance().setGDPRConsentStatus(mGDPRStatus, true);
            }
            getInstance().mFinishedAssetLoader = true;
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public static void onFinishedLaunching() {
        try {
            Log.d("hcr2", "onFinishedLaunching");
            getInstance().checkInstallReferrerDeepLink();
            getInstance().mFinishedLaunching = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FingerAccount.init(mActivityInstance, PlatformConfig.GOOGLE_PLAY_CLIENT_ID, Config.ACCOUNT_SERVER_ADDRESS_PRODUCTION);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public boolean hasFinishedLaunching() {
        return this.mFinishedLaunching;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (!z || Build.VERSION.SDK_INT < 19) {
            return;
        }
        hideSystemUI();
    }

    private void hideSystemUI() {
        this.glSurfaceView.setSystemUiVisibility(5894);
    }

    protected void checkForDeepLink(Intent intent) {
        String stringExtra;
        String stringExtra2;
        Uri data;
        String string;
        try {
            Log.d("hcr2", "checkForDeepLink");
            if (intent != null) {
                this.pendingAppLink = null;
                Bundle bundleExtra = intent.getBundleExtra("al_applink_data");
                if (bundleExtra != null && (string = bundleExtra.getString("target_url")) != null) {
                    Log.d("hcr2", "app link: " + string);
                    this.pendingAppLink = Uri.parse(string);
                    if (this.pendingAppLink == null) {
                        Log.d("hcr2", "app link parse failed");
                    } else {
                        Log.d("hcr2", "app link parse OK");
                    }
                }
                if (this.pendingAppLink == null && (data = intent.getData()) != null) {
                    Log.d("hcr2", "deep link: " + data.getPath());
                    if (!data.getPath().isEmpty()) {
                        this.pendingAppLink = data;
                    }
                }
                if (this.pendingAppLink == null && (stringExtra2 = intent.getStringExtra("referrer")) != null) {
                    Log.d("hcr2", "referrer: " + stringExtra2);
                    if (stringExtra2.contains("playhcr")) {
                        this.pendingAppLink = Uri.parse(URLDecoder.decode(stringExtra2, "UTF-8"));
                    }
                }
                if (this.pendingAppLink != null || (stringExtra = intent.getStringExtra(FirebaseMessageReceiver.FIREBASE_NOTIFICATION_PAYLOAD_KEY)) == null) {
                    return;
                }
                this.pendingAppLink = Uri.parse(stringExtra);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void handlePendingDeepLink() {
        Uri uri = this.pendingAppLink;
        if (uri != null) {
            handleAppLink(uri.getPath(), this.pendingAppLink.getQuery());
            this.pendingAppLink = null;
        }
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        Log.d("hcr2", "onNewIntent");
        super.onNewIntent(intent);
        checkForDeepLink(intent);
    }

    @Override // android.app.Activity
    protected void onStart() {
        Log.d("hcr2", "onStart");
        super.onStart();
        Firebase firebase = mFirebase;
        if (firebase == null || firebase.getAdsInstance() == null) {
            return;
        }
        mFirebase.getAdsInstance().onStart();
    }

    @Override // org.cocos2dx.lib.Cocos2dxActivity, android.app.Activity
    protected void onDestroy() {
        Log.d("hcr2", "onDestroy");
        super.onDestroy();
        try {
            unregisterManagers();
            if (mBillingHandler != null) {
                mBillingHandler.destroy();
                mBillingHandler = null;
            }
            if (mFirebase == null || mFirebase.getAdsInstance() == null) {
                return;
            }
            mFirebase.getAdsInstance().onDestroy();
            mFirebase = null;
        } catch (Exception unused) {
        }
    }

    @Override // org.cocos2dx.lib.Cocos2dxActivity, android.app.Activity
    protected void onPause() {
        Log.d("hcr2", "onPause");
        try {
            super.onPause();
            mActivityInstance.unregisterReceiver(this.ConnectivityReceiver);
            unregisterManagers();
            Tracking.stopUsage(this);
            if (mFirebase != null && mFirebase.getAdsInstance() != null) {
                mFirebase.getAdsInstance().onPause();
            }
            AppEventsLogger.deactivateApp(this);
        } catch (Exception unused) {
        }
    }

    @Override // org.cocos2dx.lib.Cocos2dxActivity, android.app.Activity
    protected void onResume() {
        Log.d("hcr2", "onResume");
        super.onResume();
        mActivityInstance.registerReceiver(this.ConnectivityReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        try {
            checkForCrashes();
            Tracking.startUsage(this);
            if (mBillingHandler != null) {
                mBillingHandler.onResume();
            }
            if (mFirebase != null && mFirebase.getAdsInstance() != null) {
                mFirebase.getAdsInstance().onResume();
            }
            AppEventsLogger.activateApp(this);
            if (!this.mIapInventoryQueryPending) {
                mHandler.postDelayed(new Runnable() { // from class: com.fingersoft.hcr2.AppActivity.7
                    @Override // java.lang.Runnable
                    public void run() {
                        Log.d("hcr2", "Starting pending inventory query");
                        try {
                            if (AppActivity.mBillingHandler != null) {
                                AppActivity.this.mIapInventoryQueryPending = false;
                                BillingHandler unused = AppActivity.mBillingHandler;
                                BillingHandler.startInventoryQuery();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 3000L);
                this.mIapInventoryQueryPending = true;
            }
            handlePendingDeepLink();
        } catch (Exception unused) {
        }
    }

    @Override // org.cocos2dx.lib.Cocos2dxActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        Log.d("hcr2", "onActivityResult(" + i + ", " + i2 + ", " + intent + ")");
        try {
            if (mBillingHandler != null && mBillingHandler.getHelper() != null && mBillingHandler.getHelper().handleActivityResult(i, i2, intent)) {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (FingerAccount.getAccountManager() != null) {
                FingerAccount.getAccountManager().onActivityResult(i, i2, intent);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            if (mGameServices != null) {
                GameServices gameServices = mGameServices;
                GameServices.onActivityResult(i, i2, intent);
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    private void checkForCrashes() {
        Log.d("hcr2", "checkForCrashes");
        CrashManager.register(this, PlatformConfig.HOCKEYAPP_APP_ID_WEEKLY);
    }

    public static int getFirebaseRemoteConfigInt(String str, int i) {
        try {
            FirebaseRemoteConfigValue value = mFirebase.getAnalyticsInstance().getRemoteConfig().getValue(str);
            return value.getSource() == 2 ? (int) value.asLong() : i;
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        }
    }

    public static double getFirebaseRemoteConfigDouble(String str, double d) {
        try {
            FirebaseRemoteConfigValue value = mFirebase.getAnalyticsInstance().getRemoteConfig().getValue(str);
            return value.getSource() == 2 ? value.asDouble() : d;
        } catch (Exception e) {
            e.printStackTrace();
            return d;
        }
    }

    public static boolean getFirebaseRemoteConfigBool(String str, boolean z) {
        try {
            FirebaseRemoteConfigValue value = mFirebase.getAnalyticsInstance().getRemoteConfig().getValue(str);
            return value.getSource() == 2 ? value.asBoolean() : z;
        } catch (Exception e) {
            e.printStackTrace();
            return z;
        }
    }

    public static String getFirebaseRemoteConfigString(String str, String str2) {
        try {
            FirebaseRemoteConfigValue value = mFirebase.getAnalyticsInstance().getRemoteConfig().getValue(str);
            return value.getSource() == 2 ? value.asString() : str2;
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }

    public static String[] getFirebaseRemoteConfigKeysWithPrefix(String str) {
        try {
            return (String[]) mFirebase.getAnalyticsInstance().getRemoteConfig().getKeysByPrefix(str).toArray(new String[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    public static int getAdSettingInt(String str, int i) {
        try {
            return ApplicationSettings.getValueInt(mActivityInstance.getApplicationContext(), str, i);
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        }
    }

    public static String getAdSettingString(String str, String str2) {
        try {
            return ApplicationSettings.getValueString(mActivityInstance.getApplicationContext(), str, str2);
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }

    public static void showDialog(String str) {
        try {
            mActivityInstance.showDialog("Hill Climb Racing 2", str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void openURL(String str) {
        try {
            Cocos2dxHelper.openURL(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void moreApps() {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse("market://search?q=pub:Fingersoft"));
            mActivityInstance.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getMarketUrl() {
        try {
            return "https://play.google.com/store/apps/details?id=" + mActivityInstance.getPackageName();
        } catch (Exception e) {
            e.printStackTrace();
            return "https://play.google.com/store/apps/details?id=com.fingersoft.hcr2";
        }
    }

    public static void shareApp() {
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType(WebRequest.CONTENT_TYPE_PLAIN_TEXT);
            intent.putExtra("android.intent.extra.TEXT", "https://play.google.com/store/apps/details?id=" + mActivityInstance.getPackageName());
            mActivityInstance.startActivity(Intent.createChooser(intent, "Share with"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playVideoAd(int i) {
        try {
            Log.d("hcr2", "PLAY VIDEO AD: " + i);
            if (mFirebase.getAdsInstance().showVideoAd()) {
                return;
            }
            notifyVideoAdFailed();
        } catch (Exception e) {
            e.printStackTrace();
            notifyVideoAdFailed();
        }
    }

    public static void loadInterstitial() {
        try {
            Log.d("hcr2", "LOAD INTERSTITIAL");
            mFirebase.getAdsInstance().loadInterstitial();
        } catch (Exception e) {
            e.printStackTrace();
            notifyInterstitialFailed();
        }
    }

    public static void showInterstitial() {
        try {
            Log.d("hcr2", "SHOW INTERSTITIAL");
            mFirebase.getAdsInstance().showInterstitial();
        } catch (Exception e) {
            e.printStackTrace();
            notifyInterstitialFailed();
        }
    }

    public static boolean hasVideoAdsAvailable(int i) {
        try {
            return mFirebase.getAdsInstance().hasVideoCampaigns();
        } catch (Exception e) {
            Log.e("hcr2", "ERROR: hasVideoAds() " + e);
            return false;
        }
    }

    public static void requestIap(final String str) {
        try {
            mHandler.post(new Runnable() { // from class: com.fingersoft.hcr2.AppActivity.8
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        AppActivity.mBillingHandler.purchase(AppActivity.mActivityInstance, str);
                    } catch (Exception e) {
                        e.printStackTrace();
                        AppActivity.onIapError(str, e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            onIapError(str, e.getMessage());
        }
    }

    @Override // com.fingersoft.liveops_sdk.utils.ISdkInitListener
    public void onLiveopsSdkInitialized() {
        Log.d("hcr2", "onLiveopsSdkInitialized()");
    }

    @Override // com.fingersoft.ads_sdk.advertising.IFSAdSdkListener
    public void onFSAdSdkInitialized() {
        Log.d("hcr2", "onFSAdSdkInitialized()");
    }

    public static void shareImage(final String str, final String str2, final String str3) {
        try {
            mHandler.post(new Runnable() { // from class: com.fingersoft.hcr2.AppActivity.9
                @Override // java.lang.Runnable
                public void run() {
                    Uri uriInsert;
                    try {
                        File file = new File(str);
                        if (Build.VERSION.SDK_INT >= 9) {
                            file.setReadable(true, false);
                        }
                        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                        if (Build.VERSION.SDK_INT >= 18) {
                            uriInsert = FileProvider.getUriForFile(AppActivity.mActivityInstance, "com.fingersoft.hcr2.fileprovider", file);
                        } else {
                            ContentValues contentValues = new ContentValues(2);
                            contentValues.put("mime_type", mimeTypeFromExtension);
                            contentValues.put("_data", file.getAbsolutePath());
                            uriInsert = AppActivity.getInstance().getContentResolver().insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, contentValues);
                        }
                        Intent intent = new Intent("android.intent.action.SEND");
                        intent.addFlags(1);
                        intent.putExtra("android.intent.extra.SUBJECT", str2);
                        intent.putExtra("android.intent.extra.TEXT", str3);
                        intent.putExtra("android.intent.extra.STREAM", uriInsert);
                        intent.setType(mimeTypeFromExtension);
                        AppActivity.getInstance().startActivity(Intent.createChooser(intent, "Share using"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shareText(final String str, final String str2) {
        try {
            mHandler.post(new Runnable() { // from class: com.fingersoft.hcr2.AppActivity.10
                @Override // java.lang.Runnable
                public void run() {
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.addFlags(1);
                    intent.putExtra("android.intent.extra.SUBJECT", str);
                    intent.putExtra("android.intent.extra.TEXT", str2);
                    intent.setType(WebRequest.CONTENT_TYPE_PLAIN_TEXT);
                    AppActivity.getInstance().startActivity(Intent.createChooser(intent, "Share using"));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized void startAnalytics(String str) {
        Log.d("hcr2", "Start analytics with uid: " + str);
        try {
            mAnalyticsUid = str;
            if (mBI != null) {
                mBI.InitializeAppsflyer(str);
            }
            if (isFirebaseEnabled()) {
                Log.d("hcr2", "Starting firebase analytics");
                mFirebase.initializeAnalyticsWithUserId(str, new FirebaseListener() { // from class: com.fingersoft.hcr2.AppActivity.11
                    @Override // com.fingersoft.hcr2.firebase.FirebaseListener
                    public void onRemoteConfigReadSuccess() {
                        Log.d("hcr2", "onRemoteConfigReadSuccess");
                        if (!AppActivity.getInstance().mFinishedAssetLoader) {
                            boolean unused = AppActivity.mFirebaseRemoteConfigPending = true;
                        } else {
                            AppActivity.onFirebaseRemoteConfigReceived();
                        }
                    }

                    @Override // com.fingersoft.hcr2.firebase.FirebaseListener
                    public void onRemoteConfigReadFailed() {
                        Log.d("hcr2", "onRemoteConfigReadFailed");
                    }
                });
                try {
                    if (!Cocos2dxHelper.getBoolForKey("first_startup_tracked", false)) {
                        Cocos2dxHelper.setBoolForKey("first_startup_tracked", true);
                        mFirebase.getAnalyticsInstance().trackUserProperty("first_app_version", getInstance().getPackageManager().getPackageInfo(getInstance().getPackageName(), 0).versionName);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mFirebase.getAnalyticsInstance().setUserID(str);
            } else {
                Log.d("hcr2", "Firebase disabled");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void setAnalyticsUserId(String str) {
        Log.d("hcr2", "---> SetAnalyticsUserId");
        try {
            mAnalyticsUid = str;
            if (mFirebase != null && mFirebase.getAnalyticsInstance() != null) {
                mFirebase.getAnalyticsInstance().setUserID(str);
            }
            BusinessIntelligence.setPlayerId(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getAdvertisingId() {
        try {
            return mLiveopsManager != null ? mLiveopsManager.getAdvertisingId() : "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void trackEvent(String str, HashMap<String, String> map) {
        try {
            if (mFirebase.getAnalyticsInstance() == null || !isFirebaseEnabled()) {
                return;
            }
            Log.d("hcr2", "Firebase event: " + str + " parameters: " + map);
            Bundle bundle = new Bundle();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                bundle.putString(entry.getKey(), entry.getValue());
            }
            mFirebase.getAnalyticsInstance().trackEvent(str, bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void trackUserProperty(String str, String str2) {
        try {
            if (mFirebase.getAnalyticsInstance() == null || !isFirebaseEnabled()) {
                return;
            }
            mFirebase.getAnalyticsInstance().trackUserProperty(str, str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getDeviceLanguage() {
        try {
            return Locale.getDefault().getLanguage();
        } catch (Exception e) {
            e.printStackTrace();
            return "en";
        }
    }

    public static String getDeviceLocale() {
        try {
            return Locale.getDefault().getCountry();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getClientSignature() {
        try {
            Signature[] signatureArr = mActivityInstance.getPackageManager().getPackageInfo(mActivityInstance.getPackageName(), 64).signatures;
            if (signatureArr.length > 0) {
                Signature signature = signatureArr[0];
                MessageDigest messageDigest = MessageDigest.getInstance("SHA");
                messageDigest.update(signature.toByteArray());
                return Base64.encodeToString(messageDigest.digest(), 2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("hcr2", "getClientSignature: 16908292");
        return "";
    }

    public static void scheduleNotification(String str, int i, String str2, String str3) {
        try {
            Log.d("hcr2", "scheduleNotification(" + str + ", " + str.hashCode() + ", " + str3 + ", " + i + ", " + str2 + ")");
            if (Build.VERSION.SDK_INT >= 26) {
                NotificationChannels.createChannel(str3);
            }
            Context context = getContext();
            long j = ((long) i) * 1000;
            NotificationCompat.Builder visibility = new NotificationCompat.Builder(context, str3).setSmallIcon(R.drawable.ic_stat_name).setContentIntent(PendingIntent.getActivity(context, 0, new Intent(context, (Class<?>) AppActivity.class), 0)).setWhen(j).setAutoCancel(true).setDefaults(-1).setVisibility(1);
            try {
                NotificationCompat.Builder.class.getMethod("setColor", Integer.TYPE);
                visibility.setColor(Color.rgb(209, 35, 35));
            } catch (Exception unused) {
            }
            if (Build.VERSION.SDK_INT < 21) {
                visibility.setContentTitle("Hill Climb Racing 2");
                visibility.setContentText(str2);
            } else {
                visibility.setContentTitle(str2);
            }
            if (Build.VERSION.SDK_INT < 26) {
                visibility.setPriority(0);
            }
            Notification notificationBuild = visibility.build();
            Intent intent = new Intent(context, (Class<?>) NotificationReceiver.class);
            intent.putExtra(NotificationReceiver.NOTIFICATION_TAG, str);
            intent.putExtra(NotificationReceiver.NOTIFICATION, notificationBuild);
            ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(0, j, PendingIntent.getBroadcast(context, str.hashCode(), intent, 134217728));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cancelNotification(String str) {
        try {
            Log.d("hcr2", "cancelNotification(" + str + ")");
            Context context = getContext();
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
            Intent intent = new Intent(context, (Class<?>) NotificationReceiver.class);
            intent.putExtra(NotificationReceiver.NOTIFICATION_TAG, str);
            PendingIntent broadcast = PendingIntent.getBroadcast(context, str.hashCode(), intent, 134217728);
            broadcast.cancel();
            alarmManager.cancel(broadcast);
            ((NotificationManager) context.getSystemService("notification")).cancel(str, str.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doFirstVersionStartupTasks() {
        try {
            if (Utils.isFirstVersionStartup(this)) {
                ((AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(PendingIntent.getBroadcast(this, 0, new Intent(this, (Class<?>) NotificationReceiver.class), 134217728));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void enableAdFree() {
        try {
            if (mAdManager != null) {
                Log.d("hcr2", "Enabling ad-free");
                mAdManager.enableAdFreeMode();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setPlayerVipStatus(boolean z) {
        if (mLiveopsManager != null) {
            Log.d("hcr2", "Set vip status: " + Boolean.toString(z));
            mLiveopsManager.setVIPStatus(z);
        }
    }

    public static void setPlayerGDPRStatus(boolean z) {
        Log.d("hcr2", "Set GDPR consent status: " + Boolean.toString(z));
        if (mFirebase.getAdsInstance() != null) {
            if (mGDPRStatus && !z) {
                mFirebase.getAdsInstance().setGDPRConsentStatus(z, true);
            } else {
                mFirebase.getAdsInstance().setGDPRConsentStatus(z, false);
            }
        }
        mGDPRStatus = z;
    }

    public static Context getAppContext() {
        try {
            return mActivityInstance.getApplicationContext();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void inviteWithFacebook(final String str) {
        try {
            mHandler.post(new Runnable() { // from class: com.fingersoft.hcr2.AppActivity.12
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        String str2 = "https://playhcr.com/invite?id=" + str;
                        Log.d("hcr2", "Using appLinkUrl " + str2);
                        if (AppInviteDialog.canShow()) {
                            AppInviteDialog.show(AppActivity.mActivityInstance, new AppInviteContent.Builder().setApplinkUrl(str2).setPreviewImageUrl("https://playhcr.com/shareimage.png").build());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean sendEmail(String str, String str2, String str3) {
        Intent intentPutExtra = new Intent("android.intent.action.SENDTO", Uri.fromParts(AdWebViewClient.MAILTO, str, null)).putExtra("android.intent.extra.SUBJECT", str2).putExtra("android.intent.extra.TEXT", str3);
        if (intentPutExtra.resolveActivity(mActivityInstance.getPackageManager()) == null) {
            return false;
        }
        mActivityInstance.startActivity(intentPutExtra);
        return true;
    }

    private static float getDeviceScreenWidth() {
        float f = mActivityInstance.getResources().getDisplayMetrics().widthPixels / mActivityInstance.getResources().getDisplayMetrics().densityDpi;
        Log.d("hcr2", "Getting device screen width: " + f);
        return f;
    }

    public static boolean isGameServicesConnected() {
        return mGameServices.isConnected();
    }

    public static void connectGameServices() {
        GameServices gameServices = mGameServices;
        GameServices.connect();
    }

    public static void disconnectGameServices() {
        GameServices gameServices = mGameServices;
        GameServices.disconnect();
    }

    public static void openAdMobTestSuite() {
        Log.d("hcr2", "AdMob Testsuite removed");
    }

    public String getAnalyticsId() {
        String str = mAnalyticsUid;
        return str == null ? "" : str;
    }

    @Override // com.fingersoft.hcr2.firebase.FirebaseAdListener
    public void onVideoAdFailed() {
        onAdFailed();
    }

    @Override // com.fingersoft.hcr2.firebase.FirebaseAdListener
    public void onVideoAdStarted(String str) {
        Log.d("Firebasead", "Rewarded video started " + str);
        notifyVideoAdStarted(str);
    }

    /* JADX WARN: Code duplicated, block: B:17:0x004e  */
    @Override // com.fingersoft.hcr2.firebase.FirebaseAdListener
    public void onVideoAdViewed(String str) {
        byte b;
        onAdViewed();
        try {
            if (str != null) {
                Log.d("hcr2", "Rewarded video viewed with adapter: " + str);
                String[] strArrSplit = str.split("\\.");
                if (strArrSplit.length > 1) {
                    String str2 = strArrSplit[strArrSplit.length - 2];
                    int iHashCode = str.hashCode();
                    if (iHashCode != 534400664) {
                        if (iHashCode == 656882328 && str.equals("com.applovin.mediation.ApplovinAdapter")) {
                            b = 1;
                        } else {
                            b = -1;
                        }
                    } else if (str.equals("com.vungle.mediation.VungleAdapter")) {
                        b = 0;
                    } else {
                        b = -1;
                    }
                    if (b == 0) {
                        if (mBI != null) {
                            BusinessIntelligence businessIntelligence = mBI;
                            BusinessIntelligence.onCustomEvent("ad_watched_vungle");
                        }
                        Log.d("hcr2", "Rewarded video viewed with adapter: vungle");
                        return;
                    }
                    if (b == 1) {
                        if (mBI != null) {
                            BusinessIntelligence businessIntelligence2 = mBI;
                            BusinessIntelligence.onCustomEvent("ad_watched_applovin");
                        }
                        Log.d("hcr2", "Rewarded video viewed with adapter: applovin");
                        return;
                    }
                    if (mBI != null) {
                        BusinessIntelligence businessIntelligence3 = mBI;
                        BusinessIntelligence.onCustomEvent("ad_watched_" + str2);
                    }
                    Log.d("hcr2", "Rewarded video viewed with adapter: " + str2);
                    return;
                }
                return;
            }
            if (mBI != null) {
                BusinessIntelligence businessIntelligence4 = mBI;
                BusinessIntelligence.onCustomEvent("ad_watched_no_adapter_name");
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.fingersoft.hcr2.firebase.FirebaseAdListener
    public void onVideoAdCancelled() {
        onAdCancelled();
    }

    @Override // com.fingersoft.hcr2.firebase.FirebaseAdListener
    public void onInterstitialAdLoaded() {
        onInterstitialLoaded();
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0054  */
    @Override // com.fingersoft.hcr2.firebase.FirebaseAdListener
    public void onInterstitialAdViewed(String str) {
        byte b;
        onInterstitialDismiss();
        if (str != null && !str.isEmpty()) {
            String[] strArrSplit = str.split("\\.");
            if (strArrSplit.length > 1) {
                Log.d("hcr2", "Interstitial viewed with adapter: " + str);
                String str2 = strArrSplit[strArrSplit.length + (-2)];
                int iHashCode = str.hashCode();
                if (iHashCode != -1919045556) {
                    if (iHashCode == 656882328 && str.equals("com.applovin.mediation.ApplovinAdapter")) {
                        b = 1;
                    } else {
                        b = -1;
                    }
                } else if (str.equals("com.vungle.mediation.VungleInterstitialAdapter")) {
                    b = 0;
                } else {
                    b = -1;
                }
                if (b == 0) {
                    if (mBI != null) {
                        BusinessIntelligence.onCustomEvent("ad_watched_vungle");
                    }
                    Log.d("hcr2", "Interstitial video viewed with adapter: vungle");
                    return;
                } else {
                    if (b == 1) {
                        if (mBI != null) {
                            BusinessIntelligence.onCustomEvent("ad_watched_applovin");
                        }
                        Log.d("hcr2", "Interstitial video viewed with adapter: applovin");
                        return;
                    }
                    if (mBI != null) {
                        BusinessIntelligence.onCustomEvent("ad_watched_" + str2);
                    }
                    Log.d("hcr2", "Interstitial video viewed with adapter: " + str2);
                    return;
                }
            }
            return;
        }
        if (mBI != null) {
            BusinessIntelligence.onCustomEvent("ad_watched_no_adapter_name");
        }
    }

    @Override // com.fingersoft.hcr2.firebase.FirebaseAdListener
    public void onInterstitialAdFailed() {
        onInterstitialFailed();
    }

    @Override // com.fingersoft.hcr2.firebase.FirebaseAdListener
    public void onAdFailed() {
        Log.d("hcr2", "onAdFailed()");
        notifyVideoAdFailed();
    }

    @Override // com.fingersoft.hcr2.firebase.FirebaseAdListener
    public void onAdViewed() {
        Log.d("hcr2", "onAdViewed()");
        notifyVideoAdCompleted();
    }

    @Override // com.fingersoft.hcr2.firebase.FirebaseAdListener
    public void onAdCancelled() {
        Log.d("hcr2", "onAdCancelled()");
        notifyVideoAdCanceled();
    }

    @Override // com.fingersoft.hcr2.firebase.FirebaseAdListener
    public void onInterstitialDismiss() {
        Log.d("hcr2", "onInterstitialDismiss()");
        notifyInterstitialDismissed();
    }

    @Override // com.fingersoft.hcr2.firebase.FirebaseAdListener
    public void onInterstitialFailed() {
        Log.d("hcr2", "onInterstitialFailed()");
        notifyInterstitialFailed();
    }

    @Override // com.fingersoft.hcr2.firebase.FirebaseAdListener
    public void onInterstitialInteract() {
        Log.d("hcr2", "onInterstitialInteract()");
        notifyInterstitialInteract();
    }

    public void onInterstitialLoaded() {
        Log.d("hcr2", "onInterstitialLoaded()");
        notifyInterstitialLoaded();
    }

    @Override // com.fingersoft.hcr2.firebase.FirebaseAdListener
    public void onInterstitialShow(String str) {
        Log.d("hcr2", "onInterstitialShow() with adapter class " + str);
        HashMap map = new HashMap();
        map.put("network", parseAdNetworkName(str));
        trackEvent("interstitial_viewed", map);
    }

    public static boolean isRooted() {
        RootBeer rootBeer = new RootBeer(getAppContext());
        rootBeer.setLogging(false);
        return rootBeer.isRootedWithoutBusyBoxCheck();
    }

    /* JADX WARN: Code duplicated, block: B:21:0x005a  */
    public static String parseAdNetworkName(String str) {
        byte b;
        if (str == null) {
            return "";
        }
        Log.d("hcr2", "Parsing adpter network name with adapter class: " + str);
        String[] strArrSplit = str.split("\\.");
        if (strArrSplit.length <= 1) {
            return "";
        }
        String str2 = strArrSplit[strArrSplit.length - 2];
        int iHashCode = str.hashCode();
        if (iHashCode != -1919045556) {
            if (iHashCode != 534400664) {
                if (iHashCode == 656882328 && str.equals("com.applovin.mediation.ApplovinAdapter")) {
                    b = 2;
                } else {
                    b = -1;
                }
            } else if (str.equals("com.vungle.mediation.VungleAdapter")) {
                b = 1;
            } else {
                b = -1;
            }
        } else if (str.equals("com.vungle.mediation.VungleInterstitialAdapter")) {
            b = 0;
        } else {
            b = -1;
        }
        if (b == 0 || b == 1) {
            str2 = "vungle";
        } else if (b != 2) {
            Log.d("hcr2", "Parsed adapter name");
        } else {
            str2 = "applovin";
        }
        Log.d("hcr2", "Parsed network name: " + str2);
        return str2;
    }

    public static boolean hasHardwareKeyboard() {
        return mActivityInstance.getResources().getConfiguration().hardKeyboardHidden == 1;
    }

    public static float getVirtualKeyboardHeightRatio() {
        return mKeyboardHeightRatio;
    }
}
