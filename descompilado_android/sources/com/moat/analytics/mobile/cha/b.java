package com.moat.analytics.mobile.cha;

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
abstract class b extends d {

    /* JADX INFO: renamed from: ʻ, reason: contains not printable characters */
    static final MoatAdEventType[] f281 = {MoatAdEventType.AD_EVT_FIRST_QUARTILE, MoatAdEventType.AD_EVT_MID_POINT, MoatAdEventType.AD_EVT_THIRD_QUARTILE};

    /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
    final Map<MoatAdEventType, Integer> f282;

    /* JADX INFO: renamed from: ʼॱ, reason: contains not printable characters */
    private final String f283;

    /* JADX INFO: renamed from: ˊॱ, reason: contains not printable characters */
    WeakReference<View> f284;

    /* JADX INFO: renamed from: ˋॱ, reason: contains not printable characters */
    private boolean f285;

    /* JADX INFO: renamed from: ˏॱ, reason: contains not printable characters */
    private Map<String, String> f286;

    /* JADX INFO: renamed from: ͺ, reason: contains not printable characters */
    private Double f287;

    /* JADX INFO: renamed from: ॱˊ, reason: contains not printable characters */
    private VideoTrackerListener f288;

    /* JADX INFO: renamed from: ॱˋ, reason: contains not printable characters */
    private final Set<MoatAdEventType> f289;

    /* JADX INFO: renamed from: ॱˎ, reason: contains not printable characters */
    private final a f290;

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    final Handler f291;

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    abstract Map<String, Object> mo290() throws o;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    static /* synthetic */ VideoTrackerListener m281(b bVar) {
        bVar.f288 = null;
        return null;
    }

    b(String str) {
        super(null, false, true);
        a.m273(3, "BaseVideoTracker", this, "Initializing.");
        this.f283 = str;
        this.f290 = new a(c.m294(), a.d.f279);
        this.f306 = this.f290.f275;
        try {
            super.m306(this.f290.f273);
        } catch (o e) {
            this.f309 = e;
        }
        this.f282 = new HashMap();
        this.f289 = new HashSet();
        this.f291 = new Handler();
        this.f285 = false;
        this.f287 = Double.valueOf(1.0d);
    }

    public void setVideoListener(VideoTrackerListener videoTrackerListener) {
        this.f288 = videoTrackerListener;
    }

    public void removeVideoListener() {
        this.f288 = null;
    }

    @CallSuper
    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    public boolean mo288(Map<String, String> map, View view) {
        try {
            m304();
            m305();
            if (view == null) {
                a.m273(3, "BaseVideoTracker", this, "trackVideoAd received null video view instance");
            }
            this.f286 = map;
            this.f284 = new WeakReference<>(view);
            mo286();
            String str = String.format("trackVideoAd tracking ids: %s | view: %s", new JSONObject(map).toString(), a.m272(view));
            a.m273(3, "BaseVideoTracker", this, str);
            a.m270("[SUCCESS] ", mo303() + " " + str);
            if (this.f303 != null) {
                this.f303.onTrackingStarted(m299());
            }
            return true;
        } catch (Exception e) {
            m307("trackVideoAd", e);
            return false;
        }
    }

    @Override // com.moat.analytics.mobile.cha.d
    public void changeTargetView(View view) {
        a.m273(3, "BaseVideoTracker", this, "changing view to " + a.m272(view));
        this.f284 = new WeakReference<>(view);
        try {
            super.changeTargetView(view);
        } catch (Exception e) {
            o.m389(e);
        }
    }

    @Override // com.moat.analytics.mobile.cha.d
    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    void mo283(List<String> list) throws o {
        if (this.f286 == null) {
            list.add("Null adIds object");
        }
        if (!list.isEmpty()) {
            throw new o(TextUtils.join(" and ", list));
        }
        super.mo283(list);
    }

    @Override // com.moat.analytics.mobile.cha.d
    public void stopTracking() {
        try {
            super.stopTracking();
            m287();
            if (this.f288 != null) {
                this.f288 = null;
            }
        } catch (Exception e) {
            o.m389(e);
        }
    }

    /* JADX INFO: renamed from: ॱˊ, reason: contains not printable characters */
    final Double m289() {
        return this.f287;
    }

