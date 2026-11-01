package com.moat.analytics.mobile.iro;

import android.app.Application;
import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
final class j extends MoatAnalytics implements t.a {

    /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
    private String f595;

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    @Nullable
    b f596;

    /* JADX INFO: renamed from: ˊॱ, reason: contains not printable characters */
    private MoatOptions f597;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    WeakReference<Context> f598;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    boolean f601 = false;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    boolean f599 = false;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    boolean f600 = false;

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    private boolean f602 = false;

    j() {
    }

    @Override // com.moat.analytics.mobile.iro.MoatAnalytics
    public final void start(Application application) {
        start(new MoatOptions(), application);
    }

    @Override // com.moat.analytics.mobile.iro.MoatAnalytics
    @UiThread
    public final void prepareNativeDisplayTracking(String str) {
        this.f595 = str;
        if (t.m598().f660 == t.c.f676) {
            return;
        }
        try {
            m541();
        } catch (Exception e) {
            o.m569(e);
        }
    }

    @UiThread
    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private void m541() {
        if (this.f596 == null) {
            this.f596 = new b(a.m452(), b.a.f510);
            this.f596.m464(this.f595);
            b.m461(3, "Analytics", this, "Preparing native display tracking with partner code " + this.f595);
            b.m459("[SUCCESS] ", "Prepared for native display tracking with partner code " + this.f595);
        }
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    final boolean m542() {
        return this.f602;
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    final boolean m543() {
        MoatOptions moatOptions = this.f597;
        return moatOptions != null && moatOptions.disableLocationServices;
    }

    @Override // com.moat.analytics.mobile.iro.t.a
    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    public final void mo544() throws o {
        o.m566();
        k.m554();
        if (this.f595 != null) {
            try {
                m541();
            } catch (Exception e) {
                o.m569(e);
            }
        }
    }

    @Override // com.moat.analytics.mobile.iro.MoatAnalytics
    public final void start(MoatOptions moatOptions, Application application) {
        try {
            if (this.f602) {
                b.m461(3, "Analytics", this, "Moat SDK has already been started.");
                return;
            }
            this.f597 = moatOptions;
            t.m598().m607();
            this.f600 = moatOptions.disableLocationServices;
            if (application == null) {
                throw new o("Moat Analytics SDK didn't start, application was null");
            }
            if (moatOptions.loggingEnabled && p.m578(application.getApplicationContext())) {
                this.f601 = true;
            }
            this.f598 = new WeakReference<>(application.getApplicationContext());
            this.f602 = true;
            this.f599 = moatOptions.autoTrackGMAInterstitials;
            a.m450(application);
            t.m598().m608(this);
            if (!moatOptions.disableAdIdCollection) {
                p.m573(application);
            }
            b.m459("[SUCCESS] ", "Moat Analytics SDK Version 2.4.0 started");
        } catch (Exception e) {
            o.m569(e);
        }
    }
}
