package com.vungle.warren.ui;

import android.util.Log;
import android.webkit.JavascriptInterface;

/* JADX INFO: loaded from: classes2.dex */
public class JavascriptBridge {
    private ActionHandler handler;

    public interface ActionHandler {
        void handleAction(String str);
    }

    public JavascriptBridge(ActionHandler actionHandler) {
        this.handler = actionHandler;
    }

    @JavascriptInterface
    public void performAction(String str) {
        Log.d("JavascriptBridge", "actionClicked(" + str + ")");
        this.handler.handleAction(str);
    }
}
