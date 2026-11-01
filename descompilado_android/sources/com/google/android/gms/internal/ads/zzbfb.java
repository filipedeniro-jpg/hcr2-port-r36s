package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbfb implements zzcut {
    private final /* synthetic */ zzbeg zzele;
    private zzdvv<Context> zzeyk;
    private zzdvv<zzctn<zzbym, zzbyf>> zzeyl;
    private zzdvv<zzcto> zzeym;
    private zzdvv<zzcvi> zzeyn;
    private zzdvv<zzcul> zzeyo;
    private zzdvv<zzcuv> zzeyp;
    private zzdvv<String> zzeyq;
    private zzdvv<zzcup> zzeyr;

    private zzbfb(zzbeg zzbegVar, Context context, String str) {
        this.zzele = zzbegVar;
        this.zzeyk = zzdvl.zzbb(context);
        this.zzeyl = new zzctm(this.zzeyk);
        this.zzeym = zzdvj.zzan(zzcuj.zzamq());
        this.zzeyn = zzdvj.zzan(zzcvl.zzamw());
        this.zzeyo = zzdvj.zzan(new zzcum(this.zzeyk, this.zzele.zzejp, this.zzele.zzejk, this.zzeyl, this.zzeym, zzcvp.zzana(), this.zzeyn));
        this.zzeyp = zzdvj.zzan(new zzcuw(this.zzeyo, this.zzeym, this.zzeyn));
        this.zzeyq = zzdvl.zzbc(str);
        this.zzeyr = zzdvj.zzan(new zzcuq(this.zzeyq, this.zzeyo, this.zzeym, this.zzeyn));
    }

    @Override // com.google.android.gms.internal.ads.zzcut
    public final zzcuv zzadi() {
        return this.zzeyp.get();
    }

    @Override // com.google.android.gms.internal.ads.zzcut
    public final zzcup zzadj() {
        return this.zzeyr.get();
    }
}
