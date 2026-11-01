package com.moat.analytics.mobile.inm;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
class n extends MoatFactory {
    n() throws m {
        if (a()) {
            return;
        }
        p.a("[ERROR] ", 3, "Factory", this, "Failed to initialize MoatFactory, SDK was not started");
        throw new m("Failed to initialize MoatFactory");
    }

    private NativeDisplayTracker a(View view, final Map<String, String> map) {
        final WeakReference weakReference = new WeakReference(view);
        return (NativeDisplayTracker) x.a(new x.a<NativeDisplayTracker>() { // from class: com.moat.analytics.mobile.inm.n.3
            @Override // com.moat.analytics.mobile.inm.x.a
            public com.moat.analytics.mobile.inm.a.b.a<NativeDisplayTracker> a() {
                View view2 = (View) weakReference.get();
                p.a("[INFO] ", 3, "Factory", this, "Attempting to create NativeDisplayTracker for " + p.a(view2));
                return com.moat.analytics.mobile.inm.a.b.a.a(new t(view2, map));
            }
        }, NativeDisplayTracker.class);
    }

    private NativeVideoTracker a(final String str) {
        return (NativeVideoTracker) x.a(new x.a<NativeVideoTracker>() { // from class: com.moat.analytics.mobile.inm.n.4
            @Override // com.moat.analytics.mobile.inm.x.a
            public com.moat.analytics.mobile.inm.a.b.a<NativeVideoTracker> a() {
                p.a("[INFO] ", 3, "Factory", this, "Attempting to create NativeVideoTracker");
                return com.moat.analytics.mobile.inm.a.b.a.a(new u(str));
            }
        }, NativeVideoTracker.class);
    }

    private WebAdTracker a(ViewGroup viewGroup) {
        final WeakReference weakReference = new WeakReference(viewGroup);
        return (WebAdTracker) x.a(new x.a<WebAdTracker>() { // from class: com.moat.analytics.mobile.inm.n.2
            @Override // com.moat.analytics.mobile.inm.x.a
            public com.moat.analytics.mobile.inm.a.b.a<WebAdTracker> a() {
                ViewGroup viewGroup2 = (ViewGroup) weakReference.get();
                p.a("[INFO] ", 3, "Factory", this, "Attempting to create WebAdTracker for adContainer " + p.a(viewGroup2));
                return com.moat.analytics.mobile.inm.a.b.a.a(new aa(viewGroup2));
            }
        }, WebAdTracker.class);
    }

    private WebAdTracker a(WebView webView) {
        final WeakReference weakReference = new WeakReference(webView);
        return (WebAdTracker) x.a(new x.a<WebAdTracker>() { // from class: com.moat.analytics.mobile.inm.n.1
            @Override // com.moat.analytics.mobile.inm.x.a
            public com.moat.analytics.mobile.inm.a.b.a<WebAdTracker> a() {
                WebView webView2 = (WebView) weakReference.get();
                p.a("[INFO] ", 3, "Factory", this, "Attempting to create WebAdTracker for " + p.a(webView2));
                return com.moat.analytics.mobile.inm.a.b.a.a(new aa(webView2));
            }
        }, WebAdTracker.class);
    }

    private <T> T a(MoatPlugin<T> moatPlugin) {
        return moatPlugin.a();
    }

    private boolean a() {
        return ((k) k.getInstance()).a();
    }

    @Override // com.moat.analytics.mobile.inm.MoatFactory
    public <T> T createCustomTracker(MoatPlugin<T> moatPlugin) {
        try {
            return (T) a(moatPlugin);
        } catch (Exception e) {
            m.a(e);
            return moatPlugin.b();
        }
    }

    @Override // com.moat.analytics.mobile.inm.MoatFactory
    public NativeDisplayTracker createNativeDisplayTracker(@NonNull View view, @NonNull Map<String, String> map) {
        try {
            return a(view, map);
        } catch (Exception e) {
            m.a(e);
            return new v.c();
        }
    }

    @Override // com.moat.analytics.mobile.inm.MoatFactory
    public NativeVideoTracker createNativeVideoTracker(String str) {
        try {
            return a(str);
        } catch (Exception e) {
            m.a(e);
            return new v.d();
        }
    }

    @Override // com.moat.analytics.mobile.inm.MoatFactory
    public WebAdTracker createWebAdTracker(@NonNull ViewGroup viewGroup) {
        try {
            return a(viewGroup);
        } catch (Exception e) {
            m.a(e);
            return new v.e();
        }
    }

    @Override // com.moat.analytics.mobile.inm.MoatFactory
    public WebAdTracker createWebAdTracker(@NonNull WebView webView) {
        try {
            return a(webView);
        } catch (Exception e) {
            m.a(e);
            return new v.e();
        }
    }
}
