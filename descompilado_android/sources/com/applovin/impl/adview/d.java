package com.applovin.impl.adview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.ViewParent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.applovin.adview.AppLovinAdView;
import com.applovin.impl.sdk.AppLovinAdServiceImpl;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdService;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class d extends WebViewClient {
    private final com.applovin.impl.sdk.h a;
    private final com.applovin.impl.sdk.n b;
    private final AdViewControllerImpl c;

    public d(AdViewControllerImpl adViewControllerImpl, com.applovin.impl.sdk.h hVar) {
        this.a = hVar;
        this.b = hVar.w();
        this.c = adViewControllerImpl;
    }

    private void a() {
        this.c.a();
    }

    private void a(Uri uri, c cVar) {
        com.applovin.impl.sdk.n nVar;
        String str;
        try {
            String queryParameter = uri.getQueryParameter("n");
            if (com.applovin.impl.sdk.utils.k.b(queryParameter)) {
                String queryParameter2 = uri.getQueryParameter("load_type");
                if ("external".equalsIgnoreCase(queryParameter2)) {
                    this.b.a("AdWebViewClient", "Loading new page externally: " + queryParameter);
                    com.applovin.impl.sdk.utils.n.a(cVar.getContext(), Uri.parse(queryParameter), this.a);
                    com.applovin.impl.sdk.utils.h.c(this.c.getAdViewEventListener(), this.c.getCurrentAd(), this.c.getParentView(), this.a);
                    return;
                }
                if ("internal".equalsIgnoreCase(queryParameter2)) {
                    this.b.a("AdWebViewClient", "Loading new page in WebView: " + queryParameter);
                    cVar.loadUrl(queryParameter);
                    String queryParameter3 = uri.getQueryParameter("bg_color");
                    if (com.applovin.impl.sdk.utils.k.b(queryParameter3)) {
                        cVar.setBackgroundColor(Color.parseColor(queryParameter3));
                        return;
                    }
                    return;
                }
                nVar = this.b;
                str = "Could not find load type in original uri";
            } else {
                nVar = this.b;
                str = "Could not find url to load from query in original uri";
            }
            nVar.d("AdWebViewClient", str);
        } catch (Throwable unused) {
            this.b.d("AdWebViewClient", "Failed to load new page from query in original uri");
        }
    }

    private void a(com.applovin.impl.a.a aVar, c cVar) {
        com.applovin.impl.a.b bVarD = aVar.d();
        if (bVarD != null) {
            com.applovin.impl.a.i.a(bVarD.c(), this.c.getSdk());
            a(cVar, bVarD.a());
        }
    }

    private void a(c cVar) {
        ViewParent parent = cVar.getParent();
        if (parent instanceof AppLovinAdView) {
            ((AppLovinAdView) parent).loadNextAd();
        }
    }

    private void a(c cVar, Uri uri) {
        AppLovinAd appLovinAdA = cVar.a();
        AppLovinAdView parentView = this.c.getParentView();
        if (parentView != null && appLovinAdA != null) {
            com.applovin.impl.sdk.c.d dVarB = cVar.b();
            if (dVarB != null) {
                dVarB.b();
            }
            this.c.a(appLovinAdA, parentView, uri);
            return;
        }
        this.b.d("AdWebViewClient", "Attempting to track click that is null or not an ApplovinAdView instance for clickedUri = " + uri);
    }

    private boolean a(WebView webView, String str, boolean z) {
        com.applovin.impl.sdk.n nVar;
        String str2;
        com.applovin.impl.a.a aVar;
        this.b.b("AdWebViewClient", "Processing click on ad URL \"" + str + "\"");
        if (str != null && (webView instanceof c)) {
            Uri uri = Uri.parse(str);
            c cVar = (c) webView;
            String scheme = uri.getScheme();
            String host = uri.getHost();
            String path = uri.getPath();
            AppLovinAd currentAd = this.c.getCurrentAd();
            if ("applovin".equals(scheme) && "com.applovin.sdk".equals(host)) {
                if (AppLovinAdService.URI_NEXT_AD.equals(path)) {
                    a(cVar);
                } else if (AppLovinAdService.URI_CLOSE_AD.equals(path)) {
                    a();
                } else if (AppLovinAdService.URI_EXPAND_AD.equals(path)) {
                    b();
                } else if (AppLovinAdService.URI_CONTRACT_AD.equals(path)) {
                    c();
                } else {
                    if (AppLovinAdServiceImpl.URI_NO_OP.equals(path)) {
                        return true;
                    }
                    if (AppLovinAdServiceImpl.URI_LOAD_URL.equals(path)) {
                        a(uri, cVar);
                    } else if (AppLovinAdServiceImpl.URI_TRACK_CLICK_IMMEDIATELY.equals(path)) {
                        if (currentAd instanceof com.applovin.impl.a.a) {
                            aVar = (com.applovin.impl.a.a) currentAd;
                            a(aVar, cVar);
                        } else {
                            a(cVar, Uri.parse(AppLovinAdServiceImpl.URI_TRACK_CLICK_IMMEDIATELY));
                        }
                    } else if (path == null || !path.startsWith("/launch/")) {
                        this.b.c("AdWebViewClient", "Unknown URL: " + str);
                        this.b.c("AdWebViewClient", "Path: " + path);
                    } else {
                        List<String> pathSegments = uri.getPathSegments();
                        if (pathSegments != null && pathSegments.size() > 1) {
                            String str3 = pathSegments.get(pathSegments.size() - 1);
                            try {
                                Context context = webView.getContext();
                                context.startActivity(context.getPackageManager().getLaunchIntentForPackage(str3));
                                a(cVar, (Uri) null);
                            } catch (Throwable th) {
                                this.b.b("AdWebViewClient", "Threw Exception Trying to Launch App for Package: " + str3, th);
                            }
                        }
                    }
                }
            } else {
                if (!z) {
                    return false;
                }
                if (currentAd instanceof com.applovin.impl.sdk.ad.g) {
                    com.applovin.impl.sdk.ad.g gVar = (com.applovin.impl.sdk.ad.g) currentAd;
                    List<String> listAv = gVar.av();
                    List<String> listAw = gVar.aw();
                    if ((listAv.isEmpty() || listAv.contains(scheme)) && (listAw.isEmpty() || listAw.contains(host))) {
                        if (currentAd instanceof com.applovin.impl.a.a) {
                            aVar = (com.applovin.impl.a.a) currentAd;
                            if (aVar.i()) {
                                a(aVar, cVar);
                            }
                        }
                        a(cVar, uri);
                    } else {
                        nVar = this.b;
                        str2 = "URL is not whitelisted - bypassing click";
                    }
                } else {
                    nVar = this.b;
                    str2 = "Bypassing click for ad of invalid type";
                }
                nVar.d("AdWebViewClient", str2);
            }
        }
        return true;
    }

    private void b() {
        this.c.expandAd();
    }

    private void c() {
        this.c.contractAd();
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        this.c.onAdHtmlLoaded(webView);
    }

    @Override // android.webkit.WebViewClient
    @TargetApi(24)
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        boolean zHasGesture = ((Boolean) this.a.a(com.applovin.impl.sdk.b.b.cl)).booleanValue() ? webResourceRequest.hasGesture() : true;
        Uri url = webResourceRequest.getUrl();
        if (url != null) {
            return a(webView, url.toString(), zHasGesture);
        }
        this.b.d("AdWebViewClient", "No url found for request");
        return false;
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return a(webView, str, true);
    }
}
