package com.inmobi.ads;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.ShareConstants;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.rendering.InMobiAdActivity;
import com.inmobi.rendering.RenderView;
import com.integralads.avid.library.inmobi.session.AbstractAvidAdSession;
import com.ironsource.sdk.constants.Constants;
import com.tonyodev.fetch.FetchConst;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: NativeAdContainer.java */
/* JADX INFO: loaded from: classes2.dex */
public class ah implements Application.ActivityLifecycleCallbacks, AdContainer {
    private static final String B = "ah";
    private au E;
    private o G;
    private ah H;
    private ah K;
    private RenderView.a L;
    private ExecutorService N;
    protected ao a;

    @NonNull
    public AdContainer.RenderingProperties b;

    @NonNull
    com.inmobi.ads.c c;

    @NonNull
    final String d;

    @NonNull
    final long e;
    final String f;

    @NonNull
    final boolean g;

    @NonNull
    final String h;

    @Nullable
    protected Set<bq> i;
    protected ca j;
    protected boolean k;
    public boolean l;
    protected boolean m;

    @NonNull
    ah n;

    @Nullable
    protected c o;

    @Nullable
    WeakReference<Activity> q;
    RenderView w;
    RenderView x;
    int y;

    @Nullable
    public List<RenderView> z;

    @NonNull
    private Set<Integer> C = new HashSet();

    @NonNull
    private List<ak> D = new ArrayList();

    @NonNull
    protected WeakReference<Context> p = new WeakReference<>(null);
    private int F = -1;
    boolean r = false;
    int s = 0;
    boolean t = true;
    boolean u = false;
    private ak I = null;
    private String J = null;
    Intent v = null;
    private final AdContainer.a M = new AdContainer.a() { // from class: com.inmobi.ads.ah.1
        @Override // com.inmobi.ads.AdContainer.a
        public final void a() {
            String unused = ah.B;
            c cVarE = ah.this.e();
            if (cVarE != null) {
                cVarE.a();
            }
        }

        @Override // com.inmobi.ads.AdContainer.a
        public final void a(Object obj) {
            c cVarE;
            if (ah.this.l() == null || (cVarE = ah.this.e()) == null) {
                return;
            }
            cVarE.b();
        }

        @Override // com.inmobi.ads.AdContainer.a
        public final void b(Object obj) {
            c cVarE = ah.this.e();
            if (cVarE != null) {
                cVarE.f();
            }
        }
    };
    private Runnable O = new Runnable() { // from class: com.inmobi.ads.ah.2
        @Override // java.lang.Runnable
        public final void run() {
            if (!ah.this.l && AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE == ah.this.b.a && ah.this.a.c) {
                String unused = ah.B;
                ah.a(ah.this);
            }
        }
    };
    ai.a A = new ai.a() { // from class: com.inmobi.ads.ah.3
        @Override // com.inmobi.ads.ai.a
        public final void a(View view, boolean z) {
            ah.this.a(z);
        }
    };

    /* JADX INFO: compiled from: NativeAdContainer.java */
    public interface c {
        void a();

        void a(String str, Map<String, Object> map);

        void a(Map<String, String> map);

        void a(boolean z);

        void b();

        void c();

        void d();

        void e();

        void f();

        void g();

        void h();

        void i();
    }

    @Override // com.inmobi.ads.AdContainer
    public final void a() {
    }

    @Override // com.inmobi.ads.AdContainer
    public String getMarkupType() {
        return "inmobiJson";
    }

    @Override // com.inmobi.ads.AdContainer
    @Nullable
    public View getVideoContainerView() {
        return null;
    }

