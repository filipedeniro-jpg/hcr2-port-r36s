package com.google.android.gms.internal.ads;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzga implements zzgv, zzgy {
    private int index;
    private int state;
    private final int zzaby;
    private zzgx zzabz;
    private zzmf zzaca;
    private long zzacb;
    private boolean zzacc = true;
    private boolean zzacd;

    public zzga(int i) {
        this.zzaby = i;
    }

    protected void onStarted() throws zzgb {
    }

    protected void onStopped() throws zzgb {
    }

    @Override // com.google.android.gms.internal.ads.zzgg
    public void zza(int i, Object obj) throws zzgb {
    }

    protected void zza(long j, boolean z) throws zzgb {
    }

    protected void zza(zzgq[] zzgqVarArr, long j) throws zzgb {
    }

    protected void zzd(boolean z) throws zzgb {
    }

    @Override // com.google.android.gms.internal.ads.zzgv
    public final zzgy zzdg() {
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzgv
    public zznx zzdh() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzgy
    public int zzdn() throws zzgb {
        return 0;
    }

    protected void zzdo() {
    }

    @Override // com.google.android.gms.internal.ads.zzgv, com.google.android.gms.internal.ads.zzgy
    public final int getTrackType() {
        return this.zzaby;
    }

    @Override // com.google.android.gms.internal.ads.zzgv
    public final void setIndex(int i) {
        this.index = i;
    }

    @Override // com.google.android.gms.internal.ads.zzgv
    public final int getState() {
        return this.state;
    }

    @Override // com.google.android.gms.internal.ads.zzgv
    public final void zza(zzgx zzgxVar, zzgq[] zzgqVarArr, zzmf zzmfVar, long j, boolean z, long j2) throws zzgb {
        zznt.checkState(this.state == 0);
        this.zzabz = zzgxVar;
        this.state = 1;
        zzd(z);
        zza(zzgqVarArr, zzmfVar, j2);
        zza(j, z);
    }

    @Override // com.google.android.gms.internal.ads.zzgv
    public final void start() throws zzgb {
        zznt.checkState(this.state == 1);
        this.state = 2;
        onStarted();
    }

    @Override // com.google.android.gms.internal.ads.zzgv
    public final void zza(zzgq[] zzgqVarArr, zzmf zzmfVar, long j) throws zzgb {
        zznt.checkState(!this.zzacd);
        this.zzaca = zzmfVar;
        this.zzacc = false;
        this.zzacb = j;
        zza(zzgqVarArr, j);
    }

    @Override // com.google.android.gms.internal.ads.zzgv
    public final zzmf zzdi() {
        return this.zzaca;
    }

    @Override // com.google.android.gms.internal.ads.zzgv
    public final boolean zzdj() {
        return this.zzacc;
    }

    @Override // com.google.android.gms.internal.ads.zzgv
    public final void zzdk() {
        this.zzacd = true;
    }

    @Override // com.google.android.gms.internal.ads.zzgv
    public final boolean zzdl() {
        return this.zzacd;
    }

    @Override // com.google.android.gms.internal.ads.zzgv
    public final void zzdm() throws IOException {
        this.zzaca.zzhb();
    }

    @Override // com.google.android.gms.internal.ads.zzgv
    public final void zzdg(long j) throws zzgb {
        this.zzacd = false;
        this.zzacc = false;
        zza(j, false);
    }

    @Override // com.google.android.gms.internal.ads.zzgv
    public final void stop() throws zzgb {
        zznt.checkState(this.state == 2);
        this.state = 1;
        onStopped();
    }

    @Override // com.google.android.gms.internal.ads.zzgv
    public final void disable() {
        zznt.checkState(this.state == 1);
        this.state = 0;
        this.zzaca = null;
        this.zzacd = false;
        zzdo();
    }

    protected final zzgx zzdp() {
        return this.zzabz;
    }

    protected final int getIndex() {
        return this.index;
    }

    protected final int zza(zzgs zzgsVar, zzim zzimVar, boolean z) {
        int iZzb = this.zzaca.zzb(zzgsVar, zzimVar, z);
        if (iZzb == -4) {
            if (zzimVar.zzfp()) {
                this.zzacc = true;
                return this.zzacd ? -4 : -3;
            }
            zzimVar.zzami += this.zzacb;
        } else if (iZzb == -5) {
            zzgq zzgqVar = zzgsVar.zzafw;
            if (zzgqVar.zzafq != Long.MAX_VALUE) {
                zzgsVar.zzafw = zzgqVar.zzdm(zzgqVar.zzafq + this.zzacb);
            }
        }
        return iZzb;
    }

    protected final void zzdh(long j) {
        this.zzaca.zzeb(j - this.zzacb);
    }

    protected final boolean zzdq() {
        return this.zzacc ? this.zzacd : this.zzaca.isReady();
    }
}
