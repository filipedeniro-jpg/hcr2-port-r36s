package com.chartboost.sdk;

import android.app.Activity;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.c.RunnableC0032c;
import com.chartboost.sdk.impl.aw;
import com.chartboost.sdk.impl.bc;
import com.chartboost.sdk.impl.c.a;
import com.chartboost.sdk.impl.l;
import com.chartboost.sdk.impl.s;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public class d {
    final aw a;
    private final l c;
    private final AtomicReference<com.chartboost.sdk.Model.e> d;
    private final Handler e;
    bc b = null;
    private int f = -1;

    public d(aw awVar, l lVar, AtomicReference<com.chartboost.sdk.Model.e> atomicReference, Handler handler) {
        this.a = awVar;
        this.c = lVar;
        this.d = atomicReference;
        this.e = handler;
    }

    void a(com.chartboost.sdk.Model.c cVar) {
        if (cVar.l != 0) {
            e(cVar);
        }
    }

    private void e(com.chartboost.sdk.Model.c cVar) {
        bc bcVar = this.b;
        if (bcVar != null && bcVar.e() != cVar) {
            CBLogging.b("CBViewController", "Impression already visible");
            cVar.a(CBError.CBImpressionError.IMPRESSION_ALREADY_VISIBLE);
            return;
        }
        boolean z = cVar.l != 2;
        cVar.l = 2;
        Activity activityB = cVar.g.b();
        CBError.CBImpressionError cBImpressionErrorJ = activityB == null ? CBError.CBImpressionError.NO_HOST_ACTIVITY : null;
        if (cBImpressionErrorJ == null) {
            cBImpressionErrorJ = cVar.j();
        }
        if (cBImpressionErrorJ != null) {
            CBLogging.b("CBViewController", "Unable to create the view while trying th display the impression");
            cVar.a(cBImpressionErrorJ);
            return;
        }
        if (this.b == null) {
            this.b = (bc) g.a().a(new bc(activityB, cVar));
            activityB.addContentView(this.b, new FrameLayout.LayoutParams(-1, -1));
        }
        CBUtility.a(activityB, cVar.p.b, this.d.get());
        if (s.a().a(11) && this.f == -1 && (cVar.n == 1 || cVar.n == 2)) {
            this.f = activityB.getWindow().getDecorView().getSystemUiVisibility();
            Chartboost.setActivityAttrs(activityB);
        }
        this.b.a();
        CBLogging.e("CBViewController", "Displaying the impression");
        cVar.s = this.b;
        if (z) {
            if (cVar.p.b == 0) {
                this.b.c().a(this.a, cVar.p);
            }
            int iIntValue = cVar.p.b == 1 ? 6 : 1;
            Integer numA = aw.a(cVar.p.o);
            if (numA != null) {
                iIntValue = numA.intValue();
            }
            cVar.m();
            c cVar2 = cVar.g;
            cVar2.getClass();
            c.RunnableC0032c runnableC0032c = cVar2.new RunnableC0032c(12);
            runnableC0032c.d = cVar;
            this.a.a(iIntValue, cVar, runnableC0032c, this);
            this.c.a();
        }
    }

    public void b(final com.chartboost.sdk.Model.c cVar) {
        CBLogging.e("CBViewController", "Dismissing impression");
        final Activity activityB = cVar.g.b();
        Runnable runnable = new Runnable() { // from class: com.chartboost.sdk.d.1
            @Override // java.lang.Runnable
            public void run() {
                com.chartboost.sdk.Model.c cVar2 = cVar;
                cVar2.l = 4;
                int iIntValue = cVar2.p.b == 1 ? 6 : 1;
                Integer numA = aw.a(cVar.p.o);
                if (numA != null) {
                    iIntValue = numA.intValue();
                }
                c cVar3 = cVar.g;
                cVar3.getClass();
                c.RunnableC0032c runnableC0032c = cVar3.new RunnableC0032c(13);
                runnableC0032c.d = cVar;
                runnableC0032c.b = activityB;
                d.this.a.a(iIntValue, cVar, runnableC0032c);
            }
        };
        if (cVar.t) {
            cVar.a(runnable);
        } else {
            runnable.run();
        }
    }

    void a(com.chartboost.sdk.Model.c cVar, Activity activity) {
        c cVar2 = cVar.g;
        cVar2.getClass();
        c.RunnableC0032c runnableC0032c = cVar2.new RunnableC0032c(14);
        runnableC0032c.d = cVar;
        this.e.post(runnableC0032c);
        cVar.l();
        CBUtility.b(activity, cVar.p.b, this.d.get());
        if (this.f != -1) {
            if (cVar.n == 1 || cVar.n == 2) {
                activity.getWindow().getDecorView().setSystemUiVisibility(this.f);
                this.f = -1;
            }
        }
    }

    void c(com.chartboost.sdk.Model.c cVar) {
        CBLogging.e("CBViewController", "Removing impression silently");
        cVar.i();
        try {
            ((ViewGroup) this.b.getParent()).removeView(this.b);
        } catch (Exception e) {
            CBLogging.a("CBViewController", "Exception removing impression silently", e);
            com.chartboost.sdk.Tracking.a.a(getClass(), "removeImpressionSilently", e);
        }
        this.b = null;
    }

    public void d(com.chartboost.sdk.Model.c cVar) {
        CBLogging.e("CBViewController", "Removing impression");
        cVar.l = 5;
        cVar.h();
        this.b = null;
        this.c.b();
        Handler handler = this.e;
        com.chartboost.sdk.impl.c cVar2 = cVar.a;
        cVar2.getClass();
        handler.post(cVar2.new a(3, cVar.m, null));
        if (cVar.v()) {
            Handler handler2 = this.e;
            com.chartboost.sdk.impl.c cVar3 = cVar.a;
            cVar3.getClass();
            handler2.post(cVar3.new a(2, cVar.m, null));
        }
        a(cVar.g);
    }

    void a(c cVar) {
        CBLogging.e("CBViewController", "Attempting to close impression activity");
        Activity activityB = cVar.b();
        if (activityB == null || !(activityB instanceof CBImpressionActivity)) {
            return;
        }
        CBLogging.e("CBViewController", "Closing impression activity");
        cVar.f();
        activityB.finish();
    }

    public bc a() {
        return this.b;
    }
}
