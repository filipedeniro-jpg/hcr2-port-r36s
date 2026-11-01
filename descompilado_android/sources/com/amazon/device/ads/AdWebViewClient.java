package com.amazon.device.ads;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.places.model.PlaceFields;
import com.ironsource.sdk.constants.Constants;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
class AdWebViewClient extends WebViewClient {
    protected static final String AAX_REDIRECT_BETA = "aax-beta.integ.amazon.com";
    protected static final String AAX_REDIRECT_GAMMA = "aax-us-east.amazon-adsystem.com";
    protected static final String AAX_REDIRECT_PROD = "aax-us-east.amazon-adsystem.com";
    public static final String AMAZON_MOBILE = "amazonmobile";
    protected static final String CORNERSTONE_BEST_ENDPOINT_BETA = "d16g-cornerstone-bes.integ.amazon.com";
    protected static final String CORNERSTONE_BEST_ENDPOINT_PROD = "pda-bes.amazon.com";
    public static final String GEO = "geo";
    public static final String GOOGLE_STREETVIEW = "google.streetview";
    private static final String LOGTAG = "AdWebViewClient";
    public static final String MAILTO = "mailto";
    public static final String SMS = "sms";
    public static final String TELEPHONE = "tel";
    public static final String VOICEMAIL = "voicemail";
    protected static final HashSet<String> intentSchemes = new HashSet<>();
    protected static Set<String> redirectHosts;
    private final AdControlAccessor adControlAccessor;
    private final AndroidBuildInfo androidBuildInfo;
    private final AdSDKBridgeList bridgeList;
    private final Context context;
    private AdWebViewClientListener listener;
    private final MobileAdsLogger logger;
    private final MobileAdsLoggerFactory loggerFactory;
    private CopyOnWriteArrayList<String> resourceList = new CopyOnWriteArrayList<>();
    private final HashMap<String, UrlExecutor> urlExecutors = new HashMap<>();
    private final WebUtils2 webUtils;

    interface AdWebViewClientListener {
        void onLoadResource(WebView webView, String str);

        void onPageFinished(WebView webView, String str);

        void onPageStarted(WebView webView, String str);

        void onReceivedError(WebView webView, int i, String str, String str2);
    }

    interface UrlExecutor {
        boolean execute(String str);
    }

    static {
        intentSchemes.add(TELEPHONE);
        intentSchemes.add(VOICEMAIL);
        intentSchemes.add(SMS);
        intentSchemes.add(MAILTO);
        intentSchemes.add(GEO);
        intentSchemes.add(GOOGLE_STREETVIEW);
        redirectHosts = new HashSet();
        redirectHosts.add("aax-us-east.amazon-adsystem.com");
        redirectHosts.add("aax-us-east.amazon-adsystem.com");
        redirectHosts.add(AAX_REDIRECT_BETA);
        redirectHosts.add(CORNERSTONE_BEST_ENDPOINT_PROD);
        redirectHosts.add(CORNERSTONE_BEST_ENDPOINT_BETA);
    }

    public AdWebViewClient(Context context, AdSDKBridgeList adSDKBridgeList, AdControlAccessor adControlAccessor, WebUtils2 webUtils2, MobileAdsLoggerFactory mobileAdsLoggerFactory, AndroidBuildInfo androidBuildInfo) {
        this.context = context;
        this.bridgeList = adSDKBridgeList;
        this.adControlAccessor = adControlAccessor;
        this.webUtils = webUtils2;
        this.loggerFactory = mobileAdsLoggerFactory;
        this.logger = this.loggerFactory.createMobileAdsLogger(LOGTAG);
        this.androidBuildInfo = androidBuildInfo;
        setupUrlExecutors();
    }

    public void setListener(AdWebViewClientListener adWebViewClientListener) {
        this.listener = adWebViewClientListener;
    }

    private void setupUrlExecutors() {
        this.urlExecutors.put(AMAZON_MOBILE, new AmazonMobileExecutor(this.context, this.loggerFactory, new AmazonDeviceLauncher(), this.webUtils));
        DefaultExecutor defaultExecutor = new DefaultExecutor(this.context);
        Iterator<String> it = intentSchemes.iterator();
        while (it.hasNext()) {
            putUrlExecutor(it.next(), defaultExecutor);
        }
    }

    public void putUrlExecutor(String str, UrlExecutor urlExecutor) {
        this.urlExecutors.put(str, urlExecutor);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        this.logger.e("Error: %s", str);
        super.onReceivedError(webView, i, str, str2);
        this.listener.onReceivedError(webView, i, str, str2);
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return openUrl(str);
    }

    public boolean openUrl(String str) {
        boolean z = !redirectHosts.contains(Uri.parse(str).getHost()) || isHoneycombVersion();
        if (interpretScheme(str, getScheme(str))) {
            return true;
        }
        return z;
    }

    protected String getScheme(String str) {
        return this.webUtils.getScheme(str);
    }

