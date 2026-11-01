package com.chartboost.sdk.impl;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;
import com.appsflyer.share.Constants;
import com.chartboost.sdk.Libraries.CBLogging;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class bd extends WebChromeClient {
    private View a;
    private ViewGroup b;
    private boolean c = false;
    private FrameLayout d;
    private WebChromeClient.CustomViewCallback e;
    private a f;
    private final bf g;
    private final Handler h;

    public interface a {
        void a(boolean z);
    }

    public bd(View view, ViewGroup viewGroup, View view2, be beVar, bf bfVar, Handler handler) {
        this.a = view;
        this.b = viewGroup;
        this.g = bfVar;
        this.h = handler;
    }

    @Override // android.webkit.WebChromeClient
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        Log.d(bd.class.getSimpleName(), "Chartboost Webview:" + consoleMessage.message() + " -- From line " + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
        return true;
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        try {
            JSONObject jSONObject = new JSONObject(str2);
            jsPromptResult.confirm(a(jSONObject.getJSONObject("eventArgs"), jSONObject.getString("eventType")));
            return true;
        } catch (JSONException unused) {
            CBLogging.b("CBWebChromeClient", "Exception caught parsing the function name from js to native");
            return true;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:68:0x010a  */
    public String a(JSONObject jSONObject, String str) {
        com.chartboost.sdk.Model.a aVar;
        int i = 4;
        switch (str) {
            case "getParameters":
                Log.d("CBWebChromeClient", "JavaScript to native " + str + " callback triggered.");
                if (this.g.e == null || (aVar = this.g.e.p) == null) {
                    return "{}";
                }
                JSONObject jSONObjectA = com.chartboost.sdk.Libraries.e.a(new com.chartboost.sdk.Libraries.e.a[0]);
                for (Map.Entry<String, String> entry : aVar.d.entrySet()) {
                    com.chartboost.sdk.Libraries.e.a(jSONObjectA, entry.getKey(), entry.getValue());
                }
                for (Map.Entry<String, com.chartboost.sdk.Model.b> entry2 : aVar.c.entrySet()) {
                    com.chartboost.sdk.Model.b value = entry2.getValue();
                    com.chartboost.sdk.Libraries.e.a(jSONObjectA, entry2.getKey(), value.a + Constants.URL_PATH_DELIMITER + value.b);
                }
                return jSONObjectA.toString();
            case "click":
                i = 0;
                break;
            case "close":
                i = 1;
                break;
            case "videoCompleted":
                i = 9;
                break;
            case "videoPlaying":
                i = 11;
                break;
            case "videoPaused":
                i = 10;
                break;
            case "videoReplay":
                i = 12;
                break;
            case "currentVideoDuration":
                i = 2;
                break;
            case "totalVideoDuration":
                i = 7;
                break;
            case "show":
                i = 6;
                break;
            case "error":
                Log.d(be.class.getName(), "Javascript Error occured");
                break;
            case "warning":
                Log.d(be.class.getName(), "Javascript warning occurred");
                i = 13;
                break;
            case "debug":
                i = 3;
                break;
            case "tracking":
                i = 8;
                break;
            case "openUrl":
                i = 5;
                break;
            case "getMaxSize":
                Log.d("CBWebChromeClient", "JavaScript to native " + str + " callback triggered.");
                return this.g.s();
            case "getScreenSize":
                Log.d("CBWebChromeClient", "JavaScript to native " + str + " callback triggered.");
                return this.g.t();
            case "getCurrentPosition":
                Log.d("CBWebChromeClient", "JavaScript to native " + str + " callback triggered.");
                return this.g.v();
            case "getDefaultPosition":
                Log.d("CBWebChromeClient", "JavaScript to native " + str + " callback triggered.");
                return this.g.u();
            case "getOrientationProperties":
                Log.d("CBWebChromeClient", "JavaScript to native " + str + " callback triggered.");
                return this.g.p();
            case "setOrientationProperties":
                Log.d("CBWebChromeClient", "JavaScript to native " + str + " callback triggered.");
                i = 14;
                break;
            default:
                Log.e("CBWebChromeClient", "JavaScript to native " + str + " callback not recognized.");
                return "Function name not recognized.";
        }
        Log.d("CBWebChromeClient", "JavaScript to native " + str + " callback triggered.");
        this.h.post(new bg(this, this.g, i, str, jSONObject));
        return "Native function successfully called.";
    }

    @Override // android.webkit.WebChromeClient
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        if (view instanceof FrameLayout) {
            this.c = true;
            this.d = (FrameLayout) view;
            this.e = customViewCallback;
            this.a.setVisibility(4);
            this.b.addView(this.d, new ViewGroup.LayoutParams(-1, -1));
            this.b.setVisibility(0);
            a aVar = this.f;
            if (aVar != null) {
                aVar.a(true);
            }
        }
    }

    @Override // android.webkit.WebChromeClient
    public void onShowCustomView(View view, int i, WebChromeClient.CustomViewCallback customViewCallback) {
        onShowCustomView(view, customViewCallback);
    }

    @Override // android.webkit.WebChromeClient
    public void onHideCustomView() {
        if (this.c) {
            this.b.setVisibility(4);
            this.b.removeView(this.d);
            this.a.setVisibility(0);
            WebChromeClient.CustomViewCallback customViewCallback = this.e;
            if (customViewCallback != null && !customViewCallback.getClass().getName().contains(".chromium.")) {
                this.e.onCustomViewHidden();
            }
            this.c = false;
            this.d = null;
            this.e = null;
            a aVar = this.f;
            if (aVar != null) {
                aVar.a(false);
            }
        }
    }
}
