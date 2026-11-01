package com.google.android.gms.internal.ads;

import java.lang.Throwable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdbg<V, X extends Throwable> extends zzdbd<V, X, zzdbq<? super X, ? extends V>, zzdcp<? extends V>> {
    zzdbg(zzdcp<? extends V> zzdcpVar, Class<X> cls, zzdbq<? super X, ? extends V> zzdbqVar) {
        super(zzdcpVar, cls, zzdbqVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdbd
    final /* synthetic */ void setResult(Object obj) {
        setFuture((zzdcp) obj);
    }

    @Override // com.google.android.gms.internal.ads.zzdbd
    final /* synthetic */ Object zza(Object obj, Throwable th) throws Exception {
        zzdbq zzdbqVar = (zzdbq) obj;
        zzdcp zzdcpVarZzf = zzdbqVar.zzf(th);
        zzczx.zza(zzdcpVarZzf, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", zzdbqVar);
        return zzdcpVarZzf;
    }
}
