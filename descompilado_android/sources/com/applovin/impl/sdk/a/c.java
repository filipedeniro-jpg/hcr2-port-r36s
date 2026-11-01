package com.applovin.impl.sdk.a;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.applovin.impl.sdk.c.g;
import com.applovin.impl.sdk.h;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdRewardListener;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes.dex */
class c {
    private final h a;
    private final com.applovin.impl.sdk.a.a b;
    private final Activity c;
    private final Runnable d;
    private final AppLovinAdRewardListener e;
    private final Timer f;

    /* JADX INFO: renamed from: com.applovin.impl.sdk.a.c$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ AppLovinAd a;

        AnonymousClass1(AppLovinAd appLovinAd) {
            this.a = appLovinAd;
        }

        @Override // java.lang.Runnable
        public void run() {
            AlertDialog.Builder builder = new AlertDialog.Builder(c.this.c);
            builder.setTitle((CharSequence) c.this.a.a(com.applovin.impl.sdk.b.b.bJ));
            builder.setMessage((CharSequence) c.this.a.a(com.applovin.impl.sdk.b.b.bK));
            builder.setCancelable(false);
            builder.setPositiveButton((CharSequence) c.this.a.a(com.applovin.impl.sdk.b.b.bL), new DialogInterface.OnClickListener() { // from class: com.applovin.impl.sdk.a.c.1.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    c.this.a.G().a(g.k);
                    c.this.f.schedule(new TimerTask() { // from class: com.applovin.impl.sdk.a.c.1.1.1
                        @Override // java.util.TimerTask, java.lang.Runnable
                        public void run() {
                            c.this.c.runOnUiThread(c.this.d);
                        }
                    }, 200L);
                }
            });
            builder.setNegativeButton((CharSequence) c.this.a.a(com.applovin.impl.sdk.b.b.bM), new DialogInterface.OnClickListener() { // from class: com.applovin.impl.sdk.a.c.1.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    c.this.a.G().a(g.l);
                    c.this.b.a(AnonymousClass1.this.a, c.this.e);
                }
            });
            builder.show();
        }
    }

    static class a {
        private h a;
        private com.applovin.impl.sdk.a.a b;
        private Activity c;
        private AppLovinAdRewardListener d;
        private Runnable e;

        private a() {
        }

        /* synthetic */ a(AnonymousClass1 anonymousClass1) {
            this();
        }

        a a(Activity activity) {
            this.c = activity;
            return this;
        }

        a a(com.applovin.impl.sdk.a.a aVar) {
            this.b = aVar;
            return this;
        }

        a a(h hVar) {
            this.a = hVar;
            return this;
        }

        a a(AppLovinAdRewardListener appLovinAdRewardListener) {
            this.d = appLovinAdRewardListener;
            return this;
        }

        a a(Runnable runnable) {
            this.e = runnable;
            return this;
        }

        c a() {
            return new c(this, null);
        }
    }

    private c(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.e;
        this.e = aVar.d;
        this.f = new Timer("IncentivizedAdLauncher");
    }

    /* synthetic */ c(a aVar, AnonymousClass1 anonymousClass1) {
        this(aVar);
    }

    static a a() {
        return new a(null);
    }

    void a(AppLovinAd appLovinAd) {
        this.c.runOnUiThread(new AnonymousClass1(appLovinAd));
    }
}
