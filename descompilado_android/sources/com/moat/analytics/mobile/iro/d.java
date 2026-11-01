package com.moat.analytics.mobile.iro;

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
abstract class d extends c {

    /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
    static final MoatAdEventType[] f524 = {MoatAdEventType.AD_EVT_FIRST_QUARTILE, MoatAdEventType.AD_EVT_MID_POINT, MoatAdEventType.AD_EVT_THIRD_QUARTILE};

    /* JADX INFO: renamed from: ʻ, reason: contains not printable characters */
    final Map<MoatAdEventType, Integer> f525;

    /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
    WeakReference<View> f526;

    /* JADX INFO: renamed from: ˊॱ, reason: contains not printable characters */
    final Handler f527;

    /* JADX INFO: renamed from: ˋॱ, reason: contains not printable characters */
    private Double f528;

    /* JADX INFO: renamed from: ˏॱ, reason: contains not printable characters */
    private Map<String, String> f529;

    /* JADX INFO: renamed from: ͺ, reason: contains not printable characters */
    private boolean f530;

    /* JADX INFO: renamed from: ॱˊ, reason: contains not printable characters */
    private VideoTrackerListener f531;

    /* JADX INFO: renamed from: ॱˋ, reason: contains not printable characters */
    private final Set<MoatAdEventType> f532;

    /* JADX INFO: renamed from: ॱˎ, reason: contains not printable characters */
    private final String f533;

    /* JADX INFO: renamed from: ᐝॱ, reason: contains not printable characters */
    private final b f534;

    /* JADX INFO: renamed from: ˊॱ, reason: contains not printable characters */
    abstract Map<String, Object> mo480() throws o;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    static /* synthetic */ VideoTrackerListener m477(d dVar) {
        dVar.f531 = null;
        return null;
    }

    d(String str) {
        super(null, false, true);
        b.m461(3, "BaseVideoTracker", this, "Initializing.");
        this.f533 = str;
        this.f534 = new b(a.m452(), b.a.f509);
        this.f520 = this.f534.f503;
        try {
            super.m471(this.f534.f505);
        } catch (o e) {
            this.f518 = e;
        }
        this.f525 = new HashMap();
        this.f532 = new HashSet();
        this.f527 = new Handler();
        this.f530 = false;
        this.f528 = Double.valueOf(1.0d);
    }

    public void setVideoListener(VideoTrackerListener videoTrackerListener) {
        this.f531 = videoTrackerListener;
    }

    public void removeVideoListener() {
        this.f531 = null;
    }

    @CallSuper
    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    public boolean mo482(Map<String, String> map, View view) {
        try {
            m470();
            m473();
            if (view == null) {
                b.m461(3, "BaseVideoTracker", this, "trackVideoAd received null video view instance");
            }
            this.f529 = map;
            this.f526 = new WeakReference<>(view);
            mo472();
            String str = String.format("trackVideoAd tracking ids: %s | view: %s", new JSONObject(map).toString(), b.m463(view));
            b.m461(3, "BaseVideoTracker", this, str);
            b.m459("[SUCCESS] ", mo469() + " " + str);
            if (this.f516 != null) {
                this.f516.onTrackingStarted(m468());
            }
            return true;
        } catch (Exception e) {
            m475("trackVideoAd", e);
            return false;
        }
    }

    @Override // com.moat.analytics.mobile.iro.c
    public void changeTargetView(View view) {
        b.m461(3, "BaseVideoTracker", this, "changing view to " + b.m463(view));
        this.f526 = new WeakReference<>(view);
        try {
            super.changeTargetView(view);
        } catch (Exception e) {
            o.m569(e);
        }
    }

    @Override // com.moat.analytics.mobile.iro.c
    /* JADX INFO: renamed from: ˏ */
    void mo474(List<String> list) throws o {
        if (this.f529 == null) {
            list.add("Null adIds object");
        }
        if (!list.isEmpty()) {
            throw new o(TextUtils.join(" and ", list));
        }
        super.mo474(list);
    }

    @Override // com.moat.analytics.mobile.iro.c
    public void stopTracking() {
        try {
            super.stopTracking();
            m485();
            if (this.f531 != null) {
                this.f531 = null;
            }
        } catch (Exception e) {
            o.m569(e);
        }
    }

    /* JADX INFO: renamed from: ˏॱ, reason: contains not printable characters */
    final Double m483() {
        return this.f528;
    }

