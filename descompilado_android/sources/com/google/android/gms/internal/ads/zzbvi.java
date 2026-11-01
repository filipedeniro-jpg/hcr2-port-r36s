package com.google.android.gms.internal.ads;

import androidx.collection.ArrayMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbvi implements zzbmt {
    private final zzbtx zzfjc;
    private final zzbub zzfkd;

    public zzbvi(zzbtx zzbtxVar, zzbub zzbubVar) {
        this.zzfjc = zzbtxVar;
        this.zzfkd = zzbubVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbmt
    public final void onAdImpression() {
        if (this.zzfjc.zzahs() == null) {
            return;
        }
        zzbbc zzbbcVarZzahr = this.zzfjc.zzahr();
        zzbbc zzbbcVarZzahq = this.zzfjc.zzahq();
        if (zzbbcVarZzahr == null) {
            zzbbcVarZzahr = zzbbcVarZzahq != null ? zzbbcVarZzahq : null;
        }
        if (!this.zzfkd.zzahh() || zzbbcVarZzahr == null) {
            return;
        }
        zzbbcVarZzahr.zza("onSdkImpression", new ArrayMap());
    }
}
