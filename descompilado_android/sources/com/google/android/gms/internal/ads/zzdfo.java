package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdfo implements zzdmc {
    private final String zzgsz;
    private final int zzgta;
    private zzdgx zzgtb;
    private zzdfv zzgtc;
    private int zzgtd;

    zzdfo(zzdja zzdjaVar) throws GeneralSecurityException {
        this.zzgsz = zzdjaVar.zzatp();
        if (this.zzgsz.equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
            try {
                zzdgy zzdgyVarZzak = zzdgy.zzak(zzdjaVar.zzatq());
                this.zzgtb = (zzdgx) zzdef.zzb(zzdjaVar);
                this.zzgta = zzdgyVarZzak.getKeySize();
                return;
            } catch (zzdqn e) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesGcmKeyFormat", e);
            }
        }
        if (!this.zzgsz.equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
            String strValueOf = String.valueOf(this.zzgsz);
            throw new GeneralSecurityException(strValueOf.length() != 0 ? "unsupported AEAD DEM key type: ".concat(strValueOf) : new String("unsupported AEAD DEM key type: "));
        }
        try {
            zzdfw zzdfwVarZzv = zzdfw.zzv(zzdjaVar.zzatq());
            this.zzgtc = (zzdfv) zzdef.zzb(zzdjaVar);
            this.zzgtd = zzdfwVarZzv.zzaqa().getKeySize();
            this.zzgta = this.zzgtd + zzdfwVarZzv.zzaqb().getKeySize();
        } catch (zzdqn e2) {
            throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesCtrHmacAeadKeyFormat", e2);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdmc
    public final int zzapv() {
        return this.zzgta;
    }

    @Override // com.google.android.gms.internal.ads.zzdmc
    public final zzddj zzl(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length != this.zzgta) {
            throw new GeneralSecurityException("Symmetric key has incorrect length");
        }
        if (this.zzgsz.equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
            return (zzddj) zzdef.zza(this.zzgsz, (zzdgx) ((zzdqd) zzdgx.zzarj().zza(this.zzgtb).zzal(zzdot.zzh(bArr, 0, this.zzgta)).zzazm()), zzddj.class);
        }
        if (this.zzgsz.equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
            byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 0, this.zzgtd);
            byte[] bArrCopyOfRange2 = Arrays.copyOfRange(bArr, this.zzgtd, this.zzgta);
            zzdge zzdgeVar = (zzdge) ((zzdqd) zzdge.zzaqp().zza(this.zzgtc.zzapw()).zzab(zzdot.zzy(bArrCopyOfRange)).zzazm());
            return (zzddj) zzdef.zza(this.zzgsz, (zzdfv) ((zzdqd) zzdfv.zzapy().zzdv(this.zzgtc.getVersion()).zzb(zzdgeVar).zzb((zzdip) ((zzdqd) zzdip.zzatg().zza(this.zzgtc.zzapx()).zzbm(zzdot.zzy(bArrCopyOfRange2)).zzazm())).zzazm()), zzddj.class);
        }
        throw new GeneralSecurityException("unknown DEM key type");
    }
}
