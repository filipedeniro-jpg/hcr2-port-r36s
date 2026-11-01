package com.ironsource.sdk.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.MutableContextWrapper;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.drive.DriveFile;
import com.ironsource.environment.ApplicationContext;
import com.ironsource.environment.ConnectivityService;
import com.ironsource.environment.DeviceStatus;
import com.ironsource.environment.LocationService;
import com.ironsource.environment.UrlHandler;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.sdk.constants.Constants;
import com.ironsource.sdk.constants.LocationConst;
import com.ironsource.sdk.data.AdUnitsReady;
import com.ironsource.sdk.data.AdUnitsState;
import com.ironsource.sdk.data.DemandSource;
import com.ironsource.sdk.data.SSABCParameters;
import com.ironsource.sdk.data.SSAEnums;
import com.ironsource.sdk.data.SSAFile;
import com.ironsource.sdk.data.SSAObj;
import com.ironsource.sdk.listeners.OnGenericFunctionListener;
import com.ironsource.sdk.listeners.OnOfferWallListener;
import com.ironsource.sdk.listeners.OnWebViewChangeListener;
import com.ironsource.sdk.listeners.internals.DSAdProductListener;
import com.ironsource.sdk.listeners.internals.DSBannerListener;
import com.ironsource.sdk.listeners.internals.DSInterstitialListener;
import com.ironsource.sdk.listeners.internals.DSRewardedVideoListener;
import com.ironsource.sdk.precache.DownloadManager;
import com.ironsource.sdk.utils.DeviceProperties;
import com.ironsource.sdk.utils.IronSourceAsyncHttpRequestTask;
import com.ironsource.sdk.utils.IronSourceSharedPrefHelper;
import com.ironsource.sdk.utils.IronSourceStorageUtils;
import com.ironsource.sdk.utils.Logger;
import com.ironsource.sdk.utils.SDKUtils;
import com.unity3d.ads.metadata.InAppPurchaseMetaData;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class IronSourceWebView extends WebView implements DownloadManager.OnPreCacheCompletion, DownloadListener {
    public static String APP_IDS = "appIds";
    public static int DISPLAY_WEB_VIEW_INTENT = 0;
    public static String EXTERNAL_URL = "external_url";
    public static String IS_INSTALLED = "isInstalled";
    public static String IS_STORE = "is_store";
    public static String IS_STORE_CLOSE = "is_store_close";
    private static String JSON_KEY_FAIL = "fail";
    private static String JSON_KEY_SUCCESS = "success";
    public static int OPEN_URL_INTENT = 1;
    public static String REQUEST_ID = "requestId";
    public static String RESULT = "result";
    public static String SECONDARY_WEB_VIEW = "secondary_web_view";
    public static String WEBVIEW_TYPE = "webview_type";
    public static int mDebugMode;
    private final String GENERIC_MESSAGE;
    private String PUB_TAG;
    private String TAG;
    private DownloadManager downloadManager;
    private Boolean isKitkatAndAbove;
    private boolean isRemoveCloseEventHandler;
    private String mApplicationKey;
    private BannerJSAdapter mBannerJsAdapter;
    private String mCacheDirectory;
    private OnWebViewChangeListener mChangeListener;
    private CountDownTimer mCloseEventTimer;
    private BroadcastReceiver mConnectionReceiver;
    private ArrayList<String> mControllerCommandsQueue;
    private String mControllerKeyPressed;
    private FrameLayout mControllerLayout;
    private SSAEnums.ControllerState mControllerState;
    Context mCurrentActivityContext;
    private View mCustomView;
    private WebChromeClient.CustomViewCallback mCustomViewCallback;
    private FrameLayout mCustomViewContainer;
    private DSBannerListener mDSBannerListener;
    private DSInterstitialListener mDSInterstitialListener;
    private DSRewardedVideoListener mDSRewardedVideoListener;
    private DemandSourceManager mDemandSourceManager;
    private boolean mGlobalControllerTimeFinish;
    private CountDownTimer mGlobalControllerTimer;
    private int mHiddenForceCloseHeight;
    private String mHiddenForceCloseLocation;
    private int mHiddenForceCloseWidth;
    private boolean mIsImmersive;
    private CountDownTimer mLoadControllerTimer;
    private MOATJSAdapter mMoatJsAdapter;
    private boolean mOWCreditsMiss;
    private Map<String, String> mOWExtraParameters;
    private boolean mOWmiss;
    private OnGenericFunctionListener mOnGenericFunctionListener;
    private OnOfferWallListener mOnOfferWallListener;
    private String mOrientationState;
    private PermissionsJSAdapter mPermissionsJsAdapter;
    private AdUnitsState mSavedState;
    private Object mSavedStateLocker;
    private State mState;
    private TokenJSAdapter mTokenJSAdapter;
    Handler mUiHandler;
    private String mUserId;
    private VideoEventsListener mVideoEventsListener;
    private ChromeClient mWebChromeClient;
    private WebViewMessagingMediator mWebViewMessagingMediator;

    private interface OnInitProductHandler {
        void handleInitProductFailed(String str, SSAEnums.ProductType productType, DemandSource demandSource);
    }

    public enum State {
        Display,
        Gone
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WebView getWebview() {
        return this;
    }

    private Map<String, String> getExtraParamsByProduct(SSAEnums.ProductType productType) {
        if (productType == SSAEnums.ProductType.OfferWall) {
            return this.mOWExtraParameters;
        }
        return null;
    }

    public IronSourceWebView(Context context, DemandSourceManager demandSourceManager) {
        super(context.getApplicationContext());
        this.TAG = IronSourceWebView.class.getSimpleName();
        this.PUB_TAG = IronSourceConstants.IRONSOURCE_CONFIG_NAME;
        this.GENERIC_MESSAGE = "We're sorry, some error occurred. we will investigate it";
        this.mControllerKeyPressed = "interrupt";
        this.mHiddenForceCloseWidth = 50;
        this.mHiddenForceCloseHeight = 50;
        this.mHiddenForceCloseLocation = Constants.ForceClosePosition.TOP_RIGHT;
        this.mControllerState = SSAEnums.ControllerState.None;
        this.isKitkatAndAbove = null;
        this.mSavedStateLocker = new Object();
        this.mIsImmersive = false;
        this.mConnectionReceiver = new BroadcastReceiver() { // from class: com.ironsource.sdk.controller.IronSourceWebView.13
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                String str;
                if (IronSourceWebView.this.mControllerState == SSAEnums.ControllerState.Ready) {
                    if (ConnectivityService.isConnectedWifi(context2)) {
                        str = "wifi";
                    } else {
                        str = ConnectivityService.isConnectedMobile(context2) ? ConnectivityService.NETWORK_TYPE_3G : "none";
                    }
                    IronSourceWebView.this.deviceStatusChanged(str);
                }
            }
        };
        Logger.i(this.TAG, "C'tor");
        this.mControllerCommandsQueue = new ArrayList<>();
        this.mCacheDirectory = initializeCacheDirectory(context.getApplicationContext());
        this.mCurrentActivityContext = context;
        this.mDemandSourceManager = demandSourceManager;
        initLayout(this.mCurrentActivityContext);
        this.mSavedState = new AdUnitsState();
        this.downloadManager = getDownloadManager();
        this.downloadManager.setOnPreCacheCompletion(this);
        this.mWebChromeClient = new ChromeClient();
        setWebViewClient(new ViewClient());
        setWebChromeClient(this.mWebChromeClient);
        setWebViewSettings();
        addJavascriptInterface(createJSInterface(context), Constants.JAVASCRIPT_INTERFACE_NAME);
        setDownloadListener(this);
        setOnTouchListener(new SupersonicWebViewTouchListener());
        this.mUiHandler = createMainThreadHandler();
    }

    JSInterface createJSInterface(Context context) {
        return new JSInterface(context);
    }

    Handler createMainThreadHandler() {
        return new Handler(Looper.getMainLooper());
    }

    DownloadManager getDownloadManager() {
        return DownloadManager.getInstance(this.mCacheDirectory);
    }

    String initializeCacheDirectory(Context context) {
        return IronSourceStorageUtils.initializeCacheDirectory(context.getApplicationContext());
    }

    public void addMoatJSInterface(MOATJSAdapter mOATJSAdapter) {
        this.mMoatJsAdapter = mOATJSAdapter;
    }

    public void addPermissionsJSInterface(PermissionsJSAdapter permissionsJSAdapter) {
        this.mPermissionsJsAdapter = permissionsJSAdapter;
    }

    public void addBannerJSInterface(BannerJSAdapter bannerJSAdapter) {
        this.mBannerJsAdapter = bannerJSAdapter;
    }

    public void addTokenJSInterface(TokenJSAdapter tokenJSAdapter) {
        this.mTokenJSAdapter = tokenJSAdapter;
    }

    public void notifyLifeCycle(String str, String str2) {
        injectJavascript(generateJSToInject(Constants.JSMethods.ON_NATIVE_LIFE_CYCLE_EVENT, parseToJson(Constants.ParametersKeys.LIFE_CYCLE_EVENT, str2, Constants.ParametersKeys.PRODUCT_TYPE, str, null, null, null, null, null, false)));
    }

    public WebViewMessagingMediator getControllerDelegate() {
        if (this.mWebViewMessagingMediator == null) {
            this.mWebViewMessagingMediator = new WebViewMessagingMediator() { // from class: com.ironsource.sdk.controller.IronSourceWebView.1
                @Override // com.ironsource.sdk.controller.WebViewMessagingMediator
                public void sendMessageToController(String str, JSONObject jSONObject) {
                    IronSourceWebView.this.injectJavascript(IronSourceWebView.this.generateJSToInject(str, jSONObject.toString()));
                }
            };
        }
        return this.mWebViewMessagingMediator;
    }

    private class SupersonicWebViewTouchListener implements View.OnTouchListener {
        private SupersonicWebViewTouchListener() {
        }

        /* JADX WARN: Type inference failed for: r9v6, types: [com.ironsource.sdk.controller.IronSourceWebView$SupersonicWebViewTouchListener$1] */
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                String str = IronSourceWebView.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("X:");
                int i = (int) x;
                sb.append(i);
                sb.append(" Y:");
                int i2 = (int) y;
                sb.append(i2);
                Logger.i(str, sb.toString());
                int deviceWidth = DeviceStatus.getDeviceWidth();
                int deviceHeight = DeviceStatus.getDeviceHeight();
                Logger.i(IronSourceWebView.this.TAG, "Width:" + deviceWidth + " Height:" + deviceHeight);
                int iDpToPx = SDKUtils.dpToPx((long) IronSourceWebView.this.mHiddenForceCloseWidth);
                int iDpToPx2 = SDKUtils.dpToPx((long) IronSourceWebView.this.mHiddenForceCloseHeight);
                if (Constants.ForceClosePosition.TOP_RIGHT.equalsIgnoreCase(IronSourceWebView.this.mHiddenForceCloseLocation)) {
                    i = deviceWidth - i;
                } else if (!Constants.ForceClosePosition.TOP_LEFT.equalsIgnoreCase(IronSourceWebView.this.mHiddenForceCloseLocation)) {
                    if (Constants.ForceClosePosition.BOTTOM_RIGHT.equalsIgnoreCase(IronSourceWebView.this.mHiddenForceCloseLocation)) {
                        i = deviceWidth - i;
                    } else if (!Constants.ForceClosePosition.BOTTOM_LEFT.equalsIgnoreCase(IronSourceWebView.this.mHiddenForceCloseLocation)) {
                        i = 0;
                        i2 = 0;
                    }
                    i2 = deviceHeight - i2;
                }
                if (i <= iDpToPx && i2 <= iDpToPx2) {
                    IronSourceWebView.this.isRemoveCloseEventHandler = false;
                    if (IronSourceWebView.this.mCloseEventTimer != null) {
                        IronSourceWebView.this.mCloseEventTimer.cancel();
                    }
                    IronSourceWebView.this.mCloseEventTimer = new CountDownTimer(2000L, 500L) { // from class: com.ironsource.sdk.controller.IronSourceWebView.SupersonicWebViewTouchListener.1
                        @Override // android.os.CountDownTimer
                        public void onTick(long j) {
                            Logger.i(IronSourceWebView.this.TAG, "Close Event Timer Tick " + j);
                        }

                        @Override // android.os.CountDownTimer
                        public void onFinish() {
                            Logger.i(IronSourceWebView.this.TAG, "Close Event Timer Finish");
                            if (IronSourceWebView.this.isRemoveCloseEventHandler) {
                                IronSourceWebView.this.isRemoveCloseEventHandler = false;
                            } else {
                                IronSourceWebView.this.engageEnd(Constants.ParametersKeys.FORCE_CLOSE);
                            }
                        }
                    }.start();
                }
            }
            return false;
        }
    }

    private void initLayout(Context context) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.mControllerLayout = new FrameLayout(context);
        this.mCustomViewContainer = new FrameLayout(context);
        this.mCustomViewContainer.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.mCustomViewContainer.setVisibility(8);
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        frameLayout.addView(this);
        this.mControllerLayout.addView(this.mCustomViewContainer, layoutParams);
        this.mControllerLayout.addView(frameLayout);
    }

    private void setWebViewSettings() {
        WebSettings settings = getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        if (Build.VERSION.SDK_INT >= 16) {
            try {
                getSettings().setAllowFileAccessFromFileURLs(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        settings.setBuiltInZoomControls(false);
        settings.setJavaScriptEnabled(true);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setGeolocationEnabled(true);
        settings.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");
        settings.setDomStorageEnabled(true);
        try {
            setDisplayZoomControls(settings);
            setMediaPlaybackJellyBean(settings);
        } catch (Throwable th) {
            Logger.e(this.TAG, "setWebSettings - " + th.toString());
        }
    }

    private void setDisplayZoomControls(WebSettings webSettings) {
        if (Build.VERSION.SDK_INT > 11) {
            webSettings.setDisplayZoomControls(false);
        }
    }

    @Override // android.webkit.WebView
    public WebBackForwardList saveState(Bundle bundle) {
        return super.saveState(bundle);
    }

    @SuppressLint({"NewApi"})
    private void setMediaPlaybackJellyBean(WebSettings webSettings) {
        if (Build.VERSION.SDK_INT >= 17) {
            webSettings.setMediaPlaybackRequiresUserGesture(false);
        }
    }

    @SuppressLint({"NewApi"})
    private void setWebDebuggingEnabled() {
        if (Build.VERSION.SDK_INT >= 19) {
            setWebContentsDebuggingEnabled(true);
        }
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.ironsource.sdk.controller.IronSourceWebView$2] */
    public void downloadController() {
        IronSourceStorageUtils.deleteFile(this.mCacheDirectory, "", Constants.MOBILE_CONTROLLER_HTML);
        String controllerUrl = SDKUtils.getControllerUrl();
        SSAFile sSAFile = new SSAFile(controllerUrl, "");
        this.mGlobalControllerTimer = new CountDownTimer(200000L, 1000L) { // from class: com.ironsource.sdk.controller.IronSourceWebView.2
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                Logger.i(IronSourceWebView.this.TAG, "Global Controller Timer Tick " + j);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                Logger.i(IronSourceWebView.this.TAG, "Global Controller Timer Finish");
                IronSourceWebView.this.mGlobalControllerTimeFinish = true;
            }
        }.start();
        if (!this.downloadManager.isMobileControllerThreadLive()) {
            Logger.i(this.TAG, "Download Mobile Controller: " + controllerUrl);
            this.downloadManager.downloadMobileControllerFile(sSAFile);
            return;
        }
        Logger.i(this.TAG, "Download Mobile Controller: already alive");
    }

    public void setDebugMode(int i) {
        mDebugMode = i;
    }

    public int getDebugMode() {
        return mDebugMode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldNotifyDeveloper(String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            Logger.d(this.TAG, "Trying to trigger a listener - no product was found");
            return false;
        }
        if (!str.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString()) ? !str.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString()) ? !str.equalsIgnoreCase(SSAEnums.ProductType.Banner.toString()) ? (str.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString()) || str.equalsIgnoreCase(SSAEnums.ProductType.OfferWallCredits.toString())) && this.mOnOfferWallListener != null : this.mDSBannerListener != null : this.mDSRewardedVideoListener != null : this.mDSInterstitialListener != null) {
            z = true;
        }
        if (!z) {
            Logger.d(this.TAG, "Trying to trigger a listener - no listener was found for product " + str);
        }
        return z;
    }

    public void setOrientationState(String str) {
        this.mOrientationState = str;
    }

    public String getOrientationState() {
        return this.mOrientationState;
    }

    private class ViewClient extends WebViewClient {
        private ViewClient() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            Logger.i("onPageStarted", str);
            super.onPageStarted(webView, str, bitmap);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            Logger.i("onPageFinished", str);
            if (str.contains("adUnit") || str.contains("index.html")) {
                IronSourceWebView.this.pageFinished();
            }
            super.onPageFinished(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            Logger.i("onReceivedError", str2 + " " + str);
            super.onReceivedError(webView, i, str, str2);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Logger.i("shouldOverrideUrlLoading", str);
            try {
                if (IronSourceWebView.this.handleSearchKeysURLs(str)) {
                    IronSourceWebView.this.interceptedUrlToStore();
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return super.shouldOverrideUrlLoading(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            boolean zContains;
            Logger.i("shouldInterceptRequest", str);
            try {
                zContains = new URL(str).getFile().contains("mraid.js");
            } catch (MalformedURLException unused) {
                zContains = false;
            }
            if (zContains) {
                String str2 = "file://" + IronSourceWebView.this.mCacheDirectory + File.separator + "mraid.js";
                try {
                    new FileInputStream(new File(str2));
                    return new WebResourceResponse("text/javascript", "UTF-8", getClass().getResourceAsStream(str2));
                } catch (FileNotFoundException unused2) {
                }
            }
            return super.shouldInterceptRequest(webView, str);
        }
    }

    private class ChromeClient extends WebChromeClient {
        private ChromeClient() {
        }

        @Override // android.webkit.WebChromeClient
        public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
            WebView webView2 = new WebView(webView.getContext());
            webView2.setWebChromeClient(this);
            webView2.setWebViewClient(new FrameBustWebViewClient());
            ((WebView.WebViewTransport) message.obj).setWebView(webView2);
            message.sendToTarget();
            Logger.i("onCreateWindow", "onCreateWindow");
            return true;
        }

        @Override // android.webkit.WebChromeClient
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            Logger.i("MyApplication", consoleMessage.message() + " -- From line " + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
            return true;
        }

        @Override // android.webkit.WebChromeClient
        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
            Logger.i("Test", "onShowCustomView");
            IronSourceWebView.this.setVisibility(8);
            if (IronSourceWebView.this.mCustomView != null) {
                Logger.i("Test", "mCustomView != null");
                customViewCallback.onCustomViewHidden();
                return;
            }
            Logger.i("Test", "mCustomView == null");
            IronSourceWebView.this.mCustomViewContainer.addView(view);
            IronSourceWebView.this.mCustomView = view;
            IronSourceWebView.this.mCustomViewCallback = customViewCallback;
            IronSourceWebView.this.mCustomViewContainer.setVisibility(0);
        }

        @Override // android.webkit.WebChromeClient
        public View getVideoLoadingProgressView() {
            FrameLayout frameLayout = new FrameLayout(IronSourceWebView.this.getCurrentActivityContext());
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            return frameLayout;
        }

        @Override // android.webkit.WebChromeClient
        public void onHideCustomView() {
            Logger.i("Test", "onHideCustomView");
            if (IronSourceWebView.this.mCustomView == null) {
                return;
            }
            IronSourceWebView.this.mCustomView.setVisibility(8);
            IronSourceWebView.this.mCustomViewContainer.removeView(IronSourceWebView.this.mCustomView);
            IronSourceWebView.this.mCustomView = null;
            IronSourceWebView.this.mCustomViewContainer.setVisibility(8);
            IronSourceWebView.this.mCustomViewCallback.onCustomViewHidden();
            IronSourceWebView.this.setVisibility(0);
        }
    }

    private class FrameBustWebViewClient extends WebViewClient {
        private FrameBustWebViewClient() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Context currentActivityContext = IronSourceWebView.this.getCurrentActivityContext();
            Intent intent = new Intent(currentActivityContext, (Class<?>) OpenUrlActivity.class);
            intent.putExtra(IronSourceWebView.EXTERNAL_URL, str);
            intent.putExtra(IronSourceWebView.SECONDARY_WEB_VIEW, false);
            currentActivityContext.startActivity(intent);
            return true;
        }
    }

    public class JSInterface {
        volatile int udiaResults = 0;

        @JavascriptInterface
        public void alert(String str) {
        }

        public JSInterface(Context context) {
        }

        @JavascriptInterface
        public void initController(String str) {
            Logger.i(IronSourceWebView.this.TAG, "initController(" + str + ")");
            SSAObj sSAObj = new SSAObj(str);
            if (sSAObj.containsKey(Constants.ParametersKeys.STAGE)) {
                String string = sSAObj.getString(Constants.ParametersKeys.STAGE);
                if (Constants.ParametersKeys.READY.equalsIgnoreCase(string)) {
                    handleControllerStageReady();
                } else if (Constants.ParametersKeys.LOADED.equalsIgnoreCase(string)) {
                    handleControllerStageLoaded();
                } else if (!Constants.ParametersKeys.FAILED.equalsIgnoreCase(string)) {
                    Logger.i(IronSourceWebView.this.TAG, "No STAGE mentioned! Should not get here!");
                } else {
                    handleControllerStageFailed();
                }
                IronSourceWebView.this.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (Build.VERSION.SDK_INT >= 16) {
                            try {
                                IronSourceWebView.this.getSettings().setAllowFileAccessFromFileURLs(false);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        }

        void handleControllerStageLoaded() {
            IronSourceWebView.this.mControllerState = SSAEnums.ControllerState.Loaded;
        }

        void handleControllerStageReady() {
            IronSourceWebView.this.mControllerState = SSAEnums.ControllerState.Ready;
            IronSourceWebView.this.mGlobalControllerTimer.cancel();
            if (IronSourceWebView.this.mLoadControllerTimer != null) {
                IronSourceWebView.this.mLoadControllerTimer.cancel();
            }
            IronSourceWebView.this.invokePendingCommands();
            IronSourceWebView ironSourceWebView = IronSourceWebView.this;
            ironSourceWebView.restoreState(ironSourceWebView.mSavedState);
        }

        void handleControllerStageFailed() {
            IronSourceWebView.this.mControllerState = SSAEnums.ControllerState.Failed;
            for (DemandSource demandSource : IronSourceWebView.this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.RewardedVideo)) {
                if (demandSource.getDemandSourceInitState() == 1) {
                    IronSourceWebView.this.sendProductErrorMessage(SSAEnums.ProductType.RewardedVideo, demandSource);
                }
            }
            for (DemandSource demandSource2 : IronSourceWebView.this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.Interstitial)) {
                if (demandSource2.getDemandSourceInitState() == 1) {
                    IronSourceWebView.this.sendProductErrorMessage(SSAEnums.ProductType.Interstitial, demandSource2);
                }
            }
            for (DemandSource demandSource3 : IronSourceWebView.this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.Banner)) {
                if (demandSource3.getDemandSourceInitState() == 1) {
                    IronSourceWebView.this.sendProductErrorMessage(SSAEnums.ProductType.Banner, demandSource3);
                }
            }
            if (IronSourceWebView.this.mOWmiss) {
                IronSourceWebView.this.sendProductErrorMessage(SSAEnums.ProductType.OfferWall, null);
            }
            if (IronSourceWebView.this.mOWCreditsMiss) {
                IronSourceWebView.this.sendProductErrorMessage(SSAEnums.ProductType.OfferWallCredits, null);
            }
        }

        /* JADX WARN: Code duplicated, block: B:10:0x0057  */
        @JavascriptInterface
        public void getDeviceStatus(String str) {
            Logger.i(IronSourceWebView.this.TAG, "getDeviceStatus(" + str + ")");
            String strExtractSuccessFunctionToCall = IronSourceWebView.this.extractSuccessFunctionToCall(str);
            String strExtractFailFunctionToCall = IronSourceWebView.this.extractFailFunctionToCall(str);
            Object[] objArr = new Object[2];
            IronSourceWebView ironSourceWebView = IronSourceWebView.this;
            Object[] deviceParams = ironSourceWebView.getDeviceParams(ironSourceWebView.getContext());
            String str2 = (String) deviceParams[0];
            if (((Boolean) deviceParams[1]).booleanValue()) {
                if (TextUtils.isEmpty(strExtractFailFunctionToCall)) {
                    strExtractSuccessFunctionToCall = null;
                } else {
                    strExtractSuccessFunctionToCall = strExtractFailFunctionToCall;
                }
            } else if (TextUtils.isEmpty(strExtractSuccessFunctionToCall)) {
                strExtractSuccessFunctionToCall = null;
            }
            if (TextUtils.isEmpty(strExtractSuccessFunctionToCall)) {
                return;
            }
            IronSourceWebView.this.injectJavascript(IronSourceWebView.this.generateJSToInject(strExtractSuccessFunctionToCall, str2, Constants.JSMethods.ON_GET_DEVICE_STATUS_SUCCESS, Constants.JSMethods.ON_GET_DEVICE_STATUS_FAIL));
        }

        @JavascriptInterface
        public void setMixedContentAlwaysAllow(String str) {
            Logger.i(IronSourceWebView.this.TAG, "setMixedContentAlwaysAllow(" + str + ")");
            IronSourceWebView.this.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.2
                @Override // java.lang.Runnable
                public void run() {
                    if (Build.VERSION.SDK_INT >= 21) {
                        IronSourceWebView.this.getSettings().setMixedContentMode(0);
                    }
                }
            });
        }

        @JavascriptInterface
        public void setAllowFileAccessFromFileURLs(String str) {
            Logger.i(IronSourceWebView.this.TAG, "setAllowFileAccessFromFileURLs(" + str + ")");
            final boolean z = new SSAObj(str).getBoolean(Constants.ParametersKeys.ALLOW_FILE_ACCESS);
            IronSourceWebView.this.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.3
                @Override // java.lang.Runnable
                public void run() {
                    if (Build.VERSION.SDK_INT >= 16) {
                        try {
                            IronSourceWebView.this.getSettings().setAllowFileAccessFromFileURLs(z);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

        @JavascriptInterface
        public void getControllerConfig(String str) {
            Logger.i(IronSourceWebView.this.TAG, "getControllerConfig(" + str + ")");
            String string = new SSAObj(str).getString(IronSourceWebView.JSON_KEY_SUCCESS);
            if (TextUtils.isEmpty(string)) {
                return;
            }
            String controllerConfig = SDKUtils.getControllerConfig();
            String testerParameters = SDKUtils.getTesterParameters();
            if (areTesterParametersValid(testerParameters)) {
                try {
                    controllerConfig = addTesterParametersToConfig(controllerConfig, testerParameters);
                } catch (JSONException unused) {
                    Logger.d(IronSourceWebView.this.TAG, "getControllerConfig Error while parsing Tester AB Group parameters");
                }
            }
            IronSourceWebView.this.injectJavascript(IronSourceWebView.this.generateJSToInject(string, controllerConfig));
        }

        @JavascriptInterface
        public String addTesterParametersToConfig(String str, String str2) throws JSONException {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = new JSONObject(str2);
            jSONObject.putOpt("testerABGroup", jSONObject2.get("testerABGroup"));
            jSONObject.putOpt("testFriendlyName", jSONObject2.get("testFriendlyName"));
            return jSONObject.toString();
        }

        @JavascriptInterface
        public boolean areTesterParametersValid(String str) {
            if (TextUtils.isEmpty(str) || str.contains("-1")) {
                return false;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                return (jSONObject.getString("testerABGroup").isEmpty() || jSONObject.getString("testFriendlyName").isEmpty()) ? false : true;
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        }

        /* JADX WARN: Code duplicated, block: B:10:0x0062  */
        @JavascriptInterface
        public void getApplicationInfo(String str) {
            Logger.i(IronSourceWebView.this.TAG, "getApplicationInfo(" + str + ")");
            String strExtractSuccessFunctionToCall = IronSourceWebView.this.extractSuccessFunctionToCall(str);
            String strExtractFailFunctionToCall = IronSourceWebView.this.extractFailFunctionToCall(str);
            SSAObj sSAObj = new SSAObj(str);
            Object[] objArr = new Object[2];
            Object[] applicationParams = IronSourceWebView.this.getApplicationParams(sSAObj.getString(Constants.ParametersKeys.PRODUCT_TYPE), SDKUtils.fetchDemandSourceId(sSAObj));
            String str2 = (String) applicationParams[0];
            if (((Boolean) applicationParams[1]).booleanValue()) {
                if (TextUtils.isEmpty(strExtractFailFunctionToCall)) {
                    strExtractSuccessFunctionToCall = null;
                } else {
                    strExtractSuccessFunctionToCall = strExtractFailFunctionToCall;
                }
            } else if (TextUtils.isEmpty(strExtractSuccessFunctionToCall)) {
                strExtractSuccessFunctionToCall = null;
            }
            if (TextUtils.isEmpty(strExtractSuccessFunctionToCall)) {
                return;
            }
            IronSourceWebView.this.injectJavascript(IronSourceWebView.this.generateJSToInject(strExtractSuccessFunctionToCall, str2, Constants.JSMethods.ON_GET_APPLICATION_INFO_SUCCESS, Constants.JSMethods.ON_GET_APPLICATION_INFO_FAIL));
        }

        /* JADX WARN: Code duplicated, block: B:10:0x0061  */
        @JavascriptInterface
        public void checkInstalledApps(String str) {
            Logger.i(IronSourceWebView.this.TAG, "checkInstalledApps(" + str + ")");
            String strExtractSuccessFunctionToCall = IronSourceWebView.this.extractSuccessFunctionToCall(str);
            String strExtractFailFunctionToCall = IronSourceWebView.this.extractFailFunctionToCall(str);
            SSAObj sSAObj = new SSAObj(str);
            Object[] appsStatus = IronSourceWebView.this.getAppsStatus(sSAObj.getString(IronSourceWebView.APP_IDS), sSAObj.getString(IronSourceWebView.REQUEST_ID));
            String str2 = (String) appsStatus[0];
            if (((Boolean) appsStatus[1]).booleanValue()) {
                if (TextUtils.isEmpty(strExtractFailFunctionToCall)) {
                    strExtractSuccessFunctionToCall = null;
                } else {
                    strExtractSuccessFunctionToCall = strExtractFailFunctionToCall;
                }
            } else if (TextUtils.isEmpty(strExtractSuccessFunctionToCall)) {
                strExtractSuccessFunctionToCall = null;
            }
            if (TextUtils.isEmpty(strExtractSuccessFunctionToCall)) {
                return;
            }
            IronSourceWebView.this.injectJavascript(IronSourceWebView.this.generateJSToInject(strExtractSuccessFunctionToCall, str2, Constants.JSMethods.ON_CHECK_INSTALLED_APPS_SUCCESS, Constants.JSMethods.ON_CHECK_INSTALLED_APPS_FAIL));
        }

        @JavascriptInterface
        public void saveFile(String str) {
            Logger.i(IronSourceWebView.this.TAG, "saveFile(" + str + ")");
            SSAFile sSAFile = new SSAFile(str);
            if (DeviceStatus.getAvailableMemorySizeInMegaBytes(IronSourceWebView.this.mCacheDirectory) <= 0) {
                IronSourceWebView.this.responseBack(str, false, DownloadManager.NO_DISK_SPACE, null);
                return;
            }
            if (!SDKUtils.isExternalStorageAvailable()) {
                IronSourceWebView.this.responseBack(str, false, DownloadManager.STORAGE_UNAVAILABLE, null);
                return;
            }
            if (IronSourceStorageUtils.isFileCached(IronSourceWebView.this.mCacheDirectory, sSAFile)) {
                IronSourceWebView.this.responseBack(str, false, DownloadManager.FILE_ALREADY_EXIST, null);
                return;
            }
            if (!ConnectivityService.isConnected(IronSourceWebView.this.getContext())) {
                IronSourceWebView.this.responseBack(str, false, DownloadManager.NO_NETWORK_CONNECTION, null);
                return;
            }
            IronSourceWebView.this.responseBack(str, true, null, null);
            String lastUpdateTime = sSAFile.getLastUpdateTime();
            if (lastUpdateTime != null) {
                String strValueOf = String.valueOf(lastUpdateTime);
                if (!TextUtils.isEmpty(strValueOf)) {
                    String path = sSAFile.getPath();
                    if (path.contains(com.appsflyer.share.Constants.URL_PATH_DELIMITER)) {
                        String[] strArrSplit = sSAFile.getPath().split(com.appsflyer.share.Constants.URL_PATH_DELIMITER);
                        path = strArrSplit[strArrSplit.length - 1];
                    }
                    IronSourceSharedPrefHelper.getSupersonicPrefHelper().setCampaignLastUpdate(path, strValueOf);
                }
            }
            IronSourceWebView.this.downloadManager.downloadFile(sSAFile);
        }

        @JavascriptInterface
        public void adUnitsReady(String str) {
            Logger.i(IronSourceWebView.this.TAG, "adUnitsReady(" + str + ")");
            final String strFetchDemandSourceId = SDKUtils.fetchDemandSourceId(new SSAObj(str));
            final AdUnitsReady adUnitsReady = new AdUnitsReady(str);
            if (!adUnitsReady.isNumOfAdUnitsExist()) {
                IronSourceWebView.this.responseBack(str, false, Constants.ErrorCodes.NUM_OF_AD_UNITS_DO_NOT_EXIST, null);
                return;
            }
            IronSourceWebView.this.responseBack(str, true, null, null);
            String productType = adUnitsReady.getProductType();
            if (SSAEnums.ProductType.RewardedVideo.toString().equalsIgnoreCase(productType) && IronSourceWebView.this.shouldNotifyDeveloper(productType)) {
                IronSourceWebView.this.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.4
                    @Override // java.lang.Runnable
                    public void run() {
                        if (Integer.parseInt(adUnitsReady.getNumOfAdUnits()) > 0) {
                            Log.d(IronSourceWebView.this.TAG, "onRVInitSuccess()");
                            IronSourceWebView.this.mDSRewardedVideoListener.onAdProductInitSuccess(SSAEnums.ProductType.RewardedVideo, strFetchDemandSourceId, adUnitsReady);
                        } else {
                            IronSourceWebView.this.mDSRewardedVideoListener.onRVNoMoreOffers(strFetchDemandSourceId);
                        }
                    }
                });
            }
        }

        @JavascriptInterface
        public void iabTokenAPI(String str) {
            try {
                Logger.i(IronSourceWebView.this.TAG, "iabTokenAPI(" + str + ")");
                IronSourceWebView.this.mTokenJSAdapter.call(new SSAObj(str).toString(), new JSCallbackTask());
            } catch (Exception e) {
                e.printStackTrace();
                Logger.i(IronSourceWebView.this.TAG, "iabTokenAPI failed with exception " + e.getMessage());
            }
        }

        @JavascriptInterface
        public void deleteFolder(String str) {
            Logger.i(IronSourceWebView.this.TAG, "deleteFolder(" + str + ")");
            SSAFile sSAFile = new SSAFile(str);
            if (!IronSourceStorageUtils.isPathExist(IronSourceWebView.this.mCacheDirectory, sSAFile.getPath())) {
                IronSourceWebView.this.responseBack(str, false, Constants.ErrorCodes.FOLDER_NOT_EXIST_MSG, "1");
            } else {
                IronSourceWebView.this.responseBack(str, IronSourceStorageUtils.deleteFolder(IronSourceWebView.this.mCacheDirectory, sSAFile.getPath()), null, null);
            }
        }

        @JavascriptInterface
        public void deleteFile(String str) {
            Logger.i(IronSourceWebView.this.TAG, "deleteFile(" + str + ")");
            SSAFile sSAFile = new SSAFile(str);
            if (!IronSourceStorageUtils.isPathExist(IronSourceWebView.this.mCacheDirectory, sSAFile.getPath())) {
                IronSourceWebView.this.responseBack(str, false, Constants.ErrorCodes.FILE_NOT_EXIST_MSG, "1");
            } else {
                IronSourceWebView.this.responseBack(str, IronSourceStorageUtils.deleteFile(IronSourceWebView.this.mCacheDirectory, sSAFile.getPath(), sSAFile.getFile()), null, null);
            }
        }

        @JavascriptInterface
        public void displayWebView(String str) {
            Intent intent;
            Logger.i(IronSourceWebView.this.TAG, "displayWebView(" + str + ")");
            IronSourceWebView.this.responseBack(str, true, null, null);
            SSAObj sSAObj = new SSAObj(str);
            boolean zBooleanValue = ((Boolean) sSAObj.get("display")).booleanValue();
            String string = sSAObj.getString(Constants.ParametersKeys.PRODUCT_TYPE);
            boolean z = sSAObj.getBoolean(Constants.ParametersKeys.IS_STANDALONE_VIEW);
            String strFetchDemandSourceId = SDKUtils.fetchDemandSourceId(sSAObj);
            if (zBooleanValue) {
                IronSourceWebView.this.mIsImmersive = sSAObj.getBoolean(Constants.ParametersKeys.IMMERSIVE);
                boolean z2 = sSAObj.getBoolean(Constants.ParametersKeys.ACTIVITY_THEME_TRANSLUCENT);
                if (IronSourceWebView.this.getState() == State.Display) {
                    Logger.i(IronSourceWebView.this.TAG, "State: " + IronSourceWebView.this.mState);
                    return;
                }
                IronSourceWebView.this.setState(State.Display);
                Logger.i(IronSourceWebView.this.TAG, "State: " + IronSourceWebView.this.mState);
                Context currentActivityContext = IronSourceWebView.this.getCurrentActivityContext();
                String orientationState = IronSourceWebView.this.getOrientationState();
                int applicationRotation = DeviceStatus.getApplicationRotation(currentActivityContext);
                if (z) {
                    ControllerView controllerView = new ControllerView(currentActivityContext);
                    controllerView.addView(IronSourceWebView.this.mControllerLayout);
                    controllerView.showInterstitial(IronSourceWebView.this);
                    return;
                }
                if (z2) {
                    intent = new Intent(currentActivityContext, (Class<?>) InterstitialActivity.class);
                } else {
                    intent = new Intent(currentActivityContext, (Class<?>) ControllerActivity.class);
                }
                if (SSAEnums.ProductType.RewardedVideo.toString().equalsIgnoreCase(string)) {
                    if ("application".equals(orientationState)) {
                        orientationState = SDKUtils.translateRequestedOrientation(DeviceStatus.getActivityRequestedOrientation(IronSourceWebView.this.getCurrentActivityContext()));
                    }
                    intent.putExtra(Constants.ParametersKeys.PRODUCT_TYPE, SSAEnums.ProductType.RewardedVideo.toString());
                    IronSourceWebView.this.mSavedState.adOpened(SSAEnums.ProductType.RewardedVideo.ordinal());
                    IronSourceWebView.this.mSavedState.setDisplayedDemandSourceId(strFetchDemandSourceId);
                    if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.RewardedVideo.toString())) {
                        IronSourceWebView.this.mDSRewardedVideoListener.onAdProductOpen(SSAEnums.ProductType.RewardedVideo, strFetchDemandSourceId);
                    }
                } else if (SSAEnums.ProductType.OfferWall.toString().equalsIgnoreCase(string)) {
                    intent.putExtra(Constants.ParametersKeys.PRODUCT_TYPE, SSAEnums.ProductType.OfferWall.toString());
                    IronSourceWebView.this.mSavedState.adOpened(SSAEnums.ProductType.OfferWall.ordinal());
                } else if (SSAEnums.ProductType.Interstitial.toString().equalsIgnoreCase(string)) {
                    if ("application".equals(orientationState)) {
                        orientationState = SDKUtils.translateRequestedOrientation(DeviceStatus.getActivityRequestedOrientation(IronSourceWebView.this.getCurrentActivityContext()));
                    }
                    intent.putExtra(Constants.ParametersKeys.PRODUCT_TYPE, SSAEnums.ProductType.Interstitial.toString());
                }
                intent.setFlags(DriveFile.MODE_WRITE_ONLY);
                intent.putExtra(Constants.ParametersKeys.IMMERSIVE, IronSourceWebView.this.mIsImmersive);
                intent.putExtra(Constants.ParametersKeys.ORIENTATION_SET_FLAG, orientationState);
                intent.putExtra(Constants.ParametersKeys.ROTATION_SET_FLAG, applicationRotation);
                currentActivityContext.startActivity(intent);
                return;
            }
            IronSourceWebView.this.setState(State.Gone);
            IronSourceWebView.this.closeWebView();
        }

        @JavascriptInterface
        public void getOrientation(String str) {
            String strExtractSuccessFunctionToCall = IronSourceWebView.this.extractSuccessFunctionToCall(str);
            String string = SDKUtils.getOrientation(IronSourceWebView.this.getCurrentActivityContext()).toString();
            if (TextUtils.isEmpty(strExtractSuccessFunctionToCall)) {
                return;
            }
            IronSourceWebView.this.injectJavascript(IronSourceWebView.this.generateJSToInject(strExtractSuccessFunctionToCall, string, Constants.JSMethods.ON_GET_ORIENTATION_SUCCESS, Constants.JSMethods.ON_GET_ORIENTATION_FAIL));
        }

        @JavascriptInterface
        public void setOrientation(String str) {
            Logger.i(IronSourceWebView.this.TAG, "setOrientation(" + str + ")");
            String string = new SSAObj(str).getString("orientation");
            IronSourceWebView.this.setOrientationState(string);
            int applicationRotation = DeviceStatus.getApplicationRotation(IronSourceWebView.this.getCurrentActivityContext());
            if (IronSourceWebView.this.mChangeListener != null) {
                IronSourceWebView.this.mChangeListener.onOrientationChanged(string, applicationRotation);
            }
        }

        @JavascriptInterface
        public void getCachedFilesMap(String str) {
            Logger.i(IronSourceWebView.this.TAG, "getCachedFilesMap(" + str + ")");
            String strExtractSuccessFunctionToCall = IronSourceWebView.this.extractSuccessFunctionToCall(str);
            if (TextUtils.isEmpty(strExtractSuccessFunctionToCall)) {
                return;
            }
            SSAObj sSAObj = new SSAObj(str);
            if (!sSAObj.containsKey("path")) {
                IronSourceWebView.this.responseBack(str, false, Constants.ErrorCodes.PATH_KEY_DOES_NOT_EXIST, null);
                return;
            }
            String str2 = (String) sSAObj.get("path");
            if (!IronSourceStorageUtils.isPathExist(IronSourceWebView.this.mCacheDirectory, str2)) {
                IronSourceWebView.this.responseBack(str, false, Constants.ErrorCodes.PATH_FILE_DOES_NOT_EXIST_ON_DISK, null);
                return;
            }
            IronSourceWebView.this.injectJavascript(IronSourceWebView.this.generateJSToInject(strExtractSuccessFunctionToCall, IronSourceStorageUtils.getCachedFilesMap(IronSourceWebView.this.mCacheDirectory, str2), Constants.JSMethods.ON_GET_CACHED_FILES_MAP_SUCCESS, Constants.JSMethods.ON_GET_CACHED_FILES_MAP_FAIL));
        }

        private void callJavaScriptFunction(String str, String str2) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            IronSourceWebView.this.injectJavascript(IronSourceWebView.this.generateJSToInject(str, str2));
        }

        @JavascriptInterface
        public void getDemandSourceState(String str) {
            String strExtractFailFunctionToCall;
            Logger.i(IronSourceWebView.this.TAG, "getMediationState(" + str + ")");
            SSAObj sSAObj = new SSAObj(str);
            String string = sSAObj.getString("demandSourceName");
            String strFetchDemandSourceId = SDKUtils.fetchDemandSourceId(sSAObj);
            String string2 = sSAObj.getString(Constants.ParametersKeys.PRODUCT_TYPE);
            if (string2 == null || string == null) {
                return;
            }
            try {
                SSAEnums.ProductType productType = SDKUtils.getProductType(string2);
                if (productType != null) {
                    DemandSource demandSourceById = IronSourceWebView.this.mDemandSourceManager.getDemandSourceById(productType, strFetchDemandSourceId);
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(Constants.ParametersKeys.PRODUCT_TYPE, string2);
                    jSONObject.put("demandSourceName", string);
                    jSONObject.put("demandSourceId", strFetchDemandSourceId);
                    if (demandSourceById == null || demandSourceById.isMediationState(-1)) {
                        strExtractFailFunctionToCall = IronSourceWebView.this.extractFailFunctionToCall(str);
                    } else {
                        strExtractFailFunctionToCall = IronSourceWebView.this.extractSuccessFunctionToCall(str);
                        jSONObject.put("state", demandSourceById.getMediationState());
                    }
                    callJavaScriptFunction(strExtractFailFunctionToCall, jSONObject.toString());
                }
            } catch (Exception e) {
                IronSourceWebView.this.responseBack(str, false, e.getMessage(), null);
                e.printStackTrace();
            }
        }

        @JavascriptInterface
        public void adCredited(final String str) {
            final String string;
            final boolean z;
            final boolean z2;
            Log.d(IronSourceWebView.this.PUB_TAG, "adCredited(" + str + ")");
            SSAObj sSAObj = new SSAObj(str);
            String string2 = sSAObj.getString(Constants.ParametersKeys.CREDITS);
            boolean z3 = false;
            final int i = string2 != null ? Integer.parseInt(string2) : 0;
            final String strFetchDemandSourceId = SDKUtils.fetchDemandSourceId(sSAObj);
            final String string3 = sSAObj.getString(Constants.ParametersKeys.PRODUCT_TYPE);
            if (TextUtils.isEmpty(string3)) {
                Log.d(IronSourceWebView.this.PUB_TAG, "adCredited | not product NAME !!!!");
            }
            if (SSAEnums.ProductType.Interstitial.toString().equalsIgnoreCase(string3)) {
                handleAdCreditedOnInterstitial(strFetchDemandSourceId, i);
                return;
            }
            String string4 = sSAObj.getString(Constants.ParametersKeys.TOTAL);
            final int i2 = string4 != null ? Integer.parseInt(string4) : 0;
            sSAObj.getBoolean("externalPoll");
            if (!SSAEnums.ProductType.OfferWall.toString().equalsIgnoreCase(string3)) {
                string = null;
                z = false;
                z2 = false;
            } else {
                if (sSAObj.isNull(InAppPurchaseMetaData.KEY_SIGNATURE) || sSAObj.isNull("timestamp") || sSAObj.isNull("totalCreditsFlag")) {
                    IronSourceWebView.this.responseBack(str, false, "One of the keys are missing: signature/timestamp/totalCreditsFlag", null);
                    return;
                }
                if (sSAObj.getString(InAppPurchaseMetaData.KEY_SIGNATURE).equalsIgnoreCase(SDKUtils.getMD5(string4 + IronSourceWebView.this.mApplicationKey + IronSourceWebView.this.mUserId))) {
                    z3 = true;
                } else {
                    IronSourceWebView.this.responseBack(str, false, "Controller signature is not equal to SDK signature", null);
                }
                boolean z4 = sSAObj.getBoolean("totalCreditsFlag");
                string = sSAObj.getString("timestamp");
                z2 = z4;
                z = z3;
            }
            if (IronSourceWebView.this.shouldNotifyDeveloper(string3)) {
                IronSourceWebView.this.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.5
                    @Override // java.lang.Runnable
                    public void run() {
                        if (string3.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString())) {
                            IronSourceWebView.this.mDSRewardedVideoListener.onRVAdCredited(strFetchDemandSourceId, i);
                            return;
                        }
                        if (string3.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString()) && z && IronSourceWebView.this.mOnOfferWallListener.onOWAdCredited(i, i2, z2) && !TextUtils.isEmpty(string)) {
                            if (IronSourceSharedPrefHelper.getSupersonicPrefHelper().setLatestCompeltionsTime(string, IronSourceWebView.this.mApplicationKey, IronSourceWebView.this.mUserId)) {
                                IronSourceWebView.this.responseBack(str, true, null, null);
                            } else {
                                IronSourceWebView.this.responseBack(str, false, "Time Stamp could not be stored", null);
                            }
                        }
                    }
                });
            }
        }

        private void handleAdCreditedOnInterstitial(final String str, final int i) {
            DemandSource demandSourceById;
            if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString()) && (demandSourceById = IronSourceWebView.this.mDemandSourceManager.getDemandSourceById(SSAEnums.ProductType.Interstitial, str)) != null && demandSourceById.isRewarded()) {
                IronSourceWebView.this.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.6
                    @Override // java.lang.Runnable
                    public void run() {
                        IronSourceWebView.this.mDSInterstitialListener.onInterstitialAdRewarded(str, i);
                    }
                });
            }
        }

        @JavascriptInterface
        public void removeCloseEventHandler(String str) {
            Logger.i(IronSourceWebView.this.TAG, "removeCloseEventHandler(" + str + ")");
            if (IronSourceWebView.this.mCloseEventTimer != null) {
                IronSourceWebView.this.mCloseEventTimer.cancel();
            }
            IronSourceWebView.this.isRemoveCloseEventHandler = true;
        }

        @JavascriptInterface
        public void onGetDeviceStatusSuccess(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onGetDeviceStatusSuccess(" + str + ")");
            IronSourceWebView.this.responseBack(str, true, null, null);
            IronSourceWebView.this.toastingErrMsg(Constants.JSMethods.ON_GET_DEVICE_STATUS_SUCCESS, str);
        }

        @JavascriptInterface
        public void onGetDeviceStatusFail(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onGetDeviceStatusFail(" + str + ")");
            IronSourceWebView.this.responseBack(str, true, null, null);
            IronSourceWebView.this.toastingErrMsg(Constants.JSMethods.ON_GET_DEVICE_STATUS_FAIL, str);
        }

        @JavascriptInterface
        public void onInitRewardedVideoSuccess(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onInitRewardedVideoSuccess(" + str + ")");
            IronSourceSharedPrefHelper.getSupersonicPrefHelper().setSSABCParameters(new SSABCParameters(str));
            IronSourceWebView.this.responseBack(str, true, null, null);
            IronSourceWebView.this.toastingErrMsg(Constants.JSMethods.ON_INIT_REWARDED_VIDEO_SUCCESS, str);
        }

        @JavascriptInterface
        public void onInitRewardedVideoFail(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onInitRewardedVideoFail(" + str + ")");
            SSAObj sSAObj = new SSAObj(str);
            final String string = sSAObj.getString("errMsg");
            final String strFetchDemandSourceId = SDKUtils.fetchDemandSourceId(sSAObj);
            DemandSource demandSourceById = IronSourceWebView.this.mDemandSourceManager.getDemandSourceById(SSAEnums.ProductType.RewardedVideo, strFetchDemandSourceId);
            if (demandSourceById != null) {
                demandSourceById.setDemandSourceInitState(3);
            }
            if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.RewardedVideo.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.7
                    @Override // java.lang.Runnable
                    public void run() {
                        String str2 = string;
                        if (str2 == null) {
                            str2 = "We're sorry, some error occurred. we will investigate it";
                        }
                        Log.d(IronSourceWebView.this.TAG, "onRVInitFail(message:" + str2 + ")");
                        IronSourceWebView.this.mDSRewardedVideoListener.onAdProductInitFailed(SSAEnums.ProductType.RewardedVideo, strFetchDemandSourceId, str2);
                    }
                });
            }
            IronSourceWebView.this.responseBack(str, true, null, null);
            IronSourceWebView.this.toastingErrMsg(Constants.JSMethods.ON_INIT_REWARDED_VIDEO_FAIL, str);
        }

        @JavascriptInterface
        public void onGetApplicationInfoSuccess(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onGetApplicationInfoSuccess(" + str + ")");
            IronSourceWebView.this.responseBack(str, true, null, null);
            IronSourceWebView.this.toastingErrMsg(Constants.JSMethods.ON_GET_APPLICATION_INFO_SUCCESS, str);
        }

        @JavascriptInterface
        public void onGetApplicationInfoFail(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onGetApplicationInfoFail(" + str + ")");
            IronSourceWebView.this.responseBack(str, true, null, null);
            IronSourceWebView.this.toastingErrMsg(Constants.JSMethods.ON_GET_APPLICATION_INFO_FAIL, str);
        }

        @JavascriptInterface
        public void onShowRewardedVideoSuccess(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onShowRewardedVideoSuccess(" + str + ")");
            IronSourceWebView.this.responseBack(str, true, null, null);
            IronSourceWebView.this.toastingErrMsg(Constants.JSMethods.ON_SHOW_REWARDED_VIDEO_SUCCESS, str);
        }

        @JavascriptInterface
        public void onShowRewardedVideoFail(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onShowRewardedVideoFail(" + str + ")");
            SSAObj sSAObj = new SSAObj(str);
            final String string = sSAObj.getString("errMsg");
            final String strFetchDemandSourceId = SDKUtils.fetchDemandSourceId(sSAObj);
            if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.RewardedVideo.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.8
                    @Override // java.lang.Runnable
                    public void run() {
                        String str2 = string;
                        if (str2 == null) {
                            str2 = "We're sorry, some error occurred. we will investigate it";
                        }
                        Log.d(IronSourceWebView.this.TAG, "onRVShowFail(message:" + string + ")");
                        IronSourceWebView.this.mDSRewardedVideoListener.onRVShowFail(strFetchDemandSourceId, str2);
                    }
                });
            }
            IronSourceWebView.this.responseBack(str, true, null, null);
            IronSourceWebView.this.toastingErrMsg(Constants.JSMethods.ON_SHOW_REWARDED_VIDEO_FAIL, str);
        }

        @JavascriptInterface
        public void onGetCachedFilesMapSuccess(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onGetCachedFilesMapSuccess(" + str + ")");
            IronSourceWebView.this.responseBack(str, true, null, null);
            IronSourceWebView.this.toastingErrMsg(Constants.JSMethods.ON_GET_CACHED_FILES_MAP_SUCCESS, str);
        }

        @JavascriptInterface
        public void onGetCachedFilesMapFail(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onGetCachedFilesMapFail(" + str + ")");
            IronSourceWebView.this.responseBack(str, true, null, null);
            IronSourceWebView.this.toastingErrMsg(Constants.JSMethods.ON_GET_CACHED_FILES_MAP_FAIL, str);
        }

        @JavascriptInterface
        public void onShowOfferWallSuccess(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onShowOfferWallSuccess(" + str + ")");
            IronSourceWebView.this.mSavedState.adOpened(SSAEnums.ProductType.OfferWall.ordinal());
            final String valueFromJsonObject = SDKUtils.getValueFromJsonObject(str, Constants.PLACEMENT_ID);
            if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.9
                    @Override // java.lang.Runnable
                    public void run() {
                        IronSourceWebView.this.mOnOfferWallListener.onOWShowSuccess(valueFromJsonObject);
                    }
                });
            }
            IronSourceWebView.this.responseBack(str, true, null, null);
            IronSourceWebView.this.toastingErrMsg(Constants.JSMethods.ON_SHOW_OFFER_WALL_SUCCESS, str);
        }

        @JavascriptInterface
        public void onShowOfferWallFail(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onShowOfferWallFail(" + str + ")");
            final String string = new SSAObj(str).getString("errMsg");
            if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.10
                    @Override // java.lang.Runnable
                    public void run() {
                        String str2 = string;
                        if (str2 == null) {
                            str2 = "We're sorry, some error occurred. we will investigate it";
                        }
                        IronSourceWebView.this.mOnOfferWallListener.onOWShowFail(str2);
                    }
                });
            }
            IronSourceWebView.this.responseBack(str, true, null, null);
            IronSourceWebView.this.toastingErrMsg(Constants.JSMethods.ON_SHOW_OFFER_WALL_FAIL, str);
        }

        @JavascriptInterface
        public void onInitInterstitialSuccess(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onInitInterstitialSuccess()");
            IronSourceWebView.this.toastingErrMsg(Constants.JSMethods.ON_INIT_INTERSTITIAL_SUCCESS, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            final String strFetchDemandSourceId = SDKUtils.fetchDemandSourceId(new SSAObj(str));
            if (TextUtils.isEmpty(strFetchDemandSourceId)) {
                Logger.i(IronSourceWebView.this.TAG, "onInitInterstitialSuccess failed with no demand source");
            } else if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.11
                    @Override // java.lang.Runnable
                    public void run() {
                        Log.d(IronSourceWebView.this.TAG, "onInterstitialInitSuccess()");
                        IronSourceWebView.this.mDSInterstitialListener.onAdProductInitSuccess(SSAEnums.ProductType.Interstitial, strFetchDemandSourceId, null);
                    }
                });
            }
        }

        @JavascriptInterface
        public void onInitInterstitialFail(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onInitInterstitialFail(" + str + ")");
            SSAObj sSAObj = new SSAObj(str);
            final String string = sSAObj.getString("errMsg");
            final String strFetchDemandSourceId = SDKUtils.fetchDemandSourceId(sSAObj);
            if (TextUtils.isEmpty(strFetchDemandSourceId)) {
                Logger.i(IronSourceWebView.this.TAG, "onInitInterstitialSuccess failed with no demand source");
                return;
            }
            DemandSource demandSourceById = IronSourceWebView.this.mDemandSourceManager.getDemandSourceById(SSAEnums.ProductType.Interstitial, strFetchDemandSourceId);
            if (demandSourceById != null) {
                demandSourceById.setDemandSourceInitState(3);
            }
            if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.12
                    @Override // java.lang.Runnable
                    public void run() {
                        String str2 = string;
                        if (str2 == null) {
                            str2 = "We're sorry, some error occurred. we will investigate it";
                        }
                        Log.d(IronSourceWebView.this.TAG, "onInterstitialInitFail(message:" + str2 + ")");
                        IronSourceWebView.this.mDSInterstitialListener.onAdProductInitFailed(SSAEnums.ProductType.Interstitial, strFetchDemandSourceId, str2);
                    }
                });
            }
            IronSourceWebView.this.responseBack(str, true, null, null);
            IronSourceWebView.this.toastingErrMsg(Constants.JSMethods.ON_INIT_INTERSTITIAL_FAIL, str);
        }

        @JavascriptInterface
        public void adClicked(String str) {
            Logger.i(IronSourceWebView.this.TAG, "adClicked(" + str + ")");
            SSAObj sSAObj = new SSAObj(str);
            String string = sSAObj.getString(Constants.ParametersKeys.PRODUCT_TYPE);
            final String strFetchDemandSourceId = SDKUtils.fetchDemandSourceId(sSAObj);
            if (TextUtils.isEmpty(strFetchDemandSourceId)) {
                return;
            }
            final SSAEnums.ProductType stringProductTypeAsEnum = IronSourceWebView.this.getStringProductTypeAsEnum(string);
            final DSAdProductListener adProductListenerByProductType = IronSourceWebView.this.getAdProductListenerByProductType(stringProductTypeAsEnum);
            if (stringProductTypeAsEnum == null || adProductListenerByProductType == null) {
                return;
            }
            IronSourceWebView.this.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.13
                @Override // java.lang.Runnable
                public void run() {
                    adProductListenerByProductType.onAdProductClick(stringProductTypeAsEnum, strFetchDemandSourceId);
                }
            });
        }

        @JavascriptInterface
        public void onShowInterstitialSuccess(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onShowInterstitialSuccess(" + str + ")");
            IronSourceWebView.this.responseBack(str, true, null, null);
            final String strFetchDemandSourceId = SDKUtils.fetchDemandSourceId(new SSAObj(str));
            if (TextUtils.isEmpty(strFetchDemandSourceId)) {
                Logger.i(IronSourceWebView.this.TAG, "onShowInterstitialSuccess called with no demand");
                return;
            }
            IronSourceWebView.this.mSavedState.adOpened(SSAEnums.ProductType.Interstitial.ordinal());
            IronSourceWebView.this.mSavedState.setDisplayedDemandSourceId(strFetchDemandSourceId);
            if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.14
                    @Override // java.lang.Runnable
                    public void run() {
                        IronSourceWebView.this.mDSInterstitialListener.onAdProductOpen(SSAEnums.ProductType.Interstitial, strFetchDemandSourceId);
                        IronSourceWebView.this.mDSInterstitialListener.onInterstitialShowSuccess(strFetchDemandSourceId);
                    }
                });
                IronSourceWebView.this.toastingErrMsg(Constants.JSMethods.ON_SHOW_INTERSTITIAL_SUCCESS, str);
            }
            setInterstitialAvailability(strFetchDemandSourceId, false);
        }

        private void setInterstitialAvailability(String str, boolean z) {
            DemandSource demandSourceById = IronSourceWebView.this.mDemandSourceManager.getDemandSourceById(SSAEnums.ProductType.Interstitial, str);
            if (demandSourceById != null) {
                demandSourceById.setAvailabilityState(z);
            }
        }

        @JavascriptInterface
        public void onInitOfferWallSuccess(String str) {
            IronSourceWebView.this.toastingErrMsg(Constants.JSMethods.ON_INIT_OFFERWALL_SUCCESS, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            IronSourceWebView.this.mSavedState.setOfferwallInitSuccess(true);
            if (IronSourceWebView.this.mSavedState.reportInitOfferwall()) {
                IronSourceWebView.this.mSavedState.setOfferwallReportInit(false);
                if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
                    IronSourceWebView.this.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.15
                        @Override // java.lang.Runnable
                        public void run() {
                            Log.d(IronSourceWebView.this.TAG, "onOfferWallInitSuccess()");
                            IronSourceWebView.this.mOnOfferWallListener.onOfferwallInitSuccess();
                        }
                    });
                }
            }
        }

        @JavascriptInterface
        public void onInitOfferWallFail(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onInitOfferWallFail(" + str + ")");
            IronSourceWebView.this.mSavedState.setOfferwallInitSuccess(false);
            final String string = new SSAObj(str).getString("errMsg");
            if (IronSourceWebView.this.mSavedState.reportInitOfferwall()) {
                IronSourceWebView.this.mSavedState.setOfferwallReportInit(false);
                if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
                    IronSourceWebView.this.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.16
                        @Override // java.lang.Runnable
                        public void run() {
                            String str2 = string;
                            if (str2 == null) {
                                str2 = "We're sorry, some error occurred. we will investigate it";
                            }
                            Log.d(IronSourceWebView.this.TAG, "onOfferWallInitFail(message:" + str2 + ")");
                            IronSourceWebView.this.mOnOfferWallListener.onOfferwallInitFail(str2);
                        }
                    });
                }
            }
            IronSourceWebView.this.responseBack(str, true, null, null);
            IronSourceWebView.this.toastingErrMsg(Constants.JSMethods.ON_INIT_OFFERWALL_FAIL, str);
        }

        @JavascriptInterface
        public void onLoadInterstitialSuccess(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onLoadInterstitialSuccess(" + str + ")");
            final String strFetchDemandSourceId = SDKUtils.fetchDemandSourceId(new SSAObj(str));
            setInterstitialAvailability(strFetchDemandSourceId, true);
            IronSourceWebView.this.responseBack(str, true, null, null);
            if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.17
                    @Override // java.lang.Runnable
                    public void run() {
                        IronSourceWebView.this.mDSInterstitialListener.onInterstitialLoadSuccess(strFetchDemandSourceId);
                    }
                });
            }
            IronSourceWebView.this.toastingErrMsg(Constants.JSMethods.ON_LOAD_INTERSTITIAL_SUCCESS, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        }

        @JavascriptInterface
        public void onLoadInterstitialFail(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onLoadInterstitialFail(" + str + ")");
            SSAObj sSAObj = new SSAObj(str);
            final String string = sSAObj.getString("errMsg");
            final String strFetchDemandSourceId = SDKUtils.fetchDemandSourceId(sSAObj);
            IronSourceWebView.this.responseBack(str, true, null, null);
            if (TextUtils.isEmpty(strFetchDemandSourceId)) {
                return;
            }
            setInterstitialAvailability(strFetchDemandSourceId, false);
            if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.18
                    @Override // java.lang.Runnable
                    public void run() {
                        String str2 = string;
                        if (str2 == null) {
                            str2 = "We're sorry, some error occurred. we will investigate it";
                        }
                        IronSourceWebView.this.mDSInterstitialListener.onInterstitialLoadFailed(strFetchDemandSourceId, str2);
                    }
                });
            }
            IronSourceWebView.this.toastingErrMsg(Constants.JSMethods.ON_LOAD_INTERSTITIAL_FAIL, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        }

        @JavascriptInterface
        public void onShowInterstitialFail(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onShowInterstitialFail(" + str + ")");
            SSAObj sSAObj = new SSAObj(str);
            final String string = sSAObj.getString("errMsg");
            final String strFetchDemandSourceId = SDKUtils.fetchDemandSourceId(sSAObj);
            IronSourceWebView.this.responseBack(str, true, null, null);
            if (TextUtils.isEmpty(strFetchDemandSourceId)) {
                return;
            }
            setInterstitialAvailability(strFetchDemandSourceId, false);
            if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.19
                    @Override // java.lang.Runnable
                    public void run() {
                        String str2 = string;
                        if (str2 == null) {
                            str2 = "We're sorry, some error occurred. we will investigate it";
                        }
                        IronSourceWebView.this.mDSInterstitialListener.onInterstitialShowFailed(strFetchDemandSourceId, str2);
                    }
                });
            }
            IronSourceWebView.this.toastingErrMsg(Constants.JSMethods.ON_SHOW_INTERSTITIAL_FAIL, str);
        }

        @JavascriptInterface
        public void onInitBannerSuccess(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onInitBannerSuccess()");
            IronSourceWebView.this.toastingErrMsg(Constants.JSMethods.ON_INIT_BANNER_SUCCESS, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            final String strFetchDemandSourceId = SDKUtils.fetchDemandSourceId(new SSAObj(str));
            if (TextUtils.isEmpty(strFetchDemandSourceId)) {
                Logger.i(IronSourceWebView.this.TAG, "onInitBannerSuccess failed with no demand source");
            } else if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Banner.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.20
                    @Override // java.lang.Runnable
                    public void run() {
                        Log.d(IronSourceWebView.this.TAG, "onBannerInitSuccess()");
                        IronSourceWebView.this.mDSBannerListener.onAdProductInitSuccess(SSAEnums.ProductType.Banner, strFetchDemandSourceId, null);
                    }
                });
            }
        }

        @JavascriptInterface
        public void onInitBannerFail(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onInitBannerFail(" + str + ")");
            SSAObj sSAObj = new SSAObj(str);
            final String string = sSAObj.getString("errMsg");
            final String strFetchDemandSourceId = SDKUtils.fetchDemandSourceId(sSAObj);
            if (TextUtils.isEmpty(strFetchDemandSourceId)) {
                Logger.i(IronSourceWebView.this.TAG, "onInitBannerFail failed with no demand source");
                return;
            }
            DemandSource demandSourceById = IronSourceWebView.this.mDemandSourceManager.getDemandSourceById(SSAEnums.ProductType.Banner, strFetchDemandSourceId);
            if (demandSourceById != null) {
                demandSourceById.setDemandSourceInitState(3);
            }
            if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Banner.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.21
                    @Override // java.lang.Runnable
                    public void run() {
                        String str2 = string;
                        if (str2 == null) {
                            str2 = "We're sorry, some error occurred. we will investigate it";
                        }
                        Log.d(IronSourceWebView.this.TAG, "onBannerInitFail(message:" + str2 + ")");
                        IronSourceWebView.this.mDSBannerListener.onAdProductInitFailed(SSAEnums.ProductType.Banner, strFetchDemandSourceId, str2);
                    }
                });
            }
            IronSourceWebView.this.responseBack(str, true, null, null);
            IronSourceWebView.this.toastingErrMsg(Constants.JSMethods.ON_INIT_BANNER_FAIL, str);
        }

        @JavascriptInterface
        public void onLoadBannerSuccess(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onLoadBannerSuccess()");
            final String strFetchDemandSourceId = SDKUtils.fetchDemandSourceId(new SSAObj(str));
            IronSourceWebView.this.responseBack(str, true, null, null);
            if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Banner.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.22
                    @Override // java.lang.Runnable
                    public void run() {
                        Log.d(IronSourceWebView.this.TAG, "onBannerLoadSuccess()");
                        IronSourceWebView.this.mDSBannerListener.onBannerLoadSuccess(strFetchDemandSourceId);
                    }
                });
            }
        }

        @JavascriptInterface
        public void onLoadBannerFail(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onLoadBannerFail()");
            SSAObj sSAObj = new SSAObj(str);
            final String string = sSAObj.getString("errMsg");
            final String strFetchDemandSourceId = SDKUtils.fetchDemandSourceId(sSAObj);
            IronSourceWebView.this.responseBack(str, true, null, null);
            if (!TextUtils.isEmpty(strFetchDemandSourceId) && IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Banner.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.23
                    @Override // java.lang.Runnable
                    public void run() {
                        Log.d(IronSourceWebView.this.TAG, "onLoadBannerFail()");
                        String str2 = string;
                        if (str2 == null) {
                            str2 = "We're sorry, some error occurred. we will investigate it";
                        }
                        IronSourceWebView.this.mDSBannerListener.onBannerLoadFail(strFetchDemandSourceId, str2);
                    }
                });
            }
        }

        @JavascriptInterface
        public void onGenericFunctionSuccess(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onGenericFunctionSuccess(" + str + ")");
            if (IronSourceWebView.this.mOnGenericFunctionListener == null) {
                Logger.d(IronSourceWebView.this.TAG, "genericFunctionListener was not found");
            } else {
                IronSourceWebView.this.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.24
                    @Override // java.lang.Runnable
                    public void run() {
                        IronSourceWebView.this.mOnGenericFunctionListener.onGFSuccess();
                    }
                });
                IronSourceWebView.this.responseBack(str, true, null, null);
            }
        }

        @JavascriptInterface
        public void onGenericFunctionFail(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onGenericFunctionFail(" + str + ")");
            if (IronSourceWebView.this.mOnGenericFunctionListener == null) {
                Logger.d(IronSourceWebView.this.TAG, "genericFunctionListener was not found");
                return;
            }
            final String string = new SSAObj(str).getString("errMsg");
            IronSourceWebView.this.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.25
                @Override // java.lang.Runnable
                public void run() {
                    IronSourceWebView.this.mOnGenericFunctionListener.onGFFail(string);
                }
            });
            IronSourceWebView.this.responseBack(str, true, null, null);
            IronSourceWebView.this.toastingErrMsg(Constants.JSMethods.ON_GENERIC_FUNCTION_FAIL, str);
        }

        @JavascriptInterface
        public void createCalendarEvent(String str) {
            Logger.i(IronSourceWebView.this.TAG, "createCalendarEvent(" + str + ")");
        }

        @JavascriptInterface
        public void openUrl(String str) {
            Logger.i(IronSourceWebView.this.TAG, "openUrl(" + str + ")");
            SSAObj sSAObj = new SSAObj(str);
            String string = sSAObj.getString("url");
            String string2 = sSAObj.getString("method");
            Context currentActivityContext = IronSourceWebView.this.getCurrentActivityContext();
            try {
                if (string2.equalsIgnoreCase(Constants.ParametersKeys.EXTERNAL_BROWSER)) {
                    UrlHandler.openUrl(currentActivityContext, string);
                } else if (string2.equalsIgnoreCase(Constants.ParametersKeys.WEB_VIEW)) {
                    Intent intent = new Intent(currentActivityContext, (Class<?>) OpenUrlActivity.class);
                    intent.putExtra(IronSourceWebView.EXTERNAL_URL, string);
                    intent.putExtra(IronSourceWebView.SECONDARY_WEB_VIEW, true);
                    intent.putExtra(Constants.ParametersKeys.IMMERSIVE, IronSourceWebView.this.mIsImmersive);
                    currentActivityContext.startActivity(intent);
                } else if (string2.equalsIgnoreCase("store")) {
                    Intent intent2 = new Intent(currentActivityContext, (Class<?>) OpenUrlActivity.class);
                    intent2.putExtra(IronSourceWebView.EXTERNAL_URL, string);
                    intent2.putExtra(IronSourceWebView.IS_STORE, true);
                    intent2.putExtra(IronSourceWebView.SECONDARY_WEB_VIEW, true);
                    currentActivityContext.startActivity(intent2);
                }
            } catch (Exception e) {
                IronSourceWebView.this.responseBack(str, false, e.getMessage(), null);
                e.printStackTrace();
            }
        }

        @JavascriptInterface
        public void setForceClose(String str) {
            Logger.i(IronSourceWebView.this.TAG, "setForceClose(" + str + ")");
            SSAObj sSAObj = new SSAObj(str);
            String string = sSAObj.getString("width");
            String string2 = sSAObj.getString("height");
            IronSourceWebView.this.mHiddenForceCloseWidth = Integer.parseInt(string);
            IronSourceWebView.this.mHiddenForceCloseHeight = Integer.parseInt(string2);
            IronSourceWebView.this.mHiddenForceCloseLocation = sSAObj.getString(Constants.ParametersKeys.POSITION);
        }

        @JavascriptInterface
        public void setBackButtonState(String str) {
            Logger.i(IronSourceWebView.this.TAG, "setBackButtonState(" + str + ")");
            IronSourceSharedPrefHelper.getSupersonicPrefHelper().setBackButtonState(new SSAObj(str).getString("state"));
        }

        @JavascriptInterface
        public void setStoreSearchKeys(String str) {
            Logger.i(IronSourceWebView.this.TAG, "setStoreSearchKeys(" + str + ")");
            IronSourceSharedPrefHelper.getSupersonicPrefHelper().setSearchKeys(str);
        }

        @JavascriptInterface
        public void setWebviewBackgroundColor(String str) {
            Logger.i(IronSourceWebView.this.TAG, "setWebviewBackgroundColor(" + str + ")");
            IronSourceWebView.this.setWebviewBackground(str);
        }

        @JavascriptInterface
        public void toggleUDIA(String str) {
            Logger.i(IronSourceWebView.this.TAG, "toggleUDIA(" + str + ")");
            SSAObj sSAObj = new SSAObj(str);
            if (!sSAObj.containsKey(Constants.ParametersKeys.TOGGLE)) {
                IronSourceWebView.this.responseBack(str, false, Constants.ErrorCodes.TOGGLE_KEY_DOES_NOT_EXIST, null);
                return;
            }
            int i = Integer.parseInt(sSAObj.getString(Constants.ParametersKeys.TOGGLE));
            if (i == 0) {
                return;
            }
            String binaryString = Integer.toBinaryString(i);
            if (TextUtils.isEmpty(binaryString)) {
                IronSourceWebView.this.responseBack(str, false, Constants.ErrorCodes.FIALED_TO_CONVERT_TOGGLE, null);
            } else if (binaryString.toCharArray()[3] == '0') {
                IronSourceSharedPrefHelper.getSupersonicPrefHelper().setShouldRegisterSessions(true);
            } else {
                IronSourceSharedPrefHelper.getSupersonicPrefHelper().setShouldRegisterSessions(false);
            }
        }

        @JavascriptInterface
        public void getUDIA(String str) {
            this.udiaResults = 0;
            Logger.i(IronSourceWebView.this.TAG, "getUDIA(" + str + ")");
            String strExtractSuccessFunctionToCall = IronSourceWebView.this.extractSuccessFunctionToCall(str);
            SSAObj sSAObj = new SSAObj(str);
            if (!sSAObj.containsKey(Constants.ParametersKeys.GET_BY_FLAG)) {
                IronSourceWebView.this.responseBack(str, false, Constants.ErrorCodes.GET_BY_FLAG_KEY_DOES_NOT_EXIST, null);
                return;
            }
            int i = Integer.parseInt(sSAObj.getString(Constants.ParametersKeys.GET_BY_FLAG));
            if (i == 0) {
                return;
            }
            String binaryString = Integer.toBinaryString(i);
            if (TextUtils.isEmpty(binaryString)) {
                IronSourceWebView.this.responseBack(str, false, Constants.ErrorCodes.FIALED_TO_CONVERT_GET_BY_FLAG, null);
                return;
            }
            char[] charArray = new StringBuilder(binaryString).reverse().toString().toCharArray();
            JSONArray jSONArray = new JSONArray();
            if (charArray[3] == '0') {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("sessions", IronSourceSharedPrefHelper.getSupersonicPrefHelper().getSessions());
                    IronSourceSharedPrefHelper.getSupersonicPrefHelper().deleteSessions();
                    jSONArray.put(jSONObject);
                } catch (JSONException unused) {
                }
            }
            if (charArray[2] == '1') {
                this.udiaResults++;
                Location lastLocation = LocationService.getLastLocation(IronSourceWebView.this.getContext());
                if (lastLocation != null) {
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put(LocationConst.LATITUDE, lastLocation.getLatitude());
                        jSONObject2.put(LocationConst.LONGITUDE, lastLocation.getLongitude());
                        jSONArray.put(jSONObject2);
                        this.udiaResults--;
                        sendResults(strExtractSuccessFunctionToCall, jSONArray);
                        Logger.i(IronSourceWebView.this.TAG, "done location");
                        return;
                    } catch (JSONException unused2) {
                        return;
                    }
                }
                this.udiaResults--;
            }
        }

        private void sendResults(String str, JSONArray jSONArray) {
            Logger.i(IronSourceWebView.this.TAG, "sendResults: " + this.udiaResults);
            if (this.udiaResults <= 0) {
                injectGetUDIA(str, jSONArray);
            }
        }

        @JavascriptInterface
        public void onUDIASuccess(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onUDIASuccess(" + str + ")");
        }

        @JavascriptInterface
        public void onUDIAFail(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onUDIAFail(" + str + ")");
        }

        @JavascriptInterface
        public void onGetUDIASuccess(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onGetUDIASuccess(" + str + ")");
        }

        @JavascriptInterface
        public void onGetUDIAFail(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onGetUDIAFail(" + str + ")");
        }

        @JavascriptInterface
        public void setUserUniqueId(String str) {
            Logger.i(IronSourceWebView.this.TAG, "setUserUniqueId(" + str + ")");
            SSAObj sSAObj = new SSAObj(str);
            if (!sSAObj.containsKey(Constants.ParametersKeys.USER_UNIQUE_ID) || !sSAObj.containsKey(Constants.ParametersKeys.PRODUCT_TYPE)) {
                IronSourceWebView.this.responseBack(str, false, Constants.ErrorCodes.UNIQUE_ID_OR_PRODUCT_TYPE_DOES_NOT_EXIST, null);
                return;
            }
            if (IronSourceSharedPrefHelper.getSupersonicPrefHelper().setUniqueId(sSAObj.getString(Constants.ParametersKeys.USER_UNIQUE_ID))) {
                IronSourceWebView.this.responseBack(str, true, null, null);
            } else {
                IronSourceWebView.this.responseBack(str, false, Constants.ErrorCodes.SET_USER_UNIQUE_ID_FAILED, null);
            }
        }

        @JavascriptInterface
        public void getUserUniqueId(String str) {
            Logger.i(IronSourceWebView.this.TAG, "getUserUniqueId(" + str + ")");
            SSAObj sSAObj = new SSAObj(str);
            if (!sSAObj.containsKey(Constants.ParametersKeys.PRODUCT_TYPE)) {
                IronSourceWebView.this.responseBack(str, false, Constants.ErrorCodes.PRODUCT_TYPE_DOES_NOT_EXIST, null);
                return;
            }
            String strExtractSuccessFunctionToCall = IronSourceWebView.this.extractSuccessFunctionToCall(str);
            if (TextUtils.isEmpty(strExtractSuccessFunctionToCall)) {
                return;
            }
            String string = sSAObj.getString(Constants.ParametersKeys.PRODUCT_TYPE);
            IronSourceWebView.this.injectJavascript(IronSourceWebView.this.generateJSToInject(strExtractSuccessFunctionToCall, IronSourceWebView.this.parseToJson(Constants.ParametersKeys.USER_UNIQUE_ID, IronSourceSharedPrefHelper.getSupersonicPrefHelper().getUniqueId(string), Constants.ParametersKeys.PRODUCT_TYPE, string, null, null, null, null, null, false), Constants.JSMethods.ON_GET_USER_UNIQUE_ID_SUCCESS, Constants.JSMethods.ON_GET_USER_UNIQUE_ID_FAIL));
        }

        /* JADX WARN: Code duplicated, block: B:14:0x0064 A[PHI: r5
  0x0064: PHI (r5v2 java.lang.String) = (r5v1 java.lang.String), (r5v9 java.lang.String) binds: [B:13:0x0062, B:10:0x0055] A[DONT_GENERATE, DONT_INLINE]] */
        @JavascriptInterface
        public void getAppsInstallTime(String str) {
            String localizedMessage;
            boolean z;
            String strExtractSuccessFunctionToCall;
            try {
                localizedMessage = DeviceStatus.getAppsInstallTime(IronSourceWebView.this.getContext(), Boolean.parseBoolean(new SSAObj(str).getString(Constants.ParametersKeys.INCLUDE_SYSTEM_APPS))).toString();
                z = false;
            } catch (Exception e) {
                Logger.i(IronSourceWebView.this.TAG, "getAppsInstallTime failed(" + e.getLocalizedMessage() + ")");
                localizedMessage = e.getLocalizedMessage();
                z = true;
            }
            String str2 = null;
            if (z) {
                strExtractSuccessFunctionToCall = IronSourceWebView.this.extractFailFunctionToCall(str);
                if (!TextUtils.isEmpty(strExtractSuccessFunctionToCall)) {
                    str2 = strExtractSuccessFunctionToCall;
                }
            } else {
                strExtractSuccessFunctionToCall = IronSourceWebView.this.extractSuccessFunctionToCall(str);
                if (!TextUtils.isEmpty(strExtractSuccessFunctionToCall)) {
                    str2 = strExtractSuccessFunctionToCall;
                }
            }
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            try {
                localizedMessage = URLDecoder.decode(localizedMessage, Charset.defaultCharset().name());
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
            IronSourceWebView.this.injectJavascript(IronSourceWebView.this.generateJSToInject(str2, localizedMessage));
        }

        @JavascriptInterface
        public void onGetUserUniqueIdSuccess(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onGetUserUniqueIdSuccess(" + str + ")");
        }

        @JavascriptInterface
        public void onGetUserUniqueIdFail(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onGetUserUniqueIdFail(" + str + ")");
        }

        private void injectGetUDIA(String str, JSONArray jSONArray) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            IronSourceWebView.this.injectJavascript(IronSourceWebView.this.generateJSToInject(str, jSONArray.toString(), Constants.JSMethods.ON_GET_UDIA_SUCCESS, Constants.JSMethods.ON_GET_UDIA_FAIL));
        }

        @JavascriptInterface
        public void onOfferWallGeneric(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onOfferWallGeneric(" + str + ")");
            if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
                IronSourceWebView.this.mOnOfferWallListener.onOWGeneric("", "");
            }
        }

        @JavascriptInterface
        public void setUserData(String str) {
            Logger.i(IronSourceWebView.this.TAG, "setUserData(" + str + ")");
            SSAObj sSAObj = new SSAObj(str);
            if (!sSAObj.containsKey(Constants.ParametersKeys.KEY)) {
                IronSourceWebView.this.responseBack(str, false, Constants.ErrorCodes.KEY_DOES_NOT_EXIST, null);
                return;
            }
            if (!sSAObj.containsKey("value")) {
                IronSourceWebView.this.responseBack(str, false, Constants.ErrorCodes.VALUE_DOES_NOT_EXIST, null);
                return;
            }
            String string = sSAObj.getString(Constants.ParametersKeys.KEY);
            String string2 = sSAObj.getString("value");
            if (IronSourceSharedPrefHelper.getSupersonicPrefHelper().setUserData(string, string2)) {
                IronSourceWebView.this.injectJavascript(IronSourceWebView.this.generateJSToInject(IronSourceWebView.this.extractSuccessFunctionToCall(str), IronSourceWebView.this.parseToJson(string, string2, null, null, null, null, null, null, null, false)));
                return;
            }
            IronSourceWebView.this.responseBack(str, false, "SetUserData failed writing to shared preferences", null);
        }

        @JavascriptInterface
        public void getUserData(String str) {
            Logger.i(IronSourceWebView.this.TAG, "getUserData(" + str + ")");
            SSAObj sSAObj = new SSAObj(str);
            if (!sSAObj.containsKey(Constants.ParametersKeys.KEY)) {
                IronSourceWebView.this.responseBack(str, false, Constants.ErrorCodes.KEY_DOES_NOT_EXIST, null);
                return;
            }
            String strExtractSuccessFunctionToCall = IronSourceWebView.this.extractSuccessFunctionToCall(str);
            String string = sSAObj.getString(Constants.ParametersKeys.KEY);
            IronSourceWebView.this.injectJavascript(IronSourceWebView.this.generateJSToInject(strExtractSuccessFunctionToCall, IronSourceWebView.this.parseToJson(string, IronSourceSharedPrefHelper.getSupersonicPrefHelper().getUserData(string), null, null, null, null, null, null, null, false)));
        }

        @JavascriptInterface
        public void onGetUserCreditsFail(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onGetUserCreditsFail(" + str + ")");
            final String string = new SSAObj(str).getString("errMsg");
            if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.26
                    @Override // java.lang.Runnable
                    public void run() {
                        String str2 = string;
                        if (str2 == null) {
                            str2 = "We're sorry, some error occurred. we will investigate it";
                        }
                        IronSourceWebView.this.mOnOfferWallListener.onGetOWCreditsFailed(str2);
                    }
                });
            }
            IronSourceWebView.this.responseBack(str, true, null, null);
            IronSourceWebView.this.toastingErrMsg(Constants.JSMethods.ON_GET_USER_CREDITS_FAILED, str);
        }

        @JavascriptInterface
        public void onAdWindowsClosed(String str) {
            Logger.i(IronSourceWebView.this.TAG, "onAdWindowsClosed(" + str + ")");
            IronSourceWebView.this.mSavedState.adClosed();
            IronSourceWebView.this.mSavedState.setDisplayedDemandSourceId(null);
            SSAObj sSAObj = new SSAObj(str);
            String string = sSAObj.getString(Constants.ParametersKeys.PRODUCT_TYPE);
            final String strFetchDemandSourceId = SDKUtils.fetchDemandSourceId(sSAObj);
            final SSAEnums.ProductType stringProductTypeAsEnum = IronSourceWebView.this.getStringProductTypeAsEnum(string);
            Log.d(IronSourceWebView.this.PUB_TAG, "onAdClosed() with type " + stringProductTypeAsEnum);
            if (IronSourceWebView.this.shouldNotifyDeveloper(string)) {
                IronSourceWebView.this.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.27
                    @Override // java.lang.Runnable
                    public void run() {
                        if (stringProductTypeAsEnum == SSAEnums.ProductType.RewardedVideo || stringProductTypeAsEnum == SSAEnums.ProductType.Interstitial) {
                            DSAdProductListener adProductListenerByProductType = IronSourceWebView.this.getAdProductListenerByProductType(stringProductTypeAsEnum);
                            if (adProductListenerByProductType != null) {
                                adProductListenerByProductType.onAdProductClose(stringProductTypeAsEnum, strFetchDemandSourceId);
                                return;
                            }
                            return;
                        }
                        if (stringProductTypeAsEnum == SSAEnums.ProductType.OfferWall) {
                            IronSourceWebView.this.mOnOfferWallListener.onOWAdClosed();
                        }
                    }
                });
            }
        }

        @JavascriptInterface
        public void onVideoStatusChanged(String str) {
            Log.d(IronSourceWebView.this.TAG, "onVideoStatusChanged(" + str + ")");
            SSAObj sSAObj = new SSAObj(str);
            String string = sSAObj.getString(Constants.ParametersKeys.PRODUCT_TYPE);
            if (IronSourceWebView.this.mVideoEventsListener == null || TextUtils.isEmpty(string)) {
                return;
            }
            String string2 = sSAObj.getString("status");
            if (Constants.ParametersKeys.VIDEO_STATUS_STARTED.equalsIgnoreCase(string2)) {
                IronSourceWebView.this.mVideoEventsListener.onVideoStarted();
                return;
            }
            if (Constants.ParametersKeys.VIDEO_STATUS_PAUSED.equalsIgnoreCase(string2)) {
                IronSourceWebView.this.mVideoEventsListener.onVideoPaused();
                return;
            }
            if (Constants.ParametersKeys.VIDEO_STATUS_PLAYING.equalsIgnoreCase(string2)) {
                IronSourceWebView.this.mVideoEventsListener.onVideoResumed();
                return;
            }
            if (Constants.ParametersKeys.VIDEO_STATUS_ENDED.equalsIgnoreCase(string2)) {
                IronSourceWebView.this.mVideoEventsListener.onVideoEnded();
                return;
            }
            if (Constants.ParametersKeys.VIDEO_STATUS_STOPPED.equalsIgnoreCase(string2)) {
                IronSourceWebView.this.mVideoEventsListener.onVideoStopped();
                return;
            }
            Logger.i(IronSourceWebView.this.TAG, "onVideoStatusChanged: unknown status: " + string2);
        }

        @JavascriptInterface
        public void postAdEventNotification(String str) {
            try {
                Logger.i(IronSourceWebView.this.TAG, "postAdEventNotification(" + str + ")");
                SSAObj sSAObj = new SSAObj(str);
                final String string = sSAObj.getString(Constants.ParametersKeys.EVENT_NAME);
                if (TextUtils.isEmpty(string)) {
                    IronSourceWebView.this.responseBack(str, false, Constants.ErrorCodes.EVENT_NAME_DOES_NOT_EXIST, null);
                    return;
                }
                String string2 = sSAObj.getString(Constants.ParametersKeys.NOTIFICATION_DEMAND_SOURCE_NAME);
                String strFetchDemandSourceId = SDKUtils.fetchDemandSourceId(sSAObj);
                String str2 = !TextUtils.isEmpty(strFetchDemandSourceId) ? strFetchDemandSourceId : string2;
                final JSONObject jSONObject = (JSONObject) sSAObj.get(Constants.ParametersKeys.EXTRA_DATA);
                String string3 = sSAObj.getString(Constants.ParametersKeys.PRODUCT_TYPE);
                final SSAEnums.ProductType stringProductTypeAsEnum = IronSourceWebView.this.getStringProductTypeAsEnum(string3);
                if (IronSourceWebView.this.shouldNotifyDeveloper(string3)) {
                    String strExtractSuccessFunctionToCall = IronSourceWebView.this.extractSuccessFunctionToCall(str);
                    if (!TextUtils.isEmpty(strExtractSuccessFunctionToCall)) {
                        IronSourceWebView.this.injectJavascript(IronSourceWebView.this.generateJSToInject(strExtractSuccessFunctionToCall, IronSourceWebView.this.parseToJson(Constants.ParametersKeys.PRODUCT_TYPE, string3, Constants.ParametersKeys.EVENT_NAME, string, "demandSourceName", string2, "demandSourceId", str2, null, false), Constants.JSMethods.POST_AD_EVENT_NOTIFICATION_SUCCESS, Constants.JSMethods.POST_AD_EVENT_NOTIFICATION_FAIL));
                    }
                    final String str3 = str2;
                    IronSourceWebView.this.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.28
                        @Override // java.lang.Runnable
                        public void run() {
                            if (stringProductTypeAsEnum == SSAEnums.ProductType.Interstitial || stringProductTypeAsEnum == SSAEnums.ProductType.RewardedVideo) {
                                DSAdProductListener adProductListenerByProductType = IronSourceWebView.this.getAdProductListenerByProductType(stringProductTypeAsEnum);
                                if (adProductListenerByProductType != null) {
                                    adProductListenerByProductType.onAdProductEventNotificationReceived(stringProductTypeAsEnum, str3, string, jSONObject);
                                    return;
                                }
                                return;
                            }
                            if (stringProductTypeAsEnum == SSAEnums.ProductType.OfferWall) {
                                IronSourceWebView.this.mOnOfferWallListener.onOfferwallEventNotificationReceived(string, jSONObject);
                            }
                        }
                    });
                    return;
                }
                IronSourceWebView.this.responseBack(str, false, Constants.ErrorCodes.PRODUCT_TYPE_DOES_NOT_EXIST, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        class JSCallbackTask {
            JSCallbackTask() {
            }

            void sendMessage(boolean z, String str, String str2) {
                SSAObj sSAObj = new SSAObj();
                sSAObj.put(z ? IronSourceWebView.JSON_KEY_SUCCESS : IronSourceWebView.JSON_KEY_FAIL, str);
                sSAObj.put("data", str2);
                IronSourceWebView.this.responseBack(sSAObj.toString(), z, null, null);
            }

            void sendMessage(boolean z, String str, SSAObj sSAObj) {
                sSAObj.put(z ? IronSourceWebView.JSON_KEY_SUCCESS : IronSourceWebView.JSON_KEY_FAIL, str);
                IronSourceWebView.this.responseBack(sSAObj.toString(), z, null, null);
            }

            void sendMessage(boolean z, String str, JSONObject jSONObject) {
                try {
                    jSONObject.put(z ? IronSourceWebView.JSON_KEY_SUCCESS : IronSourceWebView.JSON_KEY_FAIL, str);
                    IronSourceWebView.this.responseBack(jSONObject.toString(), z, null, null);
                } catch (JSONException e) {
                    e.printStackTrace();
                    e.getMessage();
                }
            }
        }

        @JavascriptInterface
        public void bannerViewAPI(String str) {
            try {
                IronSourceWebView.this.mBannerJsAdapter.sendMessageToISNAdView(str);
            } catch (Exception e) {
                e.printStackTrace();
                Logger.e(IronSourceWebView.this.TAG, "bannerViewAPI failed with exception " + e.getMessage());
            }
        }

        @JavascriptInterface
        public void moatAPI(final String str) {
            IronSourceWebView.this.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.29
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Logger.i(IronSourceWebView.this.TAG, "moatAPI(" + str + ")");
                        IronSourceWebView.this.mMoatJsAdapter.call(new SSAObj(str).toString(), JSInterface.this.new JSCallbackTask(), IronSourceWebView.this.getWebview());
                    } catch (Exception e) {
                        e.printStackTrace();
                        Logger.i(IronSourceWebView.this.TAG, "moatAPI failed with exception " + e.getMessage());
                    }
                }
            });
        }

        @JavascriptInterface
        public void permissionsAPI(String str) {
            try {
                Logger.i(IronSourceWebView.this.TAG, "permissionsAPI(" + str + ")");
                IronSourceWebView.this.mPermissionsJsAdapter.call(new SSAObj(str).toString(), new JSCallbackTask());
            } catch (Exception e) {
                e.printStackTrace();
                Logger.i(IronSourceWebView.this.TAG, "permissionsAPI failed with exception " + e.getMessage());
            }
        }

        @JavascriptInterface
        public void getDeviceVolume(String str) {
            Logger.i(IronSourceWebView.this.TAG, "getDeviceVolume(" + str + ")");
            try {
                float deviceVolume = DeviceProperties.getInstance(IronSourceWebView.this.getCurrentActivityContext()).getDeviceVolume(IronSourceWebView.this.getCurrentActivityContext());
                SSAObj sSAObj = new SSAObj(str);
                sSAObj.put(Constants.RequestParameters.DEVICE_VOLUME, String.valueOf(deviceVolume));
                IronSourceWebView.this.responseBack(sSAObj.toString(), true, null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @JavascriptInterface
        public void locationServicesEnabled(String str) {
            Logger.i(IronSourceWebView.this.TAG, "locationServicesEnabled(" + str + ")");
            try {
                boolean zLocationServicesEnabled = LocationService.locationServicesEnabled(IronSourceWebView.this.getContext());
                SSAObj sSAObj = new SSAObj(str);
                sSAObj.put("status", String.valueOf(zLocationServicesEnabled));
                IronSourceWebView.this.responseBack(sSAObj.toString(), true, null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @JavascriptInterface
        public void getDeviceLocation(String str) {
            Logger.i(IronSourceWebView.this.TAG, "getDeviceLocation(" + str + ")");
            try {
                IronSourceWebView.this.responseBack(IronSourceWebView.this.createLocationObject(str, LocationService.getLastLocation(IronSourceWebView.this.getContext())).toString(), true, null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @JavascriptInterface
        public void getDevicePreciseLocation(final String str) {
            Logger.i(IronSourceWebView.this.TAG, "getDevicePreciseLocation(" + str + ")");
            try {
                LocationService.getPreciseLocation(IronSourceWebView.this.getContext(), new LocationService.ISLocationListener() { // from class: com.ironsource.sdk.controller.IronSourceWebView.JSInterface.30
                    @Override // com.ironsource.environment.LocationService.ISLocationListener
                    public void onLocationChanged(Location location) {
                        IronSourceWebView.this.responseBack(IronSourceWebView.this.createLocationObject(str, location).toString(), true, null, null);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokePendingCommands() {
        while (this.mControllerCommandsQueue.size() > 0) {
            injectJavascript(this.mControllerCommandsQueue.get(0));
            this.mControllerCommandsQueue.remove(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SSAObj createLocationObject(String str, Location location) {
        SSAObj sSAObj = new SSAObj(str);
        if (location != null) {
            sSAObj.put("provider", location.getProvider());
            sSAObj.put(LocationConst.LATITUDE, Double.toString(location.getLatitude()));
            sSAObj.put(LocationConst.LONGITUDE, Double.toString(location.getLongitude()));
            sSAObj.put(LocationConst.ALTITUDE, Double.toString(location.getAltitude()));
            sSAObj.put(LocationConst.TIME, Long.toString(location.getTime()));
            sSAObj.put(LocationConst.ACCURACY, Float.toString(location.getAccuracy()));
            sSAObj.put(LocationConst.BEARING, Float.toString(location.getBearing()));
            sSAObj.put(LocationConst.SPEED, Float.toString(location.getSpeed()));
        } else {
            sSAObj.put("error", "location data is not available");
        }
        return sSAObj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DSAdProductListener getAdProductListenerByProductType(SSAEnums.ProductType productType) {
        if (productType == SSAEnums.ProductType.Interstitial) {
            return this.mDSInterstitialListener;
        }
        if (productType == SSAEnums.ProductType.RewardedVideo) {
            return this.mDSRewardedVideoListener;
        }
        if (productType == SSAEnums.ProductType.Banner) {
            return this.mDSBannerListener;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SSAEnums.ProductType getStringProductTypeAsEnum(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString())) {
            return SSAEnums.ProductType.Interstitial;
        }
        if (str.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString())) {
            return SSAEnums.ProductType.RewardedVideo;
        }
        if (str.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) {
            return SSAEnums.ProductType.OfferWall;
        }
        if (str.equalsIgnoreCase(SSAEnums.ProductType.Banner.toString())) {
            return SSAEnums.ProductType.Banner;
        }
        return null;
    }

    public static void setEXTERNAL_URL(String str) {
        EXTERNAL_URL = str;
    }

    public void setVideoEventsListener(VideoEventsListener videoEventsListener) {
        this.mVideoEventsListener = videoEventsListener;
    }

    public void removeVideoEventsListener() {
        this.mVideoEventsListener = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWebviewBackground(String str) {
        String string = new SSAObj(str).getString("color");
        setBackgroundColor(!Constants.ParametersKeys.TRANSPARENT.equalsIgnoreCase(string) ? Color.parseColor(string) : 0);
    }

    /* JADX WARN: Type inference failed for: r11v0, types: [com.ironsource.sdk.controller.IronSourceWebView$3] */
    public void load(final int i) {
        try {
            loadUrl("about:blank");
        } catch (Throwable th) {
            Logger.e(this.TAG, "WebViewController:: load: " + th.toString());
            new IronSourceAsyncHttpRequestTask().execute("https://www.supersonicads.com/mobile/sdk5/log?method=webviewLoadBlank");
        }
        String str = "file://" + this.mCacheDirectory + File.separator + Constants.MOBILE_CONTROLLER_HTML;
        if (new File(this.mCacheDirectory + File.separator + Constants.MOBILE_CONTROLLER_HTML).exists()) {
            JSONObject controllerConfigAsJSONObject = SDKUtils.getControllerConfigAsJSONObject();
            setWebDebuggingEnabled(controllerConfigAsJSONObject);
            String str2 = str + "?" + getRequestParameters(controllerConfigAsJSONObject);
            this.mLoadControllerTimer = new CountDownTimer(50000L, 1000L) { // from class: com.ironsource.sdk.controller.IronSourceWebView.3
                @Override // android.os.CountDownTimer
                public void onTick(long j) {
                    Logger.i(IronSourceWebView.this.TAG, "Loading Controller Timer Tick " + j);
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    Logger.i(IronSourceWebView.this.TAG, "Loading Controller Timer Finish");
                    if (i == 3) {
                        IronSourceWebView.this.mGlobalControllerTimer.cancel();
                        for (DemandSource demandSource : IronSourceWebView.this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.RewardedVideo)) {
                            if (demandSource.getDemandSourceInitState() == 1) {
                                IronSourceWebView.this.sendProductErrorMessage(SSAEnums.ProductType.RewardedVideo, demandSource);
                            }
                        }
                        for (DemandSource demandSource2 : IronSourceWebView.this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.Interstitial)) {
                            if (demandSource2.getDemandSourceInitState() == 1) {
                                IronSourceWebView.this.sendProductErrorMessage(SSAEnums.ProductType.Interstitial, demandSource2);
                            }
                        }
                        for (DemandSource demandSource3 : IronSourceWebView.this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.Banner)) {
                            if (demandSource3.getDemandSourceInitState() == 1) {
                                IronSourceWebView.this.sendProductErrorMessage(SSAEnums.ProductType.Banner, demandSource3);
                            }
                        }
                        if (IronSourceWebView.this.mOWmiss) {
                            IronSourceWebView.this.sendProductErrorMessage(SSAEnums.ProductType.OfferWall, null);
                        }
                        if (IronSourceWebView.this.mOWCreditsMiss) {
                            IronSourceWebView.this.sendProductErrorMessage(SSAEnums.ProductType.OfferWallCredits, null);
                            return;
                        }
                        return;
                    }
                    IronSourceWebView.this.load(2);
                }
            }.start();
            try {
                loadUrl(str2);
            } catch (Throwable th2) {
                Logger.e(this.TAG, "WebViewController:: load: " + th2.toString());
                new IronSourceAsyncHttpRequestTask().execute("https://www.supersonicads.com/mobile/sdk5/log?method=webviewLoadWithPath");
            }
            Logger.i(this.TAG, "load(): " + str2);
            return;
        }
        Logger.i(this.TAG, "load(): Mobile Controller HTML Does not exist");
        new IronSourceAsyncHttpRequestTask().execute("https://www.supersonicads.com/mobile/sdk5/log?method=htmlControllerDoesNotExistOnFileSystem");
    }

    private void setWebDebuggingEnabled(JSONObject jSONObject) {
        if (jSONObject.optBoolean("inspectWebview")) {
            setWebDebuggingEnabled();
        }
    }

    private void initProduct(String str, String str2, SSAEnums.ProductType productType, DemandSource demandSource, OnInitProductHandler onInitProductHandler) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            onInitProductHandler.handleInitProductFailed("User id or Application key are missing", productType, demandSource);
            return;
        }
        if (this.mControllerState == SSAEnums.ControllerState.Failed) {
            onInitProductHandler.handleInitProductFailed(SDKUtils.createErrorMessage(getErrorCodeByProductType(productType), Constants.ErrorCodes.InitiatingController), productType, demandSource);
            return;
        }
        IronSourceSharedPrefHelper.getSupersonicPrefHelper().setApplicationKey(str);
        Result resultCreateInitProductJSMethod = createInitProductJSMethod(productType, demandSource);
        injectJavascript(resultCreateInitProductJSMethod.methodName, resultCreateInitProductJSMethod.script);
        if (isControllerStateReady()) {
            return;
        }
        setMissProduct(productType, demandSource);
        if (this.mGlobalControllerTimeFinish) {
            downloadController();
            Logger.i(this.TAG, "initProduct | downloadController");
        }
    }

    public void initRewardedVideo(String str, String str2, DemandSource demandSource, DSRewardedVideoListener dSRewardedVideoListener) {
        this.mApplicationKey = str;
        this.mUserId = str2;
        this.mDSRewardedVideoListener = dSRewardedVideoListener;
        this.mSavedState.setRVAppKey(str);
        this.mSavedState.setRVUserId(str2);
        initProduct(str, str2, SSAEnums.ProductType.RewardedVideo, demandSource, new OnInitProductHandler() { // from class: com.ironsource.sdk.controller.IronSourceWebView.4
            @Override // com.ironsource.sdk.controller.IronSourceWebView.OnInitProductHandler
            public void handleInitProductFailed(String str3, SSAEnums.ProductType productType, DemandSource demandSource2) {
                IronSourceWebView.this.triggerOnControllerInitProductFail(str3, productType, demandSource2);
            }
        });
    }

    public void initInterstitial(String str, String str2, DemandSource demandSource, DSInterstitialListener dSInterstitialListener) {
        this.mApplicationKey = str;
        this.mUserId = str2;
        this.mDSInterstitialListener = dSInterstitialListener;
        this.mSavedState.setInterstitialAppKey(this.mApplicationKey);
        this.mSavedState.setInterstitialUserId(this.mUserId);
        initProduct(this.mApplicationKey, this.mUserId, SSAEnums.ProductType.Interstitial, demandSource, new OnInitProductHandler() { // from class: com.ironsource.sdk.controller.IronSourceWebView.5
            @Override // com.ironsource.sdk.controller.IronSourceWebView.OnInitProductHandler
            public void handleInitProductFailed(String str3, SSAEnums.ProductType productType, DemandSource demandSource2) {
                IronSourceWebView.this.triggerOnControllerInitProductFail(str3, productType, demandSource2);
            }
        });
    }

    public void loadInterstitial(String str) {
        HashMap map = new HashMap();
        map.put("demandSourceName", str);
        String strFlatMapToJsonAsString = SDKUtils.flatMapToJsonAsString(map);
        this.mSavedState.setReportLoadInterstitial(str, true);
        injectJavascript(generateJSToInject(Constants.JSMethods.LOAD_INTERSTITIAL, strFlatMapToJsonAsString, Constants.JSMethods.ON_LOAD_INTERSTITIAL_SUCCESS, Constants.JSMethods.ON_LOAD_INTERSTITIAL_FAIL));
    }

    public void loadInterstitial(DemandSource demandSource, Map<String, String> map) {
        if (this.mControllerState == SSAEnums.ControllerState.Failed) {
            handleLoadDuringControllerFailed(demandSource);
        } else {
            handleLoadAd(demandSource, map);
        }
    }

    private void handleLoadDuringControllerFailed(final DemandSource demandSource) {
        if (demandSource == null || !shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
            return;
        }
        runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.6
            @Override // java.lang.Runnable
            public void run() {
                IronSourceWebView.this.mDSInterstitialListener.onInterstitialLoadFailed(demandSource.getId(), "Load during controllerState failed");
            }
        });
    }

    private void handleLoadAd(DemandSource demandSource, Map<String, String> map) {
        Map<String, String> mapMergeHashMaps = SDKUtils.mergeHashMaps(new Map[]{map, demandSource.convertToMap()});
        this.mSavedState.setReportLoadInterstitial(demandSource.getId(), true);
        injectJavascript(Constants.JSMethods.LOAD_INTERSTITIAL, generateJSToInject(Constants.JSMethods.LOAD_INTERSTITIAL, SDKUtils.flatMapToJsonAsString(mapMergeHashMaps), Constants.JSMethods.ON_LOAD_INTERSTITIAL_SUCCESS, Constants.JSMethods.ON_LOAD_INTERSTITIAL_FAIL));
    }

    public void showInterstitial(JSONObject jSONObject) {
        injectJavascript(createShowProductJSMethod(SSAEnums.ProductType.Interstitial, jSONObject));
    }

    public void showInterstitial(DemandSource demandSource, Map<String, String> map) {
        injectJavascript(createShowProductJSMethod(SSAEnums.ProductType.Interstitial, new JSONObject(SDKUtils.mergeHashMaps(new Map[]{map, demandSource.convertToMap()}))));
    }

    public boolean isInterstitialAdAvailable(String str) {
        DemandSource demandSourceById = this.mDemandSourceManager.getDemandSourceById(SSAEnums.ProductType.Interstitial, str);
        return demandSourceById != null && demandSourceById.getAvailabilityState();
    }

    public void initOfferWall(String str, String str2, Map<String, String> map, OnOfferWallListener onOfferWallListener) {
        this.mApplicationKey = str;
        this.mUserId = str2;
        this.mOWExtraParameters = map;
        this.mOnOfferWallListener = onOfferWallListener;
        this.mSavedState.setOfferWallExtraParams(this.mOWExtraParameters);
        this.mSavedState.setOfferwallReportInit(true);
        initProduct(this.mApplicationKey, this.mUserId, SSAEnums.ProductType.OfferWall, null, new OnInitProductHandler() { // from class: com.ironsource.sdk.controller.IronSourceWebView.7
            @Override // com.ironsource.sdk.controller.IronSourceWebView.OnInitProductHandler
            public void handleInitProductFailed(String str3, SSAEnums.ProductType productType, DemandSource demandSource) {
                IronSourceWebView.this.triggerOnControllerInitProductFail(str3, productType, demandSource);
            }
        });
    }

    public void showOfferWall(Map<String, String> map) {
        this.mOWExtraParameters = map;
        injectJavascript(generateJSToInject(Constants.JSMethods.SHOW_OFFER_WALL, Constants.JSMethods.ON_SHOW_OFFER_WALL_SUCCESS, Constants.JSMethods.ON_SHOW_OFFER_WALL_FAIL));
    }

    public void getOfferWallCredits(String str, String str2, OnOfferWallListener onOfferWallListener) {
        this.mApplicationKey = str;
        this.mUserId = str2;
        this.mOnOfferWallListener = onOfferWallListener;
        initProduct(this.mApplicationKey, this.mUserId, SSAEnums.ProductType.OfferWallCredits, null, new OnInitProductHandler() { // from class: com.ironsource.sdk.controller.IronSourceWebView.8
            @Override // com.ironsource.sdk.controller.IronSourceWebView.OnInitProductHandler
            public void handleInitProductFailed(String str3, SSAEnums.ProductType productType, DemandSource demandSource) {
                IronSourceWebView.this.triggerOnControllerInitProductFail(str3, productType, demandSource);
            }
        });
    }

    public void initBanner(String str, String str2, DemandSource demandSource, DSBannerListener dSBannerListener) {
        this.mApplicationKey = str;
        this.mUserId = str2;
        this.mDSBannerListener = dSBannerListener;
        initProduct(str, str2, SSAEnums.ProductType.Banner, demandSource, new OnInitProductHandler() { // from class: com.ironsource.sdk.controller.IronSourceWebView.9
            @Override // com.ironsource.sdk.controller.IronSourceWebView.OnInitProductHandler
            public void handleInitProductFailed(String str3, SSAEnums.ProductType productType, DemandSource demandSource2) {
                IronSourceWebView.this.triggerOnControllerInitProductFail(str3, productType, demandSource2);
            }
        });
    }

    public void loadBanner(JSONObject jSONObject) {
        if (jSONObject != null) {
            injectJavascript(generateJSToInject(Constants.JSMethods.LOAD_BANNER, jSONObject.toString(), Constants.JSMethods.ON_LOAD_BANNER_SUCCESS, Constants.JSMethods.ON_LOAD_BANNER_FAIL));
        }
    }

    public void updateConsentInfo(JSONObject jSONObject) {
        injectJavascript(Constants.JSMethods.UPDATE_CONSENT_INFO, generateJSToInject(Constants.JSMethods.UPDATE_CONSENT_INFO, jSONObject != null ? jSONObject.toString() : null));
    }

    static class Result {
        String methodName;
        String script;

        Result() {
        }
    }

    private Result createInitProductJSMethod(SSAEnums.ProductType productType, DemandSource demandSource) {
        Result result = new Result();
        if (productType == SSAEnums.ProductType.RewardedVideo || productType == SSAEnums.ProductType.Interstitial || productType == SSAEnums.ProductType.OfferWall || productType == SSAEnums.ProductType.Banner) {
            HashMap map = new HashMap();
            map.put(Constants.RequestParameters.APPLICATION_KEY, this.mApplicationKey);
            map.put(Constants.RequestParameters.APPLICATION_USER_ID, this.mUserId);
            if (demandSource != null) {
                if (demandSource.getExtraParams() != null) {
                    map.putAll(demandSource.getExtraParams());
                }
                map.put("demandSourceName", demandSource.getDemandSourceName());
                map.put("demandSourceId", demandSource.getId());
            }
            Map<String, String> extraParamsByProduct = getExtraParamsByProduct(productType);
            if (extraParamsByProduct != null) {
                map.putAll(extraParamsByProduct);
            }
            String strFlatMapToJsonAsString = SDKUtils.flatMapToJsonAsString(map);
            Constants.JSMethods initMethodByProduct = Constants.JSMethods.getInitMethodByProduct(productType);
            String strGenerateJSToInject = generateJSToInject(initMethodByProduct.methodName, strFlatMapToJsonAsString, initMethodByProduct.successCallbackName, initMethodByProduct.failureCallbackName);
            result.methodName = initMethodByProduct.methodName;
            result.script = strGenerateJSToInject;
        } else if (productType == SSAEnums.ProductType.OfferWallCredits) {
            String strGenerateJSToInject2 = generateJSToInject(Constants.JSMethods.GET_USER_CREDITS, parseToJson(Constants.ParametersKeys.PRODUCT_TYPE, Constants.ParametersKeys.OFFER_WALL, Constants.RequestParameters.APPLICATION_KEY, this.mApplicationKey, Constants.RequestParameters.APPLICATION_USER_ID, this.mUserId, null, null, null, false), "null", Constants.JSMethods.ON_GET_USER_CREDITS_FAILED);
            result.methodName = Constants.JSMethods.GET_USER_CREDITS;
            result.script = strGenerateJSToInject2;
        }
        return result;
    }

    private String createShowProductJSMethod(SSAEnums.ProductType productType, JSONObject jSONObject) {
        HashMap map = new HashMap();
        map.put(Constants.RequestParameters.SESSION_DEPTH, Integer.toString(jSONObject.optInt(Constants.RequestParameters.SESSION_DEPTH)));
        String strOptString = jSONObject.optString("demandSourceName");
        String strFetchDemandSourceId = SDKUtils.fetchDemandSourceId(jSONObject);
        DemandSource demandSourceById = this.mDemandSourceManager.getDemandSourceById(productType, strFetchDemandSourceId);
        if (demandSourceById != null) {
            if (demandSourceById.getExtraParams() != null) {
                map.putAll(demandSourceById.getExtraParams());
            }
            if (!TextUtils.isEmpty(strOptString)) {
                map.put("demandSourceName", strOptString);
            }
            if (!TextUtils.isEmpty(strFetchDemandSourceId)) {
                map.put("demandSourceId", strFetchDemandSourceId);
            }
        }
        Map<String, String> extraParamsByProduct = getExtraParamsByProduct(productType);
        if (extraParamsByProduct != null) {
            map.putAll(extraParamsByProduct);
        }
        String strFlatMapToJsonAsString = SDKUtils.flatMapToJsonAsString(map);
        Constants.JSMethods showMethodByProduct = Constants.JSMethods.getShowMethodByProduct(productType);
        return generateJSToInject(showMethodByProduct.methodName, strFlatMapToJsonAsString, showMethodByProduct.successCallbackName, showMethodByProduct.failureCallbackName);
    }

    void setMissProduct(SSAEnums.ProductType productType, DemandSource demandSource) {
        if (productType == SSAEnums.ProductType.RewardedVideo || productType == SSAEnums.ProductType.Interstitial || productType == SSAEnums.ProductType.Banner) {
            if (demandSource != null) {
                demandSource.setDemandSourceInitState(1);
            }
        } else if (productType == SSAEnums.ProductType.OfferWall) {
            this.mOWmiss = true;
        } else if (productType == SSAEnums.ProductType.OfferWallCredits) {
            this.mOWCreditsMiss = true;
        }
        Logger.i(this.TAG, "setMissProduct(" + productType + ")");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void triggerOnControllerInitProductFail(final String str, final SSAEnums.ProductType productType, final DemandSource demandSource) {
        if (shouldNotifyDeveloper(productType.toString())) {
            runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.10
                @Override // java.lang.Runnable
                public void run() {
                    if (SSAEnums.ProductType.RewardedVideo == productType || SSAEnums.ProductType.Interstitial == productType || SSAEnums.ProductType.Banner == productType) {
                        DemandSource demandSource2 = demandSource;
                        if (demandSource2 == null || TextUtils.isEmpty(demandSource2.getId())) {
                            return;
                        }
                        DSAdProductListener adProductListenerByProductType = IronSourceWebView.this.getAdProductListenerByProductType(productType);
                        Log.d(IronSourceWebView.this.TAG, "onAdProductInitFailed (message:" + str + ")(" + productType + ")");
                        if (adProductListenerByProductType != null) {
                            adProductListenerByProductType.onAdProductInitFailed(productType, demandSource.getId(), str);
                            return;
                        }
                        return;
                    }
                    if (SSAEnums.ProductType.OfferWall == productType) {
                        IronSourceWebView.this.mOnOfferWallListener.onOfferwallInitFail(str);
                    } else if (SSAEnums.ProductType.OfferWallCredits == productType) {
                        IronSourceWebView.this.mOnOfferWallListener.onGetOWCreditsFailed(str);
                    }
                }
            });
        }
    }

    public void showRewardedVideo(JSONObject jSONObject) {
        injectJavascript(createShowProductJSMethod(SSAEnums.ProductType.RewardedVideo, jSONObject));
    }

    public void assetCached(String str, String str2) {
        injectJavascript(generateJSToInject(Constants.JSMethods.ASSET_CACHED, parseToJson(Constants.ParametersKeys.FILE, str, "path", str2, null, null, null, null, null, false)));
    }

    public void assetCachedFailed(String str, String str2, String str3) {
        injectJavascript(generateJSToInject(Constants.JSMethods.ASSET_CACHED_FAILED, parseToJson(Constants.ParametersKeys.FILE, str, "path", str2, "errMsg", str3, null, null, null, false)));
    }

    public void enterBackground() {
        if (this.mControllerState == SSAEnums.ControllerState.Ready) {
            injectJavascript(generateJSToInject(Constants.JSMethods.ENTER_BACKGROUND));
        }
    }

    public void enterForeground() {
        if (this.mControllerState == SSAEnums.ControllerState.Ready) {
            injectJavascript(generateJSToInject(Constants.JSMethods.ENTER_FOREGROUND));
        }
    }

    public void viewableChange(boolean z, String str) {
        injectJavascript(generateJSToInject(Constants.JSMethods.VIEWABLE_CHANGE, parseToJson(Constants.ParametersKeys.WEB_VIEW, str, null, null, null, null, null, null, Constants.ParametersKeys.IS_VIEWABLE, z)));
    }

    public void nativeNavigationPressed(String str) {
        injectJavascript(generateJSToInject(Constants.JSMethods.NATIVE_NAVIGATION_PRESSED, parseToJson("action", str, null, null, null, null, null, null, null, false)));
    }

    public void pageFinished() {
        injectJavascript(generateJSToInject(Constants.JSMethods.PAGE_FINISHED));
    }

    public void interceptedUrlToStore() {
        injectJavascript(generateJSToInject(Constants.JSMethods.INTERCEPTED_URL_TO_STORE));
    }

    public void failedToStartStoreActivity(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            str2 = Constants.ErrorCodes.STORE_ACTIVITY_FAILED_UNKNOWN_URL;
        }
        String str3 = str2;
        if (TextUtils.isEmpty(str)) {
            str = Constants.ErrorCodes.STORE_ACTIVITY_FAILED_REASON_UNSPECIFIED;
        }
        injectJavascript(generateJSToInject(Constants.JSMethods.FAILED_TO_START_STORE_ACTIVITY, parseToJson("errMsg", str, "url", str3, null, null, null, null, null, false)));
    }

    private void injectJavascript(String str, String str2) {
        if (!isControllerStateReady()) {
            Logger.d(this.TAG, "injectJavascript | ControllerCommandsQueue | adding command to queue | " + str);
            this.mControllerCommandsQueue.add(str2);
            return;
        }
        injectJavascript(str2);
    }

    private boolean isControllerStateReady() {
        return SSAEnums.ControllerState.Ready.equals(this.mControllerState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void injectJavascript(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String str2 = "console.log(\"JS exeption: \" + JSON.stringify(e));";
        if (getDebugMode() != SSAEnums.DebugMode.MODE_0.getValue() && (getDebugMode() < SSAEnums.DebugMode.MODE_1.getValue() || getDebugMode() > SSAEnums.DebugMode.MODE_3.getValue())) {
            str2 = "empty";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("try{");
        sb.append(str);
        sb.append("}catch(e){");
        sb.append(str2);
        sb.append("}");
        final String str3 = "javascript:" + sb.toString();
        runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.11
            @Override // java.lang.Runnable
            public void run() {
                Logger.i(IronSourceWebView.this.TAG, str3);
                try {
                    if (IronSourceWebView.this.isKitkatAndAbove != null) {
                        if (IronSourceWebView.this.isKitkatAndAbove.booleanValue()) {
                            IronSourceWebView.this.evaluateJavascriptKitKat(sb.toString());
                        } else {
                            IronSourceWebView.this.loadUrl(str3);
                        }
                    } else if (Build.VERSION.SDK_INT >= 19) {
                        try {
                            try {
                                IronSourceWebView.this.evaluateJavascriptKitKat(sb.toString());
                                IronSourceWebView.this.isKitkatAndAbove = true;
                            } catch (NoSuchMethodError e) {
                                Logger.e(IronSourceWebView.this.TAG, "evaluateJavascrip NoSuchMethodError: SDK version=" + Build.VERSION.SDK_INT + " " + e);
                                IronSourceWebView.this.loadUrl(str3);
                                IronSourceWebView.this.isKitkatAndAbove = false;
                            }
                        } catch (Throwable th) {
                            Logger.e(IronSourceWebView.this.TAG, "evaluateJavascrip Exception: SDK version=" + Build.VERSION.SDK_INT + " " + th);
                            IronSourceWebView.this.loadUrl(str3);
                            IronSourceWebView.this.isKitkatAndAbove = false;
                        }
                    } else {
                        IronSourceWebView.this.loadUrl(str3);
                        IronSourceWebView.this.isKitkatAndAbove = false;
                    }
                } catch (Throwable th2) {
                    Logger.e(IronSourceWebView.this.TAG, "injectJavascript: " + th2.toString());
                    new IronSourceAsyncHttpRequestTask().execute("https://www.supersonicads.com/mobile/sdk5/log?method=injectJavaScript");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"NewApi"})
    public void evaluateJavascriptKitKat(String str) {
        evaluateJavascript(str, null);
    }

    public Context getCurrentActivityContext() {
        return ((MutableContextWrapper) this.mCurrentActivityContext).getBaseContext();
    }

    private String getRequestParameters(JSONObject jSONObject) {
        DeviceProperties deviceProperties = DeviceProperties.getInstance(getContext());
        StringBuilder sb = new StringBuilder();
        String supersonicSdkVersion = DeviceProperties.getSupersonicSdkVersion();
        if (!TextUtils.isEmpty(supersonicSdkVersion)) {
            sb.append(Constants.RequestParameters.SDK_VERSION);
            sb.append(Constants.RequestParameters.EQUAL);
            sb.append(supersonicSdkVersion);
            sb.append(Constants.RequestParameters.AMPERSAND);
        }
        String deviceOsType = deviceProperties.getDeviceOsType();
        if (!TextUtils.isEmpty(deviceOsType)) {
            sb.append(Constants.RequestParameters.DEVICE_OS);
            sb.append(Constants.RequestParameters.EQUAL);
            sb.append(deviceOsType);
        }
        Uri uri = Uri.parse(SDKUtils.getControllerUrl());
        if (uri != null) {
            String str = uri.getScheme() + ":";
            String host = uri.getHost();
            int port = uri.getPort();
            if (port != -1) {
                host = host + ":" + port;
            }
            sb.append(Constants.RequestParameters.AMPERSAND);
            sb.append(Constants.RequestParameters.PROTOCOL);
            sb.append(Constants.RequestParameters.EQUAL);
            sb.append(str);
            sb.append(Constants.RequestParameters.AMPERSAND);
            sb.append("domain");
            sb.append(Constants.RequestParameters.EQUAL);
            sb.append(host);
            if (jSONObject.keys().hasNext()) {
                try {
                    String string = new JSONObject(jSONObject, new String[]{Constants.RequestParameters.IS_SECURED, Constants.RequestParameters.APPLICATION_KEY}).toString();
                    if (!TextUtils.isEmpty(string)) {
                        sb.append(Constants.RequestParameters.AMPERSAND);
                        sb.append(Constants.RequestParameters.CONTROLLER_CONFIG);
                        sb.append(Constants.RequestParameters.EQUAL);
                        sb.append(string);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            sb.append(Constants.RequestParameters.AMPERSAND);
            sb.append(Constants.RequestParameters.DEBUG);
            sb.append(Constants.RequestParameters.EQUAL);
            sb.append(getDebugMode());
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeWebView() {
        OnWebViewChangeListener onWebViewChangeListener = this.mChangeListener;
        if (onWebViewChangeListener != null) {
            onWebViewChangeListener.onCloseRequested();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:10:0x0022  */
    public void responseBack(String str, boolean z, String str2, String str3) {
        SSAObj sSAObj = new SSAObj(str);
        String string = sSAObj.getString(JSON_KEY_SUCCESS);
        String string2 = sSAObj.getString(JSON_KEY_FAIL);
        if (z) {
            if (TextUtils.isEmpty(string)) {
                string = null;
            }
        } else if (TextUtils.isEmpty(string2)) {
            string = null;
        } else {
            string = string2;
        }
        if (TextUtils.isEmpty(string)) {
            return;
        }
        if (!TextUtils.isEmpty(str2)) {
            try {
                str = new JSONObject(str).put("errMsg", str2).toString();
            } catch (JSONException unused) {
            }
        }
        if (!TextUtils.isEmpty(str3)) {
            try {
                str = new JSONObject(str).put(Constants.ParametersKeys.ERR_CODE, str3).toString();
            } catch (JSONException unused2) {
            }
        }
        injectJavascript(generateJSToInject(string, str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String extractSuccessFunctionToCall(String str) {
        return new SSAObj(str).getString(JSON_KEY_SUCCESS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String extractFailFunctionToCall(String str) {
        return new SSAObj(str).getString(JSON_KEY_FAIL);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String parseToJson(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                jSONObject.put(str, SDKUtils.encodeString(str2));
            }
            if (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)) {
                jSONObject.put(str3, SDKUtils.encodeString(str4));
            }
            if (!TextUtils.isEmpty(str5) && !TextUtils.isEmpty(str6)) {
                jSONObject.put(str5, SDKUtils.encodeString(str6));
            }
            if (!TextUtils.isEmpty(str7) && !TextUtils.isEmpty(str8)) {
                jSONObject.put(str7, SDKUtils.encodeString(str8));
            }
            if (!TextUtils.isEmpty(str9)) {
                jSONObject.put(str9, z);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            new IronSourceAsyncHttpRequestTask().execute(Constants.NATIVE_EXCEPTION_BASE_URL + e.getStackTrace()[0].getMethodName());
        }
        return jSONObject.toString();
    }

    private String mapToJson(Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        if (map != null && !map.isEmpty()) {
            for (String str : map.keySet()) {
                try {
                    jSONObject.put(str, SDKUtils.encodeString(map.get(str)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return jSONObject.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Object[] getDeviceParams(Context context) {
        boolean z;
        DeviceProperties deviceProperties = DeviceProperties.getInstance(context);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constants.RequestParameters.APP_ORIENTATION, SDKUtils.translateRequestedOrientation(DeviceStatus.getActivityRequestedOrientation(getCurrentActivityContext())));
            String deviceOem = deviceProperties.getDeviceOem();
            if (deviceOem != null) {
                jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.DEVICE_OEM), SDKUtils.encodeString(deviceOem));
            }
            String deviceModel = deviceProperties.getDeviceModel();
            if (deviceModel != null) {
                jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.DEVICE_MODEL), SDKUtils.encodeString(deviceModel));
                z = false;
            } else {
                z = true;
            }
            try {
                SDKUtils.loadGoogleAdvertiserInfo(context);
                String advertiserId = SDKUtils.getAdvertiserId();
                Boolean boolValueOf = Boolean.valueOf(SDKUtils.isLimitAdTrackingEnabled());
                if (!TextUtils.isEmpty(advertiserId)) {
                    Logger.i(this.TAG, "add AID and LAT");
                    jSONObject.put(Constants.RequestParameters.isLAT, boolValueOf);
                    jSONObject.put(Constants.RequestParameters.DEVICE_IDS + Constants.RequestParameters.LEFT_BRACKETS + Constants.RequestParameters.AID + Constants.RequestParameters.RIGHT_BRACKETS, SDKUtils.encodeString(advertiserId));
                }
                String deviceOsType = deviceProperties.getDeviceOsType();
                if (deviceOsType != null) {
                    jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.DEVICE_OS), SDKUtils.encodeString(deviceOsType));
                } else {
                    z = true;
                }
                String deviceOsVersion = deviceProperties.getDeviceOsVersion();
                if (deviceOsVersion != null) {
                    jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.DEVICE_OS_VERSION), deviceOsVersion.replaceAll("[^0-9/.]", ""));
                } else {
                    z = true;
                }
                String deviceOsVersion2 = deviceProperties.getDeviceOsVersion();
                if (deviceOsVersion2 != null) {
                    jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.DEVICE_OS_VERSION_FULL), SDKUtils.encodeString(deviceOsVersion2));
                }
                String strValueOf = String.valueOf(deviceProperties.getDeviceApiLevel());
                if (strValueOf != null) {
                    jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.DEVICE_API_LEVEL), strValueOf);
                } else {
                    z = true;
                }
                String supersonicSdkVersion = DeviceProperties.getSupersonicSdkVersion();
                if (supersonicSdkVersion != null) {
                    jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.SDK_VERSION), SDKUtils.encodeString(supersonicSdkVersion));
                }
                if (deviceProperties.getDeviceCarrier() != null && deviceProperties.getDeviceCarrier().length() > 0) {
                    jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.MOBILE_CARRIER), SDKUtils.encodeString(deviceProperties.getDeviceCarrier()));
                }
                String connectionType = ConnectivityService.getConnectionType(context);
                if (TextUtils.isEmpty(connectionType)) {
                    z = true;
                } else {
                    jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.CONNECTION_TYPE), SDKUtils.encodeString(connectionType));
                }
                String language = context.getResources().getConfiguration().locale.getLanguage();
                if (!TextUtils.isEmpty(language)) {
                    jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.DEVICE_LANGUAGE), SDKUtils.encodeString(language.toUpperCase()));
                }
                if (SDKUtils.isExternalStorageAvailable()) {
                    jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.DISK_FREE_SIZE), SDKUtils.encodeString(String.valueOf(DeviceStatus.getAvailableMemorySizeInMegaBytes(this.mCacheDirectory))));
                } else {
                    z = true;
                }
                String strValueOf2 = String.valueOf(DeviceStatus.getDeviceWidth());
                if (TextUtils.isEmpty(strValueOf2)) {
                    z = true;
                } else {
                    jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.DEVICE_SCREEN_SIZE) + Constants.RequestParameters.LEFT_BRACKETS + SDKUtils.encodeString("width") + Constants.RequestParameters.RIGHT_BRACKETS, SDKUtils.encodeString(strValueOf2));
                }
                jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.DEVICE_SCREEN_SIZE) + Constants.RequestParameters.LEFT_BRACKETS + SDKUtils.encodeString("height") + Constants.RequestParameters.RIGHT_BRACKETS, SDKUtils.encodeString(String.valueOf(DeviceStatus.getDeviceHeight())));
                String packageName = ApplicationContext.getPackageName(getContext());
                if (!TextUtils.isEmpty(packageName)) {
                    jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.PACKAGE_NAME), SDKUtils.encodeString(packageName));
                }
                String strValueOf3 = String.valueOf(DeviceStatus.getDeviceDensity());
                if (!TextUtils.isEmpty(strValueOf3)) {
                    jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.DEVICE_SCREEN_SCALE), SDKUtils.encodeString(strValueOf3));
                }
                String strValueOf4 = String.valueOf(DeviceStatus.isRootedDevice());
                if (!TextUtils.isEmpty(strValueOf4)) {
                    jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.IS_ROOT_DEVICE), SDKUtils.encodeString(strValueOf4));
                }
                jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.DEVICE_VOLUME), DeviceProperties.getInstance(context).getDeviceVolume(context));
                Context currentActivityContext = getCurrentActivityContext();
                if (Build.VERSION.SDK_INT >= 19 && (currentActivityContext instanceof Activity)) {
                    jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.IMMERSIVE), DeviceStatus.isImmersiveSupported((Activity) currentActivityContext));
                }
                jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.BATTERY_LEVEL), DeviceStatus.getBatteryLevel(currentActivityContext));
                jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.NETWORK_MCC), ConnectivityService.getNetworkMCC(currentActivityContext));
                jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.NETWORK_MNC), ConnectivityService.getNetworkMNC(currentActivityContext));
                jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.PHONE_TYPE), ConnectivityService.getPhoneType(currentActivityContext));
                jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.SIM_OPERATOR), SDKUtils.encodeString(ConnectivityService.getSimOperator(currentActivityContext)));
                jSONObject.put(SDKUtils.encodeString("lastUpdateTime"), ApplicationContext.getLastUpdateTime(currentActivityContext));
                jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.FIRST_INSTALL_TIME), ApplicationContext.getFirstInstallTime(currentActivityContext));
                jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.APPLICATION_VERSION_NAME), SDKUtils.encodeString(ApplicationContext.getApplicationVersionName(currentActivityContext)));
                String installerPackageName = ApplicationContext.getInstallerPackageName(currentActivityContext);
                if (!TextUtils.isEmpty(installerPackageName)) {
                    jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.INSTALLER_PACKAGE_NAME), SDKUtils.encodeString(installerPackageName));
                }
            } catch (JSONException e) {
                e = e;
                e.printStackTrace();
                new IronSourceAsyncHttpRequestTask().execute(Constants.NATIVE_EXCEPTION_BASE_URL + e.getStackTrace()[0].getMethodName());
            }
        } catch (JSONException e2) {
            e = e2;
            z = false;
        }
        return new Object[]{jSONObject.toString(), Boolean.valueOf(z)};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Object[] getApplicationParams(String str, String str2) {
        boolean z;
        JSONObject jSONObject = new JSONObject();
        Map<String, String> map = null;
        if (TextUtils.isEmpty(str)) {
            z = true;
        } else {
            SSAEnums.ProductType stringProductTypeAsEnum = getStringProductTypeAsEnum(str);
            if (stringProductTypeAsEnum == SSAEnums.ProductType.OfferWall) {
                map = this.mOWExtraParameters;
            } else {
                DemandSource demandSourceById = this.mDemandSourceManager.getDemandSourceById(stringProductTypeAsEnum, str2);
                if (demandSourceById != null) {
                    Map<String, String> extraParams = demandSourceById.getExtraParams();
                    extraParams.put("demandSourceName", demandSourceById.getDemandSourceName());
                    extraParams.put("demandSourceId", demandSourceById.getId());
                    map = extraParams;
                }
            }
            try {
                jSONObject.put(Constants.ParametersKeys.PRODUCT_TYPE, str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                Map<String, String> initSDKParams = SDKUtils.getInitSDKParams();
                if (initSDKParams != null) {
                    jSONObject = SDKUtils.mergeJSONObjects(jSONObject, new JSONObject(initSDKParams));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            z = false;
        }
        if (TextUtils.isEmpty(this.mUserId)) {
            z = true;
        } else {
            try {
                jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.APPLICATION_USER_ID), SDKUtils.encodeString(this.mUserId));
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }
        if (TextUtils.isEmpty(this.mApplicationKey)) {
            z = true;
        } else {
            try {
                jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.APPLICATION_KEY), SDKUtils.encodeString(this.mApplicationKey));
            } catch (JSONException e4) {
                e4.printStackTrace();
            }
        }
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getKey().equalsIgnoreCase("sdkWebViewCache")) {
                    setWebviewCache(entry.getValue());
                }
                try {
                    jSONObject.put(SDKUtils.encodeString(entry.getKey()), SDKUtils.encodeString(entry.getValue()));
                } catch (JSONException e5) {
                    e5.printStackTrace();
                }
            }
        }
        return new Object[]{jSONObject.toString(), Boolean.valueOf(z)};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Object[] getAppsStatus(String str, String str2) {
        boolean z;
        boolean z2;
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(str) && !str.equalsIgnoreCase("null")) {
                if (!TextUtils.isEmpty(str2) && !str2.equalsIgnoreCase("null")) {
                    List<ApplicationInfo> installedApplications = DeviceStatus.getInstalledApplications(getContext());
                    JSONArray jSONArray = new JSONArray(str);
                    JSONObject jSONObject2 = new JSONObject();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        String strTrim = jSONArray.getString(i).trim();
                        if (!TextUtils.isEmpty(strTrim)) {
                            JSONObject jSONObject3 = new JSONObject();
                            Iterator<ApplicationInfo> it = installedApplications.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    z2 = false;
                                    break;
                                }
                                if (strTrim.equalsIgnoreCase(it.next().packageName)) {
                                    jSONObject3.put(IS_INSTALLED, true);
                                    jSONObject2.put(strTrim, jSONObject3);
                                    z2 = true;
                                    break;
                                }
                            }
                            if (!z2) {
                                jSONObject3.put(IS_INSTALLED, false);
                                jSONObject2.put(strTrim, jSONObject3);
                            }
                        }
                    }
                    jSONObject.put(RESULT, jSONObject2);
                    jSONObject.put(REQUEST_ID, str2);
                    z = false;
                } else {
                    jSONObject.put("error", "requestId is null or empty");
                }
                return new Object[]{jSONObject.toString(), Boolean.valueOf(z)};
            }
            jSONObject.put("error", "appIds is null or empty");
        } catch (Exception unused) {
        }
        z = true;
        return new Object[]{jSONObject.toString(), Boolean.valueOf(z)};
    }

    @Override // com.ironsource.sdk.precache.DownloadManager.OnPreCacheCompletion
    public void onFileDownloadSuccess(SSAFile sSAFile) {
        if (sSAFile.getFile().contains(Constants.MOBILE_CONTROLLER_HTML)) {
            load(1);
        } else {
            assetCached(sSAFile.getFile(), sSAFile.getPath());
        }
    }

    @Override // com.ironsource.sdk.precache.DownloadManager.OnPreCacheCompletion
    public void onFileDownloadFail(SSAFile sSAFile) {
        if (sSAFile.getFile().contains(Constants.MOBILE_CONTROLLER_HTML)) {
            this.mGlobalControllerTimer.cancel();
            for (DemandSource demandSource : this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.RewardedVideo)) {
                if (demandSource.getDemandSourceInitState() == 1) {
                    sendProductErrorMessage(SSAEnums.ProductType.RewardedVideo, demandSource);
                }
            }
            for (DemandSource demandSource2 : this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.Interstitial)) {
                if (demandSource2.getDemandSourceInitState() == 1) {
                    sendProductErrorMessage(SSAEnums.ProductType.Interstitial, demandSource2);
                }
            }
            for (DemandSource demandSource3 : this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.Banner)) {
                if (demandSource3.getDemandSourceInitState() == 1) {
                    sendProductErrorMessage(SSAEnums.ProductType.Banner, demandSource3);
                }
            }
            if (this.mOWmiss) {
                sendProductErrorMessage(SSAEnums.ProductType.OfferWall, null);
            }
            if (this.mOWCreditsMiss) {
                sendProductErrorMessage(SSAEnums.ProductType.OfferWallCredits, null);
                return;
            }
            return;
        }
        assetCachedFailed(sSAFile.getFile(), sSAFile.getPath(), sSAFile.getErrMsg());
    }

    @Override // android.webkit.DownloadListener
    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        Logger.i(this.TAG, str + " " + str4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toastingErrMsg(final String str, String str2) {
        final String string = new SSAObj(str2).getString("errMsg");
        if (TextUtils.isEmpty(string)) {
            return;
        }
        runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.controller.IronSourceWebView.12
            @Override // java.lang.Runnable
            public void run() {
                if (IronSourceWebView.this.getDebugMode() == SSAEnums.DebugMode.MODE_3.getValue()) {
                    Toast.makeText(IronSourceWebView.this.getCurrentActivityContext(), str + " : " + string, 1).show();
                }
            }
        });
    }

    public void setControllerKeyPressed(String str) {
        this.mControllerKeyPressed = str;
    }

    public String getControllerKeyPressed() {
        String str = this.mControllerKeyPressed;
        setControllerKeyPressed("interrupt");
        return str;
    }

    public void deviceStatusChanged(String str) {
        injectJavascript(generateJSToInject(Constants.JSMethods.DEVICE_STATUS_CHANGED, parseToJson(Constants.RequestParameters.CONNECTION_TYPE, str, null, null, null, null, null, null, null, false)));
    }

    public void engageEnd(String str) {
        if (str.equals(Constants.ParametersKeys.FORCE_CLOSE)) {
            closeWebView();
        }
        injectJavascript(generateJSToInject(Constants.JSMethods.ENGAGE_END, parseToJson("action", str, null, null, null, null, null, null, null, false)));
    }

    public void registerConnectionReceiver(Context context) {
        context.registerReceiver(this.mConnectionReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public void unregisterConnectionReceiver(Context context) {
        try {
            context.unregisterReceiver(this.mConnectionReceiver);
        } catch (IllegalArgumentException unused) {
        } catch (Exception e) {
            Log.e(this.TAG, "unregisterConnectionReceiver - " + e);
            new IronSourceAsyncHttpRequestTask().execute(Constants.NATIVE_EXCEPTION_BASE_URL + e.getStackTrace()[0].getMethodName());
        }
    }

    public void pause() {
        if (Build.VERSION.SDK_INT > 10) {
            try {
                onPause();
            } catch (Throwable th) {
                Logger.i(this.TAG, "WebViewController: pause() - " + th);
                new IronSourceAsyncHttpRequestTask().execute("https://www.supersonicads.com/mobile/sdk5/log?method=webviewPause");
            }
        }
    }

    public void resume() {
        if (Build.VERSION.SDK_INT > 10) {
            try {
                onResume();
            } catch (Throwable th) {
                Logger.i(this.TAG, "WebViewController: onResume() - " + th);
                new IronSourceAsyncHttpRequestTask().execute("https://www.supersonicads.com/mobile/sdk5/log?method=webviewResume");
            }
        }
    }

    public void setOnWebViewControllerChangeListener(OnWebViewChangeListener onWebViewChangeListener) {
        this.mChangeListener = onWebViewChangeListener;
    }

    public FrameLayout getLayout() {
        return this.mControllerLayout;
    }

    public boolean inCustomView() {
        return this.mCustomView != null;
    }

    public void hideCustomView() {
        this.mWebChromeClient.onHideCustomView();
    }

    private void setWebviewCache(String str) {
        if (str.equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
            getSettings().setCacheMode(2);
        } else {
            getSettings().setCacheMode(-1);
        }
    }

    public boolean handleSearchKeysURLs(String str) {
        List<String> searchKeys = IronSourceSharedPrefHelper.getSupersonicPrefHelper().getSearchKeys();
        if (searchKeys == null) {
            return false;
        }
        try {
            if (searchKeys.isEmpty()) {
                return false;
            }
            Iterator<String> it = searchKeys.iterator();
            while (it.hasNext()) {
                if (str.contains(it.next())) {
                    UrlHandler.openUrl(getCurrentActivityContext(), str);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setState(State state) {
        this.mState = state;
    }

    public State getState() {
        return this.mState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendProductErrorMessage(SSAEnums.ProductType productType, DemandSource demandSource) {
        triggerOnControllerInitProductFail(SDKUtils.createErrorMessage(getErrorCodeByProductType(productType), Constants.ErrorCodes.InitiatingController), productType, demandSource);
    }

    /* JADX INFO: renamed from: com.ironsource.sdk.controller.IronSourceWebView$14, reason: invalid class name */
    static /* synthetic */ class AnonymousClass14 {
        static final /* synthetic */ int[] $SwitchMap$com$ironsource$sdk$data$SSAEnums$ProductType = new int[SSAEnums.ProductType.values().length];

        static {
            try {
                $SwitchMap$com$ironsource$sdk$data$SSAEnums$ProductType[SSAEnums.ProductType.RewardedVideo.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ironsource$sdk$data$SSAEnums$ProductType[SSAEnums.ProductType.Interstitial.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$ironsource$sdk$data$SSAEnums$ProductType[SSAEnums.ProductType.OfferWall.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$ironsource$sdk$data$SSAEnums$ProductType[SSAEnums.ProductType.OfferWallCredits.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$ironsource$sdk$data$SSAEnums$ProductType[SSAEnums.ProductType.Banner.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private String getErrorCodeByProductType(SSAEnums.ProductType productType) {
        int i = AnonymousClass14.$SwitchMap$com$ironsource$sdk$data$SSAEnums$ProductType[productType.ordinal()];
        if (i == 1) {
            return Constants.ErrorCodes.InitRV;
        }
        if (i == 2) {
            return Constants.ErrorCodes.InitIS;
        }
        if (i == 3) {
            return Constants.ErrorCodes.InitOW;
        }
        if (i != 4) {
            return i != 5 ? "" : Constants.ErrorCodes.InitBN;
        }
        return Constants.ErrorCodes.ShowOWCredits;
    }

    @Override // android.webkit.WebView
    public void destroy() {
        super.destroy();
        DownloadManager downloadManager = this.downloadManager;
        if (downloadManager != null) {
            downloadManager.release();
        }
        if (this.mConnectionReceiver != null) {
            this.mConnectionReceiver = null;
        }
        this.mUiHandler = null;
        this.mCurrentActivityContext = null;
    }

    private String generateJSToInject(String str) {
        return "SSA_CORE.SDKController.runFunction('" + str + "');";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String generateJSToInject(String str, String str2) {
        return "SSA_CORE.SDKController.runFunction('" + str + "?parameters=" + str2 + "');";
    }

    private String generateJSToInject(String str, String str2, String str3) {
        return "SSA_CORE.SDKController.runFunction('" + str + "','" + str2 + "','" + str3 + "');";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String generateJSToInject(String str, String str2, String str3, String str4) {
        return "SSA_CORE.SDKController.runFunction('" + str + "?parameters=" + str2 + "','" + str3 + "','" + str4 + "');";
    }

    public AdUnitsState getSavedState() {
        return this.mSavedState;
    }

    public void restoreState(AdUnitsState adUnitsState) {
        synchronized (this.mSavedStateLocker) {
            if (adUnitsState.shouldRestore() && this.mControllerState.equals(SSAEnums.ControllerState.Ready)) {
                Log.d(this.TAG, "restoreState(state:" + adUnitsState + ")");
                int displayedProduct = adUnitsState.getDisplayedProduct();
                if (displayedProduct != -1) {
                    if (displayedProduct == SSAEnums.ProductType.RewardedVideo.ordinal()) {
                        Log.d(this.TAG, "onRVAdClosed()");
                        SSAEnums.ProductType productType = SSAEnums.ProductType.RewardedVideo;
                        String displayedDemandSourceId = adUnitsState.getDisplayedDemandSourceId();
                        DSAdProductListener adProductListenerByProductType = getAdProductListenerByProductType(productType);
                        if (adProductListenerByProductType != null && !TextUtils.isEmpty(displayedDemandSourceId)) {
                            adProductListenerByProductType.onAdProductClose(productType, displayedDemandSourceId);
                        }
                    } else if (displayedProduct == SSAEnums.ProductType.Interstitial.ordinal()) {
                        Log.d(this.TAG, "onInterstitialAdClosed()");
                        SSAEnums.ProductType productType2 = SSAEnums.ProductType.Interstitial;
                        String displayedDemandSourceId2 = adUnitsState.getDisplayedDemandSourceId();
                        DSAdProductListener adProductListenerByProductType2 = getAdProductListenerByProductType(productType2);
                        if (adProductListenerByProductType2 != null && !TextUtils.isEmpty(displayedDemandSourceId2)) {
                            adProductListenerByProductType2.onAdProductClose(productType2, displayedDemandSourceId2);
                        }
                    } else if (displayedProduct == SSAEnums.ProductType.OfferWall.ordinal()) {
                        Log.d(this.TAG, "onOWAdClosed()");
                        if (this.mOnOfferWallListener != null) {
                            this.mOnOfferWallListener.onOWAdClosed();
                        }
                    }
                    adUnitsState.adOpened(-1);
                    adUnitsState.setDisplayedDemandSourceId(null);
                } else {
                    Log.d(this.TAG, "No ad was opened");
                }
                String interstitialAppKey = adUnitsState.getInterstitialAppKey();
                String interstitialUserId = adUnitsState.getInterstitialUserId();
                for (DemandSource demandSource : this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.Interstitial)) {
                    if (demandSource.getDemandSourceInitState() == 2) {
                        Log.d(this.TAG, "initInterstitial(appKey:" + interstitialAppKey + ", userId:" + interstitialUserId + ", demandSource:" + demandSource.getDemandSourceName() + ")");
                        initInterstitial(interstitialAppKey, interstitialUserId, demandSource, this.mDSInterstitialListener);
                    }
                }
                String rVAppKey = adUnitsState.getRVAppKey();
                String rVUserId = adUnitsState.getRVUserId();
                for (DemandSource demandSource2 : this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.RewardedVideo)) {
                    if (demandSource2.getDemandSourceInitState() == 2) {
                        String demandSourceName = demandSource2.getDemandSourceName();
                        Log.d(this.TAG, "onRVNoMoreOffers()");
                        this.mDSRewardedVideoListener.onRVNoMoreOffers(demandSourceName);
                        Log.d(this.TAG, "initRewardedVideo(appKey:" + rVAppKey + ", userId:" + rVUserId + ", demandSource:" + demandSourceName + ")");
                        initRewardedVideo(rVAppKey, rVUserId, demandSource2, this.mDSRewardedVideoListener);
                    }
                }
                adUnitsState.setShouldRestore(false);
            }
            this.mSavedState = adUnitsState;
        }
    }

    @Override // android.webkit.WebView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (this.mChangeListener.onBackButtonPressed()) {
                return true;
            }
            return super.onKeyDown(i, keyEvent);
        }
        return super.onKeyDown(i, keyEvent);
    }

    void runOnUiThread(Runnable runnable) {
        this.mUiHandler.post(runnable);
    }
}
