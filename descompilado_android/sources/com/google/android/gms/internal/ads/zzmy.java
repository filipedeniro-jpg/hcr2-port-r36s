package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzmy {
    public final zzmi zzbec;
    public final zzmx zzbed;
    public final Object zzbee;
    public final zzgx[] zzbef;

    public zzmy(zzmi zzmiVar, zzmx zzmxVar, Object obj, zzgx[] zzgxVarArr) {
        this.zzbec = zzmiVar;
        this.zzbed = zzmxVar;
        this.zzbee = obj;
        this.zzbef = zzgxVarArr;
    }

    public final boolean zza(zzmy zzmyVar, int i) {
        return zzmyVar != null && zzoh.zza(this.zzbed.zzax(i), zzmyVar.zzbed.zzax(i)) && zzoh.zza(this.zzbef[i], zzmyVar.zzbef[i]);
    }
}
