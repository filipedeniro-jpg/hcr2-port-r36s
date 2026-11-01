package com.moat.analytics.mobile.inm;

import android.view.View;
import androidx.annotation.CallSuper;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
abstract class h extends c {
    int l;
    private a m;
    private int n;
    private double o;
    private int p;
    private int q;

    enum a {
        UNINITIALIZED,
        PAUSED,
        PLAYING,
        STOPPED,
        COMPLETED
    }

    h(String str) {
        super(str);
        this.p = Integer.MIN_VALUE;
        this.l = Integer.MIN_VALUE;
        this.n = Integer.MIN_VALUE;
        this.q = 0;
        this.m = a.UNINITIALIZED;
        this.o = Double.NaN;
    }

    private void t() {
        this.i.postDelayed(new Runnable() { // from class: com.moat.analytics.mobile.inm.h.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (!h.this.n() || h.this.m()) {
                        h.this.l();
                    } else if (Boolean.valueOf(h.this.s()).booleanValue()) {
                        h.this.i.postDelayed(this, 200L);
                    } else {
                        h.this.l();
                    }
                } catch (Exception e) {
                    h.this.l();
                    m.a(e);
                }
            }
        }, 200L);
    }

    @Override // com.moat.analytics.mobile.inm.c
    JSONObject a(MoatAdEvent moatAdEvent) {
        Integer numValueOf;
        int i;
        if (moatAdEvent.b.equals(MoatAdEvent.a)) {
            try {
                numValueOf = o();
            } catch (Exception unused) {
                numValueOf = Integer.valueOf(this.n);
            }
            moatAdEvent.b = numValueOf;
        } else {
            numValueOf = moatAdEvent.b;
        }
        if (moatAdEvent.b.intValue() < 0 || (moatAdEvent.b.intValue() == 0 && moatAdEvent.d == MoatAdEventType.AD_EVT_COMPLETE && this.n > 0)) {
            numValueOf = Integer.valueOf(this.n);
            moatAdEvent.b = numValueOf;
        }
        if (moatAdEvent.d == MoatAdEventType.AD_EVT_COMPLETE) {
            if (numValueOf.intValue() == Integer.MIN_VALUE || (i = this.l) == Integer.MIN_VALUE || !a(numValueOf, Integer.valueOf(i))) {
                this.m = a.STOPPED;
                moatAdEvent.d = MoatAdEventType.AD_EVT_STOPPED;
            } else {
                this.m = a.COMPLETED;
            }
        }
        return super.a(moatAdEvent);
    }

    @Override // com.moat.analytics.mobile.inm.c
    public boolean a(Map<String, String> map, View view) {
        try {
            boolean zA = super.a(map, view);
            if (!zA || !p()) {
                return zA;
            }
            t();
            return zA;
        } catch (Exception e) {
            p.a(3, "IntervalVideoTracker", this, "Problem with video loop");
            a("trackVideoAd", e);
            return false;
        }
    }

    abstract boolean n();

    abstract Integer o();

    protected boolean p() {
        return true;
    }

    abstract boolean q();

    abstract Integer r();

    @CallSuper
    boolean s() {
        a aVar;
        if (n() && !m()) {
            try {
                int iIntValue = o().intValue();
                if (this.n >= 0 && iIntValue < 0) {
                    return false;
                }
                this.n = iIntValue;
                if (iIntValue == 0) {
                    return true;
                }
                int iIntValue2 = r().intValue();
                boolean zQ = q();
                double d = iIntValue2;
                Double.isNaN(d);
                double d2 = d / 4.0d;
                double dDoubleValue = j().doubleValue();
                MoatAdEventType moatAdEventType = null;
                if (iIntValue > this.p) {
                    this.p = iIntValue;
                }
                if (this.l == Integer.MIN_VALUE) {
                    this.l = iIntValue2;
                }
                if (zQ) {
                    if (this.m == a.UNINITIALIZED) {
                        moatAdEventType = MoatAdEventType.AD_EVT_START;
                        aVar = a.PLAYING;
                    } else if (this.m == a.PAUSED) {
                        moatAdEventType = MoatAdEventType.AD_EVT_PLAYING;
                        aVar = a.PLAYING;
                    } else {
                        double d3 = iIntValue;
                        Double.isNaN(d3);
                        int iFloor = ((int) Math.floor(d3 / d2)) - 1;
                        if (iFloor >= 0 && iFloor < 3) {
                            MoatAdEventType moatAdEventType2 = g[iFloor];
                            if (!this.h.containsKey(moatAdEventType2)) {
                                this.h.put(moatAdEventType2, 1);
                                moatAdEventType = moatAdEventType2;
                            }
                        }
                    }
                    this.m = aVar;
                } else if (this.m != a.PAUSED) {
                    moatAdEventType = MoatAdEventType.AD_EVT_PAUSED;
                    aVar = a.PAUSED;
                    this.m = aVar;
                }
                boolean z = moatAdEventType != null;
                if (!z && !Double.isNaN(this.o) && Math.abs(this.o - dDoubleValue) > 0.05d) {
                    moatAdEventType = MoatAdEventType.AD_EVT_VOLUME_CHANGE;
                    z = true;
                }
                if (z) {
                    dispatchEvent(new MoatAdEvent(moatAdEventType, Integer.valueOf(iIntValue), k()));
                }
                this.o = dDoubleValue;
                this.q = 0;
                return true;
            } catch (Exception unused) {
                int i = this.q;
                this.q = i + 1;
                if (i < 5) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.moat.analytics.mobile.inm.c
    public void setPlayerVolume(Double d) {
        super.setPlayerVolume(d);
        this.o = j().doubleValue();
    }

    @Override // com.moat.analytics.mobile.inm.c, com.moat.analytics.mobile.inm.b
    public void stopTracking() {
        try {
            dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_COMPLETE));
            super.stopTracking();
        } catch (Exception e) {
            m.a(e);
        }
    }
}
