package com.google.android.gms.internal.ads;

import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbqk implements zzbqh {
    private final List<String> zzdkg;
    private final zzcxw zzfas;
    private boolean zzfhp;

    public zzbqk(zzcvb zzcvbVar, zzcxw zzcxwVar) {
        this.zzdkg = zzcvbVar.zzdkg;
        this.zzfas = zzcxwVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbqh
    public final void zzagm() {
        if (this.zzfhp) {
            return;
        }
        this.zzfas.zzi(this.zzdkg);
        this.zzfhp = true;
    }
}
