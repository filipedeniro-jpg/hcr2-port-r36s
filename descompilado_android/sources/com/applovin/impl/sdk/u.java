package com.applovin.impl.sdk;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewTreeObserver;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.sdk.AppLovinSdkUtils;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class u {
    private final h a;
    private final Runnable e;
    private WeakReference<ViewTreeObserver> g;
    private View h;
    private int i;
    private long j;
    private final Object b = new Object();
    private final Rect c = new Rect();
    private long k = Long.MIN_VALUE;
    private final Handler d = new Handler(Looper.getMainLooper());
    private final ViewTreeObserver.OnPreDrawListener f = new ViewTreeObserver.OnPreDrawListener() { // from class: com.applovin.impl.sdk.u.2
        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            u.this.b();
            return true;
        }
    };

    public interface a {
        void onLogVisibilityImpression();
    }

    public u(final MaxAdView maxAdView, h hVar, final a aVar) {
        this.a = hVar;
        this.e = new Runnable() { // from class: com.applovin.impl.sdk.u.1
            @Override // java.lang.Runnable
            public void run() {
                if (u.this.h == null) {
                    return;
                }
                u uVar = u.this;
                if (!uVar.b(maxAdView, uVar.h)) {
                    u.this.b();
                } else {
                    u.this.a();
                    aVar.onLogVisibilityImpression();
                }
            }
        };
    }

    private void a(Context context, View view) {
        View viewA = com.applovin.impl.sdk.utils.n.a(context, view);
        if (viewA == null) {
            this.a.w().a("VisibilityTracker", "Unable to set view tree observer due to no root view.");
            return;
        }
        ViewTreeObserver viewTreeObserver = viewA.getViewTreeObserver();
        if (!viewTreeObserver.isAlive()) {
            this.a.w().c("VisibilityTracker", "Unable to set view tree observer since the view tree observer is not alive.");
        } else {
            this.g = new WeakReference<>(viewTreeObserver);
            viewTreeObserver.addOnPreDrawListener(this.f);
        }
    }

    private boolean a(View view, View view2) {
        return view2 != null && view2.getVisibility() == 0 && view.getParent() != null && view2.getWidth() > 0 && view2.getHeight() > 0 && view2.getGlobalVisibleRect(this.c) && ((long) (AppLovinSdkUtils.pxToDp(view2.getContext(), this.c.width()) * AppLovinSdkUtils.pxToDp(view2.getContext(), this.c.height()))) >= ((long) this.i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.d.postDelayed(this.e, ((Long) this.a.a(com.applovin.impl.sdk.b.b.cw)).longValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(View view, View view2) {
        if (!a(view, view2)) {
            return false;
        }
        if (this.k == Long.MIN_VALUE) {
            this.k = SystemClock.uptimeMillis();
        }
        return SystemClock.uptimeMillis() - this.k >= this.j;
    }

    public void a() {
        synchronized (this.b) {
            this.d.removeMessages(0);
            if (this.g != null) {
                ViewTreeObserver viewTreeObserver = this.g.get();
                if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeOnPreDrawListener(this.f);
                }
                this.g.clear();
            }
            this.k = Long.MIN_VALUE;
            this.h = null;
        }
    }

    public void a(Context context, com.applovin.impl.mediation.b.b bVar) {
        synchronized (this.b) {
            a();
            this.h = bVar.k();
            this.i = bVar.p();
            this.j = bVar.r();
            a(context, this.h);
        }
    }
}
