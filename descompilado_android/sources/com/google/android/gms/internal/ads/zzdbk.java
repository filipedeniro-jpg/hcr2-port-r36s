package com.google.android.gms.internal.ads;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzdbk<I, O> extends zzdbh<I, O, zzdbq<? super I, ? extends O>, zzdcp<? extends O>> {
    zzdbk(zzdcp<? extends I> zzdcpVar, zzdbq<? super I, ? extends O> zzdbqVar) {
        super(zzdcpVar, zzdbqVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdbh
    final /* synthetic */ void setResult(Object obj) {
        setFuture((zzdcp) obj);
    }

    @Override // com.google.android.gms.internal.ads.zzdbh
    final /* synthetic */ Object zzc(Object obj, @NullableDecl Object obj2) throws Exception {
        zzdbq zzdbqVar = (zzdbq) obj;
        zzdcp<O> zzdcpVarZzf = zzdbqVar.zzf(obj2);
        zzczx.zza(zzdcpVarZzf, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", zzdbqVar);
        return zzdcpVarZzf;
    }
}
