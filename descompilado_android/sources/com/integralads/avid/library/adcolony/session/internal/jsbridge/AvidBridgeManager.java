package com.integralads.avid.library.adcolony.session.internal.jsbridge;

import android.text.TextUtils;
import android.webkit.WebView;
import com.integralads.avid.library.adcolony.AvidBridge;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSessionContext;
import com.integralads.avid.library.adcolony.utils.AvidCommand;
import com.integralads.avid.library.adcolony.weakreference.AvidWebView;
import java.util.ArrayList;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class AvidBridgeManager {
    public static final int VIDEO_EVENT_TAG = 1;
    private final InternalAvidAdSessionContext a;
    private boolean b;
    private boolean d;
    private AvidBridgeManagerListener e;
    private final ArrayList<AvidEvent> f = new ArrayList<>();
    private AvidWebView c = new AvidWebView(null);

    public interface AvidBridgeManagerListener {
        void avidBridgeManagerDidInjectAvidJs();
    }

    public AvidBridgeManager(InternalAvidAdSessionContext internalAvidAdSessionContext) {
        this.a = internalAvidAdSessionContext;
    }

    public boolean isActive() {
        return this.b;
    }

    public void setListener(AvidBridgeManagerListener avidBridgeManagerListener) {
        this.e = avidBridgeManagerListener;
    }

    public void onAvidJsReady() {
        a();
    }

    public void setWebView(WebView webView) {
        if (this.c.get() == webView) {
            return;
        }
        this.c.set(webView);
        this.b = false;
        if (AvidBridge.isAvidJsReady()) {
            a();
        }
    }

    public void destroy() {
        setWebView(null);
    }

    public void callAvidbridge(String str) {
        this.c.injectFormattedJavaScript(str);
    }

    public void publishReadyEventForDeferredAdSession() {
        this.d = true;
        b();
    }

    public void publishNativeViewState(String str) {
        callAvidbridge(AvidCommand.setNativeViewState(str));
    }

    public void publishAppState(String str) {
        callAvidbridge(AvidCommand.setAppState(str));
    }

    public void publishVideoEvent(String str, JSONObject jSONObject) {
        if (isActive()) {
            a(str, jSONObject);
        } else {
            this.f.add(new AvidEvent(1, str, jSONObject));
        }
    }

    private void a() {
        if (this.c.isEmpty()) {
            return;
        }
        this.b = true;
        this.c.injectJavaScript(AvidBridge.getAvidJs());
        c();
        b();
        e();
        d();
    }

    private void b() {
        if (isActive() && this.d) {
            callAvidbridge(AvidCommand.publishReadyEventForDeferredAdSession());
        }
    }

    private void a(String str, JSONObject jSONObject) {
        String string = jSONObject != null ? jSONObject.toString() : null;
        if (!TextUtils.isEmpty(string)) {
            callAvidbridge(AvidCommand.publishVideoEvent(str, string));
        } else {
            callAvidbridge(AvidCommand.publishVideoEvent(str));
        }
    }

    private void c() {
        callAvidbridge(AvidCommand.setAvidAdSessionContext(this.a.getFullContext().toString()));
    }

    private void d() {
        AvidBridgeManagerListener avidBridgeManagerListener = this.e;
        if (avidBridgeManagerListener != null) {
            avidBridgeManagerListener.avidBridgeManagerDidInjectAvidJs();
        }
    }

    private void e() {
        for (AvidEvent avidEvent : this.f) {
            a(avidEvent.getType(), avidEvent.getData());
        }
        this.f.clear();
    }
}
