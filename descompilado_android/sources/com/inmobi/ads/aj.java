package com.inmobi.ads;

import android.content.Context;
import android.os.Looper;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.annotation.VisibleForTesting;
import com.facebook.internal.AnalyticsEvents;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.rendering.RenderView;
import java.lang.ref.WeakReference;
import java.util.Map;

/* JADX INFO: compiled from: NativeAdUnit.java */
/* JADX INFO: loaded from: classes2.dex */
public final class aj extends i {
    static final String y = "aj";
    boolean A;
    private boolean B;
    private a C;
    private int D;
    WeakReference<View> z;

    @Override // com.inmobi.ads.i
    public final String b() {
        return AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE;
    }

    @Override // com.inmobi.ads.i
    public final String c() {
        return null;
    }

    @NonNull
    public static aj a(@NonNull Context context, bi biVar, i.b bVar, int i) {
        i iVar = com.inmobi.ads.c.a.a.get(biVar);
        aj ajVar = iVar instanceof aj ? (aj) iVar : null;
        if (ajVar != null && 1 == i) {
            throw new IllegalStateException("There's already a pre-loading going on for the same placementID");
        }
        if (ajVar == null) {
            new StringBuilder("Creating new adUnit for placement-ID : ").append(biVar.a);
            ajVar = new aj(context, biVar.a, bVar);
            if (i != 0) {
                com.inmobi.ads.c.a.a.put(biVar, ajVar);
            }
        } else {
            new StringBuilder("Found pre-fetching adUnit for placement-ID : ").append(biVar.a);
            ajVar.a(context);
            com.inmobi.ads.c.a.a.remove(biVar);
            ajVar.B = true;
        }
        ajVar.a(bVar);
        ajVar.a(biVar.f);
        return ajVar;
    }

    private aj(@NonNull Context context, long j, i.b bVar) {
        super(context, j, bVar);
        this.A = false;
        this.D = 0;
    }

    @Override // com.inmobi.ads.i
    public final void a(Context context) {
        super.a(context);
        b(context);
    }

    @VisibleForTesting
    private void b(Context context) {
        AdContainer adContainerJ = j();
        if (adContainerJ instanceof ah) {
            ((ah) adContainerJ).a(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.inmobi.ads.i
    @UiThread
    public final void q() {
        try {
            if (p()) {
                c("IllegalState");
            } else {
                super.a(false);
            }
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, "InMobi", "Unable to Prefetch ad; SDK encountered an unexpected error");
            new StringBuilder("Prefetch failed with unexpected error: ").append(e.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
    }

    @Override // com.inmobi.ads.i
    @UiThread
    public final void a(boolean z) {
        try {
            if (p()) {
                c("IllegalState");
            } else {
                super.a(z);
            }
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, "InMobi", "Unable to Prefetch ad; SDK encountered an unexpected error");
            new StringBuilder("Prefetch failed with unexpected error: ").append(e.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
    }

    @Override // com.inmobi.ads.i
    protected final void b(a aVar) {
        i().a(aVar);
    }

    @Override // com.inmobi.ads.i
    protected final void b(long j, boolean z) {
        i.b bVarF;
        super.b(j, z);
        boolean z2 = false;
        if (!z) {
            if (j == this.d) {
                if (2 == this.a || 5 == this.a) {
                    this.a = 0;
                    if (f() != null) {
                        f().a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.AD_NO_LONGER_AVAILABLE));
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        if (j == this.d && 2 == this.a && (bVarF = f()) != null) {
            a aVar = this.C;
            if (aVar != null) {
                if (aVar instanceof bc) {
                    bc bcVar = (bc) aVar;
                    com.inmobi.ads.cache.d.a();
                    com.inmobi.ads.cache.a aVarB = com.inmobi.ads.cache.d.b(bcVar.l);
                    if (aVarB != null && aVarB.a()) {
                        this.j = new bx(aVarB.e, bcVar.m, bcVar.n, bcVar.h(), bcVar.i(), this.g.m);
                        z2 = true;
                    }
                } else {
                    z2 = true;
                }
            }
            if (!z2) {
                bVarF.a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.INTERNAL_ERROR));
                return;
            }
            if (a() != null) {
                if (!this.t) {
                    K();
                } else {
                    this.v = true;
                    J();
                }
            }
        }
    }

    @Override // com.inmobi.ads.i
    protected final void c(long j, @NonNull a aVar) {
        super.c(j, aVar);
        this.C = aVar;
        if (a(aVar)) {
            if (this.r == 0 && !aVar.j) {
                a(true, (RenderView) null);
            } else {
                c(aVar);
            }
            if (aVar.j) {
                this.t = true;
                I();
            }
        }
    }

    @Override // com.inmobi.ads.i
    public final boolean a(a aVar) {
        if (super.a(aVar)) {
            return true;
        }
        b(aVar);
        return false;
    }

    @Override // com.inmobi.ads.i
    final void K() {
        a(f(), this.h, new Runnable() { // from class: com.inmobi.ads.aj.2
            @Override // java.lang.Runnable
            public final void run() {
                if (2 == aj.this.a) {
                    aj ajVar = aj.this;
                    ajVar.a = 5;
                    AdContainer adContainerJ = ajVar.j();
                    RenderView renderView = aj.this.u;
                    i.b bVarF = aj.this.f();
                    if (!(adContainerJ instanceof ah)) {
                        if (bVarF != null) {
                            String unused = aj.y;
                            bVarF.a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.INTERNAL_ERROR));
                            return;
                        }
                        return;
                    }
                    ah ahVar = (ah) adContainerJ;
                    ahVar.w = renderView;
                    ahVar.y = aj.this.r;
                    aj.this.F();
                    if (bVarF != null) {
                        String unused2 = aj.y;
                        bVarF.a();
                    }
                }
            }
        }, Looper.getMainLooper());
    }

    public final void O() {
        try {
            super.v();
            this.h = null;
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, "InMobi", "Could not destroy native ad; SDK encountered unexpected error");
            new StringBuilder("SDK encountered unexpected error in destroying native ad unit; ").append(e.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
    }

    @Override // com.inmobi.ads.i
    protected final AdContainer.RenderingProperties.PlacementType d() {
        return AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE;
    }

    @Override // com.inmobi.ads.i
    @NonNull
    protected final Map<String, String> e() {
        Map<String, String> mapE = super.e();
        mapE.put("a-parentViewWidth", String.valueOf(com.inmobi.commons.core.utilities.b.c.a().a));
        mapE.put("a-productVersion", "NS-1.0.0-20160411");
        mapE.put("trackerType", "url_ping");
        return mapE;
    }

    @Override // com.inmobi.ads.i
    public final void n() {
        if (this.w) {
            return;
        }
        i.b bVarF = f();
        if (m()) {
            a("MissingDependency");
            if (bVarF != null) {
                bVarF.a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.MISSING_REQUIRED_DEPENDENCIES));
                return;
            }
            return;
        }
        if (1 == this.a || 2 == this.a) {
            Logger.a(Logger.InternalLogLevel.ERROR, y, "An ad load is already in progress. Please wait for the load to complete before requesting for another ad");
            if (this.B) {
                return;
            }
            a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.REQUEST_PENDING), false);
            return;
        }
        Logger.a(Logger.InternalLogLevel.DEBUG, y, "Fetching a Native ad for placement id: " + this.d);
        if (5 == this.a && !h()) {
            a(bVarF, "VAR", "");
            a(bVarF, "ARF", "");
            if (bVarF != null) {
                b(a());
                bVarF.a();
                return;
            }
            return;
        }
        super.n();
    }

