package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdnp implements zzdea {
    private final Mac zzhee;
    private final int zzhef;
    private final String zzheg;
    private final Key zzheh;

    public zzdnp(String str, Key key, int i) throws GeneralSecurityException {
        if (i < 10) {
            throw new InvalidAlgorithmParameterException("tag size too small, need at least 10 bytes");
        }
        if (key.getEncoded().length < 16) {
            throw new InvalidAlgorithmParameterException("key size too small, need at least 16 bytes");
        }
        byte b = -1;
        int iHashCode = str.hashCode();
        if (iHashCode != -1823053428) {
            if (iHashCode != 392315118) {
                if (iHashCode == 392317873 && str.equals("HMACSHA512")) {
                    b = 2;
                }
            } else if (str.equals("HMACSHA256")) {
                b = 1;
            }
        } else if (str.equals("HMACSHA1")) {
            b = 0;
        }
        if (b != 0) {
            if (b != 1) {
                if (b != 2) {
                    String strValueOf = String.valueOf(str);
                    throw new NoSuchAlgorithmException(strValueOf.length() != 0 ? "unknown Hmac algorithm: ".concat(strValueOf) : new String("unknown Hmac algorithm: "));
                }
                if (i > 64) {
                    throw new InvalidAlgorithmParameterException("tag size too big");
                }
            } else if (i > 32) {
                throw new InvalidAlgorithmParameterException("tag size too big");
            }
        } else if (i > 20) {
            throw new InvalidAlgorithmParameterException("tag size too big");
        }
        this.zzheg = str;
        this.zzhef = i;
        this.zzheh = key;
        this.zzhee = zzdnb.zzhdm.zzhf(str);
        this.zzhee.init(key);
    }

    @Override // com.google.android.gms.internal.ads.zzdea
    public final byte[] zzk(byte[] bArr) throws GeneralSecurityException {
        Mac macZzhf;
        try {
            macZzhf = (Mac) this.zzhee.clone();
        } catch (CloneNotSupportedException unused) {
            macZzhf = zzdnb.zzhdm.zzhf(this.zzheg);
            macZzhf.init(this.zzheh);
        }
        macZzhf.update(bArr);
        byte[] bArr2 = new byte[this.zzhef];
        System.arraycopy(macZzhf.doFinal(), 0, bArr2, 0, this.zzhef);
        return bArr2;
    }
}
