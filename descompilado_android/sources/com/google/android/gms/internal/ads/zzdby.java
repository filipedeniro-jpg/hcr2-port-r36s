package com.google.android.gms.internal.ads;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzdby extends zzdbl.zza {
    private final /* synthetic */ zzdbt zzgqs;
    private zzdbv zzgqw;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdby(zzdbt zzdbtVar, zzdaf<? extends zzdcp<?>> zzdafVar, boolean z, zzdbv zzdbvVar) {
        super(zzdafVar, z, false);
        this.zzgqs = zzdbtVar;
        this.zzgqw = zzdbvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdbl.zza
    final void zza(boolean z, int i, @NullableDecl Object obj) {
    }

    @Override // com.google.android.gms.internal.ads.zzdbl.zza
    final void zzaox() {
        zzdbv zzdbvVar = this.zzgqw;
        if (zzdbvVar != null) {
            zzdbvVar.execute();
        } else {
            zzczx.checkState(this.zzgqs.isDone());
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdbl.zza
    final void zzaow() {
        super.zzaow();
        this.zzgqw = null;
    }

    @Override // com.google.android.gms.internal.ads.zzdbl.zza
    final void interruptTask() {
        zzdbv zzdbvVar = this.zzgqw;
        if (zzdbvVar != null) {
            zzdbvVar.interruptTask();
        }
    }
}
