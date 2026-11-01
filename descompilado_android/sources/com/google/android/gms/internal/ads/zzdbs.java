package com.google.android.gms.internal.ads;

import java.util.Collection;
import java.util.List;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
abstract class zzdbs extends zzdbl.zza {
    private List<zzczy<V>> zzgqo;
    private final /* synthetic */ zzdbp zzgqp;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdbs(zzdbp zzdbpVar, zzdaf<? extends zzdcp<? extends V>> zzdafVar, boolean z) {
        super(zzdafVar, z, true);
        this.zzgqp = zzdbpVar;
        this.zzgqo = zzdafVar.isEmpty() ? zzdak.zzaok() : zzdas.zzdu(zzdafVar.size());
        for (int i = 0; i < zzdafVar.size(); i++) {
            this.zzgqo.add(null);
        }
    }

    abstract C zzj(List<zzczy<V>> list);

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.google.android.gms.internal.ads.zzdbl.zza
    final void zza(boolean z, int i, @NullableDecl V v) {
        List<zzczy<V>> list = this.zzgqo;
        if (list != 0) {
            list.set(i, (zzczy<V>) zzczy.zzz(v));
        } else {
            zzczx.checkState(z || this.zzgqp.isCancelled(), "Future was done before all dependencies completed");
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdbl.zza
    final void zzaox() {
        Collection collection = this.zzgqo;
        if (collection != null) {
            this.zzgqp.set(zzj(collection));
        } else {
            zzczx.checkState(this.zzgqp.isDone());
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdbl.zza
    final void zzaow() {
        super.zzaow();
        this.zzgqo = null;
    }
}
