package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public final class zzddz {
    @Deprecated
    public static final zzddw zzj(byte[] bArr) throws GeneralSecurityException {
        try {
            zzdje zzdjeVarZzm = zzdje.zzm(bArr);
            zzb(zzdjeVarZzm);
            return zzddw.zza(zzdjeVarZzm);
        } catch (zzdqn unused) {
            throw new GeneralSecurityException("invalid keyset");
        }
    }

    public static final zzddw zza(zzddv zzddvVar) throws GeneralSecurityException, IOException {
        zzdje zzdjeVarZzapi = zzddvVar.zzapi();
        zzb(zzdjeVarZzapi);
        return zzddw.zza(zzdjeVarZzapi);
    }

    private static void zzb(zzdje zzdjeVar) throws GeneralSecurityException {
        for (zzdje.zza zzaVar : zzdjeVar.zzaue()) {
            if (zzaVar.zzauj().zzatr() == zzdiu.zzb.UNKNOWN_KEYMATERIAL || zzaVar.zzauj().zzatr() == zzdiu.zzb.SYMMETRIC || zzaVar.zzauj().zzatr() == zzdiu.zzb.ASYMMETRIC_PRIVATE) {
                throw new GeneralSecurityException("keyset contains secret key material");
            }
        }
    }
}
