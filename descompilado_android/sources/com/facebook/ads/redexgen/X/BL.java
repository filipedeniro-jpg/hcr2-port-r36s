package com.facebook.ads.redexgen.X;

import android.text.TextUtils;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class BL extends WebChromeClient {
    private final WeakReference<InterfaceC0569Hh> B;
    private final WeakReference<BN> C;

    public BL(WeakReference<InterfaceC0569Hh> weakReference, WeakReference<BN> weakReference2) {
        this.B = weakReference;
        this.C = weakReference2;
    }

    @Override // android.webkit.WebChromeClient
    public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        String strMessage = consoleMessage.message();
        if (!TextUtils.isEmpty(strMessage) && consoleMessage.messageLevel() == ConsoleMessage.MessageLevel.LOG && this.C.get() != null) {
            this.C.get().B(strMessage);
            return true;
        }
        return true;
    }

    @Override // android.webkit.WebChromeClient
    public final void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        if (this.C.get() != null) {
            this.C.get().A();
        }
        if (this.B.get() != null) {
            this.B.get().vE(newProgress);
        }
    }

    @Override // android.webkit.WebChromeClient
    public final void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        if (this.B.get() != null) {
            this.B.get().xE(title);
        }
    }
}
