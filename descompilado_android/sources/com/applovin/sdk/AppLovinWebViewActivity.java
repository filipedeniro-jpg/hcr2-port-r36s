package com.applovin.sdk;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.applovin.impl.sdk.utils.k;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class AppLovinWebViewActivity extends Activity {
    public static final String EVENT_DISMISSED_VIA_BACK_BUTTON = "dismissed_via_back_button";
    public static final String INTENT_EXTRA_KEY_IMMERSIVE_MODE_ON = "immersive_mode_on";
    public static final String INTENT_EXTRA_KEY_SDK_KEY = "sdk_key";
    private WebView a;
    private EventListener b;

    public interface EventListener {
        void onReceivedEvent(String str);
    }

    public void loadUrl(String str, EventListener eventListener) {
        this.b = eventListener;
        this.a.loadUrl(str);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        EventListener eventListener = this.b;
        if (eventListener != null) {
            eventListener.onReceivedEvent(EVENT_DISMISSED_VIA_BACK_BUTTON);
        }
        super.onBackPressed();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra(INTENT_EXTRA_KEY_SDK_KEY);
        if (TextUtils.isEmpty(stringExtra)) {
            throw new IllegalArgumentException("No SDK key specified");
        }
        final AppLovinSdk appLovinSdk = AppLovinSdk.getInstance(stringExtra, new AppLovinSdkSettings(), getApplicationContext());
        this.a = new WebView(this);
        setContentView(this.a);
        WebSettings settings = this.a.getSettings();
        settings.setSupportMultipleWindows(false);
        settings.setJavaScriptEnabled(true);
        this.a.setVerticalScrollBarEnabled(true);
        this.a.setHorizontalScrollBarEnabled(true);
        this.a.setScrollBarStyle(33554432);
        this.a.setWebViewClient(new WebViewClient() { // from class: com.applovin.sdk.AppLovinWebViewActivity.1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                Uri uri = Uri.parse(str);
                String scheme = uri.getScheme();
                String host = uri.getHost();
                String path = uri.getPath();
                appLovinSdk.getLogger().a("AppLovinWebViewActivity", "Handling url load: " + str);
                if (!"applovin".equalsIgnoreCase(scheme) || !"com.applovin.sdk".equalsIgnoreCase(host) || AppLovinWebViewActivity.this.b == null) {
                    return super.shouldOverrideUrlLoading(webView, str);
                }
                if (!path.endsWith("webview_event")) {
                    return true;
                }
                Set<String> queryParameterNames = uri.getQueryParameterNames();
                String str2 = queryParameterNames.isEmpty() ? "" : (String) queryParameterNames.toArray()[0];
                if (!k.b(str2)) {
                    appLovinSdk.getLogger().d("AppLovinWebViewActivity", "Failed to parse WebView event parameter");
                    return true;
                }
                String queryParameter = uri.getQueryParameter(str2);
                appLovinSdk.getLogger().a("AppLovinWebViewActivity", "Parsed WebView event parameter name: " + str2 + " and value: " + queryParameter);
                AppLovinWebViewActivity.this.b.onReceivedEvent(queryParameter);
                return true;
            }
        });
        if (getIntent().getBooleanExtra(INTENT_EXTRA_KEY_IMMERSIVE_MODE_ON, false)) {
            getWindow().getDecorView().setSystemUiVisibility(5894);
        }
    }
}
