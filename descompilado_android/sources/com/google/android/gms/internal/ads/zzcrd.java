package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcrd<T> {
    private final Executor executor;
    private final Set<zzcra<? extends zzcrb<T>>> zzgfw;

    public zzcrd(Executor executor, Set<zzcra<? extends zzcrb<T>>> set) {
        this.executor = executor;
        this.zzgfw = set;
    }

    public final zzdcp<T> zzs(final T t) {
        final ArrayList arrayList = new ArrayList(this.zzgfw.size());
        for (final zzcra<? extends zzcrb<T>> zzcraVar : this.zzgfw) {
            zzdcp<S> zzdcpVarZzalr = zzcraVar.zzalr();
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzclp)).booleanValue()) {
                final long jElapsedRealtime = com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime();
                zzdcpVarZzalr.addListener(new Runnable(zzcraVar, jElapsedRealtime) { // from class: com.google.android.gms.internal.ads.zzcrc
                    private final zzcra zzgfu;
                    private final long zzgfv;

                    {
                        this.zzgfu = zzcraVar;
                        this.zzgfv = jElapsedRealtime;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        zzcra zzcraVar2 = this.zzgfu;
                        long j = this.zzgfv;
                        String canonicalName = zzcraVar2.getClass().getCanonicalName();
                        long jElapsedRealtime2 = com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime() - j;
                        StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 40);
                        sb.append("Signal runtime : ");
                        sb.append(canonicalName);
                        sb.append(" = ");
                        sb.append(jElapsedRealtime2);
                        zzatm.zzdy(sb.toString());
                    }
                }, zzawx.zzdwb);
            }
            arrayList.add(zzdcpVarZzalr);
        }
        return zzdcf.zzh(arrayList).zza(new Callable(arrayList, t) { // from class: com.google.android.gms.internal.ads.zzcrf
            private final Object zzdbe;
            private final List zzgfh;

            {
                this.zzgfh = arrayList;
                this.zzdbe = t;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzcrd.zza(this.zzgfh, this.zzdbe);
            }
        }, this.executor);
    }

    static final /* synthetic */ Object zza(List list, Object obj) throws Exception {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            try {
                zzcrb zzcrbVar = (zzcrb) ((zzdcp) it.next()).get();
                if (zzcrbVar != null) {
                    zzcrbVar.zzr(obj);
                }
            } catch (InterruptedException | ExecutionException e) {
                zzatm.zzc("Derive quality signals error.", e);
                throw new Exception(e);
            }
        }
        return obj;
    }
}
