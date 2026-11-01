package com.google.android.gms.internal.ads;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzbos<ListenerT> {

    @GuardedBy("this")
    private final Map<ListenerT, Executor> zzfgv = new HashMap();

    protected zzbos(Set<zzbqc<ListenerT>> set) {
        zzb(set);
    }

    public final synchronized void zza(zzbqc<ListenerT> zzbqcVar) {
        zza(zzbqcVar.zzfhn, zzbqcVar.executor);
    }

    public final synchronized void zza(ListenerT listenert, Executor executor) {
        this.zzfgv.put(listenert, executor);
    }

    private final synchronized void zzb(Set<zzbqc<ListenerT>> set) {
        Iterator<zzbqc<ListenerT>> it = set.iterator();
        while (it.hasNext()) {
            zza(it.next());
        }
    }

    protected final synchronized void zza(final zzbou<ListenerT> zzbouVar) {
        for (Map.Entry<ListenerT, Executor> entry : this.zzfgv.entrySet()) {
            final ListenerT key = entry.getKey();
            entry.getValue().execute(new Runnable(zzbouVar, key) { // from class: com.google.android.gms.internal.ads.zzbov
                private final Object zzdbe;
                private final zzbou zzfgw;

                {
                    this.zzfgw = zzbouVar;
                    this.zzdbe = key;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        this.zzfgw.zzp(this.zzdbe);
                    } catch (Throwable th) {
                        com.google.android.gms.ads.internal.zzp.zzkc().zzb(th, "EventEmitter.notify");
                        zzatm.zza("Event emitter exception.", th);
                    }
                }
            });
        }
    }
}
