package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.webkit.ValueCallback;
import android.webkit.WebViewClient;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public class zzbdh extends zzbdc implements zzbdi {

    @GuardedBy("this")
    private boolean zzefy;

    @GuardedBy("this")
    private boolean zzegr;
    private final zzbdf zzehu;

    public zzbdh(Context context, zzbdf zzbdfVar) {
        super(context);
        com.google.android.gms.ads.internal.zzp.zzkc().zzug();
        this.zzehu = zzbdfVar;
        super.setWebViewClient(zzbdfVar);
    }

    @Override // android.webkit.WebView
    public void setWebViewClient(WebViewClient webViewClient) {
    }

    @GuardedBy("this")
    protected void zzaw(boolean z) {
    }

    public final synchronized boolean isDestroyed() {
        return this.zzefy;
    }

    @Override // android.webkit.WebView
    public synchronized void destroy() {
        if (this.zzefy) {
            return;
        }
        this.zzefy = true;
        this.zzehu.zza(this);
        zzaw(false);
        zzatm.zzdy("Initiating WebView self destruct sequence in 3...");
        zzatm.zzdy("Loading blank page in WebView, 2...");
        try {
            super.loadUrl("about:blank");
        } catch (UnsatisfiedLinkError e) {
            com.google.android.gms.ads.internal.zzp.zzkc().zza(e, "AdWebViewImpl.loadUrlUnsafe");
            zzatm.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbdi
    public final synchronized void zza(zzbde zzbdeVar) {
        zzatm.zzdy("Blank page loaded, 1...");
        zzze();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @VisibleForTesting
    public synchronized void zzze() {
        zzatm.zzdy("Destroying WebView!");
        zzuh();
        zzawx.zzdwa.execute(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzbdg
            private final zzbdh zzeht;

            {
                this.zzeht = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzeht.zzaau();
            }
        });
    }

    protected void finalize() throws Throwable {
        try {
            synchronized (this) {
                if (!isDestroyed()) {
                    zzaw(true);
                }
                zzuh();
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
            throw th;
        }
    }

    private final synchronized void zzuh() {
        if (!this.zzegr) {
            this.zzegr = true;
            com.google.android.gms.ads.internal.zzp.zzkc().zzuh();
        }
    }

    @Override // android.webkit.WebView
    @TargetApi(19)
    public synchronized void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        if (isDestroyed()) {
            zzatm.zzeu("#004 The webview is destroyed. Ignoring action.");
            if (valueCallback != null) {
                valueCallback.onReceiveValue(null);
            }
            return;
        }
        super.evaluateJavascript(str, valueCallback);
    }

    @Override // com.google.android.gms.internal.ads.zzbdc, android.webkit.WebView
    public synchronized void loadUrl(String str) {
        if (!isDestroyed()) {
            super.loadUrl(str);
        } else {
            zzatm.zzeu("#004 The webview is destroyed. Ignoring action.");
        }
    }

    @Override // android.webkit.WebView
    public synchronized void loadData(String str, String str2, String str3) {
        if (!isDestroyed()) {
            super.loadData(str, str2, str3);
        } else {
            zzatm.zzeu("#004 The webview is destroyed. Ignoring action.");
        }
    }

    @Override // android.webkit.WebView
    public synchronized void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        if (!isDestroyed()) {
            super.loadDataWithBaseURL(str, str2, str3, str4, str5);
        } else {
            zzatm.zzeu("#004 The webview is destroyed. Ignoring action.");
        }
    }

    @Override // android.webkit.WebView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return !isDestroyed() && super.onTouchEvent(motionEvent);
    }

    @Override // android.webkit.WebView, android.view.View
    @TargetApi(21)
    protected void onDraw(Canvas canvas) {
        if (isDestroyed()) {
            return;
        }
        super.onDraw(canvas);
    }

    @Override // android.webkit.WebView, com.google.android.gms.internal.ads.zzbbc
    public void onPause() {
        if (isDestroyed()) {
            return;
        }
        super.onPause();
    }

    @Override // android.webkit.WebView, com.google.android.gms.internal.ads.zzbbc
    public void onResume() {
        if (isDestroyed()) {
            return;
        }
        super.onResume();
    }

    @Override // android.webkit.WebView
    public void stopLoading() {
        if (isDestroyed()) {
            return;
        }
        super.stopLoading();
    }

    @Override // com.google.android.gms.internal.ads.zzbdc, android.webkit.WebView
    public /* bridge */ /* synthetic */ void addJavascriptInterface(Object obj, String str) {
        super.addJavascriptInterface(obj, str);
    }

    @Override // com.google.android.gms.internal.ads.zzbdc, com.google.android.gms.internal.ads.zzagb, com.google.android.gms.internal.ads.zzagu
    public /* bridge */ /* synthetic */ void zzct(String str) {
        super.zzct(str);
    }

    final /* synthetic */ void zzaau() {
        super.destroy();
    }
}
