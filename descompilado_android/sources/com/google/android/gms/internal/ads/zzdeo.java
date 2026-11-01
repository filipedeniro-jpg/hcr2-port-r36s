package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdeo extends zzddt<zzddj, zzdfv, zzdfw> {
    public zzdeo() throws GeneralSecurityException {
        super(zzddj.class, zzdfv.class, zzdfw.class, "type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey");
        zzdef.zza(new zzdeq());
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
        return zzdfw.zzv(zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ zzdrn zzs(zzdot zzdotVar) throws zzdqn {
        return zzdfv.zzu(zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final /* synthetic */ zzdrn zzg(zzdrn zzdrnVar) throws GeneralSecurityException {
        zzdfw zzdfwVar = (zzdfw) zzdrnVar;
        zzdge zzdgeVar = (zzdge) zzdef.zza("type.googleapis.com/google.crypto.tink.AesCtrKey", zzdfwVar.zzaqa());
        return (zzdfv) ((zzdqd) zzdfv.zzapy().zzb(zzdgeVar).zzb((zzdip) zzdef.zza("type.googleapis.com/google.crypto.tink.HmacKey", zzdfwVar.zzaqb())).zzdv(0).zzazm());
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final /* synthetic */ zzddj zze(zzdrn zzdrnVar) throws GeneralSecurityException {
        zzdfv zzdfvVar = (zzdfv) zzdrnVar;
        return new zzdmy((zzdnm) zzdef.zza("type.googleapis.com/google.crypto.tink.AesCtrKey", zzdfvVar.zzapw(), zzdnm.class), (zzdea) zzdef.zza("type.googleapis.com/google.crypto.tink.HmacKey", zzdfvVar.zzapx(), zzdea.class), zzdfvVar.zzapx().zzatf().zzatm());
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ void zzd(zzdrn zzdrnVar) throws GeneralSecurityException {
        zzdob.zzfh(((zzdfw) zzdrnVar).zzaqa().getKeySize());
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ void zzc(zzdrn zzdrnVar) throws GeneralSecurityException {
        zzdob.zzx(((zzdfv) zzdrnVar).getVersion(), 0);
    }
}
