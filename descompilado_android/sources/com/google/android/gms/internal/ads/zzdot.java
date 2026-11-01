package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzdot implements Serializable, Iterable<Byte> {
    public static final zzdot zzhfn = new zzdpd(zzdqe.zzhkv);
    private static final zzdoz zzhfo;
    private static final Comparator<zzdot> zzhfp;
    private int zzhev = 0;

    zzdot() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int zzb(byte b) {
        return b & 255;
    }

    public abstract boolean equals(Object obj);

    public abstract int size();

    protected abstract String zza(Charset charset);

    abstract void zza(zzdou zzdouVar) throws IOException;

    protected abstract void zza(byte[] bArr, int i, int i2, int i3);

    public abstract boolean zzaxj();

    public abstract zzdpf zzaxk();

    public abstract byte zzfn(int i);

    abstract byte zzfo(int i);

    protected abstract int zzg(int i, int i2, int i3);

    public abstract zzdot zzy(int i, int i2);

    public static zzdot zzh(byte[] bArr, int i, int i2) {
        zzh(i, i + i2, bArr.length);
        return new zzdpd(zzhfo.zzj(bArr, i, i2));
    }

    public static zzdot zzy(byte[] bArr) {
        return zzh(bArr, 0, bArr.length);
    }

    static zzdot zzz(byte[] bArr) {
        return new zzdpd(bArr);
    }

    public static zzdot zzhh(String str) {
        return new zzdpd(str.getBytes(zzdqe.UTF_8));
    }

    public final byte[] toByteArray() {
        int size = size();
        if (size == 0) {
            return zzdqe.zzhkv;
        }
        byte[] bArr = new byte[size];
        zza(bArr, 0, 0, size);
        return bArr;
    }

    public final String zzaxi() {
        return size() == 0 ? "" : zza(zzdqe.UTF_8);
    }

    public final int hashCode() {
        int iZzg = this.zzhev;
        if (iZzg == 0) {
            int size = size();
            iZzg = zzg(size, 0, size);
            if (iZzg == 0) {
                iZzg = 1;
            }
            this.zzhev = iZzg;
        }
        return iZzg;
    }

    static zzdpb zzfp(int i) {
        return new zzdpb(i, null);
    }

    protected final int zzaxl() {
        return this.zzhev;
    }

    static int zzh(int i, int i2, int i3) {
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
        return String.format("<ByteString@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size()));
    }

    @Override // java.lang.Iterable
    public /* synthetic */ Iterator<Byte> iterator() {
        return new zzdow(this);
    }

    static {
        zzdow zzdowVar = null;
        zzhfo = zzdoq.zzaxg() ? new zzdpg(zzdowVar) : new zzdox(zzdowVar);
        zzhfp = new zzdov();
    }
}
