package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdfn extends zzddt<zzdea, zzdip, zzdiq> {
    public zzdfn() {
        super(zzdea.class, zzdip.class, zzdiq.class, "type.googleapis.com/google.crypto.tink.HmacKey");
    }

    @Override // com.google.android.gms.internal.ads.zzddu
    public final int getVersion() {
        return 0;
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final zzdiu.zzb zzapk() {
        return zzdiu.zzb.SYMMETRIC;
    }

    private static void zza(zzdit zzditVar) throws GeneralSecurityException {
        if (zzditVar.zzatm() < 10) {
            throw new GeneralSecurityException("tag size too small");
        }
        int i = zzdfq.zzgsw[zzditVar.zzatl().ordinal()];
        if (i == 1) {
            if (zzditVar.zzatm() > 20) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else if (i == 2) {
            if (zzditVar.zzatm() > 32) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else {
            if (i == 3) {
                if (zzditVar.zzatm() > 64) {
                    throw new GeneralSecurityException("tag size too big");
                }
                return;
            }
            throw new GeneralSecurityException("unknown hash type");
        }
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ zzdrn zzt(zzdot zzdotVar) throws zzdqn {
        return zzdiq.zzbl(zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ zzdrn zzs(zzdot zzdotVar) throws zzdqn {
        return zzdip.zzbk(zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final /* synthetic */ zzdrn zzg(zzdrn zzdrnVar) throws GeneralSecurityException {
        zzdiq zzdiqVar = (zzdiq) zzdrnVar;
        return (zzdip) ((zzdqd) zzdip.zzatg().zzen(0).zzc(zzdiqVar.zzatf()).zzbm(zzdot.zzy(zzdnq.zzfg(zzdiqVar.getKeySize()))).zzazm());
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final /* synthetic */ zzdea zze(zzdrn zzdrnVar) throws GeneralSecurityException {
        zzdip zzdipVar = (zzdip) zzdrnVar;
        zzdin zzdinVarZzatl = zzdipVar.zzatf().zzatl();
        SecretKeySpec secretKeySpec = new SecretKeySpec(zzdipVar.zzaqe().toByteArray(), "HMAC");
        int iZzatm = zzdipVar.zzatf().zzatm();
        int i = zzdfq.zzgsw[zzdinVarZzatl.ordinal()];
        if (i == 1) {
            return new zzdnp("HMACSHA1", secretKeySpec, iZzatm);
        }
        if (i == 2) {
            return new zzdnp("HMACSHA256", secretKeySpec, iZzatm);
        }
        if (i == 3) {
            return new zzdnp("HMACSHA512", secretKeySpec, iZzatm);
        }
        throw new GeneralSecurityException("unknown hash");
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ void zzd(zzdrn zzdrnVar) throws GeneralSecurityException {
        zzdiq zzdiqVar = (zzdiq) zzdrnVar;
        if (zzdiqVar.getKeySize() < 16) {
            throw new GeneralSecurityException("key too short");
        }
        zza(zzdiqVar.zzatf());
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    protected final /* synthetic */ void zzc(zzdrn zzdrnVar) throws GeneralSecurityException {
        zzdip zzdipVar = (zzdip) zzdrnVar;
        zzdob.zzx(zzdipVar.getVersion(), 0);
        if (zzdipVar.zzaqe().size() < 16) {
            throw new GeneralSecurityException("key too short");
        }
        zza(zzdipVar.zzatf());
    }
}
