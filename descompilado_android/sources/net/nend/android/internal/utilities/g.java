package net.nend.android.internal.utilities;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadFactory;
import org.json.JSONObject;

/* JADX INFO: compiled from: NendAdExecutor.java */
/* JADX INFO: loaded from: classes2.dex */
public class g {
    private static g a;
    private ExecutorService b = Executors.newCachedThreadPool(new ThreadFactory() { // from class: net.nend.android.internal.utilities.g.1
        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("net.nend.android.execution.thread");
            thread.setPriority(10);
            return thread;
        }
    });

    /* JADX INFO: compiled from: NendAdExecutor.java */
    public interface a<V> {
        void a(V v, Exception exc);
    }

    /* JADX INFO: compiled from: NendAdExecutor.java */
    public interface b<T> {
        String getRequestUrl();

        T makeResponse(byte[] bArr);
    }

    /* JADX INFO: compiled from: NendAdExecutor.java */
    public interface c<T> extends b<T> {
        T a(net.nend.android.internal.c.f fVar);
    }

    private g() {
    }

    public static synchronized g a() {
        if (a == null) {
            a = new g();
        }
        return a;
    }

    public synchronized <V> Future<V> a(Callable<V> callable) {
        return a(callable, null);
    }

    public synchronized <V> Future<V> a(Callable<V> callable, a<V> aVar) {
        e eVar;
        eVar = new e(callable, aVar);
        this.b.execute(eVar);
        return eVar;
    }

    public synchronized ExecutorService b() {
        return this.b;
    }

    /* JADX INFO: compiled from: NendAdExecutor.java */
    public static class f<V> implements Callable<V> {
        private final WeakReference<b<V>> a;
        private final String b;
        private final JSONObject c;
        private final String d;
        private final boolean e;

        public static <V> f<V> a(c<V> cVar, JSONObject jSONObject) {
            return new f<>(cVar, jSONObject, HttpRequest.METHOD_POST);
        }

        public static <V> f<V> a(b<V> bVar, JSONObject jSONObject) {
            return new f<>(bVar, jSONObject, HttpRequest.METHOD_PUT);
        }

        public f(String str) {
            this.a = null;
            this.b = str;
            this.c = null;
            this.d = HttpRequest.METHOD_GET;
            this.e = true;
        }

        public f(b<V> bVar) {
            this(bVar, null, HttpRequest.METHOD_GET);
        }

        private f(b<V> bVar, JSONObject jSONObject, String str) {
            this.a = new WeakReference<>(bVar);
            this.b = null;
            this.c = jSONObject;
            this.d = str;
            this.e = !(bVar instanceof c);
        }

        @Override // java.util.concurrent.Callable
        public V call() throws Throwable {
            String requestUrl;
            WeakReference<b<V>> weakReference = this.a;
            b<V> bVar = weakReference != null ? weakReference.get() : null;
            if (bVar != null) {
                requestUrl = bVar.getRequestUrl();
            } else {
                requestUrl = this.b;
            }
            if (!TextUtils.isEmpty(requestUrl)) {
                net.nend.android.internal.c.f fVarA = net.nend.android.internal.c.e.a(requestUrl, this.d, this.c, this.e);
                if (bVar != null) {
                    if (this.e) {
                        return bVar.makeResponse(fVarA.a());
                    }
                    return (V) ((c) bVar).a(fVarA);
                }
            } else {
                j.a(k.ERR_UNEXPECTED);
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: NendAdExecutor.java */
    public static class d implements Callable<String> {
        private final WeakReference<Context> a;

        public d(Context context) {
            this.a = new WeakReference<>(context);
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public String call() {
            Context context = this.a.get();
            return context != null ? net.nend.android.internal.utilities.c.b(context) : "";
        }
    }

    /* JADX INFO: compiled from: NendAdExecutor.java */
    private class e<V> extends FutureTask<V> {
        private final a<V> b;
        private volatile boolean c;

        public e(Callable<V> callable, a<V> aVar) {
            super(callable);
            this.c = false;
            this.b = aVar;
        }

        @Override // java.util.concurrent.FutureTask, java.util.concurrent.Future
        public boolean cancel(boolean z) {
            this.c = true;
            if (isDone() || super.isCancelled()) {
                return false;
            }
            return super.cancel(z);
        }

        @Override // java.util.concurrent.FutureTask, java.util.concurrent.Future
        public boolean isCancelled() {
            return super.isCancelled() || this.c;
        }

        @Override // java.util.concurrent.FutureTask
        protected void done() {
            if (isCancelled() || this.b == null) {
                return;
            }
            try {
                a(get(), null);
            } catch (InterruptedException | ExecutionException e) {
                j.b("Failed to execute task.", e);
                a(null, e);
            }
        }

        private void a(final V v, final Exception exc) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: net.nend.android.internal.utilities.g.e.1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Runnable
                public void run() {
                    if (e.this.isCancelled()) {
                        return;
                    }
                    e.this.b.a(v, exc);
                }
            });
        }
    }
}
