package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzccc extends zzafc {
    private final /* synthetic */ zzcbp zzfsv;
    private final /* synthetic */ Object zzfsy;
    private final /* synthetic */ String zzfsz;
    private final /* synthetic */ long zzfta;
    private final /* synthetic */ zzaxf zzftb;

    zzccc(zzcbp zzcbpVar, Object obj, String str, long j, zzaxf zzaxfVar) {
        this.zzfsv = zzcbpVar;
        this.zzfsy = obj;
        this.zzfsz = str;
        this.zzfta = j;
        this.zzftb = zzaxfVar;
    }

    @Override // com.google.android.gms.internal.ads.zzaez
    public final void onInitializationSucceeded() {
        synchronized (this.zzfsy) {
            this.zzfsv.zza(this.zzfsz, true, "", (int) (com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime() - this.zzfta));
            this.zzfsv.zzfsk.zzfz(this.zzfsz);
            this.zzftb.set(true);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaez
    public final void onInitializationFailed(String str) {
        synchronized (this.zzfsy) {
            this.zzfsv.zza(this.zzfsz, false, str, (int) (com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime() - this.zzfta));
            this.zzfsv.zzfsk.zzr(this.zzfsz, "error");
            this.zzftb.set(false);
        }
    }
}
