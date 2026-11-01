package com.vungle.warren.ui;

import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.Nullable;
import com.amazon.device.ads.AdWebViewClient;
import com.google.gson.JsonObject;
import com.ironsource.sdk.constants.Constants;
import com.my.target.ab;
import com.vungle.warren.DirectDownloadAdapter;
import com.vungle.warren.SDKDownloadClient;
import com.vungle.warren.model.Advertisement;
import com.vungle.warren.model.Placement;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class VungleWebClient extends WebViewClient {
    private MRAIDDelegate MRAIDDelegate;
    private Advertisement advertisement;
    private boolean collectConsent;
    private DirectDownloadAdapter directDownloadAdapter;
    private String gdprAccept;
    private String gdprBody;
    private String gdprDeny;
    private String gdprTitle;
    private Boolean isViewable;
    private WebView loadedWebView;
    private Placement placement;
    private boolean ready = false;

    public interface MRAIDDelegate {
        boolean processCommand(String str, JsonObject jsonObject);
    }

    public VungleWebClient(Advertisement advertisement, Placement placement, DirectDownloadAdapter directDownloadAdapter) {
        this.advertisement = advertisement;
        this.placement = placement;
        this.directDownloadAdapter = directDownloadAdapter;
    }

    public VungleWebClient(Advertisement advertisement, Placement placement) {
        this.advertisement = advertisement;
        this.placement = placement;
    }

    public void setConsentStatus(boolean z, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        this.collectConsent = z;
        this.gdprTitle = str;
        this.gdprBody = str2;
        this.gdprAccept = str3;
        this.gdprDeny = str4;
    }

    public void setMRAIDDelegate(MRAIDDelegate mRAIDDelegate) {
        this.MRAIDDelegate = mRAIDDelegate;
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(final WebView webView, String str) {
        Log.d("Vungle", "MRAID Command " + str);
        Uri uri = Uri.parse(str);
        if (!uri.getScheme().equals("mraid")) {
            return false;
        }
        String host = uri.getHost();
        if (host.equals("propertiesChangeCompleted") && !this.ready) {
            final JsonObject mRAIDArgs = this.advertisement.getMRAIDArgs();
            DirectDownloadAdapter directDownloadAdapter = this.directDownloadAdapter;
            if (directDownloadAdapter != null) {
                directDownloadAdapter.getSdkDownloadClient().setInstallStatusCheck(new SDKDownloadClient.InstallStatusCheck() { // from class: com.vungle.warren.ui.VungleWebClient.1
                    @Override // com.vungle.warren.SDKDownloadClient.InstallStatusCheck
                    public void isAppInstalled(boolean z, boolean z2) {
                        mRAIDArgs.addProperty("isDirectDownload", (Boolean) true);
                        mRAIDArgs.addProperty("isDisplayIAP", Boolean.valueOf(z2));
                        mRAIDArgs.addProperty("isInstalled", Boolean.valueOf(z));
                        mRAIDArgs.addProperty("locale", Locale.getDefault().toString());
                        mRAIDArgs.addProperty("language", Locale.getDefault().getLanguage());
                        webView.loadUrl("javascript:window.vungle.mraidBridge.notifyReadyEvent(" + mRAIDArgs + ")");
                    }
                });
                this.directDownloadAdapter.getSdkDownloadClient().installStatusRequest();
            } else {
                webView.loadUrl("javascript:window.vungle.mraidBridge.notifyReadyEvent(" + mRAIDArgs + ")");
            }
            this.ready = true;
        } else if (this.MRAIDDelegate != null) {
            JsonObject jsonObject = new JsonObject();
            for (String str2 : uri.getQueryParameterNames()) {
                jsonObject.addProperty(str2, uri.getQueryParameter(str2));
            }
            if (this.MRAIDDelegate.processCommand(host, jsonObject)) {
                webView.loadUrl("javascript:window.vungle.mraidBridge.notifyCommandComplete()");
            }
        }
        return true;
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        int adType = this.advertisement.getAdType();
        if (adType == 0) {
            webView.loadUrl("javascript:vungleInit({\"privacyPolicyEnabled\": \"true\"})");
            webView.loadUrl("javascript:function actionClicked(action){Android.performAction(action);};");
        } else {
            if (adType == 1) {
                this.loadedWebView = webView;
                this.loadedWebView.setVisibility(0);
                notifyPropertiesChange(true);
                return;
            }
            throw new IllegalArgumentException("Unknown Client Type!");
        }
    }

    public void setReady(boolean z) {
        this.ready = z;
    }

    public void notifyPropertiesChange(boolean z) {
        if (this.loadedWebView != null) {
            JsonObject jsonObject = new JsonObject();
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.addProperty("width", Integer.valueOf(this.loadedWebView.getWidth()));
            jsonObject2.addProperty("height", Integer.valueOf(this.loadedWebView.getHeight()));
            JsonObject jsonObject3 = new JsonObject();
            jsonObject3.addProperty("x", (Number) 0);
            jsonObject3.addProperty("y", (Number) 0);
            jsonObject3.addProperty("width", Integer.valueOf(this.loadedWebView.getWidth()));
            jsonObject3.addProperty("height", Integer.valueOf(this.loadedWebView.getHeight()));
            JsonObject jsonObject4 = new JsonObject();
            jsonObject4.addProperty(AdWebViewClient.SMS, (Boolean) false);
            jsonObject4.addProperty(AdWebViewClient.TELEPHONE, (Boolean) false);
            jsonObject4.addProperty("calendar", (Boolean) false);
            jsonObject4.addProperty(ab.bF, (Boolean) false);
            jsonObject4.addProperty("inlineVideo", (Boolean) false);
            jsonObject.add("maxSize", jsonObject2);
            jsonObject.add("screenSize", jsonObject2);
            jsonObject.add("defaultPosition", jsonObject3);
            jsonObject.add("currentPosition", jsonObject3);
            jsonObject.add("supports", jsonObject4);
            jsonObject.addProperty("placementType", this.advertisement.getTemplateType());
            Boolean bool = this.isViewable;
            if (bool != null) {
                jsonObject.addProperty(Constants.ParametersKeys.IS_VIEWABLE, bool);
            }
            jsonObject.addProperty("os", "android");
            jsonObject.addProperty("osVersion", Integer.toString(Build.VERSION.SDK_INT));
            jsonObject.addProperty("incentivized", Boolean.valueOf(this.placement.isIncentivized()));
            jsonObject.addProperty("enableBackImmediately", Boolean.valueOf(this.advertisement.getShowCloseDelay(this.placement.isIncentivized()) == 0));
            jsonObject.addProperty("version", "1.0");
            if (this.collectConsent) {
                jsonObject.addProperty("consentRequired", (Boolean) true);
                jsonObject.addProperty("consentTitleText", this.gdprTitle);
                jsonObject.addProperty("consentBodyText", this.gdprBody);
                jsonObject.addProperty("consentAcceptButtonText", this.gdprAccept);
                jsonObject.addProperty("consentDenyButtonText", this.gdprDeny);
            } else {
                jsonObject.addProperty("consentRequired", (Boolean) false);
            }
            Log.d("VungleWebClient", "loadJsjavascript:window.vungle.mraidBridge.notifyPropertiesChange(" + jsonObject + "," + z + ")");
            this.loadedWebView.loadUrl("javascript:window.vungle.mraidBridge.notifyPropertiesChange(" + jsonObject + "," + z + ")");
        }
    }

    public void setAdVisibility(boolean z) {
        if (this.loadedWebView != null) {
            this.isViewable = Boolean.valueOf(z);
            notifyPropertiesChange(false);
        }
    }
}
