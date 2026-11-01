package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzdrn;
import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzddt<P, KeyProto extends zzdrn, KeyFormatProto extends zzdrn> implements zzddu<P> {
    private final Class<P> zzgrx;
    private final Class<KeyProto> zzgry;
    private final Class<KeyFormatProto> zzgrz;
    private final String zzgsa;

    protected zzddt(Class<P> cls, Class<KeyProto> cls2, Class<KeyFormatProto> cls3, String str) {
        this.zzgrx = cls;
        this.zzgry = cls2;
        this.zzgrz = cls3;
        this.zzgsa = str;
    }

    protected abstract zzdiu.zzb zzapk();

    protected abstract void zzc(KeyProto keyproto) throws GeneralSecurityException;

    protected abstract void zzd(KeyFormatProto keyformatproto) throws GeneralSecurityException;

    protected abstract P zze(KeyProto keyproto) throws GeneralSecurityException;

    protected abstract KeyProto zzg(KeyFormatProto keyformatproto) throws GeneralSecurityException;

    protected abstract KeyProto zzs(zzdot zzdotVar) throws zzdqn;

    protected abstract KeyFormatProto zzt(zzdot zzdotVar) throws zzdqn;

    /* JADX WARN: Multi-variable type inference failed */
    private static <Casted> Casted zza(Object obj, String str, Class<Casted> cls) throws GeneralSecurityException {
        if (cls.isInstance(obj)) {
            return obj;
        }
        throw new GeneralSecurityException(str);
    }

    @Override // com.google.android.gms.internal.ads.zzddu
    public final P zzp(zzdot zzdotVar) throws GeneralSecurityException {
        try {
            return zzf(zzs(zzdotVar));
        } catch (zzdqn e) {
            String strValueOf = String.valueOf(this.zzgry.getName());
            throw new GeneralSecurityException(strValueOf.length() != 0 ? "Failures parsing proto of type ".concat(strValueOf) : new String("Failures parsing proto of type "), e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzddu
    public final P zza(zzdrn zzdrnVar) throws GeneralSecurityException {
        String strValueOf = String.valueOf(this.zzgry.getName());
        return zzf((zzdrn) zza(zzdrnVar, strValueOf.length() != 0 ? "Expected proto of type ".concat(strValueOf) : new String("Expected proto of type "), this.zzgry));
    }

    @Override // com.google.android.gms.internal.ads.zzddu
    public final zzdrn zzq(zzdot zzdotVar) throws GeneralSecurityException {
        try {
            return zzh(zzt(zzdotVar));
        } catch (zzdqn e) {
            String strValueOf = String.valueOf(this.zzgrz.getName());
            throw new GeneralSecurityException(strValueOf.length() != 0 ? "Failures parsing proto of type ".concat(strValueOf) : new String("Failures parsing proto of type "), e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzddu
    public final zzdrn zzb(zzdrn zzdrnVar) throws GeneralSecurityException {
        String strValueOf = String.valueOf(this.zzgrz.getName());
        return zzh((zzdrn) zza(zzdrnVar, strValueOf.length() != 0 ? "Expected proto of type ".concat(strValueOf) : new String("Expected proto of type "), this.zzgrz));
    }

    @Override // com.google.android.gms.internal.ads.zzddu
    public final String getKeyType() {
        return this.zzgsa;
    }

    @Override // com.google.android.gms.internal.ads.zzddu
    public final zzdiu zzr(zzdot zzdotVar) throws GeneralSecurityException {
        try {
            return (zzdiu) ((zzdqd) zzdiu.zzats().zzgw(this.zzgsa).zzbo(zzh(zzt(zzdotVar)).zzaxb()).zzb(zzapk()).zzazm());
        } catch (zzdqn e) {
            throw new GeneralSecurityException("Unexpected proto", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzddu
    public final Class<P> zzapj() {
        return this.zzgrx;
    }

    private final P zzf(KeyProto keyproto) throws GeneralSecurityException {
        zzc(keyproto);
        return zze(keyproto);
    }

    private final KeyProto zzh(KeyFormatProto keyformatproto) throws GeneralSecurityException {
        zzd(keyformatproto);
        KeyProto keyproto = (KeyProto) zzg(keyformatproto);
        zzc(keyproto);
        return keyproto;
    }
}
