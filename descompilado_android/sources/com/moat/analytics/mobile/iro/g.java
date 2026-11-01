package com.moat.analytics.mobile.iro;

import android.view.View;
import androidx.annotation.CallSuper;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
abstract class g extends d {

    /* JADX INFO: renamed from: ˋॱ, reason: contains not printable characters */
    private int f564;

    /* JADX INFO: renamed from: ˏॱ, reason: contains not printable characters */
    private double f565;

    /* JADX INFO: renamed from: ͺ, reason: contains not printable characters */
    private int f566;

    /* JADX INFO: renamed from: ॱˊ, reason: contains not printable characters */
    private int f567;

    /* JADX INFO: renamed from: ॱˋ, reason: contains not printable characters */
    private int f568;

    /* JADX INFO: renamed from: ॱˎ, reason: contains not printable characters */
    private int f569;

    /* JADX INFO: renamed from: ʻॱ, reason: contains not printable characters */
    abstract Integer mo517();

    /* JADX INFO: renamed from: ˋॱ, reason: contains not printable characters */
    abstract Integer mo519();

    /* JADX INFO: renamed from: ॱˊ, reason: contains not printable characters */
    abstract boolean mo520();

    /* JADX INFO: renamed from: ॱˎ, reason: contains not printable characters */
    abstract boolean mo521();

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    static final class a {

        /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
        public static final int f571 = 3;

        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        public static final int f572 = 4;

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        public static final int f573 = 1;

        /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
        public static final int f574 = 5;

        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        public static final int f575 = 2;

        static {
            int[] iArr = {1, 2, 3, 4, 5};
        }
    }

    g(String str) {
        super(str);
        this.f564 = Integer.MIN_VALUE;
        this.f568 = Integer.MIN_VALUE;
        this.f566 = Integer.MIN_VALUE;
        this.f569 = 0;
        this.f567 = a.f573;
        this.f565 = Double.NaN;
    }

    @Override // com.moat.analytics.mobile.iro.d
    /* JADX INFO: renamed from: ˋ */
    public final boolean mo482(Map<String, String> map, View view) {
        try {
            boolean zMo482 = super.mo482(map, view);
            if (!zMo482) {
                return zMo482;
            }
            this.f527.postDelayed(new Runnable() { // from class: com.moat.analytics.mobile.iro.g.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        if (g.this.mo520() && !g.this.m484()) {
                            if (Boolean.valueOf(g.this.m518()).booleanValue()) {
                                g.this.f527.postDelayed(this, 200L);
                                return;
                            } else {
                                g.this.m485();
                                return;
                            }
                        }
                        g.this.m485();
                    } catch (Exception e) {
                        g.this.m485();
                        o.m569(e);
                    }
                }
            }, 200L);
            return zMo482;
        } catch (Exception e) {
            b.m461(3, "IntervalVideoTracker", this, "Problem with video loop");
            m475("trackVideoAd", e);
            return false;
        }
    }

    @Override // com.moat.analytics.mobile.iro.d, com.moat.analytics.mobile.iro.c
    public void stopTracking() {
        try {
            dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_COMPLETE));
            super.stopTracking();
        } catch (Exception e) {
            o.m569(e);
        }
    }

    @Override // com.moat.analytics.mobile.iro.d
    public void setPlayerVolume(Double d) {
        super.setPlayerVolume(d);
        this.f565 = m486().doubleValue();
    }

    @Override // com.moat.analytics.mobile.iro.d
    /* JADX INFO: renamed from: ˋ */
    final JSONObject mo481(MoatAdEvent moatAdEvent) {
        Integer numValueOf;
        int i;
        if (!moatAdEvent.f488.equals(MoatAdEvent.f485)) {
            numValueOf = moatAdEvent.f488;
        } else {
            try {
                numValueOf = mo519();
            } catch (Exception unused) {
                numValueOf = Integer.valueOf(this.f566);
            }
            moatAdEvent.f488 = numValueOf;
        }
        if (moatAdEvent.f488.intValue() < 0 || (moatAdEvent.f488.intValue() == 0 && moatAdEvent.f490 == MoatAdEventType.AD_EVT_COMPLETE && this.f566 > 0)) {
            numValueOf = Integer.valueOf(this.f566);
            moatAdEvent.f488 = numValueOf;
        }
        if (moatAdEvent.f490 == MoatAdEventType.AD_EVT_COMPLETE) {
            if (numValueOf.intValue() == Integer.MIN_VALUE || (i = this.f568) == Integer.MIN_VALUE || !m478(numValueOf, Integer.valueOf(i))) {
                this.f567 = a.f572;
                moatAdEvent.f490 = MoatAdEventType.AD_EVT_STOPPED;
            } else {
                this.f567 = a.f574;
            }
        }
        return super.mo481(moatAdEvent);
    }

    @CallSuper
    /* JADX INFO: renamed from: ʼॱ, reason: contains not printable characters */
    final boolean m518() throws o {
        if (mo520() && !m484()) {
            try {
                int iIntValue = mo519().intValue();
                if (this.f566 >= 0 && iIntValue < 0) {
                    return false;
                }
                this.f566 = iIntValue;
                if (iIntValue == 0) {
                    return true;
                }
                int iIntValue2 = mo517().intValue();
                boolean zMo521 = mo521();
                double d = iIntValue2;
                Double.isNaN(d);
                double d2 = d / 4.0d;
                double dDoubleValue = m486().doubleValue();
                MoatAdEventType moatAdEventType = null;
                if (iIntValue > this.f564) {
                    this.f564 = iIntValue;
                }
                if (this.f568 == Integer.MIN_VALUE) {
                    this.f568 = iIntValue2;
                }
                if (zMo521) {
                    if (this.f567 == a.f573) {
                        moatAdEventType = MoatAdEventType.AD_EVT_START;
                        this.f567 = a.f571;
                    } else if (this.f567 == a.f575) {
                        moatAdEventType = MoatAdEventType.AD_EVT_PLAYING;
                        this.f567 = a.f571;
                    } else {
                        double d3 = iIntValue;
                        Double.isNaN(d3);
                        int iFloor = ((int) Math.floor(d3 / d2)) - 1;
                        if (iFloor >= 0 && iFloor < 3) {
                            MoatAdEventType moatAdEventType2 = f524[iFloor];
                            if (!this.f525.containsKey(moatAdEventType2)) {
                                this.f525.put(moatAdEventType2, 1);
                                moatAdEventType = moatAdEventType2;
                            }
                        }
                    }
                } else if (this.f567 != a.f575) {
                    moatAdEventType = MoatAdEventType.AD_EVT_PAUSED;
                    this.f567 = a.f575;
                }
                boolean z = moatAdEventType != null;
                if (!z && !Double.isNaN(this.f565) && Math.abs(this.f565 - dDoubleValue) > 0.05d) {
                    moatAdEventType = MoatAdEventType.AD_EVT_VOLUME_CHANGE;
                    z = true;
                }
                if (z) {
                    dispatchEvent(new MoatAdEvent(moatAdEventType, Integer.valueOf(iIntValue), m483()));
                }
                this.f565 = dDoubleValue;
                this.f569 = 0;
                return true;
            } catch (Exception unused) {
                int i = this.f569;
                this.f569 = i + 1;
                if (i < 5) {
                    return true;
                }
            }
        }
        return false;
    }
}
