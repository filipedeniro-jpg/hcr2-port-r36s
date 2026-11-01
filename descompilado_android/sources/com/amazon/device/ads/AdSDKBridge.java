package com.amazon.device.ads;

/* JADX INFO: loaded from: classes.dex */
interface AdSDKBridge {
    String getJavascript();

    JavascriptInteractor.Executor getJavascriptInteractorExecutor();

    String getName();

    SDKEventListener getSDKEventListener();

    boolean hasNativeExecution();
}
