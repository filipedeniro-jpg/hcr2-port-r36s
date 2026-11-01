package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjz implements zzjx {
    private final zzoa zzauh;
    private final int zzavb;
    private final int zzavf;

    public zzjz(zzjs zzjsVar) {
        this.zzauh = zzjsVar.zzauh;
        this.zzauh.zzbd(12);
        this.zzavf = this.zzauh.zzij();
        this.zzavb = this.zzauh.zzij();
    }

    @Override // com.google.android.gms.internal.ads.zzjx
    public final int zzgh() {
        return this.zzavb;
    }

    @Override // com.google.android.gms.internal.ads.zzjx
    public final int zzgi() {
        int i = this.zzavf;
        return i == 0 ? this.zzauh.zzij() : i;
    }

    @Override // com.google.android.gms.internal.ads.zzjx
    public final boolean zzgj() {
        return this.zzavf != 0;
    }
}
