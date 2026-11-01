package com.moat.analytics.mobile.vng;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import java.lang.ref.WeakReference;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
class n extends MoatFactory {
    n() throws m {
        if (a()) {
            return;
        }
        p.a(3, "Factory", this, "Failed to initialize MoatFactory. Please check that you've initialized the Moat SDK correctly.");
        p.a("[ERROR] ", "Failed to initialize MoatFactory, SDK was not started");
        throw new m();
    }

    private NativeDisplayTracker a(View view, final Map<String, String> map) {
        com.moat.analytics.mobile.vng.a.a.a.a(view);
        com.moat.analytics.mobile.vng.a.a.a.a(map);
        final WeakReference weakReference = new WeakReference(view);
        return (NativeDisplayTracker) x.a(new x.a<NativeDisplayTracker>() { // from class: com.moat.analytics.mobile.vng.n.3
            @Override // com.moat.analytics.mobile.vng.x.a
            public com.moat.analytics.mobile.vng.a.b.a<NativeDisplayTracker> a() {
                View view2 = (View) weakReference.get();
                if (view2 == null) {
                    p.a(3, "Factory", this, "Target view is null. Not creating NativeDisplayTracker.");
                    p.a("[ERROR] ", "NativeDisplayTracker creation failed, subject view is null");
                    return com.moat.analytics.mobile.vng.a.b.a.a();
                }
                Map map2 = map;
                if (map2 == null || map2.isEmpty()) {
                    p.a(3, "Factory", this, "adIds is null or empty. NativeDisplayTracker initialization failed.");
                    p.a("[ERROR] ", "NativeDisplayTracker creation failed, adIds is null or empty");
                    return com.moat.analytics.mobile.vng.a.b.a.a();
                }
                p.a(3, "Factory", this, "Creating NativeDisplayTracker for " + view2.getClass().getSimpleName() + "@" + view2.hashCode());
                p.a("[INFO] ", "Attempting to create NativeDisplayTracker for " + view2.getClass().getSimpleName() + "@" + view2.hashCode());
                return com.moat.analytics.mobile.vng.a.b.a.a(new t(view2, map));
            }
        }, NativeDisplayTracker.class);
    }

    private NativeVideoTracker a(final String str) {
        return (NativeVideoTracker) x.a(new x.a<NativeVideoTracker>() { // from class: com.moat.analytics.mobile.vng.n.4
            @Override // com.moat.analytics.mobile.vng.x.a
            public com.moat.analytics.mobile.vng.a.b.a<NativeVideoTracker> a() {
                String str2 = str;
                if (str2 == null || str2.isEmpty()) {
                    p.a(3, "Factory", this, "partnerCode is null or empty. NativeVideoTracker initialization failed.");
                    p.a("[ERROR] ", "NativeDisplayTracker creation failed, partnerCode is null or empty");
                    return com.moat.analytics.mobile.vng.a.b.a.a();
                }
                p.a(3, "Factory", this, "Creating NativeVideo tracker.");
                p.a("[INFO] ", "Attempting to create NativeVideoTracker");
                return com.moat.analytics.mobile.vng.a.b.a.a(new u(str));
            }
        }, NativeVideoTracker.class);
    }

