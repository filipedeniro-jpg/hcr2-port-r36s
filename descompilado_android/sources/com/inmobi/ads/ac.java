package com.inmobi.ads;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.rendering.InMobiAdActivity;
import com.inmobi.rendering.RenderView;
import com.tonyodev.fetch.FetchConst;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: InterstitialAdUnit.java */
/* JADX INFO: loaded from: classes2.dex */
public class ac extends i {
    private static final String A = InMobiInterstitial.class.getSimpleName();
    private static final String z = "ac";
    private int B;
    private ArrayList<WeakReference<i.b>> C;
    boolean y;

    @Override // com.inmobi.ads.i
    public final String b() {
        return "int";
    }

    @Override // com.inmobi.ads.i
    public final String c() {
        return null;
    }

    @Override // com.inmobi.ads.i
    public final void n() {
    }

    /* synthetic */ ac(Context context, long j, i.b bVar, byte b2) {
        this(context, j, bVar);
    }

    static /* synthetic */ boolean b(ac acVar) throws b, c {
        return acVar.b(false);
    }

    /* JADX INFO: compiled from: InterstitialAdUnit.java */
    public static final class a {
        static final Map<bi, ac> a = new HashMap();

        @NonNull
        static ac b(Context context, bi biVar, i.b bVar) {
            return new ac(context, biVar.a, bVar, (byte) 0);
        }

        @NonNull
        public static ac a(Context context, bi biVar, i.b bVar) {
            long j = biVar.a;
            ac acVar = a.get(biVar);
            if (acVar != null) {
                if (acVar.h()) {
                    String unused = ac.z;
                    StringBuilder sb = new StringBuilder("Found expired adUnit for placement(");
                    sb.append(j);
                    sb.append("), thus clearing it.");
                    acVar.v();
                }
                acVar.a(context);
                if (bVar != null) {
                    acVar.a(bVar);
                }
                return acVar;
            }
            ac acVar2 = new ac(context, j, bVar, (byte) 0);
            a.put(biVar, acVar2);
            return acVar2;
        }
    }

    private ac(Context context, long j, i.b bVar) {
        super(context, j, bVar);
        this.B = 0;
        this.y = false;
        this.C = new ArrayList<>(1);
        InMobiAdRequest.MonetizationContext monetizationContext = InMobiAdRequest.MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY;
        super.a(InMobiAdRequest.MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY);
    }

    @Override // com.inmobi.ads.i
    @NonNull
    protected final RenderView k() {
        RenderView renderViewK = super.k();
        if (this.y) {
            renderViewK.a();
        }
        return renderViewK;
    }

