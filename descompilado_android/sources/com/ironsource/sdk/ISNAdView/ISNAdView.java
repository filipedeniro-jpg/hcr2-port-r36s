package com.ironsource.sdk.ISNAdView;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import com.ironsource.sdk.ISAdSize;
import com.ironsource.sdk.IronSourceNetwork;
import com.ironsource.sdk.SSAFactory;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class ISNAdView extends FrameLayout {
    private String TAG;
    private Activity mActivity;
    private ISAdSize mAdViewSize;
    private String mContainerIdentifier;
    private ISNAdViewLogic mIsnAdViewLogic;
    private WebView mWebView;

    interface IErrorReportDelegate {
        void reportOnError(String str);
    }

    public ISNAdView(Activity activity, String str, ISAdSize iSAdSize) {
        super(activity);
        this.TAG = ISNAdView.class.getSimpleName();
        this.mActivity = activity;
        this.mAdViewSize = iSAdSize;
        this.mContainerIdentifier = str;
        this.mIsnAdViewLogic = new ISNAdViewLogic();
    }

    public void setControllerDelegate(ISNAdViewDelegate iSNAdViewDelegate) {
        this.mIsnAdViewLogic.setControllerDelegate(iSNAdViewDelegate);
    }

    public void loadAd(JSONObject jSONObject) throws Exception {
        try {
            try {
                SSAFactory.getPublisherInstance(this.mActivity).loadBanner(this.mIsnAdViewLogic.buildDataForLoadingAd(jSONObject, this.mContainerIdentifier));
            } catch (Exception unused) {
                throw new Exception("ISNAdView | Failed to instantiate IronSourceAdsPublisherAgent");
            }
        } catch (Exception unused2) {
            throw new Exception("ISNAdView | loadAd | Failed to build load parameters");
        }
    }

    public void load(JSONObject jSONObject) throws Exception {
        try {
            try {
                IronSourceNetwork.loadBanner(this.mIsnAdViewLogic.buildDataForLoadingAd(jSONObject, this.mContainerIdentifier));
            } catch (Exception unused) {
                throw new Exception("ISNAdView | Failed to instantiate IronSourceAdsPublisherAgent");
            }
        } catch (Exception unused2) {
            throw new Exception("ISNAdView | loadAd | Failed to build load parameters");
        }
    }

    public void performCleanup() {
        this.mActivity.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.ISNAdView.ISNAdView.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ISNAdView.this.mIsnAdViewLogic.reportAdContainerWasRemoved();
                    ISNAdView.this.removeView(ISNAdView.this.mWebView);
                    if (ISNAdView.this.mWebView != null) {
                        ISNAdView.this.mWebView.destroy();
                    }
                    ISNAdView.this.mActivity = null;
                    ISNAdView.this.mAdViewSize = null;
                    ISNAdView.this.mContainerIdentifier = null;
                    ISNAdView.this.mIsnAdViewLogic.destroy();
                    ISNAdView.this.mIsnAdViewLogic = null;
                } catch (Exception e) {
                    Log.e(ISNAdView.this.TAG, "performCleanup | could not destroy ISNAdView");
                    e.printStackTrace();
                }
            }
        });
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        ISNAdViewLogic iSNAdViewLogic = this.mIsnAdViewLogic;
        if (iSNAdViewLogic != null) {
            iSNAdViewLogic.updateViewVisibilityParameters(ISNAdViewConstants.IS_VISIBLE_KEY, i, isShown());
        }
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        ISNAdViewLogic iSNAdViewLogic = this.mIsnAdViewLogic;
        if (iSNAdViewLogic != null) {
            iSNAdViewLogic.updateViewVisibilityParameters(ISNAdViewConstants.IS_WINDOW_VISIBLE_KEY, i, isShown());
        }
    }

    public void loadUrlIntoWebView(final String str, final String str2) {
        this.mActivity.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.ISNAdView.ISNAdView.2
            @Override // java.lang.Runnable
            public void run() {
                if (ISNAdView.this.mWebView == null) {
                    ISNAdView.this.createWebView(str2);
                }
                ISNAdView iSNAdView = ISNAdView.this;
                iSNAdView.addView(iSNAdView.mWebView);
                ISNAdView.this.mWebView.loadUrl(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createWebView(final String str) {
        this.mWebView = new WebView(this.mActivity);
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.addJavascriptInterface(new ISNAdViewWebViewJSInterface(this), ISNAdViewConstants.CONTAINER_MSG_HANDLER);
        this.mWebView.setWebViewClient(new ISNAdViewWebClient(new IErrorReportDelegate() { // from class: com.ironsource.sdk.ISNAdView.ISNAdView.3
            @Override // com.ironsource.sdk.ISNAdView.ISNAdView.IErrorReportDelegate
            public void reportOnError(String str2) {
                ISNAdView.this.mIsnAdViewLogic.sendErrorMessageToController(str, str2);
            }
        }));
        this.mWebView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.mIsnAdViewLogic.setAdViewWebView(this.mWebView);
    }

    void receiveMessageFromWebView(String str) {
        this.mIsnAdViewLogic.handleMessageFromWebView(str);
    }

    public void receiveMessageFromController(String str, JSONObject jSONObject, String str2, String str3) {
        try {
            if (str.equalsIgnoreCase(ISNAdViewConstants.LOAD_WITH_URL)) {
                loadUrlIntoWebView(jSONObject.getString(ISNAdViewConstants.URL_FOR_WEBVIEW), str3);
            } else {
                this.mIsnAdViewLogic.handleMessageFromController(str, jSONObject, str2, str3);
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.mIsnAdViewLogic.sendErrorMessageToController(str3, "Could not handle message from controller: " + str + " with params: " + jSONObject.toString());
        }
    }

    public ISAdSize getAdViewSize() {
        return this.mAdViewSize;
    }
}
