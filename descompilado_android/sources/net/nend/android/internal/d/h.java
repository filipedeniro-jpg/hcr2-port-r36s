package net.nend.android.internal.d;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/* JADX INFO: compiled from: FuturePromise.java */
/* JADX INFO: loaded from: classes2.dex */
class h<T> extends f<T> {
    private Future<?> a;

    h(ExecutorService executorService, Callable<T> callable) {
        if (executorService == null || callable == null) {
            a((Throwable) new NullPointerException());
            return;
        }
        FutureTask<T> futureTask = new FutureTask<T>(callable) { // from class: net.nend.android.internal.d.h.1
            @Override // java.util.concurrent.FutureTask
            protected void done() {
                if (!isCancelled()) {
                    try {
                        h.this.a(get());
                        return;
                    } catch (InterruptedException | ExecutionException e) {
                        h.this.a(e.getCause());
                        return;
                    }
                }
                h.this.a((Throwable) new CancellationException());
            }
        };
        executorService.submit(futureTask);
        this.a = futureTask;
    }

    @Override // net.nend.android.internal.d.f, net.nend.android.internal.d.k
    public boolean c() {
        Future<?> future = this.a;
        return future != null && future.cancel(true);
    }
}
