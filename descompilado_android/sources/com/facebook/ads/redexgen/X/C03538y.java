package com.facebook.ads.redexgen.X;

import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.8y, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C03538y extends WebChromeClient {
    public final /* synthetic */ C03428n B;

    private C03538y(C03428n c03428n) {
        this.B = c03428n;
    }

    @Override // android.webkit.WebChromeClient
    public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        String strMessage;
        if (consoleMessage.messageLevel() == ConsoleMessage.MessageLevel.ERROR) {
            if (JA.RB(this.B.getContext())) {
                strMessage = consoleMessage.message() + " for request " + this.B.C.J();
            } else {
                strMessage = consoleMessage.message();
            }
            C0585Hx.D(this.B.getContext(), "dynamic_layout", C0584Hw.x, new C0583Hv(strMessage));
        }
        return super.onConsoleMessage(consoleMessage);
    }
}
