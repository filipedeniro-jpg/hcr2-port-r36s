package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdep extends zzddt<zzddj, zzdgl, zzdgm> {
    public zzdep() {
        super(zzddj.class, zzdgl.class, zzdgm.class, "type.googleapis.com/google.crypto.tink.AesEaxKey");
    }

    @Override // com.google.android.gms.internal.ads.zzddu
    public final int getVersion() {
        return 0;
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final zzdiu.zzb zzapk() {
        return zzdiu.zzb.SYMMETRIC;
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ zzdrn zzt(zzdot zzdotVar) throws zzdqn {
        return zzdgm.zzae(zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ zzdrn zzs(zzdot zzdotVar) throws zzdqn {
        return zzdgl.zzad(zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final /* synthetic */ zzdrn zzg(zzdrn zzdrnVar) throws GeneralSecurityException {
        zzdgm zzdgmVar = (zzdgm) zzdrnVar;
        return (zzdgl) ((zzdqd) zzdgl.zzaqy().zzaf(zzdot.zzy(zzdnq.zzfg(zzdgmVar.getKeySize()))).zzb(zzdgmVar.zzaqx()).zzdy(0).zzazm());
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final /* synthetic */ zzddj zze(zzdrn zzdrnVar) throws GeneralSecurityException {
        zzdgl zzdglVar = (zzdgl) zzdrnVar;
        return new zzdlm(zzdglVar.zzaqe().toByteArray(), zzdglVar.zzaqx().zzaqu());
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ void zzd(zzdrn zzdrnVar) throws GeneralSecurityException {
        zzdgm zzdgmVar = (zzdgm) zzdrnVar;
        zzdob.zzfh(zzdgmVar.getKeySize());
        if (zzdgmVar.zzaqx().zzaqu() != 12 && zzdgmVar.zzaqx().zzaqu() != 16) {
            throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
        }
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ void zzc(zzdrn zzdrnVar) throws GeneralSecurityException {
        zzdgl zzdglVar = (zzdgl) zzdrnVar;
        zzdob.zzx(zzdglVar.getVersion(), 0);
        zzdob.zzfh(zzdglVar.zzaqe().size());
        if (zzdglVar.zzaqx().zzaqu() != 12 && zzdglVar.zzaqx().zzaqu() != 16) {
            throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
        }
    }
}
