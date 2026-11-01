package com.facebook.ads.redexgen.X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.io.Serializable;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.1A, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C1A extends BroadcastReceiver {
    private Context B;
    private boolean C = false;
    private C0446Cn D;

    public C1A(C0446Cn videoView, Context context) {
        this.D = videoView;
        this.B = context.getApplicationContext();
    }

    public final void A() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.facebook.ads.interstitial.displayed:" + this.D.getUniqueId());
        intentFilter.addAction("videoInterstitalEvent:" + this.D.getUniqueId());
        intentFilter.addAction("performCtaClick:" + this.D.getUniqueId());
        C02464v.C(this.B).A(this, intentFilter);
    }

    public final void B() {
        try {
            C02464v.C(this.B).C(this);
        } catch (Exception e) {
        }
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context mContext, Intent intent) {
        String[] strArrSplit = intent.getAction().split(":");
        if (strArrSplit.length != 2 || !strArrSplit[1].equals(this.D.getUniqueId())) {
            return;
        }
        if (strArrSplit[0].equals("com.facebook.ads.interstitial.displayed")) {
            if (this.D.getListener() == null) {
                return;
            }
            this.D.getListener().wD();
            this.D.getListener().QF();
            return;
        }
        if (strArrSplit[0].equals("videoInterstitalEvent")) {
            Serializable serializableExtra = intent.getSerializableExtra("event");
            if (serializableExtra instanceof C03016y) {
                if (this.D.getListener() != null) {
                    this.D.getListener().AE();
                    this.D.getListener().QF();
                }
                if (this.C) {
                    this.D.L(1);
                } else {
                    this.D.L(((C03016y) serializableExtra).B());
                }
                this.D.setVisibility(0);
                this.D.O(C7A.USER_STARTED);
                return;
            }
            if (serializableExtra instanceof AnonymousClass74) {
                if (this.D.getListener() == null) {
                    return;
                }
                this.D.getListener().CE();
                return;
            }
            if (serializableExtra instanceof AnonymousClass73) {
                if (this.D.getListener() == null) {
                    return;
                }
                this.D.getListener().DE();
                return;
            } else if (serializableExtra instanceof AnonymousClass76) {
                if (this.D.getListener() != null) {
                    this.D.getListener().lD();
                }
                this.C = true;
                return;
            } else if (serializableExtra instanceof AnonymousClass71) {
                if (this.D.getListener() != null) {
                    this.D.getListener().pE();
                }
                this.C = false;
                return;
            } else {
                if (!(serializableExtra instanceof AnonymousClass72) || this.D.getListener() == null) {
                    return;
                }
                this.D.getListener().onPause();
                return;
            }
        }
        if (!strArrSplit[0].equals("performCtaClick")) {
            return;
        }
        this.D.Q();
    }
}
