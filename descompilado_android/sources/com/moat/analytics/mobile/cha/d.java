package com.moat.analytics.mobile.cha;

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
abstract class d {

    /* JADX INFO: renamed from: ʻ, reason: contains not printable characters */
    private WeakReference<View> f300;

    /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
    private final boolean f301;

    /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
    final boolean f302;

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    TrackerListener f303;

    /* JADX INFO: renamed from: ˊॱ, reason: contains not printable characters */
    private boolean f304;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    final String f305;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    j f306;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    WeakReference<WebView> f307;

    /* JADX INFO: renamed from: ͺ, reason: contains not printable characters */
    private boolean f308;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    o f309 = null;

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    private final u f310;

    @Deprecated
    public void setActivity(Activity activity) {
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    abstract String mo303();

    d(@Nullable View view, boolean z, boolean z2) {
        String str;
        a.m273(3, "BaseTracker", this, "Initializing.");
        if (z) {
            str = "m" + hashCode();
        } else {
            str = "";
        }
        this.f305 = str;
        this.f300 = new WeakReference<>(view);
        this.f301 = z;
        this.f302 = z2;
        this.f304 = false;
        this.f308 = false;
        this.f310 = new u();
    }

    public void setListener(TrackerListener trackerListener) {
        this.f303 = trackerListener;
    }

    public void removeListener() {
        this.f303 = null;
    }

    public void startTracking() {
        try {
            a.m273(3, "BaseTracker", this, "In startTracking method.");
            mo286();
            if (this.f303 != null) {
                this.f303.onTrackingStarted("Tracking started on " + a.m272(this.f300.get()));
            }
            String str = "startTracking succeeded for " + a.m272(this.f300.get());
            a.m273(3, "BaseTracker", this, str);
            a.m270("[SUCCESS] ", mo303() + " " + str);
        } catch (Exception e) {
            m307("startTracking", e);
        }
    }

    @CallSuper
    public void stopTracking() {
        boolean z = false;
        try {
            a.m273(3, "BaseTracker", this, "In stopTracking method.");
            this.f308 = true;
            if (this.f306 != null) {
                this.f306.m359(this);
                z = true;
            }
        } catch (Exception e) {
            o.m389(e);
        }
        StringBuilder sb = new StringBuilder("Attempt to stop tracking ad impression was ");
        sb.append(z ? "" : "un");
        sb.append("successful.");
        a.m273(3, "BaseTracker", this, sb.toString());
        String str = z ? "[SUCCESS] " : "[ERROR] ";
        StringBuilder sb2 = new StringBuilder();
        sb2.append(mo303());
        sb2.append(" stopTracking ");
        sb2.append(z ? AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED : Constants.ParametersKeys.FAILED);
        sb2.append(" for ");
        sb2.append(a.m272(this.f300.get()));
        a.m270(str, sb2.toString());
        TrackerListener trackerListener = this.f303;
        if (trackerListener != null) {
            trackerListener.onTrackingStopped("");
            this.f303 = null;
        }
    }

    @CallSuper
    public void changeTargetView(View view) {
        a.m273(3, "BaseTracker", this, "changing view to " + a.m272(view));
        this.f300 = new WeakReference<>(view);
    }

    @CallSuper
    /* JADX INFO: renamed from: ˏ */
    void mo286() throws o {
        a.m273(3, "BaseTracker", this, "Attempting to start impression.");
        m304();
        if (this.f304) {
            throw new o("Tracker already started");
        }
        if (!this.f308) {
            mo283(new ArrayList());
            j jVar = this.f306;
            if (jVar != null) {
                jVar.m360(this);
                this.f304 = true;
                a.m273(3, "BaseTracker", this, "Impression started.");
                return;
            }
            a.m273(3, "BaseTracker", this, "Bridge is null, won't start tracking");
            throw new o("Bridge is null");
        }
        throw new o("Tracker already stopped");
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    final void m306(WebView webView) throws o {
        if (webView != null) {
            this.f307 = new WeakReference<>(webView);
            if (this.f306 == null) {
                if (!(this.f301 || this.f302)) {
                    a.m273(3, "BaseTracker", this, "Attempting bridge installation.");
                    if (this.f307.get() != null) {
                        this.f306 = new j(this.f307.get(), j.e.f379);
                        a.m273(3, "BaseTracker", this, "Bridge installed.");
                    } else {
                        this.f306 = null;
                        a.m273(3, "BaseTracker", this, "Bridge not installed, WebView is null.");
                    }
                }
            }
            j jVar = this.f306;
            if (jVar != null) {
                jVar.m358(this);
            }
        }
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    final void m304() throws o {
        if (this.f309 == null) {
            return;
        }
        throw new o("Tracker initialization failed: " + this.f309.getMessage());
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    final boolean m302() {
        return this.f304 && !this.f308;
    }

    /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
    final View m300() {
        return this.f300.get();
    }

    /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
    final String m301() {
        this.f310.m443(this.f305, this.f300.get());
        return this.f310.f472;
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    final void m307(String str, Exception exc) {
        try {
            o.m389(exc);
            String strM388 = o.m388(str, exc);
            if (this.f303 != null) {
                this.f303.onTrackingFailedToStart(strM388);
            }
            a.m273(3, "BaseTracker", this, strM388);
            a.m270("[ERROR] ", mo303() + " " + strM388);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    final void m305() throws o {
        if (this.f304) {
            throw new o("Tracker already started");
        }
        if (this.f308) {
            throw new o("Tracker already stopped");
        }
    }

    @CallSuper
    /* JADX INFO: renamed from: ˋ */
    void mo283(List<String> list) throws o {
        if (this.f300.get() == null && !this.f302) {
            list.add("Tracker's target view is null");
        }
        if (!list.isEmpty()) {
            throw new o(TextUtils.join(" and ", list));
        }
    }

    /* JADX INFO: renamed from: ʻ, reason: contains not printable characters */
    final String m299() {
        return a.m272(this.f300.get());
    }
}
