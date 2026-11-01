package com.google.android.gms.internal.ads;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzdbj<I, O> extends zzdbh<I, O, zzczs<? super I, ? extends O>, O> {
    zzdbj(zzdcp<? extends I> zzdcpVar, zzczs<? super I, ? extends O> zzczsVar) {
        super(zzdcpVar, zzczsVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdbh
    final void setResult(@NullableDecl O o) {
        set(o);
    }

    @Override // com.google.android.gms.internal.ads.zzdbh
    @NullableDecl
    final /* synthetic */ Object zzc(Object obj, @NullableDecl Object obj2) throws Exception {
        return ((zzczs) obj).apply(obj2);
    }
}
