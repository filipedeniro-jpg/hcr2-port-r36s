package com.applovin.impl.adview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.amazon.device.ads.WebRequest;
import com.applovin.sdk.AppLovinAd;
import com.appsflyer.share.Constants;

/* JADX INFO: loaded from: classes.dex */
class c extends WebView {
    private final com.applovin.impl.sdk.n a;
    private final com.applovin.impl.sdk.h b;
    private com.applovin.impl.sdk.c.d c;
    private AppLovinAd d;
    private boolean e;

    c(d dVar, com.applovin.impl.sdk.h hVar, Context context) {
        super(context);
        this.d = null;
        this.e = false;
        if (hVar == null) {
            throw new IllegalArgumentException("No sdk specified.");
        }
        this.b = hVar;
        this.a = hVar.w();
        setBackgroundColor(0);
        WebSettings settings = getSettings();
        settings.setSupportMultipleWindows(false);
        settings.setJavaScriptEnabled(true);
        setWebViewClient(dVar);
        setWebChromeClient(new b(hVar));
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        setScrollBarStyle(33554432);
        setOnTouchListener(new View.OnTouchListener() { // from class: com.applovin.impl.adview.c.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (view.hasFocus()) {
                    return false;
                }
                view.requestFocus();
                return false;
            }
        });
        setOnLongClickListener(new View.OnLongClickListener() { // from class: com.applovin.impl.adview.c.12
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                c.this.a.a("AdWebView", "Received a LongClick event.");
                return true;
            }
        });
    }

    private String a(String str, String str2) {
        if (com.applovin.impl.sdk.utils.k.b(str)) {
            return com.applovin.impl.sdk.utils.n.b(str).replace("{SOURCE}", str2);
        }
        return null;
    }

    private void a(final com.applovin.impl.sdk.ad.g gVar) {
        final Boolean boolN;
        final Integer numA;
        try {
            if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.fb)).booleanValue() || gVar.ar()) {
                a(new Runnable() { // from class: com.applovin.impl.adview.c.13
                    @Override // java.lang.Runnable
                    public void run() {
                        c.this.loadUrl("about:blank");
                    }
                });
            }
            if (com.applovin.impl.sdk.utils.e.d()) {
                a(new Runnable() { // from class: com.applovin.impl.adview.c.14
                    @Override // java.lang.Runnable
                    @TargetApi(17)
                    public void run() {
                        c.this.getSettings().setMediaPlaybackRequiresUserGesture(gVar.aq());
                    }
                });
            }
            if (com.applovin.impl.sdk.utils.e.e() && gVar.at()) {
                a(new Runnable() { // from class: com.applovin.impl.adview.c.15
                    @Override // java.lang.Runnable
                    @TargetApi(19)
                    public void run() {
                        WebView.setWebContentsDebuggingEnabled(true);
                    }
                });
            }
            t tVarAu = gVar.au();
            if (tVarAu != null) {
                final WebSettings settings = getSettings();
                final WebSettings.PluginState pluginStateB = tVarAu.b();
                if (pluginStateB != null) {
                    a(new Runnable() { // from class: com.applovin.impl.adview.c.16
                        @Override // java.lang.Runnable
                        public void run() {
                            settings.setPluginState(pluginStateB);
                        }
                    });
                }
                final Boolean boolC = tVarAu.c();
                if (boolC != null) {
                    a(new Runnable() { // from class: com.applovin.impl.adview.c.17
                        @Override // java.lang.Runnable
                        public void run() {
                            settings.setAllowFileAccess(boolC.booleanValue());
                        }
                    });
                }
                final Boolean boolD = tVarAu.d();
                if (boolD != null) {
                    a(new Runnable() { // from class: com.applovin.impl.adview.c.18
                        @Override // java.lang.Runnable
                        public void run() {
                            settings.setLoadWithOverviewMode(boolD.booleanValue());
                        }
                    });
                }
                final Boolean boolE = tVarAu.e();
                if (boolE != null) {
                    a(new Runnable() { // from class: com.applovin.impl.adview.c.19
                        @Override // java.lang.Runnable
                        public void run() {
                            settings.setUseWideViewPort(boolE.booleanValue());
                        }
                    });
                }
                final Boolean boolF = tVarAu.f();
                if (boolF != null) {
                    a(new Runnable() { // from class: com.applovin.impl.adview.c.2
                        @Override // java.lang.Runnable
                        public void run() {
                            settings.setAllowContentAccess(boolF.booleanValue());
                        }
                    });
                }
                final Boolean boolG = tVarAu.g();
                if (boolG != null) {
                    a(new Runnable() { // from class: com.applovin.impl.adview.c.3
                        @Override // java.lang.Runnable
                        public void run() {
                            settings.setBuiltInZoomControls(boolG.booleanValue());
                        }
                    });
                }
                final Boolean boolH = tVarAu.h();
                if (boolH != null) {
                    a(new Runnable() { // from class: com.applovin.impl.adview.c.4
                        @Override // java.lang.Runnable
                        public void run() {
                            settings.setDisplayZoomControls(boolH.booleanValue());
                        }
                    });
                }
                final Boolean boolI = tVarAu.i();
                if (boolI != null) {
                    a(new Runnable() { // from class: com.applovin.impl.adview.c.5
                        @Override // java.lang.Runnable
                        public void run() {
                            settings.setSaveFormData(boolI.booleanValue());
                        }
                    });
                }
                final Boolean boolJ = tVarAu.j();
                if (boolJ != null) {
                    a(new Runnable() { // from class: com.applovin.impl.adview.c.6
                        @Override // java.lang.Runnable
                        public void run() {
                            settings.setGeolocationEnabled(boolJ.booleanValue());
                        }
                    });
                }
                final Boolean boolK = tVarAu.k();
                if (boolK != null) {
                    a(new Runnable() { // from class: com.applovin.impl.adview.c.7
                        @Override // java.lang.Runnable
                        public void run() {
                            settings.setNeedInitialFocus(boolK.booleanValue());
                        }
                    });
                }
                if (com.applovin.impl.sdk.utils.e.c()) {
                    final Boolean boolL = tVarAu.l();
                    if (boolL != null) {
                        a(new Runnable() { // from class: com.applovin.impl.adview.c.8
                            @Override // java.lang.Runnable
                            @TargetApi(16)
                            public void run() {
                                settings.setAllowFileAccessFromFileURLs(boolL.booleanValue());
                            }
                        });
                    }
                    final Boolean boolM = tVarAu.m();
                    if (boolM != null) {
                        a(new Runnable() { // from class: com.applovin.impl.adview.c.9
                            @Override // java.lang.Runnable
                            @TargetApi(16)
                            public void run() {
                                settings.setAllowUniversalAccessFromFileURLs(boolM.booleanValue());
                            }
                        });
                    }
                }
                if (com.applovin.impl.sdk.utils.e.f() && (numA = tVarAu.a()) != null) {
                    a(new Runnable() { // from class: com.applovin.impl.adview.c.10
                        @Override // java.lang.Runnable
                        @TargetApi(21)
                        public void run() {
                            settings.setMixedContentMode(numA.intValue());
                        }
                    });
                }
                if (!com.applovin.impl.sdk.utils.e.g() || (boolN = tVarAu.n()) == null) {
                    return;
                }
                a(new Runnable() { // from class: com.applovin.impl.adview.c.11
                    @Override // java.lang.Runnable
                    @TargetApi(23)
                    public void run() {
                        settings.setOffscreenPreRaster(boolN.booleanValue());
                    }
                });
            }
        } catch (Throwable th) {
            this.a.b("AdWebView", "Unable to apply WebView settings", th);
        }
    }

    private void a(Runnable runnable) {
        try {
            runnable.run();
        } catch (Throwable th) {
            this.a.b("AdWebView", "Unable to apply WebView setting", th);
        }
    }

    private void a(String str, String str2, String str3, com.applovin.impl.sdk.h hVar) {
        String strA = a(str3, str);
        if (com.applovin.impl.sdk.utils.k.b(strA)) {
            this.a.a("AdWebView", "Rendering webview for VAST ad with resourceContents : " + strA);
            loadDataWithBaseURL(str2, strA, WebRequest.CONTENT_TYPE_HTML, null, "");
            return;
        }
        String strA2 = a((String) hVar.a(com.applovin.impl.sdk.b.b.eK), str);
        if (com.applovin.impl.sdk.utils.k.b(strA2)) {
            this.a.a("AdWebView", "Rendering webview for VAST ad with resourceContents : " + strA2);
            loadDataWithBaseURL(str2, strA2, WebRequest.CONTENT_TYPE_HTML, null, "");
            return;
        }
        this.a.a("AdWebView", "Rendering webview for VAST ad with resourceURL : " + str);
        loadUrl(str);
    }

    AppLovinAd a() {
        return this.d;
    }

    public void a(com.applovin.impl.sdk.c.d dVar) {
        this.c = dVar;
    }

    public void a(AppLovinAd appLovinAd) {
        com.applovin.impl.sdk.n nVar;
        String str;
        com.applovin.impl.sdk.n nVar2;
        String str2;
        String str3;
        String strAs;
        String str4;
        String str5;
        String str6;
        String strAs2;
        com.applovin.impl.sdk.h hVar;
        if (this.e) {
            this.a.e("AdWebView", "Ad can not be loaded in a destroyed webview");
            return;
        }
        this.d = appLovinAd;
        try {
            if (appLovinAd instanceof com.applovin.impl.sdk.ad.i) {
                loadDataWithBaseURL(Constants.URL_PATH_DELIMITER, ((com.applovin.impl.sdk.ad.i) appLovinAd).a(), WebRequest.CONTENT_TYPE_HTML, null, "");
                nVar = this.a;
                str = "Empty ad rendered";
            } else {
                com.applovin.impl.sdk.ad.g gVar = (com.applovin.impl.sdk.ad.g) appLovinAd;
                a(gVar);
                if (gVar.ac()) {
                    setVisibility(0);
                }
                if (appLovinAd instanceof com.applovin.impl.sdk.ad.a) {
                    loadDataWithBaseURL(gVar.as(), com.applovin.impl.sdk.utils.n.b(((com.applovin.impl.sdk.ad.a) appLovinAd).a()), WebRequest.CONTENT_TYPE_HTML, null, "");
                    nVar = this.a;
                    str = "AppLovinAd rendered";
                } else {
                    if (!(appLovinAd instanceof com.applovin.impl.a.a)) {
                        return;
                    }
                    com.applovin.impl.a.a aVar = (com.applovin.impl.a.a) appLovinAd;
                    com.applovin.impl.a.b bVarD = aVar.d();
                    if (bVarD != null) {
                        com.applovin.impl.a.e eVarB = bVarD.b();
                        Uri uriB = eVarB.b();
                        String string = uriB != null ? uriB.toString() : "";
                        String strC = eVarB.c();
                        String strJ = aVar.j();
                        if (!com.applovin.impl.sdk.utils.k.b(string) && !com.applovin.impl.sdk.utils.k.b(strC)) {
                            nVar2 = this.a;
                            str2 = "Unable to load companion ad. No resources provided.";
                        } else {
                            if (eVarB.a() == com.applovin.impl.a.e.a.STATIC) {
                                this.a.a("AdWebView", "Rendering WebView for static VAST ad");
                                loadDataWithBaseURL(gVar.as(), a((String) this.b.a(com.applovin.impl.sdk.b.b.eJ), string), WebRequest.CONTENT_TYPE_HTML, null, "");
                                return;
                            }
                            if (eVarB.a() == com.applovin.impl.a.e.a.HTML) {
                                if (!com.applovin.impl.sdk.utils.k.b(strC)) {
                                    if (com.applovin.impl.sdk.utils.k.b(string)) {
                                        this.a.a("AdWebView", "Preparing to load HTML VAST ad resourceUri");
                                        strAs2 = gVar.as();
                                        hVar = this.b;
                                        a(string, strAs2, strJ, hVar);
                                        return;
                                    }
                                    return;
                                }
                                String strA = a(strJ, strC);
                                str3 = com.applovin.impl.sdk.utils.k.b(strA) ? strA : strC;
                                this.a.a("AdWebView", "Rendering WebView for HTML VAST ad with resourceContents: " + str3);
                                strAs = gVar.as();
                                str4 = WebRequest.CONTENT_TYPE_HTML;
                                str5 = null;
                                str6 = "";
                                loadDataWithBaseURL(strAs, str3, str4, str5, str6);
                                return;
                            }
                            if (eVarB.a() == com.applovin.impl.a.e.a.IFRAME) {
                                if (com.applovin.impl.sdk.utils.k.b(string)) {
                                    this.a.a("AdWebView", "Preparing to load iFrame VAST ad resourceUri");
                                    strAs2 = gVar.as();
                                    hVar = this.b;
                                    a(string, strAs2, strJ, hVar);
                                    return;
                                }
                                if (com.applovin.impl.sdk.utils.k.b(strC)) {
                                    String strA2 = a(strJ, strC);
                                    str3 = com.applovin.impl.sdk.utils.k.b(strA2) ? strA2 : strC;
                                    this.a.a("AdWebView", "Rendering WebView for iFrame VAST ad with resourceContents: " + str3);
                                    strAs = gVar.as();
                                    str4 = WebRequest.CONTENT_TYPE_HTML;
                                    str5 = null;
                                    str6 = "";
                                    loadDataWithBaseURL(strAs, str3, str4, str5, str6);
                                    return;
                                }
                                return;
                            }
                            nVar2 = this.a;
                            str2 = "Failed to render VAST companion ad of invalid type";
                        }
                        nVar2.d("AdWebView", str2);
                        return;
                    }
                    nVar = this.a;
                    str = "No companion ad provided.";
                }
            }
            nVar.a("AdWebView", str);
        } catch (Throwable th) {
            this.a.b("AdWebView", "Unable to render AppLovinAd", th);
        }
    }

    public void a(String str) {
        a(str, (Runnable) null);
    }

    public void a(String str, Runnable runnable) {
        try {
            this.a.a("AdWebView", "Forwarding \"" + str + "\" to ad template");
            loadUrl(str);
        } catch (Throwable th) {
            this.a.b("AdWebView", "Unable to forward to template", th);
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    public com.applovin.impl.sdk.c.d b() {
        return this.c;
    }

    @Override // android.webkit.WebView, android.view.View
    public void computeScroll() {
    }

    @Override // android.webkit.WebView
    public void destroy() {
        this.e = true;
        try {
            super.destroy();
            this.a.a("AdWebView", "Web view destroyed");
        } catch (Throwable th) {
            com.applovin.impl.sdk.n nVar = this.a;
            if (nVar != null) {
                nVar.b("AdWebView", "destroy() threw exception", th);
            }
        }
    }

    @Override // android.webkit.WebView, android.view.View
    protected void onFocusChanged(boolean z, int i, Rect rect) {
        try {
            super.onFocusChanged(z, i, rect);
        } catch (Exception e) {
            this.a.b("AdWebView", "onFocusChanged() threw exception", e);
        }
    }

    @Override // android.webkit.WebView, android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
    }

    @Override // android.webkit.WebView, android.view.View
    public void onWindowFocusChanged(boolean z) {
        try {
            super.onWindowFocusChanged(z);
        } catch (Exception e) {
            this.a.b("AdWebView", "onWindowFocusChanged() threw exception", e);
        }
    }

    @Override // android.webkit.WebView, android.view.View
    protected void onWindowVisibilityChanged(int i) {
        try {
            super.onWindowVisibilityChanged(i);
        } catch (Exception e) {
            this.a.b("AdWebView", "onWindowVisibilityChanged() threw exception", e);
        }
    }

    @Override // android.webkit.WebView, android.view.ViewGroup, android.view.View
    public boolean requestFocus(int i, Rect rect) {
        try {
            return super.requestFocus(i, rect);
        } catch (Exception e) {
            this.a.b("AdWebView", "requestFocus() threw exception", e);
            return false;
        }
    }

    @Override // android.view.View
    public void scrollTo(int i, int i2) {
    }
}