    protected boolean interpretScheme(String str, String str2) {
        if (str2 == null) {
            return false;
        }
        if (str2.equals(PlaceFields.ABOUT) && str.equalsIgnoreCase("about:blank")) {
            return false;
        }
        if (this.urlExecutors.containsKey(str2)) {
            return this.urlExecutors.get(str2).execute(str);
        }
        this.logger.d("Scheme %s unrecognized. Launching as intent.", str2);
        return this.webUtils.launchActivityForIntentLink(str, this.context);
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        this.listener.onPageStarted(webView, str);
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        this.logger.d("Page Finished %s", str);
        if (checkResources()) {
            return;
        }
        AdWebViewClientListener adWebViewClientListener = this.listener;
        if (adWebViewClientListener == null) {
            this.logger.w("Call to onPageFinished() ignored because listener is null.");
        } else {
            adWebViewClientListener.onPageFinished(webView, str);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onLoadResource(WebView webView, String str) {
        this.resourceList.add(str);
        this.logger.d("Loading resource: %s", str);
        this.listener.onLoadResource(webView, str);
    }

    private boolean checkResources() {
        Iterator<String> it = this.resourceList.iterator();
        boolean z = false;
        while (it.hasNext()) {
            Set<AdSDKBridgeFactory> bridgeFactoriesForResourceLoad = BridgeSelector.getInstance().getBridgeFactoriesForResourceLoad(it.next());
            if (bridgeFactoriesForResourceLoad.size() > 0) {
                Iterator<AdSDKBridgeFactory> it2 = bridgeFactoriesForResourceLoad.iterator();
                while (it2.hasNext()) {
                    AdSDKBridge adSDKBridgeCreateAdSDKBridge = it2.next().createAdSDKBridge(this.adControlAccessor);
                    if (!this.bridgeList.contains(adSDKBridgeCreateAdSDKBridge)) {
                        z = true;
                        this.bridgeList.addBridge(adSDKBridgeCreateAdSDKBridge);
                    }
                }
            }
        }
        if (z) {
            ThreadUtils.executeOnMainThread(new Runnable() { // from class: com.amazon.device.ads.AdWebViewClient.1
                @Override // java.lang.Runnable
                public void run() {
                    AdWebViewClient.this.adControlAccessor.reload();
                }
            });
        }
        return z;
    }

    boolean isHoneycombVersion() {
        return AndroidTargetUtils.isBetweenAndroidAPIs(this.androidBuildInfo, 11, 13);
    }

    static class AmazonMobileExecutor implements UrlExecutor {
        private final Context context;
        private final AmazonDeviceLauncher launcher;
        private final MobileAdsLogger logger;
        private final WebUtils2 webUtils;

        AmazonMobileExecutor(Context context) {
            this(context, new MobileAdsLoggerFactory(), new AmazonDeviceLauncher(), new WebUtils2());
        }

        AmazonMobileExecutor(Context context, MobileAdsLoggerFactory mobileAdsLoggerFactory, AmazonDeviceLauncher amazonDeviceLauncher, WebUtils2 webUtils2) {
            this.context = context;
            this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(AdWebViewClient.LOGTAG);
            this.launcher = amazonDeviceLauncher;
            this.webUtils = webUtils2;
        }

        @Override // com.amazon.device.ads.AdWebViewClient.UrlExecutor
        public boolean execute(String str) {
            specialUrlClicked(str);
            return true;
        }

        public void specialUrlClicked(String str) {
            List<String> queryParameters;
            String queryParameter;
            this.logger.d("Executing AmazonMobile Intent");
            Uri uri = Uri.parse(str);
            try {
                queryParameters = uri.getQueryParameters("intent");
            } catch (UnsupportedOperationException unused) {
                queryParameters = null;
            }
            if (queryParameters != null && queryParameters.size() > 0) {
                Iterator<String> it = queryParameters.iterator();
                while (it.hasNext()) {
                    if (launchExternalActivity(it.next())) {
                        return;
                    }
                }
                handleApplicationDefinedSpecialURL(str);
                return;
            }
            if (this.launcher.isWindowshopPresent(this.context) && !this.launcher.isInWindowshopApp(this.context)) {
                if (!uri.getHost().equals("shopping") || (queryParameter = uri.getQueryParameter("app-action")) == null || queryParameter.length() == 0) {
                    return;
                }
                if (queryParameter.equals("detail")) {
                    String queryParameter2 = uri.getQueryParameter("asin");
                    if (queryParameter2 == null || queryParameter2.length() == 0) {
                        return;
                    }
                    this.launcher.launchWindowshopDetailPage(this.context, queryParameter2);
                    return;
                }
                if (queryParameter.equals("search")) {
                    String queryParameter3 = uri.getQueryParameter("keyword");
                    if (queryParameter3 == null || queryParameter3.length() == 0) {
                        return;
                    }
                    this.launcher.launchWindowshopSearchPage(this.context, queryParameter3);
                    return;
                }
                if (queryParameter.equals(Constants.ParametersKeys.WEB_VIEW)) {
                    handleApplicationDefinedSpecialURL(str);
                    return;
                }
                return;
            }
            handleApplicationDefinedSpecialURL(str);
        }

        protected void handleApplicationDefinedSpecialURL(String str) {
            this.logger.i("Special url clicked, but was not handled by SDK. Url: %s", str);
        }

        protected boolean launchExternalActivity(String str) {
            return this.webUtils.launchActivityForIntentLink(str, this.context);
        }
    }

    static class DefaultExecutor implements UrlExecutor {
        private final Context context;

        public DefaultExecutor(Context context) {
            this.context = context;
        }

        @Override // com.amazon.device.ads.AdWebViewClient.UrlExecutor
        public boolean execute(String str) {
            WebUtils.launchActivityForIntentLink(str, this.context);
            return true;
        }
    }
}
