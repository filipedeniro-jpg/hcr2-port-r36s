package com.google.android.gms.internal.firebase_auth;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzgm implements Serializable, Iterable<Byte> {
    public static final zzgm zza = new zzgw(zzib.zzb);
    private static final zzgs zzb;
    private static final Comparator<zzgm> zzd;
    private int zzc = 0;

    zzgm() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int zzb(byte b) {
        return b & 255;
    }

    public abstract boolean equals(Object obj);

    public abstract byte zza(int i);

    public abstract int zza();

    protected abstract int zza(int i, int i2, int i3);

    public abstract zzgm zza(int i, int i2);

    protected abstract String zza(Charset charset);

    abstract void zza(zzgn zzgnVar) throws IOException;

    abstract byte zzb(int i);

    public abstract boolean zzc();

    public static zzgm zza(byte[] bArr, int i, int i2) {
        zzb(i, i + i2, bArr.length);
        return new zzgw(zzb.zza(bArr, i, i2));
    }

    public static zzgm zza(byte[] bArr) {
        return zza(bArr, 0, bArr.length);
    }

    static zzgm zzb(byte[] bArr) {
        return new zzgw(bArr);
    }

    public static zzgm zza(String str) {
        return new zzgw(str.getBytes(zzib.zza));
    }

    public final String zzb() {
        return zza() == 0 ? "" : zza(zzib.zza);
    }

    public final int hashCode() {
        int iZza = this.zzc;
        if (iZza == 0) {
            int iZza2 = zza();
            iZza = zza(iZza2, 0, iZza2);
            if (iZza == 0) {
                iZza = 1;
            }
            this.zzc = iZza;
        }
        return iZza;
    }

    static zzgu zzc(int i) {
        return new zzgu(i, null);
    }

    protected final int zzd() {
        return this.zzc;
    }

    static int zzb(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            StringBuilder sb = new StringBuilder(32);
            sb.append("Beginning index: ");
            sb.append(i);
            sb.append(" < 0");
            throw new IndexOutOfBoundsException(sb.toString());
        }
        if (i2 < i) {
            StringBuilder sb2 = new StringBuilder(66);
            sb2.append("Beginning index larger than ending index: ");
            sb2.append(i);
            sb2.append(", ");
            sb2.append(i2);
            throw new IndexOutOfBoundsException(sb2.toString());
        }
        StringBuilder sb3 = new StringBuilder(37);
        sb3.append("End index: ");
        sb3.append(i2);
        sb3.append(" >= ");
        sb3.append(i3);
        throw new IndexOutOfBoundsException(sb3.toString());
    }

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(zza()));
    }

    @Override // java.lang.Iterable
    public /* synthetic */ Iterator<Byte> iterator() {
        return new zzgp(this);
    }

    static {
        zzgp zzgpVar = null;
        zzb = zzgl.zza() ? new zzgz(zzgpVar) : new zzgq(zzgpVar);
        zzd = new zzgo();
    }
}