    /* JADX WARN: Code duplicated, block: B:42:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:44:0x00d4 A[RETURN] */
    final boolean d(i.b bVar) {
        boolean z2;
        if (m()) {
            a("MissingDependency");
            a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.MISSING_REQUIRED_DEPENDENCIES), true);
            return false;
        }
        this.w = false;
        if (bVar == null) {
            g();
            return false;
        }
        if (-1 == g(bVar)) {
            this.C.add(new WeakReference<>(bVar));
            if (!com.inmobi.commons.core.utilities.d.a()) {
                a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.NETWORK_UNREACHABLE), true);
                return false;
            }
            int i = this.a;
            if (i == 1) {
                Logger.a(Logger.InternalLogLevel.ERROR, A, "An ad load is already in progress. Please wait for the load to complete before requesting for another ad for placement id: " + this.d);
            } else if (i != 2) {
                if (i != 4) {
                    if (i == 7 || i == 8) {
                        Logger.a(Logger.InternalLogLevel.ERROR, A, "An ad is currently being viewed by the user. Please wait for the user to close the ad before requesting for another ad for placement id: " + this.d);
                        InMobiAdRequestStatus inMobiAdRequestStatus = new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.AD_ACTIVE);
                        a(inMobiAdRequestStatus);
                        int iG = g(bVar);
                        if (iG != -1) {
                            this.C.remove(iG);
                        }
                        if (bVar != null) {
                            bVar.a(inMobiAdRequestStatus);
                        }
                    } else {
                        z2 = false;
                    }
                    if (z2) {
                        return true;
                    }
                    d("AdLoadRequested");
                    return false;
                }
                if (bVar != null) {
                    bVar.a(true);
                }
            } else if (com.my.target.ai.a.da.equals(this.m)) {
                Logger.a(Logger.InternalLogLevel.ERROR, A, "An ad load is already in progress. Please wait for the load to complete before requesting for another ad for placement id: " + this.d);
            } else if (bVar != null) {
                bVar.a(true);
            }
            z2 = true;
            if (z2) {
                return true;
            }
            d("AdLoadRequested");
            return false;
        }
        a(bVar, "ART", "LoadInProgress");
        return false;
    }

    @UiThread
    public final void e(i.b bVar) {
        if (d(bVar)) {
            super.n();
        }
    }

    @UiThread
    private int g(i.b bVar) {
        i.b bVar2;
        int i = -1;
        for (int i2 = 0; i2 < this.C.size(); i2++) {
            WeakReference<i.b> weakReference = this.C.get(i2);
            if (weakReference != null && (bVar2 = weakReference.get()) != null && bVar2.equals(bVar)) {
                i = i2;
            }
        }
        return i;
    }

    private void S() {
        this.s.post(new Runnable() { // from class: com.inmobi.ads.ac.1
            @Override // java.lang.Runnable
            public final void run() {
                for (int i = 0; i < ac.this.C.size(); i++) {
                    i.b bVar = (i.b) ((WeakReference) ac.this.C.get(i)).get();
                    if (bVar == null) {
                        ac.this.g();
                    } else {
                        ac.this.a(bVar, "VAR", "");
                        ac.this.a(bVar, "ARF", "");
                        bVar.a(true);
                        bVar.a();
                        bVar.a(ac.this);
                    }
                }
                ac.this.C.clear();
            }
        });
    }

    @Override // com.inmobi.ads.i
    public final boolean a(com.inmobi.ads.a aVar) {
        if (!super.a(aVar)) {
            b(aVar);
            return false;
        }
        if (!(aVar instanceof bc)) {
            return true;
        }
        bc bcVar = (bc) aVar;
        com.inmobi.ads.cache.d.a();
        com.inmobi.ads.cache.a aVarB = com.inmobi.ads.cache.d.b(bcVar.l);
        if (aVarB == null || !aVarB.a()) {
            return false;
        }
        this.j = new bx(aVarB.e, bcVar.m, bcVar.n, bcVar.h(), bcVar.i(), this.g.m);
        return true;
    }

    @Override // com.inmobi.ads.i
    protected final void b(com.inmobi.ads.a aVar) {
        i().a(aVar);
    }

    @Override // com.inmobi.ads.i
    public final void v() {
        super.v();
    }

    final void f(i.b bVar) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            a(bVar, "AVRR", "");
            Logger.a(Logger.InternalLogLevel.ERROR, InMobiInterstitial.class.getSimpleName(), "Please ensure that you call show() on the UI thread");
            return;
        }
        if (bVar == null) {
            g();
            return;
        }
        if (!O()) {
            a(bVar, "AVRR", "");
            Logger.a(Logger.InternalLogLevel.ERROR, z, "Ad Load is not complete. Please wait for the Ad to be in a ready state before calling show.");
            HashMap map = new HashMap();
            map.put("errorCode", "ShowIntBeforeReady");
            c("AdShowFailed", map);
            bVar.b();
            return;
        }
        if (com.inmobi.commons.core.utilities.b.e.e()) {
            a(bVar);
            this.a = 7;
            if (com.my.target.ai.a.da.equals(this.m)) {
                AdContainer adContainerJ = j();
                if (h()) {
                    i(bVar);
                    if (adContainerJ != null) {
                        adContainerJ.destroy();
                        return;
                    }
                    return;
                }
                h(bVar);
                return;
            }
            final WeakReference weakReference = new WeakReference(bVar);
            this.p.execute(new Runnable() { // from class: com.inmobi.ads.ac.2
                @Override // java.lang.Runnable
                public final void run() {
                    final i.b bVar2 = (i.b) weakReference.get();
                    if (bVar2 != null) {
                        try {
                            if (ac.b(ac.this)) {
                                ac.this.a(bVar2, ac.this.h, new Runnable() { // from class: com.inmobi.ads.ac.2.1
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        AdContainer adContainerJ2 = ac.this.j();
                                        RenderView renderView = ac.this.u;
                                        if (renderView != null) {
                                            if (!(adContainerJ2 instanceof ah)) {
                                                ac.this.i(bVar2);
                                            } else {
                                                ah ahVar = (ah) adContainerJ2;
                                                ahVar.w = renderView;
                                                ahVar.y = ac.this.r;
                                            }
                                        }
                                        ac.this.h(bVar2);
                                    }
                                }, Looper.getMainLooper());
                            } else {
                                Logger.a(Logger.InternalLogLevel.ERROR, InMobiInterstitial.class.getSimpleName(), "Unable to Show Ad, canShowAd Failed");
                                ac.this.i(bVar2);
                            }
                        } catch (b e) {
                            Logger.a(Logger.InternalLogLevel.ERROR, InMobiInterstitial.class.getSimpleName(), e.getMessage());
                            ac.this.i(bVar2);
                        } catch (c e2) {
                            Logger.a(Logger.InternalLogLevel.ERROR, InMobiInterstitial.class.getSimpleName(), e2.getMessage());
                            ac.this.i(bVar2);
                        }
                    }
                }
            });
            return;
        }
        super.v();
        bVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @UiThread
    public void h(i.b bVar) {
        d("ShowInt");
        boolean zT = T();
        if (bVar == null) {
            g();
        } else {
            if (zT) {
                bVar.c();
                return;
            }
            this.a = 3;
            a(bVar, "AVRR", "");
            bVar.b();
        }
    }

    private boolean T() {
        try {
            StringBuilder sb = new StringBuilder(">>> Starting ");
            sb.append(InMobiAdActivity.class.getSimpleName());
            sb.append(" to display interstitial ad ...");
            AdContainer adContainerJ = j();
            if (adContainerJ != null && !"unknown".equals(adContainerJ.getMarkupType())) {
                int iA = InMobiAdActivity.a(adContainerJ);
                Intent intent = new Intent(a(), (Class<?>) InMobiAdActivity.class);
                intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_INDEX", iA);
                intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", 102);
                intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_TYPE", com.my.target.ai.a.da.equals(this.m) ? 200 : FetchConst.NETWORK_WIFI);
                intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_IS_FULL_SCREEN", true);
                com.inmobi.commons.a.a.a(a(), intent);
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, InMobiInterstitial.class.getSimpleName(), "Cannot show ad; SDK encountered an unexpected error");
            new StringBuilder("Encountered unexpected error while showing ad: ").append(e.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return false;
        }
    }

    @Override // com.inmobi.ads.i
    protected final AdContainer.RenderingProperties.PlacementType d() {
        return AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_FULLSCREEN;
    }

    @Override // com.inmobi.ads.i, com.inmobi.ads.h.a
    public final void a(final long j, final InMobiAdRequestStatus inMobiAdRequestStatus) {
        this.s.post(new Runnable() { // from class: com.inmobi.ads.ac.3
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    if (j == ac.this.d) {
                        Logger.a(Logger.InternalLogLevel.DEBUG, ac.A, "Failed to fetch ad for placement id: " + j + ", reason phrase available in onAdLoadFailed callback.");
                        for (int i = 0; i < ac.this.C.size(); i++) {
                            i.b bVar = (i.b) ((WeakReference) ac.this.C.get(i)).get();
                            if (bVar != null) {
                                if (i < ac.this.C.size() - 1) {
                                    ac.this.a(bVar, "VAR", "");
                                }
                                ac.this.a(bVar, "ARN", "");
                            } else {
                                ac.this.g();
                            }
                        }
                        ac.this.a(inMobiAdRequestStatus, true);
                    }
                } catch (Exception e) {
                    Logger.a(Logger.InternalLogLevel.ERROR, "[InMobi]", "Unable to load Ad; SDK encountered an unexpected error");
                    String unused = ac.z;
                    new StringBuilder("onAdFetchFailed with error: ").append(e.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                }
            }
        });
    }

    @Override // com.inmobi.ads.i
    @UiThread
    public final void c(long j, @NonNull com.inmobi.ads.a aVar) {
        try {
            super.c(j, aVar);
            Logger.a(Logger.InternalLogLevel.DEBUG, A, "Interstitial ad successfully fetched for placement id: " + this.d);
            if (j == this.d && this.a == 2) {
                a(true, k());
                try {
                    a(null, this.h, null, null);
                } catch (Exception e) {
                    C();
                    a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.INTERNAL_ERROR), false);
                    Logger.a(Logger.InternalLogLevel.ERROR, InMobiInterstitial.class.getSimpleName(), "Unable to load ad; SDK encountered an internal error");
                    new StringBuilder("Loading ad markup into container encountered an unexpected error: ").append(e.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                }
            }
        } catch (Exception e2) {
            Logger.a(Logger.InternalLogLevel.ERROR, A, "Unable to load ad; SDK encountered an internal error");
            new StringBuilder("Handling ad fetch successful encountered an unexpected error: ").append(e2.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
        }
    }

    @Override // com.inmobi.ads.i
    @UiThread
    public final void a(long j, boolean z2, @NonNull com.inmobi.ads.a aVar) {
        try {
            super.a(j, z2, aVar);
            if (j == this.d) {
                if (1 == this.a && z2) {
                    this.a = 2;
                    if (super.a(aVar)) {
                        a(f(), "ARF", "");
                        c(aVar);
                        if (aVar.j) {
                            this.t = true;
                            I();
                            return;
                        }
                        Iterator<WeakReference<i.b>> it = this.C.iterator();
                        while (it.hasNext()) {
                            i.b bVar = it.next().get();
                            if (bVar != null) {
                                bVar.a(true);
                            } else {
                                g();
                            }
                        }
                        return;
                    }
                    Iterator<WeakReference<i.b>> it2 = this.C.iterator();
                    while (it2.hasNext()) {
                        i.b bVar2 = it2.next().get();
                        if (bVar2 != null) {
                            bVar2.a(false);
                        } else {
                            g();
                        }
                    }
                    return;
                }
                if (4 == this.a || 5 == this.a || 2 == this.a) {
                    this.a = 0;
                    Iterator<WeakReference<i.b>> it3 = this.C.iterator();
                    while (it3.hasNext()) {
                        i.b bVar3 = it3.next().get();
                        if (bVar3 != null) {
                            bVar3.a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.AD_NO_LONGER_AVAILABLE));
                        } else {
                            g();
                        }
                        this.C.clear();
                    }
                }
            }
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, InMobiInterstitial.class.getSimpleName(), "Unable to load ad; SDK encountered an internal error");
            new StringBuilder("Handling ad availability change event encountered an unexpected error: ").append(e.getMessage());
        }
    }

    @Override // com.inmobi.ads.i
    @UiThread
    public final void b(long j, boolean z2) {
        super.b(j, z2);
        if (!z2) {
            if (j == this.d) {
                if (2 == this.a || 5 == this.a) {
                    this.a = 0;
                    a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.AD_NO_LONGER_AVAILABLE), false);
                    return;
                }
                return;
            }
            return;
        }
        if (j == this.d && 2 == this.a) {
            if (!this.t) {
                K();
            } else {
                this.v = true;
                J();
            }
        }
    }

    @Override // com.inmobi.ads.i
    final void K() {
        F();
        this.a = 5;
        for (int i = 0; i < this.C.size(); i++) {
            i.b bVar = this.C.get(i).get();
            if (bVar == null) {
                g();
            } else {
                if (i < this.C.size() - 1) {
                    a(bVar, "VAR", "");
                    a(bVar, "ARF", "");
                }
                bVar.a(this);
            }
        }
        this.C.clear();
    }

    @Override // com.inmobi.ads.i
    public final void x() {
        super.x();
        if (this.a == 4) {
            C();
            Logger.a(Logger.InternalLogLevel.DEBUG, A, "Successfully loaded Interstitial ad markup in the WebView for placement id: " + this.d);
            s();
            K();
        }
    }

    @Override // com.inmobi.ads.i, com.inmobi.rendering.RenderView.a
    public final void a(RenderView renderView) {
        super.a(renderView);
        if (this.a == 2) {
            this.a = 4;
            L();
        }
    }

    @Override // com.inmobi.ads.i
    public final void z() {
        super.z();
        if (this.a == 4) {
            C();
            this.a = 3;
            Logger.a(Logger.InternalLogLevel.DEBUG, A, "Failed to load the Interstitial markup in the WebView for placement id: " + this.d);
            a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.INTERNAL_ERROR), false);
        }
    }

    @Override // com.inmobi.ads.i, com.inmobi.rendering.RenderView.a
    public final synchronized void c(RenderView renderView) {
        super.c(renderView);
        b(f());
    }

    @Override // com.inmobi.ads.i, com.inmobi.rendering.RenderView.a
    public final synchronized void d(RenderView renderView) {
        super.d(renderView);
        c(f());
    }

    @Override // com.inmobi.ads.i
    public final void E() {
        b("RenderTimeOut");
        if (this.k != null) {
            i().a(this.k);
        }
        if (4 == this.a || 2 == this.a) {
            this.a = 3;
            Logger.a(Logger.InternalLogLevel.DEBUG, z, "Failed to load the Interstitial markup in the webview due to time out for placement id: " + this.d);
            a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.INTERNAL_ERROR), false);
        }
    }

    @Override // com.inmobi.ads.i
    final void L() {
        Iterator<WeakReference<i.b>> it = this.C.iterator();
        while (it.hasNext()) {
            i.b bVar = it.next().get();
            if (bVar != null) {
                bVar.a(true);
            } else {
                g();
            }
        }
    }

    /* JADX INFO: compiled from: InterstitialAdUnit.java */
    private final class b extends Exception {
        public b(String str) {
            super(str);
        }
    }

    /* JADX INFO: compiled from: InterstitialAdUnit.java */
    private final class c extends Exception {
        public c(String str) {
            super(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(final i.b bVar) {
        a(bVar, "AVFB", "");
        this.s.post(new Runnable() { // from class: com.inmobi.ads.ac.4
            @Override // java.lang.Runnable
            public final void run() {
                ac acVar = ac.this;
                acVar.a = 0;
                i.b bVar2 = bVar;
                if (bVar2 != null) {
                    bVar2.b();
                } else {
                    acVar.g();
                }
            }
        });
    }

    @Override // com.inmobi.ads.i
    public final void a(InMobiAdRequest.MonetizationContext monetizationContext) {
        super.a(InMobiAdRequest.MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY);
    }

    @Override // com.inmobi.ads.i
    public final InMobiAdRequest.MonetizationContext l() {
        return InMobiAdRequest.MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.inmobi.ads.i
    public final void q() {
        this.s.post(new Runnable() { // from class: com.inmobi.ads.ac.5
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    ac.this.w = false;
                    if (!ac.this.p()) {
                        ac.super.q();
                    } else {
                        ac.this.c("IllegalState");
                    }
                } catch (Exception e) {
                    Logger.a(Logger.InternalLogLevel.ERROR, ac.A, "Unable to Prefetch ad; SDK encountered an unexpected error");
                    String unused = ac.z;
                    new StringBuilder("Prefetch failed with unexpected error: ").append(e.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                }
            }
        });
    }

    @Override // com.inmobi.ads.i
    protected final int r() {
        if (1 == this.a) {
            Logger.a(Logger.InternalLogLevel.ERROR, A, "An ad load is already in progress. Please wait for the load to complete before requesting for another ad for placement id: " + this.d);
            return 2;
        }
        if (5 == this.a) {
            if (R()) {
                return super.r();
            }
            return 1;
        }
        return super.r();
    }

    private boolean R() {
        try {
            if (com.my.target.ai.a.da.equals(this.m)) {
                if (!h()) {
                    S();
                    return false;
                }
                super.v();
                return true;
            }
            if (b(true)) {
                S();
                return false;
            }
            return true;
        } catch (b | c unused) {
        }
    }

    public final boolean O() {
        return this.a == 5;
    }

    @Override // com.inmobi.ads.i
    @UiThread
    final void a(InMobiAdRequestStatus inMobiAdRequestStatus, boolean z2) {
        if (this.a == 1 && z2) {
            this.a = 3;
        }
        Iterator<WeakReference<i.b>> it = this.C.iterator();
        while (it.hasNext()) {
            i.b bVar = it.next().get();
            if (bVar != null) {
                bVar.a(inMobiAdRequestStatus);
            } else {
                g();
            }
        }
        this.C.clear();
        a(inMobiAdRequestStatus);
        super.v();
    }

    @Override // com.inmobi.ads.i
    final void b(i.b bVar) {
        if (this.a == 7) {
            this.B++;
            if (this.B == 1) {
                d("AdRendered");
                Logger.a(Logger.InternalLogLevel.DEBUG, A, "Successfully displayed Interstitial for placement id: " + this.d);
                if (bVar != null) {
                    bVar.d();
                    return;
                } else {
                    g();
                    return;
                }
            }
            this.a = 8;
            return;
        }
        if (this.a == 8) {
            this.B++;
        }
    }

    @Override // com.inmobi.ads.i
    final void c(i.b bVar) {
        if (this.a == 8) {
            this.B--;
            if (this.B == 1) {
                this.a = 7;
                return;
            }
            return;
        }
        if (this.a == 7) {
            this.B--;
            d("IntClosed");
            super.v();
            Logger.a(Logger.InternalLogLevel.DEBUG, A, "Interstitial ad dismissed for placement id: " + this.d);
            if (bVar != null) {
                bVar.e();
            } else {
                g();
            }
        }
    }

    private boolean b(boolean z2) throws b, c {
        com.inmobi.ads.a aVarC;
        String str = this.k;
        if (str == null) {
            return false;
        }
        if (z2) {
            i();
            com.inmobi.ads.b.b();
            aVarC = d.c(str);
        } else {
            h hVarI = i();
            h.c();
            d dVar = hVarI.b;
            com.inmobi.ads.a aVarC2 = d.c(str);
            if (aVarC2 != null) {
                d.a(str);
            }
            hVarI.a(hVarI.c);
            aVarC = aVarC2;
        }
        if (aVarC == null) {
            throw new b("No Cached Ad found for AdUnit");
        }
        if (a(aVarC)) {
            return true;
        }
        throw new c("No Cached Asset for AdUnit");
    }

    @Override // com.inmobi.ads.i
    @UiThread
    public final void H() {
        if (1 == this.a) {
            this.a = 9;
            if (this.q != null) {
                this.q.a(this);
            }
            Iterator<WeakReference<i.b>> it = this.C.iterator();
            while (it.hasNext()) {
                i.b bVar = it.next().get();
                if (bVar != null) {
                    e(bVar);
                    return;
                }
                g();
            }
        }
    }

    @Override // com.inmobi.ads.i
    @UiThread
    public final void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
        if (1 == this.a) {
            this.a = 3;
            if (this.q != null) {
                this.q.a(this, inMobiAdRequestStatus);
            }
            if (this.C.size() > 0) {
                a(inMobiAdRequestStatus, false);
            }
        }
    }
}
