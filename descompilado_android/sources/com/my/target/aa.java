package com.my.target;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.device.ads.WebRequest;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: MraidBridge.java */
/* JADX INFO: loaded from: classes2.dex */
public class aa {

    @NonNull
    private final String aS;

    @NonNull
    private final WebViewClient aT = new c();

    @Nullable
    private b aU;

    @Nullable
    private bx aV;
    private boolean aW;
    private boolean aX;

    /* JADX INFO: compiled from: MraidBridge.java */
    public interface b {
        boolean a(float f, float f2);

        boolean a(int i, int i2, int i3, int i4, boolean z, int i5);

        boolean a(@NonNull ConsoleMessage consoleMessage, @NonNull aa aaVar);

        boolean a(@NonNull String str, @NonNull JsResult jsResult);

        boolean a(boolean z, ac acVar);

        void b(@NonNull Uri uri);

        void b(boolean z);

        void c(@NonNull aa aaVar);

        boolean c(@Nullable Uri uri);

        boolean l(@NonNull String str);

        void onClose();

        void onVisibilityChanged(boolean z);

        void p();

        void q();

        boolean r();
    }

    /* JADX INFO: compiled from: MraidBridge.java */
    interface d {
        public static final String DURATION = "duration";
        public static final String HEIGHT = "height";
        public static final String URL = "url";
        public static final String WIDTH = "width";
        public static final String bf = "allowOrientationChange";
        public static final String bg = "forceOrientation";
        public static final String bh = "event";
        public static final String bi = "remain";
        public static final String bj = "customClosePosition";
        public static final String bk = "allowOffscreen";
        public static final String bl = "offsetX";
        public static final String bm = "offsetY";
    }

    /* JADX INFO: compiled from: MraidBridge.java */
    public static class e {
        public static final String bn = "inline";
        public static final String bo = "interstitial";

        /* JADX INFO: compiled from: MraidBridge.java */
        /* JADX INFO: loaded from: classes.dex */
        @Retention(RetentionPolicy.SOURCE)
        public @interface a {
        }
    }

    /* JADX INFO: compiled from: MraidBridge.java */
    public static class f {
        public static final String bp = "loading";
        public static final String bq = "default";
        public static final String br = "hidden";
        public static final String bs = "resized";
        public static final String bt = "expanded";

        /* JADX INFO: compiled from: MraidBridge.java */
        /* JADX INFO: loaded from: classes.dex */
        @Retention(RetentionPolicy.SOURCE)
        public @interface a {
        }
    }

    /* JADX INFO: compiled from: MraidBridge.java */
    interface h {
        public static final String bu = "onloadmraidjs";
    }

    /* JADX INFO: compiled from: MraidBridge.java */
    interface j {
        public static final String bv = "mytarget";
        public static final String bw = "mraid";
    }

    public static aa e(@NonNull String str) {
        return new aa(str);
    }

    private aa(@NonNull String str) {
        this.aS = str;
    }

    public void f(@NonNull String str) {
        bx bxVar = this.aV;
        if (bxVar == null) {
            com.my.target.g.a("MRAID bridge called setContentHtml before WebView was attached");
        } else {
            this.aW = false;
            bxVar.loadDataWithBaseURL("https://ad.mail.ru/", str, WebRequest.CONTENT_TYPE_HTML, "UTF-8", null);
        }
    }

    public void a(@NonNull ad adVar) {
        j("mraidbridge.setScreenSize(" + b(adVar.x()) + ");window.mraidbridge.setMaxSize(" + b(adVar.w()) + ");window.mraidbridge.setCurrentPosition(" + a(adVar.u()) + ");window.mraidbridge.setDefaultPosition(" + a(adVar.v()) + ")");
        StringBuilder sb = new StringBuilder();
        sb.append("mraidbridge.fireSizeChangeEvent(");
        sb.append(b(adVar.u()));
        sb.append(")");
        j(sb.toString());
    }

    public void a(@NonNull ArrayList<String> arrayList) {
        j("mraidbridge.setSupports(" + TextUtils.join(",", arrayList) + ")");
    }