    boolean n() {
        return false;
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

    ah(@NonNull Context context, @NonNull AdContainer.RenderingProperties renderingProperties, @NonNull ao aoVar, @NonNull String str, @NonNull String str2, @Nullable Set<bq> set, @NonNull com.inmobi.ads.c cVar, long j, boolean z, String str3) {
        this.l = false;
        this.b = renderingProperties;
        this.a = aoVar;
        this.d = str;
        this.e = j;
        this.g = z;
        this.f = str3;
        this.h = str2;
        a((AdContainer) this);
        this.k = false;
        this.l = false;
        this.c = cVar;
        this.G = new o();
        if (set != null) {
            this.i = new HashSet(set);
        }
        this.a.d.z = System.currentTimeMillis();
        a(context);
        this.y = -1;
        this.N = Executors.newSingleThreadExecutor();
        this.N.submit(this.O);
    }

    @Override // com.inmobi.ads.AdContainer
    public void setRequestedScreenOrientation() {
        Activity activityL = l();
        if (activityL == null || this.l) {
            return;
        }
        int i = this.a.a;
        if (i == 1) {
            activityL.setRequestedOrientation(1);
        } else if (i == 2) {
            activityL.setRequestedOrientation(0);
        } else {
            activityL.setRequestedOrientation(activityL.getRequestedOrientation());
        }
    }

    @Override // com.inmobi.ads.AdContainer
    public AdContainer.RenderingProperties getRenderingProperties() {
        return this.b;
    }

    @Override // com.inmobi.ads.AdContainer
    @Nullable
    public AdContainer.a getFullScreenEventsListener() {
        return this.M;
    }

    final void a(Context context) {
        B();
        this.p = new WeakReference<>(context);
        if (context instanceof Activity) {
            ((Activity) context).getApplication().registerActivityLifecycleCallbacks(this);
        }
    }

    @Nullable
    public final Context d() {
        return this.p.get();
    }

    public final void a(@NonNull AdContainer adContainer) {
        if (adContainer instanceof ah) {
            this.n = (ah) adContainer;
        }
    }

    @Nullable
    public final c e() {
        return this.o;
    }

    public final void a(@NonNull c cVar) {
        this.o = cVar;
    }

    @Nullable
    public final View f() {
        ca caVar = this.j;
        if (caVar == null) {
            return null;
        }
        return caVar.b();
    }

    final void g() {
        Map<String, String> mapA = a(this.a.d);
        a(1, mapA);
        a(2, mapA);
    }

    @Override // com.inmobi.ads.AdContainer
    @SuppressLint({"SwitchIntDef"})
    public ca getViewableAd() {
        Context contextJ = j();
        if (this.j == null && contextJ != null) {
            g();
            this.j = new aa(contextJ, this, new cc(this, this.w));
            Set<bq> set = this.i;
            if (set != null) {
                if (contextJ instanceof Activity) {
                    try {
                        Activity activity = (Activity) contextJ;
                        for (bq bqVar : set) {
                            int i = bqVar.a;
                            if (i != 1) {
                                if (i == 3 && this.y == 0) {
                                    AbstractAvidAdSession abstractAvidAdSession = (AbstractAvidAdSession) bqVar.b.get("avidAdSession");
                                    boolean z = bqVar.b.containsKey("deferred") && ((Boolean) bqVar.b.get("deferred")).booleanValue();
                                    if (abstractAvidAdSession != null) {
                                        this.j = new v(this, activity, this.j, abstractAvidAdSession, z);
                                    }
                                }
                            } else if (this.y == 0) {
                                this.j = new ad(this, activity, this.j, bqVar.b);
                            } else {
                                bqVar.b.put("zMoatIID", UUID.randomUUID().toString());
                                this.j = new ae(activity, this.j, bqVar.b);
                            }
                        }
                    } catch (Exception e) {
                        new StringBuilder("Exception occurred while creating the Display viewable ad : ").append(e.getMessage());
                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                    }
                } else {
                    HashMap map = new HashMap();
                    map.put("type", AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
                    map.put("impId", this.d);
                    com.inmobi.commons.core.e.b.a();
                    com.inmobi.commons.core.e.b.a("ads", "TrackersForService", map);
                }
            }
        }
        return this.j;
    }

    @NonNull
    public final ao h() {
        return this.a;
    }

    @Override // com.inmobi.ads.AdContainer
    public final boolean c() {
        return this.l;
    }

    @Override // com.inmobi.ads.AdContainer
    public void destroy() {
        if (this.l) {
            return;
        }
        this.l = true;
        this.F = -1;
        ah ahVar = this.H;
        if (ahVar != null) {
            ahVar.b();
        }
        this.l = true;
        this.o = null;
        au auVarA = A();
        if (auVarA != null) {
            n nVar = auVarA.c;
            Iterator<n.a> it = nVar.a.iterator();
            while (it.hasNext()) {
                it.next().a.cancel();
            }
            nVar.a.clear();
            auVarA.b();
        }
        this.D.clear();
        ca caVar = this.j;
        if (caVar != null) {
            caVar.d();
            this.j.e();
        }
        B();
        this.p.clear();
        WeakReference<Activity> weakReference = this.q;
        if (weakReference != null) {
            weakReference.clear();
        }
        List<RenderView> list = this.z;
        if (list != null) {
            list.clear();
        }
        this.a = null;
        this.w = null;
        ah ahVar2 = this.K;
        if (ahVar2 != null) {
            ahVar2.destroy();
            this.K = null;
        }
    }

    boolean i() {
        return AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE == this.b.a && l() != null;
    }

    @Nullable
    protected final Context j() {
        return (AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_FULLSCREEN == this.b.a || i()) ? l() : this.p.get();
    }

    protected final boolean k() {
        return this.k;
    }

    private ak a(@NonNull ak akVar, @NonNull ao aoVar, @NonNull String str) {
        if (com.inmobi.commons.core.utilities.b.a(this.p.get(), str)) {
            return akVar;
        }
        String[] strArrSplit = str.split("\\|");
        ak akVarB = aoVar.b(strArrSplit[0]);
        if (akVarB == null) {
            return b(aoVar.f, akVar);
        }
        if (akVarB.equals(akVar)) {
            return null;
        }
        if (1 != strArrSplit.length && 2 != strArrSplit.length) {
            if (strArrSplit.length > 2) {
                akVarB.m = ao.a(strArrSplit[2]);
            }
            return akVarB;
        }
        akVarB.m = 1;
        return akVarB;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:26:0x005d  */
    private static int a(String str) {
        switch (str.toLowerCase(Locale.US).trim()) {
            case "skip":
                return 2;
            case "reload":
            case "replay":
                return 3;
            case "exit":
                return 1;
            case "fullscreen":
                return 4;
            case "play":
                return 5;
            case "":
            default:
                return 0;
        }
    }

    final void a(@NonNull ak akVar, boolean z) {
        bt btVarF;
        if (!this.a.j || this.l) {
            return;
        }
        ak akVarB = b(this.a, akVar);
        if (akVarB != null) {
            Map<String, String> mapA = a(akVarB);
            akVarB.i = akVar.i;
            if (ShareConstants.VIDEO_URL.equals(akVarB.b) || akVarB.h) {
                ca caVar = this.j;
                if (caVar != null) {
                    caVar.a(4);
                }
                int i = akVarB.i;
                if (i != 0) {
                    String str = akVarB.r;
                    if (this.t && 4 == i) {
                        return;
                    }
                    if (2 == akVarB.m && (btVarF = ((be) akVarB).b().f()) != null && btVarF.e != null && !btVarF.e.trim().isEmpty()) {
                        str = btVarF.e;
                    }
                    if (!com.inmobi.commons.core.utilities.b.a(C(), str)) {
                        StringBuilder sb = new StringBuilder("Invalid url:");
                        sb.append(str);
                        sb.append(" will use fallback");
                        a("DeeplinkFailed", str);
                        str = akVarB.s;
                        if (!com.inmobi.commons.core.utilities.b.a(C(), str)) {
                            a("DeeplinkFallbackFailed", str);
                            return;
                        }
                    }
                    String strA = com.inmobi.commons.core.utilities.d.a(str, mapA);
                    if (this.u && !z) {
                        ah ahVarF = f(this);
                        if (ahVarF != null) {
                            c cVar = ahVarF.o;
                            if (cVar != null) {
                                if (1 == i && com.inmobi.commons.core.utilities.b.a(strA)) {
                                    cVar.c();
                                } else {
                                    cVar.g();
                                }
                            }
                            this.I = akVarB;
                            this.J = strA;
                            return;
                        }
                        return;
                    }
                    a(akVarB, i, strA);
                    return;
                }
                return;
            }
            return;
        }
        HashMap map = new HashMap();
        map.put("url", akVar.r);
        a("DeeplinkFailed", map);
        HashMap map2 = new HashMap();
        map2.put("url", akVar.s);
        a("DeeplinkFallbackFailed", map2);
    }

    final void a(int i, ak akVar) {
        if (this.C.contains(Integer.valueOf(i)) || this.l) {
            return;
        }
        w();
        a(i, (am) akVar);
    }

    @Override // com.inmobi.ads.AdContainer
    public void setFullScreenActivityContext(Activity activity) {
        this.q = new WeakReference<>(activity);
    }

    @Nullable
    public final Activity l() {
        WeakReference<Activity> weakReference = this.q;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    private void w() {
        am amVarA = this.a.a(0);
        if (this.C.contains(0) || amVarA == null) {
            return;
        }
        a(0, amVarA);
    }

    private void a(@NonNull ak akVar, @Nullable Map<String, String> map) {
        a("ReportClick", new HashMap());
        if (2 == akVar.m) {
            bt btVarF = ((be) akVar).b().f();
            if (btVarF != null && (btVarF.e != null || akVar.r == null)) {
                if (btVarF.d.size() > 0) {
                    Iterator<NativeTracker> it = btVarF.a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_CLICK).iterator();
                    while (it.hasNext()) {
                        ak.a(it.next(), map);
                    }
                    return;
                }
                return;
            }
            akVar.a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_CLICK, map);
            return;
        }
        akVar.a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_CLICK, map);
    }

    final Map<String, String> a(@NonNull ak akVar) {
        ao aoVar;
        HashMap map = new HashMap(3);
        if (!this.l && (aoVar = this.a) != null) {
            map.put("$LTS", String.valueOf(aoVar.d.z));
            am amVarA = ao.a(akVar);
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (amVarA != null && 0 != amVarA.z) {
                jCurrentTimeMillis = amVarA.z;
            }
            map.put("$STS", String.valueOf(jCurrentTimeMillis));
            map.put("$TS", String.valueOf(System.currentTimeMillis()));
        }
        return map;
    }

    private void b(@Nullable ak akVar, @Nullable Map<String, String> map) {
        if (akVar == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", akVar.g);
            jSONObject.put("asset", akVar.f);
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
        HashMap map2 = new HashMap();
        map2.put("type", AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
        map2.put("impId", this.d);
        map2.put("pageJson", jSONObject);
        com.inmobi.commons.core.e.b.a();
        com.inmobi.commons.core.e.b.a("ads", "PageRendered", map2);
        akVar.a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_PAGE_VIEW, map);
    }

    final void a(boolean z) {
        if (z) {
            x();
        } else {
            y();
        }
    }

    static NativeTimerView b(View view) {
        if (view != null) {
            return (NativeTimerView) view.findViewWithTag("timerView");
        }
        return null;
    }

    protected static void c(View view) {
        NativeTimerView nativeTimerViewB = b(view);
        if (nativeTimerViewB == null || nativeTimerViewB.c == null || !nativeTimerViewB.c.isRunning()) {
            return;
        }
        nativeTimerViewB.b = nativeTimerViewB.c.getCurrentPlayTime();
        nativeTimerViewB.c.cancel();
    }

    protected static void d(View view) {
        NativeTimerView nativeTimerViewB = b(view);
        if (nativeTimerViewB == null || nativeTimerViewB.c == null || nativeTimerViewB.c.isRunning()) {
            return;
        }
        nativeTimerViewB.c.setCurrentPlayTime(nativeTimerViewB.b);
        nativeTimerViewB.c.start();
    }

    private void x() {
        au auVarA = A();
        if (auVarA != null) {
            n nVar = auVarA.c;
            if (nVar.b) {
                return;
            }
            nVar.b = true;
            nVar.a(nVar.a);
        }
    }

    private void y() {
        au auVarA = A();
        if (auVarA != null) {
            n nVar = auVarA.c;
            if (nVar.b) {
                nVar.b = false;
                for (n.a aVar : nVar.a) {
                    ValueAnimator valueAnimator = (ValueAnimator) aVar.a;
                    aVar.b = valueAnimator.getCurrentPlayTime();
                    if (valueAnimator.getAnimatedFraction() == 1.0d) {
                        aVar.c = true;
                    }
                    valueAnimator.cancel();
                }
            }
        }
    }

    final void m() {
        ah ahVarF = f(this);
        if (ahVarF == null) {
            return;
        }
        c cVar = ahVarF.o;
        if (cVar != null) {
            cVar.c();
        }
        this.N.submit(new Runnable() { // from class: com.inmobi.ads.ah.5
            @Override // java.lang.Runnable
            public final void run() {
                if (ah.this.H == null) {
                    ah.a(ah.this);
                }
                int iA = InMobiAdActivity.a((AdContainer) ah.this.H);
                Intent intent = new Intent(ah.this.p.get(), (Class<?>) InMobiAdActivity.class);
                intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_INDEX", iA);
                intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", 102);
                intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_IS_FULL_SCREEN", true);
                intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_TYPE", FetchConst.NETWORK_WIFI);
                if (!ah.this.u) {
                    com.inmobi.commons.a.a.a(ah.this.p.get(), intent);
                } else {
                    ah.this.v = intent;
                }
            }
        });
    }

    final void o() {
        if (n()) {
            this.r = true;
            c cVar = this.o;
            if (cVar == null || this.a.g == null) {
                return;
            }
            cVar.a(this.a.g);
        }
    }

    private void a(be beVar, ah ahVar) {
        bt btVarF = beVar.b().f();
        if (btVarF == null || !btVarF.g) {
            return;
        }
        Iterator<NativeTracker> it = btVarF.a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_END_CARD_CLOSE).iterator();
        while (it.hasNext()) {
            be.a(it.next(), a(beVar));
        }
        btVarF.g = false;
        ahVar.a("EndCardClosed", ahVar.z());
    }

    private Map<String, Object> z() {
        List<ak> listC = this.K.a.c("WEBVIEW");
        bf bfVar = listC.size() > 0 ? (bf) listC.get(0) : null;
        String str = bfVar == null ? "Static" : "Rich";
        HashMap map = new HashMap();
        map.put("type", str);
        map.put("dataType", bfVar == null ? "URL" : bfVar.z);
        return map;
    }

    final void a(String str, Map<String, Object> map) {
        ah ahVarF = f(this);
        if (ahVarF == null) {
            StringBuilder sb = new StringBuilder("Target container is null. Discarding telemetry event : [");
            sb.append(str);
            sb.append(" ]");
            return;
        }
        c cVar = ahVarF.o;
        if (cVar != null) {
            cVar.a(str, map);
            return;
        }
        StringBuilder sb2 = new StringBuilder("InteractionCallback is null. Discarding telemetry event : [");
        sb2.append(str);
        sb2.append(" ]");
    }

    private static ah f(@Nullable ah ahVar) {
        ah ahVar2;
        while (ahVar != null) {
            if (ahVar.l() != null || ahVar == (ahVar2 = ahVar.n)) {
                return ahVar;
            }
            ahVar = ahVar2;
        }
        return null;
    }

    @Nullable
    private au A() {
        ca caVar = this.j;
        at atVar = caVar == null ? null : (at) caVar.f();
        if (atVar != null) {
            this.E = atVar.a;
        }
        return this.E;
    }

    @Override // com.inmobi.ads.AdContainer
    public o getApkDownloader() {
        return this.G;
    }

    public final void a(@NonNull RenderView renderView) {
        if (this.z == null) {
            this.z = new LinkedList();
        }
        if (this.z.contains(renderView)) {
            return;
        }
        this.z.add(renderView);
    }

    private void a(String str, String str2) {
        HashMap map = new HashMap();
        map.put("url", str2);
        a(str, map);
    }

    private void a(@NonNull ak akVar, int i, String str) {
        c cVar;
        if (1 == i) {
            if (!com.inmobi.commons.core.utilities.b.a(str)) {
                a(str, (String) null, akVar);
                return;
            }
            Context context = this.p.get();
            if (context != null) {
                if (l() == null && (cVar = this.o) != null) {
                    cVar.c();
                }
                InMobiAdActivity.a((RenderView) null);
                InMobiAdActivity.a(u());
                Intent intent = new Intent(context, (Class<?>) InMobiAdActivity.class);
                intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", 100);
                intent.putExtra("com.inmobi.rendering.InMobiAdActivity.IN_APP_BROWSER_URL", str);
                intent.putExtra(Constants.PLACEMENT_ID, this.e);
                intent.putExtra(Constants.RequestParameters.CREATIVE_ID, this.f);
                intent.putExtra("impressionId", this.d);
                intent.putExtra("allowAutoRedirection", this.g);
                com.inmobi.commons.a.a.a(context, intent);
                return;
            }
            return;
        }
        a(str, akVar.s, akVar);
    }

    private void a(@NonNull String str, @Nullable String str2, @NonNull ak akVar) {
        String strA;
        ah ahVarF;
        if (this.p.get() == null || (strA = com.inmobi.commons.core.utilities.b.a(this.p.get(), str, str2)) == null || (ahVarF = f(this)) == null) {
            return;
        }
        c cVar = ahVarF.o;
        if (cVar != null && !this.u) {
            cVar.g();
        }
        if (strA.equals(str2)) {
            akVar.a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_FALLBACK_URL, a(akVar));
        }
    }

