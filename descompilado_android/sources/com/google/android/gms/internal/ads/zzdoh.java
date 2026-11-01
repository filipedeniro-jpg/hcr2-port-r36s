package com.google.android.gms.internal.ads;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdoh {
    private final ConcurrentHashMap<zzdog, List<Throwable>> zzhew = new ConcurrentHashMap<>(16, 0.75f, 10);
    private final ReferenceQueue<Throwable> zzhex = new ReferenceQueue<>();

    zzdoh() {
    }

    public final List<Throwable> zza(Throwable th, boolean z) {
        Reference<? extends Throwable> referencePoll = this.zzhex.poll();
        while (referencePoll != null) {
            this.zzhew.remove(referencePoll);
            referencePoll = this.zzhex.poll();
        }
        List<Throwable> list = this.zzhew.get(new zzdog(th, null));
        if (!z || list != null) {
            return list;
        }
        Vector vector = new Vector(2);
        List<Throwable> listPutIfAbsent = this.zzhew.putIfAbsent(new zzdog(th, this.zzhex), vector);
        return listPutIfAbsent == null ? vector : listPutIfAbsent;
    }
}
