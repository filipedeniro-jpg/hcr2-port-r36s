package com.my.target;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.device.ads.WebRequest;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: BannerWebView.java */
/* JADX INFO: loaded from: classes2.dex */
public class bt extends WebView {

    /* JADX INFO: renamed from: if, reason: not valid java name */
    @NonNull
    private static final String f704if = "https://ad.mail.ru/";

    @NonNull
    private static final String ig = "javascript:AdmanJS.execute";

    @NonNull
    private final WebViewClient ih;

    @NonNull
    private final WebChromeClient ii;

    @Nullable
    private JSONObject ij;

    @Nullable
    private a ik;
    private boolean il;
    private boolean im;

    /* JADX INFO: compiled from: BannerWebView.java */
    public interface a {
        void J(@NonNull String str);

        void a(@NonNull v vVar);

        void onError(@NonNull String str);
    }

    public bt(@NonNull Context context) {
        this(context, null);
    }

    public bt(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public bt(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.ii = new b();
        this.ih = new c();
        final d dVar = new d(getContext(), this);
        dVar.a(new d.a() { // from class: com.my.target.bt.1
            @Override // com.my.target.bt.d.a
            public void aX() {
                bt.this.im = true;
            }
        });
        setOnTouchListener(new View.OnTouchListener() { // from class: com.my.target.bt.2
            @Override // android.view.View.OnTouchListener
            @SuppressLint({"ClickableViewAccessibility"})
            public boolean onTouch(View view, MotionEvent motionEvent) {
                dVar.a(motionEvent);
                return false;
            }
        });
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setSupportZoom(false);
        settings.setAppCachePath(getContext().getCacheDir().getAbsolutePath());
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        if (Build.VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(false);
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
        setWebChromeClient(this.ii);
        setWebViewClient(this.ih);
    }

    public void setBannerWebViewListener(@Nullable a aVar) {
        this.ik = aVar;
    }

    public void a(@Nullable JSONObject jSONObject, @NonNull String str) {
        this.il = false;
        this.im = false;
        loadDataWithBaseURL(f704if, str, WebRequest.CONTENT_TYPE_HTML, "UTF-8", null);
        this.ij = jSONObject;
    }

    public void a(@NonNull m mVar) {
        String str = "javascript:AdmanJS.execute(" + mVar.g().toString() + ")";
        g.a(str);
        loadUrl(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aW() {
        this.im = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I(@NonNull String str) {
        a aVar = this.ik;
        if (aVar != null) {
            aVar.J(str);
        }
    }

    /* JADX INFO: compiled from: BannerWebView.java */
    private class b extends WebChromeClient {
        private b() {
        }

        @Override // android.webkit.WebChromeClient
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            g.a("js console message: " + consoleMessage.message() + " at line: " + consoleMessage.lineNumber());
            v vVarA = j.a(consoleMessage);
            if (vVarA == null) {
                return false;
            }
            if (bt.this.ik == null) {
                return true;
            }
            bt.this.ik.a(vVarA);
            return true;
        }
    }

    /* JADX INFO: compiled from: BannerWebView.java */
    private class c extends WebViewClient {
        private c() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (!bt.this.im || str == null || str.startsWith(j.PREFIX)) {
                return true;
            }
            bt.this.I(str);
            bt.this.aW();
            return true;
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(24)
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            Uri url;
            String string;
            if (!bt.this.im || (url = webResourceRequest.getUrl()) == null || (string = url.toString()) == null || string.startsWith(j.PREFIX)) {
                return true;
            }
            bt.this.I(string);
            bt.this.aW();
            return true;
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            g.a("load page started");
            super.onPageStarted(webView, str, bitmap);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            if (bt.this.il) {
                return;
            }
            bt.this.il = true;
            g.a("page loaded");
            super.onPageFinished(webView, str);
            if (bt.this.ij != null) {
                try {
                    bt.this.a(new n(bt.this.ij));
                } catch (JSONException e) {
                    g.a("js call executing error " + e.getMessage());
                }
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            g.a("load failed. error: " + i + " description: " + str + " url: " + str2);
            super.onReceivedError(webView, i, str, str2);
            if (bt.this.ik != null) {
                a aVar = bt.this.ik;
                if (str == null) {
                    str = "unknown JS error";
                }
                aVar.onError(str);
            }
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(23)
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            super.onReceivedError(webView, webResourceRequest, webResourceError);
            CharSequence description = webResourceError.getDescription();
            String string = description != null ? description.toString() : null;
            g.a("load failed. error: " + webResourceError.getErrorCode() + " description: " + string + " url: " + webResourceRequest.getUrl().toString());
            if (bt.this.ik != null) {
                a aVar = bt.this.ik;
                if (string == null) {
                    string = "Unknown JS error";
                }
                aVar.onError(string);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onScaleChanged(WebView webView, float f, float f2) {
            super.onScaleChanged(webView, f, f2);
            g.a("scale new: " + f2 + " old: " + f);
        }
    }

    /* JADX INFO: compiled from: BannerWebView.java */
    private static class d extends GestureDetector {

        @Nullable
        private a ip;

        @NonNull
        private final View mView;

        /* JADX INFO: compiled from: BannerWebView.java */
        interface a {
            void aX();
        }

        d(@NonNull Context context, @NonNull View view) {
            this(context, view, new GestureDetector.SimpleOnGestureListener());
        }

        private d(@NonNull Context context, @NonNull View view, @NonNull GestureDetector.SimpleOnGestureListener simpleOnGestureListener) {
            super(context, simpleOnGestureListener);
            this.mView = view;
            setIsLongpressEnabled(false);
        }

        void a(@NonNull MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == 0) {
                onTouchEvent(motionEvent);
                return;
            }
            if (action == 1) {
                if (this.ip != null) {
                    g.a("Gestures: user clicked");
                    this.ip.aX();
                    return;
                } else {
                    g.a("View's onUserClick() is not registered.");
                    return;
                }
            }
            if (action == 2 && a(motionEvent, this.mView)) {
                onTouchEvent(motionEvent);
            }
        }

        void a(@Nullable a aVar) {
            this.ip = aVar;
        }

        private boolean a(@Nullable MotionEvent motionEvent, @Nullable View view) {
            if (motionEvent == null || view == null) {
                return false;
            }
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            return x >= 0.0f && x <= ((float) view.getWidth()) && y >= 0.0f && y <= ((float) view.getHeight());
        }
    }
}
