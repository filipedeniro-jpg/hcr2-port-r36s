package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdew extends zzddt<zzddj, zzdjm, zzdjn> {
    public zzdew() {
        super(zzddj.class, zzdjm.class, zzdjn.class, "type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey");
    }

    @Override // com.google.android.gms.internal.ads.zzddu
    public final int getVersion() {
        return 0;
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* bridge */ /* synthetic */ void zzd(zzdrn zzdrnVar) throws GeneralSecurityException {
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final zzdiu.zzb zzapk() {
        return zzdiu.zzb.REMOTE;
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ zzdrn zzt(zzdot zzdotVar) throws zzdqn {
        return zzdjn.zzbs(zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ zzdrn zzs(zzdot zzdotVar) throws zzdqn {
        return zzdjm.zzbr(zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final /* synthetic */ zzdrn zzg(zzdrn zzdrnVar) throws GeneralSecurityException {
        return (zzdjm) ((zzdqd) zzdjm.zzauy().zzb((zzdjn) zzdrnVar).zzez(0).zzazm());
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final /* synthetic */ zzddj zze(zzdrn zzdrnVar) throws GeneralSecurityException {
        zzdjm zzdjmVar = (zzdjm) zzdrnVar;
        String strZzava = zzdjmVar.zzaux().zzava();
        return new zzdet(zzdjmVar.zzaux().zzavb(), zzddx.zzgp(strZzava).zzgr(strZzava));
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ void zzc(zzdrn zzdrnVar) throws GeneralSecurityException {
        zzdob.zzx(((zzdjm) zzdrnVar).getVersion(), 0);
    }
}