    @Override // com.moat.analytics.mobile.iro.c
    /* JADX INFO: renamed from: ˎ */
    final void mo472() throws o {
        super.changeTargetView(this.f526.get());
        super.mo472();
        Map<String, Object> mapMo480 = mo480();
        Integer num = (Integer) mapMo480.get("width");
        Integer num2 = (Integer) mapMo480.get("height");
        Integer num3 = (Integer) mapMo480.get("duration");
        b.m461(3, "BaseVideoTracker", this, String.format(Locale.ROOT, "Player metadata: height = %d, width = %d, duration = %d", num2, num, num3));
        this.f534.m465(this.f533, this.f529, num, num2, num3);
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    JSONObject mo481(MoatAdEvent moatAdEvent) {
        if (Double.isNaN(moatAdEvent.f489.doubleValue())) {
            moatAdEvent.f489 = this.f528;
        }
        return new JSONObject(moatAdEvent.m446());
    }

    /* JADX INFO: renamed from: ॱˋ, reason: contains not printable characters */
    final void m485() {
        if (this.f530) {
            return;
        }
        this.f530 = true;
        this.f527.postDelayed(new Runnable() { // from class: com.moat.analytics.mobile.iro.d.4
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    b.m461(3, "BaseVideoTracker", this, "Shutting down.");
                    b bVar = d.this.f534;
                    b.m461(3, "GlobalWebView", bVar, "Cleaning up");
                    bVar.f503.m508();
                    bVar.f503 = null;
                    bVar.f505.destroy();
                    bVar.f505 = null;
                    d.m477(d.this);
                } catch (Exception e) {
                    o.m569(e);
                }
            }
        }, 500L);
    }

    /* JADX INFO: renamed from: ͺ, reason: contains not printable characters */
    final boolean m484() {
        return this.f525.containsKey(MoatAdEventType.AD_EVT_COMPLETE) || this.f525.containsKey(MoatAdEventType.AD_EVT_STOPPED) || this.f525.containsKey(MoatAdEventType.AD_EVT_SKIPPED);
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    static boolean m478(Integer num, Integer num2) {
        int iAbs = Math.abs(num2.intValue() - num.intValue());
        double dIntValue = num2.intValue();
        Double.isNaN(dIntValue);
        return ((double) iAbs) <= Math.min(750.0d, dIntValue * 0.05d);
    }

    public void setPlayerVolume(Double d) {
        Double dValueOf = Double.valueOf(this.f528.doubleValue() * p.m580());
        if (d.equals(this.f528)) {
            return;
        }
        b.m461(3, "BaseVideoTracker", this, String.format(Locale.ROOT, "player volume changed to %f ", d));
        this.f528 = d;
        if (dValueOf.equals(Double.valueOf(this.f528.doubleValue() * p.m580()))) {
            return;
        }
        dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_VOLUME_CHANGE, MoatAdEvent.f485, this.f528));
    }

    public void dispatchEvent(MoatAdEvent moatAdEvent) {
        try {
            JSONObject jSONObjectMo481 = mo481(moatAdEvent);
            b.m461(3, "BaseVideoTracker", this, String.format("Received event: %s", jSONObjectMo481.toString()));
            b.m459("[SUCCESS] ", mo469() + String.format(" Received event: %s", jSONObjectMo481.toString()));
            if (m476() && this.f520 != null) {
                this.f520.m510(this.f534.f502, jSONObjectMo481);
                if (!this.f532.contains(moatAdEvent.f490)) {
                    this.f532.add(moatAdEvent.f490);
                    if (this.f531 != null) {
                        this.f531.onVideoEventReported(moatAdEvent.f490);
                    }
                }
            }
            MoatAdEventType moatAdEventType = moatAdEvent.f490;
            if (moatAdEventType == MoatAdEventType.AD_EVT_COMPLETE || moatAdEventType == MoatAdEventType.AD_EVT_STOPPED || moatAdEventType == MoatAdEventType.AD_EVT_SKIPPED) {
                this.f525.put(moatAdEventType, 1);
                if (this.f520 != null) {
                    this.f520.m513(this);
                }
                m485();
            }
        } catch (Exception e) {
            o.m569(e);
        }
    }

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    final Double m486() {
        return Double.valueOf(this.f528.doubleValue() * p.m580());
    }
}
