package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdek {

    @Deprecated
    public static final zzdjs zzgso = (zzdjs) ((zzdqd) zzdjs.zzavf().zza(zzdfs.zzgso).zzb(zzddl.zza("TinkAead", "Aead", "AesCtrHmacAeadKey", 0, true)).zzb(zzddl.zza("TinkAead", "Aead", "AesEaxKey", 0, true)).zzb(zzddl.zza("TinkAead", "Aead", "AesGcmKey", 0, true)).zzb(zzddl.zza("TinkAead", "Aead", "ChaCha20Poly1305Key", 0, true)).zzb(zzddl.zza("TinkAead", "Aead", "KmsAeadKey", 0, true)).zzb(zzddl.zza("TinkAead", "Aead", "KmsEnvelopeAeadKey", 0, true)).zzhe("TINK_AEAD_1_0_0").zzazm());

    @Deprecated
    private static final zzdjs zzgsp = (zzdjs) ((zzdqd) zzdjs.zzavf().zza(zzgso).zzhe("TINK_AEAD_1_1_0").zzazm());
    public static final zzdjs zzgsq = (zzdjs) ((zzdqd) zzdjs.zzavf().zza(zzdfs.zzgsq).zzb(zzddl.zza("TinkAead", "Aead", "AesCtrHmacAeadKey", 0, true)).zzb(zzddl.zza("TinkAead", "Aead", "AesEaxKey", 0, true)).zzb(zzddl.zza("TinkAead", "Aead", "AesGcmKey", 0, true)).zzb(zzddl.zza("TinkAead", "Aead", "ChaCha20Poly1305Key", 0, true)).zzb(zzddl.zza("TinkAead", "Aead", "KmsAeadKey", 0, true)).zzb(zzddl.zza("TinkAead", "Aead", "KmsEnvelopeAeadKey", 0, true)).zzb(zzddl.zza("TinkAead", "Aead", "XChaCha20Poly1305Key", 0, true)).zzhe("TINK_AEAD").zzazm());

    public static void zzapu() throws GeneralSecurityException {
        zzdfs.zzapu();
        zzdef.zza("TinkAead", new zzdel());
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
