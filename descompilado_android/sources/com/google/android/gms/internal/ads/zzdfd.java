package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdfd extends zzddt<zzddp, zzdhz, zzdhv> implements zzddu<zzddp> {
    public zzdfd() {
        super(zzddp.class, zzdhz.class, zzdhv.class, "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey");
    }

    @Override // com.google.android.gms.internal.ads.zzddu
    public final int getVersion() {
        return 0;
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final zzdiu.zzb zzapk() {
        return zzdiu.zzb.ASYMMETRIC_PRIVATE;
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ zzdrn zzt(zzdot zzdotVar) throws zzdqn {
        return zzdhv.zzaz(zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ zzdrn zzs(zzdot zzdotVar) throws zzdqn {
        return zzdhz.zzba(zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final /* synthetic */ zzdrn zzg(zzdrn zzdrnVar) throws GeneralSecurityException {
        zzdhv zzdhvVar = (zzdhv) zzdrnVar;
        KeyPair keyPairZza = zzdmv.zza(zzdmv.zza(zzdfm.zza(zzdhvVar.zzasj().zzasl().zzasw())));
        ECPublicKey eCPublicKey = (ECPublicKey) keyPairZza.getPublic();
        ECPrivateKey eCPrivateKey = (ECPrivateKey) keyPairZza.getPrivate();
        ECPoint w = eCPublicKey.getW();
        return (zzdhz) ((zzdqd) zzdhz.zzasr().zzeh(0).zzb((zzdia) ((zzdqd) zzdia.zzast().zzei(0).zzc(zzdhvVar.zzasj()).zzbd(zzdot.zzy(w.getAffineX().toByteArray())).zzbe(zzdot.zzy(w.getAffineY().toByteArray())).zzazm())).zzbc(zzdot.zzy(eCPrivateKey.getS().toByteArray())).zzazm());
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final /* synthetic */ zzddp zze(zzdrn zzdrnVar) throws GeneralSecurityException {
        zzdhz zzdhzVar = (zzdhz) zzdrnVar;
        zzdhw zzdhwVarZzasj = zzdhzVar.zzasq().zzasj();
        zzdid zzdidVarZzasl = zzdhwVarZzasj.zzasl();
        return new zzdmf(zzdmv.zza(zzdfm.zza(zzdidVarZzasl.zzasw()), zzdhzVar.zzaqe().toByteArray()), zzdidVarZzasl.zzasx().toByteArray(), zzdfm.zza(zzdidVarZzasl.zzaqk()), zzdfm.zza(zzdhwVarZzasj.zzasn()), new zzdfo(zzdhwVarZzasj.zzasm().zzasg()));
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ void zzd(zzdrn zzdrnVar) throws GeneralSecurityException {
        zzdfm.zza(((zzdhv) zzdrnVar).zzasj());
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ void zzc(zzdrn zzdrnVar) throws GeneralSecurityException {
        zzdhz zzdhzVar = (zzdhz) zzdrnVar;
        if (zzdhzVar.zzaqe().size() == 0) {
            throw new GeneralSecurityException("invalid ECIES private key");
        }
        zzdob.zzx(zzdhzVar.getVersion(), 0);
        zzdfm.zza(zzdhzVar.zzasq().zzasj());
    }
}
