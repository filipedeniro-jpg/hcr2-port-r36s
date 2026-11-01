package com.inmobi.ads;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import com.facebook.appevents.AppEventsConstants;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.rendering.RenderView;
import java.util.Map;

/* JADX INFO: compiled from: BannerAdUnit.java */
/* JADX INFO: loaded from: classes2.dex */
public class p extends i implements Application.ActivityLifecycleCallbacks {
    private static final String B = "p";
    private static final String C = InMobiBanner.class.getSimpleName();
    public String A;
    private boolean D;
    private int E;
    public boolean y;
    boolean z;

    @Override // com.inmobi.ads.i
    public final String b() {
        return com.my.target.ai.a.cW;
    }

    @Override // com.inmobi.ads.i
    protected final void b(a aVar) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @NonNull
    public static p a(@NonNull Context context, bi biVar, i.b bVar, int i) {
        i iVar = com.inmobi.ads.c.a.a.get(biVar);
        p pVar = iVar instanceof p ? (p) iVar : null;
        if (pVar != null && 1 == i) {
            throw new IllegalStateException("There's already a pre-loading going on for the same placementID");
        }
        if (pVar == null) {
            new StringBuilder("Creating new adUnit for placement-ID : ").append(biVar.a);
            pVar = new p(context, biVar.a, bVar);
            if (i != 0) {
                com.inmobi.ads.c.a.a.put(biVar, pVar);
            }
        } else {
            new StringBuilder("Found pre-fetching adUnit for placement-ID : ").append(biVar.a);
            pVar.a(context);
            com.inmobi.ads.c.a.a.remove(biVar);
            pVar.D = true;
        }
        pVar.a(bVar);
        pVar.a(biVar.f);
        return pVar;
    }

    private p(Context context, long j, i.b bVar) {
        super(context, j, bVar);
        this.y = true;
        this.z = false;
        this.E = 0;
        if (context instanceof Activity) {
            ((Activity) context).getApplication().registerActivityLifecycleCallbacks(this);
        }
    }

    @Override // com.inmobi.ads.i
    public final void a(Context context) {
        super.a(context);
        if (context instanceof Activity) {
            ((Activity) context).getApplication().registerActivityLifecycleCallbacks(this);
        }
    }

    final void O() {
        RenderView renderView = (RenderView) j();
        if (renderView == null) {
            return;
        }
        this.z = true;
        renderView.a();
    }

