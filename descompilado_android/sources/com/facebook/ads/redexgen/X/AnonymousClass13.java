package com.facebook.ads.redexgen.X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.facebook.ads.AdError;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.13, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class AnonymousClass13 extends BroadcastReceiver {
    private C01340n B;
    private Context C;
    private AnonymousClass12 D;
    private String E;

    public AnonymousClass13(Context mContext, String mUniqueId, C01340n mAdapter, AnonymousClass12 mListener) {
        this.C = mContext;
        this.E = mUniqueId;
        this.D = mListener;
        this.B = mAdapter;
    }

    public final void A() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.facebook.ads.interstitial.impression.logged:" + this.E);
        intentFilter.addAction("com.facebook.ads.interstitial.displayed:" + this.E);
        intentFilter.addAction("com.facebook.ads.interstitial.dismissed:" + this.E);
        intentFilter.addAction("com.facebook.ads.interstitial.clicked:" + this.E);
        intentFilter.addAction("com.facebook.ads.interstitial.error:" + this.E);
        intentFilter.addAction("com.facebook.ads.interstitial.activity_destroyed:" + this.E);
        intentFilter.addAction("com.facebook.ads.interstitial.reward:" + this.E);
        C02464v.C(this.C).A(this, intentFilter);
    }

    public final void B() {
        try {
            C02464v.C(this.C).C(this);
        } catch (Exception e) {
        }
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context mContext, Intent intent) {
        String str = intent.getAction().split(":")[0];
        if (this.D == null || str == null) {
            return;
        }
        if ("com.facebook.ads.interstitial.clicked".equals(str)) {
            this.D.PE(this.B, null, true);
            return;
        }
        if ("com.facebook.ads.interstitial.dismissed".equals(str)) {
            this.D.QE(this.B);
            return;
        }
        if ("com.facebook.ads.interstitial.displayed".equals(str)) {
            this.D.RE(this.B);
            return;
        }
        if ("com.facebook.ads.interstitial.impression.logged".equals(str)) {
            this.D.UE(this.B);
            return;
        }
        if ("com.facebook.ads.interstitial.error".equals(str)) {
            this.D.TE(this.B, AdError.INTERNAL_ERROR);
        } else if ("com.facebook.ads.interstitial.activity_destroyed".equals(str)) {
            this.D.onInterstitialActivityDestroyed();
        } else {
            if (!"com.facebook.ads.interstitial.reward".equals(str)) {
                return;
            }
            this.D.VE();
        }
    }
}