    private void B() {
        Context context = this.p.get();
        if (context instanceof Activity) {
            ((Activity) context).getApplication().unregisterActivityLifecycleCallbacks(this);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        Context contextC = C();
        if (contextC == null || !contextC.equals(activity)) {
            return;
        }
        p();
    }

    final void p() {
        this.m = false;
        d(f());
        x();
        ca caVar = this.j;
        if (caVar != null) {
            caVar.a(C(), 0);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        Context contextC = C();
        if (contextC == null || !contextC.equals(activity)) {
            return;
        }
        q();
    }

    private Context C() {
        Activity activityL = l();
        return activityL == null ? this.p.get() : activityL;
    }

    void q() {
        this.m = true;
        c(f());
        y();
        ca caVar = this.j;
        if (caVar != null) {
            caVar.a(C(), 1);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        ca caVar = this.j;
        if (caVar != null) {
            caVar.a(activity, 2);
        }
    }

    final void r() {
        ak akVar = this.I;
        if (akVar != null && this.J != null) {
            a(akVar, akVar.i, this.J);
        } else {
            if (this.v == null || this.p.get() == null) {
                return;
            }
            com.inmobi.commons.a.a.a(this.p.get(), this.v);
        }
    }

    @Nullable
    final RenderView s() {
        RenderView renderView = this.w;
        return renderView == null ? this.x : renderView;
    }

    final void t() {
        new a(this).start();
    }

    /* JADX INFO: compiled from: NativeAdContainer.java */
    final class a extends Thread {
        private WeakReference<ah> b;

        a(@NonNull ah ahVar) {
            this.b = new WeakReference<>(ahVar);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            if (ah.this.l() == null) {
                String unused = ah.B;
                return;
            }
            ah ahVar = this.b.get();
            if (ahVar == null || ahVar.l) {
                return;
            }
            try {
                ao aoVarH = ahVar.h();
                if (ah.this.l() != null && aoVarH.e.length() != 0) {
                    String unused2 = ah.B;
                    JSONObject jSONObjectA = aoVarH.a();
                    if (jSONObjectA == null) {
                        return;
                    }
                    ao aoVar = new ao(ah.this.b.a, jSONObjectA, aoVarH, ah.this.b.a == AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE, ah.this.c, null);
                    if (!aoVar.c()) {
                        String unused3 = ah.B;
                        return;
                    }
                    ah ahVarA = b.a(ah.this.l(), new AdContainer.RenderingProperties(AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE), aoVar, ah.this.d, ah.this.h, null, ah.this.c, ah.this.e, ah.this.g, ah.this.f);
                    String unused4 = ah.B;
                    ahVarA.a((AdContainer) ahVar);
                    ahVarA.w = ahVar.w;
                    ahVar.K = ahVarA;
                    return;
                }
                String unused5 = ah.B;
            } catch (Exception e) {
                String unused6 = ah.B;
                new StringBuilder("Encountered unexpected error in EndCardBuilder: ").append(e.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        }
    }

    @NonNull
    final RenderView.a u() {
        if (this.L == null) {
            this.L = new RenderView.a() { // from class: com.inmobi.ads.ah.7
                @Override // com.inmobi.rendering.RenderView.a
                public final void a(RenderView renderView) {
                }

                @Override // com.inmobi.rendering.RenderView.a
                public final void a(HashMap<Object, Object> map) {
                }

                @Override // com.inmobi.rendering.RenderView.a
                public final void b(RenderView renderView) {
                }

                @Override // com.inmobi.rendering.RenderView.a
                public final void w() {
                }

                @Override // com.inmobi.rendering.RenderView.a
                public final void y() {
                }

                @Override // com.inmobi.rendering.RenderView.a
                public final void A() {
                    c cVarE = ah.this.e();
                    if (cVarE != null) {
                        cVarE.a();
                    }
                }

                @Override // com.inmobi.rendering.RenderView.a
                public final void c(RenderView renderView) {
                    c cVarE = ah.this.e();
                    if (cVarE != null) {
                        cVarE.b();
                    }
                }

                @Override // com.inmobi.rendering.RenderView.a
                public final void d(RenderView renderView) {
                    c cVarE = ah.this.e();
                    if (cVarE != null) {
                        cVarE.f();
                    }
                }

                @Override // com.inmobi.rendering.RenderView.a
                public final void b(HashMap<Object, Object> map) {
                    c cVarE = ah.this.e();
                    if (cVarE != null) {
                        cVarE.e();
                    }
                }

                @Override // com.inmobi.rendering.RenderView.a
                public final void B() {
                    c cVarE = ah.this.e();
                    if (cVarE != null) {
                        cVarE.g();
                    }
                }

                @Override // com.inmobi.rendering.RenderView.a
                public final void b(String str, Map<String, Object> map) {
                    ah.this.a(str, map);
                }

                @Override // com.inmobi.rendering.RenderView.a
                public final void G() {
                    c cVarE = ah.this.e();
                    if (cVarE == null || AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE != ah.this.b.a) {
                        return;
                    }
                    cVarE.c();
                }
            };
        }
        return this.L;
    }

    /* JADX INFO: compiled from: NativeAdContainer.java */
    static final class b {
        static ah a(@NonNull Context context, @NonNull AdContainer.RenderingProperties renderingProperties, @NonNull ao aoVar, @NonNull String str, @NonNull String str2, @Nullable Set<bq> set, @NonNull com.inmobi.ads.c cVar, long j, boolean z, String str3) {
            if (new ArrayList(aoVar.h.keySet()).contains(ShareConstants.VIDEO_URL)) {
                return new bd(context, renderingProperties, aoVar, str, str2, set, cVar, j, z, str3);
            }
            return new ah(context, renderingProperties, aoVar, str, str2, set, cVar, j, z, str3);
        }
    }

    @Override // com.inmobi.ads.AdContainer
    public final void a(int i, Map<String, String> map) {
        if (this.l) {
            return;
        }
        if (i == 1) {
            this.a.d.a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_LOAD, map);
        } else if (i != 2) {
            if (i != 3) {
            }
        } else {
            this.a.d.a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_CLIENT_FILL, map);
        }
    }

    private void a(int i, @NonNull am amVar) {
        if (this.l) {
            return;
        }
        this.C.add(Integer.valueOf(i));
        amVar.z = System.currentTimeMillis();
        if (this.k) {
            b(amVar, a(amVar));
        } else {
            this.D.add(amVar);
        }
    }

    @Nullable
    private ak b(@Nullable ao aoVar, @NonNull ak akVar) {
        if (aoVar == null) {
            return null;
        }
        String str = akVar.r;
        String str2 = akVar.s;
        ak akVarA = str != null ? a(akVar, aoVar, str) : null;
        if (akVarA == null && str2 != null) {
            akVarA = a(akVar, aoVar, str2);
        }
        if (akVarA != null) {
            StringBuilder sb = new StringBuilder("Referenced asset (");
            sb.append(akVarA.d);
            sb.append(")");
        }
        return akVarA;
    }

    @Nullable
    protected static ak a(@Nullable ao aoVar, @NonNull ak akVar) {
        while (aoVar != null) {
            String str = akVar.j;
            if (str != null && str.length() != 0) {
                String[] strArrSplit = str.split("\\|");
                if (1 != strArrSplit.length) {
                    ak akVarB = aoVar.b(strArrSplit[0]);
                    if (akVarB != null) {
                        if (akVarB.equals(akVar)) {
                            return null;
                        }
                        akVarB.l = a(strArrSplit[1]);
                        StringBuilder sb = new StringBuilder("Referenced asset (");
                        sb.append(akVarB.d);
                        sb.append(")");
                        return akVarB;
                    }
                    aoVar = aoVar.f;
                } else {
                    akVar.l = a(strArrSplit[0]);
                    return akVar;
                }
            } else {
                akVar.l = 0;
                return akVar;
            }
        }
        return null;
    }

    final void a(@Nullable View view, @NonNull ak akVar) {
        c cVar;
        if (this.l) {
            return;
        }
        w();
        ak akVarB = b(this.a, akVar);
        if (akVarB != null) {
            Map<String, String> mapA = a(akVarB);
            a(akVarB, mapA);
            if (!akVarB.equals(akVar)) {
                a(akVar, mapA);
            }
        } else {
            a(akVar, a(akVar));
        }
        ah ahVarF = f(this);
        if (ahVarF == null) {
            return;
        }
        if (!akVar.r.trim().isEmpty() && (cVar = ahVarF.o) != null) {
            cVar.e();
        }
        ak akVarA = a(this.a, akVar);
        if (akVarA != null) {
            if (view != null && ShareConstants.VIDEO_URL.equals(akVarA.b) && 5 == akVarA.l) {
                view.setVisibility(4);
                akVar.x = 4;
            }
            b(akVarA);
        }
    }

    void a(View view) {
        c cVar;
        if (this.k || this.l) {
            return;
        }
        this.k = true;
        this.a.d.a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_RENDER, a(this.a.d));
        HashMap map = new HashMap();
        map.put("type", AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_FULLSCREEN == getRenderingProperties().a ? "int" : AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
        map.put("clientRequestId", this.h);
        map.put("impId", this.d);
        com.inmobi.commons.core.e.b.a();
        com.inmobi.commons.core.e.b.a("ads", "AdRendered", map);
        com.inmobi.commons.core.e.b.a();
        com.inmobi.commons.core.e.b.a("ads", "ViewableBeaconFired", map);
        w();
        for (ak akVar : this.D) {
            b(akVar, a(akVar));
        }
        this.D.clear();
        ah ahVarF = f(this);
        if (ahVarF == null || (cVar = ahVarF.o) == null) {
            return;
        }
        cVar.d();
    }

    @TargetApi(15)
    protected void b(@NonNull ak akVar) {
        NativeVideoWrapper nativeVideoWrapper;
        int i = akVar.l;
        if (i != 0) {
            if (i == 1) {
                try {
                    if (this.w != null) {
                        this.w.d("window.imraid.broadcastEvent('close');");
                    }
                    b();
                    return;
                } catch (Exception e) {
                    new StringBuilder("Encountered unexpected error in handling exit action on video: ").append(e.getMessage());
                    Logger.a(Logger.InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in exiting video");
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                    return;
                }
            }
            if (i != 3) {
                if (i == 4) {
                    try {
                        if (AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE == this.b.a) {
                            m();
                            return;
                        }
                        return;
                    } catch (Exception e2) {
                        new StringBuilder("Encountered unexpected error in handling fullscreen action ").append(e2.getMessage());
                        Logger.a(Logger.InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in launching fullscreen ad");
                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                        return;
                    }
                }
                if (i != 5) {
                    this.r = true;
                    RenderView renderView = this.w;
                    if (renderView != null && renderView != null) {
                        renderView.d("window.imraid.broadcastEvent('skip');");
                    }
                    c(f());
                    c(akVar);
                    return;
                }
                return;
            }
            try {
                if (this.w != null) {
                    this.w.d("window.imraid.broadcastEvent('replay');");
                }
                if (f() != null) {
                    View viewF = f();
                    ViewGroup viewGroup = (ViewGroup) viewF.getParent();
                    if (viewGroup != null) {
                        viewGroup.removeView(viewF);
                    }
                }
                ah ahVar = this.n;
                NativeTimerView nativeTimerViewB = b(ahVar.f());
                if (nativeTimerViewB != null && nativeTimerViewB.c != null && nativeTimerViewB.c.isRunning()) {
                    nativeTimerViewB.c.setCurrentPlayTime(nativeTimerViewB.a * 1000);
                    nativeTimerViewB.a(1.0f);
                }
                if (!ShareConstants.VIDEO_URL.equals(akVar.b)) {
                    new StringBuilder("Action 3 not valid for asset of type: ").append(akVar.b);
                    return;
                }
                if (!(ahVar instanceof bd) || (nativeVideoWrapper = (NativeVideoWrapper) ahVar.getVideoContainerView()) == null) {
                    return;
                }
                NativeVideoView videoView = nativeVideoWrapper.getVideoView();
                be beVar = (be) videoView.getTag();
                if (beVar != null) {
                    if (beVar.a()) {
                        videoView.e();
                    } else {
                        videoView.d();
                    }
                } else if (AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_FULLSCREEN == this.b.a) {
                    videoView.e();
                } else {
                    videoView.d();
                }
                a(beVar, ahVar);
                videoView.start();
            } catch (Exception e3) {
                new StringBuilder("Encountered unexpected error in handling replay action on video: ").append(e3.getMessage());
                Logger.a(Logger.InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in replaying video");
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e3));
            }
        }
    }

    @Override // com.inmobi.ads.AdContainer
    public final void b() {
        ah ahVarF;
        try {
            if (this.l || (ahVarF = f(this)) == null) {
                return;
            }
            ahVarF.o();
            InMobiAdActivity.a((Object) ahVarF);
            if (ahVarF instanceof bd) {
                bd bdVar = (bd) ahVarF;
                NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) bdVar.getVideoContainerView();
                if (nativeVideoWrapper != null) {
                    NativeVideoView videoView = nativeVideoWrapper.getVideoView();
                    be beVar = (be) videoView.getTag();
                    beVar.v.put("seekPosition", Integer.valueOf(videoView.getCurrentPosition()));
                    beVar.v.put("lastMediaVolume", Integer.valueOf(videoView.getVolume()));
                    if (beVar.y != null) {
                        ((be) beVar.y).a(beVar);
                    }
                    a(beVar, bdVar);
                }
            }
            Activity activity = ahVarF.q == null ? null : ahVarF.q.get();
            if (activity != null && (activity instanceof InMobiAdActivity)) {
                ((InMobiAdActivity) activity).a = true;
                activity.finish();
                if (this.F != -1) {
                    activity.overridePendingTransition(0, this.F);
                }
            }
            this.n.H = null;
            this.n.N.submit(this.O);
        } catch (Exception e) {
            new StringBuilder("Encountered unexpected error in handling exit action on video: ").append(e.getMessage());
            Logger.a(Logger.InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in exiting video");
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
    }

    @UiThread
    public final void c(@Nullable ak akVar) {
        bt btVarF;
        ah ahVar = this.K;
        if (ahVar != null && f() != null) {
            try {
                a("EndCardRequested", z());
                ViewGroup viewGroup = (ViewGroup) f();
                View viewA = ahVar.getViewableAd().a(null, viewGroup, false);
                if (viewA != null) {
                    viewGroup.addView(viewA);
                    viewA.setClickable(true);
                    ahVar.x();
                    a("EndCardDisplayed", z());
                    if (!(akVar instanceof be) || (btVarF = ((be) akVar).b().f()) == null) {
                        return;
                    }
                    btVarF.g = true;
                    return;
                }
                b();
                return;
            } catch (Exception e) {
                b();
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                return;
            }
        }
        Logger.a(Logger.InternalLogLevel.DEBUG, "InMobi", "Failed to show end card");
        b();
    }

    @Override // com.inmobi.ads.AdContainer
    @NonNull
    public /* bridge */ /* synthetic */ Object getDataModel() {
        return this.a;
    }

    static /* synthetic */ void a(ah ahVar) {
        JSONObject jSONObjectA;
        ao aoVar = ahVar.a;
        if (aoVar.e.length() == 0 || (jSONObjectA = aoVar.a()) == null) {
            return;
        }
        ao aoVar2 = new ao(ahVar.b.a, jSONObjectA, aoVar, ahVar.b.a == AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE, ahVar.c, null);
        aoVar2.c = aoVar.c;
        aoVar2.j = aoVar.j;
        Context context = ahVar.p.get();
        if (!aoVar2.c() || context == null) {
            return;
        }
        ahVar.H = b.a(context, new AdContainer.RenderingProperties(AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE), aoVar2, ahVar.d, ahVar.h, ahVar.i, ahVar.c, ahVar.e, ahVar.g, ahVar.f);
        ahVar.H.a((AdContainer) ahVar);
        c cVar = ahVar.o;
        if (cVar != null) {
            ahVar.H.o = cVar;
        }
        if (aoVar.c) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.inmobi.ads.ah.4
                @Override // java.lang.Runnable
                public final void run() {
                    ah.this.H.getViewableAd().a(null, new RelativeLayout(ah.this.j()), false);
                }
            });
        }
    }
}
