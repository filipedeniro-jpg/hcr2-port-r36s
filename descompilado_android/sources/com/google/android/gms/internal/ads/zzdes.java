package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdes extends zzddt<zzddj, zzdgx, zzdgy> {
    public zzdes() {
        super(zzddj.class, zzdgx.class, zzdgy.class, "type.googleapis.com/google.crypto.tink.AesGcmKey");
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
        return zzdgy.zzak(zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ zzdrn zzs(zzdot zzdotVar) throws zzdqn {
        return zzdgx.zzaj(zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ zzdrn zzg(zzdrn zzdrnVar) throws GeneralSecurityException {
        return (zzdgx) ((zzdqd) zzdgx.zzarj().zzal(zzdot.zzy(zzdnq.zzfg(((zzdgy) zzdrnVar).getKeySize()))).zzea(0).zzazm());
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ zzddj zze(zzdrn zzdrnVar) throws GeneralSecurityException {
        return new zzdlo(((zzdgx) zzdrnVar).zzaqe().toByteArray());
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ void zzd(zzdrn zzdrnVar) throws GeneralSecurityException {
        zzdob.zzfh(((zzdgy) zzdrnVar).getKeySize());
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ void zzc(zzdrn zzdrnVar) throws GeneralSecurityException {
        zzdgx zzdgxVar = (zzdgx) zzdrnVar;
        zzdob.zzx(zzdgxVar.getVersion(), 0);
        zzdob.zzfh(zzdgxVar.zzaqe().size());
    }
}
