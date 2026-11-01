package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzec implements Runnable {
    private final /* synthetic */ zzdx zzyj;
    private final /* synthetic */ int zzym;
    private final /* synthetic */ boolean zzyn;

    zzec(zzdx zzdxVar, int i, boolean z) {
        this.zzyj = zzdxVar;
        this.zzym = i;
        this.zzyn = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzbo.zza zzaVarZzb = this.zzyj.zzb(this.zzym, this.zzyn);
        this.zzyj.zzxy = zzaVarZzb;
        if (zzdx.zza(this.zzym, zzaVarZzb)) {
            this.zzyj.zza(this.zzym + 1, this.zzyn);
        }
    }
}
