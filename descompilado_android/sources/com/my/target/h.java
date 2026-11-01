package com.my.target;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* JADX INFO: compiled from: AsyncCommand.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class h {
    private static final Executor w = Executors.newSingleThreadExecutor();
    private static final Executor x = Executors.newSingleThreadExecutor();
    private static final Executor y = new Executor() { // from class: com.my.target.h.1
        @Override // java.util.concurrent.Executor
        public void execute(@NonNull Runnable runnable) {
            h.handler.post(runnable);
        }
    };
    private static final Handler handler = new Handler(Looper.getMainLooper());

    public static void a(@NonNull Runnable runnable) {
        w.execute(runnable);
    }

    public static void b(@NonNull Runnable runnable) {
        x.execute(runnable);
    }

    public static void c(@NonNull Runnable runnable) {
        y.execute(runnable);
    }
}
