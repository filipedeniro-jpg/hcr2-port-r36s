package com.moat.analytics.mobile.cha;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import com.moat.analytics.mobile.cha.base.functional.Optional;
import java.lang.ref.WeakReference;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class k extends MoatFactory {
    k() throws o {
        if (((f) f.getInstance()).m310()) {
            return;
        }
        String str = "Failed to initialize MoatFactory, SDK was not started";
        a.m273(3, "Factory", this, str);
        a.m270("[ERROR] ", str);
        throw new o("Failed to initialize MoatFactory");
    }

    @Override // com.moat.analytics.mobile.cha.MoatFactory
    public final WebAdTracker createWebAdTracker(@NonNull WebView webView) {
        try {
            final WeakReference weakReference = new WeakReference(webView);
            return (WebAdTracker) p.m392(new p.c<WebAdTracker>() { // from class: com.moat.analytics.mobile.cha.k.5
                @Override // com.moat.analytics.mobile.cha.p.c
                /* JADX INFO: renamed from: ˋ */
                public final Optional<WebAdTracker> mo269() {
                    WebView webView2 = (WebView) weakReference.get();
                    String str = "Attempting to create WebAdTracker for " + a.m272(webView2);
                    a.m273(3, "Factory", this, str);
                    a.m270("[INFO] ", str);
                    return Optional.of(new v(webView2));
                }
            }, WebAdTracker.class);
        } catch (Exception e) {
            o.m389(e);
            return new NoOp.e();
        }
    }

    @Override // com.moat.analytics.mobile.cha.MoatFactory
    public final WebAdTracker createWebAdTracker(@NonNull ViewGroup viewGroup) {
        try {
            final WeakReference weakReference = new WeakReference(viewGroup);
            return (WebAdTracker) p.m392(new p.c<WebAdTracker>() { // from class: com.moat.analytics.mobile.cha.k.4
                @Override // com.moat.analytics.mobile.cha.p.c
                /* JADX INFO: renamed from: ˋ */
                public final Optional<WebAdTracker> mo269() throws o {
                    ViewGroup viewGroup2 = (ViewGroup) weakReference.get();
                    String str = "Attempting to create WebAdTracker for adContainer " + a.m272(viewGroup2);
                    a.m273(3, "Factory", this, str);
                    a.m270("[INFO] ", str);
                    return Optional.of(new v(viewGroup2));
                }
            }, WebAdTracker.class);
        } catch (Exception e) {
            o.m389(e);
            return new NoOp.e();
        }
    }

    @Override // com.moat.analytics.mobile.cha.MoatFactory
    public final NativeDisplayTracker createNativeDisplayTracker(@NonNull View view, @NonNull final Map<String, String> map) {
        try {
            final WeakReference weakReference = new WeakReference(view);
            return (NativeDisplayTracker) p.m392(new p.c<NativeDisplayTracker>() { // from class: com.moat.analytics.mobile.cha.k.1
                @Override // com.moat.analytics.mobile.cha.p.c
                /* JADX INFO: renamed from: ˋ */
                public final Optional<NativeDisplayTracker> mo269() {
                    View view2 = (View) weakReference.get();
                    String str = "Attempting to create NativeDisplayTracker for " + a.m272(view2);
                    a.m273(3, "Factory", this, str);
                    a.m270("[INFO] ", str);
                    return Optional.of(new q(view2, map));
                }
            }, NativeDisplayTracker.class);
        } catch (Exception e) {
            o.m389(e);
            return new NoOp.c();
        }
    }

    @Override // com.moat.analytics.mobile.cha.MoatFactory
    public final NativeVideoTracker createNativeVideoTracker(final String str) {
        try {
            return (NativeVideoTracker) p.m392(new p.c<NativeVideoTracker>() { // from class: com.moat.analytics.mobile.cha.k.3
                @Override // com.moat.analytics.mobile.cha.p.c
                /* JADX INFO: renamed from: ˋ */
                public final Optional<NativeVideoTracker> mo269() {
                    a.m273(3, "Factory", this, "Attempting to create NativeVideoTracker");
                    a.m270("[INFO] ", "Attempting to create NativeVideoTracker");
                    return Optional.of(new s(str));
                }
            }, NativeVideoTracker.class);
        } catch (Exception e) {
            o.m389(e);
            return new NoOp.b();
        }
    }

    @Override // com.moat.analytics.mobile.cha.MoatFactory
    public final <T> T createCustomTracker(l<T> lVar) {
        try {
            return lVar.create();
        } catch (Exception e) {
            o.m389(e);
            return lVar.createNoOp();
        }
    }
}
