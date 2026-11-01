package com.fingersoft.liveops_sdk.eula;

import android.content.Context;
import android.webkit.JavascriptInterface;

/* JADX INFO: loaded from: classes.dex */
public class EULAInterface {
    private Context mContext;
    private EULAWindow mEulaWindow;

    public EULAInterface(Context context, EULAWindow eULAWindow) {
        this.mContext = context;
        this.mEulaWindow = eULAWindow;
    }

    @JavascriptInterface
    public void eulaAcceptButton() {
        this.mEulaWindow.onEulaAccepted();
    }

    @JavascriptInterface
    public void eulaCancelButton() {
        this.mEulaWindow.onEulaCanceled();
    }
}
