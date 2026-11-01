package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdeq extends zzddt<zzdnm, zzdge, zzdgh> {
    public zzdeq() {
        super(zzdnm.class, zzdge.class, zzdgh.class, "type.googleapis.com/google.crypto.tink.AesCtrKey");
    }

    @Override // com.google.android.gms.internal.ads.zzddu
    public final int getVersion() {
        return 0;
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final zzdiu.zzb zzapk() {
        return zzdiu.zzb.SYMMETRIC;
    }

    private static void zza(zzdgi zzdgiVar) throws GeneralSecurityException {
        if (zzdgiVar.zzaqu() < 12 || zzdgiVar.zzaqu() > 16) {
            throw new GeneralSecurityException("invalid IV size");
        }
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ zzdrn zzt(zzdot zzdotVar) throws zzdqn {
        return zzdgh.zzac(zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ zzdrn zzs(zzdot zzdotVar) throws zzdqn {
        return zzdge.zzaa(zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final /* synthetic */ zzdrn zzg(zzdrn zzdrnVar) throws GeneralSecurityException {
        zzdgh zzdghVar = (zzdgh) zzdrnVar;
        return (zzdge) ((zzdqd) zzdge.zzaqp().zzc(zzdghVar.zzaqo()).zzab(zzdot.zzy(zzdnq.zzfg(zzdghVar.getKeySize()))).zzdx(0).zzazm());
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final /* synthetic */ zzdnm zze(zzdrn zzdrnVar) throws GeneralSecurityException {
        zzdge zzdgeVar = (zzdge) zzdrnVar;
        return new zzdln(zzdgeVar.zzaqe().toByteArray(), zzdgeVar.zzaqo().zzaqu());
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ void zzd(zzdrn zzdrnVar) throws GeneralSecurityException {
        zzdgh zzdghVar = (zzdgh) zzdrnVar;
        zzdob.zzfh(zzdghVar.getKeySize());
        zza(zzdghVar.zzaqo());
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ void zzc(zzdrn zzdrnVar) throws GeneralSecurityException {
        zzdge zzdgeVar = (zzdge) zzdrnVar;
        zzdob.zzx(zzdgeVar.getVersion(), 0);
        zzdob.zzfh(zzdgeVar.zzaqe().size());
        zza(zzdgeVar.zzaqo());
    }
}
