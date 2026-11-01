package com.google.android.gms.internal.ads;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdei {
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    public static zzdjf zzc(zzdje zzdjeVar) {
        zzdjf.zza zzaVarZzew = zzdjf.zzaun().zzew(zzdjeVar.zzaud());
        for (zzdje.zza zzaVar : zzdjeVar.zzaue()) {
            zzaVarZzew.zzb((zzdjf.zzb) ((zzdqd) zzdjf.zzb.zzaup().zzhc(zzaVar.zzauj().zzatp()).zzc(zzaVar.zzapn()).zzc(zzaVar.zzapo()).zzex(zzaVar.zzauk()).zzazm()));
        }
        return (zzdjf) ((zzdqd) zzaVarZzew.zzazm());
    }

    public static void zzd(zzdje zzdjeVar) throws GeneralSecurityException {
        int iZzaud = zzdjeVar.zzaud();
        int i = 0;
        boolean z = false;
        boolean z2 = true;
        for (zzdje.zza zzaVar : zzdjeVar.zzaue()) {
            if (zzaVar.zzapn() == zzdiy.ENABLED) {
                if (!zzaVar.zzaui()) {
                    throw new GeneralSecurityException(String.format("key %d has no key data", Integer.valueOf(zzaVar.zzauk())));
                }
                if (zzaVar.zzapo() == zzdjq.UNKNOWN_PREFIX) {
                    throw new GeneralSecurityException(String.format("key %d has unknown prefix", Integer.valueOf(zzaVar.zzauk())));
                }
                if (zzaVar.zzapn() == zzdiy.UNKNOWN_STATUS) {
                    throw new GeneralSecurityException(String.format("key %d has unknown status", Integer.valueOf(zzaVar.zzauk())));
                }
                if (zzaVar.zzauk() == iZzaud) {
                    if (z) {
                        throw new GeneralSecurityException("keyset contains multiple primary keys");
                    }
                    z = true;
                }
                if (zzaVar.zzauj().zzatr() != zzdiu.zzb.ASYMMETRIC_PUBLIC) {
                    z2 = false;
                }
                i++;
            }
        }
        if (i == 0) {
            throw new GeneralSecurityException("keyset must contain at least one ENABLED key");
        }
        if (!z && !z2) {
            throw new GeneralSecurityException("keyset doesn't contain a valid primary key");
        }
    }

    public static byte[] zzg(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (i != -1) {
                byteArrayOutputStream.write(bArr, 0, i);
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }
}