    private WebAdTracker a(ViewGroup viewGroup) {
        com.moat.analytics.mobile.vng.a.a.a.a(viewGroup);
        final WeakReference weakReference = new WeakReference(viewGroup);
        return (WebAdTracker) x.a(new x.a<WebAdTracker>() { // from class: com.moat.analytics.mobile.vng.n.2
            @Override // com.moat.analytics.mobile.vng.x.a
            public com.moat.analytics.mobile.vng.a.b.a<WebAdTracker> a() {
                ViewGroup viewGroup2 = (ViewGroup) weakReference.get();
                if (viewGroup2 == null) {
                    p.a(3, "Factory", this, "Target ViewGroup is null. Not creating WebAdTracker.");
                    p.a("[ERROR] ", "WebAdTracker not created, adContainer ViewGroup is null");
                    return com.moat.analytics.mobile.vng.a.b.a.a();
                }
                p.a(3, "Factory", this, "Creating WebAdTracker for " + viewGroup2.getClass().getSimpleName() + "@" + viewGroup2.hashCode());
                p.a("[INFO] ", "Attempting to create WebAdTracker for " + viewGroup2.getClass().getSimpleName() + "@" + viewGroup2.hashCode());
                com.moat.analytics.mobile.vng.a.b.a<WebView> aVarA = ab.a(viewGroup2);
                boolean zC = aVarA.c();
                StringBuilder sb = new StringBuilder();
                sb.append("WebView ");
                sb.append(zC ? "" : "not ");
                sb.append("found inside of ad container.");
                p.a(3, "Factory", this, sb.toString());
                if (!zC) {
                    p.a("[ERROR] ", "WebAdTracker not created, no WebView to track inside of ad container");
                }
                return com.moat.analytics.mobile.vng.a.b.a.a(new aa(aVarA.c(null)));
            }
        }, WebAdTracker.class);
    }

    private WebAdTracker a(WebView webView) {
        com.moat.analytics.mobile.vng.a.a.a.a(webView);
        final WeakReference weakReference = new WeakReference(webView);
        return (WebAdTracker) x.a(new x.a<WebAdTracker>() { // from class: com.moat.analytics.mobile.vng.n.1
            @Override // com.moat.analytics.mobile.vng.x.a
            public com.moat.analytics.mobile.vng.a.b.a<WebAdTracker> a() {
                WebView webView2 = (WebView) weakReference.get();
                if (webView2 == null) {
                    p.a(3, "Factory", this, "Target ViewGroup is null. Not creating WebAdTracker.");
                    p.a("[ERROR] ", "WebAdTracker not created, webView is null");
                    return com.moat.analytics.mobile.vng.a.b.a.a();
                }
                p.a(3, "Factory", this, "Creating WebAdTracker for " + webView2.getClass().getSimpleName() + "@" + webView2.hashCode());
                p.a("[INFO] ", "Attempting to create WebAdTracker for " + webView2.getClass().getSimpleName() + "@" + webView2.hashCode());
                return com.moat.analytics.mobile.vng.a.b.a.a(new aa(webView2));
            }
        }, WebAdTracker.class);
    }

    private <T> T a(MoatPlugin<T> moatPlugin) {
        return moatPlugin.a();
    }

    private boolean a() {
        return ((k) k.getInstance()).a();
    }

    @Override // com.moat.analytics.mobile.vng.MoatFactory
    public <T> T createCustomTracker(MoatPlugin<T> moatPlugin) {
        try {
            return (T) a(moatPlugin);
        } catch (Exception e) {
            m.a(e);
            return moatPlugin.b();
        }
    }

    @Override // com.moat.analytics.mobile.vng.MoatFactory
    public NativeDisplayTracker createNativeDisplayTracker(View view, Map<String, String> map) {
        try {
            return a(view, map);
        } catch (Exception e) {
            m.a(e);
            return new v.c();
        }
    }

    @Override // com.moat.analytics.mobile.vng.MoatFactory
    public NativeVideoTracker createNativeVideoTracker(String str) {
        try {
            return a(str);
        } catch (Exception e) {
            m.a(e);
            return new v.d();
        }
    }

    @Override // com.moat.analytics.mobile.vng.MoatFactory
    public WebAdTracker createWebAdTracker(ViewGroup viewGroup) {
        try {
            return a(viewGroup);
        } catch (Exception e) {
            m.a(e);
            return new v.e();
        }
    }

    @Override // com.moat.analytics.mobile.vng.MoatFactory
    public WebAdTracker createWebAdTracker(WebView webView) {
        try {
            return a(webView);
        } catch (Exception e) {
            m.a(e);
            return new v.e();
        }
    }
}
