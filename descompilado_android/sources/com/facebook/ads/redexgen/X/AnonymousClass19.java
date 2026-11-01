package com.facebook.ads.redexgen.X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.facebook.ads.AdError;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.19, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class AnonymousClass19 extends BroadcastReceiver {
    private AbstractC01430w B;
    private AnonymousClass18 C;
    private String D;

    public AnonymousClass19(String mUniqueID, AbstractC01430w mAdapter, AnonymousClass18 mListener) {
        this.B = mAdapter;
        this.C = mListener;
        this.D = mUniqueID;
    }

    public final IntentFilter A() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(EnumC02926p.REWARDED_VIDEO_COMPLETE.A(this.D));
        intentFilter.addAction(EnumC02926p.REWARDED_VIDEO_ERROR.A(this.D));
        intentFilter.addAction(EnumC02926p.REWARDED_VIDEO_AD_CLICK.A(this.D));
        intentFilter.addAction(EnumC02926p.REWARDED_VIDEO_IMPRESSION.A(this.D));
        intentFilter.addAction(EnumC02926p.REWARDED_VIDEO_CLOSED.A(this.D));
        intentFilter.addAction(EnumC02926p.REWARD_SERVER_SUCCESS.A(this.D));
        intentFilter.addAction(EnumC02926p.REWARD_SERVER_FAILED.A(this.D));
        intentFilter.addAction(EnumC02926p.REWARDED_VIDEO_ACTIVITY_DESTROYED.A(this.D));
        return intentFilter;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (EnumC02926p.REWARDED_VIDEO_COMPLETE.A(this.D).equals(action)) {
            this.C.EF(this.B);
            return;
        }
        if (EnumC02926p.REWARDED_VIDEO_ERROR.A(this.D).equals(action)) {
            this.C.FF(this.B, AdError.INTERNAL_ERROR);
            return;
        }
        if (EnumC02926p.REWARDED_VIDEO_AD_CLICK.A(this.D).equals(action)) {
            this.C.BF(this.B);
            return;
        }
        if (EnumC02926p.REWARDED_VIDEO_IMPRESSION.A(this.D).equals(action)) {
            this.C.DF(this.B);
            return;
        }
        if (EnumC02926p.REWARDED_VIDEO_CLOSED.A(this.D).equals(action)) {
            this.C.onRewardedVideoClosed();
            return;
        }
        if (EnumC02926p.REWARD_SERVER_FAILED.A(this.D).equals(action)) {
            this.C.zE(this.B);
        } else if (EnumC02926p.REWARD_SERVER_SUCCESS.A(this.D).equals(action)) {
            this.C.AF(this.B);
        } else {
            if (!EnumC02926p.REWARDED_VIDEO_ACTIVITY_DESTROYED.A(this.D).equals(action)) {
                return;
            }
            this.C.onRewardedVideoActivityDestroyed();
        }
    }
}
