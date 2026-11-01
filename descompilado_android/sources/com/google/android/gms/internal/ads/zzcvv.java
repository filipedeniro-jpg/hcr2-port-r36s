package com.google.android.gms.internal.ads;

import java.util.Deque;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingDeque;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcvv<T> {
    private final zzdcs zzfnq;

    @GuardedBy("this")
    private final Deque<zzdcp<T>> zzgkd = new LinkedBlockingDeque();
    private final Callable<T> zzgke;

    public zzcvv(Callable<T> callable, zzdcs zzdcsVar) {
        this.zzgke = callable;
        this.zzfnq = zzdcsVar;
    }

    public final synchronized void zzdk(int i) {
        int size = i - this.zzgkd.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.zzgkd.add(this.zzfnq.zzd(this.zzgke));
        }
    }

    public final synchronized zzdcp<T> zzanb() {
        zzdk(1);
        return this.zzgkd.poll();
    }

    public final synchronized void zza(zzdcp<T> zzdcpVar) {
        this.zzgkd.addFirst(zzdcpVar);
    }
}
