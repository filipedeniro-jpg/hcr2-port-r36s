package com.inmobi.commons.core.configs;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.inmobi.commons.core.utilities.Logger;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

/* JADX INFO: compiled from: ConfigComponent.java */
/* JADX INFO: loaded from: classes2.dex */
public class b {
    private static final String a = "b";
    private static Map<com.inmobi.commons.core.configs.a, ArrayList<WeakReference<c>>> b;
    private static h c;
    private static d d;
    private HandlerThread e;
    private HandlerC0076b f;
    private boolean g;

    /* JADX INFO: compiled from: ConfigComponent.java */
    public interface c {
        void a(com.inmobi.commons.core.configs.a aVar);
    }

    /* synthetic */ b(byte b2) {
        this();
    }

    /* JADX INFO: compiled from: ConfigComponent.java */
    private static class a {
        private static final b a = new b(0);
    }

    public static b a() {
        return a.a;
    }

    private b() {
        this.g = false;
        b = new HashMap();
        this.e = new HandlerThread("ConfigBootstrapHandler");
        this.e.start();
        this.f = new HandlerC0076b(this.e.getLooper());
        c = new h();
    }

    public final synchronized void b() {
        if (!this.g) {
            this.g = true;
            com.inmobi.commons.core.e.b.a().a("root", c.f);
            if (d == null) {
                d = new d();
                a(c, d);
            }
            Iterator<Map.Entry<com.inmobi.commons.core.configs.a, ArrayList<WeakReference<c>>>> it = b.entrySet().iterator();
            while (it.hasNext()) {
                com.inmobi.commons.core.configs.a key = it.next().getKey();
                c(key);
                b(key);
            }
        }
    }

    /* JADX INFO: compiled from: ConfigComponent.java */
    static class d implements c {
        d() {
        }

        @Override // com.inmobi.commons.core.configs.b.c
        public final void a(com.inmobi.commons.core.configs.a aVar) {
            h unused = b.c = (h) aVar;
        }
    }

