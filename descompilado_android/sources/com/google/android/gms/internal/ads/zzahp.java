package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzahp implements zzaxj<zzagk> {
    final /* synthetic */ zzaho zzdad;

    zzahp(zzaho zzahoVar) {
        this.zzdad = zzahoVar;
    }

    @Override // com.google.android.gms.internal.ads.zzaxj
    public final /* synthetic */ void zzh(zzagk zzagkVar) {
        final zzagk zzagkVar2 = zzagkVar;
        zzawx.zzdwa.execute(new Runnable(this, zzagkVar2) { // from class: com.google.android.gms.internal.ads.zzahs
            private final zzahp zzdae;
            private final zzagk zzdaf;

            {
                this.zzdae = this;
                this.zzdaf = zzagkVar2;
            }

            @Override // java.lang.Runnable
            public final void run() {
                zzahp zzahpVar = this.zzdae;
                zzagk zzagkVar3 = this.zzdaf;
                zzahpVar.zzdad.zzczg.zzh(zzagkVar3);
                zzagkVar3.destroy();
            }
        });
    }
}
