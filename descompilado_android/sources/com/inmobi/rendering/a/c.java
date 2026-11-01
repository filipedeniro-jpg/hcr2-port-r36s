package com.inmobi.rendering.a;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.inmobi.a.n;
import com.inmobi.commons.core.configs.h;
import com.inmobi.commons.core.network.NetworkError;
import com.inmobi.commons.core.network.WebViewNetworkTask;
import com.inmobi.commons.core.network.WebViewNetworkTask.NetworkTaskWebView;
import com.inmobi.commons.core.network.e;
import com.inmobi.commons.core.utilities.g;
import com.my.target.aq;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: ClickManager.java */
/* JADX INFO: loaded from: classes2.dex */
public class c implements com.inmobi.commons.core.configs.b.c {
    private static final String a = "c";
    private static c b;
    private static ExecutorService d;
    private static a e;
    private static HandlerThread f;
    private static com.inmobi.rendering.a.b h;
    private static com.inmobi.ads.c.e j;
    private long l = 0;
    private final d m = new d() { // from class: com.inmobi.rendering.a.c.7
        @Override // com.inmobi.rendering.a.c.d
        public final void a(com.inmobi.rendering.a.a aVar) {
            if (aVar != null) {
                String unused = c.a;
                StringBuilder sb = new StringBuilder("Processing click (");
                sb.append(aVar.b);
                sb.append(") completed");
                com.inmobi.rendering.a.b unused2 = c.h;
                com.inmobi.rendering.a.b.a(aVar);
                try {
                    HashMap map = new HashMap();
                    map.put("url", aVar.b);
                    map.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - c.this.l));
                    com.inmobi.commons.core.e.b.a();
                    com.inmobi.commons.core.e.b.a("ads", "PingLatency", map);
                } catch (Exception e2) {
                    String unused3 = c.a;
                    StringBuilder sb2 = new StringBuilder("Error in submitting telemetry event : (");
                    sb2.append(e2.getMessage());
                    sb2.append(")");
                }
            }
        }

        @Override // com.inmobi.rendering.a.c.d
        public final void b(com.inmobi.rendering.a.a aVar) {
            if (aVar != null) {
                String unused = c.a;
                StringBuilder sb = new StringBuilder("Pinging click (");
                sb.append(aVar.b);
                sb.append(") failed! Updating retry counts and timestamps ...");
                c.a(aVar);
                c.this.b();
            }
        }
    };
    private static final Object c = new Object();
    private static List<com.inmobi.rendering.a.a> g = new ArrayList();
    private static AtomicBoolean i = new AtomicBoolean(false);
    private static final Object k = new Object();

    /* JADX INFO: compiled from: ClickManager.java */
    interface d {
        void a(com.inmobi.rendering.a.a aVar);

        void b(com.inmobi.rendering.a.a aVar);
    }

    public static c a() {
        c cVar = b;
        if (cVar == null) {
            synchronized (c) {
                cVar = b;
                if (cVar == null) {
                    cVar = new c();
                    b = cVar;
                }
            }
        }
        return cVar;
    }

    @Override // com.inmobi.commons.core.configs.b.c
    public final void a(com.inmobi.commons.core.configs.a aVar) {
        j = ((com.inmobi.ads.c) aVar).h;
    }

    public final void b() {
        try {
            if (com.inmobi.commons.core.utilities.d.a()) {
                synchronized (k) {
                    if (i.compareAndSet(false, true)) {
                        if (f == null) {
                            HandlerThread handlerThread = new HandlerThread("pingHandlerThread");
                            f = handlerThread;
                            handlerThread.start();
                        }
                        if (e == null) {
                            e = new a(f.getLooper());
                        }
                        if (com.inmobi.rendering.a.b.a()) {
                            i.set(false);
                            i();
                        } else {
                            Message messageObtain = Message.obtain();
                            messageObtain.what = 1;
                            e.sendMessage(messageObtain);
                        }
                    }
                }
            }
        } catch (Exception e2) {
            new StringBuilder("SDK encountered unexpected error in starting the ping component; ").append(e2.getMessage());
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.inmobi.rendering.a.c$2] */
    public final void a(final String str, final Map<String, String> map) {
        new Thread() { // from class: com.inmobi.rendering.a.c.2
            final /* synthetic */ boolean c = true;

            @Override // java.lang.Thread, java.lang.Runnable
            public final void run() {
                try {
                    h hVar = new h();
                    com.inmobi.commons.core.configs.b.a().a(hVar, (com.inmobi.commons.core.configs.b.c) null);
                    if (hVar.g) {
                        return;
                    }
                    com.inmobi.rendering.a.a aVar = new com.inmobi.rendering.a.a(str, (Map<String, String>) map, this.c, c.j.a + 1);
                    String unused = c.a;
                    StringBuilder sb = new StringBuilder("Received click (");
                    sb.append(aVar.b);
                    sb.append(") for pinging over HTTP");
                    c.a(c.this, aVar);
                } catch (Exception e2) {
                    String unused2 = c.a;
                    new StringBuilder("SDK encountered unexpected error in pinging click; ").append(e2.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void i() {
        try {
            i.set(false);
            synchronized (k) {
                if (!i.get()) {
                    if (f != null) {
                        f.getLooper().quit();
                        f.interrupt();
                        f = null;
                        e = null;
                    }
                    g.clear();
                }
            }
        } catch (Exception e2) {
            new StringBuilder("SDK encountered unexpected error in stopping the ping component; ").append(e2.getMessage());
        }
    }

    public c() {
        try {
            d = Executors.newFixedThreadPool(5);
            HandlerThread handlerThread = new HandlerThread("pingHandlerThread");
            f = handlerThread;
            handlerThread.start();
            e = new a(f.getLooper());
            com.inmobi.ads.c cVar = new com.inmobi.ads.c();
            com.inmobi.commons.core.configs.b.a().a(cVar, this);
            j = cVar.h;
            h = new com.inmobi.rendering.a.b();
            g.a().a(new g.b() { // from class: com.inmobi.rendering.a.c.5
                @Override // com.inmobi.commons.core.utilities.g.b
                public final void a(boolean z) {
                    if (z) {
                        c.this.b();
                    }
                }
            });
            if (Build.VERSION.SDK_INT >= 23) {
                g.a().a("android.os.action.DEVICE_IDLE_MODE_CHANGED", new g.b() { // from class: com.inmobi.rendering.a.c.6
                    @Override // com.inmobi.commons.core.utilities.g.b
                    public final void a(boolean z) {
                        if (z) {
                            return;
                        }
                        c.this.b();
                    }
                });
            }
        } catch (Exception e2) {
            new StringBuilder("SDK encountered unexpected error in initializing the ping component; ").append(e2.getMessage());
        }
    }

    /* JADX INFO: renamed from: com.inmobi.rendering.a.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ClickManager.java */
    static final class C0083c {
        private d a;

        public C0083c(d dVar) {
            this.a = dVar;
        }

        public final void a(com.inmobi.rendering.a.a aVar) {
            try {
                com.inmobi.commons.core.network.c cVar = new com.inmobi.commons.core.network.c(HttpRequest.METHOD_GET, aVar.b);
                HashMap mapC = c.c(aVar);
                if (!mapC.isEmpty()) {
                    cVar.a(mapC);
                }
                cVar.u = false;
                cVar.b(aVar.c);
                cVar.t = aVar.i;
                cVar.r = c.j.c * 1000;
                cVar.s = c.j.c * 1000;
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                com.inmobi.commons.core.network.d dVarA = new e(cVar).a();
                try {
                    n.a().a(cVar.g());
                    n.a().b(dVarA.c());
                    n.a().c(SystemClock.elapsedRealtime() - jElapsedRealtime);
                } catch (Exception e) {
                    String unused = c.a;
                    new StringBuilder("Error in setting request-response data size. ").append(e.getMessage());
                }
                if (!dVarA.a()) {
                    this.a.a(aVar);
                    return;
                }
                NetworkError.ErrorCode errorCode = dVarA.b.a;
                if (!aVar.i && (NetworkError.ErrorCode.HTTP_SEE_OTHER == errorCode || NetworkError.ErrorCode.HTTP_MOVED_TEMP == errorCode)) {
                    this.a.a(aVar);
                    return;
                }
                d dVar = this.a;
                NetworkError networkError = dVarA.b;
                dVar.b(aVar);
            } catch (Exception e2) {
                String unused2 = c.a;
                new StringBuilder("SDK encountered unexpected error in executing ping over HTTP; ").append(e2.getMessage());
                d dVar2 = this.a;
                new NetworkError(NetworkError.ErrorCode.UNKNOWN_ERROR, "Unknown error");
                dVar2.b(aVar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static HashMap<String, String> c(com.inmobi.rendering.a.a aVar) {
        HashMap<String, String> map = new HashMap<>();
        try {
            int i2 = (j.a - aVar.f) + 1;
            if (i2 > 0) {
                map.put("X-im-retry-count", String.valueOf(i2));
            }
        } catch (Exception unused) {
        }
        return map;
    }

    /* JADX INFO: compiled from: ClickManager.java */
    static final class b {
        d a;

        public b(d dVar) {
            this.a = dVar;
        }

        public final void a(com.inmobi.rendering.a.a aVar) {
            aVar.g.set(false);
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new AnonymousClass1(aVar, handler));
        }

        /* JADX INFO: renamed from: com.inmobi.rendering.a.c$b$1, reason: invalid class name */
        /* JADX INFO: compiled from: ClickManager.java */
        final class AnonymousClass1 implements Runnable {
            final /* synthetic */ com.inmobi.rendering.a.a a;
            final /* synthetic */ Handler b;

            AnonymousClass1(com.inmobi.rendering.a.a aVar, Handler handler) {
                this.a = aVar;
                this.b = handler;
            }

            @Override // java.lang.Runnable
            public final void run() {
                com.inmobi.commons.core.network.c cVar = new com.inmobi.commons.core.network.c(HttpRequest.METHOD_GET, this.a.b);
                HashMap mapC = c.c(this.a);
                if (!mapC.isEmpty()) {
                    cVar.a(mapC);
                }
                WebViewNetworkTask webViewNetworkTask = new WebViewNetworkTask(cVar, new C00801());
                try {
                    webViewNetworkTask.c = webViewNetworkTask.new NetworkTaskWebView(com.inmobi.commons.a.a.b());
                    webViewNetworkTask.c.setWebViewClient(webViewNetworkTask.b);
                    webViewNetworkTask.c.getSettings().setJavaScriptEnabled(true);
                    webViewNetworkTask.c.getSettings().setCacheMode(2);
                    webViewNetworkTask.c.loadUrl(webViewNetworkTask.a.e(), webViewNetworkTask.a.d());
                } catch (Exception e) {
                    new StringBuilder("SDK encountered unexpected error in WebViewNetworkTask.execute() method; ").append(e.getMessage());
                }
            }

            /* JADX INFO: renamed from: com.inmobi.rendering.a.c$b$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: ClickManager.java */
            final class C00801 extends WebViewClient {
                AtomicBoolean a;
                boolean b;

                C00801() {
                }

                @Override // android.webkit.WebViewClient
                public final void onPageStarted(final WebView webView, String str, Bitmap bitmap) {
                    this.a = new AtomicBoolean(false);
                    this.b = false;
                    new Thread(new Runnable() { // from class: com.inmobi.rendering.a.c.b.1.1.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            try {
                                Thread.sleep(c.j.c * 1000);
                            } catch (InterruptedException unused) {
                            }
                            if (C00801.this.a.get()) {
                                return;
                            }
                            String unused2 = c.a;
                            StringBuilder sb = new StringBuilder("Pinging click (");
                            sb.append(AnonymousClass1.this.a.b);
                            sb.append(") via WebView timed out!");
                            AnonymousClass1.this.a.g.set(true);
                            AnonymousClass1.this.b.post(new Runnable() { // from class: com.inmobi.rendering.a.c.b.1.1.1.1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    try {
                                        WebViewNetworkTask.NetworkTaskWebView networkTaskWebView = (WebViewNetworkTask.NetworkTaskWebView) webView;
                                        if (networkTaskWebView == null || networkTaskWebView.a) {
                                            return;
                                        }
                                        webView.stopLoading();
                                    } catch (Throwable th) {
                                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(th));
                                    }
                                }
                            });
                            b.this.a.b(AnonymousClass1.this.a);
                        }
                    }).start();
                }

                @Override // android.webkit.WebViewClient
                public final void onPageFinished(WebView webView, String str) {
                    this.a.set(true);
                    if (this.b || AnonymousClass1.this.a.g.get()) {
                        return;
                    }
                    b.this.a.a(AnonymousClass1.this.a);
                }

                @Override // android.webkit.WebViewClient
                @TargetApi(22)
                public final void onReceivedError(WebView webView, int i, String str, String str2) {
                    this.b = true;
                    b.this.a.b(AnonymousClass1.this.a);
                }

                @Override // android.webkit.WebViewClient
                @TargetApi(23)
                public final void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                    this.b = true;
                    b.this.a.b(AnonymousClass1.this.a);
                }

                @Override // android.webkit.WebViewClient
                @TargetApi(23)
                public final void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
                    this.b = true;
                    b.this.a.b(AnonymousClass1.this.a);
                }

                @Override // android.webkit.WebViewClient
                public final boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                    return (Build.VERSION.SDK_INT < 21 || AnonymousClass1.this.a.i || webResourceRequest.getUrl().toString().equals(AnonymousClass1.this.a.b)) ? false : true;
                }

                @Override // android.webkit.WebViewClient
                public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
                    return (AnonymousClass1.this.a.i || str.equals(AnonymousClass1.this.a.b)) ? false : true;
                }
            }
        }
    }

    /* JADX INFO: compiled from: ClickManager.java */
    final class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            try {
                int i = message.what;
                int i2 = 3;
                if (i == 1) {
                    h hVar = new h();
                    String string = null;
                    com.inmobi.commons.core.configs.b.a().a(hVar, (com.inmobi.commons.core.configs.b.c) null);
                    if (hVar.g) {
                        return;
                    }
                    com.inmobi.rendering.a.b unused = c.h;
                    int i3 = c.j.e;
                    int i4 = c.j.b;
                    ArrayList arrayList = new ArrayList();
                    com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
                    if (bVarA.a(aq.a.dF) != 0) {
                        if (-1 != i3) {
                            string = Integer.toString(i3);
                        }
                        List<ContentValues> listA = bVarA.a(aq.a.dF, com.inmobi.rendering.a.b.a, null, null, "ts", "ts < " + (System.currentTimeMillis() - ((long) i4)), "ts ASC ", string);
                        bVarA.b();
                        Iterator<ContentValues> it = listA.iterator();
                        while (it.hasNext()) {
                            arrayList.add(com.inmobi.rendering.a.b.a(it.next()));
                        }
                    }
                    List unused2 = c.g = arrayList;
                    if (c.g.isEmpty()) {
                        com.inmobi.rendering.a.b unused3 = c.h;
                        if (com.inmobi.rendering.a.b.a()) {
                            c.i.set(false);
                            return;
                        }
                        Message messageObtain = Message.obtain();
                        messageObtain.what = 1;
                        sendMessageDelayed(messageObtain, c.j.b * 1000);
                        return;
                    }
                    com.inmobi.rendering.a.a aVar = (com.inmobi.rendering.a.a) c.g.get(0);
                    Message messageObtain2 = Message.obtain();
                    if (!aVar.h) {
                        i2 = 2;
                    }
                    messageObtain2.what = i2;
                    messageObtain2.obj = aVar;
                    long jCurrentTimeMillis = System.currentTimeMillis() - aVar.d;
                    if (jCurrentTimeMillis < c.j.b * 1000) {
                        sendMessageDelayed(messageObtain2, ((long) (c.j.b * 1000)) - jCurrentTimeMillis);
                        return;
                    } else {
                        sendMessage(messageObtain2);
                        return;
                    }
                }
                if (i == 2) {
                    if (!com.inmobi.commons.core.utilities.d.a()) {
                        c.i.set(false);
                        c.i();
                        return;
                    }
                    com.inmobi.rendering.a.a aVar2 = (com.inmobi.rendering.a.a) message.obj;
                    if (aVar2.f == 0) {
                        a(aVar2, 1);
                        return;
                    }
                    if (aVar2.a(c.j.f)) {
                        a(aVar2, 2);
                        return;
                    }
                    int i5 = (c.j.a - aVar2.f) + 1;
                    if (i5 == 0) {
                        String unused4 = c.a;
                        StringBuilder sb = new StringBuilder("Pinging click (");
                        sb.append(aVar2.b);
                        sb.append(") over HTTP");
                    } else {
                        String unused5 = c.a;
                        StringBuilder sb2 = new StringBuilder("Retry attempt #");
                        sb2.append(i5);
                        sb2.append(" for click (");
                        sb2.append(aVar2.b);
                        sb2.append(") over HTTP");
                    }
                    new C0083c(new d() { // from class: com.inmobi.rendering.a.c.a.1
                        @Override // com.inmobi.rendering.a.c.d
                        public final void a(com.inmobi.rendering.a.a aVar3) {
                            a.a(a.this, aVar3);
                        }

                        @Override // com.inmobi.rendering.a.c.d
                        public final void b(com.inmobi.rendering.a.a aVar3) {
                            String unused6 = c.a;
                            StringBuilder sb3 = new StringBuilder("Pinging click (");
                            sb3.append(aVar3.b);
                            sb3.append(") via HTTP failed ...");
                            c.a(aVar3);
                            a.b(a.this, aVar3);
                        }
                    }).a(aVar2);
                    return;
                }
                if (i == 3) {
                    if (!com.inmobi.commons.core.utilities.d.a()) {
                        c.i.set(false);
                        c.i();
                        return;
                    }
                    com.inmobi.rendering.a.a aVar3 = (com.inmobi.rendering.a.a) message.obj;
                    if (aVar3.f == 0) {
                        a(aVar3, 1);
                        return;
                    }
                    if (aVar3.a(c.j.f)) {
                        a(aVar3, 2);
                        return;
                    }
                    int i6 = (c.j.a - aVar3.f) + 1;
                    if (i6 == 0) {
                        String unused6 = c.a;
                        StringBuilder sb3 = new StringBuilder("Pinging click (");
                        sb3.append(aVar3.b);
                        sb3.append(") in WebView");
                    } else {
                        String unused7 = c.a;
                        StringBuilder sb4 = new StringBuilder("Retry attempt #");
                        sb4.append(i6);
                        sb4.append(" for click (");
                        sb4.append(aVar3.b);
                        sb4.append(") using WebView");
                    }
                    new b(new d() { // from class: com.inmobi.rendering.a.c.a.2
                        @Override // com.inmobi.rendering.a.c.d
                        public final void a(com.inmobi.rendering.a.a aVar4) {
                            a.a(a.this, aVar4);
                        }

                        @Override // com.inmobi.rendering.a.c.d
                        public final void b(com.inmobi.rendering.a.a aVar4) {
                            String unused8 = c.a;
                            StringBuilder sb5 = new StringBuilder("Pinging click (");
                            sb5.append(aVar4.b);
                            sb5.append(") via WebView failed ...");
                            c.a(aVar4);
                            a.b(a.this, aVar4);
                        }
                    }).a(aVar3);
                    return;
                }
                if (i != 4) {
                    if (i != 5) {
                        return;
                    }
                    com.inmobi.rendering.a.a aVar4 = (com.inmobi.rendering.a.a) message.obj;
                    HashMap map = new HashMap();
                    map.put("pingUrl", aVar4.b);
                    int i7 = message.arg1;
                    if (i7 == 1) {
                        map.put("errorCode", "MaxRetryCountReached");
                    } else if (i7 == 2) {
                        map.put("errorCode", "ExpiredClick");
                    }
                    try {
                        com.inmobi.commons.core.e.b.a();
                        com.inmobi.commons.core.e.b.a("ads", "PingDiscarded", map);
                    } catch (Exception e) {
                        String unused8 = c.a;
                        StringBuilder sb5 = new StringBuilder("Error in submitting telemetry event : (");
                        sb5.append(e.getMessage());
                        sb5.append(")");
                    }
                }
                com.inmobi.rendering.a.a aVar5 = (com.inmobi.rendering.a.a) message.obj;
                String unused9 = c.a;
                StringBuilder sb6 = new StringBuilder("Processing click (");
                sb6.append(aVar5.b);
                sb6.append(") completed");
                com.inmobi.rendering.a.b unused10 = c.h;
                com.inmobi.rendering.a.b.a(aVar5);
                c.g.remove(aVar5);
                if (c.g.isEmpty()) {
                    com.inmobi.rendering.a.b unused11 = c.h;
                    if (com.inmobi.rendering.a.b.a()) {
                        String unused12 = c.a;
                        c.i.set(false);
                        return;
                    } else {
                        Message messageObtain3 = Message.obtain();
                        messageObtain3.what = 1;
                        sendMessage(messageObtain3);
                        return;
                    }
                }
                com.inmobi.rendering.a.a aVar6 = (com.inmobi.rendering.a.a) c.g.get(0);
                Message messageObtain4 = Message.obtain();
                if (!aVar6.h) {
                    i2 = 2;
                }
                messageObtain4.what = i2;
                messageObtain4.obj = aVar6;
                sendMessage(messageObtain4);
            } catch (Exception e2) {
                String unused13 = c.a;
                new StringBuilder("SDK encountered unexpected error in processing ping; ").append(e2.getMessage());
            }
        }

        private void a(com.inmobi.rendering.a.a aVar, int i) {
            Message messageObtain = Message.obtain();
            messageObtain.what = 5;
            messageObtain.obj = aVar;
            messageObtain.arg1 = i;
            sendMessage(messageObtain);
        }

        static /* synthetic */ void a(a aVar, com.inmobi.rendering.a.a aVar2) {
            Message messageObtain = Message.obtain();
            messageObtain.what = 4;
            messageObtain.obj = aVar2;
            aVar.sendMessage(messageObtain);
        }

        static /* synthetic */ void b(a aVar, com.inmobi.rendering.a.a aVar2) {
            int iIndexOf = c.g.indexOf(aVar2);
            if (-1 != iIndexOf) {
                com.inmobi.rendering.a.a aVar3 = (com.inmobi.rendering.a.a) c.g.get(iIndexOf == c.g.size() + (-1) ? 0 : iIndexOf + 1);
                Message messageObtain = Message.obtain();
                messageObtain.what = aVar3.h ? 3 : 2;
                messageObtain.obj = aVar3;
                if (System.currentTimeMillis() - aVar3.d < c.j.b * 1000) {
                    aVar.sendMessageDelayed(messageObtain, c.j.b * 1000);
                } else {
                    aVar.sendMessage(messageObtain);
                }
            }
        }
    }

    static /* synthetic */ void a(c cVar, final com.inmobi.rendering.a.a aVar) {
        h.a(aVar, j.d);
        if (!com.inmobi.commons.core.utilities.d.a()) {
            i.set(false);
            i();
        } else {
            d.submit(new Runnable() { // from class: com.inmobi.rendering.a.c.4
                @Override // java.lang.Runnable
                public final void run() {
                    c.this.l = SystemClock.elapsedRealtime();
                    if (aVar.h) {
                        new b(c.this.m).a(aVar);
                    } else {
                        new C0083c(c.this.m).a(aVar);
                    }
                }
            });
        }
    }

    static /* synthetic */ void a(com.inmobi.rendering.a.a aVar) {
        if (aVar.f > 0) {
            aVar.f--;
            aVar.d = System.currentTimeMillis();
            com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
            bVarA.b(aq.a.dF, com.inmobi.rendering.a.b.b(aVar), "id = ?", new String[]{String.valueOf(aVar.a)});
            bVarA.b();
        }
    }
}
