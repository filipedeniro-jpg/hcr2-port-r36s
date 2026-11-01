package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdbn implements Runnable {
    private final /* synthetic */ int zzgqj;
    private final /* synthetic */ zzdcp zzgqk;
    private final /* synthetic */ zzdbl.zza zzgql;

    zzdbn(zzdbl.zza zzaVar, int i, zzdcp zzdcpVar) {
        this.zzgql = zzaVar;
        this.zzgqj = i;
        this.zzgqk = zzdcpVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.zzgql.zza(this.zzgqj, this.zzgqk);
        } finally {
            this.zzgql.zzaov();
        }
    }
}
