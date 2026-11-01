package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzmw {
    private zzmz zzbea;

    public abstract zzmy zza(zzgy[] zzgyVarArr, zzmi zzmiVar) throws zzgb;

    public abstract void zzd(Object obj);

    public final void zza(zzmz zzmzVar) {
        this.zzbea = zzmzVar;
    }

    protected final void invalidate() {
        zzmz zzmzVar = this.zzbea;
        if (zzmzVar != null) {
            zzmzVar.zzdw();
        }
    }
}