    public void a(@Nullable b bVar) {
        this.aU = bVar;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public void a(@NonNull bx bxVar) {
        this.aV = bxVar;
        this.aV.getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= 17 && e.bo.equals(this.aS)) {
            bxVar.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        this.aV.setScrollContainer(false);
        this.aV.setVerticalScrollBarEnabled(false);
        this.aV.setHorizontalScrollBarEnabled(false);
        this.aV.setWebViewClient(this.aT);
        this.aV.setWebChromeClient(new g());
        this.aV.setVisibilityChangedListener(new i());
    }

    public void detach() {
        this.aV = null;
    }

    public void g(String str) {
        j("mraidbridge.setPlacementType(" + JSONObject.quote(str) + ")");
    }

    public void a(boolean z) {
        if (z != this.aX) {
            j("mraidbridge.setIsViewable(" + z + ")");
        }
        this.aX = z;
    }

    public void h(String str) {
        j("mraidbridge.setState(" + JSONObject.quote(str) + ")");
    }

    public void m() {
        j("mraidbridge.fireReadyEvent()");
    }

    public boolean isVisible() {
        bx bxVar = this.aV;
        return bxVar != null && bxVar.isVisible();
    }

    @VisibleForTesting
    void a(@NonNull Uri uri) {
        b bVar;
        String scheme = uri.getScheme();
        String host = uri.getHost();
        if (j.bv.equals(scheme)) {
            if (h.bu.equals(host)) {
                com.my.target.g.a("MraidBridge: JS call onLoad");
            }
            com.my.target.g.a("MraidBridge: got mytarget scheme " + uri);
            return;
        }
        if ("mraid".equals(scheme)) {
            if (host.contains(",")) {
                host = host.substring(0, host.indexOf(",")).trim();
            }
            com.my.target.g.a("Got mraid command " + uri);
            String string = uri.toString();
            JSONObject jSONObject = null;
            ab abVar = new ab(host, this.aS);
            i(abVar.toString());
            int iIndexOf = string.indexOf("{");
            int iLastIndexOf = string.lastIndexOf("}") + 1;
            if (iIndexOf >= 0 && iLastIndexOf > 0 && iIndexOf < iLastIndexOf) {
                try {
                    if (iLastIndexOf <= string.length()) {
                        jSONObject = new JSONObject(string.substring(iIndexOf, iLastIndexOf));
                    }
                } catch (JSONException e2) {
                    a(abVar.toString(), e2.getMessage());
                    return;
                }
            }
            a(abVar, jSONObject);
            return;
        }
        try {
            new URI(uri.toString());
            bx bxVar = this.aV;
            if (bxVar == null || !bxVar.ba() || (bVar = this.aU) == null) {
                return;
            }
            bVar.b(uri);
        } catch (URISyntaxException unused) {
            com.my.target.g.a("Invalid MRAID URL: " + uri);
            a("", "Mraid command sent an invalid URL");
        }
    }

    public void a(@NonNull String str, @NonNull String str2) {
        j("mraidbridge.fireErrorEvent(" + JSONObject.quote(str2) + ", " + JSONObject.quote(str) + ")");
    }

    @Nullable
    @VisibleForTesting
    bx n() {
        return this.aV;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @VisibleForTesting
    boolean a(@NonNull ab abVar, @Nullable JSONObject jSONObject) throws JSONException {
        bx bxVar;
        String string = abVar.toString();
        if (abVar.bL && (bxVar = this.aV) != null && !bxVar.ba()) {
            a(string, "Cannot execute this command unless the user clicks");
            return false;
        }
        if (this.aU == null) {
            a(string, "Invalid state to execute this command");
            return false;
        }
        if (this.aV == null) {
            a(string, "The current WebView is being destroyed");
            return false;
        }
        switch (string) {
            case "close":
                this.aU.onClose();
                return true;
            case "setResizeProperties":
                if (jSONObject == null) {
                    a(string, "setResizeProperties params cannot be null");
                    return false;
                }
                return this.aU.a(jSONObject.getInt("width"), jSONObject.getInt("height"), jSONObject.getInt(d.bl), jSONObject.getInt(d.bm), jSONObject.optBoolean(d.bk, false), a.k(jSONObject.optString(d.bj)));
            case "resize":
                return this.aU.r();
            case "expand":
                return this.aU.c(jSONObject != null ? Uri.parse(jSONObject.getString("url")) : null);
            case "useCustomClose":
            case "setExpandProperties":
                if (jSONObject == null) {
                    a(string, "useCustomClose params cannot be null");
                    return false;
                }
                this.aU.b(jSONObject.getBoolean(ab.by));
                return true;
            case "open":
                if (jSONObject == null) {
                    a(string, "open params cannot be null");
                    return false;
                }
                this.aU.b(Uri.parse(jSONObject.getString("url")));
                return true;
            case "setOrientationProperties":
                if (jSONObject == null) {
                    a(string, "setOrientationProperties params cannot be null");
                    return false;
                }
                boolean z = jSONObject.getBoolean(d.bf);
                String string2 = jSONObject.getString(d.bg);
                ac acVarM = ac.m(string2);
                if (acVarM == null) {
                    a(string, "wrong orientation " + string2);
                    return false;
                }
                return this.aU.a(z, acVarM);
            case "vpaidInit":
                this.aU.q();
                return true;
            case "vpaidEvent":
                if (jSONObject == null) {
                    a(string, "vpaidEvent params cannot be null");
                    return false;
                }
                return this.aU.l(jSONObject.getString("event"));
            case "playheadEvent":
                if (jSONObject == null) {
                    a(string, "playheadEvent params cannot be null");
                    return false;
                }
                return this.aU.a((float) jSONObject.getDouble(d.bi), (float) jSONObject.getDouble("duration"));
            case "playVideo":
                com.my.target.g.a("playVideo is currently unsupported");
                return false;
            case "storePicture":
                com.my.target.g.a("storePicture is currently unsupported");
                return false;
            case "createCalendarEvent":
                com.my.target.g.a("createCalendarEvent is currently unsupported");
                return false;
            case "":
                a(string, "Unspecified MRAID Javascript command");
                return false;
            default:
                return true;
        }
    }

    private void i(@NonNull String str) {
        j("mraidbridge.nativeComplete(" + JSONObject.quote(str) + ")");
    }

    private void j(@NonNull String str) {
        if (this.aV == null) {
            com.my.target.g.a("Attempted to inject Javascript into MRAID WebView while was not attached:\n\t" + str);
            return;
        }
        String str2 = "javascript:window." + str + ";";
        com.my.target.g.a("Injecting Javascript into MRAID WebView " + hashCode() + ": " + str2);
        this.aV.loadUrl(str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        if (this.aW) {
            return;
        }
        this.aW = true;
        b bVar = this.aU;
        if (bVar != null) {
            bVar.c(this);
        }
    }

    @NonNull
    private String a(Rect rect) {
        return rect.left + "," + rect.top + "," + rect.width() + "," + rect.height();
    }

    @NonNull
    private String b(Rect rect) {
        return rect.width() + "," + rect.height();
    }

    /* JADX INFO: compiled from: MraidBridge.java */
    private class c extends WebViewClient {
        private c() {
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(24)
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            aa.this.a(webResourceRequest.getUrl());
            return true;
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            aa.this.a(Uri.parse(str));
            return true;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            aa.this.o();
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            com.my.target.g.a("Error: " + str);
            super.onReceivedError(webView, i, str, str2);
        }
    }

    /* JADX INFO: compiled from: MraidBridge.java */
    private class g extends WebChromeClient {
        private g() {
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            if (aa.this.aU != null) {
                return aa.this.aU.a(str2, jsResult);
            }
            return super.onJsAlert(webView, str, str2, jsResult);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            if (aa.this.aU != null) {
                return aa.this.aU.a(consoleMessage, aa.this);
            }
            return super.onConsoleMessage(consoleMessage);
        }
    }

    /* JADX INFO: compiled from: MraidBridge.java */
    private class i implements bx.a {
        private i() {
        }

        @Override // com.my.target.bx.a
        public void onVisibilityChanged(boolean z) {
            if (aa.this.aU != null) {
                aa.this.aU.onVisibilityChanged(z);
            }
        }

        @Override // com.my.target.bx.a
        public void p() {
            if (aa.this.aU != null) {
                aa.this.aU.p();
            }
        }
    }

    /* JADX INFO: compiled from: MraidBridge.java */
    @SuppressLint({"RtlHardcoded"})
    public static class a {
        public static final int CENTER = 17;
        public static final int aY = 51;
        public static final int aZ = 49;
        public static final int ba = 53;
        public static final int bb = 83;
        public static final int bc = 81;
        public static final int bd = 85;

        /* JADX INFO: renamed from: com.my.target.aa$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: MraidBridge.java */
        /* JADX INFO: loaded from: classes.dex */
        @Retention(RetentionPolicy.SOURCE)
        public @interface InterfaceC0090a {
        }

        public static int k(@Nullable String str) {
            if (str == null) {
                return 53;
            }
            switch (str) {
                case "top-left":
                    return 51;
                case "top-right":
                    return 53;
                case "center":
                    return 17;
                case "bottom-left":
                    return 83;
                case "bottom-right":
                    return 85;
                case "top-center":
                    return 49;
                case "bottom-center":
                    return 81;
                default:
                    return 53;
            }
        }
    }
}
