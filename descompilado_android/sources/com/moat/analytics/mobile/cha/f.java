package com.moat.analytics.mobile.cha;

import android.app.Application;
import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
final class f extends MoatAnalytics implements t.b {

    /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
    private String f314;

    /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
    private MoatOptions f315;

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    WeakReference<Context> f316;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    @Nullable
    a f320;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    boolean f318 = false;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    boolean f319 = false;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    boolean f317 = false;

    /* JADX INFO: renamed from: ʻ, reason: contains not printable characters */
    private boolean f313 = false;

    f() {
    }

    @Override // com.moat.analytics.mobile.cha.MoatAnalytics
    public final void start(Application application) {
        start(new MoatOptions(), application);
    }

    @Override // com.moat.analytics.mobile.cha.MoatAnalytics
    @UiThread
    public final void prepareNativeDisplayTracking(String str) {
        this.f314 = str;
        if (t.m423().f441 == t.a.f453) {
            return;
        }
        try {
            m309();
        } catch (Exception e) {
            o.m389(e);
        }
    }

    @UiThread
    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private void m309() {
        if (this.f320 == null) {
            this.f320 = new a(c.m294(), a.d.f280);
            this.f320.m277(this.f314);
            a.m273(3, "Analytics", this, "Preparing native display tracking with partner code " + this.f314);
            a.m270("[SUCCESS] ", "Prepared for native display tracking with partner code " + this.f314);
        }
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    final boolean m310() {
        return this.f313;
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    final boolean m311() {
        MoatOptions moatOptions = this.f315;
        return moatOptions != null && moatOptions.disableLocationServices;
    }

    @Override // com.moat.analytics.mobile.cha.t.b
    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    public final void mo312() throws o {
        o.m390();
        n.m375();
        if (this.f314 != null) {
            try {
                m309();
            } catch (Exception e) {
                o.m389(e);
            }
        }
    }

    @Override // com.moat.analytics.mobile.cha.MoatAnalytics
    public final void start(MoatOptions moatOptions, Application application) {
        try {
            if (this.f313) {
                a.m273(3, "Analytics", this, "Moat SDK has already been started.");
                return;
            }
            this.f315 = moatOptions;
            t.m423().m429();
            this.f317 = moatOptions.disableLocationServices;
            if (application == null) {
                throw new o("Moat Analytics SDK didn't start, application was null");
            }
            if (moatOptions.loggingEnabled && r.m405(application.getApplicationContext())) {
                this.f318 = true;
            }
            this.f316 = new WeakReference<>(application.getApplicationContext());
            this.f313 = true;
            this.f319 = moatOptions.autoTrackGMAInterstitials;
            c.m296(application);
            t.m423().m428(this);
            if (!moatOptions.disableAdIdCollection) {
                r.m408(application);
            }
            a.m270("[SUCCESS] ", "Moat Analytics SDK Version 2.4.1 started");
        } catch (Exception e) {
            o.m389(e);
        }
    }
}
