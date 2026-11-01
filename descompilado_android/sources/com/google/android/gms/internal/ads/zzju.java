package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzju {
    public int index;
    public final int length;
    public int zzaul;
    public long zzaum;
    private final boolean zzaun;
    private final zzoa zzauo;
    private final zzoa zzaup;
    private int zzauq;
    private int zzaur;

    public zzju(zzoa zzoaVar, zzoa zzoaVar2, boolean z) {
        this.zzaup = zzoaVar;
        this.zzauo = zzoaVar2;
        this.zzaun = z;
        zzoaVar2.zzbd(12);
        this.length = zzoaVar2.zzij();
        zzoaVar.zzbd(12);
        this.zzaur = zzoaVar.zzij();
        zznt.checkState(zzoaVar.readInt() == 1, "first_chunk must be 1");
        this.index = -1;
    }

    public final boolean zzgg() {
        int i = this.index + 1;
        this.index = i;
        if (i == this.length) {
            return false;
        }
        this.zzaum = this.zzaun ? this.zzauo.zzik() : this.zzauo.zzig();
        if (this.index == this.zzauq) {
            this.zzaul = this.zzaup.zzij();
            this.zzaup.zzbe(4);
            int i2 = this.zzaur - 1;
            this.zzaur = i2;
            this.zzauq = i2 > 0 ? this.zzaup.zzij() - 1 : -1;
        }
        return true;
    }
}
