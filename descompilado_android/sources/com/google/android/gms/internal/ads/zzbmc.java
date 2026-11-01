package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbmc implements zzdvi<zzaro> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzcvb> zzfcf;
    private final zzbmd zzfgj;
    private final zzdvv<zzawv> zzfgk;
    private final zzdvv<zzarq> zzfgl;

    private zzbmc(zzbmd zzbmdVar, zzdvv<Context> zzdvvVar, zzdvv<zzawv> zzdvvVar2, zzdvv<zzcvb> zzdvvVar3, zzdvv<zzarq> zzdvvVar4) {
        this.zzfgj = zzbmdVar;
        this.zzejn = zzdvvVar;
        this.zzfgk = zzdvvVar2;
        this.zzfcf = zzdvvVar3;
        this.zzfgl = zzdvvVar4;
    }

    public static zzbmc zza(zzbmd zzbmdVar, zzdvv<Context> zzdvvVar, zzdvv<zzawv> zzdvvVar2, zzdvv<zzcvb> zzdvvVar3, zzdvv<zzarq> zzdvvVar4) {
        return new zzbmc(zzbmdVar, zzdvvVar, zzdvvVar2, zzdvvVar3, zzdvvVar4);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    @Nullable
    public final /* synthetic */ Object get() {
        Context context = this.zzejn.get();
        zzawv zzawvVar = this.zzfgk.get();
        zzcvb zzcvbVar = this.zzfcf.get();
        zzarq zzarqVar = this.zzfgl.get();
        if (zzcvbVar.zzgja != null) {
            return new zzarf(context, zzawvVar, zzcvbVar.zzgja, zzcvbVar.zzgiw.zzdhn, zzarqVar);
        }
        return null;
    }
}
