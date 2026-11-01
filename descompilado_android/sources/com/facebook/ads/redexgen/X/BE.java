package com.facebook.ads.redexgen.X;

import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.facebook.ads.internal.api.BuildConfigApi;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class BE extends WebChromeClient {
    @Override // android.webkit.WebChromeClient
    public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        if (BuildConfigApi.isDebug()) {
            Log.d(BB.P, "Console message: " + consoleMessage.message());
            return true;
        }
        return true;
    }

    @Override // android.webkit.WebChromeClient
    public final void onProgressChanged(WebView view, int newProgress) {
        if (BuildConfigApi.isDebug()) {
            Log.d(BB.P, "Loading progress: " + newProgress);
        }
    }
}
