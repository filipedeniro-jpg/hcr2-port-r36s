package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdev extends zzddt<zzddj, zzdkj, zzdij> {
    public zzdev() {
        super(zzddj.class, zzdkj.class, zzdij.class, "type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key");
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
        return zzdiu.zzb.SYMMETRIC;
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ zzdrn zzt(zzdot zzdotVar) throws zzdqn {
        return zzdij.zzbj(zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ zzdrn zzs(zzdot zzdotVar) throws zzdqn {
        return zzdkj.zzcv(zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ zzdrn zzg(zzdrn zzdrnVar) throws GeneralSecurityException {
        return (zzdkj) ((zzdqd) zzdkj.zzawn().zzff(0).zzcy(zzdot.zzy(zzdnq.zzfg(32))).zzazm());
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final /* synthetic */ zzddj zze(zzdrn zzdrnVar) throws GeneralSecurityException {
        return new zzdoc(((zzdkj) zzdrnVar).zzaqe().toByteArray());
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ void zzc(zzdrn zzdrnVar) throws GeneralSecurityException {
        zzdkj zzdkjVar = (zzdkj) zzdrnVar;
        zzdob.zzx(zzdkjVar.getVersion(), 0);
        if (zzdkjVar.zzaqe().size() != 32) {
            throw new GeneralSecurityException("invalid XChaCha20Poly1305Key: incorrect key length");
        }
    }
}
