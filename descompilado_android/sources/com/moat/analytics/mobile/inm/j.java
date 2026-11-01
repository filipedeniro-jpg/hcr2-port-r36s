package com.moat.analytics.mobile.inm;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.vungle.warren.model.Cookie;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class j {

    @NonNull
    private final WeakReference<WebView> h;
    private final a n;
    private int a = 0;
    private boolean b = false;
    private boolean c = false;
    private final AtomicBoolean d = new AtomicBoolean(false);
    private boolean e = false;
    private boolean f = false;
    private boolean g = false;
    private final BroadcastReceiver o = new BroadcastReceiver() { // from class: com.moat.analytics.mobile.inm.j.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                j.this.d();
            } catch (Exception e) {
                m.a(e);
            }
            if (System.currentTimeMillis() - j.this.k > 30000) {
                j.this.i();
            }
        }
    };
    private final BroadcastReceiver p = new BroadcastReceiver() { // from class: com.moat.analytics.mobile.inm.j.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                j.this.e();
            } catch (Exception e) {
                m.a(e);
            }
        }
    };
    private final LinkedList<String> j = new LinkedList<>();
    private final List<String> m = new ArrayList();
    private final Map<b, String> i = new WeakHashMap();
    private final long k = System.currentTimeMillis();
    private final String l = String.format("javascript:(function(d,k){function l(){function d(a,b){var c=ipkn[b]||ipkn[kuea];if(c){var h=function(b){var c=b.b;c.ts=b.i;c.ticks=b.g;c.buffered=!0;a(c)};h(c.first);c.a.forEach(function(a){h(a)})}}function e(a){var b=a.a,c=a.c,h=a.b;a=a.f;var d=[];if(c)b[c]&&d.push(b[c].fn[0]);else for(key in b)if(b[key])for(var g=0,e=b[key].fn.length;g<e;g++)d.push(b[key].fn[g]);g=0;for(e=d.length;g<e;g++){var f=d[g];if('function'===typeof f)try{h?f(h):f()}catch(k){}a&&delete b[c]}}function f(a,b,c){'function'===typeof a&& (b===kuea&&c[b]?c[b].fn.push(a):c[b]={ts:+new Date,fn:[a]},c===yhgt&&d(a,b))}kuea=+new Date;iymv={};briz=!1;ewat=+new Date;bnkr=[];bjmk={};dptk={};uqaj={};ryup={};yhgt={};ipkn={};csif={};this.h=function(a){this.namespace=a.namespace;this.version=a.version;this.appName=a.appName;this.deviceOS=a.deviceOS;this.isNative=a.isNative;this.versionHash=a.versionHash;this.aqzx=a.aqzx;this.appId=a.appId;this.metadata=a};this.nvsj=function(a){briz||(f(a,ewat,iymv),briz=!0)};this.bpsy=function(a,b){var c=b||kuea; c!==kuea&&bjmk[c]||f(a,c,bjmk)};this.qmrv=function(a,b){var c=b||kuea;c!==kuea&&uqaj[c]||f(a,c,uqaj)};this.lgpr=function(a,b){f(a,b||kuea,yhgt)};this.hgen=function(a,b){f(a,b||kuea,csif)};this.xrnk=function(a){delete yhgt[a||kuea]};this.vgft=function(a){return dptk[a||kuea]||!1};this.lkpu=function(a){return ryup[a||kuea]||!1};this.crts=function(a){var b={a:iymv,b:a,c:ewat};briz?e(b):bnkr.push(a)};this.mqjh=function(a){var b=a||kuea;dptk[b]=!0;var c={a:bjmk,f:!0};b!==kuea&&(c.b=a,c.c=a);e(c)};this.egpw= function(a){var b=a||kuea;ryup[b]=!0;var c={a:uqaj,f:!0};b!==kuea&&(c.b=a,c.c=a);e(c)};this.sglu=function(a){var b=a.adKey||kuea,c={a:yhgt,b:a.event||a,g:1,i:+new Date,f:!1};b!==kuea&&(c.c=a.adKey);a=0<Object.keys(yhgt).length;if(!a||!this.isNative)if(ipkn[b]){var d=ipkn[b].a.slice(-1)[0]||ipkn[b].first;JSON.stringify(c.b)==JSON.stringify(d.b)?d.g+=1:(5<=ipkn[b].a.length&&ipkn[b].a.shift(),ipkn[b].a.push(c))}else ipkn[b]={first:c,a:[]};a&&e(c);return a};this.ucbx=function(a){e({c:a.adKey||kuea,a:csif, b:a.event,f:!1})}}'undefined'===typeof d.MoatMAK&&(d.MoatMAK=new l,d.MoatMAK.h(k),d.__zMoatInit__=!0)})(window,%s);", h());

    enum a {
        WEBVIEW,
        NATIVE_DISPLAY,
        NATIVE_VIDEO
    }

    j(WebView webView, a aVar) {
        this.h = new WeakReference<>(webView);
        this.n = aVar;
        if (d("Initialize")) {
            IntentFilter intentFilter = new IntentFilter("UPDATE_METADATA");
            IntentFilter intentFilter2 = new IntentFilter("UPDATE_VIEW_INFO");
            LocalBroadcastManager.getInstance(s.c()).registerReceiver(this.o, intentFilter);
            LocalBroadcastManager.getInstance(s.c()).registerReceiver(this.p, intentFilter2);
            d();
            i.a().a(s.c(), this);
            p.a(3, "JavaScriptBridge", this, "bridge initialization succeeded");
        }
    }

    private boolean a(WebView webView) {
        return webView.getSettings().getJavaScriptEnabled();
    }

    static /* synthetic */ int b(j jVar) {
        int i = jVar.a;
        jVar.a = i + 1;
        return i;
    }

    private void c() {
        Iterator<Map.Entry<b, String>> it = this.i.entrySet().iterator();
        while (it.hasNext()) {
            b key = it.next().getKey();
            if (key.e()) {
                g(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.mqjh(\"%s\");}", key.e));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        try {
            if (w.a().a == w.d.OFF) {
                return;
            }
            if (!this.c) {
                p.a(3, "JavaScriptBridge", this, "Attempting to establish communication (setting environment variables).");
                this.c = true;
            }
            g(this.l);
        } catch (Exception e) {
            p.a("JavaScriptBridge", this, "Attempt failed to establish communication (did not set environment variables).", e);
        }
    }

    private void d(b bVar) {
        p.a(3, "JavaScriptBridge", this, "Stopping view update loop");
        if (bVar != null) {
            i.a().a(bVar);
        }
    }

    private boolean d(String str) throws m {
        WebView webViewG = g();
        if (webViewG == null) {
            p.a(6, "JavaScriptBridge", this, "WebView is null. Can't " + str);
            throw new m("WebView is null");
        }
        if (a(webViewG)) {
            return true;
        }
        p.a(6, "JavaScriptBridge", this, "JavaScript is not enabled in the given WebView. Can't " + str);
        throw new m("JavaScript is not enabled in the WebView");
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(19)
    public void e() {
        try {
            if (w.a().a == w.d.OFF) {
                return;
            }
            if (this.g) {
                p.a(3, "JavaScriptBridge", this, "Can't send info, already cleaned up");
                return;
            }
            if (f() && (!this.b || g().getUrl() != null)) {
                if (g().getUrl() != null) {
                    this.b = true;
                }
                Iterator<Map.Entry<b, String>> it = this.i.entrySet().iterator();
                while (it.hasNext()) {
                    b key = it.next().getKey();
                    if (key == null || key.f() == null) {
                        p.a(3, "JavaScriptBridge", this, "Tracker has no subject");
                        if (key == null || !key.f) {
                            c(key);
                        }
                    }
                    if (key.e()) {
                        if (!this.d.get()) {
                            g(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.mqjh(\"%s\");}", key.e));
                        }
                        String str = String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.sglu(%s);}", key.h());
                        if (Build.VERSION.SDK_INT >= 19) {
                            g().evaluateJavascript(str, new ValueCallback<String>() { // from class: com.moat.analytics.mobile.inm.j.1
                                @Override // android.webkit.ValueCallback
                                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                                public void onReceiveValue(String str2) {
                                    String str3 = "null";
                                    if (str2 != null && !str2.equalsIgnoreCase("null") && !str2.equalsIgnoreCase("false")) {
                                        if (!str2.equalsIgnoreCase(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
                                            p.a(3, "JavaScriptBridge", j.this, "Received unusual value from Javascript:" + str2);
                                            return;
                                        }
                                        if (!j.this.f) {
                                            j.this.f = true;
                                            p.a(3, "JavaScriptBridge", j.this, "Javascript has found ad");
                                            j.this.a();
                                        }
                                        j.this.a = 0;
                                        return;
                                    }
                                    j jVar = j.this;
                                    StringBuilder sb = new StringBuilder("Received value is:");
                                    if (str2 != null) {
                                        str3 = "(String)" + str2;
                                    }
                                    sb.append(str3);
                                    p.a(3, "JavaScriptBridge", jVar, sb.toString());
                                    if (j.this.a >= 150) {
                                        p.a(3, "JavaScriptBridge", j.this, "Giving up on finding ad");
                                        j.this.b();
                                    }
                                    j.b(j.this);
                                    if (str2 == null || !str2.equalsIgnoreCase("false") || j.this.e) {
                                        return;
                                    }
                                    j.this.e = true;
                                    p.a(3, "JavaScriptBridge", j.this, "Bridge connection established");
                                }
                            });
                        } else {
                            g().loadUrl(str);
                        }
                    }
                }
                return;
            }
            StringBuilder sb = new StringBuilder("WebView became null");
            sb.append(g() == null ? "" : "based on null url");
            sb.append(", stopping tracking loop");
            p.a(3, "JavaScriptBridge", this, sb.toString());
            b();
        } catch (Exception e) {
            m.a(e);
            b();
        }
    }

    private void e(String str) {
        if (this.m.size() >= 50) {
            this.m.subList(0, 25).clear();
        }
        this.m.add(str);
    }

    private void f(String str) {
        if (this.d.get()) {
            g(str);
        } else {
            e(str);
        }
    }

    private boolean f() {
        return g() != null;
    }

    private WebView g() {
        return this.h.get();
    }

    @UiThread
    private void g(String str) {
        if (this.g) {
            p.a(3, "JavaScriptBridge", this, "Can't send, already cleaned up");
            return;
        }
        if (f()) {
            p.b(2, "JavaScriptBridge", this, str);
            if (Build.VERSION.SDK_INT >= 19) {
                g().evaluateJavascript(str, null);
            } else {
                g().loadUrl(str);
            }
        }
    }

    private String h() {
        try {
            s.a aVarD = s.d();
            s.b bVarE = s.e();
            HashMap map = new HashMap();
            String strA = aVarD.a();
            String strB = aVarD.b();
            String strC = aVarD.c();
            String string = Integer.toString(Build.VERSION.SDK_INT);
            String strB2 = s.b();
            a aVar = this.n;
            a aVar2 = a.WEBVIEW;
            String str = AppEventsConstants.EVENT_PARAM_VALUE_NO;
            Object obj = aVar == aVar2 ? AppEventsConstants.EVENT_PARAM_VALUE_NO : "1";
            Object obj2 = bVarE.e ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO;
            Object obj3 = bVarE.d ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO;
            if (!((k) MoatAnalytics.getInstance()).b()) {
                str = "1";
            }
            map.put("versionHash", "c334ae83accfebb8da23104450c896463c9cfab7");
            map.put("appName", strA);
            map.put("namespace", "INM");
            map.put("version", "2.5.0");
            map.put("deviceOS", string);
            map.put("isNative", obj);
            map.put(Cookie.APP_ID, strB);
            map.put("source", strC);
            map.put("carrier", bVarE.b);
            map.put("sim", bVarE.a);
            map.put("phone", String.valueOf(bVarE.c));
            map.put("buildFp", Build.FINGERPRINT);
            map.put("buildModel", Build.MODEL);
            map.put("buildMfg", Build.MANUFACTURER);
            map.put("buildBrand", Build.BRAND);
            map.put("buildProduct", Build.PRODUCT);
            map.put("buildTags", Build.TAGS);
            map.put("f1", obj3);
            map.put("f2", obj2);
            map.put("locationEnabled", str);
            if (strB2 != null) {
                map.put("aqzx", strB2);
            }
            return new JSONObject(map).toString();
        } catch (Exception unused) {
            return "{}";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        p.a(3, "JavaScriptBridge", this, "Stopping metadata reporting loop");
        i.a().a(this);
        LocalBroadcastManager.getInstance(s.c()).unregisterReceiver(this.o);
    }

    void a() {
        p.a(3, "JavaScriptBridge", this, "webViewReady");
        if (this.d.compareAndSet(false, true)) {
            p.a(3, "JavaScriptBridge", this, "webViewReady first time");
            i();
            Iterator<String> it = this.m.iterator();
            while (it.hasNext()) {
                g(it.next());
            }
            this.m.clear();
        }
        c();
    }

    void a(b bVar) {
        if (bVar != null) {
            p.a(3, "JavaScriptBridge", this, "adding tracker" + bVar.e);
            this.i.put(bVar, "");
        }
    }

    void a(String str) {
        f(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.crts(%s);}", str));
    }

    @UiThread
    void a(String str, JSONObject jSONObject) {
        if (this.g) {
            p.a(3, "JavaScriptBridge", this, "Can't dispatch, already cleaned up");
            return;
        }
        String string = jSONObject.toString();
        if (this.d.get() && f()) {
            g(String.format("javascript:%s.dispatchEvent(%s);", str, string));
        } else {
            this.j.add(string);
        }
    }

    void b() {
        p.a(3, "JavaScriptBridge", this, "Cleaning up");
        this.g = true;
        i();
        Iterator<Map.Entry<b, String>> it = this.i.entrySet().iterator();
        while (it.hasNext()) {
            d(it.next().getKey());
        }
        this.i.clear();
        LocalBroadcastManager.getInstance(s.c()).unregisterReceiver(this.p);
    }

    void b(b bVar) {
        if (d("startTracking")) {
            p.a(3, "JavaScriptBridge", this, "Starting tracking on tracker" + bVar.e);
            g(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.mqjh(\"%s\");}", bVar.e));
            i.a().a(s.c(), bVar);
        }
    }

    void b(String str) {
        p.a(3, "JavaScriptBridge", this, "markUserInteractionEvent:" + str);
        f(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.ucbx(%s);}", str));
    }

    void c(b bVar) {
        m mVar = null;
        if (!this.g) {
            try {
                if (d("stopTracking")) {
                    try {
                        p.a(3, "JavaScriptBridge", this, "Ending tracking on tracker" + bVar.e);
                        g(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.egpw(\"%s\");}", bVar.e));
                    } catch (Exception e) {
                        p.a("JavaScriptBridge", this, "Failed to end impression.", e);
                    }
                }
            } catch (m e2) {
                mVar = e2;
            }
            if (this.n == a.NATIVE_DISPLAY) {
                d(bVar);
            } else {
                b();
            }
            this.i.remove(bVar);
        }
        if (mVar != null) {
            throw mVar;
        }
    }

    void c(String str) {
        p.a(3, "JavaScriptBridge", this, "flushDispatchQueue");
        if (this.j.size() >= 200) {
            LinkedList linkedList = new LinkedList();
            for (int i = 0; i < 10; i++) {
                linkedList.addFirst(this.j.removeFirst());
            }
            int iMin = Math.min(Math.min(this.j.size() / 200, 10) + 200, this.j.size());
            for (int i2 = 0; i2 < iMin; i2++) {
                this.j.removeFirst();
            }
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                this.j.addFirst((String) it.next());
            }
        }
        if (!this.j.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            String str2 = "";
            int i3 = 1;
            while (!this.j.isEmpty() && i3 < 200) {
                i3++;
                String strRemoveFirst = this.j.removeFirst();
                if (sb.length() + strRemoveFirst.length() > 2000) {
                    break;
                }
                sb.append(str2);
                sb.append(strRemoveFirst);
                str2 = ",";
            }
            g(String.format("javascript:%s.dispatchMany([%s])", str, sb.toString()));
        }
        this.j.clear();
    }

    protected void finalize() throws Throwable {
        try {
            super.finalize();
            p.a(3, "JavaScriptBridge", this, "finalize");
            b();
        } catch (Exception e) {
            m.a(e);
        }
    }
}
