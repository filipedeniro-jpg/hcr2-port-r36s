package com.facebook.ads.redexgen.X;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class KD implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener {
    public final /* synthetic */ KF B;

    private KD(KF kf) {
        this.B = kf;
    }

    public /* synthetic */ KD(KF x0, K2 x1) {
        this(x0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, String> D() {
        HashMap map = new HashMap();
        map.put("touch", MJ.C(this.B.f.m258B()));
        if (this.B.a != null) {
            map.put("nti", String.valueOf(this.B.a.B()));
        }
        if (this.B.O) {
            map.put("nhs", String.valueOf(this.B.O));
        }
        if (this.B.k != null) {
            this.B.k.A(map);
        }
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E(Map<String, String> map) {
        if (this.B.B != null) {
            this.B.B.b(map);
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View v) {
        if (!this.B.f.E()) {
            Log.e("FBAudienceNetworkLog", "No touch data recorded, please ensure touch events reach the ad View by returning false if you intercept the event.");
        }
        int iD = JA.d(this.B.I);
        if (iD >= 0 && this.B.f.A() < iD) {
            if (!this.B.f.D()) {
                Log.e("FBAudienceNetworkLog", "Ad cannot be clicked before it is viewed.");
                return;
            } else {
                Log.e("FBAudienceNetworkLog", "Clicks happened too fast.");
                return;
            }
        }
        if (this.B.f.C(this.B.I)) {
            if (this.B.B == null) {
                return;
            }
            this.B.B.c(D());
        } else {
            if (JA.BB(this.B.I)) {
                if (this.B.B != null) {
                    this.B.B.f(D());
                }
                MX.B(new KB(this), new KC(this), MI.B());
                return;
            }
            E(D());
        }
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View v) {
        if (this.B.G == null || this.B.K == null) {
            return false;
        }
        this.B.K.setBounds(0, 0, this.B.G.getWidth(), this.B.G.getHeight());
        this.B.K.C(this.B.K.A() ? false : true);
        return true;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View v, MotionEvent event) {
        this.B.f.F(event, this.B.G, v);
        return this.B.g != null && this.B.g.onTouch(v, event);
    }
}
