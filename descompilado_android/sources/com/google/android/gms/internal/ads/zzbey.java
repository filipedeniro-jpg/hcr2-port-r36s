package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbey implements zzcus {
    private final /* synthetic */ zzbeg zzele;
    private Context zzext;
    private String zzexu;

    private zzbey(zzbeg zzbegVar) {
        this.zzele = zzbegVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcus
    public final zzcut zzadd() {
        zzdvo.zza(this.zzext, (Class<Context>) Context.class);
        return new zzbfb(this.zzele, this.zzext, this.zzexu);
    }

    @Override // com.google.android.gms.internal.ads.zzcus
    public final /* synthetic */ zzcus zzfm(String str) {
        this.zzexu = str;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzcus
    public final /* synthetic */ zzcus zzbs(Context context) {
        this.zzext = (Context) zzdvo.checkNotNull(context);
        return this;
    }
}
