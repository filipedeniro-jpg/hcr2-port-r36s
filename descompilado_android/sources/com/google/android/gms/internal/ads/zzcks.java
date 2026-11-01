package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcks implements zzckm<zzbrc> {
    private final zzbry zzfxh;
    private final Context zzlk;

    public zzcks(Context context, zzbry zzbryVar) {
        this.zzlk = context;
        this.zzfxh = zzbryVar;
    }

    @Override // com.google.android.gms.internal.ads.zzckm
    public final /* synthetic */ zzbrc zza(zzcvj zzcvjVar, zzcvb zzcvbVar, View view, zzckp zzckpVar) {
        zzbre zzbreVarZza = this.zzfxh.zza(new zzbkk(zzcvjVar, zzcvbVar, null), new zzcku(this, zzckr.zzgaq));
        zzckpVar.zza(new zzckt(this, zzbreVarZza));
        return zzbreVarZza.zzade();
    }
}
