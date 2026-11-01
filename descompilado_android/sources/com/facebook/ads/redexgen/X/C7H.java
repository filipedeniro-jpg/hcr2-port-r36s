package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.database.ContentObserver;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.facebook.appevents.UserDataStore;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.ironsource.sdk.constants.LocationConst;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.7H, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C7H implements InterfaceC01300j<Bundle> {
    private final JV B;
    private final C01892q C;
    private final String D;
    private final Context E;

    @Nullable
    private final Map<String, String> F;
    private int G;
    private boolean H;
    private int I;
    private final InterfaceC0449Cq J;

    @Nullable
    private String K;

    @Nullable
    private String L;
    private final C7B M;

    /* JADX WARN: Failed to parse debug info
    java.lang.ArrayIndexOutOfBoundsException: Index 9 out of bounds for length 9
    	at jadx.plugins.input.dex.sections.debuginfo.DebugInfoParser.startVar(DebugInfoParser.java:203)
    	at jadx.plugins.input.dex.sections.debuginfo.DebugInfoParser.process(DebugInfoParser.java:135)
    	at jadx.plugins.input.dex.sections.DexCodeReader.getDebugInfo(DexCodeReader.java:122)
    	at jadx.core.dex.nodes.MethodNode.getDebugInfo(MethodNode.java:656)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoAttachVisitor.visit(DebugInfoAttachVisitor.java:38)
     */
    private void S(Map<String, String> map) {
        C01912s c01912sA = this.C.A();
        C01902r c01902rA = c01912sA.A();
        map.put("vwa", String.valueOf(c01902rA.A()));
        map.put("vwm", String.valueOf(c01902rA.F()));
        map.put("vwmax", String.valueOf(c01902rA.C()));
        map.put("vtime_ms", String.valueOf(c01902rA.B() * 1000.0d));
        map.put("mcvt_ms", String.valueOf(c01902rA.D() * 1000.0d));
        if (this.K != null) {
            map.put("vw_d", this.K);
        }
        if (this.L != null) {
            map.put("vw_rsn", this.L);
        }
        C01902r c01902rB = c01912sA.B();
        map.put("vla", String.valueOf(c01902rB.A()));
        map.put("vlm", String.valueOf(c01902rB.F()));
        map.put("vlmax", String.valueOf(c01902rB.C()));
        map.put("atime_ms", String.valueOf(c01902rB.B() * 1000.0d));
        map.put("mcat_ms", String.valueOf(c01902rB.D() * 1000.0d));
    }

    public C7H(Context context, JV adEventManager, InterfaceC0449Cq videoDataProvider, List<AbstractC01260f> list, String clientToken) {
        this(context, adEventManager, videoDataProvider, list, clientToken, null);
    }

    private C7H(Context context, JV adEventManager, InterfaceC0449Cq videoDataProvider, List<AbstractC01260f> list, String clientToken, @Nullable Bundle previousInstanceState) {
        this(context, adEventManager, videoDataProvider, list, clientToken, previousInstanceState, null);
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [com.facebook.ads.redexgen.X.7B] */
    public C7H(Context context, JV adEventManager, InterfaceC0449Cq videoDataProvider, List<AbstractC01260f> list, String clientToken, @Nullable Bundle previousInstanceState, @Nullable Map<String, String> map) {
        this.H = true;
        this.I = 0;
        this.G = 0;
        this.K = null;
        this.L = null;
        this.E = context;
        this.B = adEventManager;
        this.J = videoDataProvider;
        this.D = clientToken;
        this.F = map;
        final double d = 0.5d;
        final double d2 = -1.0d;
        final double d3 = 2.0d;
        final boolean z = true;
        list.add(new AbstractC01260f(d, d2, d3, z) { // from class: com.facebook.ads.redexgen.X.7F
            @Override // com.facebook.ads.redexgen.X.AbstractC01260f
            public final void A(boolean isCompleted, boolean isPassed, C01912s statistics) {
                if (!isPassed) {
                    return;
                }
                this.B.B.DD(this.B.D, this.B.Y(C7D.MRC));
            }
        });
        final double d4 = 1.0E-7d;
        final double d5 = -1.0d;
        final double d6 = 0.001d;
        final boolean z2 = false;
        list.add(new AbstractC01260f(d4, d5, d6, z2) { // from class: com.facebook.ads.redexgen.X.7E
            @Override // com.facebook.ads.redexgen.X.AbstractC01260f
            public final void A(boolean isCompleted, boolean isPassed, C01912s statistics) {
                if (!isPassed) {
                    return;
                }
                this.B.B.DD(this.B.D, this.B.Y(C7D.VIEWABLE_IMPRESSION));
            }
        });
        if (previousInstanceState != null) {
            this.C = new C01892q(videoDataProvider.getView(), list, previousInstanceState.getBundle("adQualityManager"));
            this.I = previousInstanceState.getInt("lastProgressTimeMS");
            this.G = previousInstanceState.getInt("lastBoundaryTimeMS");
        } else {
            this.C = new C01892q(videoDataProvider.getView(), list);
        }
        final Handler handler = new Handler();
        this.M = new ContentObserver(handler, this) { // from class: com.facebook.ads.redexgen.X.7B
            private final C7H B;

            {
                this.B = this;
            }

            @Override // android.database.ContentObserver
            public final boolean deliverSelfNotifications() {
                return false;
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean selfChange) {
                this.B.E();
            }
        };
    }

    public final int A() {
        return this.I;
    }

    public final void B() {
        this.C.C();
        this.B.DD(this.D, Y(C7D.PAUSE));
    }

    public final void C() {
        this.B.DD(this.D, Y(C7D.RESUME));
    }

    public final void D() {
        this.B.DD(this.D, Y(C7D.SKIP));
    }

    public final void E() {
        if (a() < 0.05d) {
            if (this.H) {
                b();
                this.H = false;
                return;
            }
            return;
        }
        if (this.H) {
            return;
        }
        c();
        this.H = true;
    }

    public final void F() {
        this.E.getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, this.M);
    }

    public final void G() {
        this.E.getContentResolver().unregisterContentObserver(this.M);
    }

    public final void H(int currentProgressMs) {
        J(currentProgressMs, true, false);
        this.G = 0;
        this.I = 0;
        this.C.D();
        this.C.C();
    }

    public final void I(int currentProgressMs) {
        J(currentProgressMs, false, false);
    }

    public final void J(int currentProgressMs, boolean isLast, boolean byPassBoundary) {
        if (currentProgressMs <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || currentProgressMs < this.I) {
            return;
        }
        if (currentProgressMs > this.I) {
            this.C.B((currentProgressMs - this.I) / 1000.0f, a());
            this.I = currentProgressMs;
            if (byPassBoundary || currentProgressMs - this.G >= 5000) {
                this.B.DD(this.D, Z(C7D.TIME, currentProgressMs));
                this.G = this.I;
                this.C.D();
                return;
            }
        }
        if (!isLast) {
            return;
        }
        this.B.DD(this.D, Z(C7D.TIME, currentProgressMs));
    }

    public final void K(int seekFromMs, int seekToMs) {
        J(seekFromMs, true, false);
        this.G = seekToMs;
        this.I = seekToMs;
        this.C.D();
        this.C.C();
    }

    private void T(HashMap<String, String> map) {
        if (this.F != null) {
            map.putAll(this.F);
        }
    }

    private void U(Map<String, String> map, int currentTimeMS) {
        map.put("ptime", String.valueOf(this.G / 1000.0f));
        map.put(LocationConst.TIME, String.valueOf(currentTimeMS / 1000.0f));
    }

    private void V(Map<String, String> map) {
        map.put("exoplayer", String.valueOf(this.J.TC()));
        map.put("prep", Long.toString(this.J.getInitialBufferTime()));
    }

    private void W(Map<String, String> map) {
        Rect rect = new Rect();
        this.J.getGlobalVisibleRect(rect);
        map.put("pt", String.valueOf(rect.top));
        map.put("pl", String.valueOf(rect.left));
        map.put(UserDataStore.PHONE, String.valueOf(this.J.getMeasuredHeight()));
        map.put("pw", String.valueOf(this.J.getMeasuredWidth()));
        WindowManager windowManager = (WindowManager) this.E.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        map.put("vph", String.valueOf(displayMetrics.heightPixels));
        map.put("vpw", String.valueOf(displayMetrics.widthPixels));
    }

    private Map<String, String> X(int currentTimeMS) {
        HashMap<String, String> map = new HashMap<>();
        JO.B(map, this.J.getVideoStartReason() == C7A.AUTO_STARTED, this.J.UC() ? false : true);
        V(map);
        S(map);
        U(map, currentTimeMS);
        W(map);
        T(map);
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, String> Y(C7D reportType) {
        return Z(reportType, this.J.getCurrentPositionInMillis());
    }

    private Map<String, String> Z(C7D reportType, int currentTimeMS) {
        Map<String, String> mapX = X(currentTimeMS);
        mapX.put("action", String.valueOf(reportType.B));
        return mapX;
    }

    private final float a() {
        return JO.C(this.E) * this.J.getVolume();
    }

    private void b() {
        this.B.DD(this.D, Y(C7D.MUTE));
    }

    private void c() {
        this.B.DD(this.D, Y(C7D.UNMUTE));
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC01300j
    public final Bundle getSaveInstanceState() {
        K(A(), A());
        Bundle bundle = new Bundle();
        bundle.putInt("lastProgressTimeMS", this.I);
        bundle.putInt("lastBoundaryTimeMS", this.G);
        bundle.putBundle("adQualityManager", this.C.getSaveInstanceState());
        return bundle;
    }
}
