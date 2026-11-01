package com.applovin.impl.sdk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
class f implements s.a {
    private static AlertDialog b;
    private static final AtomicBoolean c = new AtomicBoolean();
    private final g a;
    private com.applovin.impl.sdk.utils.l d;

    /* JADX INFO: renamed from: com.applovin.impl.sdk.f$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ h a;
        final /* synthetic */ a b;

        AnonymousClass1(h hVar, a aVar) {
            this.a = hVar;
            this.b = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            n nVarW;
            String str;
            if (f.this.a.c()) {
                this.a.w().d("ConsentAlertManager", "Consent dialog already showing, skip showing of consent alert");
                return;
            }
            Activity activityA = this.a.V().a();
            if (activityA != null && com.applovin.impl.sdk.utils.f.a(this.a.z(), this.a)) {
                AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.f.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AlertDialog unused = f.b = new AlertDialog.Builder(AnonymousClass1.this.a.V().a()).setTitle((CharSequence) AnonymousClass1.this.a.a(com.applovin.impl.sdk.b.b.ay)).setMessage((CharSequence) AnonymousClass1.this.a.a(com.applovin.impl.sdk.b.b.az)).setCancelable(false).setPositiveButton((CharSequence) AnonymousClass1.this.a.a(com.applovin.impl.sdk.b.b.aA), new DialogInterface.OnClickListener() { // from class: com.applovin.impl.sdk.f.1.1.2
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i) {
                                AnonymousClass1.this.b.a();
                                dialogInterface.dismiss();
                                f.c.set(false);
                            }
                        }).setNegativeButton((CharSequence) AnonymousClass1.this.a.a(com.applovin.impl.sdk.b.b.aB), new DialogInterface.OnClickListener() { // from class: com.applovin.impl.sdk.f.1.1.1
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i) {
                                AnonymousClass1.this.b.b();
                                dialogInterface.dismiss();
                                f.c.set(false);
                                f.this.a(((Long) AnonymousClass1.this.a.a(com.applovin.impl.sdk.b.b.aw)).longValue(), AnonymousClass1.this.a, AnonymousClass1.this.b);
                            }
                        }).create();
                        f.b.show();
                    }
                });
                return;
            }
            if (activityA == null) {
                nVarW = this.a.w();
                str = "No parent Activity found - rescheduling consent alert...";
            } else {
                nVarW = this.a.w();
                str = "No internet available - rescheduling consent alert...";
            }
            nVarW.d("ConsentAlertManager", str);
            f.c.set(false);
            f.this.a(((Long) this.a.a(com.applovin.impl.sdk.b.b.ax)).longValue(), this.a, this.b);
        }
    }

    public interface a {
        void a();

        void b();
    }

    f(g gVar, h hVar) {
        this.a = gVar;
        hVar.T().a(this);
    }

    public void a(long j, h hVar, a aVar) {
        if (j <= 0) {
            return;
        }
        AlertDialog alertDialog = b;
        if (alertDialog == null || !alertDialog.isShowing()) {
            if (c.getAndSet(true)) {
                if (j >= this.d.a()) {
                    hVar.w().c("ConsentAlertManager", "Skip scheduling consent alert - one scheduled already with remaining time of " + this.d.a() + " milliseconds");
                    return;
                }
                hVar.w().a("ConsentAlertManager", "Scheduling consent alert earlier (" + j + "ms) than remaining scheduled time (" + this.d.a() + "ms)");
                this.d.d();
            }
            hVar.w().a("ConsentAlertManager", "Scheduling consent alert for " + j + " milliseconds");
            this.d = com.applovin.impl.sdk.utils.l.a(j, hVar, new AnonymousClass1(hVar, aVar));
        }
    }

    @Override // com.applovin.impl.sdk.s.a
    public void b() {
        com.applovin.impl.sdk.utils.l lVar = this.d;
        if (lVar != null) {
            lVar.b();
        }
    }

    @Override // com.applovin.impl.sdk.s.a
    public void c() {
        com.applovin.impl.sdk.utils.l lVar = this.d;
        if (lVar != null) {
            lVar.c();
        }
    }
}
