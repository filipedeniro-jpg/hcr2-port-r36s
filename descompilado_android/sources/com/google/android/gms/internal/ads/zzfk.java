package com.google.android.gms.internal.ads;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzfk<T> {
    private final Map<String, AtomicReference<T>> zzaao = new HashMap();

    public final AtomicReference<T> zzau(String str) {
        synchronized (this) {
            if (!this.zzaao.containsKey(str)) {
                this.zzaao.put(str, new AtomicReference<>());
            }
        }
        return this.zzaao.get(str);
    }
}
