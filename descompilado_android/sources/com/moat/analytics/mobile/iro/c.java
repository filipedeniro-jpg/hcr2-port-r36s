package com.moat.analytics.mobile.iro;

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
abstract class c {

    /* JADX INFO: renamed from: ʻ, reason: contains not printable characters */
    private WeakReference<View> f513;

    /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
    private final boolean f514;

    /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
    private boolean f515;

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    TrackerListener f516;

    /* JADX INFO: renamed from: ˊॱ, reason: contains not printable characters */
    private final y f517;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    o f518 = null;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    final String f519;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    f f520;

    /* JADX INFO: renamed from: ˏॱ, reason: contains not printable characters */
    private boolean f521;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    WeakReference<WebView> f522;

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    final boolean f523;

    @Deprecated
    public void setActivity(Activity activity) {
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    abstract String mo469();

    c(@Nullable View view, boolean z, boolean z2) {
        String str;
        b.m461(3, "BaseTracker", this, "Initializing.");
        if (z) {
            str = "m" + hashCode();
        } else {
            str = "";
        }
        this.f519 = str;
        this.f513 = new WeakReference<>(view);
        this.f514 = z;
        this.f523 = z2;
        this.f515 = false;
        this.f521 = false;
        this.f517 = new y();
    }

    public void setListener(TrackerListener trackerListener) {
        this.f516 = trackerListener;
    }

    public void removeListener() {
        this.f516 = null;
    }

    public void startTracking() {
        try {
            b.m461(3, "BaseTracker", this, "In startTracking method.");
            mo472();
            if (this.f516 != null) {
                this.f516.onTrackingStarted("Tracking started on " + b.m463(this.f513.get()));
            }
            String str = "startTracking succeeded for " + b.m463(this.f513.get());
            b.m461(3, "BaseTracker", this, str);
            b.m459("[SUCCESS] ", mo469() + " " + str);
        } catch (Exception e) {
            m475("startTracking", e);
        }
    }

    @CallSuper
    public void stopTracking() {
        boolean z = false;
        try {
            b.m461(3, "BaseTracker", this, "In stopTracking method.");
            this.f521 = true;
            if (this.f520 != null) {
                this.f520.m513(this);
                z = true;
            }
        } catch (Exception e) {
            o.m569(e);
        }
        StringBuilder sb = new StringBuilder("Attempt to stop tracking ad impression was ");
        sb.append(z ? "" : "un");
        sb.append("successful.");
        b.m461(3, "BaseTracker", this, sb.toString());
        String str = z ? "[SUCCESS] " : "[ERROR] ";
        StringBuilder sb2 = new StringBuilder();
        sb2.append(mo469());
        sb2.append(" stopTracking ");
        sb2.append(z ? AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED : Constants.ParametersKeys.FAILED);
        sb2.append(" for ");
        sb2.append(b.m463(this.f513.get()));
        b.m459(str, sb2.toString());
        TrackerListener trackerListener = this.f516;
        if (trackerListener != null) {
            trackerListener.onTrackingStopped("");
            this.f516 = null;
        }
    }

    @CallSuper
    public void changeTargetView(View view) {
        b.m461(3, "BaseTracker", this, "changing view to " + b.m463(view));
        this.f513 = new WeakReference<>(view);
    }

    @CallSuper
    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    void mo472() throws o {
        b.m461(3, "BaseTracker", this, "Attempting to start impression.");
        m470();
        if (this.f515) {
            throw new o("Tracker already started");
        }
        if (!this.f521) {
            mo474(new ArrayList());
            f fVar = this.f520;
            if (fVar != null) {
                fVar.m515(this);
                this.f515 = true;
                b.m461(3, "BaseTracker", this, "Impression started.");
                return;
            }
            b.m461(3, "BaseTracker", this, "Bridge is null, won't start tracking");
            throw new o("Bridge is null");
        }
        throw new o("Tracker already stopped");
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    final void m471(WebView webView) throws o {
        if (webView != null) {
            this.f522 = new WeakReference<>(webView);
            if (this.f520 == null) {
                if (!(this.f514 || this.f523)) {
                    b.m461(3, "BaseTracker", this, "Attempting bridge installation.");
                    if (this.f522.get() != null) {
                        this.f520 = new f(this.f522.get(), f.b.f563);
                        b.m461(3, "BaseTracker", this, "Bridge installed.");
                    } else {
                        this.f520 = null;
                        b.m461(3, "BaseTracker", this, "Bridge not installed, WebView is null.");
                    }
                }
            }
            f fVar = this.f520;
            if (fVar != null) {
                fVar.m512(this);
            }
        }
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    final void m470() throws o {
        if (this.f518 == null) {
            return;
        }
        throw new o("Tracker initialization failed: " + this.f518.getMessage());
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    final boolean m476() {
        return this.f515 && !this.f521;
    }

    /* JADX INFO: renamed from: ʻ, reason: contains not printable characters */
    final View m466() {
        return this.f513.get();
    }

    /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
    final String m467() {
        this.f517.m622(this.f519, this.f513.get());
        return this.f517.f693;
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    final void m475(String str, Exception exc) {
        try {
            o.m569(exc);
            String strM568 = o.m568(str, exc);
            if (this.f516 != null) {
                this.f516.onTrackingFailedToStart(strM568);
            }
            b.m461(3, "BaseTracker", this, strM568);
            b.m459("[ERROR] ", mo469() + " " + strM568);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    final void m473() throws o {
        if (this.f515) {
            throw new o("Tracker already started");
        }
        if (this.f521) {
            throw new o("Tracker already stopped");
        }
    }

    @CallSuper
    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    void mo474(List<String> list) throws o {
        if (this.f513.get() == null && !this.f523) {
            list.add("Tracker's target view is null");
        }
        if (!list.isEmpty()) {
            throw new o(TextUtils.join(" and ", list));
        }
    }

    /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
    final String m468() {
        return b.m463(this.f513.get());
    }
}