    @Override // com.moat.analytics.mobile.cha.d
    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    final void mo286() throws o {
        super.changeTargetView(this.f284.get());
        super.mo286();
        Map<String, Object> mapMo290 = mo290();
        Integer num = (Integer) mapMo290.get("width");
        Integer num2 = (Integer) mapMo290.get("height");
        Integer num3 = (Integer) mapMo290.get("duration");
        a.m273(3, "BaseVideoTracker", this, String.format(Locale.ROOT, "Player metadata: height = %d, width = %d, duration = %d", num2, num, num3));
        this.f290.m278(this.f283, this.f286, num, num2, num3);
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    JSONObject mo285(MoatAdEvent moatAdEvent) {
        if (Double.isNaN(moatAdEvent.f262.doubleValue())) {
            moatAdEvent.f262 = this.f287;
        }
        return new JSONObject(moatAdEvent.m267());
    }

    /* JADX INFO: renamed from: ˏॱ, reason: contains not printable characters */
    final void m287() {
        if (this.f285) {
            return;
        }
        this.f285 = true;
        this.f291.postDelayed(new Runnable() { // from class: com.moat.analytics.mobile.cha.b.5
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    a.m273(3, "BaseVideoTracker", this, "Shutting down.");
                    a aVar = b.this.f290;
                    a.m273(3, "GlobalWebView", aVar, "Cleaning up");
                    aVar.f275.m357();
                    aVar.f275 = null;
                    aVar.f273.destroy();
                    aVar.f273 = null;
                    b.m281(b.this);
                } catch (Exception e) {
                    o.m389(e);
                }
            }
        }, 500L);
    }

    /* JADX INFO: renamed from: ˋॱ, reason: contains not printable characters */
    final boolean m284() {
        return this.f282.containsKey(MoatAdEventType.AD_EVT_COMPLETE) || this.f282.containsKey(MoatAdEventType.AD_EVT_STOPPED) || this.f282.containsKey(MoatAdEventType.AD_EVT_SKIPPED);
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    static boolean m280(Integer num, Integer num2) {
        int iAbs = Math.abs(num2.intValue() - num.intValue());
        double dIntValue = num2.intValue();
        Double.isNaN(dIntValue);
        return ((double) iAbs) <= Math.min(750.0d, dIntValue * 0.05d);
    }

    public void setPlayerVolume(Double d) {
        Double dValueOf = Double.valueOf(this.f287.doubleValue() * r.m410());
        if (d.equals(this.f287)) {
            return;
        }
        a.m273(3, "BaseVideoTracker", this, String.format(Locale.ROOT, "player volume changed to %f ", d));
        this.f287 = d;
        if (dValueOf.equals(Double.valueOf(this.f287.doubleValue() * r.m410()))) {
            return;
        }
        dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_VOLUME_CHANGE, MoatAdEvent.f259, this.f287));
    }

    public void dispatchEvent(MoatAdEvent moatAdEvent) {
        try {
            JSONObject jSONObjectMo285 = mo285(moatAdEvent);
            a.m273(3, "BaseVideoTracker", this, String.format("Received event: %s", jSONObjectMo285.toString()));
            a.m270("[SUCCESS] ", mo303() + String.format(" Received event: %s", jSONObjectMo285.toString()));
            if (m302() && this.f306 != null) {
                this.f306.m365(this.f290.f272, jSONObjectMo285);
                if (!this.f289.contains(moatAdEvent.f264)) {
                    this.f289.add(moatAdEvent.f264);
                    if (this.f288 != null) {
                        this.f288.onVideoEventReported(moatAdEvent.f264);
                    }
                }
            }
            MoatAdEventType moatAdEventType = moatAdEvent.f264;
            if (moatAdEventType == MoatAdEventType.AD_EVT_COMPLETE || moatAdEventType == MoatAdEventType.AD_EVT_STOPPED || moatAdEventType == MoatAdEventType.AD_EVT_SKIPPED) {
                this.f282.put(moatAdEventType, 1);
                if (this.f306 != null) {
                    this.f306.m359(this);
                }
                m287();
            }
        } catch (Exception e) {
            o.m389(e);
        }
    }

    /* JADX INFO: renamed from: ˊॱ, reason: contains not printable characters */
    final Double m282() {
        return Double.valueOf(this.f287.doubleValue() * r.m410());
    }
}
