package net.nend.android.internal.d;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: AndroidExecutor.java */
/* JADX INFO: loaded from: classes2.dex */
public class a implements Executor {
    private final Handler a;

    public a() {
        this(Looper.getMainLooper());
    }

    public a(Looper looper) {
        this.a = new Handler(looper);
    }

    @Override // java.util.concurrent.Executor
    public void execute(@Nullable Runnable runnable) {
        this.a.post(runnable);
    }
}
