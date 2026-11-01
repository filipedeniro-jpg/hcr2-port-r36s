package com.adcolony.sdk;

import android.util.Log;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class ac {
    static boolean a = false;
    static final int b = 4000;
    static final int c = 4;
    static final int d = 3;
    static final int e = 2;
    static final int f = 1;
    static final int g = 0;
    static final int h = -1;
    static int i = 3;
    static al l;
    static JSONObject j = y.a();
    static int k = 1;
    private static ExecutorService m = null;
    private static final Queue<Runnable> n = new ConcurrentLinkedQueue();

    ac() {
    }

    static void a(int i2, String str, boolean z) {
        a(0, i2, str, z);
    }

    static void a(int i2, int i3, String str, boolean z) {
        if (a(b(i2, i3, str, z))) {
            return;
        }
        synchronized (n) {
            n.add(b(i2, i3, str, z));
        }
    }

    private static Runnable b(final int i2, final int i3, final String str, final boolean z) {
        return new Runnable() { // from class: com.adcolony.sdk.ac.1
            @Override // java.lang.Runnable
            public void run() {
                ac.a(i2, str, i3);
                int i4 = 0;
                while (i4 <= str.length() / 4000) {
                    int i5 = i4 * 4000;
                    i4++;
                    int length = i4 * 4000;
                    if (length > str.length()) {
                        length = str.length();
                    }
                    if (i3 == 3 && ac.a(y.f(ac.j, Integer.toString(i2)), 3, z)) {
                        Log.d("AdColony [TRACE]", str.substring(i5, length));
                    } else if (i3 == 2 && ac.a(y.f(ac.j, Integer.toString(i2)), 2, z)) {
                        Log.i("AdColony [INFO]", str.substring(i5, length));
                    } else if (i3 == 1 && ac.a(y.f(ac.j, Integer.toString(i2)), 1, z)) {
                        Log.w("AdColony [WARNING]", str.substring(i5, length));
                    } else if (i3 == 0 && ac.a(y.f(ac.j, Integer.toString(i2)), 0, z)) {
                        Log.e("AdColony [ERROR]", str.substring(i5, length));
                    } else if (i3 == -1 && ac.i >= -1) {
                        Log.e("AdColony [FATAL]", str.substring(i5, length));
                    }
                }
            }
        };
    }

    static boolean a(JSONObject jSONObject, int i2, boolean z) {
        int iC = y.c(jSONObject, "print_level");
        boolean zD = y.d(jSONObject, "log_private");
        if (jSONObject.length() == 0) {
            iC = i;
            zD = a;
        }
        return (!z || zD) && iC != 4 && iC >= i2;
    }

    static void a() {
        ExecutorService executorService = m;
        if (executorService == null || executorService.isShutdown() || m.isTerminated()) {
            m = Executors.newSingleThreadExecutor();
        }
        synchronized (n) {
            while (!n.isEmpty()) {
                a(n.poll());
            }
        }
    }

    static void b() {
        ExecutorService executorService = m;
        if (executorService == null) {
            return;
        }
        executorService.shutdown();
        try {
            if (m.awaitTermination(1L, TimeUnit.SECONDS)) {
                return;
            }
            m.shutdownNow();
            if (m.awaitTermination(1L, TimeUnit.SECONDS)) {
                return;
            }
            System.err.println("ADCLogManager: ScheduledExecutorService did not terminate");
        } catch (InterruptedException unused) {
            m.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    static boolean a(JSONObject jSONObject, int i2) {
        int iC = y.c(jSONObject, "send_level");
        if (jSONObject.length() == 0) {
            iC = k;
        }
        return iC >= i2 && iC != 4;
    }

    static void a(HashMap<String, Object> map) {
        try {
            l = new al(new z(new URL("https://wd.adcolony.com/logs")), Executors.newSingleThreadScheduledExecutor(), map);
            l.a(5L, TimeUnit.SECONDS);
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
        }
    }

    static void a(int i2, String str, int i3) {
        if (l == null) {
            return;
        }
        if (i3 == 3 && a(y.f(j, Integer.toString(i2)), 3)) {
            l.c(str);
            return;
        }
        if (i3 == 2 && a(y.f(j, Integer.toString(i2)), 2)) {
            l.d(str);
            return;
        }
        if (i3 == 1 && a(y.f(j, Integer.toString(i2)), 1)) {
            l.e(str);
        } else if (i3 == 0 && a(y.f(j, Integer.toString(i2)), 0)) {
            l.f(str);
        }
    }

    static void a(s sVar) {
        al alVar = l;
        if (alVar == null || k == 4) {
            return;
        }
        alVar.a(sVar);
    }

    static void c() {
        a.a("Log.set_log_level", new ah() { // from class: com.adcolony.sdk.ac.3
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                ac.i = y.c(afVar.c(), "level");
            }
        });
        a.a("Log.public.trace", new ah() { // from class: com.adcolony.sdk.ac.4
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                ac.a(y.c(afVar.c(), "module"), 3, y.b(afVar.c(), "message"), false);
            }
        });
        a.a("Log.private.trace", new ah() { // from class: com.adcolony.sdk.ac.5
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                ac.a(y.c(afVar.c(), "module"), 3, y.b(afVar.c(), "message"), true);
            }
        });
        a.a("Log.public.info", new ah() { // from class: com.adcolony.sdk.ac.6
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                ac.a(y.c(afVar.c(), "module"), 2, y.b(afVar.c(), "message"), false);
            }
        });
        a.a("Log.private.info", new ah() { // from class: com.adcolony.sdk.ac.7
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                ac.a(y.c(afVar.c(), "module"), 2, y.b(afVar.c(), "message"), true);
            }
        });
        a.a("Log.public.warning", new ah() { // from class: com.adcolony.sdk.ac.8
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                ac.a(y.c(afVar.c(), "module"), 1, y.b(afVar.c(), "message"), false);
            }
        });
        a.a("Log.private.warning", new ah() { // from class: com.adcolony.sdk.ac.9
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                ac.a(y.c(afVar.c(), "module"), 1, y.b(afVar.c(), "message"), true);
            }
        });
        a.a("Log.public.error", new ah() { // from class: com.adcolony.sdk.ac.10
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                ac.a(y.c(afVar.c(), "module"), 0, y.b(afVar.c(), "message"), false);
            }
        });
        a.a("Log.private.error", new ah() { // from class: com.adcolony.sdk.ac.2
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                ac.a(y.c(afVar.c(), "module"), 0, y.b(afVar.c(), "message"), true);
            }
        });
    }

    void a(JSONArray jSONArray) {
        j = b(jSONArray);
    }

    JSONObject b(JSONArray jSONArray) {
        JSONObject jSONObjectA = y.a();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject jSONObjectD = y.d(jSONArray, i2);
            y.a(jSONObjectA, Integer.toString(y.c(jSONObjectD, "id")), jSONObjectD);
        }
        return jSONObjectA;
    }

    private static boolean a(Runnable runnable) {
        try {
            if (m == null || m.isShutdown() || m.isTerminated()) {
                return false;
            }
            m.submit(runnable);
            return true;
        } catch (RejectedExecutionException unused) {
            Log.e("ADCLogError", "Internal error when submitting log to executor service.");
            return false;
        }
    }
}
