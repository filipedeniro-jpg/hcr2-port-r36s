package com.moat.analytics.mobile.inm;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import com.facebook.internal.AnalyticsEvents;
import com.ironsource.sdk.constants.Constants;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
abstract class b {
    m a = null;
    WeakReference<WebView> b;
    j c;
    TrackerListener d;
    final String e;
    final boolean f;
    private WeakReference<View> g;
    private final z h;
    private final boolean i;
    private boolean j;
    private boolean k;

    b(@Nullable View view, boolean z, boolean z2) {
        String str;
        p.a(3, "BaseTracker", this, "Initializing.");
        if (z) {
            str = "m" + hashCode();
        } else {
            str = "";
        }
        this.e = str;
        this.g = new WeakReference<>(view);
        this.i = z;
        this.f = z2;
        this.j = false;
        this.k = false;
        this.h = new z();
    }

    private void i() {
        String str;
        p.a(3, "BaseTracker", this, "Attempting bridge installation.");
        if (this.b.get() != null) {
            this.c = new j(this.b.get(), j.a.WEBVIEW);
            str = "Bridge installed.";
        } else {
            this.c = null;
            str = "Bridge not installed, WebView is null.";
        }
        p.a(3, "BaseTracker", this, str);
    }

    private void j() throws m {
        if (this.j) {
            throw new m("Tracker already started");
        }
    }

    private void k() throws m {
        if (this.k) {
            throw new m("Tracker already stopped");
        }
    }

    private boolean l() {
        return this.i || this.f;
    }

    abstract String a();

    void a(WebView webView) {
        if (webView != null) {
            this.b = new WeakReference<>(webView);
            if (this.c == null && !l()) {
                i();
            }
            j jVar = this.c;
            if (jVar != null) {
                jVar.a(this);
            }
        }
    }

    void a(j jVar) {
        this.c = jVar;
    }

    void a(String str, Exception exc) {
        try {
            m.a(exc);
            String strA = m.a(str, exc);
            if (this.d != null) {
                this.d.onTrackingFailedToStart(strA);
            }
            p.a(3, "BaseTracker", this, strA);
            p.a("[ERROR] ", a() + " " + strA);
        } catch (Exception unused) {
        }
    }

    @CallSuper
    void a(List<String> list) throws m {
        if (f() == null && !this.f) {
            list.add("Tracker's target view is null");
        }
        if (!list.isEmpty()) {
            throw new m(TextUtils.join(" and ", list));
        }
    }

    @CallSuper
    void b() throws m {
        p.a(3, "BaseTracker", this, "Attempting to start impression.");
        c();
        d();
        a(new ArrayList());
        j jVar = this.c;
        if (jVar == null) {
            p.a(3, "BaseTracker", this, "Bridge is null, won't start tracking");
            throw new m("Bridge is null");
        }
        jVar.b(this);
        this.j = true;
        p.a(3, "BaseTracker", this, "Impression started.");
    }

    void c() throws m {
        if (this.a == null) {
            return;
        }
        throw new m("Tracker initialization failed: " + this.a.getMessage());
    }

    @CallSuper
    public void changeTargetView(View view) {
        p.a(3, "BaseTracker", this, "changing view to " + p.a(view));
        this.g = new WeakReference<>(view);
    }

    void d() throws m {
        j();
        k();
    }

    boolean e() {
        return this.j && !this.k;
    }

    View f() {
        return this.g.get();
    }

    String g() {
        return p.a(f());
    }

    String h() {
        this.h.a(this.e, f());
        return this.h.a;
    }

    public void removeListener() {
        this.d = null;
    }

    @Deprecated
    public void setActivity(Activity activity) {
    }

    public void setListener(TrackerListener trackerListener) {
        this.d = trackerListener;
    }

    public void startTracking() {
        try {
            p.a(3, "BaseTracker", this, "In startTracking method.");
            b();
            if (this.d != null) {
                this.d.onTrackingStarted("Tracking started on " + g());
            }
            String str = "startTracking succeeded for " + g();
            p.a(3, "BaseTracker", this, str);
            p.a("[SUCCESS] ", a() + " " + str);
        } catch (Exception e) {
            a("startTracking", e);
        }
    }

    @CallSuper
    public void stopTracking() {
        boolean z = false;
        try {
            p.a(3, "BaseTracker", this, "In stopTracking method.");
            this.k = true;
            if (this.c != null) {
                this.c.c(this);
                z = true;
            }
        } catch (Exception e) {
            m.a(e);
        }
        StringBuilder sb = new StringBuilder("Attempt to stop tracking ad impression was ");
        sb.append(z ? "" : "un");
        sb.append("successful.");
        p.a(3, "BaseTracker", this, sb.toString());
        String str = z ? "[SUCCESS] " : "[ERROR] ";
        StringBuilder sb2 = new StringBuilder();
        sb2.append(a());
        sb2.append(" stopTracking ");
        sb2.append(z ? AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED : Constants.ParametersKeys.FAILED);
        sb2.append(" for ");
        sb2.append(g());
        p.a(str, sb2.toString());
        TrackerListener trackerListener = this.d;
        if (trackerListener != null) {
            trackerListener.onTrackingStopped("");
            this.d = null;
        }
    }
}
