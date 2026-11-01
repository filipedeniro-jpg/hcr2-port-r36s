package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzder extends zzddt<zzddj, zzdhf, zzdij> {
    public zzder() {
        super(zzddj.class, zzdhf.class, zzdij.class, "type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key");
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
        return zzdhf.zzap(zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ zzdrn zzg(zzdrn zzdrnVar) throws GeneralSecurityException {
        return (zzdhf) ((zzdqd) zzdhf.zzarp().zzed(0).zzaq(zzdot.zzy(zzdnq.zzfg(32))).zzazm());
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final /* synthetic */ zzddj zze(zzdrn zzdrnVar) throws GeneralSecurityException {
        return new zzdlz(((zzdhf) zzdrnVar).zzaqe().toByteArray());
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ void zzc(zzdrn zzdrnVar) throws GeneralSecurityException {
        zzdhf zzdhfVar = (zzdhf) zzdrnVar;
        zzdob.zzx(zzdhfVar.getVersion(), 0);
        if (zzdhfVar.zzaqe().size() != 32) {
            throw new GeneralSecurityException("invalid ChaCha20Poly1305Key: incorrect key length");
        }
    }
}
