package com.google.android.gms.internal.ads;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdmg {
    private ECPublicKey zzhbr;

    public zzdmg(ECPublicKey eCPublicKey) {
        this.zzhbr = eCPublicKey;
    }

    public final zzdmj zza(String str, byte[] bArr, byte[] bArr2, int i, zzdmz zzdmzVar) throws GeneralSecurityException {
        byte[] bArr3;
        KeyPair keyPairZza = zzdmv.zza(this.zzhbr.getParams());
        ECPublicKey eCPublicKey = (ECPublicKey) keyPairZza.getPublic();
        ECPrivateKey eCPrivateKey = (ECPrivateKey) keyPairZza.getPrivate();
        ECPublicKey eCPublicKey2 = this.zzhbr;
        try {
            ECParameterSpec params = eCPublicKey2.getParams();
            ECParameterSpec params2 = eCPrivateKey.getParams();
            int i2 = 1;
            if (!(params.getCurve().equals(params2.getCurve()) && params.getGenerator().equals(params2.getGenerator()) && params.getOrder().equals(params2.getOrder()) && params.getCofactor() == params2.getCofactor())) {
                throw new GeneralSecurityException("invalid public key spec");
            }
            byte[] bArrZza = zzdmv.zza(eCPrivateKey, eCPublicKey2.getW());
            EllipticCurve curve = eCPublicKey.getParams().getCurve();
            ECPoint w = eCPublicKey.getW();
            zzdmv.zza(w, curve);
            int iBitLength = (zzdmv.zza(curve).subtract(BigInteger.ONE).bitLength() + 7) / 8;
            int i3 = zzdmu.zzhcu[zzdmzVar.ordinal()];
            if (i3 == 1) {
                int i4 = (iBitLength * 2) + 1;
                bArr3 = new byte[i4];
                byte[] byteArray = w.getAffineX().toByteArray();
                byte[] byteArray2 = w.getAffineY().toByteArray();
                System.arraycopy(byteArray2, 0, bArr3, i4 - byteArray2.length, byteArray2.length);
                System.arraycopy(byteArray, 0, bArr3, (iBitLength + 1) - byteArray.length, byteArray.length);
                bArr3[0] = 4;
            } else if (i3 != 2) {
                if (i3 == 3) {
                    int i5 = iBitLength + 1;
                    byte[] bArr4 = new byte[i5];
                    byte[] byteArray3 = w.getAffineX().toByteArray();
                    System.arraycopy(byteArray3, 0, bArr4, i5 - byteArray3.length, byteArray3.length);
                    bArr4[0] = (byte) (w.getAffineY().testBit(0) ? 3 : 2);
                    bArr3 = bArr4;
                } else {
                    String strValueOf = String.valueOf(zzdmzVar);
                    StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 15);
                    sb.append("invalid format:");
                    sb.append(strValueOf);
                    throw new GeneralSecurityException(sb.toString());
                }
            } else {
                int i6 = iBitLength * 2;
                bArr3 = new byte[i6];
                byte[] byteArray4 = w.getAffineX().toByteArray();
                if (byteArray4.length > iBitLength) {
                    byteArray4 = Arrays.copyOfRange(byteArray4, byteArray4.length - iBitLength, byteArray4.length);
                }
                byte[] byteArray5 = w.getAffineY().toByteArray();
                if (byteArray5.length > iBitLength) {
                    byteArray5 = Arrays.copyOfRange(byteArray5, byteArray5.length - iBitLength, byteArray5.length);
                }
                System.arraycopy(byteArray5, 0, bArr3, i6 - byteArray5.length, byteArray5.length);
                System.arraycopy(byteArray4, 0, bArr3, iBitLength - byteArray4.length, byteArray4.length);
            }
            byte[] bArrZza2 = zzdlu.zza(bArr3, bArrZza);
            Mac macZzhf = zzdnb.zzhdm.zzhf(str);
            if (i > macZzhf.getMacLength() * 255) {
                throw new GeneralSecurityException("size too large");
            }
            if (bArr == null || bArr.length == 0) {
                macZzhf.init(new SecretKeySpec(new byte[macZzhf.getMacLength()], str));
            } else {
                macZzhf.init(new SecretKeySpec(bArr, str));
            }
            byte[] bArrDoFinal = macZzhf.doFinal(bArrZza2);
            byte[] bArr5 = new byte[i];
            macZzhf.init(new SecretKeySpec(bArrDoFinal, str));
            byte[] bArrDoFinal2 = new byte[0];
            int length = 0;
            while (true) {
                macZzhf.update(bArrDoFinal2);
                macZzhf.update(bArr2);
                macZzhf.update((byte) i2);
                bArrDoFinal2 = macZzhf.doFinal();
                if (bArrDoFinal2.length + length < i) {
                    System.arraycopy(bArrDoFinal2, 0, bArr5, length, bArrDoFinal2.length);
                    length += bArrDoFinal2.length;
                    i2++;
                } else {
                    System.arraycopy(bArrDoFinal2, 0, bArr5, length, i - length);
                    return new zzdmj(bArr3, bArr5);
                }
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new GeneralSecurityException(e.toString());
        }
    }
}