    public final void b(boolean z) {
        if (z) {
            Logger.a(Logger.InternalLogLevel.DEBUG, C, "Initiating Banner refresh for placement id: " + this.d);
        }
        Logger.a(Logger.InternalLogLevel.DEBUG, C, "Fetching a Banner ad for placement id: " + this.d);
        this.w = false;
        this.y = z;
        if (1 == this.a) {
            Logger.a(Logger.InternalLogLevel.ERROR, B, "An ad load is already in progress. Please wait for the load to complete before requesting for another ad");
            if (this.D) {
                return;
            }
            a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.REQUEST_PENDING), false);
            return;
        }
        if (2 == this.a || 8 == this.a) {
            a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.AD_ACTIVE), false);
            Logger.a(Logger.InternalLogLevel.ERROR, C, "An ad is currently being viewed by the user. Please wait for the user to close the ad before requesting for another ad for placement id: " + this.d);
            return;
        }
        super.n();
    }

    @Override // com.inmobi.ads.i
    public final void q() {
        super.q();
    }

    @Override // com.inmobi.ads.i
    @NonNull
    protected final RenderView k() {
        RenderView renderViewK = super.k();
        if (this.z) {
            renderViewK.a();
        }
        return renderViewK;
    }

    @Override // com.inmobi.ads.i
    public final String c() {
        return this.A;
    }

    @Override // com.inmobi.ads.i
    protected final AdContainer.RenderingProperties.PlacementType d() {
        return AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE;
    }

    @Override // com.inmobi.ads.i
    @NonNull
    protected final Map<String, String> e() {
        Map<String, String> mapE = super.e();
        mapE.put("u-rt", this.y ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        mapE.put("mk-ad-slot", this.A);
        return mapE;
    }

    @Override // com.inmobi.ads.i
    @UiThread
    public final void c(long j, @NonNull a aVar) {
        try {
            super.c(j, aVar);
            Logger.a(Logger.InternalLogLevel.DEBUG, C, "Banner ad fetch successful for placement id: " + this.d);
            if (j == this.d && this.a == 2) {
                a(false, k());
                try {
                    Logger.a(Logger.InternalLogLevel.DEBUG, C, "Started loading banner ad markup in WebView for placement id: " + this.d);
                    a((i.b) null, this.h, (Runnable) null, (Looper) null);
                } catch (Exception e) {
                    C();
                    if (f() != null) {
                        f().a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.INTERNAL_ERROR));
                    }
                    Logger.a(Logger.InternalLogLevel.ERROR, C, "Unable to load ad; SDK encountered an internal error");
                    new StringBuilder("Loading ad markup into container encountered an unexpected error: ").append(e.getMessage());
                }
            }
        } catch (Exception e2) {
            Logger.a(Logger.InternalLogLevel.ERROR, C, "Unable to load ad; SDK encountered an internal error");
            new StringBuilder("Handling ad fetch successful encountered an unexpected error: ").append(e2.getMessage());
        }
    }

    @Override // com.inmobi.ads.i, com.inmobi.rendering.RenderView.a
    public final void a(RenderView renderView) {
        try {
            super.a(renderView);
            if (this.a == 2) {
                C();
                this.a = 4;
                F();
                Logger.a(Logger.InternalLogLevel.DEBUG, C, "Successfully loaded Banner ad markup in the WebView for placement id: " + this.d);
                if (f() != null) {
                    f().a();
                }
                s();
            }
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, C, "Unable to load ad; SDK encountered an internal error");
            new StringBuilder("Loading ad markup into container encountered an unexpected error: ").append(e.getMessage());
        }
    }

    @Override // com.inmobi.ads.i, com.inmobi.rendering.RenderView.a
    public final void b(RenderView renderView) {
        try {
            super.b(renderView);
            if (this.a == 4) {
                this.a = 7;
                d("AdRendered");
            }
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, C, "Unable to load ad; SDK encountered an internal error");
            new StringBuilder("BannerAdUnit.onRenderViewVisible threw unexpected error: ").append(e.getMessage());
        }
    }

    @Override // com.inmobi.ads.i, com.inmobi.rendering.RenderView.a
    public final synchronized void c(RenderView renderView) {
        try {
            super.c(renderView);
            if (this.a == 7) {
                this.E++;
                this.a = 8;
                Logger.a(Logger.InternalLogLevel.DEBUG, C, "Successfully displayed banner ad for placement Id : " + this.d);
                if (f() != null) {
                    f().d();
                }
            } else if (this.a == 8) {
                this.E++;
            }
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, C, "Unable to display ad; SDK encountered an internal error");
            new StringBuilder("BannerAdUnit.onAdScreenDisplayed threw unexpected error: ").append(e.getMessage());
        }
    }

    @Override // com.inmobi.ads.i, com.inmobi.rendering.RenderView.a
    public final synchronized void d(RenderView renderView) {
        try {
            super.d(renderView);
            if (this.a == 8) {
                int i = this.E - 1;
                this.E = i;
                if (i == 0) {
                    this.a = 7;
                    if (f() != null) {
                        f().e();
                    }
                }
            }
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, C, "Unable to dismiss ad; SDK encountered an internal error");
            new StringBuilder("BannerAdUnit.onAdScreenDismissed threw unexpected error: ").append(e.getMessage());
        }
    }

    public final void S() {
        if (a() instanceof Activity) {
            ((Activity) a()).getApplication().unregisterActivityLifecycleCallbacks(this);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        Context contextA = a();
        if (contextA == null || !contextA.equals(activity)) {
            return;
        }
        R();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        Context contextA = a();
        if (contextA == null || !contextA.equals(activity)) {
            return;
        }
        Q();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        Context contextA = a();
        if (contextA == null || !contextA.equals(activity)) {
            return;
        }
        ((Activity) contextA).getApplication().unregisterActivityLifecycleCallbacks(this);
        v();
    }

    @Override // com.inmobi.ads.i
    protected final int r() {
        if (1 == this.a || 2 == this.a) {
            this.s.post(new Runnable() { // from class: com.inmobi.ads.p.1
                @Override // java.lang.Runnable
                public final void run() {
                    p.this.a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.REQUEST_PENDING), false);
                }
            });
            Logger.a(Logger.InternalLogLevel.ERROR, C, "An ad load is already in progress. Please wait for the load to complete before requesting for another ad for placement id: " + this.d);
            return 2;
        }
        if (this.a == 8) {
            this.s.post(new Runnable() { // from class: com.inmobi.ads.p.2
                @Override // java.lang.Runnable
                public final void run() {
                    p.this.a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.AD_ACTIVE), false);
                }
            });
            Logger.a(Logger.InternalLogLevel.ERROR, C, "An ad is currently being viewed by the user. Please wait for the user to close the ad before requesting for another ad for placement id: " + this.d);
            return 3;
        }
        return super.r();
    }

    @Override // com.inmobi.ads.i
    public final void H() {
        if (1 == this.a) {
            this.a = 9;
            if (this.n) {
                if (this.q != null) {
                    this.q.a(this);
                }
            } else {
                this.D = false;
                b(false);
            }
        }
    }

    @Override // com.inmobi.ads.i
    public final void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
        if (1 == this.a) {
            this.a = 3;
            if (!this.n) {
                i.b bVarF = f();
                if (bVarF == null) {
                    if (this.q != null) {
                        this.q.a(this, inMobiAdRequestStatus);
                        return;
                    }
                    return;
                } else {
                    this.D = false;
                    a(bVarF, "VAR", "");
                    a(bVarF, "ARN", "");
                    bVarF.a(inMobiAdRequestStatus);
                    return;
                }
            }
            if (this.q != null) {
                this.q.a(this, inMobiAdRequestStatus);
            }
        }
    }

    final boolean P() {
        return this.a == 8;
    }

    public final void Q() {
        AdContainer adContainerJ;
        ca viewableAd;
        int i = this.a;
        if ((i != 4 && i != 7 && i != 8) || (adContainerJ = j()) == null || (viewableAd = adContainerJ.getViewableAd()) == null) {
            return;
        }
        viewableAd.d();
    }

    public final void R() {
        AdContainer adContainerJ;
        ca viewableAd;
        int i = this.a;
        if ((i != 4 && i != 7 && i != 8) || (adContainerJ = j()) == null || (viewableAd = adContainerJ.getViewableAd()) == null) {
            return;
        }
        viewableAd.a(new View[0]);
    }
}
