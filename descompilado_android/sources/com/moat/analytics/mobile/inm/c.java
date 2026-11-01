package com.moat.analytics.mobile.inm;

import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.CallSuper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
abstract class c extends b {
    static final MoatAdEventType[] g = {MoatAdEventType.AD_EVT_FIRST_QUARTILE, MoatAdEventType.AD_EVT_MID_POINT, MoatAdEventType.AD_EVT_THIRD_QUARTILE};
    final Map<MoatAdEventType, Integer> h;
    final Handler i;
    Map<String, String> j;
    WeakReference<View> k;
    private final Set<MoatAdEventType> l;
    private VideoTrackerListener m;
    private boolean n;
    private Double o;
    private final g p;
    private final String q;

    c(String str) {
        super(null, false, true);
        p.a(3, "BaseVideoTracker", this, "Initializing.");
        this.q = str;
        this.p = new g(a.a(), g.a.VIDEO);
        super.a(this.p.b);
        try {
            super.a(this.p.a);
        } catch (m e) {
            this.a = e;
        }
        this.h = new HashMap();
        this.l = new HashSet();
        this.i = new Handler();
        this.n = false;
        this.o = Double.valueOf(1.0d);
    }

    private static boolean a(MoatAdEventType moatAdEventType) {
        return moatAdEventType == MoatAdEventType.AD_EVT_COMPLETE || moatAdEventType == MoatAdEventType.AD_EVT_STOPPED || moatAdEventType == MoatAdEventType.AD_EVT_SKIPPED;
    }

    private void b(MoatAdEvent moatAdEvent) {
        JSONObject jSONObjectA = a(moatAdEvent);
        p.a(3, "BaseVideoTracker", this, String.format("Received event: %s", jSONObjectA.toString()));
        p.a("[SUCCESS] ", a() + String.format(" Received event: %s", jSONObjectA.toString()));
        if (e() && this.c != null) {
            this.c.a(this.p.c, jSONObjectA);
            if (!this.l.contains(moatAdEvent.d)) {
                this.l.add(moatAdEvent.d);
                VideoTrackerListener videoTrackerListener = this.m;
                if (videoTrackerListener != null) {
                    videoTrackerListener.onVideoEventReported(moatAdEvent.d);
                }
            }
        }
        MoatAdEventType moatAdEventType = moatAdEvent.d;
        if (a(moatAdEventType)) {
            this.h.put(moatAdEventType, 1);
            if (this.c != null) {
                this.c.c(this);
            }
            l();
        }
    }

    JSONObject a(MoatAdEvent moatAdEvent) {
        if (Double.isNaN(moatAdEvent.c.doubleValue())) {
            moatAdEvent.c = this.o;
        }
        return new JSONObject(moatAdEvent.a());
    }

    @Override // com.moat.analytics.mobile.inm.b
    void a(List<String> list) throws m {
        if (this.j == null) {
            list.add("Null adIds object");
        }
        if (!list.isEmpty()) {
            throw new m(TextUtils.join(" and ", list));
        }
        super.a(list);
    }

    boolean a(Integer num, Integer num2) {
        int iAbs = Math.abs(num2.intValue() - num.intValue());
        double dIntValue = num2.intValue();
        Double.isNaN(dIntValue);
        return ((double) iAbs) <= Math.min(750.0d, dIntValue * 0.05d);
    }

    @CallSuper
    public boolean a(Map<String, String> map, View view) {
        try {
            c();
            d();
            if (view == null) {
                p.a(3, "BaseVideoTracker", this, "trackVideoAd received null video view instance");
            }
            this.j = map;
            this.k = new WeakReference<>(view);
            b();
            String str = String.format("trackVideoAd tracking ids: %s | view: %s", new JSONObject(map).toString(), p.a(view));
            p.a(3, "BaseVideoTracker", this, str);
            p.a("[SUCCESS] ", a() + " " + str);
            if (this.d != null) {
                this.d.onTrackingStarted(g());
            }
            return true;
        } catch (Exception e) {
            a("trackVideoAd", e);
            return false;
        }
    }

    @Override // com.moat.analytics.mobile.inm.b
    void b() throws m {
        super.changeTargetView(this.k.get());
        super.b();
        Map<String, Object> mapI = i();
        Integer num = (Integer) mapI.get("width");
        Integer num2 = (Integer) mapI.get("height");
        Integer num3 = (Integer) mapI.get("duration");
        p.a(3, "BaseVideoTracker", this, String.format(Locale.ROOT, "Player metadata: height = %d, width = %d, duration = %d", num2, num, num3));
        this.p.a(this.q, this.j, num, num2, num3);
    }

    @Override // com.moat.analytics.mobile.inm.b
    public void changeTargetView(View view) {
        p.a(3, "BaseVideoTracker", this, "changing view to " + p.a(view));
        this.k = new WeakReference<>(view);
        try {
            super.changeTargetView(view);
        } catch (Exception e) {
            m.a(e);
        }
    }

    public void dispatchEvent(MoatAdEvent moatAdEvent) {
        try {
            b(moatAdEvent);
        } catch (Exception e) {
            m.a(e);
        }
    }

    abstract Map<String, Object> i();

    Double j() {
        return Double.valueOf(k().doubleValue() * s.a());
    }

    Double k() {
        return this.o;
    }

    void l() {
        if (this.n) {
            return;
        }
        this.n = true;
        this.i.postDelayed(new Runnable() { // from class: com.moat.analytics.mobile.inm.c.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    p.a(3, "BaseVideoTracker", this, "Shutting down.");
                    c.this.p.a();
                    c.this.m = null;
                } catch (Exception e) {
                    m.a(e);
                }
            }
        }, 500L);
    }

    boolean m() {
        return this.h.containsKey(MoatAdEventType.AD_EVT_COMPLETE) || this.h.containsKey(MoatAdEventType.AD_EVT_STOPPED) || this.h.containsKey(MoatAdEventType.AD_EVT_SKIPPED);
    }

    public void removeVideoListener() {
        this.m = null;
    }

    public void setPlayerVolume(Double d) {
        Double dJ = j();
        if (d.equals(this.o)) {
            return;
        }
        p.a(3, "BaseVideoTracker", this, String.format(Locale.ROOT, "player volume changed to %f ", d));
        this.o = d;
        if (dJ.equals(j())) {
            return;
        }
        dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_VOLUME_CHANGE, MoatAdEvent.a, this.o));
    }

    public void setVideoListener(VideoTrackerListener videoTrackerListener) {
        this.m = videoTrackerListener;
    }

    @Override // com.moat.analytics.mobile.inm.b
    public void stopTracking() {
        try {
            super.stopTracking();
            l();
            if (this.m != null) {
                this.m = null;
            }
        } catch (Exception e) {
            m.a(e);
        }
    }
}
