package com.applovin.impl.sdk;

import android.app.Activity;
import android.content.Intent;
import com.applovin.sdk.AppLovinPrivacySettings;
import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.sdk.AppLovinUserService;
import com.applovin.sdk.AppLovinWebViewActivity;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class g implements f.a, AppLovinWebViewActivity.EventListener {
    private static final AtomicBoolean a = new AtomicBoolean();
    private static WeakReference<AppLovinWebViewActivity> b;
    private final h c;
    private final n d;
    private AppLovinUserService.OnConsentDialogDismissListener e;
    private f f;
    private WeakReference<Activity> g;
    private com.applovin.impl.sdk.utils.a h;

    g(h hVar) {
        this.g = new WeakReference<>(null);
        this.c = hVar;
        this.d = hVar.w();
        if (hVar.B() != null) {
            this.g = new WeakReference<>(hVar.B());
        }
        hVar.V().a(new com.applovin.impl.sdk.utils.a() { // from class: com.applovin.impl.sdk.g.1
            @Override // com.applovin.impl.sdk.utils.a, android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
                g.this.g = new WeakReference(activity);
            }
        });
        this.f = new f(this, hVar);
    }

    private void a(boolean z, long j) {
        f();
        if (z) {
            a(j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(h hVar) {
        if (!com.applovin.impl.sdk.utils.e.a((Class<?>) AppLovinWebViewActivity.class, hVar.z())) {
            this.d.e("AppLovinSdk", "Unable to show consent dialog. Please add <activity android:name=\"com.applovin.sdk.AppLovinWebViewActivity\" android:configChanges=\"keyboardHidden|orientation|screenSize\"/> to your AndroidManifest.xml file.");
            return false;
        }
        if (c()) {
            this.d.e("AppLovinSdk", "Consent dialog already showing");
            return false;
        }
        if (!com.applovin.impl.sdk.utils.f.a(hVar.z(), hVar)) {
            this.d.e("AppLovinSdk", "No internet available, skip showing of consent dialog");
            return false;
        }
        if (!((Boolean) hVar.a(com.applovin.impl.sdk.b.b.ak)).booleanValue()) {
            this.d.d("ConsentDialogManager", "Blocked publisher from showing consent dialog");
            return false;
        }
        if (com.applovin.impl.sdk.utils.k.b((String) hVar.a(com.applovin.impl.sdk.b.b.al))) {
            return true;
        }
        this.d.d("ConsentDialogManager", "AdServer returned empty consent dialog URL");
        return false;
    }

    private void f() {
        this.c.V().b(this.h);
        if (c()) {
            AppLovinWebViewActivity appLovinWebViewActivity = b.get();
            b = null;
            if (appLovinWebViewActivity != null) {
                appLovinWebViewActivity.finish();
                AppLovinUserService.OnConsentDialogDismissListener onConsentDialogDismissListener = this.e;
                if (onConsentDialogDismissListener != null) {
                    onConsentDialogDismissListener.onDismiss();
                    this.e = null;
                }
            }
        }
    }

    @Override // com.applovin.impl.sdk.f.a
    public void a() {
        this.d.a("ConsentDialogManager", "User accepted consent alert");
        if (this.g.get() != null) {
            final Activity activity = this.g.get();
            AppLovinSdkUtils.runOnUiThreadDelayed(new Runnable() { // from class: com.applovin.impl.sdk.g.4
                @Override // java.lang.Runnable
                public void run() {
                    g.this.a(activity, (AppLovinUserService.OnConsentDialogDismissListener) null);
                }
            }, ((Long) this.c.a(com.applovin.impl.sdk.b.b.an)).longValue());
        }
    }

    public void a(final long j) {
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.g.3
            @Override // java.lang.Runnable
            public void run() {
                g.this.d.a("ConsentDialogManager", "Scheduling repeating consent alert");
                g.this.f.a(j, g.this.c, g.this);
            }
        });
    }

    public void a(final Activity activity, final AppLovinUserService.OnConsentDialogDismissListener onConsentDialogDismissListener) {
        activity.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.g.2
            @Override // java.lang.Runnable
            public void run() {
                g gVar = g.this;
                if (!gVar.a(gVar.c) || g.a.getAndSet(true)) {
                    AppLovinUserService.OnConsentDialogDismissListener onConsentDialogDismissListener2 = onConsentDialogDismissListener;
                    if (onConsentDialogDismissListener2 != null) {
                        onConsentDialogDismissListener2.onDismiss();
                        return;
                    }
                    return;
                }
                g.this.g = new WeakReference(activity);
                g.this.e = onConsentDialogDismissListener;
                g.this.h = new com.applovin.impl.sdk.utils.a() { // from class: com.applovin.impl.sdk.g.2.1
                    @Override // com.applovin.impl.sdk.utils.a, android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityStarted(Activity activity2) {
                        if (activity2 instanceof AppLovinWebViewActivity) {
                            if (!g.this.c() || g.b.get() != activity2) {
                                AppLovinWebViewActivity appLovinWebViewActivity = (AppLovinWebViewActivity) activity2;
                                WeakReference unused = g.b = new WeakReference(appLovinWebViewActivity);
                                appLovinWebViewActivity.loadUrl((String) g.this.c.a(com.applovin.impl.sdk.b.b.al), g.this);
                            }
                            g.a.set(false);
                        }
                    }
                };
                g.this.c.V().a(g.this.h);
                Intent intent = new Intent(activity, (Class<?>) AppLovinWebViewActivity.class);
                intent.putExtra(AppLovinWebViewActivity.INTENT_EXTRA_KEY_SDK_KEY, g.this.c.u());
                intent.putExtra(AppLovinWebViewActivity.INTENT_EXTRA_KEY_IMMERSIVE_MODE_ON, (Serializable) g.this.c.a(com.applovin.impl.sdk.b.b.am));
                activity.startActivity(intent);
            }
        });
    }

    @Override // com.applovin.impl.sdk.f.a
    public void b() {
        this.d.a("ConsentDialogManager", "User rejected consent alert");
    }

    boolean c() {
        WeakReference<AppLovinWebViewActivity> weakReference = b;
        return (weakReference == null || weakReference.get() == null) ? false : true;
    }

    @Override // com.applovin.sdk.AppLovinWebViewActivity.EventListener
    public void onReceivedEvent(String str) {
        boolean zBooleanValue;
        h hVar;
        com.applovin.impl.sdk.b.b<Long> bVar;
        this.d.a("ConsentDialogManager", "Received event: " + str);
        if ("accepted".equalsIgnoreCase(str)) {
            AppLovinPrivacySettings.setHasUserConsent(true, this.c.z());
            f();
            return;
        }
        if ("rejected".equalsIgnoreCase(str)) {
            AppLovinPrivacySettings.setHasUserConsent(false, this.c.z());
            zBooleanValue = ((Boolean) this.c.a(com.applovin.impl.sdk.b.b.ao)).booleanValue();
            hVar = this.c;
            bVar = com.applovin.impl.sdk.b.b.at;
        } else if ("closed".equalsIgnoreCase(str)) {
            zBooleanValue = ((Boolean) this.c.a(com.applovin.impl.sdk.b.b.ap)).booleanValue();
            hVar = this.c;
            bVar = com.applovin.impl.sdk.b.b.au;
        } else {
            if (!AppLovinWebViewActivity.EVENT_DISMISSED_VIA_BACK_BUTTON.equalsIgnoreCase(str)) {
                return;
            }
            zBooleanValue = ((Boolean) this.c.a(com.applovin.impl.sdk.b.b.aq)).booleanValue();
            hVar = this.c;
            bVar = com.applovin.impl.sdk.b.b.av;
        }
        a(zBooleanValue, ((Long) hVar.a(bVar)).longValue());
    }
}
