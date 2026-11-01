package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdff {

    @Deprecated
    public static final zzdjs zzgso = (zzdjs) ((zzdqd) zzdjs.zzavf().zza(zzdek.zzgso).zzb(zzddl.zza("TinkHybridDecrypt", "HybridDecrypt", "EciesAeadHkdfPrivateKey", 0, true)).zzb(zzddl.zza("TinkHybridEncrypt", "HybridEncrypt", "EciesAeadHkdfPublicKey", 0, true)).zzhe("TINK_HYBRID_1_0_0").zzazm());

    @Deprecated
    public static final zzdjs zzgsp = (zzdjs) ((zzdqd) zzdjs.zzavf().zza(zzgso).zzhe("TINK_HYBRID_1_1_0").zzazm());
    public static final zzdjs zzgsq = (zzdjs) ((zzdqd) zzdjs.zzavf().zza(zzdek.zzgsq).zzb(zzddl.zza("TinkHybridDecrypt", "HybridDecrypt", "EciesAeadHkdfPrivateKey", 0, true)).zzb(zzddl.zza("TinkHybridEncrypt", "HybridEncrypt", "EciesAeadHkdfPublicKey", 0, true)).zzhe("TINK_HYBRID").zzazm());

    public static void zzapu() throws GeneralSecurityException {
        zzdek.zzapu();
        zzdef.zza("TinkHybridEncrypt", new zzdfi());
        zzdef.zza("TinkHybridDecrypt", new zzdfe());
        zzddl.zza(zzgsq);
    }

    static {
        try {
            zzapu();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
