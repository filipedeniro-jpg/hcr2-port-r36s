package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdel implements zzddm<zzddj> {
    @Override // com.google.android.gms.internal.ads.zzddm
    public final zzddu<zzddj> zzb(String str, String str2, int i) throws GeneralSecurityException {
        zzddu<zzddj> zzdeoVar;
        String lowerCase = str2.toLowerCase();
        if (((lowerCase.hashCode() == 2989895 && lowerCase.equals("aead")) ? (byte) 0 : (byte) -1) != 0) {
            throw new GeneralSecurityException(String.format("No support for primitive '%s'.", str2));
        }
        switch (str) {
            case "type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey":
                zzdeoVar = new zzdeo();
                break;
            case "type.googleapis.com/google.crypto.tink.AesEaxKey":
                zzdeoVar = new zzdep();
                break;
            case "type.googleapis.com/google.crypto.tink.AesGcmKey":
                zzdeoVar = new zzdes();
                break;
            case "type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key":
                zzdeoVar = new zzder();
                break;
            case "type.googleapis.com/google.crypto.tink.KmsAeadKey":
                zzdeoVar = new zzdeu();
                break;
            case "type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey":
                zzdeoVar = new zzdew();
                break;
            case "type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key":
                zzdeoVar = new zzdev();
                break;
            default:
                throw new GeneralSecurityException(String.format("No support for primitive 'Aead' with key type '%s'.", str));
        }
        if (zzdeoVar.getVersion() >= i) {
            return zzdeoVar;
        }
        throw new GeneralSecurityException(String.format("No key manager for key type '%s' with version at least %d.", str, Integer.valueOf(i)));
    }

    @Override // com.google.android.gms.internal.ads.zzddm
    public final zzdee<zzddj> zzaph() {
        return new zzden();
    }
}
