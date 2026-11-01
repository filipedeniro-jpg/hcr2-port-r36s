package com.integralads.avid.library.inmobi.session.internal.jsbridge;

import android.os.Handler;
import android.webkit.JavascriptInterface;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;

/* JADX INFO: loaded from: classes2.dex */
public class AvidJavascriptInterface {
    public static final String AVID_OBJECT = "avid";
    private final InternalAvidAdSessionContext avidAdSessionContext;
    private AvidJavascriptInterfaceCallback callback;
    private final Handler handler = new Handler();

    public interface AvidJavascriptInterfaceCallback {
        void onAvidAdSessionContextInvoked();
    }

    public AvidJavascriptInterface(InternalAvidAdSessionContext internalAvidAdSessionContext) {
        this.avidAdSessionContext = internalAvidAdSessionContext;
    }

    public AvidJavascriptInterfaceCallback getCallback() {
        return this.callback;
    }

    public void setCallback(AvidJavascriptInterfaceCallback avidJavascriptInterfaceCallback) {
        this.callback = avidJavascriptInterfaceCallback;
    }

    @JavascriptInterface
    public String getAvidAdSessionContext() {
        this.handler.post(new CallbackRunnable());
        return this.avidAdSessionContext.getStubContext().toString();
    }

    class CallbackRunnable implements Runnable {
        CallbackRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (AvidJavascriptInterface.this.callback != null) {
                AvidJavascriptInterface.this.callback.onAvidAdSessionContextInvoked();
                AvidJavascriptInterface.this.callback = null;
            }
        }
    }
}
