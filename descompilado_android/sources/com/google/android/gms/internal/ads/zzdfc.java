package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdfc extends zzddt<zzdds, zzdia, zzdij> {
    public zzdfc() {
        super(zzdds.class, zzdia.class, zzdij.class, "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey");
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
        return zzdiu.zzb.ASYMMETRIC_PUBLIC;
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ zzdrn zzt(zzdot zzdotVar) throws zzdqn {
        return zzdij.zzbj(zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ zzdrn zzs(zzdot zzdotVar) throws zzdqn {
        return zzdia.zzbb(zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final /* synthetic */ zzdrn zzg(zzdrn zzdrnVar) throws GeneralSecurityException {
        throw new GeneralSecurityException("Not implemented.");
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ zzdds zze(zzdrn zzdrnVar) throws GeneralSecurityException {
        zzdia zzdiaVar = (zzdia) zzdrnVar;
        zzdhw zzdhwVarZzasj = zzdiaVar.zzasj();
        zzdid zzdidVarZzasl = zzdhwVarZzasj.zzasl();
        return new zzdme(zzdmv.zza(zzdfm.zza(zzdidVarZzasl.zzasw()), zzdiaVar.zzasb().toByteArray(), zzdiaVar.zzasc().toByteArray()), zzdidVarZzasl.zzasx().toByteArray(), zzdfm.zza(zzdidVarZzasl.zzaqk()), zzdfm.zza(zzdhwVarZzasj.zzasn()), new zzdfo(zzdhwVarZzasj.zzasm().zzasg()));
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ void zzc(zzdrn zzdrnVar) throws GeneralSecurityException {
        zzdia zzdiaVar = (zzdia) zzdrnVar;
        zzdob.zzx(zzdiaVar.getVersion(), 0);
        zzdfm.zza(zzdiaVar.zzasj());
    }
}
