package com.inmobi.ads.cache;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.webkit.URLUtil;
import androidx.annotation.NonNull;
import com.inmobi.ads.bh;
import com.inmobi.ads.bm;
import com.inmobi.commons.core.utilities.g;
import com.squareup.picasso.Callback;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public final class AssetStore implements com.inmobi.commons.core.configs.b.c {
    private static final String f = "AssetStore";
    private static AssetStore o;
    public d a;
    public com.inmobi.ads.c.b b;
    public ExecutorService c;
    private com.inmobi.ads.c.j g;
    private ExecutorService h;
    private a i;
    private HandlerThread j;
    private ConcurrentHashMap<String, com.inmobi.ads.cache.a> l;
    private g.b m;
    private g.b n;
    private static final Object p = new Object();
    public static final Object e = new Object();
    private AtomicBoolean k = new AtomicBoolean(false);
    public AtomicBoolean d = new AtomicBoolean(false);
    private List<b> q = new ArrayList();
    private final e r = new e() { // from class: com.inmobi.ads.cache.AssetStore.1
        @Override // com.inmobi.ads.cache.e
        public final void a(@NonNull com.inmobi.commons.core.network.d dVar, @NonNull String str, @NonNull com.inmobi.ads.cache.a aVar) {
            String unused = AssetStore.f;
            StringBuilder sb = new StringBuilder("Asset fetch succeeded for URL ");
            sb.append(aVar.d);
            sb.append(" Updating location on disk (file://");
            sb.append(str);
            sb.append(")");
            com.inmobi.ads.cache.a aVarA = new com.inmobi.ads.cache.a.C0074a().a(aVar.d, str, dVar, AssetStore.this.b.a, AssetStore.this.b.e).a();
            d unused2 = AssetStore.this.a;
            d.b(aVarA);
            aVarA.k = aVar.k;
            aVarA.a = aVar.a;
            AssetStore.this.a(aVarA, true);
            try {
                AssetStore.c(AssetStore.this);
            } catch (Exception e2) {
                String unused3 = AssetStore.f;
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            }
        }

        @Override // com.inmobi.ads.cache.e
        public final void a(@NonNull com.inmobi.ads.cache.a aVar) {
            String unused = AssetStore.f;
            StringBuilder sb = new StringBuilder("Asset fetch failed for remote URL (");
            sb.append(aVar.d);
            sb.append(")");
            AssetStore.this.c(aVar.d);
            if (aVar.c <= 0) {
                String unused2 = AssetStore.f;
                AssetStore.this.a(aVar, false);
                d unused3 = AssetStore.this.a;
                d.c(aVar);
            } else {
                String unused4 = AssetStore.f;
                aVar.f = System.currentTimeMillis();
                d unused5 = AssetStore.this.a;
                d.b(aVar);
                if (!com.inmobi.commons.core.utilities.d.a()) {
                    AssetStore.this.a(aVar, false);
                }
            }
            try {
                AssetStore.c(AssetStore.this);
            } catch (Exception e2) {
                String unused6 = AssetStore.f;
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            }
        }
    };

    private AssetStore() {
        com.inmobi.ads.c cVar = new com.inmobi.ads.c();
        com.inmobi.commons.core.configs.b.a().a(cVar, this);
        this.b = cVar.n;
        this.g = cVar.m;
        this.a = d.a();
        this.c = Executors.newCachedThreadPool();
        this.h = Executors.newFixedThreadPool(1);
        this.j = new HandlerThread("assetFetcher");
        this.j.start();
        this.i = new a(this.j.getLooper(), this);
        this.m = new g.b() { // from class: com.inmobi.ads.cache.AssetStore.2
            @Override // com.inmobi.commons.core.utilities.g.b
            public final void a(boolean z) {
                if (!z) {
                    AssetStore.this.c();
                } else {
                    AssetStore.c(AssetStore.this);
                }
            }
        };
        if (Build.VERSION.SDK_INT >= 23) {
            this.n = new g.b() { // from class: com.inmobi.ads.cache.AssetStore.3
                @Override // com.inmobi.commons.core.utilities.g.b
                public final void a(boolean z) {
                    if (z) {
                        AssetStore.this.c();
                    } else {
                        AssetStore.c(AssetStore.this);
                    }
                }
            };
        }
        this.l = new ConcurrentHashMap<>(2, 0.9f, 2);
    }

    public static AssetStore a() {
        AssetStore assetStore = o;
        if (assetStore == null) {
            synchronized (p) {
                assetStore = o;
                if (assetStore == null) {
                    assetStore = new AssetStore();
                    o = assetStore;
                }
            }
        }
        return assetStore;
    }

    @Override // com.inmobi.commons.core.configs.b.c
    public final void a(com.inmobi.commons.core.configs.a aVar) {
        com.inmobi.ads.c cVar = (com.inmobi.ads.c) aVar;
        this.b = cVar.n;
        this.g = cVar.m;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(String str) {
        boolean z;
        for (int i = 0; i < this.q.size(); i++) {
            b bVar = this.q.get(i);
            Set<bm> set = bVar.b;
            Set<String> set2 = bVar.c;
            Iterator<bm> it = set.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().b.equals(str)) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (z && !set2.contains(str)) {
                bVar.c.add(str);
                bVar.d++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void b(String str) {
        boolean z;
        for (int i = 0; i < this.q.size(); i++) {
            b bVar = this.q.get(i);
            Iterator<bm> it = bVar.b.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().b.equals(str)) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (z) {
                bVar.e++;
            }
        }
    }

    private synchronized void b(com.inmobi.ads.cache.a aVar) {
        boolean z;
        for (int i = 0; i < this.q.size(); i++) {
            b bVar = this.q.get(i);
            Iterator<bm> it = bVar.b.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().b.equals(aVar.d)) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (z && !bVar.a.contains(aVar)) {
                bVar.a.add(aVar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void b(b bVar) {
        if (!this.q.contains(bVar)) {
            this.q.add(bVar);
        }
    }

    private synchronized void a(List<b> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            this.q.remove(list.get(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(com.inmobi.ads.cache.a aVar, e eVar) {
        boolean z;
        if (this.l.putIfAbsent(aVar.d, aVar) != null) {
            return false;
        }
        c cVar = new c(eVar);
        long j = this.g.c;
        ArrayList<String> arrayList = this.g.e;
        StringBuilder sb = new StringBuilder("Fetching asset (");
        sb.append(aVar.d);
        sb.append(")");
        if (com.inmobi.commons.core.utilities.d.a()) {
            if (!aVar.d.equals("") && URLUtil.isValidUrl(aVar.d)) {
                String[] strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
                try {
                    try {
                        long jElapsedRealtime = SystemClock.elapsedRealtime();
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(aVar.d).openConnection();
                        httpURLConnection.setRequestMethod(HttpRequest.METHOD_GET);
                        httpURLConnection.setConnectTimeout(60000);
                        httpURLConnection.setReadTimeout(60000);
                        if (httpURLConnection.getResponseCode() < 400) {
                            String contentType = httpURLConnection.getContentType();
                            int length = strArr.length;
                            int i = 0;
                            while (true) {
                                if (i >= length) {
                                    z = false;
                                    break;
                                }
                                String str = strArr[i];
                                if (contentType != null && str.toLowerCase(Locale.ENGLISH).equals(contentType.toLowerCase(Locale.ENGLISH))) {
                                    z = true;
                                    break;
                                }
                                i++;
                            }
                            if (!z) {
                                aVar.l = 6;
                                aVar.c = 0;
                                cVar.a.a(aVar);
                                return true;
                            }
                        }
                        long contentLength = httpURLConnection.getContentLength();
                        if (contentLength >= 0) {
                            StringBuilder sb2 = new StringBuilder("ContentSize: ");
                            sb2.append(contentLength);
                            sb2.append(" max size: ");
                            sb2.append(j);
                            if (contentLength > j) {
                                aVar.l = 7;
                                aVar.c = 0;
                                cVar.a.a(aVar);
                                return true;
                            }
                        }
                        httpURLConnection.connect();
                        File fileA = com.inmobi.commons.a.a.a(aVar.d);
                        if (fileA.exists()) {
                            fileA.delete();
                        }
                        InputStream inputStream = httpURLConnection.getInputStream();
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileA));
                        byte[] bArr = new byte[1024];
                        long j2 = 0;
                        while (true) {
                            int i2 = inputStream.read(bArr);
                            if (i2 <= 0) {
                                bufferedOutputStream.flush();
                                httpURLConnection.disconnect();
                                com.inmobi.commons.core.utilities.d.a(bufferedOutputStream);
                                long jElapsedRealtime2 = SystemClock.elapsedRealtime();
                                c.a(jElapsedRealtime, j2, jElapsedRealtime2);
                                com.inmobi.commons.core.network.d dVar = new com.inmobi.commons.core.network.d();
                                dVar.d = httpURLConnection.getHeaderFields();
                                aVar.k = c.a(aVar, fileA, jElapsedRealtime, jElapsedRealtime2);
                                aVar.a = jElapsedRealtime2 - jElapsedRealtime;
                                cVar.a.a(dVar, fileA.getAbsolutePath(), aVar);
                                return true;
                            }
                            j2 += (long) i2;
                            if (j2 <= j) {
                                bufferedOutputStream.write(bArr, 0, i2);
                            } else {
                                aVar.l = 7;
                                aVar.c = 0;
                                try {
                                    if (fileA.exists()) {
                                        fileA.delete();
                                    }
                                    httpURLConnection.disconnect();
                                    com.inmobi.commons.core.utilities.d.a(bufferedOutputStream);
                                } catch (Exception e2) {
                                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                                }
                                c.a(jElapsedRealtime, j2, SystemClock.elapsedRealtime());
                                cVar.a.a(aVar);
                                return true;
                            }
                        }
                    } catch (Exception unused) {
                        aVar.l = 0;
                        cVar.a.a(aVar);
                        return true;
                    }
                } catch (FileNotFoundException unused2) {
                    aVar.l = 4;
                    cVar.a.a(aVar);
                    return true;
                } catch (MalformedURLException unused3) {
                    aVar.l = 3;
                    cVar.a.a(aVar);
                    return true;
                } catch (ProtocolException unused4) {
                    aVar.l = 8;
                    cVar.a.a(aVar);
                    return true;
                } catch (SocketTimeoutException unused5) {
                    aVar.l = 4;
                    cVar.a.a(aVar);
                    return true;
                } catch (IOException unused6) {
                    aVar.l = 8;
                    cVar.a.a(aVar);
                    return true;
                }
            } else {
                aVar.l = 3;
                cVar.a.a(aVar);
                return true;
            }
        } else {
            aVar.l = 8;
            cVar.a.a(aVar);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        this.l.remove(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(@NonNull com.inmobi.ads.cache.a aVar, boolean z) {
        b(aVar);
        c(aVar.d);
        if (z) {
            a(aVar.d);
            e();
        } else {
            b(aVar.d);
            f();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void e() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.q.size(); i++) {
            b bVar = this.q.get(i);
            if (bVar.d == bVar.b.size()) {
                try {
                    f fVarA = bVar.a();
                    if (fVarA != null) {
                        fVarA.b(bVar);
                    }
                    arrayList.add(bVar);
                } catch (Exception e2) {
                    new StringBuilder("Encountered unexpected error in onAssetFetchSucceeded handler: ").append(e2.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                }
            }
        }
        a(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void f() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.q.size(); i++) {
            b bVar = this.q.get(i);
            if (bVar.e > 0) {
                try {
                    f fVarA = bVar.a();
                    if (fVarA != null) {
                        fVarA.a(bVar);
                    }
                    arrayList.add(bVar);
                } catch (Exception e2) {
                    new StringBuilder("Encountered unexpected error in onAssetFetchFailed handler: ").append(e2.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                }
            }
        }
        a(arrayList);
    }

    public final void b() {
        this.d.set(false);
        if (!com.inmobi.commons.core.utilities.d.a()) {
            g();
            h();
            return;
        }
        synchronized (e) {
            if (this.k.compareAndSet(false, true)) {
                if (this.j == null) {
                    this.j = new HandlerThread("assetFetcher");
                    this.j.start();
                }
                if (this.i == null) {
                    this.i = new a(this.j.getLooper(), this);
                }
                ArrayList arrayList = new ArrayList();
                List<com.inmobi.ads.cache.a> listA = d.a(this.b.b);
                if (listA.isEmpty()) {
                    c();
                    return;
                }
                for (com.inmobi.ads.cache.a aVar : listA) {
                    if (arrayList.indexOf(aVar) == -1 && !aVar.a()) {
                        arrayList.add(aVar);
                        break;
                    }
                }
                if (arrayList.isEmpty()) {
                    c();
                } else {
                    g();
                    h();
                    this.i.sendEmptyMessage(1);
                }
            }
        }
    }

    public static void a(com.inmobi.ads.cache.a aVar) {
        d.c(aVar);
        File file = new File(aVar.e);
        if (file.exists()) {
            file.delete();
        }
    }

    public final void a(final b bVar) {
        this.c.execute(new Runnable() { // from class: com.inmobi.ads.cache.AssetStore.5
            @Override // java.lang.Runnable
            public final void run() {
                AssetStore.this.b(bVar);
                String unused = AssetStore.f;
                StringBuilder sb = new StringBuilder("Attempting to cache ");
                sb.append(bVar.b.size());
                sb.append("remote URLs ");
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                for (bm bmVar : bVar.b) {
                    if (bmVar.b.trim().length() > 0 && bmVar.a == 2) {
                        arrayList.add(bmVar.b);
                    } else {
                        arrayList2.add(bmVar.b);
                    }
                }
                AssetStore.a(AssetStore.this, arrayList);
                AssetStore.this.e();
                AssetStore.this.f();
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    AssetStore.b(AssetStore.this, (String) it.next());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(com.inmobi.ads.cache.a aVar) {
        File file = new File(aVar.e);
        long jMin = Math.min(System.currentTimeMillis() + (aVar.h - aVar.f), System.currentTimeMillis() + (this.b.e * 1000));
        com.inmobi.ads.cache.a.C0074a c0074a = new com.inmobi.ads.cache.a.C0074a();
        String str = aVar.d;
        String str2 = aVar.e;
        int i = this.b.a;
        long j = aVar.i;
        c0074a.c = str;
        c0074a.d = str2;
        c0074a.b = i;
        c0074a.g = jMin;
        c0074a.h = j;
        com.inmobi.ads.cache.a aVarA = c0074a.a();
        aVarA.f = System.currentTimeMillis();
        d.b(aVarA);
        aVarA.k = c.a(aVar, file, aVar.f, aVar.f);
        aVarA.j = true;
        a(aVarA, true);
    }

    private class PicassoCallback implements Callback {
        private CountDownLatch b;
        private String c;

        PicassoCallback(CountDownLatch countDownLatch, String str) {
            this.b = countDownLatch;
            this.c = str;
        }

        @Override // com.squareup.picasso.Callback
        public void onSuccess() {
            AssetStore.this.a(this.c);
            this.b.countDown();
        }

        @Override // com.squareup.picasso.Callback
        public void onError() {
            AssetStore.this.b(this.c);
            this.b.countDown();
        }
    }

    @TargetApi(23)
    private void g() {
        g.a();
        g.b bVar = this.m;
        if (Build.VERSION.SDK_INT < 28) {
            g.a(bVar, "android.net.conn.CONNECTIVITY_CHANGE");
        } else {
            g.a(bVar, "SYSTEM_CONNECTIVITY_CHANGE");
        }
        if (Build.VERSION.SDK_INT >= 23) {
            g.a();
            g.a(this.n, "android.os.action.DEVICE_IDLE_MODE_CHANGED");
        }
    }

    @TargetApi(23)
    private void h() {
        g.a().a(this.m);
        if (Build.VERSION.SDK_INT >= 23) {
            g.a().a("android.os.action.DEVICE_IDLE_MODE_CHANGED", this.n);
        }
    }

    public final void c() {
        synchronized (e) {
            this.k.set(false);
            this.l.clear();
            if (this.j != null) {
                this.j.getLooper().quit();
                this.j.interrupt();
                this.j = null;
                this.i = null;
            }
        }
    }

    private static final class a extends Handler {
        private WeakReference<AssetStore> a;
        private final e b;

        a(@NonNull Looper looper, @NonNull AssetStore assetStore) {
            super(looper);
            this.a = new WeakReference<>(assetStore);
            this.b = new e() { // from class: com.inmobi.ads.cache.AssetStore.a.1
                @Override // com.inmobi.ads.cache.e
                public final void a(com.inmobi.commons.core.network.d dVar, String str, com.inmobi.ads.cache.a aVar) {
                    AssetStore assetStore2 = (AssetStore) a.this.a.get();
                    if (assetStore2 != null) {
                        String unused = AssetStore.f;
                        StringBuilder sb = new StringBuilder("Asset fetch succeeded for URL ");
                        sb.append(aVar.d);
                        sb.append(" Updating location on disk (file://");
                        sb.append(str);
                        sb.append(")");
                        com.inmobi.ads.cache.a aVarA = new com.inmobi.ads.cache.a.C0074a().a(aVar.d, str, dVar, assetStore2.b.a, assetStore2.b.e).a();
                        d unused2 = assetStore2.a;
                        d.b(aVarA);
                        aVarA.k = aVar.k;
                        aVarA.a = aVar.a;
                        assetStore2.a(aVarA, true);
                        a.this.a();
                        return;
                    }
                    String unused3 = AssetStore.f;
                }

                @Override // com.inmobi.ads.cache.e
                public final void a(com.inmobi.ads.cache.a aVar) {
                    AssetStore assetStore2 = (AssetStore) a.this.a.get();
                    if (assetStore2 != null) {
                        String unused = AssetStore.f;
                        StringBuilder sb = new StringBuilder("Asset fetch failed for remote URL (");
                        sb.append(aVar.d);
                        sb.append(")");
                        assetStore2.c(aVar.d);
                        if (aVar.c <= 0) {
                            assetStore2.a(aVar, false);
                            a.this.a(aVar);
                            return;
                        }
                        aVar.c--;
                        aVar.f = System.currentTimeMillis();
                        d unused2 = assetStore2.a;
                        d.b(aVar);
                        a.this.b();
                        return;
                    }
                    String unused3 = AssetStore.f;
                }
            };
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            try {
                int i = message.what;
                if (i == 1) {
                    if (this.a.get() != null) {
                        AssetStore assetStore = this.a.get();
                        com.inmobi.ads.c.b bVar = assetStore.b;
                        com.inmobi.ads.cache.a aVar = null;
                        if (bVar == null) {
                            com.inmobi.ads.c cVar = new com.inmobi.ads.c();
                            com.inmobi.commons.core.configs.b.a().a(cVar, (com.inmobi.commons.core.configs.b.c) null);
                            bVar = cVar.n;
                        }
                        d unused = assetStore.a;
                        for (com.inmobi.ads.cache.a aVar2 : d.a(bVar.b)) {
                            if (!aVar2.a()) {
                                aVar = aVar2;
                                break;
                            }
                        }
                        if (aVar == null) {
                            String unused2 = AssetStore.f;
                            assetStore.c();
                            return;
                        }
                        String unused3 = AssetStore.f;
                        Message messageObtain = Message.obtain();
                        messageObtain.what = 2;
                        messageObtain.obj = aVar.d;
                        long jCurrentTimeMillis = System.currentTimeMillis() - aVar.f;
                        try {
                            if (jCurrentTimeMillis < bVar.b * 1000) {
                                sendMessageDelayed(messageObtain, ((long) (bVar.b * 1000)) - jCurrentTimeMillis);
                                return;
                            } else {
                                sendMessage(messageObtain);
                                return;
                            }
                        } catch (Exception e) {
                            String unused4 = AssetStore.f;
                            new StringBuilder("Encountered unexpected error in Asset fetch handler").append(e.getMessage());
                            return;
                        }
                    }
                    return;
                }
                if (i == 2) {
                    if (this.a.get() != null) {
                        AssetStore assetStore2 = this.a.get();
                        String str = (String) message.obj;
                        d unused5 = assetStore2.a;
                        com.inmobi.ads.cache.a aVarB = d.b(str);
                        if (aVarB == null) {
                            b();
                            return;
                        }
                        if (aVarB.a()) {
                            String unused6 = AssetStore.f;
                            a();
                            assetStore2.a(aVarB, true);
                            return;
                        }
                        int i2 = (assetStore2.b.a - aVarB.c) + 1;
                        if (aVarB.c != 0) {
                            if (!com.inmobi.commons.core.utilities.d.a()) {
                                assetStore2.a(aVarB, false);
                                assetStore2.c();
                                return;
                            }
                            if (assetStore2.a(aVarB, this.b)) {
                                String unused7 = AssetStore.f;
                                new StringBuilder("Cache miss in handler; attempting to cache asset: ").append(aVarB.d);
                                String unused8 = AssetStore.f;
                                StringBuilder sb = new StringBuilder("Download attempt # ");
                                sb.append(i2);
                                sb.append(" in handler  to cache asset (");
                                sb.append(aVarB.d);
                                sb.append(")");
                                return;
                            }
                            String unused9 = AssetStore.f;
                            new StringBuilder("Cache miss in handler; but already attempting: ").append(aVarB.d);
                            b();
                            return;
                        }
                        aVarB.l = 11;
                        assetStore2.a(aVarB, false);
                        a(aVarB);
                        return;
                    }
                    return;
                }
                if (i != 3) {
                    if (i != 4) {
                        return;
                    }
                    com.inmobi.ads.cache.a aVar3 = (com.inmobi.ads.cache.a) message.obj;
                    AssetStore assetStore3 = this.a.get();
                    if (assetStore3 != null) {
                        d unused10 = assetStore3.a;
                        d.c(aVar3);
                    }
                    b();
                }
                b();
            } catch (Exception e2) {
                String unused11 = AssetStore.f;
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(com.inmobi.ads.cache.a aVar) {
            try {
                Message messageObtain = Message.obtain();
                messageObtain.what = 4;
                messageObtain.obj = aVar;
                sendMessage(messageObtain);
            } catch (Exception e) {
                String unused = AssetStore.f;
                new StringBuilder("Encountered unexpected error in Asset fetch handler").append(e.getMessage());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            try {
                sendEmptyMessage(3);
            } catch (Exception e) {
                String unused = AssetStore.f;
                new StringBuilder("Encountered unexpected error in Asset fetch handler").append(e.getMessage());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b() {
            try {
                sendEmptyMessage(1);
            } catch (Exception e) {
                String unused = AssetStore.f;
                new StringBuilder("Encountered unexpected error in Asset fetch handler").append(e.getMessage());
            }
        }
    }

    static /* synthetic */ void c(AssetStore assetStore) {
        if (assetStore.d.get()) {
            return;
        }
        assetStore.b();
    }

    static /* synthetic */ void b(AssetStore assetStore, final String str) {
        com.inmobi.ads.cache.a aVarA = d.a(str);
        if (aVarA != null && aVarA.a()) {
            StringBuilder sb = new StringBuilder("Cache hit; file exists location on disk (");
            sb.append(aVarA.e);
            sb.append(")");
            assetStore.c(aVarA);
            return;
        }
        com.inmobi.ads.cache.a aVarA2 = new com.inmobi.ads.cache.a.C0074a().a(str, assetStore.b.a, assetStore.b.e).a();
        if (d.a(str) == null) {
            assetStore.a.a(aVarA2);
        }
        assetStore.h.execute(new Runnable() { // from class: com.inmobi.ads.cache.AssetStore.6
            @Override // java.lang.Runnable
            public final void run() {
                d unused = AssetStore.this.a;
                com.inmobi.ads.cache.a aVarA3 = d.a(str);
                if (aVarA3 != null) {
                    if (aVarA3.a()) {
                        AssetStore.this.c(aVarA3);
                        return;
                    }
                    AssetStore assetStore2 = AssetStore.this;
                    if (assetStore2.a(aVarA3, assetStore2.r)) {
                        String unused2 = AssetStore.f;
                        new StringBuilder("Cache miss; attempting to cache asset: ").append(str);
                    } else {
                        String unused3 = AssetStore.f;
                        new StringBuilder("Cache miss; but already attempting: ").append(str);
                    }
                }
            }
        });
    }

    static /* synthetic */ void a(AssetStore assetStore, List list) {
        CountDownLatch countDownLatch = new CountDownLatch(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            try {
                bh.a(com.inmobi.commons.a.a.b()).load(str).fetch(assetStore.new PicassoCallback(countDownLatch, str));
            } catch (Exception unused) {
                countDownLatch.countDown();
            }
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException unused2) {
        }
    }
}
