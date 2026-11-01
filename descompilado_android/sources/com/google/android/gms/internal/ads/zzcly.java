package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcly {
    private final zzbui zzgcn;
    private final zzcls zzgco = new zzcls();
    private final zzbml zzgcp;

    public zzcly(zzbui zzbuiVar) {
        this.zzgcn = zzbuiVar;
        final zzcls zzclsVar = this.zzgco;
        final zzafp zzafpVarZzaie = this.zzgcn.zzaie();
        this.zzgcp = new zzbml(zzclsVar, zzafpVarZzaie) { // from class: com.google.android.gms.internal.ads.zzclx
            private final zzcls zzgcl;
            private final zzafp zzgcm;

            {
                this.zzgcl = zzclsVar;
                this.zzgcm = zzafpVarZzaie;
            }

            @Override // com.google.android.gms.internal.ads.zzbml
            public final void onAdFailedToLoad(int i) {
                zzcls zzclsVar2 = this.zzgcl;
                zzafp zzafpVar = this.zzgcm;
                zzclsVar2.onAdFailedToLoad(i);
                if (zzafpVar != null) {
                    try {
                        zzafpVar.zzcl(i);
                    } catch (RemoteException e) {
                        zzawo.zze("#007 Could not call remote method.", e);
                    }
                }
            }
        };
    }

    public final void zzc(zzur zzurVar) {
        this.zzgco.zzc(zzurVar);
    }

    public final zzbsn zzalh() {
        return new zzbsn(this.zzgcn, this.zzgco.zzale());
    }

    public final zzbmg zzali() {
        return this.zzgco;
    }

    public final zzbnm zzalj() {
        return this.zzgco;
    }

    public final zzbml zzalk() {
        return this.zzgcp;
    }

    public final zzbmt zzall() {
        return this.zzgco;
    }

    public final zzth zzalm() {
        return this.zzgco;
    }
}