    public final synchronized void c() {
        if (this.g) {
            this.g = false;
            this.f.sendEmptyMessage(5);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(com.inmobi.commons.core.configs.a aVar) {
        ArrayList<WeakReference<c>> arrayList = b.get(aVar);
        if (arrayList != null) {
            for (int i = 0; i < arrayList.size(); i++) {
                if (arrayList.get(i) != null && arrayList.get(i).get() != null) {
                    arrayList.get(i).get().a(aVar);
                }
            }
        }
    }

    public final synchronized void a(com.inmobi.commons.core.configs.a aVar, c cVar) {
        if (!this.g) {
            new StringBuilder("Config component not yet started, config can't be fetched. Requested type:").append(aVar.a());
            return;
        }
        ArrayList<WeakReference<c>> arrayList = b.get(aVar);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        arrayList.add(cVar == null ? null : new WeakReference<>(cVar));
        b.put(aVar, arrayList);
        c(aVar);
    }

    public static void d() {
        String str = c.e.a;
        String str2 = c.e.b;
        if (str.trim().length() == 0 || !a("7.2.1", str.trim())) {
            return;
        }
        Logger.a(Logger.InternalLogLevel.DEBUG, a, "A newer version (version " + str + ") of the InMobi SDK is available! You are currently on an older version (Version 7.2.1). Please download the latest InMobi SDK from " + str2);
    }

    private synchronized void c(com.inmobi.commons.core.configs.a aVar) {
        com.inmobi.commons.core.configs.c cVar = new com.inmobi.commons.core.configs.c();
        if (!cVar.a("root")) {
            new StringBuilder("RootConfig not available. Fetching root and returning defaults for config type:").append(aVar.a());
            d(new h());
            return;
        }
        cVar.a(c);
        if (a(cVar.b("root"), c.a("root"))) {
            d(new h());
        }
        if (!cVar.a(aVar.a())) {
            new StringBuilder("Requested config not present. Returning default and fetching. Config type:").append(aVar.a());
            d(aVar.d());
            return;
        }
        cVar.a(aVar);
        if (a(cVar.b(aVar.a()), c.a(aVar.a()))) {
            new StringBuilder("Requested config expired. Returning currently cached and fetching. Config type:").append(aVar.a());
            d(aVar.d());
        } else {
            new StringBuilder("Serving config from cache. Config:").append(aVar.a());
        }
    }

    private static boolean a(long j, long j2) {
        return System.currentTimeMillis() - j > j2 * 1000;
    }

    private void d(com.inmobi.commons.core.configs.a aVar) {
        Message messageObtainMessage = this.f.obtainMessage();
        messageObtainMessage.what = 1;
        messageObtainMessage.obj = aVar;
        this.f.sendMessage(messageObtainMessage);
    }

    /* JADX INFO: renamed from: com.inmobi.commons.core.configs.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ConfigComponent.java */
    static final class HandlerC0076b extends Handler implements e.a {
        private List<com.inmobi.commons.core.configs.a> a;
        private Map<String, Map<String, com.inmobi.commons.core.configs.a>> b;
        private Map<String, com.inmobi.commons.core.configs.a> c;
        private ExecutorService d;

        HandlerC0076b(Looper looper) {
            super(looper);
            this.a = new ArrayList();
            this.b = new HashMap();
            this.c = new HashMap();
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i;
            ExecutorService executorService;
            int i2 = message.what;
            if (i2 == 1) {
                com.inmobi.commons.core.configs.a aVar = (com.inmobi.commons.core.configs.a) message.obj;
                String unused = b.a;
                StringBuilder sb = new StringBuilder("Fetch requested for config:");
                sb.append(aVar.a());
                sb.append(". IsAlreadyScheduled:");
                sb.append(a(aVar.a()));
                if (a(aVar.a())) {
                    String unused2 = b.a;
                    new StringBuilder("Config fetching already in progress:").append(aVar.a());
                    return;
                } else {
                    this.a.add(aVar);
                    if (hasMessages(2)) {
                        return;
                    }
                    sendEmptyMessage(2);
                    return;
                }
            }
            if (i2 == 2) {
                sendEmptyMessageDelayed(3, b.c.c * 1000);
                return;
            }
            if (i2 == 3) {
                a(this.a);
                this.a.clear();
                ExecutorService executorService2 = this.d;
                if (executorService2 == null || executorService2.isShutdown()) {
                    this.d = Executors.newFixedThreadPool(1);
                    sendEmptyMessage(4);
                    return;
                }
                return;
            }
            if (i2 != 4) {
                if (i2 != 5 || (executorService = this.d) == null || executorService.isShutdown()) {
                    return;
                }
                this.c = null;
                this.b.clear();
                removeMessages(3);
                this.d.shutdownNow();
                return;
            }
            if (this.b.isEmpty()) {
                String unused3 = b.a;
                sendEmptyMessage(5);
                return;
            }
            Map.Entry<String, Map<String, com.inmobi.commons.core.configs.a>> next = this.b.entrySet().iterator().next();
            this.c = next.getValue();
            this.b.remove(next.getKey());
            String key = next.getKey();
            Map<String, com.inmobi.commons.core.configs.a> mapA = this.c;
            int i3 = b.c.b;
            int i4 = b.c.a;
            com.inmobi.commons.core.utilities.uid.d dVar = new com.inmobi.commons.core.utilities.uid.d(b.c.p.a);
            int iD = com.inmobi.commons.core.utilities.b.e.d();
            if (iD == 0 && mapA.containsKey("root")) {
                mapA = b.a(mapA);
                i = 1;
            } else {
                i = iD;
            }
            this.d.execute(new e(this, new f(mapA, dVar, key, i4, i3, i), mapA.containsKey("root") ? new f(b.a(mapA), dVar, b.c.e(), i4, i3, true, i) : null));
        }

        private boolean a(String str) {
            boolean z = this.b.get(b.c.b(str)) != null && this.b.get(b.c.b(str)).containsKey(str);
            Map<String, com.inmobi.commons.core.configs.a> map = this.c;
            if (map == null || !map.containsKey(str)) {
                return z;
            }
            return true;
        }

        private void a(List<com.inmobi.commons.core.configs.a> list) {
            for (int i = 0; i < list.size(); i++) {
                com.inmobi.commons.core.configs.a aVar = list.get(i);
                HashMap map = (HashMap) this.b.get(b.c.b(aVar.a()));
                if (map == null) {
                    map = new HashMap();
                    this.b.put(b.c.b(aVar.a()), map);
                }
                map.put(aVar.a(), aVar);
            }
        }

        @Override // com.inmobi.commons.core.configs.e.a
        public final void a(ConfigNetworkResponse.ConfigResponse configResponse) {
            com.inmobi.commons.core.configs.c cVar = new com.inmobi.commons.core.configs.c();
            if (configResponse.a()) {
                String unused = b.a;
                StringBuilder sb = new StringBuilder("Config fetching failed:");
                sb.append(configResponse.b.a());
                sb.append(", Error code:");
                sb.append(configResponse.c.a);
                return;
            }
            if (configResponse.a == ConfigNetworkResponse.ConfigResponse.ConfigResponseStatus.NOT_MODIFIED) {
                String unused2 = b.a;
                new StringBuilder("Config not modified status from server:").append(configResponse.b.a());
                cVar.a(configResponse.b.a(), System.currentTimeMillis());
                return;
            }
            com.inmobi.commons.core.configs.a aVar = configResponse.b;
            try {
                cVar.a.a(aVar.a() + "_config", aVar.b().toString());
                cVar.a(aVar.a(), System.currentTimeMillis());
            } catch (JSONException unused3) {
            }
            try {
                HashMap map = new HashMap();
                map.put("configName", configResponse.b.a());
                map.put("latency", "2147483647");
                com.inmobi.commons.core.e.b.a();
                com.inmobi.commons.core.e.b.a("root", "ConfigFetched", map);
            } catch (Exception e) {
                String unused4 = b.a;
                StringBuilder sb2 = new StringBuilder("Error in submitting telemetry event : (");
                sb2.append(e.getMessage());
                sb2.append(")");
            }
            try {
                String unused5 = b.a;
                new StringBuilder("Config cached successfully:").append(configResponse.b.a());
                String unused6 = b.a;
                new StringBuilder("Config cached successfully:").append(configResponse.b.b().toString());
                b.b(configResponse.b);
            } catch (JSONException unused7) {
            }
        }

        @Override // com.inmobi.commons.core.configs.e.a
        public final void a() {
            sendEmptyMessage(4);
        }
    }

    private static boolean a(String str, String str2) {
        String[] strArrSplit = str.split("\\.");
        String[] strArrSplit2 = str2.split("\\.");
        try {
            for (String str3 : strArrSplit) {
                if (Integer.valueOf(str3).intValue() < 0) {
                    return false;
                }
            }
            for (String str4 : strArrSplit2) {
                if (Integer.valueOf(str4).intValue() < 0) {
                    return false;
                }
            }
            int length = strArrSplit.length < strArrSplit2.length ? strArrSplit.length : strArrSplit2.length;
            for (int i = 0; i < length; i++) {
                if (!strArrSplit[i].equals(strArrSplit2[i])) {
                    return Integer.valueOf(strArrSplit[i]).intValue() < Integer.valueOf(strArrSplit2[i]).intValue();
                }
            }
            return strArrSplit.length < strArrSplit2.length;
        } catch (NumberFormatException unused) {
        }
    }

    static /* synthetic */ Map a(Map map) {
        HashMap map2 = new HashMap(1);
        map2.put("root", map.get("root"));
        return map2;
    }
}
