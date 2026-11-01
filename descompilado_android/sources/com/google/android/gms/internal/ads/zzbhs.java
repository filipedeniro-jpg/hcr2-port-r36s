package com.google.android.gms.internal.ads;

import android.view.View;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbhs extends zzbju {
    private final View view;

    @Nullable
    private final zzbbc zzcyx;
    private final zzcva zzfcp;
    private final int zzfcq;

    @Nullable
    private zzqz zzfcv;

    zzbhs(zzbjt zzbjtVar, View view, @Nullable zzbbc zzbbcVar, zzcva zzcvaVar, int i) {
        super(zzbjtVar);
        this.view = view;
        this.zzcyx = zzbbcVar;
        this.zzfcp = zzcvaVar;
        this.zzfcq = i;
    }

    public final zzcva zzaeq() {
        return zzcvo.zza(this.zzfef.zzgiv, this.zzfcp);
    }

    public final View zzaer() {
        return this.view;
    }

    public final int zzaen() {
        return this.zzfcq;
    }

    public final boolean zzzr() {
        zzbbc zzbbcVar = this.zzcyx;
        return zzbbcVar != null && zzbbcVar.zzyy().zzzr();
    }

    public final boolean zzaes() {
        zzbbc zzbbcVar = this.zzcyx;
        return zzbbcVar != null && zzbbcVar.zzza();
    }

    public final void zza(zzqt zzqtVar) {
        zzbbc zzbbcVar = this.zzcyx;
        if (zzbbcVar != null) {
            zzbbcVar.zza(zzqtVar);
        }
    }

    public final void zza(zzqz zzqzVar) {
        this.zzfcv = zzqzVar;
    }

    @Nullable
    public final zzqz zzaet() {
        return this.zzfcv;
    }
}
