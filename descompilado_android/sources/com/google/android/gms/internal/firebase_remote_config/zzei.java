package com.google.android.gms.internal.firebase_remote_config;

import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public final class zzei {

    @GuardedBy("ConfigCacheClient.class")
    private static final Map<String, zzei> zzkg = new HashMap();
    private static final Executor zzkk = zzem.zzko;
    private final ExecutorService zzkh;
    private final zzex zzki;

    @Nullable
    @GuardedBy("this")
    private Task<zzen> zzkj = null;

    private zzei(ExecutorService executorService, zzex zzexVar) {
        this.zzkh = executorService;
        this.zzki = zzexVar;
    }

    public final Task<zzen> zzb(zzen zzenVar) {
        zzd(zzenVar);
        return zza(zzenVar, false);
    }

    @Nullable
    public final zzen zzco() {
        return zzb(5L);
    }

    @Nullable
    @VisibleForTesting
    final zzen zzb(long j) {
        synchronized (this) {
            if (this.zzkj != null && this.zzkj.isSuccessful()) {
                return this.zzkj.getResult();
            }
            try {
                Task<zzen> taskZzcp = zzcp();
                TimeUnit timeUnit = TimeUnit.SECONDS;
                zzeo zzeoVar = new zzeo();
                taskZzcp.addOnSuccessListener(zzkk, zzeoVar);
                taskZzcp.addOnFailureListener(zzkk, zzeoVar);
                taskZzcp.addOnCanceledListener(zzkk, zzeoVar);
                if (!zzeoVar.await(5L, timeUnit)) {
                    throw new TimeoutException("Task await timed out.");
                }
                if (taskZzcp.isSuccessful()) {
                    return taskZzcp.getResult();
                }
                throw new ExecutionException(taskZzcp.getException());
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                Log.d("ConfigCacheClient", "Reading from storage file failed.", e);
                return null;
            }
        }
    }

    public final Task<zzen> zzc(zzen zzenVar) {
        return zza(zzenVar, true);
    }

    public final Task<zzen> zza(final zzen zzenVar, final boolean z) {
        return Tasks.call(this.zzkh, new Callable(this, zzenVar) { // from class: com.google.android.gms.internal.firebase_remote_config.zzeh
            private final zzei zzke;
            private final zzen zzkf;

            {
                this.zzke = this;
                this.zzkf = zzenVar;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zzke.zze(this.zzkf);
            }
        }).onSuccessTask(this.zzkh, new SuccessContinuation(this, z, zzenVar) { // from class: com.google.android.gms.internal.firebase_remote_config.zzek
            private final zzei zzke;
            private final boolean zzkm;
            private final zzen zzkn;

            {
                this.zzke = this;
                this.zzkm = z;
                this.zzkn = zzenVar;
            }

            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task then(Object obj) {
                return this.zzke.zza(this.zzkm, this.zzkn, (Void) obj);
            }
        });
    }

    public final synchronized Task<zzen> zzcp() {
        if (this.zzkj == null || (this.zzkj.isComplete() && !this.zzkj.isSuccessful())) {
            ExecutorService executorService = this.zzkh;
            zzex zzexVar = this.zzki;
            zzexVar.getClass();
            this.zzkj = Tasks.call(executorService, zzej.zza(zzexVar));
        }
        return this.zzkj;
    }

    public final void clear() {
        synchronized (this) {
            this.zzkj = Tasks.forResult(null);
        }
        this.zzki.zzdc();
    }

    private final synchronized void zzd(zzen zzenVar) {
        this.zzkj = Tasks.forResult(zzenVar);
    }

    public static synchronized zzei zza(ExecutorService executorService, zzex zzexVar) {
        String fileName;
        fileName = zzexVar.getFileName();
        if (!zzkg.containsKey(fileName)) {
            zzkg.put(fileName, new zzei(executorService, zzexVar));
        }
        return zzkg.get(fileName);
    }

    final /* synthetic */ Task zza(boolean z, zzen zzenVar, Void r3) throws Exception {
        if (z) {
            zzd(zzenVar);
        }
        return Tasks.forResult(zzenVar);
    }

    final /* synthetic */ Void zze(zzen zzenVar) throws Exception {
        return this.zzki.zzh(zzenVar);
    }
}
