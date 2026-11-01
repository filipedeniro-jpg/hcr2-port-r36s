package com.ironsource.sdk.ISNAdView;

import android.annotation.TargetApi;
import android.os.Build;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* JADX INFO: loaded from: classes2.dex */
public class ISNAdViewWebClient extends WebViewClient {
    private ISNAdView.IErrorReportDelegate mDelegate;

    private String getMessage(String str, String str2) {
        return "ISNAdViewWebClient | External Adunit failed to load." + str + " Status code: " + str2;
    }

    public ISNAdViewWebClient(ISNAdView.IErrorReportDelegate iErrorReportDelegate) {
        this.mDelegate = iErrorReportDelegate;
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
        try {
            this.mDelegate.reportOnError(getMessage("onReceivedHttpError", Build.VERSION.SDK_INT >= 21 ? String.valueOf(webResourceResponse.getStatusCode()) : ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        try {
            this.mDelegate.reportOnError(getMessage("onReceivedError", String.valueOf(i)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onReceivedError(webView, i, str, str2);
    }

    @Override // android.webkit.WebViewClient
    @TargetApi(23)
    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        try {
            this.mDelegate.reportOnError(getMessage("onReceivedErrorM", String.valueOf(webResourceError.getErrorCode())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onReceivedError(webView, webResourceRequest, webResourceError);
    }
}
