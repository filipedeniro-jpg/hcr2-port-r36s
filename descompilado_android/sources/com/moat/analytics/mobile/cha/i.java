package com.moat.analytics.mobile.cha;

import android.view.View;
import androidx.annotation.CallSuper;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
abstract class i extends b {

    /* JADX INFO: renamed from: ˋॱ, reason: contains not printable characters */
    private int f342;

    /* JADX INFO: renamed from: ˏॱ, reason: contains not printable characters */
    private int f343;

    /* JADX INFO: renamed from: ͺ, reason: contains not printable characters */
    private int f344;

    /* JADX INFO: renamed from: ॱˊ, reason: contains not printable characters */
    private double f345;

    /* JADX INFO: renamed from: ॱˋ, reason: contains not printable characters */
    private int f346;

    /* JADX INFO: renamed from: ॱˎ, reason: contains not printable characters */
    private int f347;

    /* JADX INFO: renamed from: ͺ, reason: contains not printable characters */
    abstract boolean mo333();

    /* JADX INFO: renamed from: ॱˋ, reason: contains not printable characters */
    abstract Integer mo334();

    /* JADX INFO: renamed from: ॱˎ, reason: contains not printable characters */
    abstract boolean mo335();

    /* JADX INFO: renamed from: ॱᐝ, reason: contains not printable characters */
    abstract Integer mo336();

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    static final class c {

        /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
        public static final int f349 = 2;

        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        public static final int f350 = 4;

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        public static final int f351 = 3;

        /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
        public static final int f352 = 1;

        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        public static final int f353 = 5;

        static {
            int[] iArr = {1, 2, 3, 4, 5};
        }
    }

    i(String str) {
        super(str);
        this.f346 = Integer.MIN_VALUE;
        this.f344 = Integer.MIN_VALUE;
        this.f343 = Integer.MIN_VALUE;
        this.f347 = 0;
        this.f342 = c.f352;
        this.f345 = Double.NaN;
    }

    @Override // com.moat.analytics.mobile.cha.b
    /* JADX INFO: renamed from: ॱ */
    public final boolean mo288(Map<String, String> map, View view) {
        try {
            boolean zMo288 = super.mo288(map, view);
            if (!zMo288) {
                return zMo288;
            }
            this.f291.postDelayed(new Runnable() { // from class: com.moat.analytics.mobile.cha.i.5
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        if (i.this.mo333() && !i.this.m284()) {
                            if (Boolean.valueOf(i.this.m332()).booleanValue()) {
                                i.this.f291.postDelayed(this, 200L);
                                return;
                            } else {
                                i.this.m287();
                                return;
                            }
                        }
                        i.this.m287();
                    } catch (Exception e) {
                        i.this.m287();
                        o.m389(e);
                    }
                }
            }, 200L);
            return zMo288;
        } catch (Exception e) {
            a.m273(3, "IntervalVideoTracker", this, "Problem with video loop");
            m307("trackVideoAd", e);
            return false;
        }
    }

    @Override // com.moat.analytics.mobile.cha.b, com.moat.analytics.mobile.cha.d
    public void stopTracking() {
        try {
            dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_COMPLETE));
            super.stopTracking();
        } catch (Exception e) {
            o.m389(e);
        }
    }

    @Override // com.moat.analytics.mobile.cha.b
    public void setPlayerVolume(Double d) {
        super.setPlayerVolume(d);
        this.f345 = m282().doubleValue();
    }

    @Override // com.moat.analytics.mobile.cha.b
    /* JADX INFO: renamed from: ˎ */
    final JSONObject mo285(MoatAdEvent moatAdEvent) {
        Integer numValueOf;
        int i;
        if (!moatAdEvent.f263.equals(MoatAdEvent.f259)) {
            numValueOf = moatAdEvent.f263;
        } else {
            try {
                numValueOf = mo334();
            } catch (Exception unused) {
                numValueOf = Integer.valueOf(this.f343);
            }
            moatAdEvent.f263 = numValueOf;
        }
        if (moatAdEvent.f263.intValue() < 0 || (moatAdEvent.f263.intValue() == 0 && moatAdEvent.f264 == MoatAdEventType.AD_EVT_COMPLETE && this.f343 > 0)) {
            numValueOf = Integer.valueOf(this.f343);
            moatAdEvent.f263 = numValueOf;
        }
        if (moatAdEvent.f264 == MoatAdEventType.AD_EVT_COMPLETE) {
            if (numValueOf.intValue() == Integer.MIN_VALUE || (i = this.f344) == Integer.MIN_VALUE || !m280(numValueOf, Integer.valueOf(i))) {
                this.f342 = c.f350;
                moatAdEvent.f264 = MoatAdEventType.AD_EVT_STOPPED;
            } else {
                this.f342 = c.f353;
            }
        }
        return super.mo285(moatAdEvent);
    }

    @CallSuper
    /* JADX INFO: renamed from: ʻॱ, reason: contains not printable characters */
    final boolean m332() throws o {
        if (mo333() && !m284()) {
            try {
                int iIntValue = mo334().intValue();
                if (this.f343 >= 0 && iIntValue < 0) {
                    return false;
                }
                this.f343 = iIntValue;
                if (iIntValue == 0) {
                    return true;
                }
                int iIntValue2 = mo336().intValue();
                boolean zMo335 = mo335();
                double d = iIntValue2;
                Double.isNaN(d);
                double d2 = d / 4.0d;
                double dDoubleValue = m282().doubleValue();
                MoatAdEventType moatAdEventType = null;
                if (iIntValue > this.f346) {
                    this.f346 = iIntValue;
                }
                if (this.f344 == Integer.MIN_VALUE) {
                    this.f344 = iIntValue2;
                }
                if (zMo335) {
                    if (this.f342 == c.f352) {
                        moatAdEventType = MoatAdEventType.AD_EVT_START;
                        this.f342 = c.f351;
                    } else if (this.f342 == c.f349) {
                        moatAdEventType = MoatAdEventType.AD_EVT_PLAYING;
                        this.f342 = c.f351;
                    } else {
                        double d3 = iIntValue;
                        Double.isNaN(d3);
                        int iFloor = ((int) Math.floor(d3 / d2)) - 1;
                        if (iFloor >= 0 && iFloor < 3) {
                            MoatAdEventType moatAdEventType2 = f281[iFloor];
                            if (!this.f282.containsKey(moatAdEventType2)) {
                                this.f282.put(moatAdEventType2, 1);
                                moatAdEventType = moatAdEventType2;
                            }
                        }
                    }
                } else if (this.f342 != c.f349) {
                    moatAdEventType = MoatAdEventType.AD_EVT_PAUSED;
                    this.f342 = c.f349;
                }
                boolean z = moatAdEventType != null;
                if (!z && !Double.isNaN(this.f345) && Math.abs(this.f345 - dDoubleValue) > 0.05d) {
                    moatAdEventType = MoatAdEventType.AD_EVT_VOLUME_CHANGE;
                    z = true;
                }
                if (z) {
                    dispatchEvent(new MoatAdEvent(moatAdEventType, Integer.valueOf(iIntValue), m289()));
                }
                this.f345 = dDoubleValue;
                this.f347 = 0;
                return true;
            } catch (Exception unused) {
                int i = this.f347;
                this.f347 = i + 1;
                if (i < 5) {
                    return true;
                }
            }
        }
        return false;
    }
}
