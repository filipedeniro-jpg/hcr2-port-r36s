package com.applovin.impl.adview;

import android.content.Context;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.amazon.device.ads.WebRequest;
import com.appsflyer.share.Constants;

/* JADX INFO: loaded from: classes.dex */
public class r extends WebView {
    public r(s sVar, Context context) {
        super(context);
        setBackgroundColor(0);
        WebSettings settings = getSettings();
        settings.setSupportMultipleWindows(false);
        settings.setJavaScriptEnabled(true);
        setWebViewClient(sVar);
        setWebChromeClient(new WebChromeClient());
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        setScrollBarStyle(33554432);
    }

    public void a(String str) {
        loadDataWithBaseURL(Constants.URL_PATH_DELIMITER, str, WebRequest.CONTENT_TYPE_HTML, null, "");
    }
}
