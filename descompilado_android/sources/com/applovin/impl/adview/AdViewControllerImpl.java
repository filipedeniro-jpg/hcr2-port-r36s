package com.applovin.impl.adview;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.amazon.device.ads.WebRequest;
import com.applovin.adview.AdViewController;
import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinAdViewEventListener;
import com.applovin.impl.sdk.AppLovinAdServiceImpl;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdService;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.appsflyer.share.Constants;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public class AdViewControllerImpl implements AdViewController {
    private volatile AppLovinAdClickListener A;
    private Context a;
    private ViewGroup b;
    private com.applovin.impl.sdk.h c;
    private AppLovinAdServiceImpl d;
    private com.applovin.impl.sdk.n e;
    private AppLovinAdSize f;
    private String g;
    private com.applovin.impl.sdk.c.d h;
    private com.applovin.impl.adview.d i;
    private d j;
    private com.applovin.impl.adview.c k;
    private AppLovinAd l;
    private Runnable m;
    private Runnable n;
    private volatile AppLovinAd o = null;
    private volatile AppLovinAd p = null;
    private i q = null;
    private i r = null;
    private final AtomicReference<AppLovinAd> s = new AtomicReference<>();
    private volatile boolean t = false;
    private volatile boolean u = true;
    private volatile boolean v = false;
    private volatile boolean w = false;
    private volatile AppLovinAdLoadListener x;
    private volatile AppLovinAdDisplayListener y;
    private volatile AppLovinAdViewEventListener z;

    private class a implements Runnable {
        private a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (AdViewControllerImpl.this.k != null) {
                AdViewControllerImpl.this.k.setVisibility(8);
            }
        }
    }

    private class b implements Runnable {
        private b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (AdViewControllerImpl.this.k != null) {
                try {
                    AdViewControllerImpl.this.k.loadDataWithBaseURL(Constants.URL_PATH_DELIMITER, "<html></html>", WebRequest.CONTENT_TYPE_HTML, null, "");
                } catch (Exception unused) {
                }
            }
        }
    }

    private class c implements Runnable {
        private c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (AdViewControllerImpl.this.o != null) {
                if (AdViewControllerImpl.this.k == null) {
                    AdViewControllerImpl.this.c.w().e("AppLovinAdView", "Unable to render advertisement for ad #" + AdViewControllerImpl.this.o.getAdIdNumber() + ". Please make sure you are not calling AppLovinAdView.destroy() prematurely.");
                    return;
                }
                AdViewControllerImpl.this.e.a("AppLovinAdView", "Rendering advertisement ad for #" + AdViewControllerImpl.this.o.getAdIdNumber() + "...");
                AdViewControllerImpl.b(AdViewControllerImpl.this.k, AdViewControllerImpl.this.o.getSize());
                AdViewControllerImpl.this.k.a(AdViewControllerImpl.this.o);
                if (AdViewControllerImpl.this.o.getSize() != AppLovinAdSize.INTERSTITIAL && !AdViewControllerImpl.this.v && !(AdViewControllerImpl.this.o instanceof com.applovin.impl.sdk.ad.i)) {
                    AdViewControllerImpl adViewControllerImpl = AdViewControllerImpl.this;
                    adViewControllerImpl.h = new com.applovin.impl.sdk.c.d(adViewControllerImpl.o, AdViewControllerImpl.this.c);
                    AdViewControllerImpl.this.h.a();
                    AdViewControllerImpl.this.k.a(AdViewControllerImpl.this.h);
                    if (AdViewControllerImpl.this.o instanceof com.applovin.impl.sdk.ad.g) {
                        ((com.applovin.impl.sdk.ad.g) AdViewControllerImpl.this.o).setHasShown(true);
                    }
                }
                if (AdViewControllerImpl.this.k.b() == null || !(AdViewControllerImpl.this.o instanceof com.applovin.impl.sdk.ad.g)) {
                    return;
                }
                AdViewControllerImpl.this.k.b().a(((com.applovin.impl.sdk.ad.g) AdViewControllerImpl.this.o).t() ? 0L : 1L);
            }
        }
    }

    static class d implements AppLovinAdLoadListener {
        private final AppLovinAdService a;
        private final com.applovin.impl.sdk.n b;
        private final AdViewControllerImpl c;

        d(AdViewControllerImpl adViewControllerImpl, com.applovin.impl.sdk.h hVar) {
            if (adViewControllerImpl == null) {
                throw new IllegalArgumentException("No view specified");
            }
            if (hVar == null) {
                throw new IllegalArgumentException("No sdk specified");
            }
            this.b = hVar.w();
            this.a = hVar.p();
            this.c = adViewControllerImpl;
        }

        private AdViewControllerImpl a() {
            return this.c;
        }

        @Override // com.applovin.sdk.AppLovinAdLoadListener
        public void adReceived(AppLovinAd appLovinAd) {
            AdViewControllerImpl adViewControllerImplA = a();
            if (adViewControllerImplA != null) {
                adViewControllerImplA.a(appLovinAd);
            } else {
                this.b.e("AppLovinAdView", "Ad view has been garbage collected by the time an ad was received");
            }
        }

        @Override // com.applovin.sdk.AppLovinAdLoadListener
        public void failedToReceiveAd(int i) {
            AdViewControllerImpl adViewControllerImplA = a();
            if (adViewControllerImplA != null) {
                adViewControllerImplA.a(i);
            }
        }
    }

    private void a(AppLovinAdView appLovinAdView, com.applovin.impl.sdk.h hVar, AppLovinAdSize appLovinAdSize, String str, Context context) {
        if (appLovinAdView == null) {
            throw new IllegalArgumentException("No parent view specified");
        }
        if (hVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        if (appLovinAdSize == null) {
            throw new IllegalArgumentException("No ad size specified");
        }
        this.c = hVar;
        this.d = hVar.p();
        this.e = hVar.w();
        this.f = appLovinAdSize;
        this.g = str;
        this.a = context;
        this.b = appLovinAdView;
        this.l = new com.applovin.impl.sdk.ad.i();
        this.i = new com.applovin.impl.adview.d(this, hVar);
        this.n = new a();
        this.m = new c();
        this.j = new d(this, hVar);
        a(appLovinAdSize);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AppLovinAdSize appLovinAdSize) {
        b bVar;
        try {
            this.k = new com.applovin.impl.adview.c(this.i, this.c, this.a);
            this.k.setBackgroundColor(0);
            this.k.setWillNotCacheDrawing(false);
            this.b.setBackgroundColor(0);
            this.b.addView(this.k);
            b(this.k, appLovinAdSize);
            if (this.c.g()) {
                if (!this.t && ((Boolean) this.c.a(com.applovin.impl.sdk.b.b.ek)).booleanValue()) {
                    a(this.n);
                }
                if (((Boolean) this.c.a(com.applovin.impl.sdk.b.b.el)).booleanValue()) {
                    bVar = new b();
                    a(bVar);
                }
            } else {
                if (!this.t) {
                    a(this.n);
                }
                if (((Boolean) this.c.a(com.applovin.impl.sdk.b.b.fl)).booleanValue()) {
                    bVar = new b();
                    a(bVar);
                }
            }
            this.t = true;
        } catch (Throwable th) {
            this.e.e("AppLovinAdView", "Failed to create AdView: " + th.getMessage());
        }
    }

    private void a(Runnable runnable) {
        AppLovinSdkUtils.runOnUiThread(runnable);
    }

    private void b() {
        com.applovin.impl.sdk.n nVar = this.e;
        if (nVar != null) {
            nVar.a("AppLovinAdView", "Destroying...");
        }
        com.applovin.impl.adview.c cVar = this.k;
        if (cVar != null) {
            try {
                ViewParent parent = cVar.getParent();
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(this.k);
                }
                this.k.removeAllViews();
                if (((Boolean) this.c.a(com.applovin.impl.sdk.b.b.fa)).booleanValue()) {
                    try {
                        this.k.loadUrl("about:blank");
                        this.k.onPause();
                        this.k.destroyDrawingCache();
                    } catch (Throwable th) {
                        this.e.b("AppLovinAdView", "Encountered error while cleaning up WebView", th);
                    }
                }
                this.k.destroy();
                this.k = null;
            } catch (Throwable th2) {
                this.e.a("AppLovinAdView", "Unable to destroy ad view", th2);
            }
        }
        this.v = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(View view, AppLovinAdSize appLovinAdSize) {
        int iApplyDimension;
        if (view == null) {
            return;
        }
        DisplayMetrics displayMetrics = view.getResources().getDisplayMetrics();
        int iApplyDimension2 = -1;
        if (appLovinAdSize.getLabel().equals(AppLovinAdSize.INTERSTITIAL.getLabel())) {
            iApplyDimension = -1;
        } else {
            iApplyDimension = appLovinAdSize.getWidth() == -1 ? displayMetrics.widthPixels : (int) TypedValue.applyDimension(1, appLovinAdSize.getWidth(), displayMetrics);
        }
        if (!appLovinAdSize.getLabel().equals(AppLovinAdSize.INTERSTITIAL.getLabel())) {
            iApplyDimension2 = appLovinAdSize.getHeight() == -1 ? displayMetrics.heightPixels : (int) TypedValue.applyDimension(1, appLovinAdSize.getHeight(), displayMetrics);
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        }
        layoutParams.width = iApplyDimension;
        layoutParams.height = iApplyDimension2;
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.addRule(10);
            layoutParams2.addRule(9);
        }
        view.setLayoutParams(layoutParams);
    }

    private void c() {
        a(new Runnable() { // from class: com.applovin.impl.adview.AdViewControllerImpl.1
            @Override // java.lang.Runnable
            public void run() {
                if (AdViewControllerImpl.this.q != null) {
                    AdViewControllerImpl.this.e.a("AppLovinAdView", "Detaching expanded ad: " + AdViewControllerImpl.this.q.a());
                    AdViewControllerImpl adViewControllerImpl = AdViewControllerImpl.this;
                    adViewControllerImpl.r = adViewControllerImpl.q;
                    AdViewControllerImpl.this.q = null;
                    AdViewControllerImpl adViewControllerImpl2 = AdViewControllerImpl.this;
                    adViewControllerImpl2.a(adViewControllerImpl2.f);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        a(new Runnable() { // from class: com.applovin.impl.adview.AdViewControllerImpl.6
            @Override // java.lang.Runnable
            public void run() {
                com.applovin.impl.sdk.ad.a aVarA;
                if (AdViewControllerImpl.this.r == null && AdViewControllerImpl.this.q == null) {
                    return;
                }
                if (AdViewControllerImpl.this.r != null) {
                    aVarA = AdViewControllerImpl.this.r.a();
                    AdViewControllerImpl.this.r.dismiss();
                    AdViewControllerImpl.this.r = null;
                } else {
                    aVarA = AdViewControllerImpl.this.q.a();
                    AdViewControllerImpl.this.q.dismiss();
                    AdViewControllerImpl.this.q = null;
                }
                com.applovin.impl.sdk.utils.h.b(AdViewControllerImpl.this.z, aVarA, (AppLovinAdView) AdViewControllerImpl.this.b, AdViewControllerImpl.this.c);
            }
        });
    }

    private void e() {
        com.applovin.impl.sdk.c.d dVar = this.h;
        if (dVar != null) {
            dVar.c();
            this.h = null;
        }
    }

    void a() {
        if (this.q != null || this.r != null) {
            if (((Boolean) this.c.a(com.applovin.impl.sdk.b.b.cz)).booleanValue()) {
                contractAd();
                return;
            }
            return;
        }
        this.e.a("AppLovinAdView", "Ad: " + this.o + " closed.");
        a(this.n);
        com.applovin.impl.sdk.utils.h.b(this.y, this.o, this.c);
        this.o = null;
    }

    void a(final int i) {
        if (!this.v) {
            a(this.n);
        }
        a(new Runnable() { // from class: com.applovin.impl.adview.AdViewControllerImpl.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (AdViewControllerImpl.this.x != null) {
                        AdViewControllerImpl.this.x.failedToReceiveAd(i);
                    }
                } catch (Throwable th) {
                    AdViewControllerImpl.this.e.c("AppLovinAdView", "Exception while running app load  callback", th);
                }
            }
        });
    }

    void a(final AppLovinAd appLovinAd) {
        if (appLovinAd == null) {
            this.e.d("AppLovinAdView", "No provided when to the view controller");
            a(-1);
            return;
        }
        this.w = true;
        if (this.v) {
            this.s.set(appLovinAd);
            this.e.a("AppLovinAdView", "Ad view has paused when an ad was received, ad saved for later");
        } else {
            renderAd(appLovinAd);
        }
        a(new Runnable() { // from class: com.applovin.impl.adview.AdViewControllerImpl.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (AdViewControllerImpl.this.x != null) {
                        AdViewControllerImpl.this.x.adReceived(appLovinAd);
                    }
                } catch (Throwable th) {
                    AdViewControllerImpl.this.e.e("AppLovinAdView", "Exception while running ad load callback: " + th.getMessage());
                }
            }
        });
    }

    void a(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView, Uri uri) {
        com.applovin.impl.sdk.n nVar;
        String str;
        com.applovin.impl.sdk.utils.h.a(this.A, appLovinAd, this.c);
        if (appLovinAdView == null) {
            nVar = this.e;
            str = "Unable to process ad click - AppLovinAdView destroyed prematurely";
        } else if (appLovinAd instanceof com.applovin.impl.sdk.ad.g) {
            this.d.trackAndLaunchClick(appLovinAd, appLovinAdView, this, uri);
            return;
        } else {
            nVar = this.e;
            str = "Unable to process ad click - EmptyAd is not supported.";
        }
        nVar.d("AppLovinAdView", str);
    }

    @Override // com.applovin.adview.AdViewController
    public void contractAd() {
        a(new Runnable() { // from class: com.applovin.impl.adview.AdViewControllerImpl.3
            @Override // java.lang.Runnable
            public void run() {
                AdViewControllerImpl.this.d();
                if (AdViewControllerImpl.this.b == null || AdViewControllerImpl.this.k == null || AdViewControllerImpl.this.k.getParent() != null) {
                    return;
                }
                AdViewControllerImpl.this.b.addView(AdViewControllerImpl.this.k);
                AdViewControllerImpl.b(AdViewControllerImpl.this.k, AdViewControllerImpl.this.o.getSize());
            }
        });
    }

    @Override // com.applovin.adview.AdViewController
    public void destroy() {
        if (this.k != null && this.q != null) {
            contractAd();
        }
        b();
    }

    public void dismissInterstitialIfRequired() {
        if ((this.a instanceof j) && (this.o instanceof com.applovin.impl.sdk.ad.g)) {
            boolean z = ((com.applovin.impl.sdk.ad.g) this.o).G() == com.applovin.impl.sdk.ad.g.a.DISMISS;
            j jVar = (j) this.a;
            if (z && jVar.getPoststitialWasDisplayed()) {
                jVar.dismiss();
            }
        }
    }

    @Override // com.applovin.adview.AdViewController
    public void expandAd() {
        a(new Runnable() { // from class: com.applovin.impl.adview.AdViewControllerImpl.2
            @Override // java.lang.Runnable
            public void run() {
                if (AdViewControllerImpl.this.q == null && (AdViewControllerImpl.this.o instanceof com.applovin.impl.sdk.ad.a) && AdViewControllerImpl.this.k != null) {
                    com.applovin.impl.sdk.ad.a aVar = (com.applovin.impl.sdk.ad.a) AdViewControllerImpl.this.o;
                    Activity activityA = AdViewControllerImpl.this.a instanceof Activity ? (Activity) AdViewControllerImpl.this.a : com.applovin.impl.sdk.utils.n.a((View) AdViewControllerImpl.this.k, AdViewControllerImpl.this.c);
                    if (activityA == null) {
                        AdViewControllerImpl.this.e.e("AppLovinAdView", "Unable to expand ad. No Activity found.");
                        Uri uriG = aVar.g();
                        if (uriG != null && ((Boolean) AdViewControllerImpl.this.c.a(com.applovin.impl.sdk.b.b.cG)).booleanValue()) {
                            AdViewControllerImpl.this.d.trackAndLaunchClick(aVar, AdViewControllerImpl.this.getParentView(), AdViewControllerImpl.this, uriG);
                            if (AdViewControllerImpl.this.h != null) {
                                AdViewControllerImpl.this.h.b();
                            }
                        }
                        AdViewControllerImpl.this.k.a("javascript:al_onFailedExpand();");
                        return;
                    }
                    if (AdViewControllerImpl.this.b != null) {
                        AdViewControllerImpl.this.b.removeView(AdViewControllerImpl.this.k);
                    }
                    AdViewControllerImpl adViewControllerImpl = AdViewControllerImpl.this;
                    adViewControllerImpl.q = new i(aVar, adViewControllerImpl.k, activityA, AdViewControllerImpl.this.c);
                    AdViewControllerImpl.this.q.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.applovin.impl.adview.AdViewControllerImpl.2.1
                        @Override // android.content.DialogInterface.OnDismissListener
                        public void onDismiss(DialogInterface dialogInterface) {
                            AdViewControllerImpl.this.contractAd();
                        }
                    });
                    AdViewControllerImpl.this.q.show();
                    com.applovin.impl.sdk.utils.h.a(AdViewControllerImpl.this.z, AdViewControllerImpl.this.o, (AppLovinAdView) AdViewControllerImpl.this.b, AdViewControllerImpl.this.c);
                    if (AdViewControllerImpl.this.h != null) {
                        AdViewControllerImpl.this.h.d();
                    }
                }
            }
        });
    }

    public AppLovinAdViewEventListener getAdViewEventListener() {
        return this.z;
    }

    public com.applovin.impl.adview.c getAdWebView() {
        return this.k;
    }

    public AppLovinAd getCurrentAd() {
        return this.o;
    }

    public AppLovinAdView getParentView() {
        return (AppLovinAdView) this.b;
    }

    public com.applovin.impl.sdk.h getSdk() {
        return this.c;
    }

    @Override // com.applovin.adview.AdViewController
    public AppLovinAdSize getSize() {
        return this.f;
    }

    @Override // com.applovin.adview.AdViewController
    public String getZoneId() {
        return this.g;
    }

    @Override // com.applovin.adview.AdViewController
    public void initializeAdView(AppLovinAdView appLovinAdView, Context context, AppLovinAdSize appLovinAdSize, String str, AppLovinSdk appLovinSdk, AttributeSet attributeSet) {
        if (appLovinAdView == null) {
            throw new IllegalArgumentException("No parent view specified");
        }
        if (context == null) {
            Log.e("AppLovinAdView", "Unable to build AppLovinAdView: no context provided. Please use a different constructor for this view.");
            return;
        }
        if (appLovinAdSize == null && (appLovinAdSize = com.applovin.impl.sdk.utils.b.a(attributeSet)) == null) {
            appLovinAdSize = AppLovinAdSize.BANNER;
        }
        AppLovinAdSize appLovinAdSize2 = appLovinAdSize;
        if (appLovinSdk == null) {
            appLovinSdk = AppLovinSdk.getInstance(context);
        }
        if (appLovinSdk == null || appLovinSdk.hasCriticalErrors()) {
            return;
        }
        a(appLovinAdView, com.applovin.impl.sdk.utils.n.a(appLovinSdk), appLovinAdSize2, str, context);
        if (com.applovin.impl.sdk.utils.b.b(attributeSet)) {
            loadNextAd();
        }
    }

    @Override // com.applovin.adview.AdViewController
    public boolean isAdReadyToDisplay() {
        return !TextUtils.isEmpty(this.g) ? this.d.hasPreloadedAdForZoneId(this.g) : this.d.hasPreloadedAd(this.f);
    }

    @Override // com.applovin.adview.AdViewController
    public boolean isAutoDestroy() {
        return this.u;
    }

    @Override // com.applovin.adview.AdViewController
    public void loadNextAd() {
        if (this.c == null || this.j == null || this.a == null || !this.t) {
            Log.i("AppLovinAdView", "Unable to load next ad: AppLovinAdView is not initialized.");
        } else {
            this.d.loadNextAd(this.g, this.f, this.j);
        }
    }

    public void onAdHtmlLoaded(WebView webView) {
        if (this.o instanceof com.applovin.impl.sdk.ad.g) {
            webView.setVisibility(0);
            try {
                if (this.o == this.p || this.y == null) {
                    return;
                }
                this.p = this.o;
                com.applovin.impl.sdk.utils.h.a(this.y, this.o, this.c);
            } catch (Throwable th) {
                this.e.c("AppLovinAdView", "Exception while notifying ad display listener", th);
            }
        }
    }

    @Override // com.applovin.adview.AdViewController
    public void onDetachedFromWindow() {
        if (this.t) {
            if (this.o != this.l) {
                com.applovin.impl.sdk.utils.h.b(this.y, this.o, this.c);
            }
            if (this.k == null || this.q == null) {
                this.e.a("AppLovinAdView", "onDetachedFromWindowCalled without an expanded ad present");
            } else {
                this.e.a("AppLovinAdView", "onDetachedFromWindowCalled with expanded ad present");
                if (((Boolean) this.c.a(com.applovin.impl.sdk.b.b.cy)).booleanValue()) {
                    contractAd();
                } else {
                    c();
                }
            }
            if (this.u) {
                b();
            }
        }
    }

    @Override // com.applovin.adview.AdViewController
    public void onVisibilityChanged(int i) {
        if (this.t && this.u) {
            if (i == 8 || i == 4) {
                pause();
            } else if (i == 0) {
                resume();
            }
        }
    }

    @Override // com.applovin.adview.AdViewController
    public void pause() {
        if (this.t) {
            AppLovinAd appLovinAd = this.o;
            renderAd(this.l);
            if (appLovinAd != null) {
                this.s.set(appLovinAd);
            }
            this.v = true;
        }
    }

    @Override // com.applovin.adview.AdViewController
    public void renderAd(AppLovinAd appLovinAd) {
        renderAd(appLovinAd, null);
    }

    @Override // com.applovin.adview.AdViewController
    public void renderAd(AppLovinAd appLovinAd, String str) {
        com.applovin.impl.sdk.n nVar;
        String str2;
        if (appLovinAd == null) {
            throw new IllegalArgumentException("No ad specified");
        }
        com.applovin.impl.sdk.utils.n.c(appLovinAd, this.c);
        if (!this.t) {
            Log.i("AppLovinAdView", "Unable to render ad: AppLovinAdView is not initialized.");
            return;
        }
        AppLovinAd appLovinAdB = com.applovin.impl.sdk.utils.n.b(appLovinAd, this.c);
        if (appLovinAdB == null || appLovinAdB == this.o) {
            if (appLovinAdB == null) {
                nVar = this.e;
                str2 = "Unable to render ad. Ad is null. Internal inconsistency error.";
            } else {
                nVar = this.e;
                str2 = "Ad #" + appLovinAdB.getAdIdNumber() + " is already showing, ignoring";
            }
            nVar.c("AppLovinAdView", str2);
            return;
        }
        this.e.a("AppLovinAdView", "Rendering ad #" + appLovinAdB.getAdIdNumber() + " (" + appLovinAdB.getSize() + ")");
        if (!(this.o instanceof com.applovin.impl.sdk.ad.i)) {
            com.applovin.impl.sdk.utils.h.b(this.y, this.o, this.c);
            if (!(appLovinAdB instanceof com.applovin.impl.sdk.ad.i) && appLovinAdB.getSize() != AppLovinAdSize.INTERSTITIAL) {
                e();
            }
        }
        this.s.set(null);
        this.p = null;
        this.o = appLovinAdB;
        if ((appLovinAd instanceof com.applovin.impl.sdk.ad.g) && !this.v && (this.f == AppLovinAdSize.BANNER || this.f == AppLovinAdSize.MREC || this.f == AppLovinAdSize.LEADER)) {
            this.c.p().trackImpression((com.applovin.impl.sdk.ad.g) appLovinAd);
        }
        boolean z = appLovinAdB instanceof com.applovin.impl.sdk.ad.i;
        if (!z && this.q != null) {
            if (((Boolean) this.c.a(com.applovin.impl.sdk.b.b.cx)).booleanValue()) {
                d();
                this.e.a("AppLovinAdView", "Fade out the old ad scheduled");
            } else {
                c();
            }
        }
        if (!z || (this.q == null && this.r == null)) {
            a(this.m);
        } else {
            this.e.a("AppLovinAdView", "Ignoring empty ad render with expanded ad");
        }
    }

    @Override // com.applovin.adview.AdViewController
    public void resume() {
        if (this.t) {
            AppLovinAd andSet = this.s.getAndSet(null);
            if (andSet != null) {
                renderAd(andSet);
            }
            this.v = false;
        }
    }

    @Override // com.applovin.adview.AdViewController
    public void setAdClickListener(AppLovinAdClickListener appLovinAdClickListener) {
        this.A = appLovinAdClickListener;
    }

    @Override // com.applovin.adview.AdViewController
    public void setAdDisplayListener(AppLovinAdDisplayListener appLovinAdDisplayListener) {
        this.y = appLovinAdDisplayListener;
    }

    @Override // com.applovin.adview.AdViewController
    public void setAdLoadListener(AppLovinAdLoadListener appLovinAdLoadListener) {
        this.x = appLovinAdLoadListener;
    }

    @Override // com.applovin.adview.AdViewController
    public void setAdVideoPlaybackListener(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener) {
    }

    @Override // com.applovin.adview.AdViewController
    public void setAdViewEventListener(AppLovinAdViewEventListener appLovinAdViewEventListener) {
        this.z = appLovinAdViewEventListener;
    }

    @Override // com.applovin.adview.AdViewController
    public void setAutoDestroy(boolean z) {
        this.u = z;
    }

    public void setStatsManagerHelper(com.applovin.impl.sdk.c.d dVar) {
        com.applovin.impl.adview.c cVar = this.k;
        if (cVar != null) {
            cVar.a(dVar);
        }
    }
}
