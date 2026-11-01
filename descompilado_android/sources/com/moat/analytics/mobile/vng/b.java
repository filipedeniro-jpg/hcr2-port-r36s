package com.moat.analytics.mobile.vng;

import android.app.Activity;
import android.view.View;
import android.webkit.WebView;
import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import com.facebook.internal.AnalyticsEvents;
import com.ironsource.sdk.constants.Constants;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
abstract class b {
    j a;
    final String b;
    final boolean c;
    boolean d;
    boolean e;
    private WeakReference<View> f;
    private WeakReference<WebView> g;
    private final z h;
    private final boolean i;

    b(@Nullable View view, boolean z, boolean z2) {
        String str;
        p.a(3, "BaseTracker", this, "Initializing.");
        if (z) {
            str = "m" + hashCode();
        } else {
            str = "";
        }
        this.b = str;
        this.f = new WeakReference<>(view);
        this.i = z;
        this.c = z2;
        this.d = false;
        this.e = false;
        this.h = new z();
    }

    private void g() {
        String string;
        com.moat.analytics.mobile.vng.a.a.a.a(this.g);
        p.a(3, "BaseTracker", this, "Attempting bridge installation.");
        if (this.g.get() != null) {
            if (!this.i && !this.c) {
                this.a = new j(this.g.get(), j.a.WEBVIEW);
            }
            boolean z = this.a.a;
            StringBuilder sb = new StringBuilder();
            sb.append("Bridge ");
            sb.append(z ? "" : "not ");
            sb.append("installed.");
            string = sb.toString();
        } else {
            this.a = null;
            string = "Bridge not installed, WebView is null.";
        }
        p.a(3, "BaseTracker", this, string);
    }

    abstract String a();

    void a(WebView webView) {
        if (webView != null) {
            this.g = new WeakReference<>(webView);
            if (this.a == null) {
                g();
            }
            j jVar = this.a;
            if (jVar == null || !jVar.a) {
                return;
            }
            this.a.a(this);
        }
    }

    void a(j jVar) {
        this.a = jVar;
    }

    boolean b() {
        p.a(3, "BaseTracker", this, "Attempting to start impression.");
        if (this.e) {
            p.a(3, "BaseTracker", this, "startTracking failed, tracker already started");
            p.a("[INFO] ", a() + " already started");
            return false;
        }
        boolean zB = this.a.b(this);
        StringBuilder sb = new StringBuilder();
        sb.append("Impression ");
        sb.append(zB ? "" : "not ");
        sb.append("started.");
        p.a(3, "BaseTracker", this, sb.toString());
        if (!zB) {
            return zB;
        }
        this.d = true;
        this.e = true;
        return zB;
    }

    boolean c() {
        p.a(3, "BaseTracker", this, "Attempting to stop impression.");
        this.d = false;
        boolean zC = this.a.c(this);
        StringBuilder sb = new StringBuilder();
        sb.append("Impression tracking ");
        sb.append(zC ? "" : "not ");
        sb.append("stopped.");
        p.a(3, "BaseTracker", this, sb.toString());
        return zC;
    }

    @CallSuper
    public void changeTargetView(View view) {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("changing view to ");
        if (view != null) {
            str = view.getClass().getSimpleName() + "@" + view.hashCode();
        } else {
            str = "null";
        }
        sb.append(str);
        p.a(3, "BaseTracker", this, sb.toString());
        this.f = new WeakReference<>(view);
    }

    View d() {
        return this.f.get();
    }

    String e() {
        if (d() == null) {
            return "";
        }
        return d().getClass().getSimpleName() + "@" + d().hashCode();
    }

    String f() {
        this.h.a(this.b, d());
        return this.h.a;
    }

    @Deprecated
    public void setActivity(Activity activity) {
    }

    public void startTracking() {
        boolean zB;
        try {
            p.a(3, "BaseTracker", this, "In startTracking method.");
            zB = b();
        } catch (Exception e) {
            m.a(e);
            zB = false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Attempt to start tracking ad impression was ");
        sb.append(zB ? "" : "un");
        sb.append("successful.");
        p.a(3, "BaseTracker", this, sb.toString());
        String str = zB ? "[SUCCESS] " : "[ERROR] ";
        StringBuilder sb2 = new StringBuilder();
        sb2.append(a());
        sb2.append(" startTracking ");
        sb2.append(zB ? AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED : Constants.ParametersKeys.FAILED);
        sb2.append(" for ");
        sb2.append(e());
        p.a(str, sb2.toString());
    }

    public void stopTracking() {
        boolean zC;
        try {
            p.a(3, "BaseTracker", this, "In stopTracking method.");
            zC = c();
        } catch (Exception e) {
            m.a(e);
            zC = false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Attempt to stop tracking ad impression was ");
        sb.append(zC ? "" : "un");
        sb.append("successful.");
        p.a(3, "BaseTracker", this, sb.toString());
        String str = zC ? "[SUCCESS] " : "[ERROR] ";
        StringBuilder sb2 = new StringBuilder();
        sb2.append(a());
        sb2.append(" stopTracking ");
        sb2.append(zC ? AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED : Constants.ParametersKeys.FAILED);
        sb2.append(" for ");
        sb2.append(e());
        p.a(str, sb2.toString());
    }
}
