package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Messenger;
import com.facebook.share.internal.MessengerShareContentUtility;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class J8 {
    private final String B;
    private final Context C;
    private Messenger E;
    private final String F;
    private boolean D = false;
    private final ServiceConnection G = new J7(this);

    public J8(Context context, String requestId, String anUuid) {
        this.C = context;
        this.F = requestId;
        this.B = anUuid;
    }

    public final void A() {
        Intent intent = new Intent();
        intent.setClassName("com.facebook.katana", "com.facebook.audiencenetwork.AudienceNetworkService");
        try {
            if (!this.C.bindService(intent, this.G, 1)) {
                this.C.unbindService(this.G);
            }
        } catch (Exception e) {
            C0585Hx.D(this.C, MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, C0584Hw.GB, new C0583Hv(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bundle G() {
        Bundle bundle = new Bundle();
        bundle.putInt("PARAM_PROTOCOL_VERSION", 1);
        bundle.putString("PARAM_AN_UUID", this.B);
        bundle.putString("PARAM_REQUEST_ID", this.F);
        return bundle;
    }
}
