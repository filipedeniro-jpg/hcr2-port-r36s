package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.VisibleForTesting;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Fl, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
@VisibleForTesting
public class HandlerC0522Fl extends Handler {
    private final JC B;

    private HandlerC0522Fl(Context applicationContext) {
        this.B = new JC(applicationContext);
    }

    public /* synthetic */ HandlerC0522Fl(Context x0, ServiceConnectionC0521Fk x1) {
        this(x0);
    }

    @Override // android.os.Handler
    public final void handleMessage(Message msg) {
        String string = msg.getData().getString("STR_AD_ID_KEY");
        switch (msg.what) {
            case 1:
                C01952w.B().A(string, msg.replyTo);
                break;
            case 2:
                C01952w.B().I(string);
                break;
            default:
                if (!this.B.A(msg)) {
                    super.handleMessage(msg);
                }
                break;
        }
    }
}
