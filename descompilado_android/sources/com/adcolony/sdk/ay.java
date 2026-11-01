package com.adcolony.sdk;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.amazon.device.ads.WebRequest;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.ironsource.sdk.constants.Constants;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class ay extends WebView implements ai {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private JSONArray t;
    private JSONObject u;
    private c v;
    private af w;

    @Override // com.adcolony.sdk.ai
    public void b() {
    }

    ay(Context context, af afVar, int i, int i2, c cVar) {
        super(context);
        this.c = "";
        this.d = "";
        this.f = "";
        this.g = "";
        this.t = y.b();
        this.u = y.a();
        this.w = afVar;
        a(afVar, i, i2, cVar);
        d();
    }

    ay(Context context, int i, boolean z) {
        super(context);
        this.c = "";
        this.d = "";
        this.f = "";
        this.g = "";
        this.t = y.b();
        this.u = y.a();
        this.m = i;
        this.p = z;
    }

    void a(String str) {
        if (this.s) {
            new aa.a().a("Ignoring call to execute_js as WebView has been destroyed.").a(aa.b);
            return;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            evaluateJavascript(str, null);
            return;
        }
        loadUrl("javascript:" + str);
    }

    @Override // com.adcolony.sdk.ai
    public int a() {
        return this.m;
    }

    boolean a(af afVar) {
        JSONObject jSONObjectC = afVar.c();
        return y.c(jSONObjectC, "id") == this.h && y.c(jSONObjectC, "container_id") == this.v.d() && y.b(jSONObjectC, "ad_session_id").equals(this.v.b());
    }

    void d() {
        a(false, (af) null);
    }

    void a(boolean z, af afVar) {
        a aVar;
        if (this.w == null) {
            this.w = afVar;
        }
        final JSONObject jSONObjectC = this.w.c();
        this.o = z;
        this.p = y.d(jSONObjectC, "is_display_module");
        if (z) {
            String strB = y.b(jSONObjectC, "filepath");
            this.b = strB;
            this.a = "file://" + strB;
            this.u = y.f(jSONObjectC, "info");
            this.n = true;
        }
        setFocusable(true);
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        if (Build.VERSION.SDK_INT >= 19) {
            setWebContentsDebuggingEnabled(true);
        }
        setWebChromeClient(new WebChromeClient() { // from class: com.adcolony.sdk.ay.1
            @Override // android.webkit.WebChromeClient
            public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
                new aa.a().a("JS Alert: ").a(str2).a(aa.d);
                return true;
            }

            @Override // android.webkit.WebChromeClient
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Activity activityC;
                if (consoleMessage.messageLevel() == ConsoleMessage.MessageLevel.WARNING) {
                    new aa.a().a("onConsoleMessage: ").a(consoleMessage.message()).a(aa.f);
                } else if (consoleMessage.messageLevel() == ConsoleMessage.MessageLevel.ERROR) {
                    if ((consoleMessage.message().contains("ADC3_update is not defined") || consoleMessage.message().contains("NativeLayer.dispatch_messages is not a function")) && (activityC = com.adcolony.sdk.a.c()) != null && (activityC instanceof b)) {
                        af afVar2 = new af("AdSession.finish_fullscreen_ad", 0);
                        y.b(jSONObjectC, "status", 1);
                        new aa.a().a("Unable to communicate with ad, closing. Please ").a("ensure that you have added an exception for our ").a("Javascript interface in your ProGuard ").a("configuration and that you do not have a faulty ").a("proxy enabled on your device.").a(aa.g);
                        ((b) activityC).a(afVar2);
                    }
                    new aa.a().a("onConsoleMessage: ").a(consoleMessage.message()).a(aa.h);
                }
                return true;
            }
        });
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setGeolocationEnabled(true);
        settings.setUseWideViewPort(true);
        if (Build.VERSION.SDK_INT >= 17) {
            settings.setMediaPlaybackRequiresUserGesture(false);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(true);
            settings.setAllowUniversalAccessFromFileURLs(true);
        }
        if (Build.VERSION.SDK_INT >= 23) {
            aVar = new a() { // from class: com.adcolony.sdk.ay.2
                @Override // android.webkit.WebViewClient
                public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                    if (ay.this.v != null) {
                        JSONObject jSONObjectA = y.a();
                        y.b(jSONObjectA, "id", ay.this.h);
                        y.a(jSONObjectA, "ad_session_id", ay.this.e);
                        y.b(jSONObjectA, "container_id", ay.this.v.d());
                        y.b(jSONObjectA, "code", webResourceError.getErrorCode());
                        y.a(jSONObjectA, "error", webResourceError.getDescription().toString());
                        y.a(jSONObjectA, "url", ay.this.a);
                        new af("WebView.on_error", ay.this.v.c(), jSONObjectA).b();
                    }
                    new aa.a().a("onReceivedError: ").a(webResourceError.getDescription().toString()).a(aa.h);
                }

                @Override // android.webkit.WebViewClient
                public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
                    if (webResourceRequest.getUrl().toString().endsWith("mraid.js")) {
                        try {
                            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(ay.this.f.getBytes("UTF-8"));
                            ay.this.r = true;
                            return new WebResourceResponse("text/javascript", "UTF-8", byteArrayInputStream);
                        } catch (UnsupportedEncodingException unused) {
                            new aa.a().a("UTF-8 not supported.").a(aa.h);
                        }
                    }
                    return null;
                }
            };
        } else if (Build.VERSION.SDK_INT >= 21) {
            aVar = new a() { // from class: com.adcolony.sdk.ay.3
                @Override // android.webkit.WebViewClient
                public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
                    if (webResourceRequest.getUrl().toString().endsWith("mraid.js")) {
                        try {
                            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(ay.this.f.getBytes("UTF-8"));
                            ay.this.r = true;
                            return new WebResourceResponse("text/javascript", "UTF-8", byteArrayInputStream);
                        } catch (UnsupportedEncodingException unused) {
                            new aa.a().a("UTF-8 not supported.").a(aa.h);
                        }
                    }
                    return null;
                }
            };
        } else {
            aVar = new a();
        }
        addJavascriptInterface(new Object() { // from class: com.adcolony.sdk.ay.4
            @JavascriptInterface
            public void dispatch_messages(String str) {
                JSONArray jSONArrayB = y.b(str);
                if (jSONArrayB == null) {
                    new aa.a().a("[INTERNAL] ADCJSON parse error in dispatch_messages ").a("javascript interface function").a(aa.g);
                    return;
                }
                for (int i = 0; i < jSONArrayB.length(); i++) {
                    com.adcolony.sdk.a.a().q().a(y.d(jSONArrayB, i));
                }
            }
        }, "NativeLayer");
        setWebViewClient(aVar);
        if (this.p) {
            try {
                FileInputStream fileInputStream = new FileInputStream(this.b);
                StringBuilder sb = new StringBuilder(fileInputStream.available());
                byte[] bArr = new byte[1024];
                while (true) {
                    int i = fileInputStream.read(bArr, 0, 1024);
                    if (i < 0) {
                        break;
                    } else {
                        sb.append(new String(bArr, 0, i));
                    }
                }
                loadDataWithBaseURL(this.a, sb.toString().replaceFirst("var\\s*ADC_DEVICE_INFO\\s*=\\s*null\\s*;", "var ADC_DEVICE_INFO = " + y.b(y.f(jSONObjectC, "info"), TtmlNode.TAG_METADATA) + ";\n"), WebRequest.CONTENT_TYPE_HTML, null, null);
            } catch (IOException e) {
                a(e);
                return;
            } catch (IllegalArgumentException e2) {
                a(e2);
                return;
            } catch (IndexOutOfBoundsException e3) {
                a(e3);
                return;
            }
        } else if (this.a.startsWith("http") || this.a.startsWith(Constants.ParametersKeys.FILE)) {
            loadUrl(this.a);
        } else {
            loadDataWithBaseURL(this.d.equals("") ? "data" : this.d, z ? y.b(jSONObjectC, "data") : this.a, WebRequest.CONTENT_TYPE_HTML, null, null);
        }
        if (!z) {
            e();
            f();
        }
        if (z || this.n) {
            com.adcolony.sdk.a.a().q().a(this);
        }
        if (this.c.equals("")) {
            return;
        }
        a(this.c);
    }

    private boolean a(Exception exc) {
        AdColonyInterstitialListener listener;
        new aa.a().a(exc.getClass().toString()).a(" during metadata injection w/ metadata = ").a(y.b(this.u, TtmlNode.TAG_METADATA)).a(aa.h);
        AdColonyInterstitial adColonyInterstitialRemove = com.adcolony.sdk.a.a().m().c().remove(y.b(this.u, "ad_session_id"));
        if (adColonyInterstitialRemove == null || (listener = adColonyInterstitialRemove.getListener()) == null) {
            return false;
        }
        listener.onExpiring(adColonyInterstitialRemove);
        adColonyInterstitialRemove.a(true);
        return true;
    }

    private void b(Exception exc) {
        new aa.a().a(exc.getClass().toString()).a(" during metadata injection w/ metadata = ").a(y.b(this.u, TtmlNode.TAG_METADATA)).a(aa.h);
        JSONObject jSONObjectA = y.a();
        y.a(jSONObjectA, "id", this.e);
        new af("AdSession.on_error", this.v.c(), jSONObjectA).b();
    }

    void e() {
        this.v.n().add(com.adcolony.sdk.a.a("WebView.set_visible", new ah() { // from class: com.adcolony.sdk.ay.5
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (ay.this.a(afVar)) {
                    ay.this.c(afVar);
                }
            }
        }, true));
        this.v.n().add(com.adcolony.sdk.a.a("WebView.set_bounds", new ah() { // from class: com.adcolony.sdk.ay.6
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (ay.this.a(afVar)) {
                    ay.this.b(afVar);
                }
            }
        }, true));
        this.v.n().add(com.adcolony.sdk.a.a("WebView.execute_js", new ah() { // from class: com.adcolony.sdk.ay.7
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (ay.this.a(afVar)) {
                    ay.this.a(y.b(afVar.c(), "custom_js"));
                }
            }
        }, true));
        this.v.o().add("WebView.set_visible");
        this.v.o().add("WebView.set_bounds");
        this.v.o().add("WebView.execute_js");
    }

    void f() {
        setVisibility(4);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.k, this.l);
        layoutParams.setMargins(this.i, this.j, 0, 0);
        layoutParams.gravity = 0;
        this.v.addView(this, layoutParams);
    }

    void a(af afVar, int i, c cVar) {
        a(afVar, i, -1, cVar);
        f();
    }

    void a(af afVar, int i, int i2, c cVar) {
        JSONObject jSONObjectC = afVar.c();
        this.a = y.b(jSONObjectC, "url");
        if (this.a.equals("")) {
            this.a = y.b(jSONObjectC, "data");
        }
        this.d = y.b(jSONObjectC, "base_url");
        this.c = y.b(jSONObjectC, "custom_js");
        this.e = y.b(jSONObjectC, "ad_session_id");
        this.u = y.f(jSONObjectC, "info");
        this.g = y.b(jSONObjectC, "mraid_filepath");
        if (!this.p) {
            try {
                this.f = com.adcolony.sdk.a.a().j().a(this.g, false).toString();
                this.f = this.f.replaceFirst("bridge.os_name\\s*=\\s*\"\"\\s*;", "bridge.os_name = \"\";\nvar ADC_DEVICE_INFO = " + this.u.toString() + ";\n");
            } catch (IOException e) {
                b(e);
            } catch (IllegalArgumentException e2) {
                b(e2);
            } catch (IndexOutOfBoundsException e3) {
                b(e3);
            }
        }
        this.h = i;
        this.v = cVar;
        if (i2 >= 0) {
            this.m = i2;
        } else {
            e();
        }
        this.k = y.c(jSONObjectC, "width");
        this.l = y.c(jSONObjectC, "height");
        this.i = y.c(jSONObjectC, "x");
        this.j = y.c(jSONObjectC, "y");
        this.n = y.d(jSONObjectC, "enable_messages") || this.o;
    }

    void b(af afVar) {
        JSONObject jSONObjectC = afVar.c();
        this.i = y.c(jSONObjectC, "x");
        this.j = y.c(jSONObjectC, "y");
        this.k = y.c(jSONObjectC, "width");
        this.l = y.c(jSONObjectC, "height");
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(this.i, this.j, 0, 0);
        layoutParams.width = this.k;
        layoutParams.height = this.l;
        setLayoutParams(layoutParams);
        if (this.o) {
            JSONObject jSONObjectA = y.a();
            y.a(jSONObjectA, "success", true);
            y.b(jSONObjectA, "id", this.m);
            afVar.a(jSONObjectA).b();
        }
    }

    void c(af afVar) {
        if (y.d(afVar.c(), "visible")) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
        if (this.o) {
            JSONObject jSONObjectA = y.a();
            y.a(jSONObjectA, "success", true);
            y.b(jSONObjectA, "id", this.m);
            afVar.a(jSONObjectA).b();
        }
    }

    @Override // com.adcolony.sdk.ai
    public void a(JSONObject jSONObject) {
        synchronized (this.t) {
            this.t.put(jSONObject);
        }
    }

    @Override // com.adcolony.sdk.ai
    public void c() {
        if (com.adcolony.sdk.a.d() && this.q) {
            aw.a(new Runnable() { // from class: com.adcolony.sdk.ay.8
                @Override // java.lang.Runnable
                public void run() {
                    String string;
                    synchronized (ay.this.t) {
                        string = "";
                        if (ay.this.t.length() > 0) {
                            string = ay.this.n ? ay.this.t.toString() : "";
                            ay.this.t = y.b();
                        }
                    }
                    if (ay.this.n) {
                        ay.this.a("NativeLayer.dispatch_messages(ADC3_update(" + string + "));");
                    }
                }
            });
        }
    }

    private class a extends WebViewClient {
        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return false;
        }

        private a() {
        }

        @Override // android.webkit.WebViewClient
        public void onLoadResource(WebView webView, String str) {
            if (str.equals(ay.this.a)) {
                ay.this.a("if (typeof(CN) != 'undefined' && CN.div) {\n  if (typeof(cn_dispatch_on_touch_begin) != 'undefined') CN.div.removeEventListener('mousedown',  cn_dispatch_on_touch_begin, true);\n  if (typeof(cn_dispatch_on_touch_end) != 'undefined')   CN.div.removeEventListener('mouseup',  cn_dispatch_on_touch_end, true);\n  if (typeof(cn_dispatch_on_touch_move) != 'undefined')  CN.div.removeEventListener('mousemove',  cn_dispatch_on_touch_move, true);\n}\n");
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            ay.this.q = false;
            ay.this.r = false;
            new aa.a().a("onPageStarted with URL = ").a(str).a(aa.d);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            JSONObject jSONObjectA = y.a();
            y.b(jSONObjectA, "id", ay.this.h);
            y.a(jSONObjectA, "url", str);
            new aa.a().a("onPageFinished called with URL = ").a(str).a(aa.b);
            if (ay.this.v != null) {
                y.a(jSONObjectA, "ad_session_id", ay.this.e);
                y.b(jSONObjectA, "container_id", ay.this.v.d());
                new af("WebView.on_load", ay.this.v.c(), jSONObjectA).b();
            } else {
                new af("WebView.on_load", ay.this.m, jSONObjectA).b();
            }
            if ((ay.this.n || ay.this.o) && !ay.this.q && (str.startsWith("data") || str.startsWith(Constants.ParametersKeys.FILE) || str.equals(ay.this.d) || ay.this.r)) {
                new aa.a().a("WebView data loaded - executing ADC3_init").a(aa.b);
                new aa.a().a("==============================================================").a(aa.b);
                new aa.a().a("ADC3_init(").a(ay.this.m).a(",").a(ay.this.u.toString()).a(");").a(aa.b);
                new aa.a().a("==============================================================").a(aa.b);
                ay.this.a("ADC3_init(" + ay.this.m + "," + ay.this.u.toString() + ");");
                ay.this.q = true;
            }
            if (ay.this.o) {
                JSONObject jSONObjectA2 = y.a();
                y.a(jSONObjectA2, "success", true);
                y.b(jSONObjectA2, "id", ay.this.m);
                ay.this.w.a(jSONObjectA2).b();
            }
        }

        @Override // android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            if (Build.VERSION.SDK_INT < 21 && str.endsWith("mraid.js")) {
                try {
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(ay.this.f.getBytes("UTF-8"));
                    ay.this.r = true;
                    return new WebResourceResponse("text/javascript", "UTF-8", byteArrayInputStream);
                } catch (UnsupportedEncodingException unused) {
                    new aa.a().a("UTF-8 not supported.").a(aa.h);
                }
            }
            return null;
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            if (Build.VERSION.SDK_INT >= 23) {
                return;
            }
            JSONObject jSONObjectA = y.a();
            y.b(jSONObjectA, "id", ay.this.h);
            y.a(jSONObjectA, "ad_session_id", ay.this.e);
            y.b(jSONObjectA, "container_id", ay.this.v.d());
            y.b(jSONObjectA, "code", i);
            y.a(jSONObjectA, "error", str);
            y.a(jSONObjectA, "url", str2);
            new af("WebView.on_error", ay.this.v.c(), jSONObjectA).b();
        }
    }

    boolean g() {
        return this.p;
    }

    boolean h() {
        return this.s;
    }

    void a(boolean z) {
        this.s = z;
    }
}
