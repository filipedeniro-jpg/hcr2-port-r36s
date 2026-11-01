package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzddl {
    public static zzdjb zza(String str, String str2, String str3, int i, boolean z) {
        zzdjb.zza zzaVarZzgz = zzdjb.zzaub().zzgz(str2);
        String strValueOf = String.valueOf(str3);
        return (zzdjb) ((zzdqd) zzaVarZzgz.zzha(strValueOf.length() != 0 ? "type.googleapis.com/google.crypto.tink.".concat(strValueOf) : new String("type.googleapis.com/google.crypto.tink.")).zzer(0).zzbg(true).zzhb(str).zzazm());
    }

    public static void zza(zzdjs zzdjsVar) throws GeneralSecurityException {
        for (zzdjb zzdjbVar : zzdjsVar.zzave()) {
            if (zzdjbVar.zzatp().isEmpty()) {
                throw new GeneralSecurityException("Missing type_url.");
            }
            if (zzdjbVar.zzatx().isEmpty()) {
                throw new GeneralSecurityException("Missing primitive_name.");
            }
            if (zzdjbVar.zzaua().isEmpty()) {
                throw new GeneralSecurityException("Missing catalogue_name.");
            }
            zzddm<?> zzddmVarZzgt = zzdef.zzgt(zzdjbVar.zzaua());
            zzdef.zza(zzddmVarZzgt.zzaph());
            zzdef.zza(zzddmVarZzgt.zzb(zzdjbVar.zzatp(), zzdjbVar.zzatx(), zzdjbVar.zzaty()), zzdjbVar.zzatz());
        }
    }
}
