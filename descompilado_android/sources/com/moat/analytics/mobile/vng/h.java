package com.moat.analytics.mobile.vng;

import android.view.View;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
abstract class h<PlayerOrIMAAd> extends c<PlayerOrIMAAd> {
    int l;
    private a m;
    private int n;
    private double o;
    private int p;
    private int q;

    protected enum a {
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
        this.o = Double.NaN;
        this.q = 0;
        this.m = a.UNINITIALIZED;
    }

    private void n() {
        this.h.postDelayed(new Runnable() { // from class: com.moat.analytics.mobile.vng.h.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (h.this.j.get() != null && !h.this.i() && Boolean.valueOf(h.this.m()).booleanValue()) {
                        h.this.h.postDelayed(this, 200L);
                    } else {
                        h hVar = h.this;
                        hVar.h();
                    }
                } catch (Exception e) {
                    h.this.h();
                    m.a(e);
                }
            }
        }, 200L);
    }

    @Override // com.moat.analytics.mobile.vng.c
    protected JSONObject a(MoatAdEvent moatAdEvent) {
        Integer numValueOf;
        int i;
        if (moatAdEvent.b.equals(MoatAdEvent.a)) {
            try {
                numValueOf = j();
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

    @Override // com.moat.analytics.mobile.vng.c
    public boolean a(Map<String, String> map, PlayerOrIMAAd playerorimaad, View view) {
        try {
            if (!this.e) {
                n();
            }
        } catch (Exception e) {
            m.a(e);
        }
        return super.a(map, playerorimaad, view);
    }

    protected abstract Integer j();

    protected abstract boolean k();

    protected abstract Integer l();

    boolean m() {
        a aVar;
        if (this.j.get() == null || i()) {
            return false;
        }
        try {
            int iIntValue = j().intValue();
            if (this.n >= 0 && iIntValue < 0) {
                return false;
            }
            this.n = iIntValue;
            if (iIntValue == 0) {
                return true;
            }
            int iIntValue2 = l().intValue();
            boolean zK = k();
            double d = iIntValue2;
            Double.isNaN(d);
            double d2 = d / 4.0d;
            double dA = s.a();
            MoatAdEventType moatAdEventType = null;
            if (iIntValue > this.p) {
                this.p = iIntValue;
            }
            if (this.l == Integer.MIN_VALUE) {
                this.l = iIntValue2;
            }
            if (zK) {
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
                    if (iFloor > -1 && iFloor < 3) {
                        MoatAdEventType moatAdEventType2 = f[iFloor];
                        if (!this.g.containsKey(moatAdEventType2)) {
                            this.g.put(moatAdEventType2, 1);
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
            if (!z && !Double.isNaN(this.o) && Math.abs(this.o - dA) > 0.05d) {
                moatAdEventType = MoatAdEventType.AD_EVT_VOLUME_CHANGE;
                z = true;
            }
            if (z) {
                dispatchEvent(new MoatAdEvent(moatAdEventType, Integer.valueOf(iIntValue), Double.valueOf(dA)));
            }
            this.o = dA;
            this.q = 0;
            return true;
        } catch (Exception unused) {
            int i = this.q;
            this.q = i + 1;
            return i < 5;
        }
    }

    @Override // com.moat.analytics.mobile.vng.c, com.moat.analytics.mobile.vng.b
    public void stopTracking() {
        try {
            dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_COMPLETE));
            p.a("[SUCCESS] ", a() + " stopTracking succeeded for " + e());
        } catch (Exception e) {
            m.a(e);
        }
    }
}
