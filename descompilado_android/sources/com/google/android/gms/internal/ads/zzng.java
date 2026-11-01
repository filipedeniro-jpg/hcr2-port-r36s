package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzng implements zzna {
    private final boolean zzbei;
    private final int zzbej;
    private final byte[] zzbek;
    private final zznb[] zzbel;
    private int zzbem;
    private int zzben;
    private int zzbeo;
    private zznb[] zzbep;

    public zzng(boolean z, int i) {
        this(true, 65536, 0);
    }

    private zzng(boolean z, int i, int i2) {
        zznt.checkArgument(true);
        zznt.checkArgument(true);
        this.zzbei = true;
        this.zzbej = 65536;
        this.zzbeo = 0;
        this.zzbep = new zznb[100];
        this.zzbek = null;
        this.zzbel = new zznb[1];
    }

    public final synchronized void reset() {
        if (this.zzbei) {
            zzaz(0);
        }
    }

    public final synchronized void zzaz(int i) {
        boolean z = i < this.zzbem;
        this.zzbem = i;
        if (z) {
            zzm();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzna
    public final synchronized zznb zzhw() {
        zznb zznbVar;
        this.zzben++;
        if (this.zzbeo > 0) {
            zznb[] zznbVarArr = this.zzbep;
            int i = this.zzbeo - 1;
            this.zzbeo = i;
            zznbVar = zznbVarArr[i];
            this.zzbep[this.zzbeo] = null;
        } else {
            zznbVar = new zznb(new byte[this.zzbej], 0);
        }
        return zznbVar;
    }

    @Override // com.google.android.gms.internal.ads.zzna
    public final synchronized void zza(zznb zznbVar) {
        this.zzbel[0] = zznbVar;
        zza(this.zzbel);
    }

    @Override // com.google.android.gms.internal.ads.zzna
    public final synchronized void zza(zznb[] zznbVarArr) {
        if (this.zzbeo + zznbVarArr.length >= this.zzbep.length) {
            this.zzbep = (zznb[]) Arrays.copyOf(this.zzbep, Math.max(this.zzbep.length << 1, this.zzbeo + zznbVarArr.length));
        }
        for (zznb zznbVar : zznbVarArr) {
            zznt.checkArgument(zznbVar.data == null || zznbVar.data.length == this.zzbej);
            zznb[] zznbVarArr2 = this.zzbep;
            int i = this.zzbeo;
            this.zzbeo = i + 1;
            zznbVarArr2[i] = zznbVar;
        }
        this.zzben -= zznbVarArr.length;
        notifyAll();
    }

    @Override // com.google.android.gms.internal.ads.zzna
    public final synchronized void zzm() {
        int iMax = Math.max(0, zzoh.zze(this.zzbem, this.zzbej) - this.zzben);
        if (iMax >= this.zzbeo) {
            return;
        }
        Arrays.fill(this.zzbep, iMax, this.zzbeo, (Object) null);
        this.zzbeo = iMax;
    }

    public final synchronized int zzhz() {
        return this.zzben * this.zzbej;
    }

    @Override // com.google.android.gms.internal.ads.zzna
    public final int zzhx() {
        return this.zzbej;
    }
}