    @Override // com.inmobi.ads.i
    protected final int r() {
        if (1 == this.a || 2 == this.a) {
            Logger.a(Logger.InternalLogLevel.ERROR, "InMobi", "An ad load is already in progress. Please wait for the load to complete before requesting for another ad for placement id: " + this.d);
            this.s.post(new Runnable() { // from class: com.inmobi.ads.aj.1
                @Override // java.lang.Runnable
                public final void run() {
                    aj.this.a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.REQUEST_PENDING), false);
                }
            });
            return 2;
        }
        return super.r();
    }

    public final boolean P() {
        return this.a == 5;
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
                this.B = false;
                n();
            }
        }
    }

    @Override // com.inmobi.ads.i
    public final void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
        if (1 == this.a) {
            this.a = 3;
            i.b bVarF = f();
            if (this.n || bVarF == null) {
                if (this.q != null) {
                    this.q.a(this, inMobiAdRequestStatus);
                }
            } else {
                this.B = false;
                a(bVarF, "VAR", "");
                a(bVarF, "ARN", "");
                bVarF.a(inMobiAdRequestStatus);
            }
        }
    }

    @Override // com.inmobi.ads.i
    final void b(i.b bVar) {
        if (this.a == 5) {
            this.a = 7;
        } else if (this.a == 7) {
            this.D++;
        }
        Logger.a(Logger.InternalLogLevel.DEBUG, "InMobi", "Successfully displayed fullscreen for placement id: " + this.d);
        if (this.D == 0) {
            if (bVar != null) {
                bVar.d();
            } else {
                g();
            }
        }
    }

    @Override // com.inmobi.ads.i
    final void c(i.b bVar) {
        if (this.a == 7) {
            int i = this.D;
            if (i > 0) {
                this.D = i - 1;
            } else {
                this.a = 5;
            }
        }
        Logger.a(Logger.InternalLogLevel.DEBUG, "InMobi", "Successfully dismissed fullscreen for placement id: " + this.d);
        if (this.D == 0 && this.a == 5) {
            if (bVar != null) {
                bVar.e();
            } else {
                g();
            }
        }
    }
}
