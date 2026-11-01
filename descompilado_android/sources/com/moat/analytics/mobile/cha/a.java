package com.moat.analytics.mobile.cha;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.amazon.device.ads.WebRequest;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class a {

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    final String f272;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    WebView f273;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private boolean f274;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    j f275;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private final int f276;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    static /* synthetic */ boolean m271(a aVar) {
        aVar.f274 = true;
        return true;
    }

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    static final class d {

        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        public static final int f279 = 2;

        /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
        public static final int f280 = 1;

        static {
            int[] iArr = {1, 2};
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    a(Application application, int i) {
        this.f276 = i;
        this.f274 = false;
        this.f272 = String.format(Locale.ROOT, "_moatTracker%d", Integer.valueOf((int) (Math.random() * 1.0E8d)));
        this.f273 = new WebView(application);
        WebSettings settings = this.f273.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccess(false);
        settings.setDatabaseEnabled(false);
        settings.setDomStorageEnabled(false);
        settings.setGeolocationEnabled(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(false);
        settings.setSaveFormData(false);
        if (Build.VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(false);
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(1);
        }
        try {
            this.f275 = new j(this.f273, i == d.f279 ? j.e.f377 : j.e.f378);
        } catch (o e) {
            o.m389(e);
        }
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    final void m277(String str) {
        if (this.f276 == d.f280) {
            this.f273.setWebViewClient(new WebViewClient() { // from class: com.moat.analytics.mobile.cha.a.2
                @Override // android.webkit.WebViewClient
                public final void onPageFinished(WebView webView, String str2) {
                    if (a.this.f274) {
                        return;
                    }
                    try {
                        a.m271(a.this);
                        a.this.f275.m362();
                    } catch (Exception e) {
                        o.m389(e);
                    }
                }
            });
            this.f273.loadData("<!DOCTYPE html>\n<html>\n<head lang=\"en\">\n   <meta charset=\"UTF-8\">\n   <title></title>\n</head>\n<body style=\"margin:0;padding:0;\">\n    <script src=\"https://z.moatads.com/" + str + "/moatad.js\" type=\"text/javascript\"></script>\n</body>\n</html>", WebRequest.CONTENT_TYPE_HTML, "utf-8");
        }
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    final void m278(String str, Map<String, String> map, Integer num, Integer num2, Integer num3) {
        if (this.f276 == d.f279) {
            this.f273.setWebViewClient(new WebViewClient() { // from class: com.moat.analytics.mobile.cha.a.5
                @Override // android.webkit.WebViewClient
                public final void onPageFinished(WebView webView, String str2) {
                    if (a.this.f274) {
                        return;
                    }
                    try {
                        a.m271(a.this);
                        a.this.f275.m362();
                        a.this.f275.m361(a.this.f272);
                    } catch (Exception e) {
                        o.m389(e);
                    }
                }
            });
            JSONObject jSONObject = new JSONObject(map);
            this.f273.loadData(String.format(Locale.ROOT, "<html><head></head><body><div id=\"%s\" style=\"width: %dpx; height: %dpx;\"></div><script>(function initMoatTracking(apiname, pcode, ids, duration) {var events = [];window[pcode + '_moatElToTrack'] = document.getElementById('%s');var moatapi = {'dropTime':%d,'adData': {'ids': ids, 'duration': duration, 'url': 'n/a'},'dispatchEvent': function(ev) {if (this.sendEvent) {if (events) { events.push(ev); ev = events; events = false; }this.sendEvent(ev);} else {events.push(ev);}},'dispatchMany': function(evs){for (var i=0, l=evs.length; i<l; i++) {this.dispatchEvent(evs[i]);}}};Object.defineProperty(window, apiname, {'value': moatapi});var s = document.createElement('script');s.src = 'https://z.moatads.com/' + pcode + '/moatvideo.js?' + apiname + '#' + apiname;document.body.appendChild(s);})('%s', '%s', %s, %s);</script></body></html>", "mianahwvc", num, num2, "mianahwvc", Long.valueOf(System.currentTimeMillis()), this.f272, str, jSONObject.toString(), num3), WebRequest.CONTENT_TYPE_HTML, null);
        }
    }

    a() {
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    static void m273(int i, String str, Object obj, String str2) {
        if (t.m423().f442) {
            if (obj == null) {
                Log.println(i, "Moat" + str, String.format("message = %s", str2));
            } else {
                Log.println(i, "Moat" + str, String.format("id = %s, message = %s", Integer.valueOf(obj.hashCode()), str2));
            }
        }
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    static void m274(String str, Object obj, String str2) {
        if (t.m423().f439) {
            String str3 = "Moat" + str;
            Object[] objArr = new Object[2];
            objArr[0] = obj == null ? "null" : Integer.valueOf(obj.hashCode());
            objArr[1] = str2;
            Log.println(2, str3, String.format("id = %s, message = %s", objArr));
        }
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    static void m275(String str, Object obj, String str2, Exception exc) {
        if (t.m423().f442) {
            Log.e("Moat" + str, String.format("id = %s, message = %s", Integer.valueOf(obj.hashCode()), str2), exc);
        }
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    static void m270(String str, String str2) {
        if (t.m423().f442 || !((f) MoatAnalytics.getInstance()).f318) {
            return;
        }
        Log.println(str.equals("[ERROR] ") ? 6 : 2, "MoatAnalytics", str + str2);
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    static String m272(View view) {
        if (view == null) {
            return "null";
        }
        return view.getClass().getSimpleName() + "@" + view.hashCode();
    }
}
