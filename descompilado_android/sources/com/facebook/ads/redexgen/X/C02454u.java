package com.facebook.ads.redexgen.X;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.4u, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C02454u {
    public boolean B;
    public boolean C;
    public final IntentFilter D;
    public final BroadcastReceiver E;

    public C02454u(IntentFilter _filter, BroadcastReceiver _receiver) {
        this.D = _filter;
        this.E = _receiver;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("Receiver{");
        sb.append(this.E);
        sb.append(" filter=");
        sb.append(this.D);
        if (this.C) {
            sb.append(" DEAD");
        }
        sb.append("}");
        return sb.toString();
    }
}
