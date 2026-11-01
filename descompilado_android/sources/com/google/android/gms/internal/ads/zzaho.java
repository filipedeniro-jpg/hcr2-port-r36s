package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaho extends zzaxi<zzagk> {
    private zzavb<zzagk> zzczg;
    private final Object lock = new Object();
    private boolean zzdab = false;
    private int zzdac = 0;

    public zzaho(zzavb<zzagk> zzavbVar) {
        this.zzczg = zzavbVar;
    }

    public final zzahk zzrc() {
        zzahk zzahkVar = new zzahk(this);
        synchronized (this.lock) {
            zza(new zzahn(this, zzahkVar), new zzahq(this, zzahkVar));
            Preconditions.checkState(this.zzdac >= 0);
            this.zzdac++;
        }
        return zzahkVar;
    }

    protected final void zzrd() {
        synchronized (this.lock) {
            Preconditions.checkState(this.zzdac > 0);
            zzatm.zzdy("Releasing 1 reference for JS Engine");
            this.zzdac--;
            zzrf();
        }
    }

    public final void zzre() {
        synchronized (this.lock) {
            Preconditions.checkState(this.zzdac >= 0);
            zzatm.zzdy("Releasing root reference. JS Engine will be destroyed once other references are released.");
            this.zzdab = true;
            zzrf();
        }
    }

    private final void zzrf() {
        synchronized (this.lock) {
            Preconditions.checkState(this.zzdac >= 0);
            if (this.zzdab && this.zzdac == 0) {
                zzatm.zzdy("No reference is left (including root). Cleaning up engine.");
                zza(new zzahp(this), new zzaxg());
            } else {
                zzatm.zzdy("There are still references to the engine. Not destroying.");
            }
        }
    }
}
